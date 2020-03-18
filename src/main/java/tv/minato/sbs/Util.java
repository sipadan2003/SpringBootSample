package tv.minato.sbs;

public class Util {
	public static final String API1_PATH = "/api/v1";
	private Util() {}

	public static String threadInfo() {
		final Thread t  = Thread.currentThread();
		final String deamon = t.isDaemon() ? "(deamon)" : "(non-deamon)";
//		final String dt = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME);
		return "["+t.getName()+"("+deamon+")]";
	}
}
