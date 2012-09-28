package com.lorent.common.screenshot;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class DesktopCapture extends JFrame
  implements ActionListener
{
  private static final long serialVersionUID = 1L;
  JButton confirm;
  BufferedImage desktopImg;
  CaptureView view;
  MyTray tray;
  boolean iconed = false;

  public DesktopCapture() { super("EasyCapture");
    init();
    addWindowListener(new WindowAdapter() {
      public void windowIconified(WindowEvent e) {
        DesktopCapture.this.iconed = true;
        DesktopCapture.this.setVisible(false);
      }

      public void windowClosing(WindowEvent e)
      {
        int option = JOptionPane.showConfirmDialog(DesktopCapture.this, "是否最小化到托盘?", "提示:", 0);
        if (option == 0) {
          DesktopCapture.this.iconed = true;

          DesktopCapture.this.setVisible(false);
        } else {
          System.exit(0);
        }
      }
    });
    pack();
    setSize(350, 230);
    setLocation(500, 300);
    setDefaultCloseOperation(2);
    setResizable(false);
    setVisible(true);
  }

  void init()
  {
    setContentPane(new ImagePanel("/com/lorent/vovo/resource/images/screenshot/weixin_bg.jpg"));
    Container con = getContentPane();
    con.setLayout(new BorderLayout());
    this.confirm = new JButton("截图");
    this.confirm.addActionListener(this);
    JPanel jp = new JPanel();
    jp.setLayout(new FlowLayout());
    jp.setOpaque(false);
    jp.add(new ImageLabel("/com/lorent/vovo/resource/images/screenshot/blackCat.png"));
    jp.add(this.confirm);
    jp.add(new ImageLabel("/com/lorent/vovo/resource/images/screenshot/whiteCat.png"));
    JTextArea jta = new JTextArea();
    jta.setOpaque(false);
    jta.setEditable(false);
    jta.setForeground(Color.WHITE);
    jta.setFont(new Font("楷体", 0, 11));
    jta.setText("操作提示:--> 1.单击托盘图标-->进入截图状态\n\t2.双击右键------->退出截图状态\n\t3.双击左键------->保存截图\n\t4.单击右键---->重新选择截图区域   ^_^");
    con.add(jp, "Center");
    con.add(jta, "South");
    this.tray = new MyTray(this);
  }

  public void captureDesktop() throws Exception
  {
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    Rectangle rect = new Rectangle(d);
    this.desktopImg = new BufferedImage((int)d.getWidth(), (int)d.getHeight(), 6);
    GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
    GraphicsDevice device = environment.getDefaultScreenDevice();
    Robot robot = new Robot(device);
    this.desktopImg = robot.createScreenCapture(rect);
  }

  public void capture()
  {
    setVisible(false);
    try {
      Thread.sleep(200L);
      captureDesktop();
    }
    catch (Exception e1) {
      e1.printStackTrace();
    }

    if (this.view == null)
      this.view = null;//new CaptureView(this, this.desktopImg);
    else
      this.view.refreshBackGround(this.desktopImg);
  }

  public void saveCapture(int x1, int y1, int x2, int y2)
  {
    JFileChooser chooser = new JFileChooser();
    chooser.setFileFilter(new FileNameExtensionFilter("*.gif", new String[] { "gif" }));
    chooser.setFileFilter(new FileNameExtensionFilter("*.bmp", new String[] { "bmp" }));
    chooser.setFileFilter(new FileNameExtensionFilter("*.png", new String[] { "png" }));
    chooser.setSelectedFile(new File(chooser.getCurrentDirectory(), "截图"));
    int state = chooser.showSaveDialog(this);
    if (state == 0) {
      File file = chooser.getSelectedFile();
      FileFilter filter = chooser.getFileFilter();
      String descrition = filter.getDescription();
      String endWith = descrition.substring(2);
      captureToFile(this.desktopImg.getSubimage(x1, y1, Math.abs(x2 - x1), Math.abs(y2 - y1)), endWith, new File(file.getAbsoluteFile() + "." + endWith));
    }
  }

  public void captureToFile(BufferedImage img, String endWith, File file)
  {
    if (!file.exists()) {
      file.mkdir();
    }
    try
    {
      ImageIO.write(img, endWith, file);
    }
    catch (IOException e) {
      JOptionPane.showMessageDialog(null, "截图保存出错...");
      e.printStackTrace();
    }
  }

  public static void main(String[] args)
  {
    try
    {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      DesktopCapture desk = new DesktopCapture();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public void actionPerformed(ActionEvent e)
  {
    if (e.getActionCommand().equals("截图"))
      capture();
  }
}