package com.lorent.vovo;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smackx.filetransfer.FileTransfer;
import org.jivesoftware.smackx.filetransfer.FileTransferRequest;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;

import com.jhlabs.image.GaussianFilter;
import com.jtattoo.plaf.mcwin.McWinLookAndFeel;
import com.lorent.common.app.BaseApplication;
import com.lorent.common.event.MyEvent;
import com.lorent.common.manager.ConfigManager;
import com.lorent.common.manager.ViewManager;
import com.lorent.common.permission.NoPermissionException;
import com.lorent.common.util.LCMUtil;
import com.lorent.common.util.ProcessUtil;
import com.lorent.common.util.StringUtil;
import com.lorent.util.LCCUtil;
import com.lorent.util.LCCUtil.Device;
import com.lorent.vovo.dto.LoginInfo;
import com.lorent.vovo.ui.MessageTabPanel;
import com.lorent.vovo.util.Constants;
import com.lorent.vovo.util.DataUtil;
import com.lorent.vovo.util.Constants.DataKey;


public class Vovo extends BaseApplication{
	
//	private static Vovo instance;
//	public static Vovo getInstance(){
//		return instance;
//	}
//	
	public static VovoContext getMyContext(){
		return (VovoContext) getContext(Vovo.class);
	}

	@Override
	protected void init() throws Exception {
		super.init();	
	}
	
	public static String getBuild(){
		return getMyContext().getConfigManager().getProperty("build", "");
	}
	
	@Override
	protected void beforeStart()throws Exception{
        String javahome = System.getProperty("java.home");
        log.info("javahome:" + javahome);
		BufferedImage bufimg = ImageIO.read(MessageTabPanel.class.getResource("/com/lorent/vovo/resource/images/lvd1600_sip_back.png"));
		setValue(Constants.DataKey.BACKGROUND_IMAGE.toString(), bufimg);
		GaussianFilter gaussianFilter = new GaussianFilter(8.5f);
		BufferedImage resultImage = gaussianFilter.filter(bufimg, bufimg);
		setValue(Constants.DataKey.BACKGROUND_GAUSSIAN_IMAGE.toString(), resultImage);
		BufferedImage whiteimg = ImageIO.read(getClass().getResource("/com/lorent/vovo/resource/images/whitealpha.png"));
		setValue(Constants.DataKey.WHITE_IMAGE.toString(), whiteimg);
		BufferedImage whiteimgx = ImageIO.read(getClass().getResource("/com/lorent/vovo/resource/images/whitealphax.png"));
		setValue(Constants.DataKey.WHITE_IMAGE_X.toString(), whiteimgx);
		//load system emotion
		loadSystemEmotion();
		//init data
		DataUtil.setValue(DataKey.FRIEND_CHAT, new HashMap<String, Chat>());
		DataUtil.setValue(DataKey.FileTransfer, new HashMap<String, FileTransfer>());
		DataUtil.setValue(DataKey.FileTransferRequest, new HashMap<String, FileTransferRequest>());
		DataUtil.setValue(DataKey.NotReadMsg, new HashMap<String, List<Object[]>>());
	}
	
	private void loadSystemEmotion(){
		Map<String, ImageIcon> map = new HashMap<String, ImageIcon>();
		for (int i = 0; i < 1000; i++) {
			String filename = "e" + i ;
			URL url = this.getClass().getResource(Constants.SYSTEM_EMOTION_PATH + filename + ".gif");
			if (url == null) {
				break;
			} else {
				ImageIcon icon = new ImageIcon(url);
				map.put(filename, icon);
			}
		}
		setValue(Constants.DataKey.SYSTEM_EMOTION.toString(), map);
	}
	
	
	public static void main(String[] args) {
		Vovo instance = new Vovo();
		instance.setContext(new VovoContext());
		instance.execute();
	}
	
	@Override
	protected String getContextPath() {
		return "classpath:com/lorent/vovo/config/applicationContext-*.xml";
	}

	@Override
	protected String getFilterPath() {
		return "/com/lorent/vovo/config/filter_conf.xml";
	}

	@Override
	protected String getListenerPath() {
		return "/com/lorent/vovo/config/event_listener.xml";
	}
	
