import javax.swing.*;
import java.awt.*;

public class MapPoints extends JPanel {

    @Override
    public void paintComponent(Graphics g) {

        setPreferredSize(new Dimension (670,500));

        super.paintComponent(g);
        //850,80,670,420
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(4));
        g2d.setColor(Color.RED);

        for(int i=0;i<8;i++) {
            for(int h=0;h<12;h++)
                g2d.drawLine(50+h*82, 40+i*82, 50+h*82, 40+i*82);

        }




    }
}