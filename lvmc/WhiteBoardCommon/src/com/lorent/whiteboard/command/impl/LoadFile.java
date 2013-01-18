package com.lorent.whiteboard.command.impl;

import java.io.File;
import java.lang.reflect.Method;

import javax.swing.JOptionPane;

import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lorent.commonconfig.ConfigUtil;
import com.lorent.util.FileListConfigUtilFactory;
import com.lorent.whiteboard.command.MeetingCommandAdaptor;
import com.lorent.whiteboard.model.Meeting;
import com.lorent.whiteboard.model.Whiteboard;

public class LoadFile extends MeetingCommandAdaptor {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory
			.getLogger(LoadFile.class);
	private static final String apppath = System.getProperty("user.dir");
	private final String fileName;

	public LoadFile(String meetingId,String fileName) {
		super(meetingId);
		this.fileName=fileName;
	}

	@Override
	public void execute(Meeting meeting, IoSession session) {
		ConfigUtil configUtil = FileListConfigUtilFactory.getFileListConfUtilByMeetingID(meetingId, session);
		String realFileName = configUtil.getProperty(fileName);
		Whiteboard whiteboard = meeting.createWhiteboard(realFileName);
		if(whiteboard==null){
			session.write(new ShowMessage("根据服务器配置，已经达到最大白板个数。", 
					"尊敬的用户", JOptionPane.INFORMATION_MESSAGE,new LoadedFile(fileName)));
			logger.info("{} Whiteboard {} create failed because of maxBoards!",meeting,realFileName);
			return;
		}
		if(whiteboard.getCurrentCommandId()!=0){
			session.write(new ShowMessage("文档已被加载。", 
					"尊敬的用户", JOptionPane.INFORMATION_MESSAGE,new LoadedFile(fileName)));
			logger.info("{} Whiteboard {} create failed because of loaded!",meeting,realFileName);
			return;
		}
		String filePath=apppath+"/files/"+meetingId+"/"+realFileName;
		logger.info("LoadToWhiteBoard {}",filePath+" , "+fileName);
		try {
			Class<?> clazz = Class.forName("com.lorent.fileserver.convertor.FileConvertors");
			Method method = clazz.getDeclaredMethod("convert", File.class,String.class,String.class,IoSession.class);
			method.invoke(null, new File(filePath),fileName,meetingId,session);
		} catch (Exception e) {
			meeting.removeWhiteboard(realFileName);
			logger.warn("convert file {} failed",filePath);
			logger.warn("failed by",e);
			session.write(new ShowMessage("加载文件失败！", 
					"尊敬的用户", JOptionPane.INFORMATION_MESSAGE,new LoadedFile(fileName)));
		}
	}

	@Override
	public String toString() {
		return "LoadFile [fileName=" + fileName + ", meetingId=" + meetingId
				+ "]";
	}

}
