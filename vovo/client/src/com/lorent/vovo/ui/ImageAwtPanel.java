/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.vovo.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;

/**
 *
 * @author Administrator
 */
public class ImageAwtPanel extends Panel {
    private static final long serialVersionUID = 1L;
    private Image img;

    public void setImg(Image img) {
        this.img = img;
    }

    public ImageAwtPanel(){
        
    }

    public ImageAwtPanel(Image img) {
        this.img = img;
    }

    public ImageAwtPanel(Image img, int width, int height) {
        this.img = img;
        this.setPreferredSize(new Dimension(width, height));
        this.setSize(width, height);
    }

    public void DrawImage(){
        Graphics graphics = getGraphics();
        int w = getWidth();
        int h = getHeight();
        graphics.drawImage(img, 0, 0, w, h, null);
    }
    
    @Override
    public void paint(Graphics g) {
        int w = getWidth();
        int h = getHeight();
        g.drawImage(img, 0, 0, w, h, null);
        super.paint(g);
    }

    
    
//    @Override
//    public void paintComponents(Graphics g) {
//        super.paintComponents(g);
//        int w = getWidth();
//        int h = getHeight();
//        g.drawImage(img, 0, 0, w, h, null);
//    }
//    
//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        int w = getWidth();
//        int h = getHeight();
//        g.drawImage(img, 0, 0, w, h, null);
//    }
}
