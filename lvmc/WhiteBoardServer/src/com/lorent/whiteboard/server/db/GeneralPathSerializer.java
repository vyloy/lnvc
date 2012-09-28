package com.lorent.whiteboard.server.db;

import java.awt.geom.GeneralPath;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public class GeneralPathSerializer extends Serializer<GeneralPath> {

	@Override
	public void write(Kryo kryo, Output output, GeneralPath object) {
		try {
			ObjectOutputStream stream = new ObjectOutputStream(output);
			stream.writeObject(object);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public GeneralPath create(Kryo kryo, Input input, Class<GeneralPath> type) {
		try {
			ObjectInputStream stream = new ObjectInputStream(input);
			return (GeneralPath) stream.readObject();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

}
