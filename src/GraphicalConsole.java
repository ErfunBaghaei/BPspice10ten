import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;

import static java.awt.Component.CENTER_ALIGNMENT;

public class GraphicalConsole {
    InitialTextProccesor initialTextProccesor;
    GraphicalConsole(File file) {
         this.initialTextProccesor= new InitialTextProccesor(file);
    }
    public void run() {

        MapPoints mapPoints=new MapPoints();

        JTextField drawElement=new JTextField("r2");
        //First, you must choose a file...
        JTextArea textConsole=new JTextArea("* hi this is a test\n" +

        "l1 2 1 1m\n"+
        "v1 1 0 5 0 0 0\n"+
        "c2 2 0 1m\n"+
        ".tran 6m\n"+
        "dv 0.1m\n"+
        "dI 5m\n"+
        "dT 0.1m\n"+
                "END"
        );

        ImageIcon startImage=new ImageIcon("BPSPICEIMAGE.jpg");

        JLabel startImageLabel=new JLabel(startImage);

        JPanel loading=new JPanel();

        loading.add(startImageLabel);

        JButton run=new JButton("RUN");

        JButton draw=new JButton("DRAW");

        JButton output=new JButton("OUTPUT");

        JButton open=new JButton("OPEN");

        JFrame mainPage = new JFrame("BPSPICE10");

        JPanel outPutInformationsPanel=new JPanel();

        JTextArea outPutInformationsLabel=new JTextArea("Results will be shown here:");
        outPutInformationsLabel.setEditable(false);

        outPutInformationsLabel.setForeground(Color.WHITE);

        outPutInformationsLabel.setBackground(Color.BLUE);

        Font font = new Font(Font.SERIF, Font.PLAIN,  16);

        Font font1 = new Font(Font.SERIF, Font.PLAIN,  14);

        outPutInformationsLabel.setFont(font1.deriveFont(font1.getStyle() | Font.BOLD));


        outPutInformationsLabel.setFont(font1);




        textConsole.setFont(font);

        JFrame circuitFrame=new JFrame("Circuit Graph");

        JPanel circuit = new MapPoints();

        JMenuBar menuBar = new JMenuBar();

        circuit.setLayout(null);

        JLabel which=new JLabel("Type the name of the element to draw its graphs:");


        textConsole.setBounds(10,60,570,725);

        loading.setBounds(480,250,497,300);
        loading.setBackground(Color.white);

        run.setBounds(12,35,80,20);

        draw.setBounds(95,35,80,20);

        output.setBounds(178,35,90,20);

        open.setBounds(271,35,90,20);

        mainPage.setBounds(0,0,1800,990);

        circuit.setBounds(850,60,670,500);

        circuitFrame.setBounds(13,90,700,560);

        which.setBounds(370,34,270,20);

        drawElement.setBounds(643,35,70,22);

        which.setBackground(Color.gray);

        JScrollPane scroll = new JScrollPane (outPutInformationsLabel,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBounds(580,60,940,725);
        outPutInformationsLabel.setBounds(580,60,940,725);



        circuit.setBackground(Color.WHITE);



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



        ActionListener runActionListener=new RunActionListener(circuit,textConsole,file,circuitFrame,mainPage,outPutInformationsLabel,initialTextProccesor);
        run.addActionListener(runActionListener);

        ActionListener drawActionListener=new DrawActionListener((RunActionListener) runActionListener,((RunActionListener) runActionListener).initialTextProccesor,drawElement);
        draw.addActionListener(drawActionListener);


        Container container = mainPage.getContentPane();
        container.setBackground(Color.WHITE);
        Border border = BorderFactory.createLineBorder(new Color(19, 193, 30), 2, true);
        JRootPane rootPane = mainPage.getRootPane();

        drawElement.setBorder(border);
        open.setBorder(border);
        run.setBorder(border);
        draw.setBorder(border);
        output.setBorder(border);
        menuBar.setBorder(border);

        rootPane.setBorder(border);
        circuit.setBorder(border);
        textConsole.setBorder(border);
        outPutInformationsLabel.setBorder(border);
        border = BorderFactory.createLineBorder(new Color(19, 193, 30), 3, true);
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



        loading.setVisible(false);
        mainPage.setVisible(false);
        mainPage.add(textConsole);
        mainPage.add(scroll);
        mainPage.add(menuBar);
        mainPage.add(run);
        mainPage.add(open);
        mainPage.add(draw);
        mainPage.add(output);
        mainPage.add(which);
        mainPage.add(drawElement);
        mainPage.setVisible(true);


    }



}
