import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;

public class GraphicalConsole {


    public void run() {

        MapPoints mapPoints=new MapPoints();
//* hi this is a test
//v1 0 1 1 0 0 0
//r1 0 1 5
//r3 1 2 2
//c1 0 2 5
//r2 0 2 4
//.tran 10m
//dv 1m
//dI 1m
//dT 10u
        //"HI,Welcome to BPSPICE 10 \n First you should open a txt file..."
        JTextArea textConsole=new JTextArea("* hi this is a test\n" +
                "l1 1 2 1\n" +
                "c1 1 2 2\n"+
                "c2 0 2 1\n" +
                "l3 0 2 2\n"+
                "l2 0 1 1\n" +
                "i1 0 1 5 0 0 0\n"+
                "r1 0 3 1\n"+
                "v2 1 3 4 0 0 0\n"+
                "r2 1 3 1\n"+
                "r3 0 3 1\n"+
                "v1 2 3 5 0 0 0\n"+
                "r4 3 2 4\n"+
                ".tran 10m\n" +
                "dv 1m\n" +
                "dI 1m\n" +
                "dT 10u");

        ImageIcon startImage=new ImageIcon("BPSPICEIMAGE.jpg");

        JLabel startImageLabel=new JLabel(startImage);

        JPanel loading=new JPanel();

        Font  font = new Font(Font.SERIF, Font.PLAIN,  16);

        loading.add(startImageLabel);

        JButton run=new JButton("RUN");

        JButton draw=new JButton("DRAW");

        JButton output=new JButton("OUTPUT");

        JButton open=new JButton("OPEN");

        JFrame mainPage = new JFrame("BPSPICE10");

        JLabel outPutInformationsLabel=new JLabel("Results will be shown here",SwingConstants.LEFT);

        outPutInformationsLabel.setBounds(850,480,670,305);

        outPutInformationsLabel.setBackground(Color.BLUE);

        outPutInformationsLabel.setForeground(Color.WHITE);


        textConsole.setFont(font);


        JPanel circuit = new MapPoints();
        JPanel outPutInformations=new JPanel();
        JMenuBar menuBar = new JMenuBar();
        circuit.setLayout(null);




        textConsole.setBounds(10,60,840,725);

        loading.setBounds(480,250,497,300);

        run.setBounds(12,35,80,20);

        draw.setBounds(95,35,80,20);

        output.setBounds(178,35,90,20);

        open.setBounds(271,35,90,20);

        mainPage.setBounds(0,1,1800,1000);

        circuit.setBounds(850,60,670,500);

        outPutInformations.setBounds(850,560,670,225);


        circuit.setBackground(Color.WHITE);
        outPutInformations.setBackground(Color.BLUE);



        mainPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ActionListener openActionListener=new OpenActionListener(mainPage,textConsole);


        JMenu menu1 = new JMenu("File");
        JMenu menu2 = new JMenu("Edit");
        JMenu menu3 = new JMenu("Help");
        JMenuItem i1, i2, i3, i4, i5, i6, i7, i8,
                i9, i10, i11, i12, i13, i14, i15, i16, i17;
        i1 = new JMenuItem("Open");
        i2 = new JMenuItem("Save as...");
        i3 = new JMenuItem("Save All");
        i4 = new JMenuItem("Delete Procject");
        i5 = new JMenuItem("Print");
        i6 = new JMenuItem("Copy");
        i7 = new JMenuItem("Cut");
        i8 = new JMenuItem("Paste");
        i9 =new JMenuItem("Redo");
        i10 = new JMenuItem("Undo");
        i11 = new JMenuItem("Delete");
        i12 = new JMenuItem("Find");
        i13 = new JMenuItem("Replace");
        i14 = new JMenuItem("HOW TO WORK WITH BPSPICE10?");
        i15 = new JMenuItem("README");
        i16 = new JMenuItem("LICENSE");
        i17 = new JMenuItem("ABOUT US");



        menu1.add(i1);
        menu1.add(i2);
        menu1.add(i3);
        menu1.add(i4);
        menu1.add(i5);
        menu2.add(i6);
        menu2.add(i7);
        menu2.add(i8);
        menu2.add(i9);
        menu2.add(i10);
        menu2.add(i11);
        menu2.add(i12);
        menu2.add(i13);
        menu3.add(i14);
        menu3.add(i15);
        menu3.add(i16);
        menu3.add(i17);
        menuBar.add(menu1);
        menuBar.add(menu2);
        menuBar.add(menu3);

        i1.addActionListener(openActionListener);
        open.addActionListener(openActionListener);

        File file=new File("input.txt");
        file= ((OpenActionListener) openActionListener).file;



        ActionListener runActionListener=new RunActionListener(circuit,textConsole,file);
        run.addActionListener(runActionListener);

        ActionListener drawActionListener=new DrawActionListener((RunActionListener) runActionListener);
        draw.addActionListener(drawActionListener);


        Container container = mainPage.getContentPane();
        container.setBackground(Color.WHITE);
        Border border = BorderFactory.createLineBorder(Color.GREEN, 3, true);
        JRootPane rootPane = mainPage.getRootPane();



        rootPane.setBorder(border);
        circuit.setBorder(border);
        textConsole.setBorder(border);
        outPutInformations.setBorder(border);

        mainPage.setLayout(null);





        menuBar.setBounds(0, 0, 1800, 30);
        mainPage.setVisible(true);
        mainPage.add(loading);

        try
        {
            Thread.sleep(1000);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }





        outPutInformations.add(outPutInformationsLabel);

        loading.setVisible(false);
        mainPage.setVisible(false);
        mainPage.add(textConsole);
        mainPage.add(circuit);
        mainPage.add(outPutInformations);
        mainPage.add(menuBar);
        mainPage.add(run);
        mainPage.add(open);
        mainPage.add(draw);
        mainPage.add(output);
        mainPage.setVisible(true);


    }



}
