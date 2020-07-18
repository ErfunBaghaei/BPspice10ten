import javax.swing.*;
import java.awt.*;


public class GraphPaint extends JPanel {
    RunActionListener runActionListener;
    int check=0;
    public GraphPaint(RunActionListener runActionListener,int check){
        this.runActionListener=runActionListener;
        this.check=check;
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);



        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(0));
        g2d.setColor(Color.gray);
        for(int i=0;i<11;i++)
            g2d.drawLine(10+i*100, 5, 10+i*100, 485);
        for(int i=0;i<7;i++)
            g2d.drawLine(10, 5+i*80, 1010, 5+i*80);


        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(Color.RED);
        g2d.drawLine(10, 5, 10, 485);
        g2d.drawLine(10, 245, 1010, 245);
        setBackground(Color.BLACK);

        JLabel xAxis=new JLabel();
        xAxis.setBounds(10,250,1010,250);



        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(2));





        ///////make the wrong correct!!!!



        double wrongendtime =15;
        double wrongtimeTick=0.1;
        int number=150;
        int pixelWidthScale=1000/number;
        int pixelHeightScale=280/number;
        double[] wrongQuantities=new double[10000];
        for(int j=0;j<150;j++)
            wrongQuantities[j]=j*j*0.01;






        for (int ii = 0; ii <= number; ii++) {
            g2d.drawLine(10+ii*pixelWidthScale, 245-ii, 10+(ii+1)*pixelWidthScale, 245-ii-1);
        }
    }
}