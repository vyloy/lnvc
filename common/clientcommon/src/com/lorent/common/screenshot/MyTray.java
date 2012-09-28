package com.lorent.common.screenshot;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;

public class MyTray
  implements ActionListener, MouseListener
{
  private Image icon;
  private TrayIcon trayIcon;
  private SystemTray systemTray;
  private DesktopCapture frame;
  private PopupMenu pop = new PopupMenu();
  private MenuItem capture = new MenuItem("capture");
  private MenuItem show = new MenuItem("open");
  private MenuItem exit = new MenuItem("exit");

  public MyTray(DesktopCapture frame) {
    this.frame = frame;

    this.icon = new ImageIcon(getClass().getResource("/com/lorent/vovo/resource/images/screenshot/xiaomai.png")).getImage();

    if (SystemTray.isSupported()) {
      this.systemTray = SystemTray.getSystemTray();
      this.trayIcon = new TrayIcon(this.icon, "单击直接截图-EasyCapture", this.pop);
      this.pop.add(this.capture);
      this.pop.add(this.show);
      this.pop.add(this.exit);
      try
      {
        this.systemTray.add(this.trayIcon);
      } catch (AWTException e1) {
        e1.printStackTrace();
        this.trayIcon.addMouseListener(this);
      }
    }
    this.trayIcon.addMouseListener(this);
    this.show.addActionListener(this);
    this.exit.addActionListener(this);
    this.capture.addActionListener(this);
  }

  public void actionPerformed(ActionEvent e)
  {
    if (e.getSource() == this.show) {
      this.frame.iconed = false;
      this.frame.setVisible(true);
      this.frame.setExtendedState(0);
    } else if (e.getSource() == this.capture) {
      this.frame.capture();
    }
    else {
      System.exit(0);
    }
  }

  public void mouseClicked(MouseEvent e)
  {
    if ((e.getClickCount() == 1) && (e.getButton() != 3))
      this.frame.capture();
  }

  public void mouseEntered(MouseEvent arg0)
  {
  }

  public void mouseExited(MouseEvent arg0)
  {
  }

  public void mousePressed(MouseEvent arg0)
  {
  }

  public void mouseReleased(MouseEvent arg0)
  {
  }
}