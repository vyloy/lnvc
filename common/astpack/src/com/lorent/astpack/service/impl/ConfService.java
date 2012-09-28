/**
 * 
 */
package com.lorent.astpack.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.asteriskjava.fastagi.command.AnswerCommand;
import org.asteriskjava.live.AsteriskChannel;
import org.asteriskjava.live.AsteriskQueue;
import org.asteriskjava.live.AsteriskQueueEntry;
import org.asteriskjava.live.AsteriskServerListener;
import org.asteriskjava.live.ChannelState;
import org.asteriskjava.live.MeetMeUser;
import org.asteriskjava.live.internal.AsteriskAgentImpl;
import org.asteriskjava.live.internal.AsteriskServerImpl;
import org.asteriskjava.manager.ManagerConnection;
import org.asteriskjava.manager.ManagerConnectionFactory;
import org.asteriskjava.manager.SendActionCallback;
import org.asteriskjava.manager.action.AgiAction;
import org.asteriskjava.manager.action.CommandAction;
import org.asteriskjava.manager.action.CoreShowChannelsAction;
import org.asteriskjava.manager.action.ExtensionStateAction;
import org.asteriskjava.manager.action.HangupAction;
import org.asteriskjava.manager.action.ListCommandsAction;
import org.asteriskjava.manager.action.OriginateAction;
import org.asteriskjava.manager.action.RedirectAction;
import org.asteriskjava.manager.action.StatusAction;
import org.asteriskjava.manager.event.CoreShowChannelEvent;
import org.asteriskjava.manager.event.ManagerEvent;
import org.asteriskjava.manager.event.NewChannelEvent;
import org.asteriskjava.manager.event.PeerEntryEvent;
import org.asteriskjava.manager.event.PeerStatusEvent;
import org.asteriskjava.manager.response.CommandResponse;
import org.asteriskjava.manager.response.ExtensionStateResponse;
import org.asteriskjava.manager.response.ManagerResponse;

import com.lorent.astpack.service.IConfService;
import com.lorent.astpack.service.resources.PropertiesUtil;


public class ConfService implements IConfService {

	private static String mMcuUser = "lntmcu";
	private Logger log = Logger.getLogger(ConfService.class);
	private ManagerConnectionFactory mManagerConnectionFactory;
	private AsteriskServerImpl mAsteriskServerImpl;
	private Map<String, CoreShowChannelEvent> mCoreShowChannelEventMap;
	
	public class myAsteriskServer extends AsteriskServerImpl{

		public myAsteriskServer(ManagerConnection createManagerConnection) {
			super(createManagerConnection);
		}

		@Override
		public void onManagerEvent(ManagerEvent event) {
			log.debug("        onManagerEvent: "+event);				
			
			if (event.getClass().getName().equals("org.asteriskjava.manager.event.CoreShowChannelEvent")) {
				CoreShowChannelEvent event2 = (CoreShowChannelEvent) event;
				mCoreShowChannelEventMap.put(event2.getChannel(), event2);
			}
			
			if (event.getClass().getName().equals("org.asteriskjava.manager.event.NewChannelEvent")){
				NewChannelEvent evt2 = (NewChannelEvent) event;

			}
			if (event.getClass().getName().equals("org.asteriskjava.manager.event.PeerStatusEvent")) {
				PeerStatusEvent evt2 = (PeerStatusEvent) event;
			}
			super.onManagerEvent(event);
		}
	}
	
//	private void initConst() {
//		mIpAddr = PropertiesUtil.getConstant("IpAddr");
//		mManagerName = PropertiesUtil.getConstant("ManagerName");
//		mManagerPsw = PropertiesUtil.getConstant("ManagerPsw");
//		mMcuUser = PropertiesUtil.getConstant("McuUser");
//	}
	
