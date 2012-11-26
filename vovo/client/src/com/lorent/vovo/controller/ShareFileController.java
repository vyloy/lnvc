package com.lorent.vovo.controller;

import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPCommunicationListener;
import it.sauronsoftware.ftp4j.FTPDataTransferListener;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPFile;
import it.sauronsoftware.ftp4j.FTPListParseException;
import it.sauronsoftware.ftp4j.FTPListParser;

import java.awt.Color;
import java.io.File;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

import com.lorent.common.controller.BaseController;
import com.lorent.common.util.FileUtil;
import com.lorent.vovo.Vovo;
import com.lorent.vovo.ui.MessageTabPanel;
import com.lorent.vovo.ui.ShareFileListItem;
import com.lorent.vovo.ui.ShareFileListPanel;
import com.lorent.vovo.util.Constants;
import com.lorent.vovo.util.VovoStringUtil;

public class ShareFileController extends BaseController {
	
	private static Logger log = Logger.getLogger(ShareFileController.class);
	
	private String ftpUser = "";
	private String ftpPsw = "";
//	private static FTPClient ftpClient = new FTPClient();
	
	private static Map<String, FTPClient> ftpClientsMap = new HashMap<String, FTPClient>();
	
	private String ftpAddr = "";
	private int ftpPort;
//	private String ftpGroupChatPath = "GroupChatFilePath";
//	private String ftpCommomFilePath = "CommonFilePath";
	
	public String getFtpAddr() {
		return ftpAddr;
	}

	public int getFtpPort() {
		return ftpPort;
	}

	public void setFtpConfig(String ftpUser,String ftpPsw,String ftpAddr,int ftpPort) throws Exception{
		this.ftpUser = ftpUser;
		this.ftpPsw = ftpPsw;
		this.ftpAddr = ftpAddr;
		this.ftpPort = ftpPort;
	}
	
	private FTPClient getFtpClient(String sessionID){
		FTPClient tempFtpClient = ftpClientsMap.get(sessionID);
		if (tempFtpClient == null) {
			tempFtpClient = new FTPClient();
			ftpClientsMap.put(sessionID, tempFtpClient);
		}
		return tempFtpClient;
	}
	
	private void checkAndCreateDefaultFtpPath(String parentPath,String sessionID) throws Exception{
		log.debug("checkAndCreateDefaultFtpPath");
		FTPClient ftpClient = getFtpClient(sessionID);
//		ftpClient.setAutoNoopTimeout(10000);
		if (!ftpClient.isConnected()) {
//			ftpClient.setAutoNoopTimeout(10000);
			ftpClient.connect(ftpAddr, ftpPort);
			ftpClient.login(ftpUser, ftpPsw);
		}
		
		if (ftpClient.isConnected()) {
			ftpClient.changeDirectory("/");
//			ftpClient.changeDirectory("/GroupChatFilePath/"+sessionID);
			
			//GroupChatFilePath
			boolean pathExist = false;
			FTPFile[] list = ftpClient.list();
			for (FTPFile ftpFile : list) {
				if (ftpFile.getType() == FTPFile.TYPE_DIRECTORY && ftpFile.getName().equals(parentPath)) {
					pathExist = true;
					break;
				}
			}
			if (!pathExist) {
				ftpClient.createDirectory(parentPath);
			}
			ftpClient.changeDirectory(parentPath);
			//SessionID
			pathExist=false;
			FTPFile[] list2 = ftpClient.list();
			for (FTPFile ftpFile : list2) {
				if (ftpFile.getType() == FTPFile.TYPE_DIRECTORY && ftpFile.getName().equals(sessionID)) {
					pathExist = true;
					break;
				}
			}
			if (!pathExist) {
				ftpClient.createDirectory(sessionID);
			}
			
			
		}
//		ftpClient.disconnect(false);
		
		
	}
	
