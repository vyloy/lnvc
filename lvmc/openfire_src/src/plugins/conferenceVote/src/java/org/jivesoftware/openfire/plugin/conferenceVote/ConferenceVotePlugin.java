package org.jivesoftware.openfire.plugin.conferenceVote;

import java.io.File;

import org.dom4j.Element;
import org.jivesoftware.openfire.IQHandlerInfo;
import org.jivesoftware.openfire.XMPPServer;
import org.jivesoftware.openfire.auth.UnauthorizedException;
import org.jivesoftware.openfire.container.Plugin;
import org.jivesoftware.openfire.container.PluginManager;
import org.jivesoftware.openfire.handler.IQHandler;
import org.xmpp.packet.IQ;

public class ConferenceVotePlugin implements Plugin{

	@Override
	public void destroyPlugin() {
		
	}

	@Override
	public void initializePlugin(PluginManager manager, File pluginDirectory) {
		XMPPServer service = XMPPServer.getInstance();   
	    service.getIQRouter().addHandler(new ConferenceVoteIQHandler());
	}
	
	
	

}
