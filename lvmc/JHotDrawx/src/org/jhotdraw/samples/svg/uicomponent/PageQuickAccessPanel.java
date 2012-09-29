package org.jhotdraw.samples.svg.uicomponent;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.lorent.whiteboard.client.ViewParam;

public class PageQuickAccessPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private transient JTextField pageField;
	private transient JLabel pagesLabel;
	private transient JButton first;
	private transient JButton pre;
	private transient JButton next;
	private transient JButton last;
	private volatile int page;
	private volatile int pages;
	
	public PageQuickAccessPanel() {
		this(1,1);
	}

	public PageQuickAccessPanel(int page, int pages) {
		this.page = page;
		this.pages = pages;
		
		pageField = new JTextField(String.valueOf(page));
		pageField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					String pageText = pageField.getText();
					try{
						int page = Integer.parseInt(pageText);
						if(page<1||page>PageQuickAccessPanel.this.pages)
							throw new IllegalArgumentException();
						ViewParam.getView().setPage(page);
					}catch(RuntimeException re){
						pageField.setText(String.valueOf(PageQuickAccessPanel.this.page));
					}
				}
			}
		});
		add(pageField);
		pageField.setColumns(2);
		
		pagesLabel = new JLabel("/ "+String.valueOf(pages));
		add(pagesLabel);
		
	}

	public JLabel getPagesLabel() {
		return pagesLabel;
	}
	
	public void setPage(int page){
		this.page=page;
		refreshPage();
	}
	
	private void refreshPage(){
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				if(first!=null&&pageField!=null){
					boolean back=true;
					boolean forward=true;
					if(page==1){
						back=false;
					}
					if(page==pages){
						forward=false;
					}
					setDrawBackButtons(back);
					setGoForwardButtons(forward);
					if(!back&&!forward&&pageField.isEnabled()){
						pageField.setEnabled(false);
					}else if(!pageField.isEnabled()){
						pageField.setEnabled(true);
					}
					pageField.setText(String.valueOf(page));
					pageField.repaint();
				}
			}
		});
	}
	
	private void refreshPages(){
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				if(pagesLabel==null)
					return;
				pagesLabel.setText("/ "+String.valueOf(pages));
				pagesLabel.repaint();
			}
		});
	}
	
	public synchronized void setPagecount(int pages){
		if(pages>this.pages){
			this.pages=pages;
			refreshPage();
			refreshPages();
		}
	}
	
	public int getPagecount(){
		return pages;
	}

	private void setDrawBackButtons(boolean enable){
		if(first.isEnabled()!=enable){
			first.setEnabled(enable);
		}
		if(pre.isEnabled()!=enable){
			pre.setEnabled(enable);
		}
	}
	
	private void setGoForwardButtons(boolean enable){
		if(next.isEnabled()!=enable){
			next.setEnabled(enable);
		}
		if(last.isEnabled()!=enable){
			last.setEnabled(enable);
		}
	}
	
	public void setReciever(JButton first,JButton pre,JButton next,JButton last){
		this.first=first;
		this.pre=pre;
		this.next=next;
		this.last=last;
		refreshPage();
	}
	
	@Override
	public Dimension getPreferredSize() {
		Dimension fs = pageField.getPreferredSize();
		Dimension ls = pagesLabel.getPreferredSize();
		return new Dimension(fs.width+ls.width+10, Math.max(fs.height, ls.height)+6);
	}

	@Override
	public Dimension getMaximumSize() {
		return this.getPreferredSize();
	}

	@Override
	public Dimension getMinimumSize() {
		return this.getPreferredSize();
	}
}
