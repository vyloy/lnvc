/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.lvmc.service;

import com.lorent.lvmc.util.Constants;
import org.jivesoftware.smack.packet.IQ;

/**
 *
 * @author test
 */
public class VoteIQ extends IQ{

    private final String xml;

    @Override
    public String getChildElementXML() {
        return this.xml;
    }

    public VoteIQ(final String xml) {
        this.xml = "<query xmlns='"+Constants.XMLNS+"'>\n" + xml + "\n</query>";
    }

}
