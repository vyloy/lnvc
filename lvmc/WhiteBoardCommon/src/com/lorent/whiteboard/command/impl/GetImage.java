package com.lorent.whiteboard.command.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.Collection;

import javax.imageio.ImageIO;

import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lorent.commonconfig.ConfigUtil;
import com.lorent.util.FileListConfigUtilFactory;
import com.lorent.whiteboard.command.ClientRunnable;
import com.lorent.whiteboard.model.CommandsManager;
import com.lorent.whiteboard.model.Updater;
import com.lorent.whiteboard.model.View;
import com.lorent.whiteboard.model.Whiteboard;


public class GetImage extends BroadcastCommand implements ClientRunnable{

	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory
			.getLogger(GetImage.class);
	
	protected final int pageCount;

	public GetImage(String meetingId, String whiteboardId, int page,int pageCount) {
		super(meetingId, whiteboardId);
		this.page=page;
		this.pageCount=pageCount;
	}

	@Override
	public void execute(Whiteboard board, IoSession session) {
		String apppath=System.getProperty("user.dir");
		String fileName=whiteboardId;
		
		ConfigUtil configUtil = FileListConfigUtilFactory.getFileListConfUtilByMeetingID(getMeetingId(), session);
		fileName = configUtil.getProperty(fileName);
		
		StringBuilder filePath = new StringBuilder(apppath)
				.append(File.separator).append("files").append(File.separator)
				.append(getMeetingId()).append(File.separator).append(fileName);
		logger.debug("fileName {} page {}", fileName, page);
		int index = fileName.lastIndexOf('.');

		// 拼接转换后的图片文件地址
		// eg:/opt/lcp/lvmcserver/files/426893/abc.pdf.jpgs/abc.pdf
		// eg:/opt/lcp/lvmcserver/files/426893/abc.ppt.pdf/converted.pdf.jpgs/converted.pdf
		if (".pdf".equals(fileName.substring(index))) {
			filePath.append(".jpgs").append(File.separator).append(fileName);
		} else {
			filePath.append(".pdf").append(File.separator)
					.append("converted.pdf.jpgs").append(File.separator)
					.append("converted.pdf");
		}
		filePath.append('_').append(pageCount).append('_').append(page)
				.append(".jpg");
		try {
			BufferedImage image = ImageIO.read(new File(filePath.toString()));
			Class<?> clazz = Class
					.forName("org.jhotdraw.samples.svg.figures.SVGImage");
			Constructor<?> constructor = clazz
					.getConstructor(BufferedImage.class);
			Object updater = constructor.newInstance(image);
			this.updater=(Updater<?>) updater;
			
			session.write(this);
		} catch (IOException e) {
			logger.error("Read File {} error", filePath.toString());
			logger.error("GetPage Error!", e);
		} catch (Exception e) {
			logger.error("GetPage Error!", e);
		}
	}

	@Override
	public void run(CommandsManager manager) {
		View view = manager.getView(whiteboardId);
		Collection<BroadcastCommand> commands = view.getPageCommands(page);
		for(BroadcastCommand c:commands){
			if(c instanceof BroadcastConvertedCommand){
				((BroadcastConvertedCommand) c).setUpdaterReference(updater);
				logger.debug("image updated by {}",this);
			}
		}
		view.refreshPage(page);
	}

	@Override
	public String toString() {
		return "GetImage [pageCount=" + pageCount + ", updater=" + updater
				+ ", whiteboardId=" + whiteboardId + ", page=" + page + "]";
	}

}
