/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ChatMainPanel.java
 *
 * Created on 2011-12-9, 16:19:49
 */
package com.lorent.lvmc.ui;

import com.lorent.lvmc.bean.PicInfo;
import com.lorent.lvmc.controller.ControllerFacade;
import com.lorent.lvmc.dto.LoginInfo;
import com.lorent.lvmc.util.DataUtil;
import com.lorent.common.util.ParaUtil;
import com.lorent.lvmc.bean.ChatComboxMemberModel;
import com.lorent.lvmc.controller.ChatConctroller;
import com.lorent.lvmc.util.StringUtil;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.AdjustmentEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import org.apache.log4j.Logger;

/**
 *
 * @author test
 */
public class ChatMainPanel extends javax.swing.JPanel {

	private Logger log = Logger.getLogger(ChatMainPanel.class);
	private PicsJWindow picsJWindow = null;
	private boolean isFocusOnSendMsgTextpanel = false;
	private FacePopupMenu pmenu = new FacePopupMenu(this);
	private boolean isToBottom = true;
	private StyleContext context = new StyleContext();
	
	/** Creates new form ChatMainPanel */
	public ChatMainPanel() {
		log.info("ChatMainPanel init");
		initComponents();
		init();
	}

	public void initcombox(ChatComboxMemberModel[] members) {
		log.info("==============initcombox=============");
		this.personList.removeAllItems();
		for (int i = 0; i < members.length; i++) {
			this.personList.addItem(members[i]);
		}
	}

	public void initCheckBox() {
		this.privateChat.setSelected(false);
		this.privateChat.setEnabled(false);
	}

	public void addComboxItem(ChatComboxMemberModel user) {
		int count = this.personList.getItemCount();
		for(int i = 1; i < count; i++){
			Object o = this.personList.getItemAt(i);
			if(o instanceof ChatComboxMemberModel){
				ChatComboxMemberModel temp = (ChatComboxMemberModel)o;
				if(temp!= null && temp.getKey().equals(user.getKey())){//exist
					return;
				}
			}
		}
		this.personList.addItem(user);
	}

	public void removeComboxItem(ChatComboxMemberModel user) {
		this.personList.removeItem(user);
	}