	public void getPathFileList(ShareFileListPanel panel,String parentPath,String absolutepath,String sessionID) throws Exception{
		System.out.println(absolutepath+","+sessionID);
		log.info("getPathFileList parentPath:"+parentPath+" , absolutepath:"+absolutepath+" , sessionID"+sessionID);
		if (sessionID == null || absolutepath == null) {
			log.error("getPathFileList sessionID can not null , "+parentPath+" , "+absolutepath+" , "+sessionID);
//			throw new Exception("sessionID can not null ,"+sessionID+","+absolutepath);
			return;
		}
		String filepath = "/"+parentPath+"/"+absolutepath;
		
		FTPClient ftpClient = getFtpClient(sessionID);
		if (!ftpClient.isConnected()) {
			ftpClient.connect(ftpAddr, ftpPort);
			ftpClient.login(ftpUser, ftpPsw);
		}
		
		if (ftpClient.isConnected()) {
			ftpClient.changeDirectory(filepath);
//			System.out.println("changeDirectory: "+filepath );
			FTPFile[] list = ftpClient.list();
			MessageTabPanel view = Vovo.getMyContext().getViewManager().getView(Constants.ViewKey.MESSAGETABPANEL.toString());
//			com.lorent.vovo.ui.ShareFileListPanel panel = (ShareFileListPanel) view.getTab(sessionID);

			panel.setCurrentFtpPath(absolutepath);
			
			panel.getFilePathTextField().setText(absolutepath.replaceFirst(sessionID, "/").replaceFirst("//", "/"));
			
			DefaultListModel model = (DefaultListModel) panel.getFileList().getModel();
			model.removeAllElements();
			
			
			
			if (!absolutepath.equals(sessionID)) {
				int lastIndexOf = absolutepath.lastIndexOf("/");
				String substring = absolutepath.substring(0,lastIndexOf);
				ShareFileListItem upShareFileListItem = new ShareFileListItem();
				upShareFileListItem.setFileName("..");
				upShareFileListItem.setAbsolutePath(substring);
				upShareFileListItem.setFolderIcon(false);
				upShareFileListItem.setUpperFolderType(true);
				upShareFileListItem.setSessionID(sessionID);
				model.addElement(upShareFileListItem);	
			}
			for (FTPFile ftpFile : list) {
				String filename = ftpFile.getName();
				
				ShareFileListItem shareFileListItem = new ShareFileListItem();
				shareFileListItem.setFileName(filename);
				
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String format = simpleDateFormat.format(ftpFile.getModifiedDate());
				shareFileListItem.getModifyDateLabel().setText(format);
				shareFileListItem.setSessionID(sessionID);
				if (ftpFile.getType() == FTPFile.TYPE_DIRECTORY) {
					shareFileListItem.setAbsolutePath(absolutepath+"/"+filename);
					shareFileListItem.setFolderIcon(false);
				}
				else{
					shareFileListItem.setAbsolutePath(absolutepath+"/"+filename);
					shareFileListItem.setIconFromFileName(filename);
					shareFileListItem.setFileSize(ftpFile.getSize());
					shareFileListItem.getSizeLabel().setText(FileUtil.fileSizeToUserString(ftpFile.getSize()));
				}
				
				model.addElement(shareFileListItem);
				
			}
		}
		ftpClient.disconnect(true);
	}
	
	public void getDefaultFileList(ShareFileListPanel panel) throws Exception{
		log.info("getDefaultFileList");
		String sessionID = panel.getSessionID();
		if (sessionID == null) {
//			throw new Exception("sessionID can not null");
			log.error("getDefaultFileList sessionID can not null"+panel);
			return;
		}
		checkAndCreateDefaultFtpPath(panel.getParentFtpPath(),sessionID);
		if (panel.getCurrentFtpPath() == null) {
			panel.setCurrentFtpPath(sessionID);
		}
		getPathFileList(panel,panel.getParentFtpPath(),panel.getCurrentFtpPath(),sessionID);
	}
	
