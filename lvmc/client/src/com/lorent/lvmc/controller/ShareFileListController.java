/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.lvmc.controller;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import org.apache.log4j.Logger;

import com.lorent.common.util.FileUtil;
import com.lorent.lvmc.Launcher;
import com.lorent.lvmc.dto.LoginInfo;
import com.lorent.lvmc.ui.ConfirmUploadFileDialog;
import com.lorent.lvmc.ui.MsgUploadFileDialog;
import com.lorent.lvmc.ui.ShareFileListItem;
import com.lorent.lvmc.ui.ShareFileListPanel;
import com.lorent.lvmc.ui.WhiteBoardPanel;
import com.lorent.lvmc.util.ConfigUtil;
import com.lorent.lvmc.util.DataUtil;
import com.lorent.lvmc.util.LCMUtil;
import com.lorent.lvmc.util.MathUtil;
import com.lorent.lvmc.util.PermissionUtil;
import com.lorent.lvmc.util.StringUtil;

/**
 *
 * @author Administrator
 */
public class ShareFileListController extends BaseController {
    
    private static Logger log = Logger.getLogger(ShareFileListController.class);
    
    
    public void upLoadShareFileProgress(String uniqueFileID,String localFilePath,long position,long length,boolean state) throws Exception{
        ShareFileListPanel panel = ViewManager.getComponent(ShareFileListPanel.class);
        ShareFileListItem upLoadShareFileListItem = panel.getUpLoadShareFileListItem(uniqueFileID);
        if (upLoadShareFileListItem != null) {
            double d = 1;
            if (position <= length) {
                d = 100 * position / length;
                
            }
            final int  n = (int) d;
            upLoadShareFileListItem.getProgressBar().setValue(n);
            if (state) {
                upLoadShareFileListItem.getProgressBar().setString("上传: "+n + "%");
                if (n==100) {
                	upLoadShareFileListItem.getCancelTransferXHyperlink().setVisible(false);
				}
                else{
                	upLoadShareFileListItem.getCancelTransferXHyperlink().setVisible(true);
                }
            }
            else{
                upLoadShareFileListItem.getProgressBar().setString("上传失败："+n + "%");
                upLoadShareFileListItem.getProgressBar().setForeground(Color.red);
                upLoadShareFileListItem.getCancelTransferXHyperlink().setVisible(false);
            }
//            panel.getTransferFileList().repaint();
        }
    }
    
    
    
