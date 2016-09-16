import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] foo){
        try {
            ServerSocket ss = new ServerSocket(8888);
            while(true) {
                Socket s = ss.accept();
                System.out.println("a client connected!");
                DataInputStream dis = new DataInputStream(s.getInputStream());
                String msg = dis.readUTF();
                System.out.println(msg);
                dis.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