	public void doubleClickFileList(ShareFileListPanel panel ,ShareFileListItem item) throws Exception{
		log.info("doubleClickFileList "+item.getAbsolutePath()+" , "+item.getSessionID());
		if (item == null) {
			return;
		}
		if (item.isFolder()) {
//			MessageTabPanel view = Vovo.getMyContext().getViewManager().getView(Constants.ViewKey.MESSAGETABPANEL.toString());
//			ShareFileListPanel panel = (ShareFileListPanel) view.getTab(item.getSessionID());
//			checkAndCreateDefaultFtpPath(item.getSessionID());
			getPathFileList(panel,panel.getParentFtpPath(),item.getAbsolutePath(), item.getSessionID());
//			ftpClient.changeDirectory(item.getAbsolutePath());
		}
		
	}
	
	
	public void createFilePath(ShareFileListPanel panel) throws Exception{
		log.info("createFilePath "+panel);
		String showInputDialog = JOptionPane.showInputDialog(Vovo.getMyContext().getViewManager().getUIString("ShareFileListController.createPath")+"：");
		if (showInputDialog == null || showInputDialog.equals("")) {
			return;
		}else if(showInputDialog!=null && VovoStringUtil.dirContainspecialChar(showInputDialog)){
			this.showErrorDialog(null, VovoStringUtil.getFormatString(VovoStringUtil.getErrorString("ShareFileController.createFilePath.character.error"), "\"","*"));
			return;
		}
		showInputDialog = VovoStringUtil.replaceStarCharacter(showInputDialog, "");
		try {
			FTPClient ftpClient = getFtpClient(panel.getSessionID());
			if (!ftpClient.isConnected()) {
				ftpClient.connect(ftpAddr, ftpPort);
				ftpClient.login(ftpUser, ftpPsw);
			}
			
			if (ftpClient.isConnected()) {
				String filepath = "/"+panel.getParentFtpPath()+"/"+panel.getCurrentFtpPath();
				ftpClient.changeDirectory(filepath);
				ftpClient.createDirectory(showInputDialog);
			}
		} catch (Exception e) {
			if (e instanceof FTPException) {
				FTPException exception =  (FTPException)e;
				if (exception.getCode() == 550) {
					log.error("创建目录的名称重复");
					showErrorDialog(VovoStringUtil.getErrorString("error.normal.tip"), VovoStringUtil.getErrorString("error.pathisexist"));
				}
			}
			else{
				throw e;
			}
		}
		getPathFileList(panel,panel.getParentFtpPath(),panel.getCurrentFtpPath(),panel.getSessionID());
	}
	
	class FtpFileInfo{
		private FTPFile file;
		public FTPFile getFile() {
			return file;
		}
		public void setFile(FTPFile file) {
			this.file = file;
		}
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
		private String path;
	}
	