    public void uploadShareFileOnDrop(List<File> files) throws Exception{
    	if(!PermissionUtil.hasPermission(PermissionUtil.FILE_UPLOAD)){
    		this.showErrorDialog(null, StringUtil.getErrorString("no.permission.upload.file"));
			return;
		}
    	//检查是否拖的是文件夹
    	ShareFileListPanel panel = ViewManager.getComponent(ShareFileListPanel.class);
    	DefaultListModel model = (DefaultListModel) panel.getFileList().getModel();
        Object[] array = model.toArray();
        
        ArrayList<String> existFileNames = new ArrayList<String>();
        final ArrayList<File> findedFiles = new ArrayList<File>();
    	for (File file : files) {
			if (file.isFile()) {
				findedFiles.add(file);
			}
			else if(file.isDirectory()){
				List<File> allFiles = FileUtil.getAllFiles(file);
				findedFiles.addAll(allFiles);
			}
		}
    	//检查文件大小是否超出限制
    	ArrayList<String> tooMaxFileNames = new ArrayList<String>();
    	String maxsize = Launcher.getLCMUtil().getSystemProperties("lvmc").get("sharefile.upload.maxfilesize");
        long maxfilesize = Long.parseLong(maxsize);
        for (File file : findedFiles) {
			if (file.isFile() && file.length() > maxfilesize) {
				tooMaxFileNames.add(file.getName());
			}
		}
        if (tooMaxFileNames.size() > 0) {
        	MsgUploadFileDialog msgUploadFileDialog = new MsgUploadFileDialog(null, true);
        	msgUploadFileDialog.getMessageLabel().setText(StringUtil.getUIString("ShareFileListController.msgUploadFileSizeMax"));
        	DefaultListModel msgModel = (DefaultListModel) msgUploadFileDialog.getMessageList().getModel();
        	msgModel.clear();
        	for (String string : tooMaxFileNames) {
    			msgModel.addElement(string);
    		}
        	ViewManager.setWindowCenterLocation(msgUploadFileDialog);
        	msgUploadFileDialog.setVisible(true);
        	return;
		}
    	
    	//检查文件是否已经存在
    	for (File file : findedFiles) {
    		
	        for (Object obj : array) {
	        	ShareFileListItem item = (ShareFileListItem)obj;
	        	if (item.getFileName().equals(file.getName())) {
	        		existFileNames.add(file.getName());
	        		break;
				}
			}
		}
    	
    	if (existFileNames.size() > 0 ) {
    		ConfirmUploadFileDialog confirmUploadFileDialog = new ConfirmUploadFileDialog(null, true);
        	confirmUploadFileDialog.getMessageLabel().setText(StringUtil.getUIString("ShareFileListController.askUploadConfirmFiles"));
        	DefaultListModel msgModel = (DefaultListModel) confirmUploadFileDialog.getMessageList().getModel();
        	msgModel.clear();
        	for (File file : findedFiles) {
    			msgModel.addElement(file.getName());
    		}
        	ViewManager.setWindowCenterLocation(confirmUploadFileDialog);
        	confirmUploadFileDialog.show();
        	boolean okFlag = confirmUploadFileDialog.isOkFlag();
        	if (!okFlag) {
        		return;
			}
		}

        new SwingWorker<Object, Object>(){
        @Override
        protected Object doInBackground() throws Exception {
        	ShareFileListPanel panel = ViewManager.getComponent(ShareFileListPanel.class);
        	for (File file : findedFiles) {
    			if (file.isFile()) {
    				String path = file.getAbsolutePath();
    				String uniqueFileID = DataUtil.getUniqueFileID();
    	            ShareFileListItem shareFileListItem = new ShareFileListItem();
    	            shareFileListItem.getProgressBar().setVisible(true);
    	            int lastIndexOf = path.lastIndexOf("\\");
    	            shareFileListItem.setFileName(path.substring(lastIndexOf+1));
    	            shareFileListItem.setIconFromFileName(path);
    	            shareFileListItem.setUniqueFileID(uniqueFileID);
    	            
    	            panel.addUpLoadShareFileListItem(uniqueFileID, shareFileListItem);

    	            
    	            services.getShareFileService().setTransferState(uniqueFileID, true);
                    services.getShareFileService().upLoadFile(uniqueFileID,DataUtil.getLoginInfo().getConfno(), path,false);
                    services.getShareFileService().removeTransferState(uniqueFileID);
    			}
    		}
            return new Object();
        }
        }.execute();
    	
    	
    }
    
    public void uploadShareFile() throws Exception{
    	JFileChooser chooser = new JFileChooser();//new LvmcJFileChooser();
        chooser.setSelectedFile(new File(com.lorent.lvmc.util.Constants.AppPath));
        chooser.setApproveButtonText(StringUtil.getUIString("LvmcJFileChooser.uploadButton.text"));
        int showOpenDialog = chooser.showOpenDialog(DataUtil.getTopWindow());
        if (showOpenDialog == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            long fileLength = selectedFile.length();
            if (fileLength == 0) {
            	showErrorDialog(StringUtil.getErrorString("error.title"),StringUtil.getErrorString("uploadfile.length.iszero"));
            	return;
			}
            String maxsize = Launcher.getLCMUtil().getSystemProperties("lvmc").get("sharefile.upload.maxfilesize");
            long maxfilesize = Long.parseLong(maxsize);
            if(fileLength > maxfilesize){//ConfigUtil.getLongProperty("uploadFileSize")
                showErrorDialog(StringUtil.getErrorString("error.title"),StringUtil.getFormatString(StringUtil.getErrorString("uploadfile.length.toomuch"), MathUtil.convertByte(ConfigUtil.getLongProperty("uploadFileSize"), 0)));
                return;
            }
            
            final String path = selectedFile.getAbsolutePath();
            ShareFileListPanel panel = ViewManager.getComponent(ShareFileListPanel.class);
            //检查文件是否已经存在
            DefaultListModel model = (DefaultListModel) panel.getFileList().getModel();
            Object[] array = model.toArray();
            for (Object obj : array) {
            	ShareFileListItem item = (ShareFileListItem)obj;
            	if (item.getFileName().equals(selectedFile.getName())) {
					int showConfirmDialog = JOptionPane.showConfirmDialog(null, StringUtil.getUIString("ShareFileListController.askUploadCover"), "", JOptionPane.YES_NO_OPTION);
					if (showConfirmDialog != JOptionPane.YES_OPTION) {
						return;
					}
					break;
				}
			}
            
            final String uniqueFileID = DataUtil.getUniqueFileID();
            ShareFileListItem shareFileListItem = new ShareFileListItem();
            shareFileListItem.getProgressBar().setVisible(true);
            int lastIndexOf = path.lastIndexOf("\\");
            shareFileListItem.setFileName(path.substring(lastIndexOf+1));
            shareFileListItem.setIconFromFileName(path);
            shareFileListItem.setUniqueFileID(uniqueFileID);
            panel.addUpLoadShareFileListItem(uniqueFileID, shareFileListItem);
//            panel.getTabbedPane().setSelectedIndex(0);
//            int size = panel.getTransferFileList().getModel().getSize();
//            Rectangle cellBounds = panel.getTransferFileList().getCellBounds(size-1, size-1);
//            panel.getTransferFileListScrollPane().getViewport().scrollRectToVisible(cellBounds);
            
            new SwingWorker<Object, Object>(){
                @Override
                protected Object doInBackground() throws Exception {
                	services.getShareFileService().setTransferState(uniqueFileID, true);
                    services.getShareFileService().upLoadFile(uniqueFileID,DataUtil.getLoginInfo().getConfno(), path,false);
                    services.getShareFileService().removeTransferState(uniqueFileID);
                    return new Object();
                }
            }.execute();
        }
    }
    
    
    
