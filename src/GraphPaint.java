import javax.swing.*;
import java.awt.*;


public class GraphPaint extends JPanel {
    RunActionListener runActionListener;
    int check = 0;
    InitialTextProccesor initialTextProccesor;
    Element element;

    public GraphPaint(RunActionListener runActionListener, int check, InitialTextProccesor initialTextProccesor, Element element) {
        this.runActionListener = runActionListener;
        this.check = check;
        this.initialTextProccesor = initialTextProccesor;
        this.element = element;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(0));
        g2d.setColor(Color.BLACK);
        for (int i = 0; i < 11; i++)
            g2d.drawLine(10 + i * 100, 5, 10 + i * 100, 565);
        for (int i = 0; i < 9; i++)
            g2d.drawLine(10, 5 + i * 70, 1010, 5 + i * 70);


        g2d.setStroke(new BasicStroke(3));
        g2d.setColor(Color.RED);
        g2d.drawLine(10, 5, 10, 564);
        g2d.drawLine(10, 286, 1010, 286);
        setBackground(Color.WHITE);

        JLabel xAxis = new JLabel();
        xAxis.setBounds(10, 280, 1010, 280);


        g2d.setColor(Color.GREEN);
        g2d.setStroke(new BasicStroke(3));


        int maxIndex = 0;
        double timeTick = initialTextProccesor.deltat;
        double endTime = initialTextProccesor.time;
        int numberOfPoints = (int) (endTime / timeTick) + 1;


        int distance = (int) (Math.ceil(1000.0 / (numberOfPoints - 1)));
        double[] powerValues = new double[numberOfPoints];
        for (int h = 0; h < numberOfPoints; h++) {
            powerValues[h] = element.voltageValues[h] * element.currentValues[h];
        }


        if (check == 0)
            maxIndex = max(element.voltageValues, numberOfPoints);
        else if (check == 1)
            maxIndex = max(element.voltageValues, numberOfPoints);
        else if (check == 2)
            maxIndex = max(element.voltageValues, numberOfPoints);

        if (maxIndex == -1) {
            g2d.drawLine(10, 285, 1000, 285);
            System.out.println("we ar highed :)");
        }

        else {
            if (check == 0) {


                for (int ii = 0; ii < numberOfPoints - 1; ii++) {

                    g2d.drawLine(distance * (ii) + 10, 285 - (int) (Math.ceil(280 * (element.voltageValues[ii] / Math.abs(element.voltageValues[maxIndex])))), distance * (ii + 1) + 10,
                            285 - (int) (Math.ceil(280 * (element.voltageValues[ii + 1]))));
                }
            } else if (check == 1) {
                for (int ii = 0; ii < numberOfPoints - 1; ii++) {
                    g2d.drawLine(distance * (ii) + 10, 285 - (int) (Math.ceil(280 * (element.currentValues[ii]))), distance * (ii + 1) + 10,
                            285 - (int) (Math.ceil(280 * (element.currentValues[ii + 1]))));

                }
            } else if (check == 2) {
                for (int ii = 0; ii < numberOfPoints - 1; ii++) {
                    g2d.drawLine(distance * (ii) + 10, 285 - (int) (Math.ceil(280 * (powerValues[ii]))), distance * (ii + 1) + 10,
                            285 - (int) (Math.ceil(280 * (powerValues[ii + 1]))));

                }
            }
        }
    }


    public int max(double[] a, int size) {
        int indexOfMax = 0;
        for (int i = 0; i < size; i++) {
            if (Math.abs(a[i]) > Math.abs(a[indexOfMax]))
                indexOfMax = i;
        }
        if(a[indexOfMax]==0.0)
            return -1;
        return indexOfMax;
    }


}