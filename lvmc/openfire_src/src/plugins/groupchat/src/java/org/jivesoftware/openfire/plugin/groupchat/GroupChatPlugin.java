package org.jivesoftware.openfire.plugin.groupchat;

import java.io.File;

import org.jivesoftware.openfire.XMPPServer;
import org.jivesoftware.openfire.container.Plugin;
import org.jivesoftware.openfire.container.PluginManager;
import org.jivesoftware.util.AlreadyExistsException;


public class GroupChatPlugin implements Plugin{

	@Override
	public void destroyPlugin() {
		
	}

	@Override
	public void initializePlugin(PluginManager manager, File pluginDirectory) {
		createGroupChatService();
		XMPPServer service = XMPPServer.getInstance();   
	    service.getIQRouter().addHandler(new GroupChatIQHandler());
	}
	
	public void createGroupChatService(){
		try {
			XMPPServer.getInstance().getMultiUserChatManager().createMultiUserChatService("groupchat", "群组", false);
		} catch (AlreadyExistsException e) {
		}
	}
}
