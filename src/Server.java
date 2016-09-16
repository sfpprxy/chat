import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.AbstractList;

public class Server {

    public static void main(String[] foo){
        try {
            ServerSocket ss = new ServerSocket(8888);
            while(true) {
                Socket s = ss.accept();
                System.out.println("a client connected!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
