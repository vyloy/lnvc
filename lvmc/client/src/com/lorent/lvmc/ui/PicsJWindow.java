package com.lorent.lvmc.ui;
import   java.awt.*; 

import   javax.swing.*; 

import   java.awt.event.*; 
import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import org.apache.log4j.Logger;
/** 
  *   <p> Title:   pictures</p> 
  * 
  *   <p> Description:   </p> 
  * 
  *   <p> Copyright:   Copyright   (c)   2011 </p> 
  * 
  *   <p> Company:   </p> 
  * 
  *   @author   not   attributable 
  *   @version   1.0 
  */ 
public   class   PicsJWindow  extends   JDialog   { 
	private static final long serialVersionUID = 1L;
        private static Logger log = Logger.getLogger(PicsJWindow.class);
	GridLayout   gridLayout1   =   new   GridLayout(7,15); 
    JLabel[]   ico=new   JLabel[105]; /*放表情*/
    int  i;
    ChatMainPanel   owner;
//    String[] intro = {"","","","","","","","","","","","","","","",
//    		"","","","","","","","","","","","","","","",
//    		"","","","","","","","","","","","","","","",
//    		"","","","","","","","","","","","","","","",
//    		"","","","","","","","","","","","","","","",
//    		"","","","","","","","","","","","","","","",
//    		"","","","","","","","","","","","","","","",};/*图片描述*/
    public   PicsJWindow(ChatMainPanel   owner)   {  
        this.owner=owner; 
        try   { 
            init(); 
//            this.setAlwaysOnTop(true); 
        } 
        catch   (Exception   e)   { 
        	log.error("PicsJWindow", e);
            e.printStackTrace(); 
        } 
    } 
    private Logger logger=Logger.getLogger(PicsJWindow.class);
    private   void   init() { 
        this.setPreferredSize(new Dimension(28 * 15, 28 * 7));
        JPanel p = new JPanel();
        p.setOpaque(true);
        this.setContentPane(p);
        p.setLayout(gridLayout1);
        this.setUndecorated(true);
        this.setResizable(false);
        p.setBorder(BorderFactory.createLineBorder(new Color(225, 225, 225), 3));
        p.setBackground(Color.WHITE);
        String fileName = "";
        int filesize = 0;
        try{
          log.info(this.getClass().getResource("/com/lorent/lvmc/resource/images/defaultface"));
          String path=this.getClass().getResource("/com/lorent/lvmc/resource/images/defaultface").toString();
            if (path.startsWith("jar:file")) {
                int max = 10;
                for (int i = 0; i < 100; i++) {
                    URL url = this.getClass().getResource("/com/lorent/lvmc/resource/images/defaultface/e" + i + ".gif");
                    if (null != url) {
                        if (i == max - 1) {
                            max += 10;
                        }
                    }
                    if (null == url) {
                        filesize=i;
                        break;
                    }
                }
            } else {
                filesize = getEmotionSize((this.getClass().getResource("/com/lorent/lvmc/resource/images/defaultface").toURI().getPath().substring(1)));
            }
        }catch(Exception e){
            e.printStackTrace();
            logger.error("init", e);
            filesize=59;
        }
        ico=new   JLabel[filesize];
        for(i=0;i <filesize;i++){
        	try{
        		fileName= "/com/lorent/lvmc/resource/images/defaultface/e"+i+".gif";/*修改图片路径*/ 
                ico[i] =new   JLabel(new  ChatPic(this.getClass().getResource(fileName),i),SwingConstants.CENTER);
                ico[i].setBorder(BorderFactory.createLineBorder(new Color(225,225,225), 1));
                ico[i].setToolTipText(i+"");
                ico[i].addMouseListener(new   MouseAdapter(){ 
                    public void mouseClicked(MouseEvent e) {
                        if (e.getButton() == 1) {
                            JLabel cubl = (JLabel) (e.getSource());
                            ChatPic cupic = (ChatPic) (cubl.getIcon());
//                                    cupic.setPictext("");
                            owner.insertSendPic(cupic);
                            cubl.setBorder(BorderFactory.createLineBorder(new Color(225, 225, 225), 1));
                            getObj().dispose();
                        }
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        ((JLabel) e.getSource()).setBorder(BorderFactory.createLineBorder(Color.BLUE));
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        dispose(e);
                        ((JLabel) e.getSource()).setBorder(BorderFactory.createLineBorder(new Color(225, 225, 225), 1));
                        
                        }
                });
                p.add(ico[i]);
        	}catch(Exception ex){
        		log.info(ex.getMessage());
        	}
        }
        getObj().addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
//                System.out.println(1111111111);
                getObj().dispose();
            }
        });
        getObj().addMouseListener(new   MouseAdapter(){
            @Override
                public void mouseExited(MouseEvent e) {
                    dispose(e);
                    
                }
        });
