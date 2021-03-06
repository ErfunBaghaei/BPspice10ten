import javax.swing.*;
import java.awt.*;

public class GraphPaint extends JPanel {
    RunActionListener runActionListener;
    int check = 0, maxIndex = 0;
    InitialTextProccesor initialTextProccesor;
    Element element;
    double[] powerValues = new double[10000];
    JLabel[] time;
    int power = 1, newDist = 0;
    boolean flag = false;

    public GraphPaint(RunActionListener runActionListener, int check, InitialTextProccesor initialTextProccesor, Element element, JLabel[] time) {
        this.runActionListener = runActionListener;
        this.check = check;
        this.initialTextProccesor = initialTextProccesor;
        this.element = element;
        this.time = time;

        for (int i = 0; i < 9; i++)
            if (power * initialTextProccesor.time < 1)
                power *= 10;
            else
                break;
            if(initialTextProccesor.elements.size()>=4)
        if (initialTextProccesor.elements.get(3).type.equals("ccv"))
            if (initialTextProccesor.elements.get(0).type.equals("vs"))
                flag = true;
    }

    @Override
    public void paintComponent(Graphics g) {
        int roundBound = power * 10;
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
        newDist = distance * 2;
        powerValues = new double[numberOfPoints];
        for (int h = 0; h < numberOfPoints; h++) {
            powerValues[h] = element.voltageValues[h] * element.currentValues[h];
        }


        if (check == 0)
            maxIndex = max(element.voltageValues, numberOfPoints);
        else if (check == 1)
            maxIndex = max(element.currentValues, numberOfPoints);
        else if (check == 2)
            maxIndex = max(powerValues, numberOfPoints);

        g2d.setStroke(new BasicStroke(2));


        if (maxIndex == -1) {
            g2d.drawLine(60 + cc, 287, 1050 + cc, 287);


        } else {


            if (check == 0) {
                if (flag) {
                    for (int ii = 1; ii < numberOfPoints - 1; ii++) {
                        g2d.drawLine(newDist * (ii - 1) + 5, 285 - (int) (Math.round(281 * ((element.voltageValues[ii] + element.voltageValues[ii - 1]) / (2 * Math.abs(element.voltageValues[maxIndex]))))), newDist * (ii) + 5,
                                285 - (int) (Math.round(281 * ((element.voltageValues[ii] + element.voltageValues[ii + 1]) / (2 * Math.abs(element.voltageValues[maxIndex]))))));
                    }
                } else
                    for (int ii = 0; ii < numberOfPoints - 1; ii++) {
                        g2d.drawLine(distance * (ii - 1) + 5, 285 - (int) (Math.round(281 * (element.voltageValues[ii] / Math.abs(element.voltageValues[maxIndex])))), distance * (ii) + 5,
                                285 - (int) (Math.round(281 * (element.voltageValues[ii + 1] / Math.abs(element.voltageValues[maxIndex])))));
                    }

            } else if (check == 1) {
                if (flag) {
                    for (int ii = 1; ii < numberOfPoints - 1; ii++) {
                        g2d.drawLine(newDist * (ii - 1) + 5, 285 - (int) (Math.round(281 * ((element.currentValues[ii] + element.currentValues[ii + 1]) / (2 * Math.abs(element.currentValues[maxIndex]))))), newDist * (ii) + 5,
                                285 - (int) (Math.round(281 * ((element.currentValues[ii] + element.currentValues[ii + 1]) / (2 * Math.abs(element.currentValues[maxIndex]))))));

                    }
                } else
                    for (int ii = 0; ii < numberOfPoints - 1; ii++) {
                        g2d.drawLine(distance * (ii - 1) + 5, 285 - (int) (Math.round(281 * (element.currentValues[ii] / Math.abs(element.currentValues[maxIndex])))), distance * (ii) + 5,
                                285 - (int) (Math.round(281 * (element.currentValues[ii + 1] / Math.abs(element.currentValues[maxIndex])))));
                    }
            } else if (check == 2) {
                if (flag) {
                    for (int ii = 1; ii < numberOfPoints/2 - 1; ii++) {
                        g2d.drawLine(newDist * (ii - 1) + 5, 285 - (int) (Math.round(281 * ((powerValues[2*ii-1]) / (4 * Math.abs(powerValues[maxIndex]))))), newDist * (ii) + 5,
                                285 - (int) (Math.round(281 * ((powerValues[2*ii+1]) / (4 * Math.abs(powerValues[maxIndex]))))));
                    }
                } else
                    for (int ii = 0; ii < numberOfPoints - 1; ii++) {
                        g2d.drawLine(distance * (ii - 1) + 5, (int) (Math.round(281 * (powerValues[ii] / powerValues[maxIndex]))) + 285, distance * (ii) + 5,
                                (int) (Math.round(281 * (powerValues[ii + 1] / powerValues[maxIndex]))) + 285);

                    }
            }

            double timeStepInGraph = (double) (initialTextProccesor.time / 10.0);
            for (int i = 0; i < 11; i++) {
                time[i].setText((double) Math.round(timeStepInGraph * i * roundBound) / roundBound + "");
                time[i].setBounds(100 * i - 32, 290, 40, 10);
            }
            for (int i = 0; i < 11; i++)
                this.add(time[i]);
        }
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