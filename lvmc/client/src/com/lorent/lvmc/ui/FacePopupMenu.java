package com.lorent.lvmc.ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.URL;

import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;

import org.apache.log4j.Logger;

public class FacePopupMenu extends JPopupMenu {

	ChatMainPanel owner;
	GridLayout gridLayout1 = new GridLayout(6,10); 
	private static Logger log = Logger.getLogger(FacePopupMenu.class);
	
	public FacePopupMenu(ChatMainPanel   owner){
		this.owner=owner;
		init();
	}
	
	private void init() {
//		this.setPreferredSize(new Dimension(28 * 9, 28 * 7));
//		this.setLayout(new VerticalGridLayout(0, this.getComponentCount()));
		this.setLayout(gridLayout1);
		int filesize = 0;
		String fileName = "";
		try {
			log.info(this.getClass().getResource(
					"/com/lorent/lvmc/resource/images/defaultface"));
			String path = this.getClass().getResource(
					"/com/lorent/lvmc/resource/images/defaultface").toString();
			if (path.startsWith("jar:file")) {
				int max = 10;
				for (int i = 0; i < 100; i++) {
					URL url = this.getClass().getResource(
							"/com/lorent/lvmc/resource/images/defaultface/e"
									+ i + ".gif");
					if (null != url) {
						if (i == max - 1) {
							max += 10;
						}
					}
					if (null == url) {
						filesize = i;
						break;
					}
				}
			} else {
				filesize = getEmotionSize((this.getClass().getResource(
						"/com/lorent/lvmc/resource/images/defaultface").toURI().getPath().substring(1)));
			}
		} catch (Exception e) {
			log.error("init", e);
			e.printStackTrace();
			filesize = 59;
		}
//		JMenuItem[] items = new JMenuItem[filesize];
		for(int i=0;i <filesize;i++){ 
            fileName= "/com/lorent/lvmc/resource/images/defaultface/e"+i+".gif";/*修改图片路径*/ 
            final PopupMenuLabel label = new PopupMenuLabel(new ChatPic(this.getClass().getResource(fileName),i),SwingConstants.CENTER);
            label.setPreferredSize(new Dimension(28,28));
            label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            label.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
            label.addMouseListener(new java.awt.event.MouseAdapter(){
            	
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getButton() == 1) {
						label.setOpaqueAndReflesh(false);
//                      cubl.setBorder(BorderFactory.createLineBorder(new Color(225, 225, 225), 1));
						FacePopupMenu.this.setVisible(false);
						ChatPic cupic = (ChatPic) (label.getIcon());
//                                cupic.setPictext("");
                        owner.insertSendPic(cupic);
                        
                    }
				}
            	
            });

            this.add(label);
        }
	}
	


    private void determineAndSetLocation() {
        Point loc = owner.getEmotionBtn().getLocationOnScreen();/*控件相对于屏幕的位置*/
        setBounds(loc.x - getPreferredSize().width / 3, loc.y - getPreferredSize().height,
                getPreferredSize().width, getPreferredSize().height);
    }
    
    public void showPopup() {
    	this.setVisible(true);
        this.show(owner.getEmotionBtn(), 0, -this.getPreferredSize().height);
    }
	
	public int getEmotionSize(String root) {
		File file = new File(root);
		return file.listFiles().length;
	}
	
}
