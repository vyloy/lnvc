package com.lorent.common.screenshot;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class ToolImagePanel extends ImagePanel
{
  private static final long serialVersionUID = 1L;
  int width;
  int height;
  int QUANTITY = 1;

  public ToolImagePanel()
  {
  }

  public ToolImagePanel(String imgUrl) {
    super(imgUrl);
  }

  public ToolImagePanel(Image img)
  {
    super(img);
  }

  public void refreshImg(Image img) {
    this.img = img;
    this.width = getWidth();
    this.height = getHeight();
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(Color.RED);
    g.drawLine(this.width / 2, 0, this.width / 2, this.height);
    g.drawLine(0, this.height / 2, this.width, this.height / 2);
    for (int i = 1; i < this.QUANTITY / 2 + 1; i++) {
      g.drawLine(this.width / 2 - i, 0, this.width / 2 - i, this.height);
      g.drawLine(0, this.height / 2 - i, this.width, this.height / 2 - i);
    }
    for (int i = 1; i < this.QUANTITY / 2 + 1; i++) {
      g.drawLine(this.width / 2 + i, 0, this.width / 2 + i, this.height);
      g.drawLine(0, this.height / 2 + i, this.width, this.height / 2 + i);
    }
  }
}