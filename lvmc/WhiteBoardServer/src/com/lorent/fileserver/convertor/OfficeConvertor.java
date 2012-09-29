package com.lorent.fileserver.convertor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OfficeConvertor extends ConvertorAdapter {
	private static final Logger logger = LoggerFactory.getLogger(OfficeConvertor.class);
	private static final OfficeManager officeManager = new DefaultOfficeManagerConfiguration().setRetryTimeout(60000).buildOfficeManager();
	private volatile boolean started=false;
	
	@Override
	public void open() {
		if(!started){
			started=true;
			officeManager.start();
		}
	}


	@Override
	public void close() {
		if(started){
			started=false;
			officeManager.stop();
		}
	}


	@Override
	public void convert(File file,Converted converted) throws IOException {
	    OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager);
	    File folder = file.getParentFile();
		if(folder==null){
			throw new FileNotFoundException("The folder of file is null!");
		}
		File outputFolder = new File(folder,file.getName()+".pdf");
		if(!outputFolder.exists()){
			outputFolder.mkdirs();
		}
	    File outputFile = new File(outputFolder,"converted.pdf");
	    if(!outputFile.exists())
	    	converter.convert(file, outputFile);
	    IcePdfConvertor.convert(outputFile, converted,file);
		logger.info("File {} exported.",file);
	}
	
}