	@Override
	protected String getI18nPath() {
		return "com/lorent/vovo/resource/i18n/view";
	}

	@Override
	protected String getConfigFilePath() {
		log.info("Vovo config file : "+Constants.CONFIG_PATH);
		log.info("java.library.path: "+System.getProperty("java.library.path"));
//		LCCUtil.getInstance();
		return Constants.CONFIG_PATH;
//		return "c:\\vovo.conf";
	}

	@Override
	protected void startApp() {
		/*
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DATE);
		if (year >= 2013 && month >= 1) {
			JOptionPane.showMessageDialog(null, "超过期限");
			System.exit(0);
		}
		
		List<Device> localMicList = LCCUtil.getInstance().getLocalMicList();
		for (Device device : localMicList) {
			log.info("localMicList: "+device.getIndex()+","+device.getName());
		}
		List<Device> localPlayBackList = LCCUtil.getInstance().getLocalPlayBackList();
		for (Device device : localPlayBackList) {
			log.info("localPlayBackList: "+device.getIndex()+","+device.getName());
		}
		int localMicIndex = Vovo.getConfigManager().getIntProperty("localMicIndex", 0);
		int localPlayBackIndex = Vovo.getConfigManager().getIntProperty("localPlayBackIndex", 0);
		LCCUtil.getInstance().setMic(localMicIndex);
		LCCUtil.getInstance().setPlayBack(localPlayBackIndex);
		
		new Thread(){

			@Override
			public void run() {
				try {
					String cmdstr = "cmd /c "+StringUtil.convertFilePath2DOSCommandStr(Constants.USER_DIR+"\\processwatch.exe");
					ProcessUtil.getInstance().startProcess(cmdstr);
					log.info(cmdstr);
				} catch (Exception e) {
					log.error("startApp", e);
					e.printStackTrace();
				}
			}
			
		}.start();
		*/
		NativeInterface.open();
		try {
			 UIManager.setLookAndFeel(new McWinLookAndFeel());
//			UIManager.setLookAndFeel(new AeroLookAndFeel());
//			UIManager.setLookAndFeel(new VoVoLookAndFeel());
			// UIManager.setLookAndFeel(new BernsteinLookAndFeel());
			// UIManager.put("TabbedPaneUI", "MyTabbedPaneUI");
		} catch (Exception e) {
			e.printStackTrace();
		}
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				exeC("main", "showLogin");
			}
		});
		NativeInterface.runEventPump();
	}
	
	public static LCMUtil getLcmUtil() throws Exception{
		LoginInfo loginInfo = getMyContext().getDataManager().getValue(Constants.DataKey.LOGGININFO.toString());
		String xmlRpcUrl = "http://"+loginInfo.getServerIP()+ Vovo.getMyContext().getConfigManager().getProperty("lcm.xmlrpc", ":6090/lcmRpc");
		return LCMUtil.newInstance(xmlRpcUrl);
	}
		
	public static Object exeC(String className, String methodName, Object... paras){
		return getMyContext().getExecuteManager().executeController(className, methodName, paras);
	}
	
	public static Object exeS(String className, String methodName, Object... paras)throws Exception{
		return getMyContext().getExecuteManager().executeService(className, methodName, paras);
	}
	
	
	public static void doCheckPermission(String id,Object... paras) throws NoPermissionException{
		getMyContext().getPermissionUtil().checkPermission(id, paras);
	}
	
	public static void sendMessage(String id,Object[] objs){
		getMyContext().getMessageManager().sendMessage(id, objs);
	}
	
	public static void sendMessage(MyEvent myEvent){
		getMyContext().getMessageManager().sendMessage(myEvent);
	}

	
	public static ConfigManager getConfigManager(){
		return getMyContext().getConfigManager();
	}
	
	public static ViewManager getViewManager(){
		return getMyContext().getViewManager();
	}

	
	public static <T> T getValue(String key){
		return (T)getMyContext().getDataManager().getValue(key);
	}
	
	public static void setValue(String key, Object o){
		getMyContext().getDataManager().setValue(key, o);
	}


	@Override
	protected String getPermissionFilePath() {
		return "/com/lorent/vovo/config/permission_config.xml";
	}
	
}
