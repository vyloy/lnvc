package com.lorent.vovo.ui;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.UIManager;

import com.lorent.common.tree.MemberBean;



public class SelectedUserList extends JList {
	
	private Map<String,DepartmentTreeNodePanel> userMap = new HashMap<String,DepartmentTreeNodePanel>();
	
	public SelectedUserList(){
		this.setModel(new DefaultListModel());
        this.setCellRenderer(new MyListCellRenderer());
        DefaultListModel model = (DefaultListModel) this.getModel();
        model.removeAllElements();
	}

	private class MyListCellRenderer extends DefaultListCellRenderer{

        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
//            MemberDto data = (MemberDto) value;
//            System.out.println(data.getName());
            
            DepartmentTreeNodePanel item = (DepartmentTreeNodePanel) value;
            item.setInfo(item.getInfo());
            item.getCheckBox().setVisible(false);
            item.getjLabel1().setVisible(false);
            item.getjLabel4().setVisible(false);
            if(isSelected || cellHasFocus){
                item.setBackground(UIManager.getColor("List.selectionBackground"));
//                item.setBackground(Color.WHITE);
            }else{
                item.setBackground(UIManager.getColor("List.background"));
            }
            return item;
        }
    }
	
	
	public void addDepartmentTreeNodePanel(DepartmentTreeNodePanel panel){
		DefaultListModel model = (DefaultListModel) this.getModel();
		if(!panel.getInfo().isDepartmentFlag() && !userMap.containsKey(panel.getInfo().getMember().getJid())){
			DepartmentTreeNodePanel p = new DepartmentTreeNodePanel();
			p.setInfo(panel.getInfo());
			p.setDepOrUserNameLabelText(panel.getDepOrUserNameLabelText());
			userMap.put(panel.getInfo().getMember().getJid(), p);
	        model.addElement(p);
		}
	}
	
	public void removeDepartmentTreeNodePanel(DepartmentTreeNodePanel panel){
		DefaultListModel model = (DefaultListModel) this.getModel();
		Enumeration<DepartmentTreeNodePanel> en = (Enumeration<DepartmentTreeNodePanel>) model.elements();
		while(en.hasMoreElements()){
			DepartmentTreeNodePanel p = en.nextElement();
			if(!panel.getInfo().isDepartmentFlag() && p.getInfo().getMember().getJid().equals(panel.getInfo().getMember().getJid())){
				model.removeElement(p);
				userMap.remove(p.getInfo().getMember().getJid());
				break;
			}
		}
	}
	
	public void reflashMemberNode(DepartmentTreeNodePanel panel){
		DefaultListModel model = (DefaultListModel) this.getModel();
		Enumeration<DepartmentTreeNodePanel> en = (Enumeration<DepartmentTreeNodePanel>) model.elements();
		while(en.hasMoreElements()){
			DepartmentTreeNodePanel p = en.nextElement();
			if(!panel.getInfo().isDepartmentFlag() && p.getInfo().getMember().getJid().equals(panel.getInfo().getMember().getJid())){
				p.setInfo(panel.getInfo());
				this.repaint();
				break;
			}
		}
	}
	
	public List<String> getMembers(){
		List<String> members = new ArrayList<String>();
		DefaultListModel model = (DefaultListModel) this.getModel();
		Enumeration<DepartmentTreeNodePanel> en = (Enumeration<DepartmentTreeNodePanel>) model.elements();
		while(en.hasMoreElements()){
			DepartmentTreeNodePanel p = en.nextElement();
			members.add(p.getInfo().getMember().getJid());
		}
		return members;
	}
	
	public List<String> getMembersUserName(){
		List<String> members = new ArrayList<String>();
		DefaultListModel model = (DefaultListModel) this.getModel();
		Enumeration<DepartmentTreeNodePanel> en = (Enumeration<DepartmentTreeNodePanel>) model.elements();
		while(en.hasMoreElements()){
			DepartmentTreeNodePanel p = en.nextElement();
			members.add(p.getInfo().getMember().getUsername());
		}
		return members;
	}
	
	public List<String> getMembersLccAccounts(){
		List<String> members = new ArrayList<String>();
		DefaultListModel model = (DefaultListModel) this.getModel();
		Enumeration<DepartmentTreeNodePanel> en = (Enumeration<DepartmentTreeNodePanel>) model.elements();
		while(en.hasMoreElements()){
			DepartmentTreeNodePanel p = en.nextElement();
			members.add(p.getInfo().getMember().getLccAccount());
		}
		return members;
	}
	
	public List<MemberBean> getMemberBeans(){
		List<MemberBean> members = new ArrayList<MemberBean>();
		DefaultListModel model = (DefaultListModel) this.getModel();
		Enumeration<DepartmentTreeNodePanel> en = (Enumeration<DepartmentTreeNodePanel>) model.elements();
		while(en.hasMoreElements()){
			DepartmentTreeNodePanel p = en.nextElement();
			members.add(p.getInfo().getMember());
		}
		return members;
	}
}
