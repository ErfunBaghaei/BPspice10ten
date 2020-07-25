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
    JFrame circuitFrame,mainPage;
    JTextArea outPutInformationsLabel;
    InitialTextProccesor initialTextProccesor;
    Solver solver;
    public RunActionListener(JPanel circuit,JTextArea textConsole , File file,JFrame circuitFrame,JFrame mainPage,JTextArea outPutInformationsLabel,InitialTextProccesor initialTextProccesor){
        this.circuit=circuit;
        this.file=file;
        this.textConsole=textConsole;
        this.circuitFrame=circuitFrame;
        this.mainPage=mainPage;
        this.outPutInformationsLabel=outPutInformationsLabel;
        this.initialTextProccesor=initialTextProccesor;
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


        boolean cont=true;
        cont = initialTextProccesor.start();
        if (cont) {
            errorFinder.find();

            circuitDrawer(initialTextProccesor);
            circuitFrame.add(circuit);

            circuitFrame.setVisible(true);

            solver= new Solver(initialTextProccesor);

            initialTextProccesor.create_union();

            initialTextProccesor.set_union();

            solver.mainsolver();

            OutPutInformationPrint outPutInformationPrint = new OutPutInformationPrint(outPutInformationsLabel, initialTextProccesor,solver);

            outPutInformationsLabel.setText("");

            outPutInformationsLabel.setText(outPutInformationPrint.printOut());



        }
    }

    public void circuitDrawer(InitialTextProccesor initialTextProccesor){

        DrawKit drawKit=new DrawKit(initialTextProccesor, circuit);
        drawKit.circuitDrawer();
        circuit.revalidate();

        circuit.repaint();


    }



}
