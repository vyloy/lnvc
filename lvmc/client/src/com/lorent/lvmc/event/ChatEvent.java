/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.lvmc.event;

/**
 *
 * @author Administrator
 */
public class ChatEvent extends BaseEvent{
    public ChatEvent(Object source, int type, Object[] paras){
		super(source, type, paras);
    }
}
