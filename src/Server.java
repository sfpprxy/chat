import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] foo){
        boolean isStarted;
        ServerSocket ss;
        Socket s = null ;
        DataInputStream dis = null;
        try {
            ss = new ServerSocket(8888);
            isStarted = true;
            while(isStarted) {
                boolean isConnected;
                s = ss.accept();
                System.out.println("a client connected!");
                isConnected = true;
                dis = new DataInputStream(s.getInputStream());
                while (isConnected) {
                    String msg = dis.readUTF();
                    System.out.println(msg);
                }
            }
        } catch (EOFException e) {
            System.out.println("Client closed");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (dis != null) {dis.close();}
                if (s != null) {s.close();}
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
