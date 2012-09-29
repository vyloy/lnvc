package com.lorent.whiteboard.server.db;

import java.awt.geom.GeneralPath;
import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;

import org.jhotdraw.samples.svg.figures.SVGImage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;

public class DatabaseInputStream {

	private static final Logger logger = LoggerFactory
			.getLogger(DatabaseInputStream.class);
	
	private Kryo kryo=new Kryox();
	
	private Input input ;

	private BufferedInputStream bin;

	private FileInputStream in;
	
	public DatabaseInputStream(File file) {
		try{
			in = new FileInputStream(file);
		}catch(Exception e){
			logger.error("DatabaseInputStream.init error",e);
			throw new RuntimeException(e);
		}
		kryo.addDefaultSerializer(GeneralPath.class,new GeneralPathSerializer());
		kryo.addDefaultSerializer(SVGImage.class,new SVGImageSerializer());
		bin = new BufferedInputStream(in);
		input= new Input(bin);
	}
	
	public Object read() throws EOFException{
		try{
			return kryo.readClassAndObject(input);
		}catch(RuntimeException e){
			if(e.getMessage().equals("Buffer underflow."))
				throw new EOFException();
			else
				throw e;
		}
	}
	
	public void close(){
		input.close();
	}
}
