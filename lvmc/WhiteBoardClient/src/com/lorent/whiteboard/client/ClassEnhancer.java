package com.lorent.whiteboard.client;

import java.util.LinkedList;
import java.util.List;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import com.lorent.whiteboard.client.ClientEnhancer.BytesToClass;

class ClassEnhancer extends ClassVisitor implements Opcodes {

	private List<Data> list = new LinkedList<Data>();

	class Data {
		final int access;
		final String name;
		final String desc;
		final String signature;
		final String[] exceptions;

		public Data(int access, String name, String desc, String signature,
				String[] exceptions) {
			this.access = access;
			this.name = name;
			this.desc = desc;
			this.signature = signature;
			this.exceptions = exceptions;
		}

	}

	public ClassEnhancer(ClassVisitor cv) {
		super(ASM4, cv);
	}

	public LinkedList<Class<?>> create(BytesToClass bytesToClass) {
		LinkedList<Class<?>> classes = new LinkedList<Class<?>>();
		for (Data d : list) {
			Type[] args = createProxyMethod(d.access, d.name, d.desc,
					d.signature, d.exceptions);
			byte[] classBytes = createProxyClass(args, d.access, d.name,
					d.desc, d.signature, d.exceptions);
			Class<?> c = bytesToClass.defineClass(
					getProxyClassName(d.name, args,true).replaceAll("/", "\\."), classBytes);
			classes.add(c);
		}
		return classes;
	}

	public String getProxyClassName(String name, Type[] args,boolean skipFirst) {
		StringBuilder result=new StringBuilder("com/lorent/whiteboard/client/ClientMethodASM4");
		result.append('_');
		for(int i=skipFirst?1:0;i<args.length;i++){
			Type arg=args[i];
			result.append(arg.getClassName().replaceAll("\\.", "_"));
			result.append('_');
		}
		return  result.toString();
	}
	
	public String getProxyClassName(String name,Type[] args){
		return getProxyClassName(name,args,false);
	}

	@Override
	public void visit(int version, int access, String name, String signature,
			String superName, String[] interfaces) {
		super.visit(version, access, name+"ASM", signature, name, interfaces);
	}

	@Override
	public MethodVisitor visitMethod(final int access, final String name,
			final String desc, final String signature, final String[] exceptions) {
		if (cv == null)
			return null;
		if (name.startsWith("broadcast")) {
			list.add(new Data(access, name, desc, signature, exceptions));
		} else if (name.equals("<init>")) {
			createInitMethod();
		}
		return null;
	}

