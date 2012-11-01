package com.lorent.vovo.controller;

import it.sauronsoftware.ftp4j.FTPDataTransferListener;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.log4j.Logger;
import org.jdesktop.swingx.painter.ImagePainter;
import org.jdesktop.swingx.painter.ImagePainter.ScaleType;

import com.lorent.common.controller.BaseController;
import com.lorent.common.dto.LCMVideoClip;
import com.lorent.common.dto.VovoMyInfo;
import com.lorent.common.util.FileUtil;
import com.lorent.common.util.LCMUtil;
import com.lorent.common.util.ProcessUtil;
import com.lorent.common.util.StringUtil;
import com.lorent.lvmc.util.DataUtil;
import com.lorent.vovo.Vovo;
import com.lorent.vovo.dto.LoginInfo;
import com.lorent.vovo.ui.MainFrame;
import com.lorent.vovo.ui.UploadMonitorDialog;
import com.lorent.vovo.ui.UploadVideoClipDialog;
import com.lorent.vovo.ui.VideoClipInfoDialog;
import com.lorent.vovo.ui.VideoClipItem;
import com.lorent.vovo.ui.VideoClipPanel;
import com.lorent.vovo.util.Constants;
import com.lorent.vovo.util.VovoStringUtil;

public class VideoClipController extends BaseController {
	private static Logger log  = Logger.getLogger(VideoClipController.class);
	
	private int pageSize = 4;
	private int pageIndex = 0;
	private int maxPageIndex = -1;
	private int pageIndexM = 0;
	private int maxPageIndexM = -1;
	
	public void showUploadVideoClipDialog() throws Exception{
		UploadVideoClipDialog uploadVideoClipDialog = new UploadVideoClipDialog(null, true);
		context.getViewManager().setWindowCenterLocation(uploadVideoClipDialog);
		uploadVideoClipDialog.setVisible(true);
		
	}
	
	public void showUploadMonitorDialog() throws Exception{
		UploadMonitorDialog uploadMonitorDialog = new UploadMonitorDialog(null, true);
		context.getViewManager().setWindowCenterLocation(uploadMonitorDialog);
		uploadMonitorDialog.setVisible(true);
	}
	
	public void selectedMonitorPictureFile(UploadMonitorDialog dialog) throws Exception{
		JFileChooser jFileChooser = new JFileChooser();
		jFileChooser.setFileFilter(new FileNameExtensionFilter("*.jpg", new String[]{"jpg"}));
		SimpleDateFormat dateformat1=new SimpleDateFormat("yyyyMMddHHmmss_SSS");
		String currentTime=dateformat1.format(new Date(Vovo.getLcmUtil().getSystemTime()));
		int showOpenDialog = jFileChooser.showOpenDialog(dialog);
		if (showOpenDialog == JFileChooser.APPROVE_OPTION) {
			File thefile = jFileChooser.getSelectedFile();
//			dialog.getHightFilePathTextField().setText(jFileChooser.getSelectedFile().getAbsolutePath());
			dialog.getThumbnailTextField().setText(thefile.getAbsolutePath());
			
			String target = Constants.USER_HOME+"\\lorent\\vovo\\videoclip\\";
			String cacheFileName = target+currentTime+"_"+thefile.getName();
			
//			FileUtil.fileCopy(thefile.getAbsolutePath(), cacheFileName);
			FileUtil.localFileCopy(thefile.getAbsolutePath(), cacheFileName);
			
			File cacheimagefile = new File(cacheFileName);
			if (cacheimagefile.exists()) {
				BufferedImage bufferimage =ImageIO.read(cacheimagefile);
				ImagePainter ip = new ImagePainter(bufferimage);
				ip.setScaleToFit(true);
				ip.setScaleType(ScaleType.Distort);
				dialog.getThumbnailXPanel1().setBackgroundPainter(ip);
				dialog.setCacheFileName(cacheimagefile.getName());
			}
		}
	}
	
