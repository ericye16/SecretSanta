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
            while (alreadyVisited.contains(i)) {
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

    /**
     * Simple method for drawing arrows and circles between two points.
     *
     * @param x1 x-location of the first coordinate
     * @param y1 y-location of the first coordinate
     * @param x2 x-location of the second coordinate
     * @param y2 y-location of the second coordinate
     * @param g  The graphics to draw them on.
     */
    private void drawArrow(int x1, int y1, int x2, int y2, Graphics g) {

        //the line connecting the two points
        g.drawLine(x1, y1, x2, y2);
        //draw a circle at the original place
        g.drawOval(x1 - 5, y1 - 5, 11, 11);

        /*
        now the fun part...
        I want the arrowheads to be coming off at 20 degrees from the angle of the main line.
        Arrowheads are draw like this ->, connecting the endpoint of the line to two points on either side of it.
        To draw it, we need to find the change in x and y relative to the endpoint of the original line.
        */

        //elementary mathematics for the win!
        final int lengthOfArrowHead = 10;
        final double angleOfArrowHead = Math.PI / 9.0; //20 degrees -- remember we're in Radians.
        int arrowHeadX1;
        int arrowHeadX2;
        int arrowHeadY1;
        int arrowHeadY2;

        double slope = (double) (y2 - y1) / (double) (x2 - x1);
        if (slope == Float.NEGATIVE_INFINITY) {
            //the line is going straight down.
            /*
             Therefore, the change in y is:
             +lengthOfArrowHead * cos(angleOfArrowHead)
             and the change in x is:
             +/- lengthOfArrowHead * sin(angleOfArrowHead)
             */

            arrowHeadX1 = x2 + (int) (lengthOfArrowHead * Math.sin(angleOfArrowHead));
            arrowHeadX2 = x2 - (int) (lengthOfArrowHead * Math.sin(angleOfArrowHead));
            arrowHeadY1 = y2 + (int) (lengthOfArrowHead * Math.cos(angleOfArrowHead));
            arrowHeadY2 = y2 + (int) (lengthOfArrowHead * Math.cos(angleOfArrowHead));
        } else if (slope == Float.POSITIVE_INFINITY) {
            //the line is going straight up.
            /*
            Same as above, but the change is y is negative.
             */

            arrowHeadX1 = x2 + (int) (lengthOfArrowHead * Math.sin(angleOfArrowHead));
            arrowHeadX2 = x2 - (int) (lengthOfArrowHead * Math.sin(angleOfArrowHead));
            arrowHeadY1 = y2 - (int) (lengthOfArrowHead * Math.cos(angleOfArrowHead));
            arrowHeadY2 = y2 - (int) (lengthOfArrowHead * Math.cos(angleOfArrowHead));
        } else {
            //this is a little more difficult.
            double slopeAngleFromX = Math.atan2((y2 - y1), (x2 - x1));
            double slopeAngleFromY = Math.PI / 2 - slopeAngleFromX;

            /*
            I'll document the below when I have time. But this took a while to figure out.
             */

            arrowHeadX1 = x2 - (int) (lengthOfArrowHead * Math.cos(angleOfArrowHead + slopeAngleFromX));
            arrowHeadY1 = y2 - (int) (lengthOfArrowHead * Math.sin(angleOfArrowHead + slopeAngleFromX));
            arrowHeadX2 = x2 - (int) (lengthOfArrowHead * Math.sin(angleOfArrowHead + slopeAngleFromY));
            arrowHeadY2 = y2 - (int) (lengthOfArrowHead * Math.cos(angleOfArrowHead + slopeAngleFromY));
        }
        g.setColor(Color.green);
        g.drawLine(x2, y2, arrowHeadX1, arrowHeadY1); //right
        g.drawLine(x2, y2, arrowHeadX2, arrowHeadY2);  //left
        g.setColor(Color.black);

    }

    public void paint(Graphics g) {

        //draw our stuff here.
        drawArrow(0, 0, 500, 500, g);
    }
}
