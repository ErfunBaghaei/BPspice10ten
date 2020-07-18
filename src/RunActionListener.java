import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RunActionListener implements ActionListener {
    JPanel circuit;
    File file;
    JTextArea textConsole;
    public RunActionListener(JPanel circuit,JTextArea textConsole , File file){
        this.circuit=circuit;
        this.file=file;
        this.textConsole=textConsole;
    }

    InitialTextProccesor initialTextProccesor;
    @Override
    public void actionPerformed(ActionEvent a) {
        String updatedText = textConsole.getText();
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(updatedText);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        initialTextProccesor = new InitialTextProccesor(file);

        initialTextProccesor.start();

        circuitDrawer(initialTextProccesor);


        initialTextProccesor.create_union();
        initialTextProccesor.set_union();
        initialTextProccesor.solve();


    }

    public void circuitDrawer(InitialTextProccesor initialTextProccesor){

        DrawKit drawKit=new DrawKit(initialTextProccesor, circuit);
        drawKit.circuitDrawer();

        // drawKit.wireDrawer(1,9);
        //   drawKit.wireDrawer(25,17);
        //   drawKit.wireDrawer(2,1);
        //   drawKit.capacitorDrawer(2,3);
        //   drawKit.wireDrawer(4,3);
        //   drawKit.wireDrawer(4,5);
        //   drawKit.resistorDrawer(6,5);
        //   drawKit.wireDrawer(7,6);
        //   drawKit.wireDrawer(7,15);
        //   drawKit.capacitorDrawer(15,23);
        //   drawKit.wireDrawer(31,23);
        //   drawKit.wireDrawer(31,30);
        //   drawKit.inductorDrawer(30,29);
        //   drawKit.wireDrawer(29,28);
        //   drawKit.diodeDrawer(28,27);
        //   drawKit.wireDrawer(27,26);
        //   drawKit.voltageSourceDrawer(26,25);
        //   drawKit.currentSourceDrawer(9,17);





        circuit.revalidate();

        circuit.repaint();


    }



}
