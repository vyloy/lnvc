/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.common.manager;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.lorent.common.event.EventHelper;
import com.lorent.common.event.Listener;
import com.lorent.common.event.MyEvent;

/**
 *
 * @author jack
 */
public class MessageManager extends BaseManager{

    private boolean flag = true;
    private ArrayBlockingQueue<MyEvent> eventNewList = new ArrayBlockingQueue<MyEvent>(5000);
    private Logger log = Logger.getLogger(MessageManager.class);
    private Map<String, List<Listener>> listeners;
    
    public MessageManager() {
    	
    }

    public void init(String filePath) {
        try{
            listeners = new EventHelper().parseConfigFile(filePath);
            new DispatchEventThread().start();
        }catch(Exception e){
        	log.error("MessageUtil()", e);
        }
	}

	public synchronized void sendMessage(MyEvent event) {
        eventNewList.offer(event);
    }
    
    public synchronized void sendMessage(String eventId, Object[] paras) {
        MyEvent event = new MyEvent();
        event.setId(eventId);
        event.setParas(paras);
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

    public void doAction(MyEvent le) {
        List<Listener> list = listeners.get(le.getId());
        if (list != null) {
            for (Listener info : list) {
                String className = info.getClassName();
                String methodName = info.getMethodName();
                context.getExecuteManager().executeController(className, methodName, le.getParas());
            }
        }
    }
}
