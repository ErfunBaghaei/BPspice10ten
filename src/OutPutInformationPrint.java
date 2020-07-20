import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class OutPutInformationPrint {
    JLabel outPutInformationsLabel;
    InitialTextProccesor initialTextProccesor;

    OutPutInformationPrint(JLabel outPutInformationsLabel, InitialTextProccesor initialTextProccesor) {
        this.outPutInformationsLabel = outPutInformationsLabel;
        this.initialTextProccesor = initialTextProccesor;
    }

    public void printOut() {
        File fileOut = new File("Result");
        String labelString = new String(outPutInformationsLabel.getText());
        try {
            FileWriter out = new FileWriter(fileOut);


            for (int i = 0; i < initialTextProccesor.nodesInOrder.size(); i++) {
                labelString = labelString + initialTextProccesor.nodesInOrder.get(i).name + " >>>  ";
                out.write(initialTextProccesor.nodesInOrder.get(i).name + " >>>  ");
                System.out.print(initialTextProccesor.nodesInOrder.get(i).name + " >>>  ");
                for (int j = 0; j < (int) initialTextProccesor.time / initialTextProccesor.deltat; j++) {
                    labelString = labelString + initialTextProccesor.nodesInOrder.get(i).voltageValues[j] + " | ";
                    out.write(initialTextProccesor.nodesInOrder.get(i).voltageValues[j] + " | ");
                    System.out.print(initialTextProccesor.nodesInOrder.get(i).voltageValues[j] + " | ");
                }
                System.out.println();
                labelString = labelString + "\n";

            }


            for (int i = 0; i < initialTextProccesor.elements.size(); i++) {
                labelString = labelString + initialTextProccesor.elements.get(i).name + " >>>  ";
                out.write(initialTextProccesor.elements.get(i).name + " >>>  ");
                System.out.print(initialTextProccesor.elements.get(i).name + " >>>  ");
                for (int j = 0; j < (int) initialTextProccesor.time / initialTextProccesor.deltat; j++) {
                    labelString = labelString + "v: " + initialTextProccesor.elements.get(i).voltageValues[j]
                            + " i: " + initialTextProccesor.elements.get(i).currentValues[j]
                            + " p: " + initialTextProccesor.elements.get(i).voltageValues[j]
                            * initialTextProccesor.elements.get(i).currentValues[j] + " | ";
                    out.write("v: " + initialTextProccesor.elements.get(i).voltageValues[j]
                            + " i: " + initialTextProccesor.elements.get(i).currentValues[j]
                            + " p: " + initialTextProccesor.elements.get(i).voltageValues[j]
                            * initialTextProccesor.elements.get(i).currentValues[j] + " | ");

                    System.out.print("v: " + initialTextProccesor.elements.get(i).voltageValues[j]);
                    System.out.print(" i: " + initialTextProccesor.elements.get(i).currentValues[j]);
                    System.out.print(" p: " + initialTextProccesor.elements.get(i).voltageValues[j]
                            * initialTextProccesor.elements.get(i).currentValues[j] + " | ");


                }
                labelString = labelString + "\n";
                System.out.println();

            }
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }


}
