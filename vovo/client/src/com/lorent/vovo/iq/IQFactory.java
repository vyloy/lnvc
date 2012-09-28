package com.lorent.vovo.iq;

import org.jivesoftware.smack.packet.IQ;

public class IQFactory {

	public IQ generateIQ(final String xml,final String spaceName){
		return new VovoIQ(xml,spaceName);
	}
	
	
}
