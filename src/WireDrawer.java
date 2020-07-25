import javax.swing.*;
import java.awt.*;

public class WireDrawer extends JPanel {



    int nodePCoordinatex,nodeMCoordinatex,nodePCoordinatey,nodeMCoordinatey;
    public WireDrawer(int nodePx,int nodeMx,int nodePy,int nodeMy){
        this.nodePCoordinatex=nodePx;
        this.nodeMCoordinatex=nodeMx;
        this.nodePCoordinatey=nodePy;
        this.nodeMCoordinatey=nodeMy;

        System.out.println(nodePCoordinatex+"  "+nodePCoordinatey+" "+nodeMCoordinatex+" "+nodeMCoordinatey+"ijijij");
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(4));
        g2d.setColor(Color.RED);

        setPreferredSize(new Dimension (670,420));
        g2d.drawLine(200,1200,300,200);


    }


}
