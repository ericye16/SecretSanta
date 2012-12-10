import javax.swing.*;
import java.awt.*;

/**
 * User: Eric
 * Date: 10/12/12
 * Time: 12:18 PM
 */
public class Graphing {
    public static void main(String[] argv) {
        JFrame f = new JFrame();
        MyCanvas c = new MyCanvas();
        f.getContentPane().add(c);
        f.setSize(1200, 700);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}


class MyCanvas extends Canvas{

    public Dimension getPreferredSize() {
        return new Dimension(1200, 700);
    }

    public void paint(Graphics g) {

        //draw our stuff here.
        g.drawLine(0, 0, 1200, 700);
    }
}