	public void selectedVideoClipFile(UploadVideoClipDialog dialog,Constants.VideoDefinition definitaion) throws Exception{
		JFileChooser jFileChooser = new JFileChooser();
		jFileChooser.setFileFilter(new FileNameExtensionFilter("*.mp4", new String[]{"mp4"}));
		//改为获得服务器时间
		SimpleDateFormat dateformat1=new SimpleDateFormat("yyyyMMddHHmmss_SSS");
		String currentTime=dateformat1.format(new Date(Vovo.getLcmUtil().getSystemTime()));
		int showOpenDialog = jFileChooser.showOpenDialog(dialog);
		if (showOpenDialog == JFileChooser.APPROVE_OPTION) {
			File thefile = jFileChooser.getSelectedFile();
			if (definitaion.equals(Constants.VideoDefinition.High)) {
				dialog.setSelectedHighVideoFilePath(thefile.getAbsolutePath());
				//ffmpeg
				String ffmpeg = StringUtil.convertFilePath2DOSCommandStr(Constants.USER_DIR+"\\ffmpeg\\ffmpeg.exe");
				//ffmpeg -i "flvplayer.flv" -y -f image2 -t 0.001 -s 352x240 %home%\lorent\vovo\videoclip\test.jpg
				String target = Constants.USER_HOME+"\\lorent\\vovo\\videoclip\\";
				File file = new File(target);
				if (!file.exists()) {
					file.mkdirs();
				}
				String selectedFile = StringUtil.convertFilePath2DOSCommandStr(jFileChooser.getSelectedFile().getAbsolutePath());
				String cacheFileName = target+currentTime+"_"+thefile.getName()+".jpg";
				dialog.setCurrentTime(currentTime);
				String targetPath = StringUtil.convertFilePath2DOSCommandStr(cacheFileName);
				String cmdStr ="cmd /c " + StringUtil.convertFilePath2DOSCommandStr(ffmpeg+" -i "+selectedFile+" -ss 3 -vframes 1 -r 1 -ac 1 -ab 2 -s 352x240 -f image2 "+targetPath);
				log.info(cmdStr);
				Process startProcess = ProcessUtil.getInstance().startProcess(cmdStr);
				byte b[] = new byte[1024];
	            int r = 0;
				while ((r = startProcess.getErrorStream().read(b, 0, 1024)) > -1) {
	                log.info(new String(b, 0, r));
	            }
				startProcess.waitFor();
				
				mp4box(selectedFile);
				
				dialog.getHightFilePathTextField().setText(jFileChooser.getSelectedFile().getAbsolutePath());
				File cacheimagefile = new File(cacheFileName);
				if (cacheimagefile.exists()) {
					BufferedImage bufferimage =ImageIO.read(cacheimagefile);
					ImagePainter ip = new ImagePainter(bufferimage);
					ip.setScaleToFit(true);
					ip.setScaleType(ScaleType.Distort);
					dialog.getThumbnailXPanel1().setBackgroundPainter(ip);
					dialog.setThumbnailImageFilePath(cacheFileName);
				}
			}
			else if(definitaion.equals(Constants.VideoDefinition.Standard)){
				mp4box(thefile.getAbsolutePath());
				dialog.setSelectedStandardVideoFilePath(thefile.getAbsolutePath());
				dialog.getStandardFilePathTextField().setText(jFileChooser.getSelectedFile().getAbsolutePath());
			}
		}
	}
	
	private void mp4box(String targetFilePath) throws Exception{
		//mp4box
		byte b[] = new byte[1024];
        int r = 0;
		String mp4box = StringUtil.convertFilePath2DOSCommandStr(Constants.USER_DIR+"\\GPAC\\mp4box.exe");
		String cmdStr = "cmd /c "+StringUtil.convertFilePath2DOSCommandStr(mp4box +" -hint "+targetFilePath);
		System.out.println(cmdStr);
		log.info(cmdStr);
		Process startProcess1 = ProcessUtil.getInstance().startProcess(cmdStr);
		while ((r = startProcess1.getInputStream().read(b, 0, 1024)) > -1) {
            log.info(new String(b, 0, r));
        }
		startProcess1.waitFor();
	}
	