	private void deleteFtpPath(String parentPath,String absolutepath,String sessionID,ShareFileListPanel panel) throws Exception{
		FTPClient ftpClient = getFtpClient(sessionID);
		if (!ftpClient.isConnected()) {
			ftpClient.connect(ftpAddr, ftpPort);
			ftpClient.login(ftpUser, ftpPsw);
		}
		String filepath = "/"+parentPath+"/"+absolutepath;
		if (ftpClient.isConnected()) {
			ftpClient.changeDirectory(filepath);
//			System.out.println("changeDirectory: "+filepath );
			FTPFile[] list = ftpClient.list();
			
			LinkedBlockingQueue<FTPFile> linkedBlockingQueue = new LinkedBlockingQueue<FTPFile>();
			
			LinkedList<FtpFileInfo> synchronizedList = new LinkedList<FtpFileInfo>();//(LinkedList<FTPFile>) Collections.synchronizedList(new LinkedList<FTPFile>());
			LinkedList<String> pathslinkedList = new LinkedList<String>();
			LinkedList<String> fileslinkedList = new LinkedList<String>();
			
			int count = 0;
			for (FTPFile ftpFile : list) {
				FtpFileInfo ftpFileInfo = new FtpFileInfo();
				ftpFileInfo.setFile(ftpFile);
				ftpFileInfo.setPath(ftpClient.currentDirectory()+"/"+ftpFile.getName());
				synchronizedList.addFirst(ftpFileInfo);
//				System.out.println("add1: "+ftpFileInfo.getPath());
				if (ftpFile.getType() == FTPFile.TYPE_DIRECTORY) {
					pathslinkedList.add(ftpFileInfo.getPath());
				}
				else{
					fileslinkedList.add(ftpFileInfo.getPath());
				}
				count++;
			}
			while (!synchronizedList.isEmpty()) {
				FtpFileInfo first = synchronizedList.poll();
				if (first.getFile().getType() == FTPFile.TYPE_DIRECTORY) {
//					System.out.println("currentPath: "+first.getPath());
					ftpClient.changeDirectory(first.getPath());
//					String currentDirectory = ftpClient.currentDirectory();
					FTPFile[] list2 = ftpClient.list();
					for (FTPFile ftpFile : list2) {
						FtpFileInfo ftpFileInfo = new FtpFileInfo();
						ftpFileInfo.setFile(ftpFile);
						ftpFileInfo.setPath(ftpClient.currentDirectory()+"/"+ftpFile.getName());
						synchronizedList.addFirst(ftpFileInfo);
//						System.out.println("add2: "+ftpFileInfo.getPath());
						if (ftpFile.getType() == FTPFile.TYPE_DIRECTORY) {
							pathslinkedList.add(ftpFileInfo.getPath());
						}
						else{
							fileslinkedList.add(ftpFileInfo.getPath());
						}
						count++;
					}
				}
				else{
//					System.out.println("delete file: "+first.getPath());
				}
			}
			
			ftpClient.changeDirectory(filepath);
//			MessageTabPanel view = Vovo.getMyContext().getViewManager().getView(Constants.ViewKey.MESSAGETABPANEL.toString());
//			com.lorent.vovo.ui.ShareFileListPanel panel = (ShareFileListPanel) view.getTab(sessionID);
			ShareFileListItem shareFileListItem = new ShareFileListItem();
            shareFileListItem.getProgressBar().setVisible(true);
//            int lastIndexOf = filepath.lastIndexOf("/");
            shareFileListItem.setFileName(absolutepath.replaceFirst(sessionID,"/").replaceFirst("//", "/"));
			shareFileListItem.setSessionID(sessionID);
			shareFileListItem.setFolderIcon(false);
			shareFileListItem.getItemInfoPanel().setVisible(false);
			panel.addUpLoadShareFileListItem(shareFileListItem);
			
			String uiString = Vovo.getMyContext().getViewManager().getUIString("ShareFileListController.deleteProcess");
			int i = 1;
			for (String string : fileslinkedList) {
				ftpClient.deleteFile(string);
				double d = 1;
	            if (i <= count) {
	                d = 100 * i / count;
	                
	            }
	            final int  n = (int) d;
	            shareFileListItem.getProgressBar().setValue(n);
				shareFileListItem.getProgressBar().setString(MessageFormat.format(uiString, n));
				i++;
			}
			String pollFirst;
			do {
				pollFirst = pathslinkedList.pollLast();
				if (pollFirst != null ) {
					ftpClient.deleteDirectory(pollFirst);
					double d = 1;
		            if (i <= count) {
		                d = 100 * i / count;
		                
		            }
		            final int  n = (int) d;
					shareFileListItem.getProgressBar().setValue(n);
					shareFileListItem.getProgressBar().setString(MessageFormat.format(uiString, n));
					i++;
				}
				
			} while (pollFirst != null);
			ftpClient.changeDirectory(filepath);
			ftpClient.changeDirectoryUp();
			ftpClient.deleteDirectory(filepath);
			if (count == 0) {
				shareFileListItem.getProgressBar().setValue(100);
				shareFileListItem.getProgressBar().setString(MessageFormat.format(uiString, 100));
			}
		}
	}
	
	public void deleteFileAndPath(ShareFileListPanel panel,ShareFileListItem item) throws Exception{
		log.info("deleteFileAndPath "+panel+" , "+item);
		if (item == null) {
			return;
		}
		FTPClient ftpClient = getFtpClient(panel.getSessionID());
		if (!ftpClient.isConnected()) {
			ftpClient.connect(ftpAddr, ftpPort);
			ftpClient.login(ftpUser, ftpPsw);
		}
		String filepath = "/"+panel.getParentFtpPath()+"/"+item.getAbsolutePath();
		int lastIndexOf = item.getAbsolutePath().lastIndexOf("/");
		String substring = item.getAbsolutePath().substring(lastIndexOf+1);
		if (item.isFolder()) {
			if (!item.isUpperFolderType()) {
				String uiString = Vovo.getMyContext().getViewManager().getUIString("ShareFileListController.deletePathConfirm");
				int showConfirmDialog = JOptionPane.showConfirmDialog(null, uiString+substring,"VoVo",JOptionPane.YES_NO_OPTION);
				if (showConfirmDialog == JOptionPane.YES_OPTION) {
					//删除文件夹
					deleteFtpPath(panel.getParentFtpPath(),item.getAbsolutePath(),panel.getSessionID(),panel);
					getPathFileList(panel,panel.getParentFtpPath(),panel.getCurrentFtpPath(),panel.getSessionID());
				}
			}
		}
		else{
			String uiString = Vovo.getMyContext().getViewManager().getUIString("ShareFileListController.deleteFileConfirm");
			int showConfirmDialog = JOptionPane.showConfirmDialog(null, uiString+substring,"VoVo",JOptionPane.YES_NO_OPTION);
			if (showConfirmDialog == JOptionPane.YES_OPTION) {
				ftpClient.deleteFile(filepath);
				getPathFileList(panel,panel.getParentFtpPath(),panel.getCurrentFtpPath(),panel.getSessionID());
			}
		}
	}
	
