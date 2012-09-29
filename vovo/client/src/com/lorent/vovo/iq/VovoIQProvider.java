package com.lorent.vovo.iq;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smack.util.StringUtils;
import org.xmlpull.v1.XmlPullParser;


public class VovoIQProvider implements IQProvider{
	
	private String spaceName;
	
	public VovoIQProvider(){
		super();
	}
	
	public VovoIQProvider(String domain){
		spaceName = domain;
	}

	@Override
	public IQ parseIQ(final XmlPullParser parser) throws Exception {
		final StringBuffer buffer = new StringBuffer();

		//        System.out.println("===="+parser.toString());
		// skip the <query> tag by calling parser.next()    
		while (true) {
			//            System.out.println("===="+parser.next());
			//System.out.println(parser.getText());
			switch (parser.next()) {
			case XmlPullParser.TEXT:
				// We need to escape characters like & and <    
				buffer.append(StringUtils.escapeForXML(parser.getText()));
				break;

			case XmlPullParser.START_TAG:
				int count = parser.getAttributeCount();
				if(count>0){
					buffer.append('<' + parser.getName() + ' ');
					for(int i=0;i<count;i++){
						buffer.append(parser.getAttributeName(i) + '=' + '"'
								+ StringUtils.escapeForXML(parser.getAttributeValue(i)) + '"');
					}

					buffer.append('>');
				}else{
					buffer.append('<' + parser.getName() + '>');
				}
				break;

			case XmlPullParser.END_TAG:
				if ("query".equals(parser.getName())) {
					// don't save the </query> end tag    
					return new VovoIQ(buffer.toString().trim(),spaceName);
				} else {
					buffer.append("</" + parser.getName() + '>');
					break;
				}
			default:
			}
		}
	}

}
