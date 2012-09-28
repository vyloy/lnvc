/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.lvmc.event;

import com.lorent.common.util.ParaUtil;
import com.lorent.lvmc.util.Constants;
import com.lorent.lvmc.util.MessageUtil;
import com.lorent.lvmc.util.StringUtil;
import com.lorent.lvmc.util.XmlUtilParse;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.packet.Packet;

/**
 *
 * @author test
 */
public class MyVoteListener implements PacketListener{

    private Logger log = Logger.getLogger(MyVoteListener.class);
    
    @Override
    public void processPacket(Packet packet) {
        try {
            log.debug(packet.getXmlns());
            log.debug("接收数据：" + packet.toXML());
            //接收vote服务端发过来的数据
            log.debug("解析xml:");
    //        XmlUtilParse util=new XmlUtilParse(packet.toXML());
//            System.out.println("packet.toXML()============="+packet.toXML());
            Document doc = DocumentHelper.parseText(packet.toXML());
            XmlUtilParse util=new XmlUtilParse(doc);

    //        
    //        System.out.println("title:"+util.getElementValue("title"));
    //        System.out.println("title_remark:"+util.getElementValue("title_remark"));
    //        System.out.println("options:"+util.getElementValue("option"));
    //        System.out.println("id:"+util.getElementValue("id"));
    //        System.out.println("name:"+util.getElementValue("name"));
    //        System.out.println("name:"+util.getElementValue("name"));
            //只处理 投票返回的数据
            if (Constants.XMLNS.equals(util.getNamespaceURI())) {
                log.debug("operate_name:"+util.getNameValue());
                //返回不成功的数据
                if (!util.getResult()) {
                    ParaUtil paraUtil = ParaUtil.newInstance().setString("errorMsg", StringUtil.getErrorString("operateFail.msg")).setString("returnMsg",util.getFailMsg() );
                    MessageUtil.getInstance().sendMessage("meetingVote", new Object[]{paraUtil});
                    return;
                }
                //创建投票主题
                if ("create_vote".equals(util.getNameValue())) {
                    MessageUtil.getInstance().sendMessage("create_vote", new Object[]{util});
                    return;
                }
                //投票主题修改
                if("update_vote".equals(util.getNameValue())){
                    MessageUtil.getInstance().sendMessage("update_vote", new Object[]{util});
                    return;
                    
                }
                //增加投票表决项
                if("insert_select".equals(util.getNameValue())){
                    MessageUtil.getInstance().sendMessage("insert_select", new Object[]{util});
                    return;
                    
                }
                //修改表决项
                if("update_select".equals(util.getNameValue())){
                    MessageUtil.getInstance().sendMessage("update_select", new Object[]{util});
                    return;
                    
                }
                //删除表决项
                if("delete_select".equals(util.getNameValue())){
                    MessageUtil.getInstance().sendMessage("delete_select", new Object[]{util});
                    return;
                    
                }
                //统计结果 
                if("count".equals(util.getNameValue())){
                    MessageUtil.getInstance().sendMessage("count", new Object[]{util});
                    return;
                    
                }
                //提交投票
                if("dovote".equals(util.getNameValue())){
                    MessageUtil.getInstance().sendMessage("dovote", new Object[]{util});
                    return;
                    
                }
                //加载投票信息
                if("load_vote".equals(util.getNameValue())){
                    MessageUtil.getInstance().sendMessage("load_vote", new Object[]{util});
                    return;
                }
                //加载用户已投票信息
                if("search_vote_record".equals(util.getNameValue())){
                    MessageUtil.getInstance().sendMessage("search_vote_record", new Object[]{util});
                    return;
                }
            }
        } catch (DocumentException ex) {
            log.error("processPacket", ex);
        }
    }
    
}
