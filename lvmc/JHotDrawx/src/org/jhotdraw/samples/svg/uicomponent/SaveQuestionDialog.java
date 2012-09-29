package org.jhotdraw.samples.svg.uicomponent;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.text.DefaultFormatter;

import org.jhotdraw.draw.DefaultDrawingView;
import org.jhotdraw.util.PicsGenerator;

import com.lorent.whiteboard.client.ViewParam;
import com.lorent.whiteboard.model.View;

public class SaveQuestionDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();
	private JSpinner beginPageSpinner;
	private JSpinner endPageSpinner;
	private JProgressBar progressBar;
	private JButton okButton;
	private JButton cancelButton;
	private PicsGenerator picsGenerator;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SaveQuestionDialog dialog = new SaveQuestionDialog(15);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SaveQuestionDialog(int pagecount) {
		this.setModal(true);
		setBounds(100, 100, 186, 149);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 52, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel label = new JLabel("起始页码：");
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.insets = new Insets(0, 0, 5, 5);
			gbc_label.gridx = 1;
			gbc_label.gridy = 0;
			contentPanel.add(label, gbc_label);
		}
		{
			beginPageSpinner = new JSpinner(new SpinnerNumberModel(1,1,pagecount,1));
			JComponent comp = beginPageSpinner.getEditor();
		    JFormattedTextField field = (JFormattedTextField) comp.getComponent(0);
		    DefaultFormatter formatter = (DefaultFormatter) field.getFormatter();
		    formatter.setCommitsOnValidEdit(true);
		    beginPageSpinner.addChangeListener(new ChangeListener() {

		        @Override
		        public void stateChanged(ChangeEvent e) {
		        	Integer value = (Integer) beginPageSpinner.getValue();
		        	if(value.compareTo((Integer) endPageSpinner.getValue())>0){
		        		endPageSpinner.setValue(value);
		        	}
		        	((SpinnerNumberModel)endPageSpinner.getModel()).setMinimum(value);
		        }
		    });
			GridBagConstraints gbc_beginPageSpinner = new GridBagConstraints();
			gbc_beginPageSpinner.insets = new Insets(0, 0, 5, 0);
			gbc_beginPageSpinner.gridx = 2;
			gbc_beginPageSpinner.gridy = 0;
			contentPanel.add(beginPageSpinner, gbc_beginPageSpinner);
		}
		{
			JLabel label = new JLabel("结束页码：");
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.insets = new Insets(0, 0, 5, 5);
			gbc_label.gridx = 1;
			gbc_label.gridy = 1;
			contentPanel.add(label, gbc_label);
		}
		{
			endPageSpinner = new JSpinner(new SpinnerNumberModel(pagecount,1,pagecount,1));
			GridBagConstraints gbc_endPageSpinner = new GridBagConstraints();
			gbc_endPageSpinner.insets = new Insets(0, 0, 5, 0);
			gbc_endPageSpinner.gridx = 2;
			gbc_endPageSpinner.gridy = 1;
			contentPanel.add(endPageSpinner, gbc_endPageSpinner);
		}
		{
			JLabel label = new JLabel("进度：");
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.anchor = GridBagConstraints.EAST;
			gbc_label.insets = new Insets(0, 0, 0, 5);
			gbc_label.gridx = 1;
			gbc_label.gridy = 2;
			contentPanel.add(label, gbc_label);
		}
		{
			progressBar = new JProgressBar(0, pagecount) {
				private static final long serialVersionUID = 1L;

				public void setValue(int n) {
					super.setValue(n);
					if (n == this.getMaximum()){
						okButton.setVisible(false);
						cancelButton.setText("完成");
					}
				}
			};
			progressBar.setMinimumSize(new Dimension(65, 16));
			progressBar.setStringPainted(true);
			GridBagConstraints gbc_progressBar = new GridBagConstraints();
			gbc_progressBar.gridx = 2;
			gbc_progressBar.gridy = 2;
			contentPanel.add(progressBar, gbc_progressBar);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("确定");
				okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						File homeDirectory = FileSystemView.getFileSystemView().getHomeDirectory();
						JFileChooser fileChooser = new JFileChooser(homeDirectory);
						fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
						fileChooser.addChoosableFileFilter(new FileFilter() {
							
							@Override
							public String getDescription() {
								return "Folder";
							}
							
							@Override
							public boolean accept(File f) {
								if(f.isDirectory())
									return true;
								return false;
							}
						});
						View view = ViewParam.getView();
						int result = fileChooser.showSaveDialog(SaveQuestionDialog.this);
						if(result==JFileChooser.APPROVE_OPTION){
							File file = fileChooser.getSelectedFile();
							okButton.setEnabled(false);
							Integer beginPage = (Integer)beginPageSpinner.getValue();
							Integer count = (Integer)endPageSpinner.getValue()+1-beginPage;
							picsGenerator = new PicsGenerator(
									(DefaultDrawingView) view, file,
									beginPage, count, progressBar);
							progressBar.setMaximum(count);
							picsGenerator.run();
						}
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("取消");
				cancelButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if(picsGenerator!=null){
							picsGenerator.interrupt();
						}
						dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
		Image icon = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB_PRE);
		setIconImage(icon);
	}
	
	public void dispose(){
		setVisible(false);
		super.dispose();
	}

	public JSpinner getBeginPageSpinner() {
		return beginPageSpinner;
	}
	public JSpinner getEndPageSpinner() {
		return endPageSpinner;
	}
	public JProgressBar getProgressBar() {
		return progressBar;
	}
	public JButton getOkButton() {
		return okButton;
	}
	public JButton getCancelButton() {
		return cancelButton;
	}
}