	public void cancelDownLoadShareFileProgress(String uniqueFileID) throws Exception{
		log.info("cancelDownLoadShareFileProgress");
		ShareFileListPanel panel = ViewManager.getComponent(ShareFileListPanel.class);
        ShareFileListItem downLoadShareFileListItem = panel.getDownLoadShareFileListItem(uniqueFileID);
        if (downLoadShareFileListItem != null) {
        	downLoadShareFileListItem.getCancelTransferXHyperlink().setVisible(false);
        }
	}
	
	public void cancelUpLoadShareFileProgress(String uniqueFileID) throws Exception{
		log.info("upLoadShareFileProgress");
		ShareFileListPanel panel = ViewManager.getComponent(ShareFileListPanel.class);
        ShareFileListItem upLoadShareFileListItem = panel.getUpLoadShareFileListItem(uniqueFileID);
        if (upLoadShareFileListItem != null) {
        	upLoadShareFileListItem.getCancelTransferXHyperlink().setVisible(false);
        }
	}
    
    public void downLoadShareFileProgress(String uniqueFileID,long position,long length,boolean state) throws Exception{
        ShareFileListPanel panel = ViewManager.getComponent(ShareFileListPanel.class);
        ShareFileListItem downLoadShareFileListItem = panel.getDownLoadShareFileListItem(uniqueFileID);
        if (downLoadShareFileListItem != null) {
            double d = 1;
            if (position <= length) {
                d = 100 * position / length;
                
            }
            final int  n = (int) d;
//            log.info("process:"+n);
            downLoadShareFileListItem.getProgressBar().setValue(n);
            if (state) {
                downLoadShareFileListItem.getProgressBar().setString("下载: "+n + "%");
                if (n == 100) {
					downLoadShareFileListItem.getCancelTransferXHyperlink().setVisible(false);
					downLoadShareFileListItem.getOpenXHyperlink().setVisible(true);
					downLoadShareFileListItem.getOpenPathXHyperlink().setVisible(true);
				}
                else{
                	downLoadShareFileListItem.getCancelTransferXHyperlink().setVisible(true);
                }
            }
            else{
                downLoadShareFileListItem.getProgressBar().setString("下载失败："+n + "%");
                downLoadShareFileListItem.getProgressBar().setForeground(Color.red);
                downLoadShareFileListItem.getCancelTransferXHyperlink().setVisible(false);
            }
//            panel.getTransferFileList().repaint();
        }
    }
    
    
    public void deleteShareFile(final String serverFileName) throws Exception{
    	if (showConfirmDialog("lvmc提示",StringUtil.getFormatString(StringUtil.getUIString("ShareFileListController.askDelete"), serverFileName)) != JOptionPane.YES_OPTION) {
            return;
        }
    	new SwingWorker<Object, Object>(){
            @Override
            protected Object doInBackground() throws Exception {
                services.getShareFileService().deleteFile(DataUtil.getLoginInfo().getConfno(), serverFileName);
                return new Object();
            }
        }.execute();
    }
    
    public void cancelTransferShareFile(String uniqueFileID) throws Exception{
    	log.info("cancelTransferShareFile: "+uniqueFileID);
    	services.getShareFileService().removeTransferState(uniqueFileID);
    }
    
