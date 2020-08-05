import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.PublicKey;

public class DrawActionListener implements ActionListener {
    RunActionListener runActionListener;
    InitialTextProccesor initialTextProccesor;
    JLabel[] rowV = new JLabel[9];
    JLabel[] rowI = new JLabel[9];
    JLabel[] rowP = new JLabel[9];
    JLabel[] time = new JLabel[11];
    JTextField whichElement;
    double[] powerValues;

    public DrawActionListener(RunActionListener runActionListener, InitialTextProccesor initialTextProccesor, JTextField whichElement) {
        this.runActionListener = runActionListener;
        this.initialTextProccesor = initialTextProccesor;
        this.whichElement = whichElement;

        for (int i = 0; i < 9; i++)
            rowV[i] = new JLabel("0");
        for (int i = 0; i < 9; i++)
            rowI[i] = new JLabel("0");
        for (int i = 0; i < 9; i++)
            rowP[i] = new JLabel("0");
        for (int i = 0; i < 11; i++)
            time[i] = new JLabel("T");


    }


    int check = 1;

    JFrame voltageGraph = new JFrame("Voltage");
    JFrame currentGraph = new JFrame("Current");
    JFrame powerGraph = new JFrame("Power");


    Element element = null;
    String name;

    @Override
    public void actionPerformed(ActionEvent a) {


        name = whichElement.getText();
        if (name.equals("")) {
            JOptionPane.showMessageDialog(voltageGraph,
                    "please give an element!", "Error!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        boolean flag = false;

        for (int i = 0; i < initialTextProccesor.elements.size(); i++) {
            if (name.equals(initialTextProccesor.elements.get(i).name)) {
                flag = true;
                element = initialTextProccesor.elements.get(i);
            }
        }
        if (flag == false) {
            JOptionPane.showMessageDialog(voltageGraph,
                    "Invalid element!", "Error!", JOptionPane.ERROR_MESSAGE);
            return;
        }


        GraphPaint voltageGraphPaint = new GraphPaint(runActionListener, 0, initialTextProccesor, element, time);
        GraphPaint currentGraphPaint = new GraphPaint(runActionListener, 1, initialTextProccesor, element, time);
        GraphPaint powerGraphPaint = new GraphPaint(runActionListener, 2, initialTextProccesor, element, time);

        voltageGraph.getContentPane().setBackground(Color.WHITE);
        currentGraph.getContentPane().setBackground(Color.WHITE);
        powerGraph.getContentPane().setBackground(Color.WHITE);


        voltageGraph.setBounds(0, 100, 1090, 610);
        currentGraph.setBounds(0, 650, 1090, 610);
        powerGraph.setBounds(900, 100, 1090, 610);

        voltageGraphPaint.setBounds(60, 0, 1020, 610);
        currentGraphPaint.setBounds(60, 0, 1020, 610);
        powerGraphPaint.setBounds(60, 0, 1020, 610);

        voltageGraph.setLayout(null);
        currentGraph.setLayout(null);
        powerGraph.setLayout(null);


        Border border = BorderFactory.createLineBorder(new Color(19, 193, 30), 2, true);
        voltageGraph.getRootPane().setBorder(border);
        currentGraph.getRootPane().setBorder(border);
        powerGraph.getRootPane().setBorder(border);
        int VmaxIndex, ImaxIndex, PmaxIndex;
        int size = (int) (initialTextProccesor.time / initialTextProccesor.deltat) + 1;
        double timeStepInGraph = (double) (initialTextProccesor.time / 10.0);

        VmaxIndex = voltageGraphPaint.max(element.voltageValues, size);
        ImaxIndex = voltageGraphPaint.max(element.currentValues, size);
        PmaxIndex = voltageGraphPaint.max(element.voltageValues, size);

        time[0].setText("0");
        time[0].setBounds(65, 290, 40, 10);
        for (int i = 1; i < 11; i++) {
            time[i].setText(timeStepInGraph * i + "");
            time[i].setBounds(100 * i + 65, 290, 40, 10);
        }

        powerValues = new double[size];
        for (int h = 0; h < size; h++) {
            powerValues[h] = element.voltageValues[h] * element.currentValues[h];
        }

        double unit;
        if (VmaxIndex != -1) {

            unit = Math.abs(element.voltageValues[VmaxIndex] / 4.0);
            for (int i = 0; i < 9; i++) {
                rowV[i].setText(((int) ((Math.abs(element.voltageValues[VmaxIndex]) - unit * i) * 100000.0) / 100000.0) + "");

            }
        } else {
            for (int i = 0; i < 9; i++)
                rowV[i].setText(4 - i + "*10^-17");
            rowV[4].setText("       0");
        }
        if (ImaxIndex != -1) {
            unit = Math.abs(element.currentValues[ImaxIndex] / 4.0);
            for (int i = 0; i < 9; i++) {
                rowI[i].setText(((int) ((Math.abs(element.currentValues[ImaxIndex]) - unit * i) * 100000.0) / 100000.0) + "");

            }
        } else {
            for (int i = 0; i < 9; i++)
                rowI[i].setText(4 - i + "*10^-17");
            rowI[4].setText("       0");
        }
        if (PmaxIndex != -1) {
            unit = Math.abs(powerValues[PmaxIndex] / 4.0);
            for (int i = 0; i < 9; i++) {
                rowP[i].setText(((int) ((Math.abs(powerValues[PmaxIndex]) - unit * i) * 1000000.0) / 1000000.0) + "");

            }
        } else {
            for (int i = 0; i < 9; i++)
                rowP[i].setText(4 - i + "*10^-17");
            rowP[4].setText("        0");
        }


        for (int i = 1; i < 8; i++) {

            rowV[i].setBounds(3, -5 + i * 70, 75, 15);
            rowI[i].setBounds(3, -5 + i * 70, 75, 15);
            rowP[i].setBounds(3, -5 + i * 70, 75, 15);
        }
        rowV[0].setBounds(3, 5, 75, 15);
        rowV[8].setBounds(3, 545, 75, 15);
        rowI[0].setBounds(3, 5, 75, 15);
        rowI[8].setBounds(3, 545, 75, 15);
        rowP[0].setBounds(3, 5, 75, 15);
        rowP[8].setBounds(3, 545, 75, 15);


        for (int i = 0; i < 9; i++)
            voltageGraph.add(rowV[i]);

        for (int i = 0; i < 9; i++)
            currentGraph.add(rowI[i]);

        for (int i = 0; i < 9; i++)
            powerGraph.add(rowP[i]);

        currentGraph.add(currentGraphPaint);
        powerGraph.add(powerGraphPaint);
        voltageGraph.add(voltageGraphPaint);
        currentGraph.setVisible(true);
        powerGraph.setVisible(true);
        voltageGraph.setVisible(true);






    }
}
