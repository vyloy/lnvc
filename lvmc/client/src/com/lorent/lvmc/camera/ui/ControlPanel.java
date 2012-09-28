package com.lorent.lvmc.camera.ui;

import gnu.io.CommPortIdentifier;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.HashSet;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

import com.lorent.lvmc.camera.CameraController;
import com.lorent.lvmc.camera.PanTiltDrive;
import com.lorent.lvmc.camera.PelcoDPanTiltDrive;
import com.lorent.lvmc.camera.PelcoPPanTiltDrive;

public class ControlPanel extends JPanel {
	private JComboBox comPorts;
	private CameraController controller;
	boolean connected;
	private JButton connectButton;
	private JComboBox pantiltNumber;
	private JSlider panSpeed;
	private JSlider tiltSpeed;
	private JButton leftButton;
	private JButton upButton;
	private JButton downButton;
	private JButton rightButton;
	private JPanel drivePanel;
	private JPanel speedPanel;
	private JPanel connectPanel;
	private JLabel label_2;
	private JComboBox protocol;
	private JButton zoomTeleButton;
	private JButton zoomWideButton;
	private JButton button_2;

	/**
	 * Create the panel.
	 */
	public ControlPanel() {
		setLayout(new BorderLayout(0, 0));
		connectPanel = new JPanel();
		add(connectPanel, BorderLayout.NORTH);
		JLabel label_1 = new JLabel("串口号：");
		connectPanel.add(label_1);
		comPorts = new JComboBox();
		connectPanel.add(comPorts);
		comPorts.setSize(new Dimension(33, 23));
		comPorts.setMaximumRowCount(1);
		comPorts.setMaximumSize(new Dimension(38, 23));
		JLabel label = new JLabel("云台号：");
		connectPanel.add(label);
		pantiltNumber = new JComboBox();
		connectPanel.add(pantiltNumber);
		pantiltNumber.setMaximumSize(new Dimension(33, 23));
		label_2 = new JLabel("协议：");
		connectPanel.add(label_2);
		protocol = new JComboBox();
		connectPanel.add(protocol);
		connectButton = new JButton("连接");
		connectPanel.add(connectButton);
		connectButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(!connected){
					controller=new CameraController((PanTiltDrive) protocol.getSelectedItem());
					try {
						controller.connect((String) comPorts.getSelectedItem());
						connected=true;
						connectButton.setText("断开");
						connected();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}else{
					controller.close();
					connected=false;
					connectButton.setText("连接");
					disconnected();
				}
			}
		});
		speedPanel = new JPanel();
		add(speedPanel, BorderLayout.WEST);
		GridBagLayout gbl_speedPanel = new GridBagLayout();
		gbl_speedPanel.columnWidths = new int[]{22, 60, 73, 0};
		gbl_speedPanel.rowHeights = new int[]{24, 24, 0};
		gbl_speedPanel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_speedPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		speedPanel.setLayout(gbl_speedPanel);
		JLabel lblNewLabel = new JLabel("水平转速：");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		speedPanel.add(lblNewLabel, gbc_lblNewLabel);
		panSpeed = new JSlider();
		panSpeed.setMaximumSize(new Dimension(100, 24));
		panSpeed.setPreferredSize(new Dimension(100, 24));
		panSpeed.setMinimumSize(new Dimension(50, 24));
		panSpeed.setMinimum(1);
		panSpeed.setMaximum(18);
		panSpeed.setValue(9);
		GridBagConstraints gbc_panSpeed = new GridBagConstraints();
		gbc_panSpeed.anchor = GridBagConstraints.NORTHWEST;
		gbc_panSpeed.insets = new Insets(0, 0, 5, 0);
		gbc_panSpeed.gridx = 2;
		gbc_panSpeed.gridy = 0;
		speedPanel.add(panSpeed, gbc_panSpeed);
		JLabel lblNewLabel_1 = new JLabel("垂直转速：");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 1;
		speedPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		tiltSpeed = new JSlider();
		tiltSpeed.setPreferredSize(new Dimension(100, 24));
		tiltSpeed.setMinimumSize(new Dimension(50, 24));
		tiltSpeed.setMaximumSize(new Dimension(100, 24));
		tiltSpeed.setMaximum(17);
		tiltSpeed.setMinimum(1);
		tiltSpeed.setValue(8);
		GridBagConstraints gbc_tiltSpeed = new GridBagConstraints();
		gbc_tiltSpeed.anchor = GridBagConstraints.NORTHWEST;
		gbc_tiltSpeed.gridx = 2;
		gbc_tiltSpeed.gridy = 1;
		speedPanel.add(tiltSpeed, gbc_tiltSpeed);
		drivePanel = new JPanel();
		add(drivePanel, BorderLayout.CENTER);
		GridBagLayout gbl_drivePanel = new GridBagLayout();
		gbl_drivePanel.columnWidths = new int[]{40, 47, 47, 47, 0};
		gbl_drivePanel.rowHeights = new int[]{25, 25, 25, 0};
		gbl_drivePanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_drivePanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		drivePanel.setLayout(gbl_drivePanel);
		upButton = new JButton("上");
		upButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					controller.up(getAddress(), panSpeed.getValue(), tiltSpeed.getValue());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				try {
					controller.stop(getAddress());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		zoomTeleButton = new JButton("放大");
		zoomTeleButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					controller.zoomTele(getAddress());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				try {
					controller.zoomStop(getAddress());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_zoomTeleButton = new GridBagConstraints();
		gbc_zoomTeleButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_zoomTeleButton.insets = new Insets(0, 0, 5, 5);
		gbc_zoomTeleButton.gridx = 1;
		gbc_zoomTeleButton.gridy = 0;
		drivePanel.add(zoomTeleButton, gbc_zoomTeleButton);
		GridBagConstraints gbc_upButton = new GridBagConstraints();
		gbc_upButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_upButton.anchor = GridBagConstraints.NORTH;
		gbc_upButton.insets = new Insets(0, 0, 5, 5);
		gbc_upButton.gridx = 2;
		gbc_upButton.gridy = 0;
		drivePanel.add(upButton, gbc_upButton);
		leftButton = new JButton("左");
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					controller.left(getAddress(), panSpeed.getValue(), tiltSpeed.getValue());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				try {
					controller.stop(getAddress());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		zoomWideButton = new JButton("缩小");
		zoomWideButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					controller.zoomWide(getAddress());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				try {
					controller.zoomStop(getAddress());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_zoomWideButton = new GridBagConstraints();
		gbc_zoomWideButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_zoomWideButton.insets = new Insets(0, 0, 5, 0);
		gbc_zoomWideButton.gridx = 3;
		gbc_zoomWideButton.gridy = 0;
		drivePanel.add(zoomWideButton, gbc_zoomWideButton);
		GridBagConstraints gbc_leftButton = new GridBagConstraints();
		gbc_leftButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_leftButton.anchor = GridBagConstraints.NORTH;
		gbc_leftButton.insets = new Insets(0, 0, 5, 5);
		gbc_leftButton.gridx = 1;
		gbc_leftButton.gridy = 1;
		drivePanel.add(leftButton, gbc_leftButton);
		rightButton = new JButton("右");
		rightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					controller.right(getAddress(), panSpeed.getValue(), tiltSpeed.getValue());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				try {
					controller.stop(getAddress());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		button_2 = new JButton("重置");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					controller.reset(getAddress());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_button_2 = new GridBagConstraints();
		gbc_button_2.insets = new Insets(0, 0, 5, 5);
		gbc_button_2.gridx = 2;
		gbc_button_2.gridy = 1;
		drivePanel.add(button_2, gbc_button_2);
		GridBagConstraints gbc_rightButton = new GridBagConstraints();
		gbc_rightButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_rightButton.anchor = GridBagConstraints.NORTH;
		gbc_rightButton.insets = new Insets(0, 0, 5, 0);
		gbc_rightButton.gridx = 3;
		gbc_rightButton.gridy = 1;
		drivePanel.add(rightButton, gbc_rightButton);
		downButton = new JButton("下");
		downButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					controller.down(getAddress(), panSpeed.getValue(), tiltSpeed.getValue());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				try {
					controller.stop(getAddress());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_downButton = new GridBagConstraints();
		gbc_downButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_downButton.anchor = GridBagConstraints.NORTH;
		gbc_downButton.insets = new Insets(0, 0, 0, 5);
		gbc_downButton.gridx = 2;
		gbc_downButton.gridy = 2;
		drivePanel.add(downButton, gbc_downButton);
		connectPanel.setBorder(BorderFactory.createTitledBorder("连接设备"));
		drivePanel.setBorder(BorderFactory.createTitledBorder("操作镜头"));
		speedPanel.setBorder(BorderFactory.createTitledBorder("移动速度"));
		
		init();
		disconnected();
	}
	
	private void connected(){
		PanTiltDrive panTiltDrive = controller.getPanTiltDrive();
		panSpeed.setMinimum(panTiltDrive.getMinPanSpeed());
		panSpeed.setMaximum(panTiltDrive.getMaxPanSpeed());
		panSpeed.setValue(panTiltDrive.getDefaultPanSpeed());
		tiltSpeed.setMaximum(panTiltDrive.getMaxTiltSpeed());
		tiltSpeed.setMinimum(panTiltDrive.getMinTiltSpeed());
		tiltSpeed.setValue(panTiltDrive.getDefaultTiltSpeed());
		Component[] components = drivePanel.getComponents();
		for (int i = 0; i < components.length; i++) {
			components[i].setEnabled(true);
		}
		components = speedPanel.getComponents();
		for (int i = 0; i < components.length; i++) {
			components[i].setEnabled(true);
		}
		comPorts.setEnabled(false);
		pantiltNumber.setEnabled(false);
		protocol.setEnabled(false);
		
	}
	
	private void disconnected(){
		Component[] components = drivePanel.getComponents();
		for (int i = 0; i < components.length; i++) {
			components[i].setEnabled(false);
		}
		components = speedPanel.getComponents();
		for (int i = 0; i < components.length; i++) {
			components[i].setEnabled(false);
		}
		comPorts.setEnabled(true);
		pantiltNumber.setEnabled(true);
		protocol.setEnabled(true);
	}
	
	private void init(){
		for (int i = 1; i < 6; i++) {
			pantiltNumber.addItem(String.valueOf(i));
		}
		HashSet<CommPortIdentifier> availableSerialPorts = CameraController.getAvailableSerialPorts();
		for(CommPortIdentifier i :availableSerialPorts){
			String name = i.getName();
			comPorts.addItem(name);
		}
		
		protocol.addItem(new PanTiltDrive());
		protocol.addItem(new PelcoDPanTiltDrive());
		protocol.addItem(new PelcoPPanTiltDrive());
	}
	public int getAddress(){
		return Integer.parseInt((String) pantiltNumber.getSelectedItem());
	}

	public JComboBox getComPorts() {
		return comPorts;
	}
	public JButton getConnectButton() {
		return connectButton;
	}
	public JComboBox getPantiltNumber() {
		return pantiltNumber;
	}
	public JSlider getPanSpeed() {
		return panSpeed;
	}
	public JSlider getTiltSpeed() {
		return tiltSpeed;
	}
	public JButton getLeftButton() {
		return leftButton;
	}
	public JButton getUpButton() {
		return upButton;
	}
	public JButton getDownButton() {
		return downButton;
	}
	public JButton getRightButton() {
		return rightButton;
	}
	public JPanel getDrivePanel() {
		return drivePanel;
	}
	public JPanel getSpeedPanel() {
		return speedPanel;
	}
	public JPanel getConnectPanel() {
		return connectPanel;
	}
	
	public void dispose(){
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				if(connected)
					controller.close();
			}
		});
	}
}
