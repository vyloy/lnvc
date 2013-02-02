/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.lvmc.service;

import com.lorent.lvmc.util.ConfigUtil;
import com.lorent.lvmc.util.DataUtil;
import com.lorent.lvmc.util.ShareFileServerUtil;

/**
 *
 * @author Administrator
 */
public class ShareFileService extends BaseService{
	
	public void deleteFile(String meetingID,String serverFileName) throws Exception{
//      ShareFileServerUtil.getInstance().connect("127.0.0.1", 8889);//DataUtil.getLoginInfo().getServerIP()
//        ShareFileServerUtil.getInstance().connect(DataUtil.getLoginInfo().getServerIP(), Integer.parseInt(ConfigUtil.getProperty("fileServerPort","8889")));
        ShareFileServerUtil.getInstance().deleteFile(meetingID, serverFileName,ShareFileServerUtil.getInstance().getSession());
//        ShareFileServerUtil.getInstance().close();
	}
	
	public void setTransferState(String uniqueFileID,Boolean bRunning) throws Exception{
//    	processStateMap.put(uniqueFileID, bRunning);
		ShareFileServerUtil.getInstance().setTransferState(uniqueFileID, bRunning);
    }
    
    public void removeTransferState(String uniqueFileID) throws Exception{
//    	processStateMap.remove(uniqueFileID);
    	ShareFileServerUtil.getInstance().removeTransferState(uniqueFileID);
    }
	
    public synchronized void downLoadFile(String uniqueFileID,String meetingID,String serverFileName,String localFilePath) throws Exception{
//        ShareFileServerUtil.getInstance().connect("127.0.0.1", 8889);//DataUtil.getLoginInfo().getServerIP()
//        ShareFileServerUtil.getInstance().connect(DataUtil.getLoginInfo().getServerIP(), Integer.parseInt(ConfigUtil.getProperty("fileServerPort","8889")));
        ShareFileServerUtil.getInstance().downLoadFileFromServer(uniqueFileID,meetingID, serverFileName, localFilePath,ShareFileServerUtil.getInstance().getSession());
//        ShareFileServerUtil.getInstance().close();
    }
    
    public void loadFileToBoard(String meetingID,String serverFileName) throws Exception{
//        ShareFileServerUtil.getInstance().connect("127.0.0.1", 8889);//DataUtil.getLoginInfo().getServerIP()
//        ShareFileServerUtil.getInstance().connect(DataUtil.getLoginInfo().getServerIP(), Integer.parseInt(ConfigUtil.getProperty("fileServerPort","8889")));
    	ShareFileServerUtil.getInstance().loadFileToBoard(meetingID, serverFileName,ShareFileServerUtil.getInstance().getSession());
//    	ShareFileServerUtil.getInstance().close();
    }
    
    public void getServerFileList(String meetingID) throws Exception{
//        ShareFileServerUtil.getInstance().connect("127.0.0.1", 8889);//DataUtil.getLoginInfo().getServerIP()
//        ShareFileServerUtil.getInstance().connect(DataUtil.getLoginInfo().getServerIP(), Integer.parseInt(ConfigUtil.getProperty("fileServerPort","8889")));
        ShareFileServerUtil.getInstance().getFileListFromServer(meetingID,ShareFileServerUtil.getInstance().getSession());
//        ShareFileServerUtil.getInstance().close();
//        return  fileListFromServer;
    }
    
    public synchronized void upLoadFile(String uniqueFileID,String meetingID,String localFilePath, boolean convertFileToImgFlag) throws Exception{
//        ShareFileServerUtil.getInstance().connect("127.0.0.1", 8889);//DataUtil.getLoginInfo().getServerIP()
//        ShareFileServerUtil.getInstance().connect(DataUtil.getLoginInfo().getServerIP(), Integer.parseInt(ConfigUtil.getProperty("fileServerPort","8889")));
        ShareFileServerUtil.getInstance().sendFileToServer(uniqueFileID,meetingID, localFilePath,convertFileToImgFlag,ShareFileServerUtil.getInstance().getSession());
//        ShareFileServerUtil.getInstance().close();
    }
    
    public void keepConnect(String meetingID,String username) throws Exception{
//        ShareFileServerUtil.getInstance().connectKeepAlive("127.0.0.1", 8889, meetingID,username);
        ShareFileServerUtil.getInstance().connectKeepAlive(DataUtil.getLoginInfo().getServerIP(), Integer.parseInt(ConfigUtil.getProperty("fileServerPort","8889")), meetingID,username);
    }
    
    public void close()throws Exception{
        ShareFileServerUtil.getInstance().close();
    }
    
    public  long getServerFileSize(String meetingID,String serverFileName) throws Exception{
//        ShareFileServerUtil.getInstance().connect("127.0.0.1", 8889);//DataUtil.getLoginInfo().getServerIP()
//        ShareFileServerUtil.getInstance().connect(DataUtil.getLoginInfo().getServerIP(), Integer.parseInt(ConfigUtil.getProperty("fileServerPort","8889")));
        long fileSize = ShareFileServerUtil.getInstance().getServerFileSize(meetingID, serverFileName,ShareFileServerUtil.getInstance().getSession());
//        ShareFileServerUtil.getInstance().close();
        return fileSize;
    }
    
    public String getServerRealFileName(String meetingID,String fileName) throws Exception{
    	String serverRealFileName = ShareFileServerUtil.getInstance().getServerRealFileName(meetingID, fileName,ShareFileServerUtil.getInstance().getSession());
    	return serverRealFileName;
    }
}
