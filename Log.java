package wwdh.wdapi;

import java.util.concurrent.*;
import org.apache.log4j.*;


/**
 * egyszerű, logolást segítő osztály
 **/
public class Log {

    private static Logger log = Logger.getLogger(Log.class);

    //engedélyezve van-e a logolás?
    public static boolean enable = true;

    public static void log(Object... s) {
        logWithStackPos(3,s);
    }
    
    private static void logWithStackPos(int pos,Object... s) {
        if (enable) {
            String ss = "";
            if (s != null && s.length > 0) {
                for (int i = 0; i < s.length; i++) {
                    ss += s[i] + ",";
                }
                ss = ss.substring(0, ss.length() - 1);
            }
            write(ss, pos);
        }
    }

    public static void log(Object s) {
        if (enable) {
            write(s, 2);
        }
    }

    public static void logForce(Object s) {
        write(s, 2);
    }

    public static void logForce(Object s, int pos) {
        write(s, pos);
    }

    private static void write(Object s, int stackpos) {
        Thread t = Thread.currentThread();
        StackTraceElement[] st = new Exception().getStackTrace();
        long id = t.getId();
        String print = "[" + ((st != null && st.length > stackpos) ? st[stackpos] : "???") + "]#" + id + ": " + s;
        log.info(print);
    }
}


