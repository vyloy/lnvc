package com.lorent.vovo.ui;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class TestBoxLayout {
  public static void main(String args[]) {
    JFrame f = new JFrame();
    f.setLayout(new FlowLayout());
    f.setSize(240, 250);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    JScrollPane jscrlpLabel = new JScrollPane(new JLabel(
        "<html>A<br>B<br>C<br>D<br>E<br>F<br>G<br>H<br></html>."));

    jscrlpLabel.setPreferredSize(new Dimension(200, 100));

    JLabel label = new JLabel("Option");
    JCheckBox a = new JCheckBox("A");
    JCheckBox b = new JCheckBox("B");
    JCheckBox c = new JCheckBox("C");
    JCheckBox d = new JCheckBox("D");
    JCheckBox e = new JCheckBox("E");

    final Box box = Box.createVerticalBox();
    box.add(label);
    box.add(a);
    box.add(b);
    box.add(c);
    box.add(d);
    box.add(e);
    JButton bt = new JButton("aa");
    final JScrollPane jscrlpBox = new JScrollPane(box);
    jscrlpBox.setPreferredSize(new Dimension(140, 90));
    bt.addActionListener(new java.awt.event.ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			box.add(new JCheckBox("AA"));
			jscrlpBox.revalidate();
			jscrlpBox.repaint();
		}
    	
    });
    
    f.add(jscrlpLabel);
    f.add(jscrlpBox);
    f.add(bt);
    f.setVisible(true);
  }
}