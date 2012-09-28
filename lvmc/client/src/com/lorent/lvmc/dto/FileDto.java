/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.lvmc.dto;

import javax.swing.ImageIcon;

/**
 *
 * @author Administrator
 */
public class FileDto {
    //文件名
    private String fileName;
    //文件图标
    private ImageIcon icon;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
}