	public void init() {
		this.fontpanel.setVisible(false);
		//--add by chard 2012年4月25日17:45:45
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		fontNames = ge.getAvailableFontFamilyNames();
		for (int i = 0; i < fontNames.length; i++) {
			jComboBox1.addItem(fontNames[i]);
		}
		jComboBox1.setSelectedItem("宋体");
		int initFontSize = Integer.parseInt(this.fontsize.toString());
		fontSizes = new String[13];
		for (int i = 0; i < fontSizes.length; i++) {
			fontSizes[i] = Integer.toString((i + initFontSize));
			jComboBox2.addItem(fontSizes[i]);
		}
		jComboBox2.setSelectedItem(fontsize);
		
		StyledDocument document = new DefaultStyledDocument(context);
		docMsg = sendMsgTextpanel.getStyledDocument();
		recieveMsgTextpanel.setStyledDocument(document);
		docChat = recieveMsgTextpanel.getStyledDocument();
		createRecieveDocumentStyles(context);
		picsJWindow = new PicsJWindow(this);

		FontAndText dateFont = new FontAndText(StringUtil
				.getUIString("sendMsgTextpanel.tipContent"), "宋体",
				initFontSize, Color.GRAY);
		FontAndText dateFont1 = new FontAndText(" ", "宋体", initFontSize,
				Color.BLACK);
		try {
			docMsg.insertString(docMsg.getLength(), dateFont.getText(),
					dateFont.getAttrSet());
			docMsg.insertString(docMsg.getLength(), dateFont1.getText(),
					dateFont1.getAttrSet());
			sendMsgTextpanel.setCaretPosition(docMsg.getLength());
		} catch (BadLocationException ex) {
			log.error("init", ex);
		}
		javax.swing.JScrollBar verticalScrollBar = jScrollPane2
				.getVerticalScrollBar();
		verticalScrollBar.addMouseListener(new JScrollBarMouseAdapter(
				verticalScrollBar));
		verticalScrollBar.addMouseWheelListener(new JScrollBarMouseAdapter(
				verticalScrollBar));
		verticalScrollBar
				.addAdjustmentListener(new JScrollBarAdjustmentListener(
						verticalScrollBar));
		this.addComponentListener(new ComponentAdapter() {

			@Override
			public void componentResized(ComponentEvent e) {
				ChatMainPanel.this.picsJWindow.dispose();
			}

			@Override
			public void componentMoved(ComponentEvent e) {
				ChatMainPanel.this.picsJWindow.dispose();
			}

			@Override
			public void componentHidden(ComponentEvent e) {
				ChatMainPanel.this.picsJWindow.dispose();
			}
		});
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				ChatMainPanel.this.picsJWindow.dispose();
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				ChatMainPanel.this.picsJWindow.dispose();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				ChatMainPanel.this.picsJWindow.dispose();
			}
		});
	};

	private Object fontstyle = "宋体";
	private Object fontsize = "12";
	private Color myfontcolor = Color.BLACK;

	private String stylemsg = "-^*$+#" + fontstyle + "-^*$+#" + fontsize
			+ "-^*$+#" + myfontcolor.getRed() + "," + myfontcolor.getGreen()
			+ "," + myfontcolor.getBlue();

	private void setSendMsgFont() {

		if (null != fontstyle)
			fontstyle = this.jComboBox1.getSelectedItem();

		if (null != this.jComboBox2.getSelectedItem()) {
			fontsize = this.jComboBox2.getSelectedItem();
		}
		if (null != colorStyle)
			myfontcolor = colorStyle;
		log
				.debug(" style " + fontstyle + " \n" + fontsize + "\n"
						+ myfontcolor);
		//		sendMsgTextpanel.getText();
		Font font = new Font(fontstyle.toString(), 0, Integer.parseInt(fontsize
				.toString()));
		sendMsgTextpanel.setFont(font);
		sendMsgTextpanel.setForeground(myfontcolor);
		if (this.isFocusOnSendMsgTextpanel) {
			setInputContentFont();

			this.jPanel3.setPreferredSize(new java.awt.Dimension(this.jPanel3
					.getPreferredSize().width, 15 + Integer.parseInt(fontsize
					.toString())));
		}

		stylemsg = "-^*$+#" + fontstyle + "-^*$+#" + fontsize + "-^*$+#"
				+ myfontcolor.getRed() + "," + myfontcolor.getGreen() + ","
				+ myfontcolor.getBlue();

		this.jPanel3.revalidate();
		this.jPanel3.repaint();
	}

	public void insertSendPic(ImageIcon imgIc) {
		//        try {
		//            docMsg.insertString(sendMsgTextpanel.getCaretPosition(),"", null);//docMsg.getLength()
		//        } catch (BadLocationException ex) {
		//            Logger.getLogger(ChatMainPanel.class.getName()).log(Level.SEVERE, null, ex);
		//        }
		//        sendMsgTextpanel.setCaretPosition(docMsg.getLength()); // 设置插入位置
		if (!this.isFocusOnSendMsgTextpanel) {
			sendMsgTextpanel.setText("");
			this.isFocusOnSendMsgTextpanel = true;

		}
		int i = -1;
		try {
			i = sendMsgTextpanel.getCaretPosition();
			sendMsgTextpanel.getStyledDocument().insertString(
					sendMsgTextpanel.getCaretPosition(), " ", null);
		} catch (BadLocationException e) {
			log.error("insertSendPic", e);
			e.printStackTrace();
		}
		sendMsgTextpanel.insertIcon(imgIc); // 插入图片

		try {
			docMsg.remove(i, 1);//清除图片前一个空格
		} catch (BadLocationException e) {
			log.error("insertSendPic",e);
			e.printStackTrace();
		}
		sendMsgTextpanel.requestFocusInWindow();
		//insert(new FontAttrib()); // 这样做可以换行
	}

	public javax.swing.JButton getEmotionBtn() {
		return emotionButton;
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		jScrollPane2 = new javax.swing.JScrollPane();
		recieveMsgTextpanel = new javax.swing.JTextPane();
		fontpanel = new javax.swing.JPanel();
		jComboBox1 = new javax.swing.JComboBox();
		jComboBox2 = new javax.swing.JComboBox();
		jButton1 = new javax.swing.JButton();
		jPanel4 = new javax.swing.JPanel();
		jPanel2 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		personList = new javax.swing.JComboBox();
		privateChat = new javax.swing.JCheckBox();
		emotionButton = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
		jPanel3 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		sendMsgTextpanel = new javax.swing.JTextPane();
		sendMsgButton = new javax.swing.JButton();

		setLayout(new java.awt.BorderLayout());

		jPanel1.setLayout(new java.awt.BorderLayout());

		jScrollPane2
				.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
					public void mouseWheelMoved(
							java.awt.event.MouseWheelEvent evt) {
						jScrollPane2MouseWheelMoved(evt);
					}
				});
		jScrollPane2
				.addComponentListener(new java.awt.event.ComponentAdapter() {
					public void componentResized(
							java.awt.event.ComponentEvent evt) {
						jScrollPane2ComponentResized(evt);
					}
				});

		recieveMsgTextpanel.setEditable(false);
		recieveMsgTextpanel
				.addComponentListener(new java.awt.event.ComponentAdapter() {
					public void componentResized(
							java.awt.event.ComponentEvent evt) {
						recieveMsgTextpanelComponentResized(evt);
					}
				});
		jScrollPane2.setViewportView(recieveMsgTextpanel);

		jPanel1.add(jScrollPane2, java.awt.BorderLayout.CENTER);

		fontpanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

		jComboBox1.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				jComboBox1ItemStateChanged(evt);
			}
		});
		jComboBox1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jComboBox1ActionPerformed(evt);
			}
		});

		jComboBox2.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				jComboBox2ItemStateChanged(evt);
			}
		});
		jComboBox2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jComboBox2ActionPerformed(evt);
			}
		});

		jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/com/lorent/lvmc/resource/images/color-choose.png"))); // NOI18N
		jButton1.setBorderPainted(false);
		jButton1.setContentAreaFilled(false);
		jButton1.setPreferredSize(new java.awt.Dimension(30, 25));
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout fontpanelLayout = new javax.swing.GroupLayout(
				fontpanel);
		fontpanel.setLayout(fontpanelLayout);
		fontpanelLayout
				.setHorizontalGroup(fontpanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								fontpanelLayout
										.createSequentialGroup()
										.addGap(2, 2, 2)
										.addComponent(
												jComboBox1,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												135,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(5, 5, 5)
										.addComponent(
												jComboBox2,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												56,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jButton1,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(204, 204, 204)));
		fontpanelLayout
				.setVerticalGroup(fontpanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								fontpanelLayout
										.createSequentialGroup()
										.addGroup(
												fontpanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.CENTER)
														.addComponent(
																jComboBox2,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jButton1,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jComboBox1,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(0, 0, 0)));

		jPanel1.add(fontpanel, java.awt.BorderLayout.SOUTH);

		add(jPanel1, java.awt.BorderLayout.CENTER);

		jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4,
				javax.swing.BoxLayout.Y_AXIS));

		jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

		jLabel1.setText("\u6211\u5bf9");

		personList.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
				"所有人", "Item 2", "Item 3", "Item 4" }));
		personList.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				personListItemStateChanged(evt);
			}
		});

		privateChat.setText("\u79c1\u804a");

		emotionButton
				.setIcon(new javax.swing.ImageIcon(
						getClass()
								.getResource(
										"/com/lorent/lvmc/resource/images/emotion/message_emotion.png"))); // NOI18N
		emotionButton.setBorderPainted(false);
		emotionButton.setContentAreaFilled(false);
		emotionButton.setPreferredSize(new java.awt.Dimension(30, 25));
		emotionButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				emotionButtonMouseClicked(evt);
			}
		});

		jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/com/lorent/lvmc/resource/images/font.png"))); // NOI18N
		jButton2.setBorderPainted(false);
		jButton2.setContentAreaFilled(false);
		jButton2.setFocusable(false);
		jButton2.setPreferredSize(new java.awt.Dimension(30, 25));
		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(
				jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout
				.setHorizontalGroup(jPanel2Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel2Layout
										.createSequentialGroup()
										.addGap(5, 5, 5)
										.addComponent(jLabel1)
										.addGap(5, 5, 5)
										.addComponent(
												personList,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												101,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(privateChat)
										.addGap(5, 5, 5)
										.addComponent(
												emotionButton,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(5, 5, 5)
										.addComponent(
												jButton2,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(182, Short.MAX_VALUE)));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel2Layout.createParallelGroup(
						javax.swing.GroupLayout.Alignment.CENTER).addComponent(
						jLabel1).addComponent(personList,
						javax.swing.GroupLayout.PREFERRED_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(
						privateChat).addComponent(emotionButton,
						javax.swing.GroupLayout.PREFERRED_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(
						jButton2, javax.swing.GroupLayout.PREFERRED_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.PREFERRED_SIZE)));

		jPanel4.add(jPanel2);

		jPanel3.setLayout(new java.awt.BorderLayout());

		sendMsgTextpanel.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				sendMsgTextpanelFocusGained(evt);
			}
		});
		sendMsgTextpanel.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent evt) {
				sendMsgTextpanelKeyPressed(evt);
			}

			public void keyReleased(java.awt.event.KeyEvent evt) {
				sendMsgTextpanelKeyReleased(evt);
			}

			public void keyTyped(java.awt.event.KeyEvent evt) {
				sendMsgTextpanelKeyTyped(evt);
			}
		});
		jScrollPane1.setViewportView(sendMsgTextpanel);

		jPanel3.add(jScrollPane1, java.awt.BorderLayout.CENTER);

		sendMsgButton.setText("\u53d1\u9001");
		sendMsgButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				sendMsgButtonActionPerformed(evt);
			}
		});
		jPanel3.add(sendMsgButton, java.awt.BorderLayout.EAST);

		jPanel4.add(jPanel3);

		add(jPanel4, java.awt.BorderLayout.SOUTH);
	}// </editor-fold>
	//GEN-END:initComponents

	private void jScrollPane2MouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
		javax.swing.JScrollBar jb = jScrollPane2.getVerticalScrollBar();
		this.isToBottom(jb);
	}

	private void jScrollPane2ComponentResized(java.awt.event.ComponentEvent evt) {
		if (this.isToBottom) {
			setjScrollPane2PositionBottom();
		}
	}

	private void recieveMsgTextpanelComponentResized(
			java.awt.event.ComponentEvent evt) {
		if (this.isToBottom) {
			setjScrollPane2PositionBottom();
		}
	}

	public void setjScrollPane2PositionBottom() {
		javax.swing.JScrollBar jb = jScrollPane2.getVerticalScrollBar();
		jb.setValue(jb.getMaximum() - jb.getVisibleAmount());
		this.isToBottom = true;
	}

	private void sendMsgTextpanelKeyTyped(java.awt.event.KeyEvent evt) {
		boolean flag = false;
		char[] notInputKeys = new char[] { (char) evt.VK_ENTER,
				(char) evt.VK_DELETE, 
				(char)evt.VK_SPACE,
				(char) evt.VK_BACK_SPACE };
		for (int i = 0; i < notInputKeys.length; i++) {
			if (notInputKeys[i] == evt.getKeyChar()) {
				flag = true;
				break;
			}
		}
		if (evt.getModifiers() == KeyEvent.CTRL_MASK) {
			flag = true;
		}
		if (!flag) {
			evt.consume();
			String content = String.valueOf(evt.getKeyChar());
			//			System.out.println("content================" + evt.getKeyChar());
			FontAndText dateFont1 = new FontAndText(content, fontstyle
					.toString(), Integer.parseInt(fontsize.toString()),
					myfontcolor);
			try {
				int len = sendMsgTextpanel.getCaretPosition();
				//				System.out.println("len================" + len);
				String selectText = sendMsgTextpanel.getSelectedText();
				if(selectText!=null&&selectText.length()>0){
					int start = sendMsgTextpanel.getSelectionStart();
					sendMsgTextpanel.getStyledDocument().remove(start, selectText.length());
				
				}
				sendMsgTextpanel.getStyledDocument().insertString(len,
						dateFont1.getText(), dateFont1.getAttrSet());

				//				sendMsgTextpanel.setCaretPosition(docMsg.getLength());
				//				sendMsgTextpanel.getStyledDocument().remove(len, 1);
				//				sendMsgTextpanel.getStyledDocument().remove(len, content.length());
			} catch (Exception ex) {
				log.error("sendMsgTextpanelKeyTyped", ex);
			}
		}
		setInputContentFont();
	}

	private void sendMsgTextpanelKeyReleased(java.awt.event.KeyEvent evt) {
		//		setInputContentFont();
	}

	private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {
		setSendMsgFont();
	}

	private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {
		setSendMsgFont();
	}

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		colorStyle = JColorChooser.showDialog(DataUtil.getTopWindow(),
				"选择字体颜色", colorStyle);
		jButton1.setForeground(colorStyle);
		setSendMsgFont();
	}

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
		if (this.fontpanel_open) {
			this.fontpanel.setVisible(false);
			this.fontpanel_open = false;
		} else {
			this.fontpanel.setVisible(true);
			this.fontpanel_open = true;
		}
		if (this.isToBottom) {
			setjScrollPane2PositionBottom();
		}
	}

	SimpleDateFormat dateFormat = new SimpleDateFormat("HH:MM:ss");

	private boolean fontpanel_open = false;
	private Color colorStyle = Color.black;// 设置字体的默认颜色为黑色;

	private void sendMsgButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendMsgButtonActionPerformed
		String picpos = buildPicInfo();
		String msg = sendMsgTextpanel.getText() + stylemsg + "-^*$+%" + picpos;//文本和表情信息
		//if send massage is null then return;
		if ((null == sendMsgTextpanel.getText()
				|| sendMsgTextpanel.getText().trim().length() == 0 || sendMsgTextpanel
				.getText().length() == 0)
				&& (null == picpos || picpos.length() == 0)) {
			return;
		}
		if (!this.isFocusOnSendMsgTextpanel) {
			return;
		}
		//        System.out.println("文本信息："+sendMsgTextpanel.getText());
		//        System.out.println("发送的信息+图片:"+msg);
		//        String[] mst=msg.split("[*]");
		//        System.out.println("发送的信息+图片长度:"+mst[0].length());
		LoginInfo loginInfo = DataUtil.getValue(DataUtil.Key.LoginInfo);
		ChatComboxMemberModel saygoal = (ChatComboxMemberModel) personList
				.getSelectedItem();
		if (null == saygoal) {
			saygoal = new ChatComboxMemberModel(null, "所有人");
		}
		String isprivateChat = "0";//public chat
		Object[] o = privateChat.getSelectedObjects();
		boolean flag = false;
		if (null != o && o.length > 0) {
			isprivateChat = "1";//private chat
			flag = (Boolean) ControllerFacade.execute("chatController",
					"sendMsg", ParaUtil.newInstance().setString("msg", msg)
							.setString("toPerson", saygoal.key).setString(
									"sendperson",
									DataUtil.getLoginInfo().getNickName())
							.setString("isprivateChat", isprivateChat));
		} else {
			flag = (Boolean) ControllerFacade.execute("chatController",
					"sendMsg", ParaUtil.newInstance().setString("msg", msg)
							.setString("toPerson", saygoal.name).setString(
									"sendperson",
									DataUtil.getLoginInfo().getNickName())
							.setString("isprivateChat", isprivateChat));
		}
		if (flag) {
			sendMsgTextpanel.setText(null);
		}
		if ("1".equals(isprivateChat)) {
			String msgbody[] = new String[2];
			if (msg.indexOf("-^*$+%") > 0) {
				msgbody[0] = msg.substring(0, msg.indexOf("-^*$+%"));
				msgbody[1] = msg.substring(msg.indexOf("-^*$+%") + 6);
			} else {
				msgbody = msg.split("[*]");
			}
			addPrivateMsg(saygoal.name, msgbody[0], myPicInfo, " 我 ",
					dateFormat.format(new Date()));
		}
	}//GEN-LAST:event_sendMsgButtonActionPerformed

	private void sendMsgTextpanelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sendMsgTextpanelKeyPressed

		//        FontAndText dateFont1 = new FontAndText(sendMsgTextpanel.getText(),"宋体",12,Color.BLUE);
		//        System.out.println(sendMsgTextpanel.getText());
		//            try {
		////                docMsg.setCharacterAttributes(0, sendMsgTextpanel.getText().length(), dateFont1.getAttrSet(), true);
		//                docMsg.insertString(docMsg.getLength(), dateFont1.getText(), dateFont1.getAttrSet());
		//            } catch (Exception ex) {
		//                java.util.logging.Logger.getLogger(ChatMainPanel.class.getName()).log(Level.SEVERE, null, ex);
		//            }
		//            sendMsgTextpanel.setCaretPosition(docMsg.getLength());
		//set  'Contrl + Enter' 作为发送快捷键 
		if (evt.getKeyCode() == KeyEvent.VK_ENTER) {//&&evt.isControlDown()
			
			evt.consume();//消除回车键换行
			try {
				log.debug("huiche====================fasongxiaoxi:"+sendMsgTextpanel.getStyledDocument().getText(0, sendMsgTextpanel.getStyledDocument().getLength()));
			} catch (BadLocationException e) {
				log.error("sendMsgTextpanelKeyPressed", e);
				e.printStackTrace();
			}
			sendMsgButtonActionPerformed(null);
			sendMsgTextpanel.setText("");
			sendMsgTextpanel.revalidate();
			sendMsgTextpanel.repaint();
		}
		//		setInputContentFont();

	}//GEN-LAST:event_sendMsgTextpanelKeyPressed

	private void personListItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_personListItemStateChanged
		String selectItem = this.personList.getSelectedItem() == null ? null
				: this.personList.getSelectedItem().toString();
		if (null != selectItem && "所有人".equals(selectItem)) {
			this.privateChat.setEnabled(false);
			this.privateChat.setSelected(false);
		} else if (null != selectItem && selectItem.length() > 0) {
			this.privateChat.setSelected(false);
			this.privateChat.setEnabled(true);
		}
	}//GEN-LAST:event_personListItemStateChanged

	private void emotionButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_emotionButtonMouseClicked
		showPopupMenu();
	}//GEN-LAST:event_emotionButtonMouseClicked

	private void sendMsgTextpanelFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_sendMsgTextpanelFocusGained
		if (!this.isFocusOnSendMsgTextpanel) {
			this.isFocusOnSendMsgTextpanel = true;
			setSendMsgFont();
			sendMsgTextpanel.setText("");
		}
	}//GEN-LAST:event_sendMsgTextpanelFocusGained

	public void setInputContentFont() {
		MutableAttributeSet attr = new SimpleAttributeSet();
		StyleConstants.setFontSize(attr, Integer.parseInt(fontsize.toString()));
		StyleConstants.setFontFamily(attr, fontstyle.toString());
		StyleConstants.setForeground(attr, sendMsgTextpanel.getForeground());
		sendMsgTextpanel.getStyledDocument().setCharacterAttributes(0,
				sendMsgTextpanel.getStyledDocument().getLength(), attr, false);
	}

	private void showPopupMenu() {
		if (pmenu == null) {
			pmenu = new FacePopupMenu(this);
		}
//		pmenu.setBackground(NimRODLookAndFeel.getControl());
		pmenu.showPopup();
		//				if (null == picsJWindow)
		//							picsJWindow = new PicsJWindow(this);
		//						picsJWindow.setVisible(true);
	}

	FontAndText dateFont = new FontAndText("", "宋体", 12, Color.BLUE);
	private FontAndText myFont = null;

	public void insertWhiteRow(FontAndText myFont){
		try{
			if(myFont==null || myFont.getSize()<21){
				recieveMsgTextpanel.setCaretPosition(docChat.getLength());
				FontAndText tempFont = new FontAndText("", myFont.getName(), 2, myFont.getColor());
				docChat.insertString(docChat.getLength(), "\n", tempFont
						.getAttrSet());
			}
		}catch(Exception ex){
			log.error("insertWhiteRow", ex);
		}
	}
	
	public void addMeg(String sayto, String msgcontant,
			List<PicInfo> myPicInfo, String sender, String sendDate) {
		
		if ("所有人".equals(sayto)) {
			insertHeader(sender, null, sendDate, false);
		} else if (sayto.indexOf("From") >= 0) {
			insertHeader(sender, null, sendDate, true);
		} else {
			insertHeader(sender, sayto, sendDate, true);
		}
		//		pos2 = recieveMsgTextpanel.getStyledDocument().getLength()+2;
		
		if (msgcontant.indexOf("-^*$+#") >= 0) {
			String message = msgcontant.substring(0, msgcontant
					.indexOf("-^*$+#"));
			String reSult = msgcontant.substring(
					msgcontant.indexOf("-^*$+#") + 6, msgcontant.length());

			String _fontstyle = reSult.substring(0, reSult.indexOf("-^*$+#"));
			reSult = reSult.substring(reSult.indexOf("-^*$+#") + 6, reSult
					.length());

			String _fontsize = reSult.substring(0, reSult.indexOf("-^*$+#"));
			reSult = reSult.substring(reSult.indexOf("-^*$+#") + 6, reSult
					.length());

			String _color[] = reSult.split(",");
			Color msgColor = new Color(Integer.parseInt(_color[0]), Integer
					.parseInt(_color[1]), Integer.parseInt(_color[2]));
			//                myFont=getFontAttrib();
			myFont = new FontAndText("", _fontstyle, Integer
					.parseInt(_fontsize), msgColor);
			myFont.setText(message);
			insertWhiteRow(myFont);
			
			insert(message);
			insertPic(myPicInfo);
		} else {
			insertWhiteRow(null);
			
			insert(msgcontant);
			insertPic(myPicInfo);
		}
	}

	public void addPrivateMsg(String sayto, String msgcontant,
			List<PicInfo> myPicInfo, String sender, String sendDate) {
		insertHeader(null, sayto, sendDate, true);
		pos2 = recieveMsgTextpanel.getCaretPosition();

		if (msgcontant.indexOf("-^*$+#") >= 0) {
			String message = msgcontant.substring(0, msgcontant
					.indexOf("-^*$+#"));
			String reSult = msgcontant.substring(
					msgcontant.indexOf("-^*$+#") + 6, msgcontant.length());

			String _fontstyle = reSult.substring(0, reSult.indexOf("-^*$+#"));
			reSult = reSult.substring(reSult.indexOf("-^*$+#") + 6, reSult
					.length());

			String _fontsize = reSult.substring(0, reSult.indexOf("-^*$+#"));
			reSult = reSult.substring(reSult.indexOf("-^*$+#") + 6, reSult
					.length());

			String _color[] = reSult.split(",");
			Color msgColor = new Color(Integer.parseInt(_color[0]), Integer
					.parseInt(_color[1]), Integer.parseInt(_color[2]));
			//                myFont=getFontAttrib();
			myFont = new FontAndText("", _fontstyle, Integer
					.parseInt(_fontsize), msgColor);
			myFont.setText(message);
			insertWhiteRow(myFont);
			insert(message);
			insertPic(myPicInfo);
		} else {
			insertWhiteRow(null);
			insert(msgcontant);
			insertPic(myPicInfo);
		}
	}

	public void addOpenfireDisconnectMsg() {
		FontAndText dateFont = new FontAndText("", "宋体", 12, Color.gray);
		dateFont.setText(StringUtil
				.getErrorString("MainController.sendMsg.openfireDisconnect"));
		insert(dateFont);
		sendMsgButton.setEnabled(false);
	}
	
	public void addReconnectMsg() {
		FontAndText dateFont = new FontAndText("", "宋体", 12, Color.gray);
		dateFont.setText(StringUtil
				.getUIString("server.isreconnect"));
		insert(dateFont);
		sendMsgButton.setEnabled(true);
	}
	
	

	private JComboBox fontName = null, fontSize = null, fontColor = null;

	private FontAndText getFontAttrib() {
		FontAndText att = new FontAndText();
		att.setText(sendMsgTextpanel.getText() + "*" + buildPicInfo());//文本和表情信息
		return att;
	}

	private void insert(FontAndText attrib) {
		try { // 插入文本
			docChat.insertString(docChat.getLength(), attrib.getText() + "\n",
					attrib.getAttrSet());
			recieveMsgTextpanel.setCaretPosition(docChat.getLength()); // 设置滚动到最下边
		} catch (Exception e) {
			log.error("insert", e);
			e.printStackTrace();
		}
	}

	private void insertContentTopPic(){
		recieveMsgTextpanel.setCaretPosition(docChat.getLength());
		String fileName;
		fileName = "/com/lorent/lvmc/resource/images/chatwhite.png";/*修改图片路径*/
		recieveMsgTextpanel.insertIcon(new ImageIcon(this.getClass()
				.getResource(fileName))); /*插入图片*/
		
	}
	
	private void insert(String text) {
		try { // 插入文本
//			FontAndText tempFont = null;
//			tempFont = new FontAndText("", "宋体", 13, javax.swing.UIManager.getColor("List.background"));
//			docChat.insertString(docChat.getLength(), blankText,
//					tempFont.getAttrSet());
//			insertContentTopPic();
			recieveMsgTextpanel.setCaretPosition(docChat.getLength());
			pos2 = recieveMsgTextpanel.getCaretPosition();
			
			
			docChat.insertString(docChat.getLength(), text,
					myFont.getAttrSet());
			recieveMsgTextpanel.setLogicalStyle(context.getStyle(contentStyle));
			docChat.insertString(docChat.getLength(), "\n", SimpleAttributeSet.EMPTY);
//			docChat.insertString(docChat.getLength(), text + "\n",
//					style);
			//			recieveMsgTextpanel.setCaretPosition(docChat.getLength()); // 设置插入位置
			setjScrollPane2PositionBottom();
		} catch (Exception e) {
			log.error("insert", e);
			e.printStackTrace();
		}
	}
	
	private final static String contentStyle = "contentStyle";
	private final static String headStyle = "headStyle";
	
	public static void createRecieveDocumentStyles(StyleContext sc) {  
	    Style defaultStyle = sc.getStyle(StyleContext.DEFAULT_STYLE);

	    // Create and add the main document style
	    Style mainStyle = sc.addStyle(contentStyle, defaultStyle);
	    StyleConstants.setLeftIndent(mainStyle, 16);

	    

	    // Create and add the heading style
	    Style heading2Style = sc.addStyle(headStyle, null);
	    StyleConstants.setLeftIndent(heading2Style, 0);
	  }

	private void insertHeader(String from, String to, String date,
			boolean isprivate) {
		String myname = DataUtil.getLoginInfo().getNickName();
		try { // 插入文本
			Color c = null;
			int nameSize = 13;
			int infoSize = 11;
			String fontname = "宋体";
			FontAndText tempFont = null;
			FontAndText privateFont = new FontAndText("", fontname, 14,
					Color.GRAY);
			if (myname.equals(from) || from == null) {
				c = new Color(0, 80, 40);
			} else {
				c = Color.BLUE;
			}
			recieveMsgTextpanel.setCaretPosition(docChat.getLength());
			if (to == null) {
				if (isprivate) {
					docChat.insertString(docChat.getLength(), "[From] ",
							privateFont.getAttrSet());
					tempFont = new FontAndText("", fontname, nameSize, c);
					docChat.insertString(docChat.getLength(), from + "  ",
							tempFont.getAttrSet());
					tempFont = new FontAndText("", fontname, infoSize, c);
					docChat.insertString(docChat.getLength(), date,
							tempFont.getAttrSet());
				} else {
					tempFont = new FontAndText("", fontname,
							nameSize, c);
					docChat.insertString(docChat.getLength(), from + "  ",
							tempFont.getAttrSet());
					tempFont = new FontAndText("", fontname, infoSize, c);
					docChat.insertString(docChat.getLength(), date,
							tempFont.getAttrSet());
				}
			} else if (from == null) {
				docChat.insertString(docChat.getLength(), "[to] ", privateFont
						.getAttrSet());
				tempFont = new FontAndText("", fontname, nameSize, c);
				docChat.insertString(docChat.getLength(), to + "  ", tempFont
						.getAttrSet());
				tempFont = new FontAndText("", fontname, infoSize, c);
				docChat.insertString(docChat.getLength(), date, tempFont
						.getAttrSet());
			} else {
				tempFont = new FontAndText("", fontname, nameSize,
						c);
				docChat.insertString(docChat.getLength(), from, tempFont
						.getAttrSet());
				tempFont = new FontAndText("", fontname, infoSize, c);
				docChat.insertString(docChat.getLength(), " to ", tempFont
						.getAttrSet());
				tempFont = new FontAndText("", fontname, nameSize, c);
				docChat.insertString(docChat.getLength(), to + "  ", tempFont
						.getAttrSet());
				tempFont = new FontAndText("", fontname, infoSize, c);
				docChat.insertString(docChat.getLength(), date, tempFont
						.getAttrSet());
			}
//			recieveMsgTextpanel.setParagraphAttributes(SimpleAttributeSet.EMPTY, true);
			
			recieveMsgTextpanel.setLogicalStyle(context.getStyle(headStyle));
			docChat.insertString(docChat.getLength(), "\n", tempFont.getAttrSet());
			recieveMsgTextpanel.setCaretPosition(docChat.getLength()); // 设置插入位置

		} catch (Exception e) {
			log.error("insertHeader", e);
		}
	}

	int pos1;
	int pos2;
	String blankText = "  ";

	public void insertPic(List<PicInfo> myPicInfo) {
		if (myPicInfo.size() <= 0) {
			return;
		} else {
//			pos2 = pos2 + blankText.length();
			for (int i = 0; i < myPicInfo.size(); i++) {
				PicInfo pic = myPicInfo.get(i);
				recieveMsgTextpanel.setCaretPosition(pos2 + pic.getPos()); /*设置插入位置*/

				String fileName;
				fileName = "/com/lorent/lvmc/resource/images/defaultface/e"
						+ pic.getVal() + ".gif";/*修改图片路径*/
				recieveMsgTextpanel.insertIcon(new ImageIcon(this.getClass()
						.getResource(fileName))); /*插入图片*/
				try {
//					recieveMsgTextpanel.getStyledDocument().remove(
//							pos2 + pic.getPos() + 1, 1);
				} catch (Exception e) {
					log.error("insertPic", e);
					e.printStackTrace();
				}
			}
			this.myPicInfo.clear();
		}

		//		recieveMsgTextpanel.setCaretPosition(docChat.getLength()); /*设置滚动到最下边*/
		setjScrollPane2PositionBottom();
		//insert(new FontAttrib()); /*这样做可以换行*/
	}

	private StyledDocument docMsg = null;
	private StyledDocument docChat = null;
	private List<PicInfo> myPicInfo = new LinkedList<PicInfo>();

	/**
	 * 重组发送的表情信息
	 * @return 重组后的信息串  格式为   位置|代号+位置|代号+……
	 */
	private String buildPicInfo() {
		StringBuilder sb = new StringBuilder("");
		//遍历jtextpane找出所有的图片信息封装成指定格式
		for (int i = 0; i < this.sendMsgTextpanel.getText().length(); i++) {
			if (docMsg.getCharacterElement(i).getName().equals("icon")) {
				//ChatPic = (ChatPic)
				Icon icon = StyleConstants.getIcon(sendMsgTextpanel
						.getStyledDocument().getCharacterElement(i)
						.getAttributes());
				ChatPic cupic = (ChatPic) icon;
				PicInfo picInfo = new PicInfo(i, cupic.getIm() + "");
				myPicInfo.add(picInfo);
				sb.append(i + "&" + cupic.getIm() + "+");
			}
		}
		log.debug(sb.toString());
		return sb.toString();
		//return null;
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton emotionButton;
	private javax.swing.JPanel fontpanel;
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JComboBox jComboBox1;
	private javax.swing.JComboBox jComboBox2;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JComboBox personList;
	private javax.swing.JCheckBox privateChat;
	private javax.swing.JTextPane recieveMsgTextpanel;
	private javax.swing.JButton sendMsgButton;
	private javax.swing.JTextPane sendMsgTextpanel;
	// End of variables declaration//GEN-END:variables

	//--- add by chard
	private String[] fontNames;// 字体名称;

	private String[] fontSizes;// 字体尺寸；

	/**
	 *字体属性辅助类
	 */
	class FontAndText {
		public static final int GENERAL = 0; // 常规
		private String msg = "", name = "宋体"; // 要输入的文本和字体名称

		private int size = 0; //字号

		private Color color = new Color(225, 225, 225); // 文字颜色

		private SimpleAttributeSet attrSet = null; // 属性集

		/**
		 * 一个空的构造（可当做换行使用）
		 */

		public FontAndText() {
		}

		public FontAndText(String msg, String fontName, int fontSize,
				Color color) {
			this.msg = msg;
			this.name = fontName;
			this.size = fontSize;
			this.color = color;
		}

		/**
		 * 返回属性集
		 * 
		 * @return
		 */
		public SimpleAttributeSet getAttrSet() {
			attrSet = new SimpleAttributeSet();
			if (name != null) {
				StyleConstants.setFontFamily(attrSet, name);
			}
			StyleConstants.setBold(attrSet, false);
			StyleConstants.setItalic(attrSet, false);
			StyleConstants.setFontSize(attrSet, size);
			StyleConstants.setLineSpacing(attrSet, 3);
			if (color != null)
				StyleConstants.setForeground(attrSet, color);
			return attrSet;
		}

		public String toString() {
			//将消息分为四块便于在网络上传播
			return name + "|" + size + "|" + color.getRed() + "-"
					+ color.getGreen() + "-" + color.getBlue() + "|" + msg;
		}

		public String getText() {
			return msg;
		}

		public void setText(String text) {
			this.msg = text;
		}

		public Color getColor() {
			return color;
		}

		public void setColor(Color color) {
			this.color = color;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getSize() {
			return size;
		}

		public void setSize(int size) {
			this.size = size;
		}
	}

	public void isToBottom(javax.swing.JScrollBar bar) {
//		System.out.println("bar.getMaximum()========================="
//				+ bar.getMaximum() + ":" + bar.getVisibleAmount() + ":"
//				+ bar.getValue());
		if ((bar.getMaximum() - bar.getVisibleAmount()) > bar.getValue()) {
			ChatMainPanel.this.isToBottom = false;
//			System.out
//					.println("gun dong tiao  ==================== not bottom");
		} else {
			ChatMainPanel.this.isToBottom = true;
//			System.out.println("gun dong tiao  ====================bottom");
		}
	}

	public class JScrollBarAdjustmentListener implements
			java.awt.event.AdjustmentListener {

		private javax.swing.JScrollBar bar;

		public JScrollBarAdjustmentListener(javax.swing.JScrollBar bar1) {
			bar = bar1;
		}

		@Override
		public void adjustmentValueChanged(AdjustmentEvent e) {
			int type = e.getAdjustmentType();
			if (type == 5) {
				return;
			}
//			System.out.println("e.getAdjustmentType()====================="
//					+ e.getAdjustmentType());
			isToBottom(bar);
		}

	}

	public class JScrollBarMouseAdapter extends java.awt.event.MouseAdapter {
		private javax.swing.JScrollBar bar;

		public JScrollBarMouseAdapter(javax.swing.JScrollBar bar1) {
			bar = bar1;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			isToBottom(bar);
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			isToBottom(bar);
		}

		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			isToBottom(bar);
		}
	}
	
	public void clearReceive(){
		this.recieveMsgTextpanel.setText("");
	}

}
