/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * InputField.java
 *
 * Created on 2011-9-9, 11:03:02
 */
package com.lorent.vovo.ui;

import org.jdesktop.swingx.JXDatePicker;

/**
 *
 * @author jack
 */
public class DateInput extends javax.swing.JPanel {

    public DateInput() {
        initComponents();
    }
    
    public void setLabel(String str){
        this.txtLbl.setText(str);
    }
    
    public String getLabel(){
        return this.txtLbl.getText();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtLbl = new javax.swing.JLabel();
        jXDatePicker1 = new org.jdesktop.swingx.JXDatePicker();

        txtLbl.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(txtLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jdesktop.swingx.JXDatePicker jXDatePicker1;
    private javax.swing.JLabel txtLbl;
    // End of variables declaration//GEN-END:variables

    public JXDatePicker getjXDatePicker1() {
        return jXDatePicker1;
    }

    public void setjXDatePicker1(JXDatePicker jXDatePicker1) {
        this.jXDatePicker1 = jXDatePicker1;
    }
    
    
}
