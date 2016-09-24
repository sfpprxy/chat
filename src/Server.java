import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] foo){
        boolean isStarted = false;
        try {
            ServerSocket ss = new ServerSocket(8888);
            isStarted = true;
            while(isStarted) {
                boolean isConnected = false;
                Socket s = ss.accept();
                System.out.println("a client connected!");
                isConnected = true;
                DataInputStream dis = new DataInputStream(s.getInputStream());
                // Fix: Server stop when client exit without sending a msg
                while (isConnected) {
                    String msg = dis.readUTF();
                    System.out.println(msg);
                }
                dis.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
