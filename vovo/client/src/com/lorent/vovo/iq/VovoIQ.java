package com.lorent.vovo.iq;

import org.jivesoftware.smack.packet.IQ;

public class VovoIQ extends IQ{

	private final String xml;
	
	public VovoIQ(final String xml,String spaceName) {    
        this.xml = "<query xmlns='"+spaceName+"'>\n" + xml + "\n</query>";    
	}    

	

	public String getChildElementXML() {    
	        return this.xml;    
	}
	
}