	private void createInitMethod(){
		MethodVisitor mv = cv.visitMethod(ACC_PUBLIC, "<init>", "()V", null,null);
		mv.visitVarInsn(ALOAD, 0);
		mv.visitMethodInsn(INVOKESPECIAL, "com/lorent/whiteboard/client/Client", "<init>", "()V");
		mv.visitVarInsn(ALOAD, 0);
		mv.visitTypeInsn(NEW,
				"com/lorent/whiteboard/client/BroadcastServiceThreadFactory");
		mv.visitInsn(DUP);
		mv.visitMethodInsn(INVOKESPECIAL,
				"com/lorent/whiteboard/client/BroadcastServiceThreadFactory",
				"<init>", "()V");
		mv.visitMethodInsn(INVOKESTATIC, "java/util/concurrent/Executors",
				"newCachedThreadPool",
				"(Ljava/util/concurrent/ThreadFactory;)Ljava/util/concurrent/ExecutorService;");
		mv.visitFieldInsn(PUTFIELD, "com/lorent/whiteboard/client/ClientASM",
				"_asm_broadcastService",
				"Ljava/util/concurrent/ExecutorService;");
		mv.visitInsn(RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();
	}
	
	private byte[] createProxyClass(Type[] args, int access, String name,
			String desc, String signature, String[] exceptions) {
		String owner = getProxyClassName(name, Type.getArgumentTypes(desc));
		ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
		writer.visit(Opcodes.V1_6, Opcodes.ACC_PUBLIC, owner, null,
				"java/lang/Object", new String[] { "java/lang/Runnable" });
		for (int i = 0; i < args.length; i++) {
			Type type = args[i];
			writer.visitField(0, "__asm__field__" + i, type.getDescriptor(),
					null, null).visitEnd();
		}
		MethodVisitor mv = writer.visitMethod(0, "<init>",
				Type.getMethodDescriptor(Type.VOID_TYPE, args), null, null);
		mv.visitVarInsn(ALOAD, 0);
		mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
		for (int i = 1; i <= args.length; i++) {
			Type type = args[i - 1];
			mv.visitVarInsn(ALOAD, 0);
			switch (type.getSort()) {
			case Type.OBJECT:
				mv.visitVarInsn(ALOAD, i);
				break;
			case Type.INT:
			case Type.BOOLEAN:
			case Type.BYTE:
			case Type.SHORT:
			case Type.CHAR:
				mv.visitVarInsn(ILOAD, i);
				break;
			case Type.LONG:
				mv.visitVarInsn(LLOAD, i);
				break;
			case Type.DOUBLE:
				mv.visitVarInsn(DLOAD, i);
				break;
			case Type.FLOAT:
				mv.visitVarInsn(FLOAD, i);
				break;
			}

			mv.visitFieldInsn(PUTFIELD, owner, "__asm__field__" + (i-1),
					type.getDescriptor());
		}
		mv.visitInsn(RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = writer.visitMethod(ACC_PUBLIC, "run", "()V", null, null);
		for (int i = 0; i <args.length; i++) {
			Type type = args[i];
			mv.visitVarInsn(ALOAD, 0);
			mv.visitFieldInsn(GETFIELD, owner, "__asm__field__" + (i),
					type.getDescriptor());
			if(i==0)
				mv.visitTypeInsn(CHECKCAST, "com/lorent/whiteboard/client/ClientASM");
		}
		mv.visitMethodInsn(INVOKEVIRTUAL,
				"com/lorent/whiteboard/client/ClientASM", "_super_"+name, desc);
		mv.visitInsn(RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();
		return writer.toByteArray();
	}

	private Type[] createProxyMethod(int access, String name, String desc,
			String signature, String[] exceptions) {
		MethodVisitor mv = cv.visitMethod(access, name, desc, signature,
				exceptions);
		mv.visitVarInsn(ALOAD, 0);
		mv.visitFieldInsn(GETFIELD, "com/lorent/whiteboard/client/ClientASM",
				"_asm_broadcastService",
				"Ljava/util/concurrent/ExecutorService;");

		Type[] types = Type.getArgumentTypes(desc);
		Type[] args = new Type[types.length + 1];
		String owner = getProxyClassName(name, types);
		mv.visitTypeInsn(NEW, owner);
		mv.visitInsn(DUP);
		mv.visitVarInsn(ALOAD, 0);
		args[0] = Type.getObjectType("com/lorent/whiteboard/client/Client");
		for (int i = 1; i <= types.length; i++) {
			Type type = types[i - 1];
			args[i] = type;
			switch (type.getSort()) {
			case Type.OBJECT:
				mv.visitVarInsn(ALOAD, i);
				break;
			case Type.INT:
			case Type.BOOLEAN:
			case Type.BYTE:
			case Type.SHORT:
			case Type.CHAR:
				mv.visitVarInsn(ILOAD, i);
				break;
			case Type.LONG:
				mv.visitVarInsn(LLOAD, i);
				break;
			case Type.DOUBLE:
				mv.visitVarInsn(DLOAD, i);
				break;
			case Type.FLOAT:
				mv.visitVarInsn(FLOAD, i);
				break;
			}
		}

		mv.visitMethodInsn(INVOKESPECIAL, owner, "<init>",
				Type.getMethodDescriptor(Type.VOID_TYPE, args));
		mv.visitMethodInsn(INVOKEINTERFACE,
				"java/util/concurrent/ExecutorService", "execute",
				"(Ljava/lang/Runnable;)V");
		mv.visitInsn(RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();
		
		
		mv = cv.visitMethod(ACC_PUBLIC, "_super_"+name, desc, signature,
				exceptions);
		mv.visitVarInsn(ALOAD, 0);
		for (int i = 1; i <= types.length; i++) {
			Type type = types[i - 1];
			args[i] = type;
			switch (type.getSort()) {
			case Type.OBJECT:
				mv.visitVarInsn(ALOAD, i);
				break;
			case Type.INT:
			case Type.BOOLEAN:
			case Type.BYTE:
			case Type.SHORT:
			case Type.CHAR:
				mv.visitVarInsn(ILOAD, i);
				break;
			case Type.LONG:
				mv.visitVarInsn(LLOAD, i);
				break;
			case Type.DOUBLE:
				mv.visitVarInsn(DLOAD, i);
				break;
			case Type.FLOAT:
				mv.visitVarInsn(FLOAD, i);
				break;
			}
		}
		mv.visitMethodInsn(INVOKESPECIAL, "com/lorent/whiteboard/client/Client", name, desc);
		mv.visitInsn(RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();
		return args;
	}
}