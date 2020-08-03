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
    JFrame circuitFrame, mainPage;
    JTextArea outPutInformationsLabel;
    InitialTextProccesor initialTextProccesor;
    Solver solver;

    public RunActionListener(JPanel circuit, JTextArea textConsole, File file, JFrame circuitFrame, JFrame mainPage, JTextArea outPutInformationsLabel, InitialTextProccesor initialTextProccesor) {
        this.circuit = circuit;
        this.file = file;
        this.textConsole = textConsole;
        this.circuitFrame = circuitFrame;
        this.mainPage = mainPage;
        this.outPutInformationsLabel = outPutInformationsLabel;
        this.initialTextProccesor = initialTextProccesor;
    }

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


        ErrorFinder errorFinder = new ErrorFinder(mainPage, initialTextProccesor);

        int flag1=0;
        int cont = 0;
        cont = initialTextProccesor.start();
        if (cont==0) {


            circuitDrawer(initialTextProccesor);
            circuitFrame.add(circuit);

            circuitFrame.setVisible(true);

            solver = new Solver(initialTextProccesor, circuitFrame);

       boolean flag = initialTextProccesor.create_union();
       if (flag) {

           initialTextProccesor.set_union();

           flag1=solver.mainsolver();
           if(flag1==0) {

               OutPutInformationPrint outPutInformationPrint = new OutPutInformationPrint(outPutInformationsLabel, initialTextProccesor, solver);

               outPutInformationsLabel.setText("");

               outPutInformationsLabel.setText(outPutInformationPrint.printOut());
           }
           else
               JOptionPane.showMessageDialog(mainPage, "There is an error,you can see the error descriptions from HELP", "Error "+flag1, JOptionPane.ERROR_MESSAGE);

       }
        }
        else if(cont==-4)
            JOptionPane.showMessageDialog(mainPage, "There is an error,you can see the error descriptions from HELP", "Error "+cont, JOptionPane.ERROR_MESSAGE);

    }

    public void circuitDrawer(InitialTextProccesor initialTextProccesor) {

        DrawKit drawKit = new DrawKit(initialTextProccesor, circuit);
        drawKit.circuitDrawer();
        circuit.revalidate();

        circuit.repaint();


    }


}