    public void downLoadShareFile(final String serverFileName) throws Exception{
        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(new File(com.lorent.lvmc.util.Constants.AppPath+"/"+serverFileName));
        int showOpenDialog = chooser.showSaveDialog(DataUtil.getTopWindow());
        if (showOpenDialog == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            //检查文件是否已经存在
            if (selectedFile.exists()) {
                if (JOptionPane.showConfirmDialog(null, StringUtil.getUIString("ShareFileListController.askDownLoad")) != JOptionPane.YES_OPTION) {
                    return;
                }
            }
            final String path = selectedFile.getAbsolutePath();
            ShareFileListPanel panel = ViewManager.getComponent(ShareFileListPanel.class);
            final String uniqueFileID = DataUtil.getUniqueFileID();
            ShareFileListItem shareFileListItem = new ShareFileListItem();
            shareFileListItem.getProgressBar().setVisible(true);
            shareFileListItem.setFileName(selectedFile.getName());
            shareFileListItem.setAbsolutePath(path);
            shareFileListItem.setIconFromFileName(path);
            shareFileListItem.setUniqueFileID(uniqueFileID);
            panel.addDownLoadShareFileListItem(uniqueFileID, shareFileListItem);
//            panel.getTabbedPane().setSelectedIndex(1);
//            int size = panel.getTransferFileList().getModel().getSize();
//            Rectangle cellBounds = panel.getTransferFileList().getCellBounds(size-1, size-1);
//            panel.getTransferFileListScrollPane().getViewport().scrollRectToVisible(cellBounds);
            new SwingWorker<Object, Object>(){
                @Override
                protected Object doInBackground() throws Exception {
                	services.getShareFileService().setTransferState(uniqueFileID, true);
                    services.getShareFileService().downLoadFile(uniqueFileID, DataUtil.getLoginInfo().getConfno(), serverFileName, path);
                    services.getShareFileService().removeTransferState(uniqueFileID);
                    return new Object();
                }
            }.execute();
        }
    }
    public void loadShareFileToBoard(final String serverFileName) throws Exception{
        Boolean flag = isMaxWhiteBoard();
        if(flag){
            JOptionPane.showMessageDialog(null, StringUtil.getErrorString("ismaxwhiteboard"));
            return;
        }
        if(this.isLoadBoardFileTooMuch(serverFileName)){
            JOptionPane.showMessageDialog(null, StringUtil.getFormatString(StringUtil.getErrorString("whiteBoardFile.lenth.toomuch"), MathUtil.convertByte(ConfigUtil.getLongProperty("whiteFileSize"), 0)));
            return;
        }
    	new SwingWorker<Object, Object>(){
    		@Override
    		protected Object doInBackground() throws Exception {
    			services.getShareFileService().loadFileToBoard(DataUtil.getLoginInfo().getConfno(), serverFileName);
    			return new Object();
    		}
    	}.execute();
    }
    
    public void reflashButtonClick() throws Exception{
        services.getShareFileService().getServerFileList(DataUtil.getLoginInfo().getConfno());
        /*
        ShareFileListPanel panel = ViewManager.getComponent(ShareFileListPanel.class);
        DefaultListModel model = (DefaultListModel) panel.getFileList().getModel();
        model.removeAllElements();
        ArrayList<FileDto> list = (ArrayList<FileDto>) getShareFileList(DataUtil.getLoginInfo().getConfno());
        if (list != null) {
            
            for (FileDto fileDto : list) {
                ShareFileListItem shareFileListItem = new ShareFileListItem();
                shareFileListItem.setFileName(fileDto.getFileName());
                shareFileListItem.setIconFromFileName(fileDto.getFileName());
                model.addElement(shareFileListItem);
            }
        }
        */
    }
    
//    public ArrayList getShareFileList(String meetingID) throws Exception{
//        String[] serverFileList = services.getShareFileService().getServerFileList(meetingID);
//        log.info("serverFileList"+serverFileList.length+","+serverFileList);
//        ArrayList list = new ArrayList();
//        for (int i = 0; i < serverFileList.length; i++) {
//            FileDto fileDto = new FileDto();
//            fileDto.setFileName(serverFileList[i]);
//            list.add(fileDto);
//        }
//        return list;
//    }
    
