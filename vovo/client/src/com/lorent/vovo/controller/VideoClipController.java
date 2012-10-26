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
import com.lorent.vovo.ui.UploadVideoClipDialog;
import com.lorent.vovo.ui.VideoClipInfoDialog;
import com.lorent.vovo.ui.VideoClipItem;
import com.lorent.vovo.ui.VideoClipPanel;
import com.lorent.vovo.util.Constants;
import com.lorent.vovo.util.VovoStringUtil;

public class VideoClipController extends BaseController {
	private static Logger log  = Logger.getLogger(VideoClipController.class);
	
	private int pageIndex = 0;
	private int pageSize = 4;
	private int maxPageIndex = -1;
	
	
	public void showUploadVideoClipDialog() throws Exception{
		UploadVideoClipDialog uploadVideoClipDialog = new UploadVideoClipDialog(null, true);
		context.getViewManager().setWindowCenterLocation(uploadVideoClipDialog);
		uploadVideoClipDialog.setVisible(true);
		
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
				System.out.println(cmdStr);
				Process startProcess = ProcessUtil.getInstance().startProcess(cmdStr);
				byte b[] = new byte[1024];
	            int r = 0;
				while ((r = startProcess.getErrorStream().read(b, 0, 1024)) > -1) {
	                log.info(new String(b, 0, r));
	            }
				startProcess.waitFor();
				
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
				dialog.setSelectedStandardVideoFilePath(thefile.getAbsolutePath());
				dialog.getStandardFilePathTextField().setText(jFileChooser.getSelectedFile().getAbsolutePath());
			}
			

		}
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
					JOptionPane.showMessageDialog(null, VovoStringUtil.getUIString("VideoClipController.uploadSuccess"), VovoStringUtil.getUIString("VideoClipController.infoTip"), JOptionPane.YES_OPTION);
					dialog.dispose();
					reflashVideoClipPanel();
				}
                return new Object();
            }
        }.execute();
	}
	
	public void deleteVideoClip(VideoClipItem item) throws Exception{
		int showConfirmDialog = JOptionPane.showConfirmDialog(null, VovoStringUtil.getUIString("VideoClipController.askConfirmDelete"), VovoStringUtil.getUIString("VideoClipController.infoTip"), JOptionPane.YES_NO_OPTION);
		if (showConfirmDialog == JOptionPane.YES_OPTION) {
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
							imagePainter = new ImagePainter(ImageIO.read(getClass().getResource("/com/lorent/vovo/resource/images/brokenimg.jpg")));
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
	
	public void showVideoClipInfoDialog(VideoClipItem item) throws Exception{
		MainFrame frame = Vovo.getViewManager().getView(Constants.ViewKey.MAINFRAME.toString());
		VideoClipInfoDialog videoClipInfoDialog = new VideoClipInfoDialog(frame, true);
		videoClipInfoDialog.getCreaterNameTextField().setText(item.getLcmVideoClip().getCreaterName());
		videoClipInfoDialog.getTitleTextField().setText(item.getLcmVideoClip().getTitle());
		videoClipInfoDialog.getDescriptionTextArea().setText(item.getLcmVideoClip().getDescription());
		videoClipInfoDialog.getPictureXPanel().setBackgroundPainter(item.getPictureXPanel().getBackgroundPainter());
		Vovo.getViewManager().setWindowCenterLocation(videoClipInfoDialog);
		videoClipInfoDialog.setTitle("视频信息");
		videoClipInfoDialog.setVisible(true);
	}
	
}