	public void downLoadFile(ShareFileListPanel panel,ShareFileListItem item) throws Exception{
		
		if (item.isFolder()) {
			return;
		}
		JFileChooser chooser = new JFileChooser();
		chooser.setSelectedFile(new File(item.getFileName()));
		int showOpenDialog = chooser.showSaveDialog(null);
		if (showOpenDialog == JFileChooser.APPROVE_OPTION) {
			final File selectedFile = chooser.getSelectedFile();
			
			//检查文件是否已经存在
			
            if (selectedFile.exists()) {
                if (JOptionPane.showConfirmDialog(null, Vovo.getMyContext().getViewManager().getUIString("ShareFileListController.coverFileConfirm"),"VoVo",JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
                    return;
                }
            }
			
            long fileLength = item.getFileSize();
            final String sessionID = panel.getSessionID();
            FTPClient ftpClient = getFtpClient(sessionID);
            if (!ftpClient.isConnected()) {
    			ftpClient.connect(ftpAddr, ftpPort);
    			ftpClient.login(ftpUser, ftpPsw);
    		}
            String filepath = "/"+panel.getParentFtpPath()+"/"+panel.getCurrentFtpPath();
//            ftpClient.changeDirectory(filepath);
            final String absolutePath = "/"+panel.getParentFtpPath()+"/"+item.getAbsolutePath();
            System.out.println(absolutePath);
            
			ShareFileListItem shareFileListItem = new ShareFileListItem();
            shareFileListItem.getProgressBar().setVisible(true);
//            int lastIndexOf = filepath.lastIndexOf("/");
            shareFileListItem.setFileName(selectedFile.getName());
            shareFileListItem.setAbsolutePath(selectedFile.getAbsolutePath());
			shareFileListItem.setSessionID(panel.getSessionID());
			shareFileListItem.setIconFromFileName(selectedFile.getName());
			shareFileListItem.getItemInfoPanel().setVisible(false);
			panel.addUpLoadShareFileListItem(shareFileListItem);
           
			final String uiString = Vovo.getMyContext().getViewManager().getUIString("ShareFileListController.downloadProcess");
			
            final MyTransferListener listener= new MyTransferListener(){
				
				@Override
				public void transferred(int length) {
					super.transferred(length);
					
					double d = 1;
					d = 100 * transferedLength / fileLength;
			        int  n = (int) d;
					
					item.getProgressBar().setValue(n);
					item.getProgressBar().setString(MessageFormat.format(uiString, n));
//					panel.getTransferFileList().repaint();
				}

				@Override
				public void aborted() {
					super.aborted();
					panel.getFileList().setEnabled(true);
					panel.getFileList().setOpaque(false);
					panel.setOperationButtonEnable(true);
					panel.getFileList().repaint();
					item.getCancelDownLoadHyperlink().setVisible(false);
				}

				@Override
				public void completed() {
					super.completed();
					panel.getFileList().setEnabled(true);
					panel.getFileList().setOpaque(false);
					panel.setOperationButtonEnable(true);
					item.getProgressBar().setValue(100);
					item.getProgressBar().setString(MessageFormat.format(uiString, 100));
					panel.getFileList().repaint();
					item.getCancelDownLoadHyperlink().setVisible(false);
					item.getOpenHyperlink().setVisible(true);
					item.getOpenPathHyperlink().setVisible(true);
				}

				@Override
				public void failed() {
					super.failed();
					panel.getFileList().setEnabled(true);
					panel.getFileList().setOpaque(false);
					panel.setOperationButtonEnable(true);
					panel.getFileList().repaint();
					item.getCancelDownLoadHyperlink().setVisible(false);
				}

				@Override
				public void started() {
					super.started();
					panel.getFileList().setEnabled(false);
					panel.getFileList().setOpaque(true);
					panel.getFileList().setBackground(new Color(212, 208, 200));//
					panel.setOperationButtonEnable(false);
					item.getCancelDownLoadHyperlink().setVisible(true);
				}
				
				
				
			};
			listener.item = shareFileListItem;
			listener.panel = panel;
			listener.fileLength = fileLength;
            
            new Thread(){

				@Override
				public void run() {
					try {
						FTPClient ftpClient = getFtpClient(sessionID);
						ftpClient.download(absolutePath, selectedFile, listener);
						
					} catch (Exception e) {
						log.error("downLoadFile Thread",e);
					} 
				}
            }.start();
		}
	}
	
	public void cancelTransferData(ShareFileListItem item) throws Exception{
		FTPClient ftpClient = getFtpClient(item.getSessionID());
		ftpClient.abortCurrentDataTransfer(true);
	}
	
	public void deleteFileAtFtpServer(String targetDirectory,String fileName) throws Exception{
		FTPClient ftpClient = getFtpClient(Constants.TEMPFTPCLIENTSESSIONID);
		if (!ftpClient.isConnected()) {
			ftpClient.connect(ftpAddr, ftpPort);
			ftpClient.login(ftpUser, ftpPsw);
		}
		if (ftpClient.isConnected()) {
			ftpClient.changeDirectory(targetDirectory);
			if (fileName != null && !fileName.equals("")) {
				ftpClient.deleteFile(fileName);
			}
		}
	}
	
	public void upLoadFileToFtpServer(File file,FTPDataTransferListener listener,String targetDirectory,String newFileName) throws Exception{
		FTPClient ftpClient = getFtpClient(Constants.TEMPFTPCLIENTSESSIONID);
		if (!ftpClient.isConnected()) {
			ftpClient.setAutoNoopTimeout(3000);
			ftpClient.connect(ftpAddr, ftpPort);
			ftpClient.login(ftpUser, ftpPsw);
		}
		if (ftpClient.isConnected()) {
			ftpClient.setType(FTPClient.TYPE_BINARY);
			ftpClient.changeDirectory(targetDirectory);
			ftpClient.upload(file,listener);
			if (newFileName != null && !newFileName.equals("")) {
				ftpClient.rename(file.getName(), newFileName);
			}
		}
	}
	
	public boolean checkFileExistInFtpServer(String targetDirectory,String filename) throws Exception{
		FTPClient ftpClient = getFtpClient(Constants.TEMPFTPCLIENTSESSIONID);
		if (!ftpClient.isConnected()) {
//			ftpClient.setAutoNoopTimeout(30000);
			ftpClient.connect(ftpAddr, ftpPort);
			ftpClient.login(ftpUser, ftpPsw);
		}
		if (ftpClient.isConnected()) {
			ftpClient.changeDirectory(targetDirectory);
			String[] listNames = ftpClient.listNames();
			for (String name : listNames) {
				if (name.equals(filename)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void upLoadFile(ShareFileListPanel panel) throws Exception{
		
		log.info("upLoadFile "+panel);
		JFileChooser chooser = new JFileChooser();
		int showOpenDialog = chooser.showOpenDialog(null);
		if (showOpenDialog == JFileChooser.APPROVE_OPTION) {
			final File selectedFile = chooser.getSelectedFile();
            long fileLength = selectedFile.length();
            final String sessionID = panel.getSessionID();
            FTPClient ftpClient = getFtpClient(sessionID);
            if (!ftpClient.isConnected()) {
    			ftpClient.connect(ftpAddr, ftpPort);
    			ftpClient.login(ftpUser, ftpPsw);
    		}
            String filepath = "/"+panel.getParentFtpPath()+"/"+panel.getCurrentFtpPath();
            ftpClient.changeDirectory(filepath);
            
          //询问是否覆盖
			String[] listNames = ftpClient.listNames();
			FTPFile[] list = ftpClient.list();
			for (FTPFile ftpFile : list) {
				if (ftpFile.getName().equals(selectedFile.getName())) {
//					JOptionPane.showMessageDialog(null, "文件已存在，是否覆盖");
					int showConfirmDialog = JOptionPane.showConfirmDialog(null, VovoStringUtil.getUIString("ShareFileListController.coverFileConfirm"),"",JOptionPane.YES_NO_OPTION);
					if (showConfirmDialog != JOptionPane.YES_OPTION) {
						return;
					}
					break;
				}
			}
            
//            MessageTabPanel view = Vovo.getMyContext().getViewManager().getView("MessageTabPanel");
			ShareFileListItem shareFileListItem = new ShareFileListItem();
            shareFileListItem.getProgressBar().setVisible(true);
//            int lastIndexOf = filepath.lastIndexOf("/");
            shareFileListItem.setFileName(selectedFile.getName());
            shareFileListItem.setAbsolutePath(selectedFile.getAbsolutePath());
			shareFileListItem.setSessionID(panel.getSessionID());
			shareFileListItem.setIconFromFileName(selectedFile.getName());
			shareFileListItem.getItemInfoPanel().setVisible(false);
			panel.addUpLoadShareFileListItem(shareFileListItem);
           
			
			
			final String uiString = Vovo.getMyContext().getViewManager().getUIString("ShareFileListController.uploadProcess");
            final MyTransferListener listener= new MyTransferListener(){
				
				@Override
				public void transferred(int length) {
					super.transferred(length);
					double d = 1;
					d = 100 * transferedLength / fileLength;
			        int  n = (int) d;
					item.getProgressBar().setValue(n);
					item.getProgressBar().setString(MessageFormat.format(uiString, n));
//					panel.getTransferFileList().repaint();
				}
				
				@Override
				public void aborted() {
					super.aborted();
					panel.getFileList().setEnabled(true);
					panel.getFileList().setOpaque(false);
					panel.setOperationButtonEnable(true);
					panel.getFileList().repaint();
					item.getCancelUpLoadHyperlink().setVisible(false);
				}

				@Override
				public void completed() {
					super.completed();
					panel.getFileList().setEnabled(true);
					panel.getFileList().setOpaque(false);
					panel.setOperationButtonEnable(true);
					item.getProgressBar().setValue(100);
					item.getProgressBar().setString(MessageFormat.format(uiString, 100));
					try {
						getDefaultFileList(panel);
						panel.getFileList().repaint();
					} catch (Exception e) {
						log.error("completed getDefaultFileList",e);
					}
					item.getCancelUpLoadHyperlink().setVisible(false);
				}

				@Override
				public void failed() {
					super.failed();
					panel.getFileList().setEnabled(true);
					panel.getFileList().setOpaque(false);
					panel.setOperationButtonEnable(true);
					panel.getFileList().repaint();
					item.getCancelUpLoadHyperlink().setVisible(false);
				}

				@Override
				public void started() {
					super.started();
					panel.getFileList().setEnabled(false);
					panel.getFileList().setOpaque(true);
					panel.getFileList().setBackground(new Color(212, 208, 200));//
//					panel.getButtonToolBar().setEnabled(false);
					panel.setOperationButtonEnable(false);
					item.getCancelUpLoadHyperlink().setVisible(true);
				}
				
			};
			listener.item = shareFileListItem;
			listener.panel = panel;
			listener.fileLength = fileLength;
            
            new Thread(){

				@Override
				public void run() {
					try {
						FTPClient ftpClient = getFtpClient(sessionID);
						ftpClient.upload(selectedFile,listener);
					} catch (Exception e) {
						log.error("upLoadFile Thread",e);
					} 
				}
            }.start();
		}
	}
	
	class MyTransferListener implements FTPDataTransferListener {

		public long transferedLength = 0;
		public ShareFileListItem item;
		public ShareFileListPanel panel;
		public long fileLength = 0;
		
		@Override
		public void aborted() {
			
		}

		@Override
		public void completed() {
			
		}

		@Override
		public void failed() {
			
		}

		@Override
		public void started() {
			
		}

		@Override
		public void transferred(int length) {
			transferedLength = transferedLength + length;
		}
		
	}
	
}
