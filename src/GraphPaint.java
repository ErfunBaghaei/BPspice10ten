import javax.swing.*;
import java.awt.*;


public class GraphPaint extends JPanel {
    RunActionListener runActionListener;
    int check=0;
    InitialTextProccesor initialTextProccesor;
    public GraphPaint(RunActionListener runActionListener,int check,InitialTextProccesor initialTextProccesor){
        this.runActionListener=runActionListener;
        this.check=check;
        this.initialTextProccesor=initialTextProccesor;
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





        double timeTick=initialTextProccesor.deltat;
        double endTime =initialTextProccesor.time;
        int numberOfPoints=(int)(endTime/timeTick)+1;
        int pixelWidthScale=1000/numberOfPoints;








        for (int ii = 0; ii <= numberOfPoints; ii++) {
            g2d.drawLine(10+ii*pixelWidthScale, 245-ii, 10+(ii+1)*pixelWidthScale, 245-ii-1);
        }
    }


    public int max(double[] a,int size){
        int indexOfMax=0;
        for(int i=0;i<size;i++){
            if(a[i]>a[indexOfMax])
                indexOfMax=i;
        }
        return indexOfMax;
    }




}