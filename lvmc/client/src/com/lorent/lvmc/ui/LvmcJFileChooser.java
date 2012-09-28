/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.lvmc.ui;


import com.lorent.common.util.ParaUtil;
import com.lorent.lvmc.controller.ControllerFacade;
import com.lorent.lvmc.util.Constants;
import com.lorent.lvmc.util.StringUtil;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class LvmcJFileChooser extends JFileChooser{
    
    private boolean convertFileToImgFlag = false;

    public boolean getConvertFileToImgFlag() {
        return convertFileToImgFlag;
    }

    public void setConvertFileToImgFlag(boolean convertFileToImgFlag) {
        this.convertFileToImgFlag = convertFileToImgFlag;
    }
    
    
    
    public LvmcJFileChooser(){
        super();
//        super.setDialogType(JFileChooser.OPEN_DIALOG);
//        System.out.println(super.getLayout());
        Component[] chooserChildren = super.getComponents();
        javax.swing.JPanel bottomPanel = (javax.swing.JPanel)chooserChildren[3];
        Component[] bottomPanelChildren = bottomPanel.getComponents();
        javax.swing.JPanel jp1 = (javax.swing.JPanel)bottomPanelChildren[3];
        Component[] cp2 = jp1.getComponents();
//        System.out.println(jp1.getClass() + "===" + jp1.getLayout());
        javax.swing.JButton uploadAndChangeButton = new javax.swing.JButton(StringUtil.getUIString("LvmcJFileChooser.uploadAndChangeButton.text"));
//        uploadAndChangeButton.setToolTipText("");
//        jp1.add(uploadAndChangeButton);
        javax.swing.JButton[] buttons = new javax.swing.JButton[cp2.length];
        for(int i = 0;i<buttons.length;i++){
            buttons[i] = (javax.swing.JButton)cp2[i];
            buttons[i].setToolTipText(null);
            if(i == 0){
                buttons[i].setText(StringUtil.getUIString("LvmcJFileChooser.uploadButton.text"));
            }
//            javax.swing.JButton b = (javax.swing.JButton)cp;
//            System.out.println(cp.getClass() + "===" + b.getText() + "==" + b.getLayout());
        }
        this.setApproveButtonText(StringUtil.getUIString("LvmcJFileChooser.uploadButton.text"));
        java.awt.GridBagConstraints gridBagConstraints;
        jp1.setLayout(null);
        javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
        javax.swing.JPanel jPanel2 = new javax.swing.JPanel();
        jp1.setLayout(new java.awt.GridBagLayout());
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 180, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jp1.add(jPanel1, gridBagConstraints);

        

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 255, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(uploadAndChangeButton)
                    .addGap(6, 6, 6)
                    .addComponent(buttons[0])
                    .addGap(6, 6, 6)
                    .addComponent(buttons[1])
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 10, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(uploadAndChangeButton)
                        .addComponent(buttons[0])
                        .addComponent(buttons[1]))
                    .addGap(0, 10, Short.MAX_VALUE)))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.weighty = 1.0;
        jp1.add(jPanel2, gridBagConstraints);
        
        uploadAndChangeButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                File file = getSelectedFile();
                if(file == null || !file.exists()){
                    return;
                }
//                Boolean flag = (Boolean) ControllerFacade.execute("shareFileListController", "isMaxWhiteBoard");
//                if(flag){
//                    JOptionPane.showMessageDialog(null, StringUtil.getErrorString("ismaxwhiteboard"));
//                    return;
//                }
//                
//                Boolean bSupport = (Boolean) ControllerFacade.execute("shareFileListController", "checkFileSupportConvert", file.getAbsolutePath());
//                if(bSupport) {
//                    System.out.println("===============" + LvmcJFileChooser.this);
//                    ParaUtil paras = ParaUtil.newInstance();
//                    paras.getMap().put("fileChooser", LvmcJFileChooser.this);
//                    ControllerFacade.execute("fileChooserController", "approveSelection", paras);
////                    LvmcJFileChooser.this.setConvertFileToImgFlag(true);
////                    LvmcJFileChooser.super.approveSelection();
//                }
//                else{
//                   JOptionPane.showMessageDialog(null, StringUtil.getErrorString("convertfile.notsupport"));
//                }
                Object[] dparas = new String[]{file.getAbsolutePath()};
                ParaUtil paras = ParaUtil.newInstance();
                paras.getMap().put("fileChooser", LvmcJFileChooser.this);
                paras.getMap().put(Constants.FILE_NAME, file.getAbsolutePath());
                ControllerFacade.execute("fileChooserController", "approveSelection",
                        paras);
            }
        });
    }
    
    
}
