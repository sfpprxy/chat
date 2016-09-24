import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client extends Frame{

    Socket s = null;
    DataOutputStream dos = null;
    TextField sendField = new TextField();
    TextArea chatBoard = new TextArea();

    public static void main(String[] foo){
        new Client().launchFrame();
    }

    public void launchFrame() {
        setLocation(400, 200);
        setSize(300, 300);
        add(sendField, BorderLayout.SOUTH);
        add(chatBoard, BorderLayout.NORTH);
        pack();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                disconnect();
                System.exit(0);
            }
        });
        sendField.addActionListener(new sendListener());
        setVisible(true);
        connect();
    }

    public void connect() {
        try {
            s = new Socket("127.0.0.1", 8888);
            dos = new DataOutputStream(s.getOutputStream());
            System.out.println("connected!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            dos.close();
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class sendListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String msg = sendField.getText().trim();
            chatBoard.setText(msg);
            sendField.setText("");
            try {
                dos.writeUTF(msg);
                dos.flush();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
