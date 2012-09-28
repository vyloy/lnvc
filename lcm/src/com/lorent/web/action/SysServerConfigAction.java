package com.lorent.web.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.lorent.exception.ArgsException;
import com.lorent.exception.CustomSqlException;
import com.lorent.model.McuServerBean;
import com.lorent.model.SysServerconfigBean;
import com.lorent.util.PropertiesUtil;
import com.lorent.util.StringUtil;

public class SysServerConfigAction extends BaseAction<SysServerconfigBean, Integer> {

	private static final Logger log = Logger.getLogger(SysServerConfigAction.class); 
	private SysServerconfigBean sysServerconfigBean;
	private McuServerBean server;
	
	
	
	public McuServerBean getMcuServerBean() {
		return server;
	}

	public void setMcuServerBean(McuServerBean mcuServerBean) {
		this.server = mcuServerBean;
	}

	public SysServerconfigBean getSysServerconfigBean() {
		return sysServerconfigBean;
	}

	public void setSysServerconfigBean(SysServerconfigBean sysServerconfigBean) {
		this.sysServerconfigBean = sysServerconfigBean;
	}

	@Override
	public SysServerconfigBean getModel() {
		return sysServerconfigBean;
	}

	public boolean checkbean(){
		if(!(null!=sysServerconfigBean.getAllownfilestyle()&&sysServerconfigBean.getAllownfilestyle().length()>0)){
			return false;
		}
		if(!(null!=sysServerconfigBean.getCstimeout())){
			return false;
		}
		if(!(null!=sysServerconfigBean.getDesktopport())){
			return false;
		}
		if(!(null!=sysServerconfigBean.getDesktopport2())){
			return false;
		}
		if(!(null!=sysServerconfigBean)){
			return false;
		}
		return false;
	}
	
	private int index=0;
	private String dealWithSpecialcode(String s){
		String re=s;
		while(null!=re&&re.indexOf(":",index)>0){
			index=re.indexOf(":",index);
			re=re.substring(0,re.indexOf(":", index))+"\\:"+re.substring(re.indexOf(":",index)+1);
			index=index+3;
		}
		return re;
	}
	private static DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
	
	public void validData()throws Exception{
		this.callBackUrl = "app/sysServerConfigAction_searchconfig_sysserverconfig.action";
		if(StringUtil.isEmpty(sysServerconfigBean.getCustomcodelength())){
			throw new ArgsException(PropertiesUtil.getMsgResource("page.sysconfig.customerCodeLength") + PropertiesUtil.getErrorResource("args.noempty"));
		}
		if(StringUtil.isEmpty(sysServerconfigBean.getConfnolength())){
			throw new ArgsException(PropertiesUtil.getMsgResource("page.sysconfig.confNoLength") + PropertiesUtil.getErrorResource("args.noempty"));
		}
		if(StringUtil.isEmpty(sysServerconfigBean.getLccnolength())){
			throw new ArgsException(PropertiesUtil.getMsgResource("page.sysconfig.lccnoLength") + PropertiesUtil.getErrorResource("args.noempty"));
		}
		int customerCodeLength = parseInt(sysServerconfigBean.getCustomcodelength());
		int confCodeLength = parseInt(sysServerconfigBean.getConfnolength());
		int lccnoLength = parseInt(sysServerconfigBean.getLccnolength());
		if(customerCodeLength <= 0){
			throw new ArgsException("args.customerCodeDesc");
		}
		if(confCodeLength <= customerCodeLength + 1){
			throw new ArgsException("args.confnoDesc");
		}
		if(lccnoLength <= customerCodeLength){
			throw new ArgsException("args.lccnoDesc");
		}
	}
	
	private int parseInt(String str)throws Exception{
		try{
			int i = Integer.parseInt(str);
			return i;
		}catch(Exception e){
			throw new ArgsException("args.mustInt");
		}
		
	}
	
