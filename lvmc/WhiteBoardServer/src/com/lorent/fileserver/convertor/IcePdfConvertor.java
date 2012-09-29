package com.lorent.fileserver.convertor;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.icepdf.core.pobjects.Document;
import org.icepdf.core.pobjects.Page;
import org.icepdf.core.util.GraphicsRenderingHints;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IcePdfConvertor extends ConvertorAdapter{
	private static final Logger logger = LoggerFactory
			.getLogger(IcePdfConvertor.class);
	
	@Override
	public void convert(File file,Converted converted) throws IOException {
		convert(file, converted, file);
	}
	
	public static void convert(File file,Converted converted,File source) throws IOException {
		Document doc = new Document();
		try {
			doc.setFile(file.getCanonicalPath());
		} catch (Exception e) {
			throw new IOException(e);
		}
		
		int pagecount = doc.getNumberOfPages();
		
		logger.info("File {} has {} pages.",file,pagecount);
		File folder = file.getParentFile();
		if(folder==null){
			throw new FileNotFoundException("The folder of file is null!");
		}
		File outputFolder = new File(folder,file.getName()+".jpgs");
		if(!outputFolder.exists()){
			outputFolder.mkdirs();
		}
		logger.info("Jpgs will export to folder {}",outputFolder);
		
		float scale = 1.4f;
		float rotation = 0f;
		boolean first=true;
		for (int i = 0; i < doc.getNumberOfPages(); i++) {
			String outputPath = outputFolder.getPath() + File.separator
					+ file.getName() + "_" + pagecount + "_" + (i + 1) + ".jpg";
			File outputFile = new File(outputPath);
			BufferedImage image=null;
			if(!outputFile.exists()){
				image = (BufferedImage)
						doc.getPageImage(i,
								GraphicsRenderingHints.SCREEN,
								Page.BOUNDARY_CROPBOX, rotation, scale);
				ImageIO.write(image, "jpeg", outputFile);
			}
			if(first){
				first=false;
				if(image==null){
					image = (BufferedImage)
							doc.getPageImage(i,
									GraphicsRenderingHints.SCREEN,
									Page.BOUNDARY_CROPBOX, rotation, scale);
				}
				converted.converted(image,source,1,doc.getNumberOfPages());
			}
		}
	}
}
