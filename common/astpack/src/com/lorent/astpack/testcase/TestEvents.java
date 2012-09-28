package com.lorent.astpack.testcase;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.List;

import javax.sql.rowset.serial.SerialArray;

import org.asteriskjava.live.AsteriskChannel;
import org.asteriskjava.live.AsteriskQueue;
import org.asteriskjava.live.AsteriskQueueEntry;
import org.asteriskjava.live.AsteriskServerListener;
import org.asteriskjava.live.MeetMeRoom;
import org.asteriskjava.live.MeetMeUser;
import org.asteriskjava.live.internal.AsteriskAgentImpl;
import org.asteriskjava.live.internal.AsteriskServerImpl;
import org.asteriskjava.manager.ManagerConnection;
import org.asteriskjava.manager.ManagerConnectionFactory;
import org.asteriskjava.manager.TimeoutException;
import org.asteriskjava.manager.action.ExtensionStateAction;
import org.asteriskjava.manager.action.OriginateAction;
import org.asteriskjava.manager.action.QueueStatusAction;
import org.asteriskjava.manager.action.SipPeersAction;
import org.asteriskjava.manager.action.SipShowPeerAction;
import org.asteriskjava.manager.event.ManagerEvent;
import org.asteriskjava.manager.event.PeerEntryEvent;
import org.asteriskjava.manager.event.PeerStatusEvent;
import org.asteriskjava.manager.response.ExtensionStateResponse;
import org.asteriskjava.manager.response.ManagerResponse;
import org.asteriskjava.manager.response.SipShowPeerResponse;

public class TestEvents {

	public class myAsteriskServer extends AsteriskServerImpl {

		public myAsteriskServer(ManagerConnection createManagerConnection) {
			super(createManagerConnection);
		}

		@Override
		public void onManagerEvent(ManagerEvent event) {
			System.out.println("        onManagerEvent: " + event);

			if (event.getClass().getName()
					.equals("org.asteriskjava.manager.event.PeerStatusEvent")) {
				// PeerStatusEvent evt2 =new PeerStatusEvent(event);
				PeerStatusEvent evt2 = (PeerStatusEvent) event;
				System.out.println("        state: " + evt2.getPeerStatus());
			}
			super.onManagerEvent(event);
		}
	}

	private AsteriskServerImpl serverImpl;

	public TestEvents() {
		// super();
		ManagerConnectionFactory factory = new ManagerConnectionFactory(
		// "10.168.150.203", "manager", "123");
				// "192.168.125.133", "manager", "123");
				"10.168.100.202", "manager", "123");
		serverImpl = new myAsteriskServer(factory.createManagerConnection());
	}

	private String channel1 = "";
	private String channel2 = "";

