package wwdh.wdapi;

import java.nio.*;
import java.io.*;
import java.text.*;
import java.util.*;

/**
 * helper metódusok
 */
public class Util {
    private static String currentip = null;

    public static void sleep(long l) {
        try {
            Thread.sleep(l);
        } catch (Exception e) {}        
    }

    public static byte[] convertByteBufferToArray(ByteBuffer content) {
        byte[] buffer;
        int offset;
        int length = content.remaining();
        if (content.hasArray())
        {
            buffer = content.array();
            offset = content.arrayOffset();
        }
        else
        {
            buffer = new byte[length];
            content.get(buffer);
            offset = 0;
        }

        return buffer;            
    }        

    public static int byteArrayIndexOf(byte[] source, byte[] dest, int offset, int endpos) {
        return KnuthMorrisPratBinarySearching.indexOf(source, dest, offset, endpos);
        /*for (int i=offset;i<=endpos-dest.length;i++) {
            boolean f = true;            
            for (int j=0;j<dest.length;j++) {
                if (source[i + j] != dest[j]) {
                    f = false;
                    break;
                }
            }
            if (f) {                 
                return i;
            }
        }            
        return -1;*/
    }

    public static long getPID() {
        String processName = java.lang.management.ManagementFactory.getRuntimeMXBean().getName();
        if (processName != null && processName.length() > 0) {
            try {
                return Long.parseLong(processName.split("@")[0]);
            }
            catch (Exception e) {
                return 0;
            }
        }
        return 0;
    }   

    public static String getCurrentIp() {
        return currentip;
    }

	private static String executeCommand(String command) throws Exception {
		StringBuffer output = new StringBuffer();
		Process p = Runtime.getRuntime().exec(command);
        p.waitFor();
        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

        String line = "";
        while ((line = reader.readLine())!= null) {
            output.append(line + "\n");
        }
		return output.toString().trim();
    }    

    public static void init() throws Exception {
        String hn = executeCommand("hostname -I");
        String[] t = hn.split(" ");
        if (t == null || t.length <= 0) {
            throw new Exception("Failed to get hostname: "+hn);
        } else {
            currentip = t[0].trim();
        }        
    } 

    public static String getServerTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormat.format(calendar.getTime());
    }    
}