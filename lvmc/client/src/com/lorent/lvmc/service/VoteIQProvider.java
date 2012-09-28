/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.lvmc.service;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smack.util.StringUtils;
import org.xmlpull.v1.XmlPullParser;

/**
 *
 * @author test
 */
public class VoteIQProvider implements IQProvider{

    @Override
    public IQ parseIQ(final XmlPullParser parser) throws Exception {
        final StringBuffer buffer = new StringBuffer();   //        System.out.println("===="+parser.toString());
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
                    if ("operate".equals(parser.getName())) {
                        buffer.append('<' + parser.getName() + ' '
                                + parser.getAttributeName(0) + '=' + '"'
                                + parser.getAttributeValue(0) + '"' + '>');
                    } else {
                        buffer.append('<' + parser.getName() + '>');
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if ("query".equals(parser.getName())) {
                        // don't save the </query> end tag    
                        return new VoteIQ(buffer.toString().trim());
                    } else {
                        buffer.append("</" + parser.getName() + '>');
                        break;
                    }
                default:
            }
        }
    }

    
}
