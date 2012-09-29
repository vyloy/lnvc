package org.jivesoftware.openfire.plugin.conferenceVote;

import java.util.ArrayList;
import java.util.List;

import org.xmpp.packet.IQ;

public class VoteBroadCastAction extends CountAction{

	@Override
	public List execute(IQ request) {
		List replys = new ArrayList();
		super.setOperateName(ActionFactory.BOARDCAST);
		List list = super.execute(request);
		replys.add(list.get(0));
		if(this.isFlag()){
			List<String> userAddress = this.getUserAddress(this.getRoomJidStr());
			replys.add(userAddress);
		}
		return replys;
	}

	

	
}