	class FTPDataTransferAdater implements FTPDataTransferListener{
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
		public void transferred(int transferedLength) {
		}
	}
	
	public void uploadVideoClip(final UploadVideoClipDialog dialog) throws Exception{
		//检测输入
		String highFilePath = dialog.getHightFilePathTextField().getText();
		if (highFilePath ==  null || highFilePath.equals("")) {
			showErrorDialog(VovoStringUtil.getUIString("VideoClipController.infoTip"), VovoStringUtil.getUIString("VideoClipController.needselectFileHigh"));
			return;
		}
		String standardFilePath = dialog.getStandardFilePathTextField().getText();
		if (standardFilePath == null || standardFilePath.equals("")) {
			showErrorDialog(VovoStringUtil.getUIString("VideoClipController.infoTip"), VovoStringUtil.getUIString("VideoClipController.needselectFileStandard"));
			return;
		}
		String title = dialog.getTitleTextField().getText();
		if (title == null || title.equals("")) {
			showErrorDialog(VovoStringUtil.getUIString("VideoClipController.infoTip"), VovoStringUtil.getUIString("VideoClipController.needTitle"));
			return;
		}
		else if (title.length() >= 100) {
			showErrorDialog(VovoStringUtil.getUIString("VideoClipController.infoTip"), VovoStringUtil.getUIString("VideoClipController.titleTooLong"));
			return;
		}
		String description = dialog.getDescriptionTextArea().getText();
		if (description == null || description.equals("")) {
			showErrorDialog(VovoStringUtil.getUIString("VideoClipController.infoTip"), VovoStringUtil.getUIString("VideoClipController.needDescription"));
			return;
		}
		else if (description.length() >= 255) {
			showErrorDialog(VovoStringUtil.getUIString("VideoClipController.infoTip"), VovoStringUtil.getUIString("VideoClipController.descriptionTooLong"));
			return;
		}
		
		dialog.getTitleTextField().setEnabled(false);
		dialog.getDescriptionTextArea().setEnabled(false);
		dialog.getSelectFileHighButton().setEnabled(false);
		dialog.getSelectFileStandardButton().setEnabled(false);
		dialog.getUploadButton().setEnabled(false);
		//上传文件至ftp
		final File thumbailfile = new File(dialog.getThumbnailImageFilePath());
		final long thumbailfilesize = thumbailfile.length();
		
		final String uiString1 = "步骤2: "+ thumbailfile.getName() + "  {0} %";
		final File file = new File(dialog.getSelectedHighVideoFilePath());
		final String newFileName =dialog.getCurrentTime()+"_"+ file.getName();
		final FTPDataTransferListener thumbnailDataTransferListener = new FTPDataTransferAdater(){
			private long curentLength = 0;
			@Override
			public void transferred(int transferedLength) {
				// TODO Auto-generated method stub
				curentLength = curentLength + transferedLength;
				double d = 1;
				d = 100 * curentLength / thumbailfilesize;
		        int  n = (int) d;
		        dialog.getResultProgressBar().setValue(n);
		        dialog.getResultProgressBar().setString(MessageFormat.format(uiString1, n));
			}
		};
		
		final long filesize = file.length();
		final String uiString2 = "步骤1: "+file.getName() +"  {0} %";
		final FTPDataTransferListener ftpDataTransferListener = new FTPDataTransferAdater(){
			private long curentLength = 0;
			@Override
			public void transferred(int transferedLength) {
				curentLength = curentLength + transferedLength;
				double d = 1;
				d = 100 * curentLength / filesize;
		        int  n = (int) d;
		        dialog.getResultProgressBar().setValue(n);
		        dialog.getResultProgressBar().setString(MessageFormat.format(uiString2, n));
			}
			
		};
		
		final File file1 = new File(dialog.getSelectedStandardVideoFilePath());
		final String newFileName1 =dialog.getCurrentTime()+"_"+ file1.getName();
		final long filesize1 = file1.length();
		final String uiString3 = "步骤3: "+file1.getName() +"  {0} %";
		final FTPDataTransferListener ftpDataTransferListener1 = new FTPDataTransferAdater(){
			private long curentLength = 0;
			@Override
			public void transferred(int transferedLength) {
				curentLength = curentLength + transferedLength;
				double d = 1;
				d = 100 * curentLength / filesize1;
		        int  n = (int) d;
		        dialog.getResultProgressBar().setValue(n);
		        dialog.getResultProgressBar().setString(MessageFormat.format(uiString3, n));
			}
			
		};
		
		new SwingWorker<Object, Object>(){
            @Override
            protected Object doInBackground() throws Exception {
            	Vovo.exeC("sharefile", "upLoadFileToFtpServer", file,ftpDataTransferListener,"/VideoClips",newFileName);
            	Vovo.exeC("sharefile", "upLoadFileToFtpServer", thumbailfile,thumbnailDataTransferListener,"/VideoClips","");
            	Vovo.exeC("sharefile", "upLoadFileToFtpServer", file1,ftpDataTransferListener1,"/VideoClips",newFileName1);
            	String ftpAddr = (String) Vovo.exeC("sharefile", "getFtpAddr");
            	Integer ftpPort = (Integer) Vovo.exeC("sharefile", "getFtpPort");
            	LoginInfo info = Vovo.getMyContext().getDataManager().getValue(Constants.DataKey.LOGGININFO.toString());
            	VovoMyInfo vovoinfo = Vovo.getLcmUtil().getVovoMyInfo(info.getUsername());
            	boolean uploadVideoClipInfo = Vovo.getLcmUtil().uploadVideoClipInfo(newFileName,newFileName1, "ftp://"+ftpAddr+":"+ftpPort+"/VideoClips/"+thumbailfile.getName(), dialog.getTitleTextField().getText(),dialog.getDescriptionTextArea().getText(),ftpAddr,vovoinfo.getRealName(),vovoinfo.getUsername());
            	if (uploadVideoClipInfo) {
//					System.out.println(uploadVideoClipInfo);
					JOptionPane.showMessageDialog(null, VovoStringUtil.getUIString("VideoClipController.uploadSuccess"));
					dialog.dispose();
					reflashVideoClipPanel();
				}
                return new Object();
            }
        }.execute();
	}
	
