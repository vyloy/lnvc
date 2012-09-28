/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.vovo.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private Image img;

    public void setImg(Image img) {
        this.img = img;
    }

    public ImagePanel(){
        
    }

    public ImagePanel(Image img) {
        this.img = img;
    }

    public ImagePanel(Image img, int width, int height) {
        this.img = img;
        this.setPreferredSize(new Dimension(width, height));
        this.setSize(width, height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int w = getWidth();
        int h = getHeight();
        g.drawImage(img, 0, 0, w, h, null);
    }
}
