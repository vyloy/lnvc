package com.lorent.xmlrpc;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import com.lorent.dto.XmlrpcConf;
import com.lorent.dto.XmlrpcUser;


public class McuXmlrpc {
	
	/**初始化xmlrpc client
	 * @param xmlrpcUrl
	 * @return
	 * @throws Exception
	 */
	public static XmlRpcClient initClient(String xmlrpcUrl)throws Exception{
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
		config.setServerURL(new URL(xmlrpcUrl));
		config.setConnectionTimeout(10000);
		config.setReplyTimeout(20000);
		XmlRpcClient client = new XmlRpcClient();
		client.setConfig(config);
		return client;
	}
	
	
	/**创建会议
	 * @param xmlrpcUrl
	 * @param confno
	 * @param layout
	 * @param quality
	 * @return
	 * @throws Exception
	 */
	public static boolean createConf(String xmlrpcUrl, String confno, int layout, int quality)throws Exception{
		XmlRpcClient client = initClient(xmlrpcUrl);
		boolean flag = (Boolean)client.execute("lcm.create_conference", new Object[]{confno, layout, quality});
		if(!flag){
			throw new Exception("xmlrpc.mcucreateconffail");
		}
		return flag;
	}
	
	/**创建会议
	 * @param xmlrpcUrl
	 * @param confno
	 * @param layout
	 * @param quality
	 * @return
	 * @throws Exception
	 */
	public static boolean createForwardConference(String xmlrpcUrl, String confno)throws Exception{
		XmlRpcClient client = initClient(xmlrpcUrl);
		boolean flag = (Boolean)client.execute("lcm.create_forward_conference", new Object[]{confno});
		return flag;
	}
	
	/**修改会议
	 * @param xmlrpcUrl
	 * @param confno
	 * @param layout
	 * @param quality
	 * @return
	 * @throws Exception
	 */
	public static boolean updateConf(String xmlrpcUrl, String confno, int layout, int quality)throws Exception{
		XmlRpcClient client = initClient(xmlrpcUrl);
		boolean flag = (Boolean)client.execute("lcm.update_conference", new Object[]{confno, layout, quality});
		return flag;
	}
	
	public static boolean updateForwardConference(String xmlrpcUrl, String confno) throws Exception{
		XmlRpcClient client = initClient(xmlrpcUrl);
		boolean flag = (Boolean)client.execute("lcm.update_conference", new Object[]{confno});
		return flag;
	}
	
	/**删除会议
	 * @param xmlrpcUrl
	 * @param confno
	 * @return
	 * @throws Exception
	 */
	public static boolean removeConf(String xmlrpcUrl, String confno)throws Exception{
		XmlRpcClient client = initClient(xmlrpcUrl);
		boolean flag = (Boolean)client.execute("lcm.remove_conference", new Object[]{confno});
		return flag;
	}
	
	/**获取会议列表
	 * @param xmlrpcUrl
	 * @return
	 * @throws Exception
	 */
	public static List<XmlrpcConf> getConfList(String xmlrpcUrl)throws Exception{
		XmlRpcClient client = initClient(xmlrpcUrl);
//		Object[] o1 = (Object[])client.execute("lcm.get_conference_information_list", new Object[]{});
		Object[] o1 = (Object[])client.execute("lcm.get_mixed_conference_information_list", new Object[]{});
		List<XmlrpcConf> list = new ArrayList<XmlrpcConf>();
		for(Object o : o1){
			Map map = (Map)o;
			XmlrpcConf conf = new XmlrpcConf();
			conf.setConfno((String)map.get("id"));
			conf.setLayout((Integer)map.get("layout"));
			conf.setQuality((Integer)map.get("quality"));
			list.add(conf);
//			System.out.println("id=" + id + "&layout=" + layout + "&quality=" + quality);
		}
		return list;
	}
	
	public static List<XmlrpcConf> getForwardConferenceList(String xmlrpcUrl)throws Exception{
		XmlRpcClient client = initClient(xmlrpcUrl);
//		Object[] o1 = (Object[])client.execute("lcm.get_conference_information_list", new Object[]{});
		Object[] o1 = (Object[])client.execute("lcm.get_muxed_conference_information_list", new Object[]{});
		List<XmlrpcConf> list = new ArrayList<XmlrpcConf>();
		for(Object o : o1){
			Map map = (Map)o;
			XmlrpcConf conf = new XmlrpcConf();
			conf.setConfno((String)map.get("id"));
			list.add(conf);
//			System.out.println("id=" + id + "&layout=" + layout + "&quality=" + quality);
		}
		return list;
	}
	
	/**获取会议用户
	 * @param xmlrpcUrl
	 * @param confno
	 * @return
	 * @throws Exception
	 */
	public static List<XmlrpcUser> getConfUser(String xmlrpcUrl, String confno)throws Exception{
		XmlRpcClient client = initClient(xmlrpcUrl);
		Object[] o1 = (Object[])client.execute("lcm.get_conference_members", new Object[]{confno});
		List<XmlrpcUser> list = new ArrayList<XmlrpcUser>();
		for(Object o : o1){
			Map map = (Map)o;
			XmlrpcUser user = new XmlrpcUser();
			user.setLccno((String)map.get("member_id"));
			user.setIp((String)map.get("ip"));
			user.setPosition((Integer)map.get("pos"));
			user.setStatus((Integer)map.get("stat"));
			list.add(user);
//			System.out.println("id=" + id + "&layout=" + layout + "&quality=" + quality);
		}
		return list;
	}
	
