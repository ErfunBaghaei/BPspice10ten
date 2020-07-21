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
    JFrame graph=new JFrame("Graphs");
    JPanel graphPanel=new JPanel();


    @Override
    public void actionPerformed(ActionEvent a){
System.out.println(initialTextProccesor.time+"kiikikikik");

        GraphPaint voltageGraphPaint=new GraphPaint(runActionListener,0,initialTextProccesor);
        GraphPaint currentGraphPaint=new GraphPaint(runActionListener,1,initialTextProccesor);
        GraphPaint powerGraphPaint=new GraphPaint(runActionListener,2,initialTextProccesor);


        JScrollPane scroll = new JScrollPane (graphPanel,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setBounds(0,0,1030,1620);

        graph.setBounds(0,0,1030,1620);

        graphPanel.setBounds(0,0,1030,1620);

        Border border = BorderFactory.createLineBorder(Color.GREEN, 2, true);


        graphPanel.setBackground(Color.BLACK);

        graphPanel.add(voltageGraphPaint);
        graphPanel.add(currentGraphPaint);
        graphPanel.add(powerGraphPaint);


        graph.add(scroll);
        graph.setVisible(true);
    }
}
