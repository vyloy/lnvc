package com.lorent.fileserver.convertor;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.mina.core.session.IoSession;
import org.jhotdraw.samples.svg.figures.SVGImage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lorent.whiteboard.command.impl.BroadcastConvertedCommand;
import com.lorent.whiteboard.server.Main;

public class FileConvertors {
	private static final Logger logger = LoggerFactory
			.getLogger(FileConvertors.class);
	
	private static final HashMap<String,Convertor> convertors=new HashMap<String,Convertor>();
	
	static {
		convertors.put(".pdf", new IcePdfConvertor());
		OfficeConvertor officeConvertor = new OfficeConvertor();
		convertors.put(".ppt", officeConvertor);
		convertors.put(".doc", officeConvertor);
		convertors.put(".xls", officeConvertor);
		convertors.put(".pptx", officeConvertor);
		convertors.put(".docx", officeConvertor);
		convertors.put(".xlsx", officeConvertor);
	}
	
	public static void convert(File file,final String displayFileName,final String meetingId,final IoSession session) throws IOException{
		logger.info("file: "+file.getName()+" display:"+displayFileName+" meetingId:"+meetingId);
		int index = file.getName().lastIndexOf('.');
		if(index==-1){
			throw new IllegalArgumentException("file has not a valid suffix!It is "+file.getName());
		}
		String suffix = file.getName().substring(index).toLowerCase();
		Convertor convertor = convertors.get(suffix);
		if(convertor==null){
			logger.info("File {} has not valid convertor yet.",file);
			return;
		}
		convertor.convert(file,new Converted() {
			
			@Override
			public void converted(BufferedImage image,File file,int page,int pageCount) {
				Main.getServer().getHandler().getMeetings().execute(session, 
						new BroadcastConvertedCommand(meetingId, new SVGImage(image), displayFileName, page,pageCount));
			}
		});
	}
	
	public static void convert(File file, String meetingId, IoSession session) throws IOException{
		convert(file, file.getName(), meetingId, session);
	}
	
	public static Convertor getConvertor(String suffix){
		if(suffix==null)
			return null;
		return convertors.get(suffix);
	}
	
	public static void open(){
		for(Entry<String,Convertor> e:convertors.entrySet()){
			e.getValue().open();
		}
	}
	
	public static void close(){
		for(Entry<String,Convertor> e:convertors.entrySet()){
			e.getValue().close();
		}
	}
}