	public void run() {

		serverImpl.addAsteriskServerListener(new AsteriskServerListener() {

			@Override
			public void onNewQueueEntry(AsteriskQueueEntry arg0) {
				System.out.println("        onNewQueueEntry: " + arg0);
			}

			@Override
			public void onNewMeetMeUser(MeetMeUser arg0) {
				System.out.println("        onNewMeetMeUser: " + arg0);
			}

			@Override
			public void onNewAsteriskChannel(AsteriskChannel arg0) {
				System.out.println("        onNewAsteriskChannel: " + arg0);
				System.out.println("        " + arg0.getName());

			}

			@Override
			public void onNewAgent(AsteriskAgentImpl arg0) {
				System.out.println("        onNewAgent: " + arg0);
			}
		});

		// add property change listeners to existing objects
		for (AsteriskChannel asteriskChannel : serverImpl.getChannels()) {
			System.out.println("        AsteriskChannel: " + asteriskChannel);
			asteriskChannel.addPropertyChangeListener(new PropertyChangeListener() {

						@Override
						public void propertyChange(PropertyChangeEvent evt) {
							System.out
									.println("        propertyChange: " + evt);
						}
					});
		}

		for (AsteriskQueue asteriskQueue : serverImpl.getQueues()) {
			System.out.println("        AsteriskQueue: " + asteriskQueue);
			for (AsteriskQueueEntry asteriskChannel : asteriskQueue
					.getEntries()) {
				asteriskChannel.addPropertyChangeListener(new PropertyChangeListener() {

							@Override
							public void propertyChange(PropertyChangeEvent evt) {
								System.out.println("        propertyChange: "
										+ evt);
							}
						});
			}
		}

		for (MeetMeRoom meetMeRoom : serverImpl.getMeetMeRooms()) {
			System.out.println("        MeetMeRoom: " + meetMeRoom);
			for (MeetMeUser user : meetMeRoom.getUsers()) {
				user.addPropertyChangeListener(new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						System.out.println("        propertyChange: " + evt);
					}
				});
			}
		}

		List<String> executeCliCommand = serverImpl
				.executeCliCommand("sip show peer 1771");
		for (String string : executeCliCommand) {
			 System.out.println(string);
		}
		
	    
		
		List<PeerEntryEvent> peerEntries = serverImpl.getPeerEntries();
		for (PeerEntryEvent peerEntryEvent : peerEntries) {
			System.out.println("        "+peerEntryEvent.getObjectName()+","+peerEntryEvent.getObjectUserName()+","+peerEntryEvent.getStatus()+"; :"+peerEntryEvent);
		}
		

		OriginateAction originateAction;
		ManagerResponse originateResponse;


		SipShowPeerAction sipShowPeerAction = new SipShowPeerAction();
		SipPeersAction action = new SipPeersAction();
		try {
			 ManagerResponse sendAction =
			 serverImpl.getManagerConnection().sendAction(action);
			 System.out.println(" SipPeersAction :"+sendAction);
			sipShowPeerAction.setPeer("1771");
			SipShowPeerResponse response = (SipShowPeerResponse) serverImpl
					.getManagerConnection().sendAction(sipShowPeerAction);
			System.out.println("1771 SipShowPeerAction: " + response);
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalStateException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (TimeoutException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			ExtensionStateAction extensionStateAction = new ExtensionStateAction();
			extensionStateAction.setExten("81060001");
			ExtensionStateResponse sendAction = (ExtensionStateResponse) serverImpl
					.getManagerConnection().sendAction(extensionStateAction);

			System.out.println(sendAction.getResponse());
			System.out.println(sendAction);
			System.out.println(sendAction.getAttribute("81060001 Status"));
			System.out.println(sendAction.getStatus());

			extensionStateAction.setExten("1069");
			extensionStateAction.setContext("default");
			sendAction = (ExtensionStateResponse) serverImpl
					.getManagerConnection().sendAction(extensionStateAction);

			System.out.println(sendAction.getResponse());
			System.out.println(sendAction);
			System.out.println(sendAction.getAttribute("1069 Status"));
			System.out.println(sendAction.getStatus());
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalStateException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (TimeoutException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		/*
		 * 
		 * try { originateResponse =
		 * serverImpl.getManagerConnection().sendAction(originateAction, 15000);
		 * System
		 * .out.println("        getResponse: "+originateResponse.getResponse
		 * ());
		 * System.out.println("        message: "+originateResponse.getMessage
		 * ());
		 * 
		 * //originateResponse =
		 * serverImpl.getManagerConnection().sendAction(originateAction2,15000);
		 * System
		 * .out.println("        getResponse: "+originateResponse.getResponse
		 * ());
		 * System.out.println("        message: "+originateResponse.getMessage
		 * ());
		 * 
		 * 
		 * } catch (IllegalArgumentException e1) { e1.printStackTrace(); } catch
		 * (IllegalStateException e1) { e1.printStackTrace(); } catch
		 * (IOException e1) { e1.printStackTrace(); } catch (TimeoutException
		 * e1) { e1.printStackTrace(); }
		 */

		while (true) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// serverImpl.getManagerConnection().logoff();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestEvents events = new TestEvents();
		events.run();
		System.out.println("        exit");
	}

}
