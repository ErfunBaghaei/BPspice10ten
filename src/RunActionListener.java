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
    JFrame circuitFrame;
    public RunActionListener(JPanel circuit,JTextArea textConsole , File file,JFrame circuitFrame){
        this.circuit=circuit;
        this.file=file;
        this.textConsole=textConsole;
        this.circuitFrame=circuitFrame;
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
        circuitFrame.add(circuit);
        circuitFrame.setVisible(true);

        initialTextProccesor.create_union();
        initialTextProccesor.set_union();
        //initialTextProccesor.solve();


    }

    public void circuitDrawer(InitialTextProccesor initialTextProccesor){

        DrawKit drawKit=new DrawKit(initialTextProccesor, circuit);
        drawKit.circuitDrawer();
        circuit.revalidate();

        circuit.repaint();


    }



}
