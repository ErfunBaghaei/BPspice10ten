import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class OutPutInformationPrint {
    JFrame mainPage;
    InitialTextProccesor initialTextProccesor;
    OutPutInformationPrint(JFrame mainPage,InitialTextProccesor initialTextProccesor){
        this.mainPage=mainPage;
        this.initialTextProccesor=initialTextProccesor;
    }
    public void printOut() {
        File fileOut=new File("Result");
        try {
            FileWriter out = new FileWriter(fileOut);



            for(int i=0;i<initialTextProccesor.nodesInOrder.size();i++){
                out.write(initialTextProccesor.nodesInOrder.get(i).name+" >>>  ");
                System.out.print(initialTextProccesor.nodesInOrder.get(i).name+" >>>  ");
                for(int j=0;j<(int)initialTextProccesor.time/initialTextProccesor.deltat;j++){
                    out.write(initialTextProccesor.nodesInOrder.get(i).voltageValues[j]+" | ");
                    System.out.print(initialTextProccesor.nodesInOrder.get(i).voltageValues[j]+" | ");
                }
                System.out.println();

            }



            for(int i=0;i<initialTextProccesor.elements.size();i++){
                out.write(initialTextProccesor.elements.get(i).name+" >>>  ");
                System.out.print(initialTextProccesor.elements.get(i).name+" >>>  ");
                for(int j=0;j<(int)initialTextProccesor.time/initialTextProccesor.deltat;j++){
                    out.write("v: "+initialTextProccesor.elements.get(i).voltageValues[j]
                            +" i: "+initialTextProccesor.elements.get(i).currentValues[j]
                            +" p: "+initialTextProccesor.elements.get(i).voltageValues[j]
                            *initialTextProccesor.elements.get(i).currentValues[j]+" | ");

                    System.out.print("v: "+initialTextProccesor.elements.get(i).voltageValues[j]);
                    System.out.print(" i: "+initialTextProccesor.elements.get(i).currentValues[j]);
                    System.out.print(" p: "+initialTextProccesor.elements.get(i).voltageValues[j]
                            *initialTextProccesor.elements.get(i).currentValues[j]+" | ");


                }
                System.out.println();

            }
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }









}