	public ConfService(String ipAddr,int port, String managerName,String managerPsw){
		mCoreShowChannelEventMap = new HashMap<String, CoreShowChannelEvent>();
		mManagerConnectionFactory = new ManagerConnectionFactory(ipAddr,port,managerName,managerPsw);
		mAsteriskServerImpl = new myAsteriskServer(mManagerConnectionFactory.createManagerConnection());

		mAsteriskServerImpl.addAsteriskServerListener(new AsteriskServerListener() {
			
			@Override
			public void onNewQueueEntry(AsteriskQueueEntry entry) {
				log.info("onNewQueueEntry: "+entry);
			}
			
			@Override
			public void onNewMeetMeUser(MeetMeUser user) {
				log.info("onNewMeetMeUser: "+user);
			}
			
			@Override
			public void onNewAsteriskChannel(AsteriskChannel channel) {
				log.info("onNewAsteriskChannel: "+channel);
				//System.out.println("onNewAsteriskChannel: getVariable"+channel.getVariable("mp4playmedia"));
			}
			
			@Override
			public void onNewAgent(AsteriskAgentImpl agent) {
				log.info("onNewAgent: "+agent);
			}
		});
		log.info("ConfService()");
	}
	
//	public ConfService(String ipAddr){
//		this(ipAddr,mManagerName,mManagerPsw);
//	}
//	
//	public ConfService() {
//		this(mIpAddr, mManagerName, mManagerPsw);
//	}

	/* (non-Javadoc)
	 * @see com.lorent.astpack.service.IConfService#addUsersInConf(java.lang.String, java.lang.String[])
	 */
	@Override
	public Map addUsersInConf(String confno, String[] lccnos) throws Exception {
		Map map = new HashMap();
		for (int i = 0; i < lccnos.length; i++) {
			OriginateAction action = new OriginateAction();
			action.setChannel("SIP/"+lccnos[i]);
			action.setExten(confno);
			action.setPriority(new Integer(1));
			action.setTimeout(15000);
			action.setAsync(true);
//			action.setCallerId(lccnos[i]);
			action.setCallerId(confno);
			try {
				ManagerResponse originateResponse = mAsteriskServerImpl.getManagerConnection().sendAction(action, 15000);
				System.out.println(originateResponse.getResponse());
				
				System.out.println(originateResponse);
				//System.out.println(originateResponse.getEventList());
			} catch (Exception e) {
				e.printStackTrace();
				//throw e;
			}
		}
		return map;
	}
	
	@Override
	protected void finalize() throws Throwable {
		mAsteriskServerImpl.getManagerConnection().logoff();
		System.out.println("ConfService finalize");
		super.finalize();
	}

