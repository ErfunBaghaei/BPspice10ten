import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class OutPutInformationPrint {
    JTextArea outPutInformationsLabel;
    InitialTextProccesor initialTextProccesor;

    OutPutInformationPrint(JTextArea outPutInformationsLabel, InitialTextProccesor initialTextProccesor) {
        this.outPutInformationsLabel = outPutInformationsLabel;
        this.initialTextProccesor = initialTextProccesor;
    }

    int lineNumber = 1;

    public String printOut() {
        File fileOut = new File("Result");
        String labelString = new String("Nodes:\n "+lineNumber + "- ");
        try {
            FileWriter out = new FileWriter(fileOut);

            lineNumber++;
            for (int i = 0; i < initialTextProccesor.nodesInOrder.size(); i++) {

                labelString = labelString + initialTextProccesor.nodesInOrder.get(i).name + "  >   ";
                out.write(initialTextProccesor.nodesInOrder.get(i).name + "  >   ");
                System.out.print(initialTextProccesor.nodesInOrder.get(i).name + "  >   ");
                for (int j = 0; j < (int) initialTextProccesor.time / initialTextProccesor.deltat; j++) {
                    if(j%27==0&&j!=0) {
                        labelString += "\n"+lineNumber+"-  ";
                        lineNumber++;
                    }
                    labelString = labelString + initialTextProccesor.nodesInOrder.get(i).voltageValues[j] + " | ";
                    out.write(initialTextProccesor.nodesInOrder.get(i).voltageValues[j] + " | ");
                    System.out.print(initialTextProccesor.nodesInOrder.get(i).voltageValues[j] + " | ");
                }
                System.out.println();
                if(i != initialTextProccesor.nodesInOrder.size() - 1) {
                    labelString = labelString + "\n" + lineNumber + "- ";
                    lineNumber++;
                }

            }

            labelString = labelString +"\n\n***********************************************************************************\n\nElements:\n ";
            labelString = labelString + lineNumber + " - ";
            lineNumber++;
            for (int i = 0; i < initialTextProccesor.elements.size(); i++) {
                labelString = labelString + initialTextProccesor.elements.get(i).name + "  >   ";
                out.write(initialTextProccesor.elements.get(i).name + "  >   ");
                System.out.print(initialTextProccesor.elements.get(i).name + "  > " + "  ");
                for (int j = 0; j < (int) initialTextProccesor.time / initialTextProccesor.deltat; j++) {
                    if(j%6==0&&j!=0) {
                        labelString += "\n "+lineNumber+" -  ";
                        lineNumber++;
                    }
                    labelString = labelString + " v: " + initialTextProccesor.elements.get(i).voltageValues[j]
                            + " i: " + initialTextProccesor.elements.get(i).currentValues[j]
                            + " p: " + initialTextProccesor.elements.get(i).voltageValues[j]
                            * initialTextProccesor.elements.get(i).currentValues[j] + " | ";
                    out.write(" v: " + initialTextProccesor.elements.get(i).voltageValues[j]
                            + " i: " + initialTextProccesor.elements.get(i).currentValues[j]
                            + " p: " + initialTextProccesor.elements.get(i).voltageValues[j]
                            * initialTextProccesor.elements.get(i).currentValues[j] + " | ");

                    System.out.print(" v: " + initialTextProccesor.elements.get(i).voltageValues[j]);
                    System.out.print(" i: " + initialTextProccesor.elements.get(i).currentValues[j]);
                    System.out.print(" p: " + initialTextProccesor.elements.get(i).voltageValues[j]
                            * initialTextProccesor.elements.get(i).currentValues[j] + " | ");


                }
                if (i != initialTextProccesor.elements.size() - 1)
                    labelString = labelString + "\n" + lineNumber + "- ";
                System.out.println();

                lineNumber++;
            }
            out.close();
            return labelString;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;

    }
}