	public String saveconfig()throws Exception{
		validData();
		
		//更新mcu数据库保存的配置信息
		if(null!=server.getId()){
			int saveflag=0;
			if(!oldServerconfigBean.getServerUrl().equals(sysServerconfigBean.getServerUrl())){
				server.setServerUrl(sysServerconfigBean.getServerUrl());
				saveflag=1;
			}
			try {
				if(saveflag==1)
				   serviceFacade.getMcuServerService().createOrUpdate(server);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//修改mcu配置文件
		if(null!=sysServerconfigBean){
			try {
			if(null!=sysServerconfigBean.getMcuserverip()&&!sysServerconfigBean.getMcuserverip().equals(oldServerconfigBean.getMcuserverip())){
				String re=sysServerconfigBean.getMcuserverip();
				if(null!=re&&re.indexOf(":")>0){
					this.index=0;
					re= dealWithSpecialcode(re);
				}
				String modify="sed -i -e \"s:\\("+this.mux_cs_ip+"\\).*:\\1"+" = "+re+":\" "+mcuconfigurl;
				log.info("执行命令："+modify);
				String[] cmds = new String[]{"/bin/sh", "-c", modify};
				Process pro;
				pro = Runtime.getRuntime().exec(cmds);
				int ret = pro.waitFor();
				BufferedReader br2 = new BufferedReader (new InputStreamReader(pro.getInputStream()));   
				String msg = null;
				while ((msg = br2.readLine()) != null) {
					log.info("修改命令执行结果"+msg);   
				}
				br2 = new BufferedReader (new InputStreamReader(pro.getErrorStream()));   
				msg = null;
				while ((msg = br2.readLine()) != null) {
					log.error("修改命令执行错误"+msg);
				}
				log.info("----------修改执行结束-----------");
			}
			} catch (Exception e) {
				e.printStackTrace();
			}	   
		}
		
		List<String> list=new ArrayList<String>();
		if(null!=sysServerconfigBean){
			
			//修改 openfire ip
			if(!oldServerconfigBean.getOpenfireip().equals(sysServerconfigBean.getOpenfireip())){
				PropertiesUtil.setConstant(this.openfireip, sysServerconfigBean.getOpenfireip());
			}
			//修改 openfire port
			if(!oldServerconfigBean.getOpenfireport().equals(sysServerconfigBean.getOpenfireport())){
                PropertiesUtil.setConstant(this.openfireport, sysServerconfigBean.getOpenfireport());
			}
			//修改 openfire timeout
			if(!oldServerconfigBean.getOpenfiretimeout().equals(sysServerconfigBean.getOpenfiretimeout())){
				PropertiesUtil.setConstant(this.openfiretimeout, sysServerconfigBean.getOpenfiretimeout());
			}
			
			//修改 customercodelength
			if(!oldServerconfigBean.getCustomcodelength().equals(sysServerconfigBean.getCustomcodelength())){
				PropertiesUtil.setConstant(this.customercodelength, sysServerconfigBean.getCustomcodelength());
			}
			//修改 confnolength
			if(!oldServerconfigBean.getConfnolength().equals(sysServerconfigBean.getConfnolength())){
				PropertiesUtil.setConstant(this.confnolength, sysServerconfigBean.getConfnolength());
			}
			//修改 confnumber
			if(!oldServerconfigBean.getLcmconfnumber().equals(sysServerconfigBean.getLcmconfnumber())){
				PropertiesUtil.setConstant(this.confnumber, sysServerconfigBean.getLcmconfnumber());
			}
			//修改lccnolength
			if(!oldServerconfigBean.getLccnolength().equals(sysServerconfigBean.getLccnolength())){
				PropertiesUtil.setConstant(this.lccnolength, sysServerconfigBean.getLccnolength());
			}
			//修改whitebordnumber
			if(!oldServerconfigBean.getWhitenumber().equals(sysServerconfigBean.getWhitenumber())){
				PropertiesUtil.setConstant(this.whitenumber, sysServerconfigBean.getWhitenumber());
			}
			//修改sms serialport
			if (!oldServerconfigBean.getSerialport().equals(sysServerconfigBean.getSerialport())) {
				PropertiesUtil.setConstant(this.serialport, sysServerconfigBean.getSerialport());
			}
			
			//修改邮件服务的配置  
				 String file = emailUrl;
//				 String projectname=ServletActionContext.getRequest().getContextPath();
				 String filepath=System.getProperty("user.dir").replace("bin", "conf");
//			     String path = filepath+projectname+file;
			     String path = filepath+file;
			     File filedoc = new File(path);   
			     if (filedoc.exists() && filedoc.isFile()) {  
			    	 try {
						Document doc=factory.newDocumentBuilder().parse(new FileInputStream(path));
						int modifyflag=0;
						  NodeList nodelist=doc.getElementsByTagName("Resource");
				            for(int i=0;i<nodelist.getLength();i++){
				                Element brandElement=(Element) nodelist.item(i);
//				                String brandName=brandElement.getAttribute("name");
				              //修改邮件服务的配置
				                if(null!=brandElement.getAttribute("mail.smtp.host")&&brandElement.getAttribute("mail.smtp.host").length()>0)
								if(!oldServerconfigBean.getEmailserver().equals(sysServerconfigBean.getEmailserver())){
									brandElement.setAttribute("mail.smtp.host", sysServerconfigBean.getEmailserver());
									modifyflag=1;
								}
				                if(null!=brandElement.getAttribute("mail.smtp.port")&&brandElement.getAttribute("mail.smtp.port").length()>0)
								if(!oldServerconfigBean.getEmailport().equals(sysServerconfigBean.getEmailport())){
									brandElement.setAttribute("mail.smtp.port", sysServerconfigBean.getEmailport());
									modifyflag=1;
								}
				                if(null!=brandElement.getAttribute("mail.smtp.username")&&brandElement.getAttribute("mail.smtp.username").length()>0)
								if(!oldServerconfigBean.getEmailacount().equals(sysServerconfigBean.getEmailacount())){
									brandElement.setAttribute("mail.smtp.username", sysServerconfigBean.getEmailacount());
									modifyflag=1;
								}
				                if(null!=brandElement.getAttribute("mail.smtp.password")&&brandElement.getAttribute("mail.smtp.password").length()>0)
								if(!oldServerconfigBean.getEmailpassword().equals(sysServerconfigBean.getEmailpassword())){
									brandElement.setAttribute("mail.smtp.password", sysServerconfigBean.getEmailpassword());
									modifyflag=1;
								}
				                
				                if(null!=brandElement.getAttribute("app.config.serverip")&&brandElement.getAttribute("app.config.serverip").length()>0)
				                	if(!oldServerconfigBean.getAppserverip().equals(sysServerconfigBean.getAppserverip())){
				                		brandElement.setAttribute("app.config.serverip", sysServerconfigBean.getAppserverip());
				                		modifyflag=1;
				                	}
				                if(null!=brandElement.getAttribute("app.config.server.manager")&&brandElement.getAttribute("app.config.server.manager").length()>0)
				                	if(!oldServerconfigBean.getAppservermanager().equals(sysServerconfigBean.getAppservermanager())){
				                		brandElement.setAttribute("app.config.server.manager", sysServerconfigBean.getAppservermanager());
				                		modifyflag=1;
				                	}
				                if(null!=brandElement.getAttribute("app.config.server.managerpwd")&&brandElement.getAttribute("app.config.server.managerpwd").length()>0)
				                	if(!oldServerconfigBean.getAppserverpwd().equals(sysServerconfigBean.getAppserverpwd())){
				                		brandElement.setAttribute("app.config.server.managerpwd", sysServerconfigBean.getAppserverpwd());
				                		modifyflag=1;
				                	}
				            }
				            
						if(1==modifyflag){
						Transformer trans = TransformerFactory.newInstance().newTransformer(); 
						trans.transform(new DOMSource(doc), new StreamResult(new FileOutputStream(path)));
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
			     }
		}
		try {
			oldServerconfigBean=(SysServerconfigBean) sysServerconfigBean.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		try {
			serviceFacade.getOperateRecordService().record("修改系统服务设置");
		} catch (CustomSqlException e) {
			log.error(e.getMessage(), e);
		}
		return SUCCESS;
	}

	
	public String dealSpecialString(String s){
		String result="";
		if(null!=s&&s.length()>0)
		   result=s.replaceAll(":", "\\\\"+"\\\\:");
		return result;
	}
	
	public String searchconfig() {
		initConfigInfo();
		return SUCCESS;
	}
	
	public String editconfig(){

		return SUCCESS;
	}
	public String restartServer(){
		try{
//			String command="/opt/lcp/tools/lorent.sh restart";
			String command="reboot";
			log.info("正在重启系统服务......");
			String[] cmds = new String[]{"/bin/sh", "-c", command};
			Process pro;
			pro = Runtime.getRuntime().exec(cmds);
	//		int ret = pro.waitFor();
	//		BufferedReader br2 = new BufferedReader (new InputStreamReader(pro.getInputStream()));   
	//		String msg = null;
	//		while ((msg = br2.readLine()) != null) {
	//			log.info("重启命令执行结果"+msg);   
	//		}
	//		br2 = new BufferedReader (new InputStreamReader(pro.getErrorStream()));   
	//		msg = null;
	//		while ((msg = br2.readLine()) != null) {
	//			log.error("重启命令执行错误"+msg);
	//		}
	//		log.info("----------重启执行结束-----------");
		}catch (Exception e) {
			log.error("执行重启命令出错:"+e.getMessage());
			e.printStackTrace();
		}
		return SUCCESS;
	}
	private SysServerconfigBean oldServerconfigBean=null;
	//muc 配置url
	String mcuconfigurl=PropertiesUtil.getConstant("mcuconfigurl");
	//mcu服务器ip
	private String mux_cs_ip="mux_cs_ip";
	//服务器ip
	private String cs_ip="cs_ip =";
	//获取lcm配置信息url
	String lcmconfurl=PropertiesUtil.getConstant("lcmconfurl");
	
	//客户号长度
	private String customercodelength="CUSTOMER_CODE_LENGTH";
	//会议号码长度
	private String confnolength="CONFERENCE_NO_LENGTH";
	////获取lcm会议数量
	private String confnumber="max.newconf";
	////获取即时号码长度
	private String lccnolength="LCC_NO_LENGTH";
	//白板数量
	private String whitenumber="lvmc.whiteboard.maxnumber";
	//即时通讯地址
	private String openfireip="openfire.serverIP";
	//即时通讯端口
	private String openfireport="openfire.serverPort";
	//即时通讯超时
	private String openfiretimeout="openfire.timeout";
	//邮件配置
	private String emailUrl=PropertiesUtil.getConstant("emailUrl");
	//短信猫通讯端口
	private String serialport="sms.serialport";
	/**
	 * 从配置文件读取配置信息  
	 */
	private void initConfigInfo(){
		if (null == sysServerconfigBean) {
			sysServerconfigBean = new SysServerconfigBean();
		}
//		Runtime.getRuntime().exec(command, envp)
		//邮件配置
		this.initEmailconfig();
		initMcuConfig();
		InitConfigInfo2();
		initServermcu();//通过java Runtime.exec调用shell
		try {
			oldServerconfigBean=(SysServerconfigBean) sysServerconfigBean.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}

	private void initServermcu() {
		try {
			String cmd = "sed -n '/^"+this.mux_cs_ip+"/p' "+mcuconfigurl;
			String[] cmds = new String[]{"/bin/sh", "-c", cmd};
			Process pro = Runtime.getRuntime().exec(cmds);	   
			int ret = pro.waitFor();
			BufferedReader br2 = new BufferedReader (new InputStreamReader(pro.getInputStream()));   
			String msg = null;
			while ((msg = br2.readLine()) != null) {
				log.info("命令执行结果"+msg);   
				if(msg.indexOf(this.mux_cs_ip)>=0){
					sysServerconfigBean.setMcuserverip(msg.substring(msg.indexOf(this.mux_cs_ip)+11));
				}
			}
			br2 = new BufferedReader (new InputStreamReader(pro.getErrorStream()));   
			msg = null;
			while ((msg = br2.readLine()) != null) {
				log.error("命令执行错误"+msg);
			}
			log.info("----------执行结束-----------");
			
		} catch (Exception e) {
            e.printStackTrace();
		}
	}
	
	public void initMcuConfig(){
		server=new McuServerBean();
		server.setServerStatus(1);
		try {
			List<McuServerBean> list=serviceFacade.getMcuServerService().getByExample(server);
			if(null!=list&&list.size()>0){
				server=list.get(0);
				sysServerconfigBean.setServerName(server.getServerName());
				sysServerconfigBean.setServerUrl(server.getServerUrl());
				sysServerconfigBean.setCsIP(server.getCsIP());
				sysServerconfigBean.setCsPort(""+server.getCsPort());
				sysServerconfigBean.setCsUsername(server.getCsUsername());
				sysServerconfigBean.setCsUserPasswd(server.getCsUserPasswd());
			}
		} catch (CustomSqlException e) {
			e.printStackTrace();
		}
	}
	
	public void initEmailconfig(){
		 String file = emailUrl;
//		 String file = "/Catalina/localhost/lcm.xml";
		 String filepath=System.getProperty("user.dir").replace("bin", "conf");
	     String path = filepath+file;
	     File filedoc = new File(path);   
	     if (filedoc.exists() && filedoc.isFile()) {  
	    	 try {
				Document doc=factory.newDocumentBuilder().parse(new FileInputStream(path));
				 NodeList nodelist=doc.getElementsByTagName("Resource");
		            for(int i=0;i<nodelist.getLength();i++){
		                Element brandElement=(Element) nodelist.item(i);
		              //读取邮件服务的配置
		                if(null!=brandElement.getAttribute("mail.smtp.host")&&brandElement.getAttribute("mail.smtp.host").length()>0)
		                	sysServerconfigBean.setEmailserver(brandElement.getAttribute("mail.smtp.host"));
		                if(null!=brandElement.getAttribute("mail.smtp.port")&&brandElement.getAttribute("mail.smtp.port").length()>0)
		                	sysServerconfigBean.setEmailport(brandElement.getAttribute("mail.smtp.port"));
		                if(null!=brandElement.getAttribute("mail.smtp.username")&&brandElement.getAttribute("mail.smtp.username").length()>0)
		                	sysServerconfigBean.setEmailacount(brandElement.getAttribute("mail.smtp.username"));
		                if(null!=brandElement.getAttribute("mail.smtp.password")&&brandElement.getAttribute("mail.smtp.password").length()>0)
		                	sysServerconfigBean.setEmailpassword(brandElement.getAttribute("mail.smtp.password"));
		            }
	    	 }catch (Exception e) {
				log.error(e.getMessage());
			}
	     }
	}
	private void InitConfigInfo2(){

		
		//获取客户号长度
		String customcodelength_sed=PropertiesUtil.getConstant(this.customercodelength);
		sysServerconfigBean.setCustomcodelength(customcodelength_sed);
		
		//获取会议号码长度
		String confnolength_sed=PropertiesUtil.getConstant(this.confnolength);
		sysServerconfigBean.setConfnolength(confnolength_sed);

		//获取lcm会议数量
		String lcmconfnumber_sed=PropertiesUtil.getConstant(this.confnumber);
		sysServerconfigBean.setLcmconfnumber(lcmconfnumber_sed);
		
		//获取即时号码长度
		String lccnolength_sed=PropertiesUtil.getConstant(this.lccnolength);
		sysServerconfigBean.setLccnolength(lccnolength_sed);
		
		//获取白板数量
		String whitenumber_sed=PropertiesUtil.getConstant(this.whitenumber);
		sysServerconfigBean.setWhitenumber(whitenumber_sed);
		
		//即时通讯地址
		String openfireip_sed=PropertiesUtil.getConstant(this.openfireip);
		sysServerconfigBean.setOpenfireip(openfireip_sed);
		
		//即时通讯端口
		String openfireport_sed=PropertiesUtil.getConstant(this.openfireport);
		sysServerconfigBean.setOpenfireport(openfireport_sed);
		
		//即时通讯超时
		String openfiretimeout_sed=PropertiesUtil.getConstant(this.openfiretimeout);
		sysServerconfigBean.setOpenfiretimeout(openfiretimeout_sed);
		
		//短信猫通讯端口
		String smsserialport_sed = PropertiesUtil.getConstant(this.serialport);
		sysServerconfigBean.setSerialport(smsserialport_sed);
	}
	
	
}