	public void uploadMonitor(final UploadMonitorDialog dialog) throws Exception{
		//检查输入
		String thumbnail = dialog.getThumbnailTextField().getText();
		if (thumbnail == null || thumbnail.equals("")) {
			showErrorDialog(VovoStringUtil.getUIString("VideoClipController.infoTip"), VovoStringUtil.getUIString("VideoClipController.needselectThumbnail"));
			return;
		}
		String title = dialog.getTitleTextField().getText();
		if (title == null || title.equals("")) {
			showErrorDialog(VovoStringUtil.getUIString("VideoClipController.infoTip"), VovoStringUtil.getUIString("VideoClipController.needTitle"));
			return;
		}
		else if(title.length() >= 100){
			showErrorDialog(VovoStringUtil.getUIString("VideoClipController.infoTip"), VovoStringUtil.getUIString("VideoClipController.titleTooLong"));
			return;
		}
		String description = dialog.getDescriptionTextArea().getText();
		if (description == null || description.equals("")) {
			showErrorDialog(VovoStringUtil.getUIString("VideoClipController.infoTip"), VovoStringUtil.getUIString("VideoClipController.needDescription"));
			return;
		}
		else if(description.length()>=255){
			showErrorDialog(VovoStringUtil.getUIString("VideoClipController.infoTip"), VovoStringUtil.getUIString("VideoClipController.descriptionTooLong"));
			return;
		}
		String liveUrl = dialog.getLiveStreamURLTextField().getText();
		if (liveUrl == null || liveUrl.equals("")) {
			showErrorDialog(VovoStringUtil.getUIString("VideoClipController.infoTip"), VovoStringUtil.getUIString("VideoClipController.needLiveStreamUrl"));
			return;
		}
		else if(liveUrl.length() >=2048){
			showErrorDialog(VovoStringUtil.getUIString("VideoClipController.infoTip"), VovoStringUtil.getUIString("VideoClipController.liveStreamUrlTooLong"));
			return;
		}
		dialog.getLiveStreamURLTextField().setEnabled(false);
		dialog.getTitleTextField().setEnabled(false);
		dialog.getDescriptionTextArea().setEnabled(false);
		dialog.getUploadButton().setEnabled(false);
		//上传文件至ftp
		
		final File thumbailfile = new File(dialog.getThumbnailTextField().getText());
		final String uiString = " "+ thumbailfile.getName() + "  {0} %";
		final long thumbailfilesize = thumbailfile.length();
		final FTPDataTransferListener thumbnailDataTransferListener = new FTPDataTransferAdater(){
			private long curentLength = 0;
			@Override
			public void transferred(int transferedLength) {
				curentLength = curentLength + transferedLength;
				double d = 1;
				d = 100 * curentLength / thumbailfilesize;
		        int  n = (int) d;
		        dialog.getResultProgressBar().setValue(n);
		        dialog.getResultProgressBar().setString(MessageFormat.format(uiString, n));
			}
		};
		new SwingWorker<Object, Object>(){
            @Override
            protected Object doInBackground() throws Exception {
            	Vovo.exeC("sharefile", "upLoadFileToFtpServer", thumbailfile,thumbnailDataTransferListener,"/Monitor",dialog.getCacheFileName());
            	String ftpAddr = (String) Vovo.exeC("sharefile", "getFtpAddr");
            	Integer ftpPort = (Integer) Vovo.exeC("sharefile", "getFtpPort");
            	LoginInfo info = Vovo.getMyContext().getDataManager().getValue(Constants.DataKey.LOGGININFO.toString());
            	VovoMyInfo vovoinfo = Vovo.getLcmUtil().getVovoMyInfo(info.getUsername());
            	String liveStreamUrl = dialog.getLiveStreamURLTextField().getText();
            	String thumbnailFtpUrl = "ftp://"+ftpAddr+":"+ftpPort+"/Monitor/"+dialog.getCacheFileName();
            	String title = dialog.getTitleTextField().getText();
            	String description = dialog.getDescriptionTextArea().getText();
            	boolean uploadMonitorInfo = Vovo.getLcmUtil().uploadMonitorInfo(liveStreamUrl, thumbnailFtpUrl, title, description, ftpAddr, vovoinfo.getRealName(),vovoinfo.getUsername());
            	if (uploadMonitorInfo) {
					JOptionPane.showMessageDialog(null, VovoStringUtil.getUIString("VideoClipController.uploadSuccess"));
					dialog.dispose();
					reflashMonitorPanel();
				}
            	
                return new Object();
            }
        }.execute();
	}
		
