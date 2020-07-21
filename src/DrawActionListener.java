import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.PublicKey;

public class DrawActionListener implements ActionListener {
    RunActionListener runActionListener;
    InitialTextProccesor initialTextProccesor;

    JTextField whichElement;
    public DrawActionListener(RunActionListener runActionListener,InitialTextProccesor initialTextProccesor,JTextField whichElement){
        this.runActionListener=runActionListener;
        this.initialTextProccesor=initialTextProccesor;
        this.whichElement=whichElement;
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

        voltageGraph.setBounds(0,0,1026,604);
        currentGraph.setBounds(0,643,1026,604);
        powerGraph.setBounds(560,0,1026,604);



        voltageGraphPaint.setBounds(0,0,1030,610);
        currentGraphPaint.setBounds(0,0,1030,610);
        powerGraphPaint.setBounds(0,0,1030,610);


        Border border = BorderFactory.createLineBorder(Color.GREEN, 2, true);
        voltageGraphPaint.setBorder(border);
        currentGraphPaint.setBorder(border);
        powerGraphPaint.setBorder(border);


        voltageGraph.setVisible(true);
        currentGraph.setVisible(true);
        powerGraph.setVisible(true);

    }
}