    public void serverReturnFileNames(final String[] filenames) throws Exception{
    	log.info("serverReturnFileNames");
    	
    	SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {

				try {
					ShareFileListPanel panel = ViewManager.getComponent(ShareFileListPanel.class);
					DefaultListModel model = (DefaultListModel) panel.getFileList().getModel();
					model.removeAllElements();
					for (String filename : filenames) {
						log.info("serverReturnFileNames: " + filename);
						ShareFileListItem shareFileListItem = new ShareFileListItem();
						shareFileListItem.setFileName(filename);
						shareFileListItem.setIconFromFileName(filename);
						model.addElement(shareFileListItem);
					}
				} catch (Exception e) {
					log.error("serverReturnFileNames", e);
					e.printStackTrace();
				}
				
  
            }
        });
    	
        
    }
    
    public void serverCallClientGetFileList() throws Exception{
        services.getShareFileService().getServerFileList(DataUtil.getLoginInfo().getConfno());
//        ShareFileListPanel panel = ViewManager.getComponent(ShareFileListPanel.class);
//        DefaultListModel model = (DefaultListModel) panel.getFileList().getModel();
//        model.removeAllElements();
//        ArrayList<FileDto> list = (ArrayList<FileDto>) getShareFileList(DataUtil.getLoginInfo().getConfno());
//        if (list != null) {
//            
//            for (FileDto fileDto : list) {
//                ShareFileListItem shareFileListItem = new ShareFileListItem();
//                shareFileListItem.setFileName(fileDto.getFileName());
//                shareFileListItem.setIconFromFileName(fileDto.getFileName());
//                model.addElement(shareFileListItem);
//            }
//        }
    }
    
    public boolean  checkFileSupportConvert(String filename) throws Exception{
        boolean bFlag = false;
        String extName = filename.substring(filename.lastIndexOf(".")+1);
        for (String fileType : com.lorent.lvmc.util.Constants.SUPPORT_CONVERT_FILETYPE) {
            if (fileType.equals(extName.toLowerCase())) {
                bFlag = true;
                break;
            }
        }
        return bFlag;
    }
    
    private boolean checkFileSupportPlay(String filename) throws Exception{
    	boolean bFlag = false;
        String extName = filename.substring(filename.lastIndexOf(".")+1);
        for (String fileType : com.lorent.lvmc.util.Constants.SUPPORT_PLAY_FILETYPE) {
            if (fileType.equals(extName.toLowerCase())) {
                bFlag = true;
                break;
            }
        }
        return bFlag;
    }
    
    public boolean isMaxWhiteBoard()throws Exception{
        int whiteboardnum=Integer.parseInt(LCMUtil.getWhiteBoardNumber(((LoginInfo)DataUtil.getValue(DataUtil.Key.LoginInfo)).getConfno()));
        WhiteBoardPanel whiteBoardPanel = ViewManager.getComponent(WhiteBoardPanel.class);
        if(whiteBoardPanel.getJTabbedPane1().getTabCount() >= whiteboardnum){//电子白板数量在lcm const.properties配置文件中配置
            return true;
        }
        return false;
    }
    
    
    public void showPopupMemu(String filename,java.awt.event.MouseEvent evt) throws Exception{
        ShareFileListPanel panel = ViewManager.getComponent(ShareFileListPanel.class);
        boolean flag = PermissionUtil.hasPermission(PermissionUtil.SHARE_DOCUMENT);
        if(flag){
            panel.getLoadToBoardMenuItem().setEnabled(checkFileSupportConvert(filename));
        }else{
            panel.getLoadToBoardMenuItem().setVisible(false);
        }
        if(PermissionUtil.hasPermission(PermissionUtil.DELETE_DOCUMENT)){
            panel.getDeleteFileMenuItem().setVisible(true);
        }else{
            panel.getDeleteFileMenuItem().setVisible(false);
        }
        if (PermissionUtil.hasPermission(PermissionUtil.PLAY_DOCUMENT)) {
        	panel.getVlcPlayMenuItem().setVisible(true);
        	panel.getVlcPlayMenuItem().setEnabled(checkFileSupportPlay(filename));
		}
        else{
        	panel.getVlcPlayMenuItem().setVisible(false);
        }
        panel.getFileListPopupMenu().show(evt.getComponent(), evt.getPoint().x, evt.getPoint().y);
    }
    
    public boolean isLoadBoardFileTooMuch(String fileName) throws Exception{
        long fileSize = services.getShareFileService().getServerFileSize( DataUtil.getLoginInfo().getConfno(), fileName);
        if(fileSize>ConfigUtil.getLongProperty("whiteFileSize")){
            return true;
        }else{
            return false;
        }
    }
    
    public void loadFileToBoardFileProcess(String filename) throws Exception{
//        boolean flag = PermissionUtil.hasPermission(PermissionUtil.SHARE_DOCUMENT);
        if(PermissionUtil.hasPermission(PermissionUtil.SHARE_DOCUMENT)){//判断权限
            if(checkFileSupportConvert(filename)){//判断文件格式
                loadShareFileToBoard(filename);
            }else{
                showErrorDialog(StringUtil.getErrorString("error.title"), StringUtil.getErrorString("convertfile.notsupport.whiteBoard"));
            }
        }else{
            showErrorDialog(StringUtil.getErrorString("error.title"), StringUtil.getErrorString("permit.nopermission"));
        }
    }
}