	public void deleteVideoClip(VideoClipItem item) throws Exception{
		int showConfirmDialog = JOptionPane.showConfirmDialog(null, VovoStringUtil.getUIString("VideoClipController.askConfirmDelete"), VovoStringUtil.getUIString("VideoClipController.infoTip"), JOptionPane.YES_NO_OPTION);
		if (showConfirmDialog == JOptionPane.YES_OPTION) {
			
			if (item.getLcmVideoClip().getIsmonitor()) {
				String filenameHigh = FileUtil.getFileNameFromURL(item.getLcmVideoClip().getVideoClipUrlHigh());
				if (filenameHigh != null && !filenameHigh.equals("")) {
					Vovo.exeC("sharefile", "deleteFileAtFtpServer", "/Monitor",filenameHigh);
				}
				Vovo.getLcmUtil().deleteVideoClip(item.getLcmVideoClip().getId());
				reflashMonitorPanel();
			}
			else{
				String filenameHigh = FileUtil.getFileNameFromURL(item.getLcmVideoClip().getVideoClipUrlHigh());
				if (filenameHigh != null && !filenameHigh.equals("")) {
					Vovo.exeC("sharefile", "deleteFileAtFtpServer", "/VideoClips",filenameHigh);
				}
				String filenameStandard = FileUtil.getFileNameFromURL(item.getLcmVideoClip().getVideoClipUrlStandard());
				if (filenameStandard != null && !filenameStandard.equals("")) {
					Vovo.exeC("sharefile", "deleteFileAtFtpServer", "/VideoClips", filenameStandard);
				}
				String filenameThumbnail = FileUtil.getFileNameFromURL(item.getLcmVideoClip().getThumbnailUrl());
				if (filenameThumbnail != null && !filenameThumbnail.equals("")) {
					Vovo.exeC("sharefile", "deleteFileAtFtpServer", "/VideoClips", filenameThumbnail);
				}
				Vovo.getLcmUtil().deleteVideoClip(item.getLcmVideoClip().getId());
				reflashVideoClipPanel();
			}
		}
	}
	
