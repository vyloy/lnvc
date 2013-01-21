package com.lorent.common.screenshot;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JTextArea;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;


public class CaptureView extends JWindow
  implements MouseListener, KeyListener, MouseMotionListener, WindowFocusListener, FocusListener
{
  private static final long serialVersionUID = 1L;
  private BufferedImage desktopImg;
  private boolean captured = false; private boolean draging = false; private boolean toolPanelAtRight = true;
  private int x = 0; private int y = 0; private int x1 = 0; private int y1 = 0; private int x2 = 1; private int y2 = 1;
  private int point_x;
  private int point_y;
  private Color point_color;
//  private DesktopCapture window;
  private ImagePanel toolPanel;
//  private final int TOOLPANEL_WIDTH = 200; private final int TOOLPANEL_HEIGHT = 300; private final int HALF_PICK_IMG = 40;
  private JTextArea infoArea;
  private ToolImagePanel pickImgPanel;
  private ScreenShotListener screenShotListener;

  public CaptureView(java.awt.Frame frame,ScreenShotListener screenShotListener)
  {
//    super((MainFrame)Vovo.getViewManager().getView(Constants.ViewKey.MAINFRAME.toString()));
	super(frame);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    Rectangle rect = new Rectangle(d);
    this.desktopImg = new BufferedImage((int)d.getWidth(), (int)d.getHeight(), 6);
    GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
    GraphicsDevice device = environment.getDefaultScreenDevice();
    Robot robot;
	try {
		robot = new Robot(device);
		desktopImg = robot.createScreenCapture(rect);
	} catch (AWTException e) {
		e.printStackTrace();
	}
//    this.desktopImg = img;
	this.screenShotListener = screenShotListener;
    setSize(Toolkit.getDefaultToolkit().getScreenSize());
    init();
    setVisible(true);
    setAlwaysOnTop(true);
    requestFocus();
  }

  void init() {
    setContentPane(new BackGroundPanel(this.desktopImg));
    setLayout(null);
    this.toolPanel = new ImagePanel("/com/lorent/common/screenshot/weixin_bg.jpg");
    this.toolPanel.setLayout(new BorderLayout());
    this.pickImgPanel = new ToolImagePanel();
    this.infoArea = new JTextArea();
    this.infoArea.setOpaque(false);
    this.infoArea.setEditable(false);
    this.infoArea.setForeground(Color.WHITE);
    this.infoArea.setFont(new Font("楷体", 0, 11));
    this.infoArea.setText("");
    this.toolPanel.add(this.pickImgPanel, "Center");
    this.toolPanel.add(this.infoArea, "South");
    this.toolPanel.setLocation(getWidth() - 200, 0);
    this.toolPanel.setSize(200, 300);
    getLayeredPane().add(this.toolPanel, 300);
    addKeyListener(this);
    addMouseListener(this);
    addMouseMotionListener(this);
  }

  public void refreshBackGround(BufferedImage img) {
    this.desktopImg = img;
    setContentPane(new BackGroundPanel(this.desktopImg));
    setVisible(true);
    setAlwaysOnTop(true);
    requestFocus();
  }

  public void paint(Graphics g) {
    super.paint(g);
    g.setColor(Color.BLACK);
    if (this.captured) {
      if (this.draging) {
        g.drawLine(this.point_x, 0, this.point_x, getHeight());
        g.drawLine(0, this.point_y, getWidth(), this.point_y);
      }
      confirmArea();
      if ((this.x1 < this.x2) && (this.y1 < this.y2))
        g.drawImage(this.desktopImg.getSubimage(this.x1, this.y1, Math.abs(this.x2 - this.x1), Math.abs(this.y2 - this.y1)), this.x1, this.y1, null);
      g.drawRect(this.x1, this.y1, Math.abs(this.x2 - this.x1), Math.abs(this.y2 - this.y1));
    } else {
      g.drawLine(this.point_x, 0, this.point_x, getHeight());
      g.drawLine(0, this.point_y, getWidth(), this.point_y);
    }
    repaintToolPanel();
  }

  public void repaintToolPanel()
  {
    if (this.toolPanelAtRight) {
      if ((this.point_x > getWidth() - 200 - 100) && (this.point_y < 400)) {
        this.toolPanel.setLocation(0, 0);
        this.toolPanelAtRight = false;
      }
    }
    else if ((this.point_x < 300) && (this.point_y < 400)) {
      this.toolPanel.setLocation(getWidth() - 200, 0);
      this.toolPanelAtRight = true;
    }

    this.point_color = new Color(this.desktopImg.getRGB(this.point_x, this.point_y));

    refreshInfoText();
  }

  public void refreshInfoText()
  {
    String text = new String("操作提示:\n1.双击右键--->退出截图状态\n2.双击左键--->保存截图\n3.单击右键--->重新选择截图区域");
    String infoString;
    if (this.captured)
      infoString = "X,Y: " + this.point_x + "," + this.point_y + "  W*H: " + Math.abs(this.x2 - this.x) + "*" + Math.abs(this.y2 - this.y) + 
        "\n当前RBG:(" + this.point_color.getRed() + "," + this.point_color.getGreen() + "," + this.point_color.getBlue() + ")\n" + text;
    else {
      infoString = "X,Y: " + this.point_x + "," + this.point_y + "  W*H: 0*0" + 
        "\n当前RBG:(" + this.point_color.getRed() + "," + this.point_color.getGreen() + "," + this.point_color.getBlue() + ")\n" + text;
    }
    Font font = new Font("宋体",Font.BOLD,12);
    this.infoArea.setFont(font);
    this.infoArea.setText(infoString);
    int pickImg_x;
    int pick_x1;
    int pick_x2;
    if (this.point_x - 40 < 0) {
      pick_x1 = 0;
      pick_x2 = this.point_x + 40;
      pickImg_x = 40 - this.point_x;
    }
    else
    {
      
      if (this.point_x + 40 > getWidth()) {
        pick_x1 = this.point_x - 40;
        pick_x2 = getWidth();
        pickImg_x = 0;
      } else {
        pick_x1 = this.point_x - 40;
        pick_x2 = this.point_x + 40;
        pickImg_x = 0;
      }
    }
    int pickImg_y;
    int pick_y1;
    int pick_y2;
    if (this.point_y - 40 < 0) {
      pick_y1 = 0;
      pick_y2 = this.point_y + 40;
      pickImg_y = 40 - this.point_y;
    }
    else
    {
      if (this.point_y + 40 > getHeight()) {
        pick_y1 = this.point_y - 40;
        pick_y2 = getHeight();
        pickImg_y = 0;
      } else {
        pick_y1 = this.point_y - 40;
        pick_y2 = this.point_y + 40;
        pickImg_y = 0;
      }
    }
    BufferedImage pickImg = new BufferedImage(80, 80, 1);
    Graphics pickGraphics = pickImg.getGraphics();
    pickGraphics.drawImage(this.desktopImg.getSubimage(pick_x1, pick_y1, pick_x2 - pick_x1, pick_y2 - pick_y1), pickImg_x, pickImg_y, Color.black, null);
    this.pickImgPanel.refreshImg(pickImg.getScaledInstance(200, 200, 16));
    this.toolPanel.validate();
  }

  public void confirmArea()
  {
    this.x1 = this.x;
    this.y1 = this.y;
    if (this.x2 < this.x1) {
      if (this.y2 < this.y1) {
        int temp = this.x1;
        this.x1 = this.x2;
        this.x2 = temp;
        temp = this.y1;
        this.y1 = this.y2;
        this.y2 = temp;
      } else {
        int temp = this.x1;
        this.x1 = this.x2;
        this.x2 = temp;
      }
    }
    else if (this.y2 < this.y1) {
      int temp = this.y1;
      this.y1 = this.y2;
      this.y2 = temp;
    }
  }

  public void exit()
  {
    this.x = 0;
    this.y = 0;
    this.x1 = 0;
    this.y1 = 0;
    this.x2 = 1;
    this.y2 = 1;
    this.point_x = 0;
    this.point_y = 0;
    this.captured = false;
    this.draging = false;
    this.toolPanel.setLocation(getWidth() - 200, 0);
    this.toolPanelAtRight = true;
    setVisible(false);
    screenShotListener.doExit();
  }
  
  public void saveCapture(int x1, int y1, int x2, int y2){
//	  setClipboardImage(this.desktopImg.getSubimage(x1, y1, Math.abs(x2 - x1), Math.abs(y2 - y1)));
	  if(Math.abs(x2 - x1)!=0 || Math.abs(y2 - y1)!=0){
		  screenShotListener.doSave(this.desktopImg.getSubimage(x1, y1, Math.abs(x2 - x1), Math.abs(y2 - y1)));
	  }
  }
  
  protected static void setClipboardImage(final Image image) {
		Transferable trans = new Transferable() {
			public DataFlavor[] getTransferDataFlavors() {
				return new DataFlavor[] { DataFlavor.imageFlavor };
			}

			public boolean isDataFlavorSupported(DataFlavor flavor) {
				return DataFlavor.imageFlavor.equals(flavor);
			}

			public Object getTransferData(DataFlavor flavor)
					throws UnsupportedFlavorException, IOException {
				if (isDataFlavorSupported(flavor))
					return image;
				throw new UnsupportedFlavorException(flavor);
			}

		};
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(trans,
				null);
	}

  public void mouseClicked(MouseEvent e)
  {
    if (e.getButton() == 1) {
      if (e.getClickCount() == 2) {
//    	  SwingUtilities.invokeLater(new Runnable(){
//    		  public void run(){
//    			  setVisible(false);
//    	        saveCapture(CaptureView.this.x1, CaptureView.this.y1, CaptureView.this.x2, CaptureView.this.y2);
//    	        exit();
//    		  }
//    	  });
		if (Math.abs(x2 - x1) != 0 || Math.abs(y2 - y1) != 0) {
			setVisible(false);
			saveCapture(this.x1, this.y1, this.x2, this.y2);
			exit();
		}
      }
    } else if (e.getClickCount() == 2) {
//      if (!this.window.iconed) {
//        this.window.setVisible(true);
//        this.window.toFront();
//      }
      exit();
    }
  }

  public void mouseEntered(MouseEvent e)
  {
  }

  public void mouseExited(MouseEvent e)
  {
  }

  public void mousePressed(MouseEvent e)
  {
    if ((e.getButton() == 1) && 
      (!this.captured)) {
      this.point_x = e.getX();
      this.point_y = e.getY();
      this.x = this.point_x;
      this.y = this.point_y;
      this.draging = true;
      this.captured = true;
    }
  }

  public void mouseReleased(MouseEvent e)
  {
    if (e.getButton() == 1) {
      if (this.draging) {
        this.point_x = e.getX();
        this.point_y = e.getY();
        this.x2 = this.point_x;
        this.y2 = this.point_y;
        repaint();
        this.draging = false;
      }
    } else {
      this.draging = false;
      this.captured = false;
      this.point_x = e.getX();
      this.point_y = e.getY();
      repaint();
    }
  }

  public void mouseDragged(MouseEvent e)
  {
    if (this.draging) {
      this.point_x = e.getX();
      this.point_y = e.getY();
      this.x2 = this.point_x;
      this.y2 = this.point_y;
      repaint();
    }
  }

  public void mouseMoved(MouseEvent e)
  {
    if (!this.captured) {
      this.point_x = e.getX();
      this.point_y = e.getY();
      repaint();
    }
  }

  public void keyPressed(KeyEvent e)
  {
    if (e.getKeyCode() == 3) {
      setVisible(false);
      dispose();
    }
  }

  public void keyReleased(KeyEvent e)
  {
    System.out.println("aaa");
    if (e.getKeyCode() == 65) {
      setVisible(false);
      dispose();
    }
  }

  public void keyTyped(KeyEvent e)
  {
    if (e.getKeyCode() == 27) {
      setVisible(false);
      dispose();
    }
  }

	@Override
	public void windowGainedFocus(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void windowLostFocus(WindowEvent e) {
		
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		System.out.println("screenshot lose  focus========================");
		exit();
	}
}