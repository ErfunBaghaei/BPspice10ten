import javax.swing.*;
import java.awt.*;


public class GraphPaint extends JPanel {
    RunActionListener runActionListener;
    int check = 0, maxIndex = 0;
    InitialTextProccesor initialTextProccesor;
    Element element;
    double[] powerValues = new double[10000];
    JLabel[] time;

    public GraphPaint(RunActionListener runActionListener, int check, InitialTextProccesor initialTextProccesor, Element element, JLabel[] time) {
        this.runActionListener = runActionListener;
        this.check = check;
        this.initialTextProccesor = initialTextProccesor;
        this.element = element;
        this.time = time;

    }

    int roundBound = 1000;

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int cc = -58;
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(0));
        g2d.setColor(Color.BLACK);
        for (int i = 0; i < 11; i++)
            g2d.drawLine(60 + cc + i * 100, 5, 60 + cc + i * 100, 565);
        for (int i = 0; i < 9; i++)
            g2d.drawLine(60 + cc, 5 + i * 70, 1060 + cc, 5 + i * 70);


        g2d.setStroke(new BasicStroke(3));
        g2d.setColor(Color.RED);
        g2d.drawLine(60 + cc, 6, 60 + cc, 564);
        g2d.drawLine(60 + cc, 286, 1065 + cc, 286);
        setBackground(new Color(255, 255, 255, 255));

        JLabel xAxis = new JLabel();
        xAxis.setBounds(10 + cc, 281, 1010, 281);


        g2d.setColor(Color.GREEN);
        g2d.setStroke(new BasicStroke(3));


        double timeTick = initialTextProccesor.deltat;
        double endTime = initialTextProccesor.time;
        int numberOfPoints = (int) (endTime / timeTick) + 1;


        int distance = (int) (Math.ceil(1000.0 / (numberOfPoints - 1)));
        powerValues = new double[numberOfPoints];
        for (int h = 0; h < numberOfPoints; h++) {
            powerValues[h] = element.voltageValues[h] * element.currentValues[h];
        }


        if (check == 0)
            maxIndex = max(element.voltageValues, numberOfPoints);
        else if (check == 1)
            maxIndex = max(element.voltageValues, numberOfPoints);
        else if (check == 2)
            maxIndex = max(element.voltageValues, numberOfPoints);

        g2d.setStroke(new BasicStroke(2));

        if (maxIndex == -1) {
            g2d.drawLine(60 + cc, 287, 1050 + cc, 287);


        } else {


            if (check == 0) {

                for (int ii = 0; ii < numberOfPoints - 1; ii++) {

                    g2d.drawLine(distance * (ii - 1) + 5, 284 - (int) (Math.round(281 * (element.voltageValues[ii] / Math.abs(element.voltageValues[maxIndex])))), distance * (ii) + 5,
                            284 - (int) (Math.round(281 * (element.voltageValues[ii + 1] / Math.abs(element.voltageValues[maxIndex])))));
                }
            } else if (check == 1) {
                for (int ii = 0; ii < numberOfPoints - 1; ii++) {

                    g2d.drawLine(distance * (ii - 1) + 5, 284 - (int) (Math.round(281 * (element.currentValues[ii] / Math.abs(element.currentValues[maxIndex])))), distance * (ii) + 5,
                            284 - (int) (Math.round(281 * (element.currentValues[ii + 1] / Math.abs(element.currentValues[maxIndex])))));

                }
            } else if (check == 2) {
                for (int ii = 0; ii < numberOfPoints - 1; ii++) {
                    g2d.drawLine(distance * (ii - 1) + 5, 284 - (int) (Math.round(281 * (powerValues[ii] / powerValues[maxIndex]))), distance * (ii) + 5,
                            284 - (int) (Math.round(281 * (powerValues[ii + 1] / powerValues[maxIndex]))));

                }
            }
        }

        double timeStepInGraph = (double) (initialTextProccesor.time / 10.0);

        for (int i = 0; i < 11; i++) {
            time[i].setText((double) Math.round(timeStepInGraph * i * roundBound) / roundBound + "");
            time[i].setBounds(100 * i - 30, 290, 40, 10);
        }
        for (int i = 0; i < 11; i++)
            this.add(time[i]);
    }
    public int max(double[] a, int size) {
        int indexOfMax = 0;
        for (int i = 0; i < size; i++) {
            if (Math.abs(a[i]) > Math.abs(a[indexOfMax]))
                indexOfMax = i;
        }
        if (a[indexOfMax] == 0.0)
            return -1;
        return indexOfMax;
    }
}