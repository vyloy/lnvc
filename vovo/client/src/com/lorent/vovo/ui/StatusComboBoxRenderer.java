package com.lorent.vovo.ui;

import java.awt.Component;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import org.apache.log4j.Logger;

import com.lorent.common.manager.ViewManager;
import com.lorent.vovo.controller.LoginStatusManager;
import com.lorent.vovo.dto.LoginStatus;
import com.lorent.vovo.util.Constants;

public class StatusComboBoxRenderer implements ListCellRenderer {

	private static Logger log = Logger.getLogger(StatusComboBoxRenderer.class);
	private static String[] status;
	private static ImageIcon[] images;
	
	static{
		List<LoginStatus> list = LoginStatusManager.getLoginStatusList();
		String[] strs = new String[list.size()];
        for(int i=0;i<list.size();i++){
            LoginStatus ls = list.get(i);
            strs[i] = ls.getStatus();
        }
        status = strs;
        images = new ImageIcon[status.length];
        for (int i = 0; i < status.length; i++) {
            int statusCode = LoginStatusManager.getStatusCodeByActionCommand(status[i]);
            try {
            	switch (statusCode) {
				case Constants.STATUS_ONLINE:
					images[i] = new ImageIcon(StatusComboBoxRenderer.class.getResource("/com/lorent/vovo/resource/images/state_ico_ok.png"));
					break;
				case Constants.STATUS_OFFLINE:
					images[i] = new ImageIcon(StatusComboBoxRenderer.class.getResource("/com/lorent/vovo/resource/images/state_ico_offline.png"));
					break;
					
				case Constants.STATUS_BUSY:
					images[i] = new ImageIcon(StatusComboBoxRenderer.class.getResource("/com/lorent/vovo/resource/images/state_ico_busy.png"));
					break;
					
				case Constants.STATUS_AWAY:
					images[i] = new ImageIcon(StatusComboBoxRenderer.class.getResource("/com/lorent/vovo/resource/images/state_ico_away.png"));
					break;
				default:
					break;
				}
            } catch (Exception ex) {
                log.error("StatusComboBoxRenderer", ex);
            }
        }
	}
	
	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		int selectedIndex = 0;
        LoginStatus ls = (LoginStatus)value;
        for(int i=0;i<status.length;i++){
            if(status[i].equals(ls.getStatus())){
                  selectedIndex = i;
                  break;
            }
        }
        JLabel jLabel = new JLabel(images[selectedIndex]);
        jLabel.setText(status[selectedIndex]);
        jLabel.setFont(list.getFont());
        if (isSelected) {
//        	jLabel.setBackground(list.getSelectionBackground());
//        	jLabel.setForeground(list.getSelectionForeground());
        	jLabel.setBorder(BorderFactory.createEtchedBorder());
        	
		}
        else{
//        	jLabel.setBackground(list.getBackground());
//        	jLabel.setForeground(list.getForeground());
        	jLabel.setBorder(null);
        }
		return jLabel;
		
	}

}
