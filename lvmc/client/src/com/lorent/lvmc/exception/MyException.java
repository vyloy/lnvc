/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.lvmc.exception;

import com.lorent.lvmc.util.StringUtil;

/**
 *
 * @author jack
 */
public class MyException extends Exception{

    @Override
    public String getMessage() {
        String temp = super.getMessage();
        return StringUtil.getErrorString(temp);
    }
    
    public MyException(String message) {
        super(message);
    }

    public MyException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
