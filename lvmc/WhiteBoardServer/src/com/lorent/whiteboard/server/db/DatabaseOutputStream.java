package com.lorent.whiteboard.server.db;

import java.awt.geom.GeneralPath;
import java.io.File;
import java.io.FileOutputStream;
import java.util.concurrent.ExecutorService;

import org.jhotdraw.samples.svg.figures.SVGImage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;

public class DatabaseOutputStream {

	private static final Logger logger = LoggerFactory
			.getLogger(DatabaseOutputStream.class);
	
	private final Kryo kryo = new Kryo();
	
	private FileOutputStream out;
	
	private Output output;
	
	private final File file;

	private final ExecutorService executor;


	public DatabaseOutputStream(File file, ExecutorService executor){
		if(file==null||executor==null)
			throw new IllegalArgumentException("file==null||executor == null!");
		try {
			out = new FileOutputStream(file,true);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		this.executor=executor;
		this.file=file;
		kryo.addDefaultSerializer(GeneralPath.class,new GeneralPathSerializer());
		kryo.addDefaultSerializer(SVGImage.class,new SVGImageSerializer());
		output=new Output(out);
	}
	
	public void write(final Object o){
		executor.execute(new Runnable() {
			public void run() {
				try{
					kryo.writeClassAndObject(output, o);
					output.flush();
					out.flush();
					out.getChannel().force(false);
				}catch(Exception e){
					logger.error("DatabaseOutputStream.write error",e);
				}
			}
		});
	}
	
	public void close(){
		executor.execute(new Runnable() {
			public void run() {
				try{
					out.getChannel().force(false);
					out.close();
				}catch(Exception e){
					logger.error("DatabaseOutputStream.close error",e);
				}
			}
		});
	}
	
	public void delete(){
		executor.execute(new Runnable() {
			public void run() {
				try{
					file.delete();
					logger.debug("Removed meeting file {}",file);
				}catch(Exception e){
					logger.error("DatabaseOutputStream.delete error",e);
				}
			}
		});
	}

	public FileOutputStream getOut() {
		return out;
	}

	public void setOut(FileOutputStream out, Output output) {
		this.out = out;
		this.output=output;
	}

	public Kryo getKryo() {
		return kryo;
	}

}
