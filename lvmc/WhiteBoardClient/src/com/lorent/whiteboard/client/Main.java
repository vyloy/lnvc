package com.lorent.whiteboard.client;

import java.net.UnknownHostException;

public class Main {
	public static void main(String[] args) throws UnknownHostException {
		if(args.length<2||args.length>3){
			printExample();
			return;
		}
		try{
			if("create".equalsIgnoreCase(args[0])){
				Client.create(args[1], args[2]);
			}else if("remove".equalsIgnoreCase(args[0])){
				Client.remove(args[1], args[2]);
			}else if("shutdown".equalsIgnoreCase(args[0])){
				Client.shutdownServer(args[1]);
			}else if("query".equalsIgnoreCase(args[0])){
				Client.getMeetingIds(args[1]);
			}
		}catch(RuntimeException e){
			e.printStackTrace();
			printExample();
		}
	}
	
	public static void printExample(){
		System.out.println("option serverIp meetingId");
		System.out.println("example:");
		System.out.println("create 127.0.0.1 111");
		System.out.println("remove 127.0.0.1 111");
		System.out.println("shutdown 127.0.0.1");
	}
}
