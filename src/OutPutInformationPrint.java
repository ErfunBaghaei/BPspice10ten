import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class OutPutInformationPrint {
    JTextArea outPutInformationsLabel;
    InitialTextProccesor initialTextProccesor;
    Solver solver;
    OutPutInformationPrint(JTextArea outPutInformationsLabel, InitialTextProccesor initialTextProccesor,Solver solver) {
        this.outPutInformationsLabel = outPutInformationsLabel;
        this.initialTextProccesor = initialTextProccesor;
        this.solver=solver;
    }

    int lineNumber = 1,powerOfTen=1000;
    public String printOut() {
        File fileOut = new File("Result.txt");
System.out.println("llllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll"+lineNumber);
        String labelString = new String("Nodes:\n"+lineNumber + "- ");
        try {
            FileWriter out = new FileWriter(fileOut);
            out.write("Nodes:\n"+lineNumber + "- ");
            lineNumber++;
            for (int i = 1; i < solver.nodesInOrder.size(); i++) {

                labelString = labelString + solver.nodesInOrder.get(i).name + "  >   ";
                out.write(solver.nodesInOrder.get(i).name + "  >   ");
                System.out.print(solver.nodesInOrder.get(i).name + "  >   ");
                for (int j = 0; j < (int) Math.ceil(initialTextProccesor.time / initialTextProccesor.deltat); j++) {
                    if(j%16==0&&j!=0) {
                        out.write("\n"+lineNumber+"-  ");
                        labelString += "\n"+lineNumber+"-  ";
                        lineNumber++;
                    }
                    labelString = labelString + (double) Math.round( solver.nodesInOrder.get(i).voltageValues[j]* powerOfTen) / powerOfTen + "|   ";
                    out.write((double) Math.round( solver.nodesInOrder.get(i).voltageValues[j]* powerOfTen) / powerOfTen + "|   ");
                    System.out.print(solver.nodesInOrder.get(i).voltageValues[j] + "|   ");
                }


                System.out.println();
                out.write("\n"+lineNumber+"- ");
                if(i != solver.nodesInOrder.size() - 1) {
                    labelString = labelString + "\n" + lineNumber + "- ";
                    lineNumber++;
                }

            }

            out.write("\n\n***************************************************************************************************\n\nElements:\n");
            out.write(lineNumber + " - ");
            labelString = labelString +"\n\n***************************************************************************************************\n\nElements:\n";
            labelString = labelString + lineNumber + " - ";
            lineNumber++;
            for (int i = 0; i < initialTextProccesor.elements.size(); i++) {

                labelString = labelString + initialTextProccesor.elements.get(i).name + "  >   ";
                out.write(initialTextProccesor.elements.get(i).name + "  >   ");
                System.out.print(initialTextProccesor.elements.get(i).name + "  > " + "  ");
                for (int j = 0; j < (int) Math.ceil(initialTextProccesor.time / initialTextProccesor.deltat); j++) {
                    if(j%5==0&&j!=0) {
                        out.write("\n"+lineNumber+" -  ");
                        labelString += "\n"+lineNumber+" -  ";
                        lineNumber++;
                    }
                    labelString = labelString + " v: " +(double) Math.round( initialTextProccesor.elements.get(i).voltageValues[j]* powerOfTen) / powerOfTen
                            + " i: " +(double) Math.round( initialTextProccesor.elements.get(i).currentValues[j]* powerOfTen) / powerOfTen
                            + " p: " + (double) Math.round( initialTextProccesor.elements.get(i).voltageValues[j]*initialTextProccesor.elements.get(i).currentValues[j]* powerOfTen) / powerOfTen+"|   ";
                    out.write(" v: " +(double) Math.round( initialTextProccesor.elements.get(i).voltageValues[j]* powerOfTen) / powerOfTen
                            + " i: " +(double) Math.round( initialTextProccesor.elements.get(i).currentValues[j]* powerOfTen) / powerOfTen
                            + " p: " + (double) Math.round( initialTextProccesor.elements.get(i).voltageValues[j]*initialTextProccesor.elements.get(i).currentValues[j]* powerOfTen) / powerOfTen+"|   ");

                    System.out.print(" v: " + initialTextProccesor.elements.get(i).voltageValues[j]);
                    System.out.print(" i: " + initialTextProccesor.elements.get(i).currentValues[j]);
                    System.out.print(" p: " + initialTextProccesor.elements.get(i).voltageValues[j]
                            * initialTextProccesor.elements.get(i).currentValues[j] + "|   ");


                }

                out.write("\n\n"+lineNumber+"- ");
                labelString+="\n";
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
