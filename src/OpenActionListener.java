import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class OpenActionListener implements ActionListener {
    JFrame mainPage;
    FileSystemView fsv;
    File file = new File("input.txt");
    JFileChooser fileChooser = new JFileChooser(file, fsv);
    JTextArea consoleText;
    public OpenActionListener(JFrame mainPage,JTextArea consoleText){
        this.mainPage=mainPage;
        this.consoleText=consoleText;

    }

    @Override
    public void actionPerformed(ActionEvent a) {

        fsv = FileSystemView.getFileSystemView();
        int response = fileChooser.showOpenDialog(mainPage);
        file=fileChooser.getSelectedFile();
        String wholeText="";
        String[] fileLines=new String[100];
        int lineNumber=0;
        try
        {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()){
                fileLines[lineNumber]=fileScanner.nextLine();

                wholeText=wholeText+fileLines[lineNumber]+"\n";
                lineNumber++;
            }

        }
        catch (FileNotFoundException e){
            System.out.print("File not found !");
        }
        consoleText.setText(wholeText);

    }
}