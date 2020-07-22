import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.PublicKey;

public class DrawActionListener implements ActionListener {
    RunActionListener runActionListener;
    InitialTextProccesor initialTextProccesor;
    JLabel[] row=new JLabel[9];
    JTextField whichElement;
    public DrawActionListener(RunActionListener runActionListener,InitialTextProccesor initialTextProccesor,JTextField whichElement){
        this.runActionListener=runActionListener;
        this.initialTextProccesor=initialTextProccesor;
        this.whichElement=whichElement;

        for(int i=0;i<9;i++)
            row[i]=new JLabel("1");
        for(int i=1;i<8;i++){
            row[i].setBounds(0,-5+ i * 70,55,15);
        }
        row[0].setBounds(-2,5,55,15);
        row[8].setBounds(-2,545,55,15);

    }






    int check=1;

    JFrame voltageGraph=new JFrame("Voltage");
    JFrame currentGraph=new JFrame("Current");
    JFrame powerGraph=new JFrame("Power");


    Element element=null;
    String name;

    @Override
    public void actionPerformed(ActionEvent a){



        name=whichElement.getText();
        if(name.equals(""))
        {
            JOptionPane.showMessageDialog(voltageGraph,
                    "please give an element!", "Error!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        boolean flag=false;

        for(int i=0;i<initialTextProccesor.elements.size();i++){
            if(name.equals(initialTextProccesor.elements.get(i).name)){
                flag=true;
                element=initialTextProccesor.elements.get(i);
            }
        }
        if(flag==false)
        {
            JOptionPane.showMessageDialog(voltageGraph,
                    "Invalid element!", "Error!", JOptionPane.ERROR_MESSAGE);
            return;
        }


        GraphPaint voltageGraphPaint=new GraphPaint(runActionListener,0,initialTextProccesor,element);
        GraphPaint currentGraphPaint=new GraphPaint(runActionListener,1,initialTextProccesor,element);
        GraphPaint powerGraphPaint=new GraphPaint(runActionListener,2,initialTextProccesor,element);
        voltageGraph.add(voltageGraphPaint);
        currentGraph.add(currentGraphPaint);
        powerGraph.add(powerGraphPaint);

        voltageGraph.setBounds(0,0,1076,610);
        currentGraph.setBounds(0,643,1076,610);
        powerGraph.setBounds(560,0,1076,610);

        voltageGraph.setLayout(null);
        currentGraph.setLayout(null);
        powerGraph.setLayout(null);

        voltageGraph.getContentPane().setBackground( Color.WHITE );
        currentGraph.getContentPane().setBackground( Color.WHITE );
        powerGraph.getContentPane().setBackground( Color.WHITE );

        voltageGraphPaint.setBounds(0,0,1080,610);
        currentGraphPaint.setBounds(0,0,1080,610);
        powerGraphPaint.setBounds(0,0,1080,610);


        Border border = BorderFactory.createLineBorder(Color.GREEN, 3, true);
        voltageGraph.getRootPane().setBorder(border);
        currentGraph.getRootPane().setBorder(border);
        powerGraph.getRootPane().setBorder(border);

        /*


            for(int i=0;i<9;i++){
                row[i]=new JLabel(4-i+"*10^-68");



                  double unit;

            if (check == 0) {
                unit = element.voltageValues[maxIndex] / 4.0;
                for (int i = 0; i < 9; i++) {
                    row[i].setText(element.voltageValues[maxIndex]-unit*i+"");

                }
            }
            else if (check == 1) {
                unit = element.voltageValues[maxIndex] / 4.0;
                for (int i = 0; i < 9; i++) {
                    row[i].setText(element.currentValues[maxIndex]-unit*i+ "");

                }
            }
            else if (check == 2) {
                unit = element.voltageValues[maxIndex] / 4.0;
                for (int i = 0; i < 9; i++) {
                    row[i].setText(powerValues[maxIndex]-unit*i+ "");

                }
            }






        */
        for(int i=0;i<9;i++)
            voltageGraph.add(row[i]);
        voltageGraph.setVisible(true);
        currentGraph.setVisible(true);
        powerGraph.setVisible(true);

    }
}
