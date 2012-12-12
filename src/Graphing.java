import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;

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

    public static HashMap<Integer, Integer> getSubGraphs(int[] recipients) {
        HashMap<Integer, Integer> out = new HashMap<Integer, Integer>();
        HashSet<Integer> alreadyVisited = new HashSet<Integer>();

        //initialize
        int i = 0;
        int count = 1;
        //alreadyVisited.add(0);

        do {
            while(alreadyVisited.contains(i)) {
                i++;
                i %= recipients.length;
            }
            count = 0;
            do {
                count++;
                alreadyVisited.add(i);
                i = recipients[i];
            } while (!alreadyVisited.contains(i));
            if (out.containsKey(count)) {
                int before = out.get(count);
                out.put(count, before + 1);
            } else {
                out.put(count, 1);
            }
        } while (alreadyVisited.size() < recipients.length);
        return out;
    }
}


class MyCanvas extends Canvas {

    public Dimension getPreferredSize() {
        return new Dimension(1200, 700);
    }

    public void paint(Graphics g) {

        //draw our stuff here.
        g.drawLine(0, 0, 1200, 700);
    }
}