	private void reflashMonitorPanel(final int pageIndex,final int pageSize)throws Exception{
		SwingUtilities.invokeLater(new Runnable() {
					
			@Override
			public void run() {
				try {
					LoginInfo info = Vovo.getMyContext().getDataManager().getValue(Constants.DataKey.LOGGININFO.toString());
					LCMVideoClip[] monitorList = Vovo.getLcmUtil().getMonitorList(pageIndex, pageSize);
					log.info("MonitorList size: "+monitorList.length);
					VideoClipPanel panel = Vovo.getViewManager().getView(Constants.ViewKey.VIDEOCLIPPANEL.toString());
					panel.getMonitorPanel().removeAll();
					for (LCMVideoClip lcmVideoClip : monitorList) {
//						System.out.println(lcmVideoClip);
						VideoClipItem videoClipItem = new VideoClipItem();
						videoClipItem.setLcmVideoClip(lcmVideoClip);
//						videoClipItem.getPictureLabel().setIcon(new ImageIcon(new URL(lcmVideoClip.getThumbnailUrl())));
						videoClipItem.getTitleLabel().setText(lcmVideoClip.getTitle());
						videoClipItem.getDescriptionLabel().setText(lcmVideoClip.getDescription());
						
						ImagePainter imagePainter = null;
						try {
//							URL url = new URL(lcmVideoClip.getThumbnailUrl());
							imagePainter = new ImagePainter(new URL(lcmVideoClip.getThumbnailUrl()));
						} catch (Exception e) {
							imagePainter = new ImagePainter(ImageIO.read(getClass().getResource("/com/lorent/vovo/resource/images/brokenimg.png")));
							log.error("reflashVideoClipPanel ",e );
						}
						imagePainter.setScaleToFit(true);
						imagePainter.setScaleType(ScaleType.Distort);
						videoClipItem.getPictureXPanel().setBackgroundPainter(imagePainter);
						
						if (lcmVideoClip.getCreaterNo() == null || lcmVideoClip.getCreaterNo().equals("")) {
							videoClipItem.getDeleteButton().setVisible(false);
						}
						else if(lcmVideoClip.getCreaterNo().equals(lcmVideoClip.getCreaterNo())){
							videoClipItem.getDeleteButton().setVisible(true);
						}
						else{
							videoClipItem.getDeleteButton().setVisible(false);
						}
						panel.getMonitorPanel().add(videoClipItem);
					}
					panel.getMonitorPanel().repaint();
					panel.getMonitorPanel().revalidate();
				} catch (Exception e) {
					log.error("reflashMonitorPanel", e);
				}
			}
		});
	}
	
