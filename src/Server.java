import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    boolean isStarted;
    ServerSocket ss;

    public static void main(String[] foo){
        new Server().start();
    }

    public void start() {
        try {
            ss = new ServerSocket(8888);
            isStarted = true;
            while(isStarted) {
                Socket s = ss.accept();
                ClientThread c = new ClientThread(s);
                new Thread(c).start();
                System.out.println("a client connected!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                ss.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class ClientThread implements Runnable {

        private Socket s;
        private DataInputStream dis = null;
        private boolean isConnected;

        public ClientThread(Socket s) {
            this.s = s;
            try {
                dis = new DataInputStream(s.getInputStream());
                isConnected = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                while (isConnected) {
                    String msg = dis.readUTF();
                    System.out.println(msg);
                }
            } catch (EOFException e) {
                System.out.println("Client closed");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (dis != null) {
                        dis.close();
                    }
                    if (s != null) {
                        s.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
