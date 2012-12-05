package lorent.tools.jni;

public class VideoMonitoring {
    public static native int start(String str, Object obj1);
    public static native int stop(int id);
    
	static{
    	System.loadLibrary("monitor_jni");
    }
}