	private void reflashVideoClipPanel(final int pageIndex,final int pageSize) throws Exception{
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				try {
					LoginInfo info = Vovo.getMyContext().getDataManager().getValue(Constants.DataKey.LOGGININFO.toString());
					LCMVideoClip[] videoClipList = Vovo.getLcmUtil().getVideoClipList(pageIndex, pageSize);
					log.info("videoClipList size: "+videoClipList.length);
					VideoClipPanel panel = Vovo.getViewManager().getView(Constants.ViewKey.VIDEOCLIPPANEL.toString());
					panel.getVideoClipPanel().removeAll();
					for (LCMVideoClip lcmVideoClip : videoClipList) {
//						System.out.println(lcmVideoClip);
						VideoClipItem videoClipItem = new VideoClipItem();
						videoClipItem.setLcmVideoClip(lcmVideoClip);
//						videoClipItem.getPictureLabel().setIcon(new ImageIcon(new URL(lcmVideoClip.getThumbnailUrl())));
						videoClipItem.getTitleLabel().setText(lcmVideoClip.getTitle());
						videoClipItem.getDescriptionLabel().setText(lcmVideoClip.getDescription());
						
						ImagePainter imagePainter = null;
						try {
//							URL url = new URL(lcmVideoClip.getThumbnailUrl());
							imagePainter = new ImagePainter(new URL(lcmVideoClip.getThumbnailUrl()));
						} catch (Exception e) {
							imagePainter = new ImagePainter(ImageIO.read(getClass().getResource("/com/lorent/vovo/resource/images/brokenimg.png")));
							log.error("reflashVideoClipPanel ",e );
						}
						imagePainter.setScaleToFit(true);
						imagePainter.setScaleType(ScaleType.Distort);
						videoClipItem.getPictureXPanel().setBackgroundPainter(imagePainter);
						
						if (lcmVideoClip.getCreaterNo() == null || lcmVideoClip.getCreaterNo().equals("")) {
							videoClipItem.getDeleteButton().setVisible(false);
						}
						else if(lcmVideoClip.getCreaterNo().equals(lcmVideoClip.getCreaterNo())){
							videoClipItem.getDeleteButton().setVisible(true);
						}
						else{
							videoClipItem.getDeleteButton().setVisible(false);
						}
						panel.getVideoClipPanel().add(videoClipItem);
					}
					panel.getVideoClipPanel().repaint();
					panel.getVideoClipPanel().revalidate();
				} catch (Exception e) {
					log.error("reflashVideoClipPanel", e);
				}
			}
		});
	}
	
	//刷新
	public void reflashMonitorPanel() throws Exception{
		int monitorListLength = Vovo.getLcmUtil().getMonitorListLength();
		int left = monitorListLength % pageSize;
		if (left >0 ) {
			maxPageIndexM = monitorListLength / pageSize + 1;
		}
		else{
			maxPageIndexM = monitorListLength / pageSize;
		}
		reflashMonitorPanel(pageIndexM, pageSize);
		VideoClipPanel panel = Vovo.getViewManager().getView(Constants.ViewKey.VIDEOCLIPPANEL.toString());
		if (maxPageIndexM-1 <= pageIndexM) {
			panel.getLastPageMonitorButton().setEnabled(false);
		}
		else{
			panel.getLastPageMonitorButton().setEnabled(true);
		}
		if (1 <= pageIndexM) {
			panel.getPrePageMonitorButton().setEnabled(true);
		}
		else{
			panel.getPrePageMonitorButton().setEnabled(false);
		}
	}
	
	//刷新
	public void reflashVideoClipPanel() throws Exception{
		int videoListLength = Vovo.getLcmUtil().getVideoListLength();
		int left = videoListLength % pageSize;
		if (left >0 ) {
			maxPageIndex = videoListLength / pageSize + 1;
		}
		else{
			maxPageIndex = videoListLength / pageSize;
		}
		reflashVideoClipPanel(pageIndex,pageSize);
		VideoClipPanel panel = Vovo.getViewManager().getView(Constants.ViewKey.VIDEOCLIPPANEL.toString());
		if (maxPageIndex-1 <= pageIndex) {
			panel.getLastPageButton().setEnabled(false);
		}
		else{
			panel.getLastPageButton().setEnabled(true);
		}
		if (1 <= pageIndex) {
			panel.getPrePageButton().setEnabled(true);
		}
		else{
			panel.getPrePageButton().setEnabled(false);
		}
	}
	
	public void preVideoClipPage() throws Exception{
		pageIndex--;
		reflashVideoClipPanel();
	}
	
	public void lastVideoClipPage() throws Exception{
		pageIndex++;
		reflashVideoClipPanel();
	}
	
	public void preMonitorPage() throws Exception{
		pageIndexM--;
		reflashMonitorPanel();
	}
	
	public void lastMonitorPage() throws Exception{
		pageIndexM++;
		reflashMonitorPanel();
	}
	
	public void showVideoClipInfoDialog(VideoClipItem item) throws Exception{
		MainFrame frame = Vovo.getViewManager().getView(Constants.ViewKey.MAINFRAME.toString());
		VideoClipInfoDialog videoClipInfoDialog = new VideoClipInfoDialog(frame, true);
		videoClipInfoDialog.getCreaterNameTextField().setText(item.getLcmVideoClip().getCreaterName());
		videoClipInfoDialog.getTitleTextField().setText(item.getLcmVideoClip().getTitle());
		videoClipInfoDialog.getDescriptionTextArea().setText(item.getLcmVideoClip().getDescription());
		videoClipInfoDialog.getPictureXPanel().setBackgroundPainter(item.getPictureXPanel().getBackgroundPainter());
		Vovo.getViewManager().setWindowCenterLocation(videoClipInfoDialog);
		videoClipInfoDialog.setTitle("视频信息");
		if (item.getLcmVideoClip().getIsmonitor()) {
			videoClipInfoDialog.getLiveStreamUrlLabel().setVisible(true);
			videoClipInfoDialog.getLiveStreamUrlTextField().setVisible(true);
			videoClipInfoDialog.getLiveStreamUrlTextField().setText(item.getLcmVideoClip().getVideoClipUrlHigh());
		}
		else{
			videoClipInfoDialog.getLiveStreamUrlLabel().setVisible(false);
			videoClipInfoDialog.getLiveStreamUrlTextField().setVisible(false);
		}
		videoClipInfoDialog.setVisible(true);
	}
	
	public void playVideoClip(String url) throws Exception{
		String vlc = StringUtil.convertFilePath2DOSCommandStr(Constants.USER_DIR+"\\vlc\\vlc.exe");
		String newUrl = StringUtil.convertFilePath2DOSCommandStr(url);
		final String cmdStr =  "cmd /c "+StringUtil.convertFilePath2DOSCommandStr(vlc+" --one-instance "+newUrl);
		new Thread(){

			@Override
			public void run() {
				try {
					log.info(cmdStr);
					Process startProcess = ProcessUtil.getInstance().startProcess(cmdStr);
//					startProcess.waitFor();
					log.info("play video end");
				} catch (Exception e) {
					log.error("playVideoClip", e);
				}
			}
		}.start();
		
	}
	
	public void startLiveTv() throws Exception{
		String liveTv = StringUtil.convertFilePath2DOSCommandStr(Constants.USER_DIR+"\\livetv\\tv.jar");
		final String cmdStr = "cmd /c "+StringUtil.convertFilePath2DOSCommandStr("java -jar "+liveTv);
		new Thread(){

			@Override
			public void run() {
				try {
					log.info(cmdStr);
					Process startProcess = ProcessUtil.getInstance().startProcess(cmdStr);
					startProcess.waitFor();
					log.info("start liveTv end");
				} catch (Exception e) {
					log.error("startLiveTv", e);
				}
			}
		}.start();
	}
}
