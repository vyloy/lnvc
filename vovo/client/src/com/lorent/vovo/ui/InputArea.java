/*
 * MessageArea.java
 *
 * Created on __DATE__, __TIME__
 */

package com.lorent.vovo.ui;

import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.text.StyleConstants;

import org.apache.log4j.Logger;

import com.lorent.lvmc.bean.PicInfo;
import com.lorent.lvmc.ui.ChatPic;
import com.lorent.vovo.Vovo;
import com.lorent.vovo.dto.FontStyle;
import com.lorent.vovo.ui.EmotionPanel.ClickListener;
import com.lorent.vovo.util.Constants;
import com.lorent.vovo.util.DataUtil;
import com.lorent.vovo.util.ImageUtil;
import com.lorent.vovo.util.VovoStringUtil;

/**
 *
 * @author  Jack
 */
public class InputArea extends javax.swing.JPanel {

	private Logger log = Logger.getLogger(InputArea.class);
	final static String SCREENSHOT_SAVE_DIR = Constants.USER_DATA_DIR + "/" + DataUtil.getUserName() + "/images/";

	/** Creates new form MessageArea */
	public InputArea() {
		initComponents();
		init();
	}

	private void init() {
		//============init emotion menu
		emotionMenu = new JPopupMenu();
		emotionPanel = new EmotionPanel();

		emotionPanel.setClickListener(new ClickListener() {

			@Override
			public void mouseClick(String name, ImageIcon icon) {
				insertPic(name, icon);
			}
		});
		emotionPanel.init((Map<String, ImageIcon>) Vovo
				.getValue(Constants.DataKey.SYSTEM_EMOTION.toString()));
		emotionMenu.add(emotionPanel);

		//=============
		fontpanel.setVisible(false);

		//=============init fontNameCB
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		String[] fontNames = ge.getAvailableFontFamilyNames();
		for (String fontName : fontNames) {
			this.fontNameCB.addItem(fontName);
		}
		this.fontNameCB.setSelectedItem(currentStyle.getName());
		fontNameCB.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				fontNameCBItemStateChanged(evt);
			}
		});

		//==============init fontSizeCB
		for (int i = 8; i < 23; i++) {
			this.fontSizeCB.addItem(i);
		}
		this.fontSizeCB.setSelectedItem(currentStyle.getSize());
		fontSizeCB.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				fontSizeCBItemStateChanged(evt);
			}
		});

		//==============set default font
		changeStyle();

		//==============init inputPane
		this.inputPane.setEventListener(new MyTextPane.EventListener() {
			
			@Override
			public void enterType(KeyEvent e) {
				sendMsg();
				e.consume();
			}
			
			@Override
			public void dropFile(File file) {
				
			}
		});
	}

	private FontStyle currentStyle = new FontStyle();

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jPanel3 = new javax.swing.JPanel();
		fontpanel = new javax.swing.JPanel();
		fontNameCB = new javax.swing.JComboBox();
		fontSizeCB = new javax.swing.JComboBox();
		boldCB = new javax.swing.JCheckBox();
		italicCB = new javax.swing.JCheckBox();
		chooseClorBtn = new javax.swing.JButton();
		jPanel1 = new javax.swing.JPanel();
		emotionBtn = new javax.swing.JButton();
		fontBtn = new javax.swing.JButton();
		screenshotButton = new javax.swing.JButton();
		jScrollPane1 = new javax.swing.JScrollPane();
		inputPane = new com.lorent.vovo.ui.MyTextPane();
		jPanel4 = new javax.swing.JPanel();
		sendBtn = new javax.swing.JButton();

		setMaximumSize(new java.awt.Dimension(2147483647, 168));
		setMinimumSize(new java.awt.Dimension(450, 166));
		setPreferredSize(new java.awt.Dimension(450, 166));
		setLayout(new java.awt.BorderLayout());

		jPanel3.setMaximumSize(new java.awt.Dimension(32767, 64));
		jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3,
				javax.swing.BoxLayout.PAGE_AXIS));

		fontpanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		fontpanel.setMaximumSize(new java.awt.Dimension(32767, 29));
		fontpanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT,
				5, 0));
		fontpanel.add(fontNameCB);
		fontpanel.add(fontSizeCB);

		boldCB.setText("\u52a0\u7c97");
		boldCB.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				boldCBItemStateChanged(evt);
			}
		});
		fontpanel.add(boldCB);

		italicCB.setText("\u659c\u4f53");
		italicCB.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				italicCBItemStateChanged(evt);
			}
		});
		fontpanel.add(italicCB);

		chooseClorBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/com/lorent/vovo/resource/images/color-choose.png"))); // NOI18N
		chooseClorBtn.setBorderPainted(false);
		chooseClorBtn.setContentAreaFilled(false);
		chooseClorBtn.setPreferredSize(new java.awt.Dimension(30, 25));
		chooseClorBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				chooseClorBtnActionPerformed(evt);
			}
		});
		fontpanel.add(chooseClorBtn);

		jPanel3.add(fontpanel);

		jPanel1.setMaximumSize(new java.awt.Dimension(32767, 35));
		jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

		emotionBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/com/lorent/vovo/resource/images/message_emotion.png"))); // NOI18N
		emotionBtn.setContentAreaFilled(false);
		emotionBtn.setPreferredSize(new java.awt.Dimension(18, 18));
		emotionBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				emotionBtnActionPerformed(evt);
			}
		});
		jPanel1.add(emotionBtn);

		fontBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/com/lorent/vovo/resource/images/font.png"))); // NOI18N
		fontBtn.setContentAreaFilled(false);
		fontBtn.setPreferredSize(new java.awt.Dimension(18, 18));
		fontBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				fontBtnActionPerformed(evt);
			}
		});
		jPanel1.add(fontBtn);

		screenshotButton
				.setIcon(new javax.swing.ImageIcon(getClass().getResource(
						"/com/lorent/vovo/resource/images/screenshot.png"))); // NOI18N
		screenshotButton.setToolTipText("\u622a\u56fe");
		screenshotButton.setContentAreaFilled(false);
		screenshotButton.setPreferredSize(new java.awt.Dimension(18, 18));
		screenshotButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				screenshotButtonActionPerformed(evt);
			}
		});
		jPanel1.add(screenshotButton);

		jPanel3.add(jPanel1);

		add(jPanel3, java.awt.BorderLayout.NORTH);

		jScrollPane1.setMaximumSize(new java.awt.Dimension(32767, 82));
		jScrollPane1.setMinimumSize(new java.awt.Dimension(23, 82));

		inputPane.setMinimumSize(new java.awt.Dimension(6, 80));
		inputPane.setPreferredSize(new java.awt.Dimension(6, 80));
		jScrollPane1.setViewportView(inputPane);

		add(jScrollPane1, java.awt.BorderLayout.CENTER);

		jPanel4.setMaximumSize(new java.awt.Dimension(32767, 27));
		jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 2,
				2));

		sendBtn.setText("\u53d1\u9001");
		sendBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				sendBtnActionPerformed(evt);
			}
		});
		jPanel4.add(sendBtn);

		add(jPanel4, java.awt.BorderLayout.SOUTH);
	}// </editor-fold>
	//GEN-END:initComponents

	private void screenshotButtonActionPerformed(java.awt.event.ActionEvent evt) {
		Vovo.exeC("screenShot", "capture");
	}

	private void italicCBItemStateChanged(java.awt.event.ItemEvent evt) {
		boolean flag = this.italicCB.isSelected();
		currentStyle.setItalic(flag);
		changeStyle();
	}

	private void fontSizeCBItemStateChanged(java.awt.event.ItemEvent evt) {
		Integer selectSize = (Integer) this.fontSizeCB.getSelectedItem();
		currentStyle.setSize(selectSize);
		changeStyle();
	}

	private void fontNameCBItemStateChanged(java.awt.event.ItemEvent evt) {
		String selectName = (String) this.fontNameCB.getSelectedItem();
		currentStyle.setName(selectName);
		changeStyle();
	}

	private void boldCBItemStateChanged(java.awt.event.ItemEvent evt) {
		boolean flag = this.boldCB.isSelected();
		currentStyle.setBold(flag);
		changeStyle();
	}

	private void chooseClorBtnActionPerformed(java.awt.event.ActionEvent evt) {
		Color newColor = JColorChooser.showDialog(this, "选择颜色", currentStyle
				.getColor());
		if (newColor != null) {
			currentStyle.setColor(newColor);
			changeStyle();
		}
	}

	private void changeStyle() {
		this.inputPane.changeFontStyle(currentStyle);
		this.inputPane.repaint();
	}

	private void fontBtnActionPerformed(java.awt.event.ActionEvent evt) {
		//		if(!this.fontpanel.isVisible()){
		//			this.setPreferredSize(new Dimension(this.getPreferredSize().width,this.getPreferredSize().height + this.fontpanel.getMinimumSize().height));
		//		}else{
		//			this.setPreferredSize(new Dimension(this.getPreferredSize().width,this.getPreferredSize().height - this.fontpanel.getMinimumSize().height));
		//		}
		this.fontpanel.setVisible(!this.fontpanel.isVisible());
	}

	private void emotionBtnActionPerformed(java.awt.event.ActionEvent evt) {
		emotionMenu.show(emotionBtn, 0,
				0 - emotionMenu.getPreferredSize().height);
	}

	private void sendBtnActionPerformed(java.awt.event.ActionEvent evt) {
		sendMsg();
	}

	private void insertPic(String name, ImageIcon icon) {
		try {
			this.inputPane.insertIcon(name, icon);
			emotionMenu.setVisible(false);
			this.inputPane.requestFocus();
		} catch (Exception e) {
			log.error("insertPic", e);
			JOptionPane.showMessageDialog(this, "insertPic error");
		}
	}

	private Map<String,String> buildPicInfo() {
		String startFlag = "[";
		String endFlag = "]";
		Map<String,String> imgs = new HashMap<String,String>();
		//遍历jtextpane找出所有的图片信息封装成指定格式
		for (int i = 0; i < this.inputPane.getText().length(); i++) {
			if (inputPane.getStyledDocument().getCharacterElement(i).getName().equals("icon")) {
				ImageIcon icon = (ImageIcon)StyleConstants.getIcon(inputPane
						.getStyledDocument().getCharacterElement(i)
						.getAttributes());
				int sp = this.inputPane.getText().indexOf(startFlag, i);
				if(sp>-1){
					int ep = this.inputPane.getText().indexOf(endFlag, sp);
					if(ep>0 && ep>sp){
						String temp = this.inputPane.getText().substring(sp + startFlag.length(), ep);
						if(!temp.startsWith(Constants.IMG_FLAG)){
							continue;
						}
						temp = temp.replaceAll(Constants.IMG_FLAG, "");
						File file = new File(SCREENSHOT_SAVE_DIR + temp + "." + Constants.SCREENSHOT_IMG_SUFFIX);
						BufferedImage bufferedImage = ImageUtil.bufferImage(icon.getImage(),BufferedImage.TYPE_INT_RGB);//new BufferedImage(icon.getImage().getWidth(null), icon.getImage().getHeight(null), 6);
						try {
							imgs.put(temp, VovoStringUtil.encodeImgToStr(bufferedImage));
							ImageUtil.captureToFile(bufferedImage, Constants.SCREENSHOT_IMG_SUFFIX, file);
						} catch (Exception e) {
						}
					}
				}else{
					break;
				}
			}
		}
		return imgs;
		//return null;
	}
	
	private void sendMsg() {
		String msg = this.inputPane.getText();
		if(msg==null || msg.trim().length()<1){
			return;
		}
		listener.sendMsg(msg, currentStyle, buildPicInfo());
		this.inputPane.setText("");
		this.inputPane.requestFocus();
	}

	public interface EventListener {
		public void sendMsg(String msg, FontStyle style, Map<String,String> imgs);
	}

	private EventListener listener;

	public void setEventListener(EventListener l) {
		this.listener = l;
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JCheckBox boldCB;
	private javax.swing.JButton chooseClorBtn;
	private javax.swing.JButton emotionBtn;
	private javax.swing.JButton fontBtn;
	private javax.swing.JComboBox fontNameCB;
	private javax.swing.JComboBox fontSizeCB;
	private javax.swing.JPanel fontpanel;
	private com.lorent.vovo.ui.MyTextPane inputPane;
	private javax.swing.JCheckBox italicCB;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JButton screenshotButton;
	private javax.swing.JButton sendBtn;
	// End of variables declaration//GEN-END:variables

	private JPopupMenu emotionMenu;
	private EmotionPanel emotionPanel;

	public static void main(String[] args) {
		JFrame j = new JFrame();
		j.setSize(800, 600);
		j.setLocationRelativeTo(null);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.add(new InputArea());
		j.setVisible(true);
	}
}