	/* (non-Javadoc)
	 * @see com.lorent.astpack.service.IConfService#playMediaInConf(java.lang.String, java.lang.String, boolean)
	 */
	@Override
	public Map playMediaInConf(String confno, String mediaPath, boolean play)
			throws Exception {
		Map map = new HashMap();
		if (play) {
			
			OriginateAction action = new OriginateAction();
			action.setChannel("SIP/"+confno+"@"+mMcuUser);
			action.setApplication("mp4play");
			action.setData(mediaPath);
			action.setPriority(new Integer(1));
			action.setTimeout(15000);
			//action.setCallerId(mediaPath);
			action.setVariable("mp4playmedia", mediaPath);
			action.setVariable("confno",confno);
			try {
				ManagerResponse originateResponse = mAsteriskServerImpl.getManagerConnection().sendAction(action, 15000);
				//System.out.println(originateResponse.getResponse());
				//System.out.println(originateResponse.getMessage());
				//System.out.println(originateResponse.getEventList());
				//System.out.println(originateResponse.getUniqueId());
				
				//mMediaPathMap.put(originateResponse.getUniqueId(), mediaPath);
				//AsteriskChannel channelById = mAsteriskServerImpl.getChannelById(originateResponse.getUniqueId());
				//channelById.setVariable("MediaPath", mediaPath);
				//System.out.println(channelById);
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}else {
			
			Collection<AsteriskChannel> channels = mAsteriskServerImpl.getChannels();
			String channelName = "";
			double dTemp = 0.0;
			for (AsteriskChannel asteriskChannel : channels) {
				if (asteriskChannel.getVariable("mp4playmedia").equals(mediaPath) && asteriskChannel.getVariable("confno").equals(confno)) {
					if (Double.parseDouble(asteriskChannel.getId()) > dTemp) {
						dTemp = Double.parseDouble(asteriskChannel.getId());
						channelName = asteriskChannel.getName();
					}
				}
			}
			if (!channelName.equals("")) {
				HangupAction action = new HangupAction();
				
				action.setChannel(channelName);
				try {
					ManagerResponse originateResponse = mAsteriskServerImpl.getManagerConnection().sendAction(action, 15000);
				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				}
			}
		}
		
		return map;
	}

	/* (non-Javadoc)
	 * @see com.lorent.astpack.service.IConfService#joinExtenSpy(java.lang.String, java.lang.String)
	 */
	@Override
	public Map joinExtenSpy(String lccnofrom, String lccnoto,String prefix) throws Exception {
		//判断lccnoto是否在会议中
		if (isLccInConf(lccnoto)) {
			OriginateAction action = new OriginateAction();
			AsteriskChannel confChannel = getConfChannel(lccnoto);
			System.out.println(confChannel.getCallerId().getNumber()+" , "+confChannel);
			action.setChannel("SIP/"+lccnofrom);
			action.setExten(confChannel.getCallerId().getNumber());
			action.setPriority(new Integer(1));
			action.setTimeout(15000);
			try {
				ManagerResponse originateResponse = mAsteriskServerImpl.getManagerConnection().sendAction(action, 15000);
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
			
		}else {
			
			OriginateAction action = new OriginateAction();
			
			//action.setApplication("ExtenSpy");
			//action.setData(lccnoto+",Bq");
			action.setChannel("SIP/"+lccnofrom);
			action.setExten(prefix+lccnoto);
			action.setPriority(new Integer(1));
			action.setTimeout(15000);
			try {
				ManagerResponse originateResponse = mAsteriskServerImpl.getManagerConnection().sendAction(action, 15000);
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}

		}
		return null;
	}
	
	
	/* (non-Javadoc)
	 * @see com.lorent.astpack.service.IConfService#forceDisconnect(java.lang.String)
	 */
	@Override
	public Map forceDisconnect(String lccno) throws Exception {
		log.info("forceDisconnect: "+lccno);
		AsteriskChannel channel = getLastChannel(lccno);
		if (channel != null) {
			HangupAction action = new HangupAction();
			
			action.setChannel(channel.getName());
			try {
				ManagerResponse originateResponse = mAsteriskServerImpl.getManagerConnection().sendAction(action, 15000);
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}
		return null;
	}
	
	

	/* (non-Javadoc)
	 * @see com.lorent.astpack.service.IConfService#listenExtenSpy(java.lang.String)
	 */
	@Override
	public Map listenExtenSpy(String lccno,String targetlccno,String prefix) throws Exception {
		
		log.info("listenExtenSpy from:"+lccno+" , to:"+targetlccno+" , prefix:"+prefix);
		if (isLccInConf(targetlccno)) {
			AsteriskChannel confChannel = getConfChannel(targetlccno);
			/*
			OriginateAction action = new OriginateAction();
			System.out.println(confChannel.getCallerId().getNumber()+" , "+confChannel);
			action.setChannel("SIP/"+lccno);
			action.setExten(confChannel.getCallerId().getNumber());
			action.setPriority(new Integer(1));
			action.setTimeout(15000);
			try {
				ManagerResponse originateResponse = mAsteriskServerImpl.getManagerConnection().sendAction(action, 15000);
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
			*/
			call(lccno, confChannel.getCallerId().getNumber());
			System.out.println(confChannel);
		}else {
			/*
			OriginateAction action = new OriginateAction();
			action.setChannel("SIP/"+lccno);
			action.setExten("88888"+targetlccno);
			action.setPriority(new Integer(1));
			action.setTimeout(15000);
			try {
				ManagerResponse originateResponse = mAsteriskServerImpl.getManagerConnection().sendAction(action, 15000);
				System.out.println(originateResponse.getResponse());
				System.out.println(originateResponse.getMessage());
				System.out.println(originateResponse.getEventList());
				System.out.println(originateResponse.getUniqueId());
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
			*/
			call(lccno, prefix+targetlccno);
		}
		
		
//		System.out.println("listenExtenSpy");
		return null;
	}

	
	
	/* (non-Javadoc)
	 * @see com.lorent.astpack.service.IConfService#call(java.lang.String, java.lang.String)
	 */
	@Override
	public Map call(String lccnoFrom, String lccnoTo) throws Exception {
		log.info("call "+lccnoFrom+" to "+lccnoTo);
		OriginateAction action = new OriginateAction();
		action.setChannel("SIP/"+lccnoFrom);
		action.setExten(lccnoTo);
		action.setPriority(new Integer(1));
//		action.setContext("default");
//		action.setTimeout(15000);
		try {
			ManagerResponse originateResponse = mAsteriskServerImpl.getManagerConnection().sendAction(action, 15000);
			log.debug("ManagerResponse: "+originateResponse.getResponse());
			log.debug(""+originateResponse);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.lorent.astpack.service.IConfService#callAsync(java.lang.String, java.lang.String)
	 */
//	@Override
	public Map callAsync(String lccnoFrom, String lccnoTo) throws Exception {
		log.info("call "+lccnoFrom+" to "+lccnoTo);
		OriginateAction action = new OriginateAction();
		action.setChannel("SIP/"+lccnoFrom);
		action.setExten(lccnoTo);
		action.setPriority(new Integer(1));
		try {
			ManagerResponse originateResponse = mAsteriskServerImpl.getManagerConnection().sendAction(action);
			log.debug("ManagerResponse: "+originateResponse.getResponse());
			log.debug(""+originateResponse);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return null;
	}

	/* (non-Javadoc)
	 * @see com.lorent.astpack.service.IConfService#answer(java.lang.String, java.lang.String)
	 */
	@Override
	public Map answer(String lccnoHost, String lccnoBy) throws Exception {
		log.info("answer "+lccnoHost+" answer "+lccnoBy);
		CoreShowChannelsAction coreShowChannelsAction = new CoreShowChannelsAction();
		mCoreShowChannelEventMap.clear();
		try {
			coreShowChannelsAction.setActionId("answer_action");
			ManagerResponse originateResponse = mAsteriskServerImpl.getManagerConnection().sendAction(coreShowChannelsAction, 15000);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		Thread.sleep(500);
		//判断是否正在呼叫
		String targetChannel = "";
		//System.out.println("lccnoHost:"+lccnoHost+" lccnoBy:"+lccnoBy);
		for (CoreShowChannelEvent element : mCoreShowChannelEventMap.values()) {
			log.info(""+element.getChannelstatedesc()+"  ,"+element.getExtension()+" , "+"CoreShowChannelEvent:"+element);
			if (element.getChannelstatedesc().equals("Ring") && element.getExtension().equals(lccnoBy)) {
				targetChannel = element.getChannel();
				log.info("targetChannel:"+targetChannel);
			}
		}
		if (!targetChannel.equals("")) {//非会议
			RedirectAction action = new RedirectAction();
			//action.setChannel("SIP/"+lccnoBy);//imcomming channel
			action.setChannel(targetChannel);
			action.setExten(lccnoHost);
			action.setPriority(new Integer(1));
			
			try {
				ManagerResponse originateResponse = mAsteriskServerImpl.getManagerConnection().sendAction(action, 15000);
				//System.out.println("1 "+originateResponse.getResponse());
				//System.out.println("2 "+originateResponse.getMessage());
				//System.out.println("3 "+originateResponse);
				//System.out.println("4 "+originateResponse.getEventList());
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}
		//System.out.println("answer...");
		return null;
	}
	
	

	/* (non-Javadoc)
	 * @see com.lorent.astpack.service.IConfService#mute(java.lang.String, java.lang.String)
	 */
	@Override
	public Map meetMeMute(String confno, String lccno) throws Exception {
		// TODO Auto-generated method stub
		
		CommandAction commandAction = new CommandAction("konf list ");
		CommandResponse response = (CommandResponse) mAsteriskServerImpl.getManagerConnection().sendAction(commandAction);
		for (String line : response.getResult()){
		     System.out.println(line);
		}
		
		ListCommandsAction listCommandsAction = new ListCommandsAction();
		try {
			ManagerResponse originateResponse = mAsteriskServerImpl.getManagerConnection().sendAction(listCommandsAction, 15000);
			//System.out.println("1 "+originateResponse.getResponse());
			//System.out.println("2 "+originateResponse.getMessage());
			//System.out.println("3 "+originateResponse);
			//System.out.println("4 "+originateResponse.getEventList());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return null;
	}

	/**
	 * 该号码是否在会议中
	 * @param lccno
	 * @return true:在会议中,false:非会议中
	 */
	private boolean isLccInConf(String lccno){
		if (getConfChannel(lccno)!=null) {
			return true;
		}else {
			return false;
		}
	}

	
	/**
	 * 获得LCC号码所在会议中的Channel
	 * @param lccno
	 * @return
	 */
	private AsteriskChannel getConfChannel(String lccno) {
		Collection<AsteriskChannel> channels = mAsteriskServerImpl.getChannels();
		for (AsteriskChannel asteriskChannel : channels) {
			//System.out.println(asteriskChannel);
			
			AsteriskChannel linkedChannel = asteriskChannel.getLinkedChannel();
			if (linkedChannel != null) {
				if (linkedChannel.getName().startsWith("SIP/"+mMcuUser) && asteriskChannel.getName().startsWith("SIP/"+lccno)) {
					//System.out.println("getConfChannel find!  in conf !");
					return linkedChannel;
				}
			}
		}
		return null;
	}
	
	/**
	 * 获得该LCC号码最近创建的channel
	 * @param lccno
	 * @return
	 */
	private AsteriskChannel getLastChannel(String lccno){
		Collection<AsteriskChannel> channels = mAsteriskServerImpl.getChannels();
		
		
		AsteriskChannel asteriskChannel = null;
		double dTemp = 0.0;
		for (AsteriskChannel channeltmp : channels) {
//			System.out.println("getLastChannel channel:"+channeltmp);
			if (channeltmp.getName().startsWith("SIP/"+lccno)) {
				if (Double.parseDouble(channeltmp.getId()) > dTemp) {
					dTemp = Double.parseDouble(channeltmp.getId());
					asteriskChannel = channeltmp;
					//System.out.println("getChannel find! ");
				}
			}
		}
		return asteriskChannel;
		
	}
	
	private PeerEntryEvent getPeerEntryEvent(String lccno) {
		List<PeerEntryEvent> peerEntries = mAsteriskServerImpl.getPeerEntries();
		for (PeerEntryEvent peerEntryEvent : peerEntries) {
			if (peerEntryEvent.getObjectName().equals(lccno)) {
				return peerEntryEvent;
			}
		}
		return null;
	}
	
	
	private Map<String, String> getPeerProperties(String lccno) {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		List<String> executeCliCommand = mAsteriskServerImpl.executeCliCommand("sip show peer "+lccno);
		for (String tempstr: executeCliCommand) {
			Pattern keyCompile = Pattern.compile("\\w*\\s*.*:");
			Matcher keyMatcher = keyCompile.matcher(tempstr);
			Pattern valueCompile = Pattern.compile(":.*\\w*");
			Matcher valueMatcher = valueCompile.matcher(tempstr);
			if (keyMatcher.find() && valueMatcher.find()) {
				String findkey = keyMatcher.group().replace(":", "").trim();
				String findValue = valueMatcher.group().replace(":", "").trim();
				hashMap.put(findkey, findValue);
			}
		}
		return hashMap;
	}
	
	public String getPeerProperty(String lccno,String key){
		Map<String, String> peerProperties = getPeerProperties(lccno);
		return peerProperties.get(key);
	}

	/* (non-Javadoc)
	 * @see com.lorent.astpack.service.IConfService#getPeerStauts(java.lang.String)
	 */
	@Override
	public Map getPeerStatus(String lccno) throws Exception {
		log.info("getPeerStauts "+lccno);
		HashMap hashMap = new HashMap();

		AsteriskChannel lastChannel = getLastChannel(lccno);
		if (lastChannel != null) {
			ChannelState state = lastChannel.getState();
			if (state == ChannelState.UP) {
				hashMap.put("Status", com.lorent.astpack.util.Constants.PEER_STATUS_INUSE);
			}else if(state == ChannelState.RING){
				hashMap.put("Status", com.lorent.astpack.util.Constants.PEER_STATUS_RING);
			}else if(state == ChannelState.RINGING){
				hashMap.put("Stauts", com.lorent.astpack.util.Constants.PEER_STATUS_RINGING);
			}else{
				hashMap.put("Status", com.lorent.astpack.util.Constants.PEER_STATUS_UNKNOWN);
			}
		}
		else{
			if (getPeerEntryEvent(lccno) != null) {
				if (getPeerProperty(lccno,"Expire").equals("-1")) {
					hashMap.put("Status", com.lorent.astpack.util.Constants.PEER_STATUS_UNAVAILABLE);
				}
				else{
					hashMap.put("Status", com.lorent.astpack.util.Constants.PEER_STATUS_NOT_INUSE);
				}
			}
			else{
				hashMap.put("Status", com.lorent.astpack.util.Constants.PEER_STATUS_UNAVAILABLE);
			}
		}
		log.info(lccno+" status:"+hashMap.get("Status"));
		return hashMap;
	}
	
}
