/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.lvmc.util;

import com.lorent.lvmc.controller.ControllerFacade;
import com.lorent.lvmc.dto.ListenerDto;
import com.lorent.lvmc.event.MyEvent;
import com.lorent.lvmc.event.XmlEventConfig;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

/**
 *
 * @author jack
 */
public class MessageUtil {

    private static MessageUtil instance;
    private boolean flag = true;
    private ArrayBlockingQueue<MyEvent> eventNewList = new ArrayBlockingQueue<MyEvent>(1000);
    private Logger log = Logger.getLogger(MessageUtil.class);
    
    private MessageUtil() {
        try{
            new XmlEventConfig().parseConfigFile();
            new DispatchEventThread().start();
        }catch(Exception e){
        	log.error("MessageUtil()", e);
        }
    }

    public static MessageUtil getInstance() {
        if (instance == null) {
            instance = new MessageUtil();
        }
        return instance;
    }

    public synchronized void sendMessage(MyEvent event) {
        eventNewList.offer(event);
    }
    
    public synchronized void sendMessage(String eventId, Object[] paras) {
    	sendMessage(eventId, paras, false);
    }
    
    public synchronized void sendMessage(String eventId, Object[] paras, boolean runInEDT) {
    	MyEvent event = new MyEvent();
    	event.setId(eventId);
    	event.setParas(paras);
    	event.setRunInEDT(runInEDT);
    	eventNewList.offer(event);
    }

    public void stop() {
        flag = false;
    }

    private class DispatchEventThread extends Thread {

        @Override
        public void run() {
            while (flag) {
                try {
                    MyEvent le = eventNewList.poll(1000, TimeUnit.MILLISECONDS);
                    //System.out.println(le);
                    if (le != null) {
                        doAction(le);
                    }

                } catch (InterruptedException ex) {
                	log.error("DispatchEventThread", ex);
                    ex.printStackTrace();
                }

            }
        }
    }

    public void doAction(final MyEvent le) {
        final List<ListenerDto> list = XmlEventConfig.getListeners(le.getId());
        if (list != null) {
        	if(le.isRunInEDT()){
        		SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
			            for (ListenerDto info : list) {
			                String className = info.getClassName();
			                String methodName = info.getMethodName();
			                ControllerFacade.execute(className, methodName, le.getParas());
			            }
					}
				});
        	}else{
	            for (ListenerDto info : list) {
	                String className = info.getClassName();
	                String methodName = info.getMethodName();
	                ControllerFacade.execute(className, methodName, le.getParas());
	            }
        	}

        }
    }
}