//        
//        this.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
//            public void mouseMoved(MouseEvent e) {
//                int mx = e.getXOnScreen();
//                int my = e.getYOnScreen();
//                int ox = getObj().getX();
//                int oy = getObj().getY();
//                int ow = getObj().getWidth();
//                int oh = getObj().getHeight();
//                System.out.println("mouseMoved====" + mx + "=" + my + "=" + ox + "=" + oy + "=" +ow + "=" + oh);
//                boolean xflag = false;
//                boolean yflag = false;
//                if(mx>ox-1 && mx<ox+ow){
//                    xflag = true;
//                }
//                if(my>oy-1 && my<oy+oh){
//                    yflag = true;
//                }
//                if(xflag && yflag){
//                    return;
//                }
//                getObj().dispose();
//            }
//
//        });
        
//        p.addFocusListener(new java.awt.event.FocusAdapter() {
////            public void focusGained(java.awt.event.FocusEvent evt) {
////                phoneNumberTextFieldFocusGained(evt);
////            }
//            @Override
//            public void focusLost(java.awt.event.FocusEvent evt) {
//                System.out.println("aaa");
//                getObj().dispose();
//            }
//        });
    }

    public void dispose(MouseEvent e){
        int mx = e.getXOnScreen();
        int my = e.getYOnScreen();
        int ox = getObj().getX();
        int oy = getObj().getY();
        int ow = getObj().getWidth();
        int oh = getObj().getHeight();
//                System.out.println("mouseExited====" + mx + "=" + my + "=" + ox + "=" + oy + "=" +ow + "=" + oh);
        boolean xflag = false;
        boolean yflag = false;
        if(mx>ox+3 && mx<ox+ow-3){
            xflag = true;
        }
        if(my>oy+3 && my<oy+oh-3){
            yflag = true;
        }
        if(xflag && yflag){
            return;
        }
        getObj().dispose();
    }
    
    @Override
    public void setVisible(boolean show) {
        if (show) {
            determineAndSetLocation();
        }
        super.setVisible(show);
    }

    private void determineAndSetLocation() {
        Point loc = owner.getEmotionBtn().getLocationOnScreen();/*控件相对于屏幕的位置*/
        setBounds(loc.x - getPreferredSize().width / 3, loc.y - getPreferredSize().height,
                getPreferredSize().width, getPreferredSize().height);
    }

    private JDialog getObj() {
        return this;
    }

     public static void recursion(String root) {
        File file = new File(root);
        File[] subFile = file.listFiles();
        for (int i = 0; i < subFile.length; i++) {
            if (subFile[i].isDirectory()) {
                log.debug("目录: " + subFile[i].getName());
                recursion(subFile[i].getAbsolutePath());
            } else {
                log.debug("文件: " + subFile[i].getName());
            }
        }
    }
    public int getEmotionSize(String root){
     File file = new File(root);
     return file.listFiles().length;
    }
  public static void main(String[] args) {
      
       recursion(PicsJWindow.class.getResource("../resource/i18n/images/defaultface").toString().substring(6));
 }

}