package modal;

public class Environment {

	private static String browser = null;
	private static String hub = null;
	private static String qaEnv = null;
	private static String oS = null;
	private static String machine = null;
	
	private static boolean addImgPass = false;
	private static boolean addImgFail = false;
	private static boolean addImgErr = false;

	
	
	public static void setBrowser(String brs) {
		browser = brs;
	}

	public static String getBrowser() {
		return browser;
	}
	
	
	public static void setQaEnv(String env) {
		qaEnv = env;
	}

	public static String getQaEnv() {
		return qaEnv;
	}
	
	
	public static void setMachine(String mac) {
		machine = mac;
	}

	public static String getMachine() {
		return machine;
	}
	
	public static void setHub(String hb) {
		hub = hb;
	}

	public static String getHub() {
		return hub;
	}
	
	public static void setOs(String os) {
		oS = os;
	}

	public static String getOs() {
		return oS;
	}
	
	public static void setAddImgPass(boolean addimgpass){
		addImgPass = addimgpass;
	}
	
	public static boolean getAddImgPass() {
		return addImgPass;
	}
	
	public static void setAddImgFail(boolean addimgfail){
		addImgFail = addimgfail;
	}
	
	public static boolean getAddImgFail() {
		return addImgFail;
	}
	
	public static void setAddImgError(boolean addimgerr){
		addImgErr = addimgerr;
	}
	
	public static boolean getAddImgError() {
		return addImgErr;
	}
	
	
}
