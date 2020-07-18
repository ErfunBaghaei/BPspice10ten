import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.PublicKey;

public class DrawActionListener implements ActionListener {
    RunActionListener runActionListener;

    public DrawActionListener(RunActionListener runActionListener){
        this.runActionListener=runActionListener;
    }

    int check=1;

    GraphPaint voltageGraphPaint=new GraphPaint(runActionListener,0);
    GraphPaint currentGraphPaint=new GraphPaint(runActionListener,1);
    GraphPaint powerGraphPaint=new GraphPaint(runActionListener,2);

    @Override
    public void actionPerformed(ActionEvent a){
        Border border = BorderFactory.createLineBorder(Color.GREEN, 2, true);

        JFrame voltageGraph=new JFrame("Voltage");
        voltageGraph.setBounds(0,0,1030,540);

        JFrame currentGraph=new JFrame("Current");
        currentGraph.setBounds(0,280,1030,540);

        JFrame powerGraph=new JFrame("Power");
        powerGraph.setBounds(0,560,1030,540);

        voltageGraph.getContentPane().setBackground(Color.BLACK);

        voltageGraph.add(voltageGraphPaint);
        currentGraph.add(currentGraphPaint);
        powerGraph.add(powerGraphPaint);



        voltageGraph.setVisible(true);
        currentGraph.setVisible(true);
        powerGraph.setVisible(true);
    }
}
