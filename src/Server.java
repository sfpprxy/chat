import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    boolean isStarted;
    ServerSocket ss;
    List<ClientThread> clients = new ArrayList<ClientThread>();

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
                clients.add(c);
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
        private DataOutputStream dos = null;
        private boolean isConnected;

        public ClientThread(Socket s) {
            this.s = s;
            try {
                dis = new DataInputStream(s.getInputStream());
                dos = new DataOutputStream(s.getOutputStream());
                isConnected = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void send(String msg) {
            try {
                dos.writeUTF(msg);
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
                    for (int i = 0; i < clients.size(); i++) {
                        ClientThread c = clients.get(i);
                        c.send(msg);
                    }
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
                    if (dos != null) {
                        dos.close();
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
