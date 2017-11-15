import java.io.*;
import java.net.*;

public class TestServer {
    public static class Client extends Thread {
        private Socket s;
        public Client(Socket s) {
            this.s = s;
            start();
        }

        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

                int l;
                while ((l = in.read()) != -1) {
                    System.out.print((char)l);
                }            

                System.out.println("end*****");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8777);
        while(true) {
            new Client(serverSocket.accept());
        }

        /*
send = 
"GET / HTTP/1.1\r\n"+
"Host: "+uri.getHost()+"\r\n"+
"User-Agent: GuzzleHttp/6.2.1 curl/7.47.1 PHP/7.1.3\r\n"+
"Content-Type: application/json; charset=utf-8\r\n"+
"Content-ID: 12318.1498220883.3829.364353843\r\n"+
"X-test-Developer: wwdh\r\n"+
"X-test-Debug: /paralell_execution/web/frontend/runtime/debug\r\n"+
"X-test-Mode: sync\r\n"+
"\r\n\r\n";
*/        
    }
}