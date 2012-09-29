/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.lvmc.bean;

/**
 *
 * @author test
 */
public class ChatComboxMemberModel {
    public String key;
    public String name;

    public ChatComboxMemberModel(String _key, String _name) {
        key = _key;
        name = _name;
    }

    public String toString() {
        return name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    
}
