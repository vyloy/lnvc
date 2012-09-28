/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.vovo.ui;

import java.awt.Color;
import javax.swing.JProgressBar;

/**
 *
 * @author Administrator
 */
public class FileJProgressBar extends JProgressBar{
    
    private String key;
    private String fileName;
    private String userName;
    private String groupSessionID;
    
    public FileJProgressBar(String key,String fileName,String userName,String groupSessionID){
        this.key = key;
        this.fileName = fileName;
        this.userName = userName;
        this.groupSessionID = groupSessionID;
        setStringPainted(true);
        setBackground(Color.WHITE);
        setForeground(Color.green);
        setIndeterminate(false);
    }
}
