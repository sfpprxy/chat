import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.TextField;

public class Client extends Frame{

    TextField tf = new TextField();
    TextArea ta = new TextArea();

    public static void main(String[] foo){
        new Client().launchFrame();
    }

    public void launchFrame() {
        setLocation(500, 200);
        setSize(300, 300);
        add(tf, BorderLayout.SOUTH);
        add(ta, BorderLayout.NORTH);
        pack();
        setVisible(true);
    }
}
