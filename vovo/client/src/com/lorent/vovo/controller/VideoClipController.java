package com.lorent.vovo.controller;

import it.sauronsoftware.ftp4j.FTPDataTransferListener;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.log4j.Logger;
import org.jdesktop.swingx.painter.ImagePainter;
import org.jdesktop.swingx.painter.ImagePainter.ScaleType;

import com.lorent.common.controller.BaseController;
import com.lorent.common.util.LCMUtil;
import com.lorent.common.util.ProcessUtil;
import com.lorent.common.util.StringUtil;
import com.lorent.lvmc.util.DataUtil;
import com.lorent.vovo.Vovo;
import com.lorent.vovo.ui.UploadVideoClipDialog;
import com.lorent.vovo.util.Constants;
import com.lorent.vovo.util.VovoStringUtil;

public class VideoClipController extends BaseController {
	private static Logger log  = Logger.getLogger(VideoClipController.class);
	
	public void showUploadVideoClipDialog() throws Exception{
		UploadVideoClipDialog uploadVideoClipDialog = new UploadVideoClipDialog(null, true);
		context.getViewManager().setWindowCenterLocation(uploadVideoClipDialog);
		uploadVideoClipDialog.setVisible(true);
		
	}
	
	public void selectedVideoClipFile(UploadVideoClipDialog dialog) throws Exception{
		JFileChooser jFileChooser = new JFileChooser();
		jFileChooser.setFileFilter(new FileNameExtensionFilter("*.mp4", new String[]{"mp4"}));
		//改为获得服务器时间
		SimpleDateFormat dateformat1=new SimpleDateFormat("yyyyMMddHHmmss_SSS");
		String currentTime=dateformat1.format(new Date(Vovo.getLcmUtil().getSystemTime()));
		int showOpenDialog = jFileChooser.showOpenDialog(dialog);
		if (showOpenDialog == JFileChooser.APPROVE_OPTION) {
			File thefile = jFileChooser.getSelectedFile();
			dialog.setSelectedVideoFilePath(thefile.getAbsolutePath());
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
			String cmdStr ="cmd /c " + StringUtil.convertFilePath2DOSCommandStr(ffmpeg+" -i "+selectedFile+" -y -f image2 -t 0.001 -s 352x240 "+targetPath);
			System.out.println(cmdStr);
			Process startProcess = ProcessUtil.getInstance().startProcess(cmdStr);
			byte b[] = new byte[1024];
            int r = 0;
			while ((r = startProcess.getErrorStream().read(b, 0, 1024)) > -1) {
                log.info(new String(b, 0, r));
            }
			startProcess.waitFor();
			
			dialog.getFilePathTextField().setText(jFileChooser.getSelectedFile().getAbsolutePath());
			
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
	}
	
	public void uploadVideoClip(final UploadVideoClipDialog dialog) throws Exception{
		//上传文件至ftp
		final File thumbailfile = new File(dialog.getThumbnailImageFilePath());
		final long thumbailfilesize = thumbailfile.length();
		
		final String uiString1 = "步骤2: "+ thumbailfile.getName() + "  {0} %";
		
		final File file = new File(dialog.getSelectedVideoFilePath());
		final String newFileName =dialog.getCurrentTime()+"_"+ file.getName();
		final FTPDataTransferListener thumbnailDataTransferListener = new FTPDataTransferListener(){
			private long curentLength = 0;
			
			@Override
			public void aborted() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void completed() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void failed() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void started() {
				// TODO Auto-generated method stub
				
			}

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
		final FTPDataTransferListener ftpDataTransferListener = new FTPDataTransferListener(){

			private long curentLength = 0;
			
			@Override
			public void aborted() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void completed() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void failed() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void started() {
				// TODO Auto-generated method stub
				
			}

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
		
		new SwingWorker<Object, Object>(){
            @Override
            protected Object doInBackground() throws Exception {
            	Vovo.exeC("sharefile", "upLoadFileToFtpServer", file,ftpDataTransferListener,"/VideoClips",newFileName);
            	Vovo.exeC("sharefile", "upLoadFileToFtpServer", thumbailfile,thumbnailDataTransferListener,"/VideoClips","");
            	String ftpAddr = (String) Vovo.exeC("sharefile", "getFtpAddr");
            	Integer ftpPort = (Integer) Vovo.exeC("sharefile", "getFtpPort");
            	boolean uploadVideoClipInfo = Vovo.getLcmUtil().uploadVideoClipInfo(newFileName, "ftp://"+ftpAddr+":"+ftpPort+"/VideoClips/"+thumbailfile.getName(), dialog.getTitleTextField().getText(),dialog.getDescriptionTextArea().getText(),ftpAddr);
            	if (uploadVideoClipInfo) {
					System.out.println(uploadVideoClipInfo);
					JOptionPane.showConfirmDialog(dialog, VovoStringUtil.getUIString("VideoClipController.uploadSuccess"), VovoStringUtil.getUIString("VideoClipController.infoTip"), JOptionPane.OK_OPTION);
					dialog.dispose();
				}
            	
                return new Object();
            }
        }.execute();
		
	}
	
}
