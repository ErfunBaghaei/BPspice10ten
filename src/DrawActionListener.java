import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.PublicKey;

public class DrawActionListener implements ActionListener {
    RunActionListener runActionListener;
    InitialTextProccesor initialTextProccesor;
    public DrawActionListener(RunActionListener runActionListener,InitialTextProccesor initialTextProccesor){
        this.runActionListener=runActionListener;
        this.initialTextProccesor=initialTextProccesor;
    }






    int check=1;

    JTextField whichElement=new JTextField();



    JFrame voltageGraph=new JFrame("Voltage");
    JFrame currentGraph=new JFrame("Current");
    JFrame powerGraph=new JFrame("Power");


    @Override
    public void actionPerformed(ActionEvent a){
        Element element=new Element();
        String name;
        name=whichElement.getText();
        for(int i=0;i<initialTextProccesor.elements.size();i++){
            if(name.equals(initialTextProccesor.elements.get(i).name)){
                element=initialTextProccesor.elements.get(i);
            }
        }


        GraphPaint voltageGraphPaint=new GraphPaint(runActionListener,0,initialTextProccesor,element);
        GraphPaint currentGraphPaint=new GraphPaint(runActionListener,1,initialTextProccesor,element);
        GraphPaint powerGraphPaint=new GraphPaint(runActionListener,2,initialTextProccesor,element);
        voltageGraph.add(voltageGraphPaint);
        currentGraph.add(currentGraphPaint);
        powerGraph.add(powerGraphPaint);

        voltageGraph.setBounds(0,0,1030,610);
        currentGraph.setBounds(0,643,1030,610);
        powerGraph.setBounds(560,0,1030,610);



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
