package lorent.stb.tv;

public class DVB
{
	//接收完一个serviceId的PF
	public static final String MESSAGE_EIT_RECV_PF ="20000032";
	//接收完一个serviceId的schedule
	public static final String MESSAGE_EIT_RECV_SCHEDULE="20000033";
	//节目预定提醒，只提醒一次 带SerId, EventId
	public final static String MESSAGE_ORDER_REMIND="20000050";
	//节目预定录制提醒，只提醒一次 带SerId, EventId
	public final static String MESSAGE_ORDER_PVR_REMIND = "20000060";
	//预约点播，搜索成功
	public final static String  MESSAGE_NVOD_RECV_FINISH ="20000100";
	//NVOD搜索失败
	public final static String  MESSAGE_NVOD_RECV_FAILED = "20000101";
}
