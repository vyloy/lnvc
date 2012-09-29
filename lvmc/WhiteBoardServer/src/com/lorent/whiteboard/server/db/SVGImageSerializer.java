package com.lorent.whiteboard.server.db;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.jhotdraw.samples.svg.figures.SVGImage;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public class SVGImageSerializer extends Serializer<SVGImage> {

	@Override
	public void write(Kryo kryo, Output output, SVGImage object) {
		try {
			ObjectOutputStream stream = new ObjectOutputStream(output);
			stream.writeObject(object);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public SVGImage create(Kryo kryo, Input input, Class<SVGImage> type) {
		try {
			ObjectInputStream stream = new ObjectInputStream(input);
			return (SVGImage) stream.readObject();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

}