	/**删除会议用户
	 * @param xmlrpcUrl
	 * @param confno
	 * @param lccno
	 * @return
	 * @throws Exception
	 */
	public static boolean removeConfUser(String xmlrpcUrl, String confno, String lccno)throws Exception{
		XmlRpcClient client = initClient(xmlrpcUrl);
		boolean flag = (Boolean)client.execute("lcm.delete_conference_member", new Object[]{confno, lccno});
		return flag;
	}
	
	/*
	 * 邀请会议用户
	 * @param xmlrpcUrl
	 * @param confno
	 * @param lccno
	 * @return
	 * @throws Exception
	 */
	public static boolean inviteConfUser(String xmlrpcUrl , String confno, String lccno) throws Exception{
		XmlRpcClient client = initClient(xmlrpcUrl);
		boolean flag = (Boolean)client.execute("lcm.invite_conference_member", new Object[]{confno, lccno});
		return flag;
	}
	
	public static XmlrpcConf getConfByConfNo(String xmlrpcUrl, String confno)throws Exception{
		List<XmlrpcConf> conflist = McuXmlrpc.getConfList(xmlrpcUrl);
		for(XmlrpcConf conf : conflist){
			if(conf.getConfno().equals(confno)){
				return conf;
			}
		}
		return null;
	}
	
	public static XmlrpcConf getForwardConferenceByConfNo(String xmlrpcUrl, String confno)throws Exception{
		List<XmlrpcConf> conflist = McuXmlrpc.getForwardConferenceList(xmlrpcUrl);
		for(XmlrpcConf conf : conflist){
			if(conf.getConfno().equals(confno)){
				return conf;
			}
		}
		return null;
	}
	
	public static boolean setConfUserVideo(String xmlrpcUrl, String confno, String lccno, int open)throws Exception{
		XmlRpcClient client = initClient(xmlrpcUrl);
		boolean flag = (Boolean)client.execute("lcm.enable_member_video", new Object[]{confno, lccno, open});
		return flag;
	}
	
	public static boolean setConfUserAudio(String xmlrpcUrl, String confno, String lccno, int open)throws Exception{
		XmlRpcClient client = initClient(xmlrpcUrl);
		boolean flag = (Boolean)client.execute("lcm.enable_member_audio", new Object[]{confno, lccno, open});
		return flag;
	}
	
	public static boolean setConfUserVideo(String xmlrpcUrl, String confno, String lccno, boolean enable)throws Exception{
		int open = 0;
		if(enable){
			open = 1;
		}
		return setConfUserVideo(xmlrpcUrl, confno, lccno, open);
	}
	
	public static boolean setConfUserAudio(String xmlrpcUrl, String confno, String lccno, boolean enable)throws Exception{
		int open = 0;
		if(enable){
			open = 1;
		}
		return setConfUserAudio(xmlrpcUrl, confno, lccno, open);
	}
	
	public static void main(String[] args)throws Exception{
		String xmlrpcUrl = "http://10.168.250.12:6080/lcmRPC"; 
//		McuXmlrpc.inviteConfUser(xmlrpcUrl, "326579", "33039");
		McuXmlrpc.createForwardConference(xmlrpcUrl,"9 8");
		/*
		String menu = "\n1.getConfList\n" +
				"2.getConfByConfno\n" +
				"3.getConfMembers\n" +
				"4.ListAllConfAndUsers\n" +
				"9.exit\n" +
				"input your choice";
		String xmlrpcUrl = "http://10.168.100.200:6080/lcmRPC"; 
		boolean flag = true;
		int choose = -1;
		Scanner sc = new Scanner(System.in);
		while(flag){
			System.out.println(menu);
			choose = sc.nextInt();
			if(choose == 1){
				List<XmlrpcConf> confs = getConfList(xmlrpcUrl);
				if(confs.size()==0){
					System.out.println("no conf");
				}else{
					for(XmlrpcConf conf : confs){
						System.out.println("confno=" + conf.getConfno() + "&layout=" + conf.getLayout() + "&quality" + conf.getQuality());
					}
				}
			}else if(choose == 2){
				System.out.println("input the confno");
				String confno = sc.next();
				XmlrpcConf conf = getConfByConfNo(xmlrpcUrl, confno);
				if(conf == null){
					System.out.println("no conf's no is " + confno);
				}else{
					System.out.println("confno=" + conf.getConfno() + "&layout=" + conf.getLayout() + "&quality" + conf.getQuality());
				}
			}else if(choose == 3){
				System.out.println("input the confno");
				String confno = sc.next();
				List<XmlrpcUser> users = getConfUser(xmlrpcUrl, confno);
				if(users.size() == 0){
					System.out.println("no user is conf");
				}else{
					for(XmlrpcUser user : users){
						System.out.println("lccno=" + user.getLccno() + "&ip=" + user.getIp() + "&position=" + user.getPosition() + "&status=" + user.getStatus());
					}
				}
			}else if(choose == 4){
				List<XmlrpcConf> confs = getConfList(xmlrpcUrl);
				if(confs.size() == 0){
					System.out.println("no conf");
				}else{
					for(XmlrpcConf conf : confs){
						System.out.println("confno=" + conf.getConfno() + "&layout=" + conf.getLayout() + "&quality" + conf.getQuality());
						List<XmlrpcUser> users = getConfUser(xmlrpcUrl, conf.getConfno());
						if(users.size() == 0){
							System.out.println("no user is conf");
						}else{
							for(XmlrpcUser user : users){
								System.out.println("\tlccno=" + user.getLccno() + "&ip=" + user.getIp() + "&position=" + user.getPosition() + "&status=" + user.getStatus());
							}
						}
					}
				}
			}else if(choose == 9){
				flag = false;
			}			
		}
		*/
	}
}
