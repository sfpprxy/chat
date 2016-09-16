import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Client extends Frame{

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
                System.exit(0);
            }
        });
        sendField.addActionListener(new sendListener());
        setVisible(true);
    }

    private class sendListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String msg = sendField.getText().trim();
            chatBoard.setText(msg);
            sendField.setText("");
        }
    }
}
