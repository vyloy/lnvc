package com.lorent.common.component;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import com.sun.awt.AWTUtilities;

public class FadeWindow {

	static float value = 1.0f;
	protected Timer timer;
	

	private Window instance = null;
	private Integer bottmToolKitHeight;
	private Integer x;
	private Integer y;
	
	public FadeWindow(Window win){
		instance = win;
		AWTUtilities.setWindowOpacity(instance, 0.0f);
	}
	
	public void setWindowOpacity(float opacity){
		AWTUtilities.setWindowOpacity(instance, opacity);
	}

	public void startFadeIn() {

		instance.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				timer.stop();
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						AWTUtilities.setWindowOpacity(instance, 1.0f);
					}
				});
			}
		});
		
		value = 0.0f;
		timer = new Timer(100, new FadeInListener());
		timer.start();
	}

	public void startFadeOut() {
		value = 1.0f;
		timer = new Timer(100, new FadeOutListener());
		timer.start();
	}

	public void startRuning(){
		bottmToolKitHeight = Toolkit.getDefaultToolkit().getScreenInsets(instance.getGraphicsConfiguration()).bottom;
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		x = dimension.width - instance.getWidth();
		y = dimension.height;
		instance.setLocation(x, y);
		AWTUtilities.setWindowOpacity(instance, 1.0f);
//		instance.setVisible(true);
		
		new Thread() {

			@Override
			public void run() {
				Integer delay = 1;
				Integer step = 1;
				Integer end = instance.getHeight() + bottmToolKitHeight;
				while (true) {
					try {
						step++;
						y = y - 1;
						instance.setLocation(x, y);
						if (step > end) {
							// Thread.sleep(stayTime);
							break;
						}
						Thread.sleep(delay);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		}.start();
	}
	
	public void setPosistionInRightBottom() {
		bottmToolKitHeight = Toolkit.getDefaultToolkit().getScreenInsets(instance.getGraphicsConfiguration()).bottom;
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth() - instance.getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight() - instance.getHeight() - bottmToolKitHeight;
		instance.setLocation((int) width, (int) height);
	}

	//由透明变不透明
	class FadeInListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			value += 0.02f;
			if (value <= 1.0f) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						AWTUtilities.setWindowOpacity(instance, value);
					}
				});
			} else {
				//				System.exit(0);
			}
		}
	}

	//由不透明至透明
	class FadeOutListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			value -= 0.02f;
			if (value >= 0.02f) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						AWTUtilities.setWindowOpacity(instance, value);
					}
				});
			} else {
				//				System.exit(0);
			}
		}

	}
	
	public void setVisible(boolean flag){
		instance.setVisible(flag);
	}
	
	public Timer getTimer() {
		return timer;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame jFrame = new javax.swing.JFrame();
				
				final JDialog jDialog = new JDialog(jFrame,false);

//				jDialog.setSize(270, 120);
				
//				final FadeDialog dialog = new FadeDialog(jFrame, false);
				jDialog.addWindowListener(new java.awt.event.WindowAdapter() {
					public void windowClosing(java.awt.event.WindowEvent e) {
						System.exit(0);
					}
				});
				/*
				jDialog.addComponentListener(new ComponentListener() {
					
					@Override
					public void componentShown(ComponentEvent e) {
						jDialog.setVisible(true);
					}
					
					@Override
					public void componentResized(ComponentEvent e) {
						if (e.getSource().equals(jDialog)) {
							
						}
					}
					
					@Override
					public void componentMoved(ComponentEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void componentHidden(ComponentEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
				*/
//				JOptionPane.showMessageDialog(null, "");
//				JOptionPane.showConfirmDialog(null, "");
				
				final JOptionPane jOptionPane = new JOptionPane("某公司某某某xxxxxxxxxxxxxxx (xxxxxxx)  申请控制，是否允许？",JOptionPane.QUESTION_MESSAGE,JOptionPane.YES_NO_OPTION);
				
				jOptionPane.addPropertyChangeListener(new PropertyChangeListener() {
					
					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						String propertyName = evt.getPropertyName();
						
						System.out.println(propertyName+" , "+evt.getSource()+" , "+evt.getNewValue());
						if (jDialog.isVisible() && evt.getSource() == jOptionPane && propertyName.equals(JOptionPane.VALUE_PROPERTY)) {
//							jDialog.setVisible(false);
//							jDialog.dispose();
//							System.exit(0);
							if (evt.getNewValue().equals(JOptionPane.YES_OPTION)) {
								System.out.println("yes");
							}
							else if (evt.getNewValue().equals(JOptionPane.NO_OPTION)) {
								System.out.println("no");
							}
						}
					}
				});
				
				
//				jDialog.add(new JButton("dfdf"));
//				jDialog.setContentPane(jOptionPane);
				jDialog.add(jOptionPane);
//				JDialog createDialog = jOptionPane.createDialog(jFrame,"信息提示");
//				createDialog.setSize(300, 300);
//				createDialog.setVisible(true);
//				createDialog.setVisible(false);
				jDialog.pack();
				FadeWindow fadeWindow = new FadeWindow(jDialog);
				fadeWindow.setPosistionInRightBottom();
				fadeWindow.setWindowOpacity(1.0f);
				fadeWindow.setVisible(true);
				
//				fadeWindow.setPosistionInRightBottom();
//				fadeWindow.startFadeIn();
//				fadeWindow.setWindowOpacity(1.0f);
				
//				fadeWindow.startRuning();
			}
		});
	}

}
