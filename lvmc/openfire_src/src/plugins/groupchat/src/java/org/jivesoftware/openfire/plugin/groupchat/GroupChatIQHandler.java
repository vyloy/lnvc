package org.jivesoftware.openfire.plugin.groupchat;

import java.util.List;
import java.util.Map;

import org.dom4j.Element;
import org.jivesoftware.openfire.IQHandlerInfo;
import org.jivesoftware.openfire.PacketException;
import org.jivesoftware.openfire.auth.UnauthorizedException;
import org.jivesoftware.openfire.handler.IQHandler;
import org.jivesoftware.openfire.plugin.groupchat.util.Constants;
import org.jivesoftware.util.LocaleUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xmpp.packet.IQ;
import org.xmpp.packet.Packet;
import org.xmpp.packet.PacketError;

public class GroupChatIQHandler extends IQHandler{   

	
	private static final Logger Log = LoggerFactory.getLogger(GroupChatIQHandler.class);
//    public static final String moduleName = "test";   
       
    private IQHandlerInfo info;   
    public GroupChatIQHandler(){   
        super(Constants.MODULE_NAME);   
        info = new IQHandlerInfo(Constants.PREFIX_NAME, Constants.SPACE_NAME);//设置监听的命名空间   
    }   
       
    @Override  
    public IQHandlerInfo getInfo() {   
        return info;//取得指定监听命名空间的IQHandeler   
    }   
    
    @Override
    public void process(Packet packet) throws PacketException {
        IQ iq = (IQ) packet;
        try {
        	List list = doHandle(iq);
        	
            if (list != null && list.size()==2) {
            	IQ reply = (IQ)list.get(0);
            	List<String> userJids = (List<String>)list.get(1);
            	if(userJids !=null && userJids.size()>0){
            		for(String userJid:userJids){
                		reply.setTo(userJid);
                		deliverer.deliver(reply);
                	}
            	}else{
            		deliverer.deliver(reply);
            	}
            	
            }else if(list != null && list.size()==1){
            	IQ reply = (IQ)list.get(0);
            	deliverer.deliver(reply);
            }
        }
        catch (org.jivesoftware.openfire.auth.UnauthorizedException e) {
        	e.printStackTrace();
        	Log.error("",e);
        	Log.error(LocaleUtils.getLocalizedString("admin.error"), e);
            if (iq != null) {
                try {
                    IQ response = IQ.createResultIQ(iq);
                    response.setChildElement(iq.getChildElement().createCopy());
                    response.setError(PacketError.Condition.not_authorized);
                    sessionManager.getSession(iq.getFrom()).process(response);
                }
                catch (Exception de) {
                    Log.error(LocaleUtils.getLocalizedString("admin.error"), de);
                    sessionManager.getSession(iq.getFrom()).close();
                }
            }
        }
        catch (Exception e) {
        	e.printStackTrace();
        	Log.error(e.getMessage(),e);
            Log.error(LocaleUtils.getLocalizedString("admin.error"), e);
            try {
                IQ response = IQ.createResultIQ(iq);
                response.setChildElement(iq.getChildElement().createCopy());
                response.setError(PacketError.Condition.internal_server_error);
                sessionManager.getSession(iq.getFrom()).process(response);
            }
            catch (Exception e1) {
                // Do nothing
            }
        }
    }
    
    
    public List doHandle(IQ packet) throws UnauthorizedException{
    	Element query = packet.getChildElement();//取得客户端发送过来的xml
        Element operate = query.element("operate");
        String command = operate.attributeValue("name");
        BaseAction action = ActionFactory.getInstance().getAction(command);
        List list = action.execute(packet);
    	return list;
    }

    @Override  
    public IQ handleIQ(IQ packet) throws UnauthorizedException {   
        
        return null;  
        
        

    }   
       
}   
