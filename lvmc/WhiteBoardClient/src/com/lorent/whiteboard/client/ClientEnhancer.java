package com.lorent.whiteboard.client;

import java.io.IOException;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

public class ClientEnhancer {
	@SuppressWarnings("unchecked")
	public static Class<Client> enhancedClient(){
		ClassReader classReader;
		try {
			classReader = new ClassReader(
					"com.lorent.whiteboard.client.Client");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		ClassWriter w = new ClassWriter(classReader, ClassWriter.COMPUTE_FRAMES);
		ClassEnhancer r = new ClassEnhancer(w);
		classReader.accept(r, 0);
		w.visitField(Opcodes.ACC_PRIVATE | Opcodes.ACC_FINAL,
				"_asm_broadcastService",
				"Ljava/util/concurrent/ExecutorService;", null, null).visitEnd();
		BytesToClass bytesToClass = new BytesToClass();
		r.create(bytesToClass);
		byte[] result = w.toByteArray();
		return (Class<Client>) bytesToClass.defineClass("com.lorent.whiteboard.client.ClientASM", result);
	}
	
	static class BytesToClass extends ClassLoader{
		
		public BytesToClass() {
			super();
		}

		public BytesToClass(ClassLoader parent) {
			super(parent);
		}

		public Class<?> defineClass(String name,byte[] b){
			return defineClass(name,b,0,b.length);
		}
	}
}
