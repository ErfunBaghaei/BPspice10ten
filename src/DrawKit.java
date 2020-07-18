import javax.swing.*;
import java.awt.desktop.SystemEventListener;
import java.util.ArrayList;
import java.util.HashMap;

public class DrawKit {

    DrawKit() {
    }

    InitialTextProccesor initialTextProccesor;
    JPanel circuit;
    PixelCoordinate[] nodeCoordiantes = new PixelCoordinate[60];
    HashMap<Integer, PixelCoordinate> nodeCoordinates = new HashMap<Integer, PixelCoordinate>();
    HashMap<Integer, String> nodeCodes = new HashMap<Integer, String>();
    HashMap<Integer, Node> nodeIndex = new HashMap<Integer, Node>();


    public DrawKit(InitialTextProccesor initialTextProccesor, JPanel circuit) {
        this.initialTextProccesor = initialTextProccesor;
        this.circuit = circuit;
        for (int i = 1; i < 9; i++) {
            for (int j = 0; j < 6; j++) {
                nodeCoordiantes[i + j * 8] = new PixelCoordinate(82 * i - 30, 451 - j * 82);
                nodeCoordinates.put(i + j * 8, nodeCoordiantes[i + j * 8]);
            }
        }
    }

    public void groundDrawer() {
        ImageIcon gndImage = new ImageIcon("gnd.png");
        JLabel gnd = new JLabel(gndImage);
        nodeDrawer(4);
        gnd.setBounds(nodeCoordinates.get(10).x + 134, nodeCoordinates.get(10).y + 83, 54, 34);
        circuit.add(gnd);

    }

    public void resistorDrawer(int nodeP, int nodeM) {
        nodeDrawer(nodeP);
        nodeDrawer(nodeM);

        ImageIcon resistorHorizontalimage = new ImageIcon("resistorHorizontal.png");
        ImageIcon resistorVerticalimage = new ImageIcon("resistorVertical.png");
        JLabel resistorHorizontal;
        JLabel resistorVertical;
        int min = minimum(nodeP, nodeM);
        int max = maximum(nodeP, nodeM);
        if (nodeP - nodeM == 1 || nodeM - nodeP == 1) {
            resistorHorizontal = new JLabel(resistorHorizontalimage);
            resistorHorizontal.setBounds(nodeCoordinates.get(min).x, nodeCoordinates.get(min).y - 40, 80, 80);

            circuit.add(resistorHorizontal);
        } else if (nodeP - nodeM == 8 || nodeM - nodeP == 8) {
            resistorVertical = new JLabel(resistorVerticalimage);
            resistorVertical.setBounds(nodeCoordinates.get(max).x - 42, nodeCoordinates.get(max).y, 80, 80);

            circuit.add(resistorVertical);
        }
    }

    public void diodeDrawer(int nodeP, int nodeM) {
        nodeDrawer(nodeP);
        nodeDrawer(nodeM);

        ImageIcon diodeUpimage = new ImageIcon("diodeUp.png");
        ImageIcon diodeDownimage = new ImageIcon("diodeDown.png");
        ImageIcon diodeRightimage = new ImageIcon("diodeRight.png");
        ImageIcon diodeLeftimage = new ImageIcon("diodeLeft.png");
        JLabel diodeUp;
        JLabel diodeDown;
        JLabel diodeRight;
        JLabel diodeLeft;
        int min = minimum(nodeP, nodeM);
        int max = maximum(nodeP, nodeM);
        if (nodeM - nodeP == 1) {
            diodeRight = new JLabel(diodeRightimage);
            diodeRight.setBounds(nodeCoordinates.get(min).x, nodeCoordinates.get(min).y - 40, 80, 80);

            circuit.add(diodeRight);
        } else if (nodeP - nodeM == 8) {
            diodeDown = new JLabel(diodeDownimage);
            diodeDown.setBounds(nodeCoordinates.get(max).x - 42, nodeCoordinates.get(max).y, 80, 80);

            circuit.add(diodeDown);
        } else if (nodeM - nodeP == 8) {
            diodeUp = new JLabel(diodeUpimage);
            diodeUp.setBounds(nodeCoordinates.get(max).x - 42, nodeCoordinates.get(max).y, 80, 80);

            circuit.add(diodeUp);
        }
        if (nodeP - nodeM == 1) {
            diodeLeft = new JLabel(diodeLeftimage);
            diodeLeft.setBounds(nodeCoordinates.get(min).x, nodeCoordinates.get(min).y - 40, 80, 80);

            circuit.add(diodeLeft);
        }

    }

    public void voltageSourceDrawer(int nodeP, int nodeM) {
        nodeDrawer(nodeP);
        nodeDrawer(nodeM);

        ImageIcon voltageSourceUpimage = new ImageIcon("voltageSourceUp.png");
        ImageIcon voltageSourceDownimage = new ImageIcon("voltageSourceDown.png");
        ImageIcon voltageSourceRightimage = new ImageIcon("voltageSourceRight.png");
        ImageIcon voltageSourceLeftimage = new ImageIcon("voltageSourceLeft.png");
        JLabel voltageSourceUp;
        JLabel voltageSourceDown;
        JLabel voltageSourceRight;
        JLabel voltageSourceLeft;
        int min = minimum(nodeP, nodeM);
        int max = maximum(nodeP, nodeM);
        if (nodeM - nodeP == 1) {
            voltageSourceRight = new JLabel(voltageSourceRightimage);
            voltageSourceRight.setBounds(nodeCoordinates.get(min).x, nodeCoordinates.get(min).y - 40, 80, 80);

            circuit.add(voltageSourceRight);
        } else if (nodeP - nodeM == 8) {
            voltageSourceDown = new JLabel(voltageSourceDownimage);
            voltageSourceDown.setBounds(nodeCoordinates.get(max).x - 42, nodeCoordinates.get(max).y, 80, 80);

            circuit.add(voltageSourceDown);
        } else if (nodeM - nodeP == 8) {
            voltageSourceUp = new JLabel(voltageSourceUpimage);
            voltageSourceUp.setBounds(nodeCoordinates.get(max).x - 42, nodeCoordinates.get(max).y, 80, 80);

            circuit.add(voltageSourceUp);
        }
        if (nodeP - nodeM == 1) {
            voltageSourceLeft = new JLabel(voltageSourceLeftimage);
            voltageSourceLeft.setBounds(nodeCoordinates.get(min).x, nodeCoordinates.get(min).y - 40, 80, 80);

            circuit.add(voltageSourceLeft);
        }


    }

    public void currentSourceDrawer(int nodeP, int nodeM) {
        nodeDrawer(nodeP);
        nodeDrawer(nodeM);

        ImageIcon currentSourceUpimage = new ImageIcon("currentSourceUp.png");
        ImageIcon currentSourceDownimage = new ImageIcon("currentSourceDown.png");
        ImageIcon currentSourceRightimage = new ImageIcon("currentSourceRight.png");
        ImageIcon currentSourceLeftimage = new ImageIcon("currentSourceLeft.png");
        JLabel currentSourceUp;
        JLabel currentSourceDown;
        JLabel currentSourceRight;
        JLabel currentSourceLeft;
        int min = minimum(nodeP, nodeM);
        int max = maximum(nodeP, nodeM);

        if (nodeM - nodeP == 1) {
            currentSourceRight = new JLabel(currentSourceRightimage);
            currentSourceRight.setBounds(nodeCoordinates.get(min).x, nodeCoordinates.get(min).y - 40, 80, 80);

            circuit.add(currentSourceRight);
        } else if (nodeP - nodeM == 8) {
            currentSourceDown = new JLabel(currentSourceDownimage);
            currentSourceDown.setBounds(nodeCoordinates.get(max).x - 42, nodeCoordinates.get(max).y, 80, 80);

            circuit.add(currentSourceDown);
        } else if (nodeM - nodeP == 8) {
            currentSourceUp = new JLabel(currentSourceUpimage);
            currentSourceUp.setBounds(nodeCoordinates.get(max).x - 42, nodeCoordinates.get(max).y, 80, 80);

            circuit.add(currentSourceUp);
        }
        if (nodeP - nodeM == 1) {
            currentSourceLeft = new JLabel(currentSourceLeftimage);
            currentSourceLeft.setBounds(nodeCoordinates.get(min).x, nodeCoordinates.get(min).y - 40, 80, 80);

            circuit.add(currentSourceLeft);
        }

    }

    public void capacitorDrawer(int nodeP, int nodeM) {
        nodeDrawer(nodeP);
        nodeDrawer(nodeM);

        ImageIcon capacitorHorizontalimage = new ImageIcon("capacitorHorizontal.png");
        ImageIcon capacitorVerticalimage = new ImageIcon("capacitorVertical.png");

        JLabel capacitorHorizontal;
        JLabel capacitorVertical;
        int min = minimum(nodeP, nodeM);
        int max = maximum(nodeP, nodeM);
        if (nodeP - nodeM == 1 || nodeM - nodeP == 1) {
            capacitorHorizontal = new JLabel(capacitorHorizontalimage);
            capacitorHorizontal.setBounds(nodeCoordinates.get(min).x, nodeCoordinates.get(min).y - 40, 80, 80);

            circuit.add(capacitorHorizontal);
        } else if (nodeP - nodeM == 8 || nodeM - nodeP == 8) {
            capacitorVertical = new JLabel(capacitorVerticalimage);
            System.out.println(nodeCoordinates.get(max).x);
            capacitorVertical.setBounds(nodeCoordinates.get(max).x - 42, nodeCoordinates.get(max).y, 80, 80);

            circuit.add(capacitorVertical);
        }

    }

    public void inductorDrawer(int nodeP, int nodeM) {
        nodeDrawer(nodeP);
        nodeDrawer(nodeM);

        ImageIcon inductorHorizontalimage = new ImageIcon("inductorHorizontal.png");
        ImageIcon inductorVerticalimage = new ImageIcon("inductorVertical.png");

        JLabel inductorHorizontal;
        JLabel inductorVertical;
        int min = minimum(nodeP, nodeM);
        int max = maximum(nodeP, nodeM);
        if (nodeP - nodeM == 1 || nodeM - nodeP == 1) {
            inductorHorizontal = new JLabel(inductorHorizontalimage);
            inductorHorizontal.setBounds(nodeCoordinates.get(min).x - 1, nodeCoordinates.get(min).y - 41, 80, 80);

            circuit.add(inductorHorizontal);
        } else if (nodeP - nodeM == 8 || nodeM - nodeP == 8) {
            inductorVertical = new JLabel(inductorVerticalimage);
            System.out.println(nodeCoordinates.get(max).x);
            inductorVertical.setBounds(nodeCoordinates.get(max).x - 41, nodeCoordinates.get(max).y + 1, 80, 80);

            circuit.add(inductorVertical);
        }


    }

    public void wireDrawer(int nodeP, int nodeM) {

        ImageIcon wireHorizontalimage = new ImageIcon("wireHorizontal.png");
        ImageIcon wireVerticalimage = new ImageIcon("wireVertical.png");

        JLabel wireHorizontal;
        JLabel wireVertical;
        int min = minimum(nodeP, nodeM);
        int max = maximum(nodeP, nodeM);


        if (max - min == 1) {
            wireHorizontal = new JLabel(wireHorizontalimage);
            wireHorizontal.setBounds(nodeCoordinates.get(min).x, nodeCoordinates.get(min).y - 41, 80, 80);

            circuit.add(wireHorizontal);
        } else if (max - min == 8) {
            wireVertical = new JLabel(wireVerticalimage);

            System.out.println("knekncencienvie" + max);
            wireVertical.setBounds(nodeCoordinates.get(max).x - 42, nodeCoordinates.get(max).y, 80, 80);

            circuit.add(wireVertical);
        } else if (max - min == 2 || max - min == 3 || max - min == 4) {
            int distance = max - min;
            int x = min;
            for (int i = 0; i < distance; i++) {
                wireDrawer(x, x + 1);
                x++;
            }
        }

    }

    public void nodeDrawer(int nodeP) {
        ImageIcon nodeimage = new ImageIcon("node.png");
        JLabel node = new JLabel(nodeimage);
        node.setBounds(nodeCoordinates.get(nodeP).x - 5, nodeCoordinates.get(nodeP).y - 4, 7, 7);
        circuit.add(node);
    }

    public int minimum(int x, int y) {
        if (x >= y)
            return y;
        else
            return x;

    }

    public int maximum(int x, int y) {
        if (x <= y)
            return y;
        else
            return x;

    }

    int currentNode = 10, up = 8, down = -8, right = 1, left = -1, lastNode = 2, remainder = 1;
    boolean flag = false, flag1 = false;
    int arrow = 1;  //1>>up  2>>right   3>>down   0>>left
    String type;
    ArrayList<Element> elements;
    ArrayList<Node> node;
    public void circuitDrawer() {


        node = initialTextProccesor.nodes;
        elements = initialTextProccesor.elements;



        circuit.revalidate();
        circuit.repaint();

        groundDrawer();

        if (node.size() == 1)
            oneNode();
        else if (node.size() == 2)
            twoNode();


        else if (node.size() == 3)
            threeNode();

        if (node.size() == 4) {


        }


    }


    public void oneNode() {


        for (int i = 0; i < elements.size(); i++) {
            type = elements.get(i).type;
            switch (type) {
                case "c":
                    capacitorDrawer(currentNode, currentNode + up);
                    break;

                case "r":
                    resistorDrawer(currentNode, currentNode + up);
                    break;

                case "l":
                    inductorDrawer(currentNode, currentNode + up);
                    break;

                case "vs":
                    voltageSourceDrawer(currentNode, currentNode + up);
                    break;
                case "cs":
                    currentSourceDrawer(currentNode, currentNode + up);
                    break;

            }

            wireDrawer(currentNode, currentNode + down);
            wireDrawer(currentNode + down, currentNode + down + right);
            wireDrawer(currentNode + up, currentNode + up * 2);
            wireDrawer(currentNode + up * 2, currentNode + up * 2 + right);
            currentNode += right;
        }
        wireDrawer(currentNode + up, currentNode + up * 2);
        wireDrawer(currentNode, currentNode + up);
        wireDrawer(currentNode, currentNode + down);


    }

    public void twoNode() {
        type = elements.get(0).type;

        for (int i = 0; i < elements.size(); i++) {
            System.out.println("hihihii" + "  " + i);
            type = elements.get(i).type;
            switch (type) {
                case "c":
                    capacitorDrawer(currentNode, currentNode + up);
                    break;

                case "r":
                    resistorDrawer(currentNode, currentNode + up);
                    break;

                case "l":
                    inductorDrawer(currentNode, currentNode + up);
                    break;

                case "vs":
                    voltageSourceDrawer(currentNode, currentNode + up);
                    break;
                case "cs":
                    currentSourceDrawer(currentNode, currentNode + up);
                    break;

            }
            wireDrawer(currentNode + up, currentNode + 2 * up);
            wireDrawer(currentNode, currentNode + down);


            if (i != elements.size() - 1) {
                wireDrawer(currentNode + 2 * up, currentNode + 2 * up + right);
                wireDrawer(currentNode + down, currentNode + down + right);
            }

            currentNode += right;


        }

    }

    public void threeNode() {

        System.out.println("jjj  "+elements.size());
        System.out.println("jjj  "+node.size());
        for (int i = 0; i < elements.size(); i++) {
            type = elements.get(i).type;
            if ((elements.get(i).node2.equals(node.get(1).name) && elements.get(i).node1.equals("0"))
                    || (elements.get(i).node1.equals(node.get(1).name) && elements.get(i).node2.equals("0"))) {
                System.out.println("didididdidididi 1111");
                wireDrawer(currentNode, currentNode + down);
                wireDrawer(currentNode + up, currentNode + 2 * up);


                if (flag) {
                    wireDrawer(currentNode + 2 * up, currentNode + 2 * up + left);
                    wireDrawer(currentNode + down, currentNode + down + left);
                }
                flag = true;
                switch (type) {
                    case "c":
                        capacitorDrawer(currentNode, currentNode + up);
                        break;

                    case "r":
                        resistorDrawer(currentNode, currentNode + up);
                        break;
                    case "l":
                        inductorDrawer(currentNode, currentNode + up);
                        break;
                    case "vs":
                        voltageSourceDrawer(currentNode, currentNode + up);
                        break;
                    case "cs":
                        currentSourceDrawer(currentNode, currentNode + up);
                        break;

                }
                currentNode += right;

            }
        }

        currentNode -= right;
        int x=0;
        for (int i = 0; i < elements.size(); i++) {
            type = elements.get(i).type;
            if (elements.get(i).node2.equals(node.get(2).name) && elements.get(i).node1.equals(node.get(1).name)
                    || elements.get(i).node1.equals(node.get(2).name) && elements.get(i).node2.equals(node.get(1).name)) {

                System.out.println("didididdidididi 2222");
                switch (type) {
                    case "c":
                        capacitorDrawer(currentNode + 2 * up, currentNode + 2 * up + right);
                        break;

                    case "r":
                        resistorDrawer(currentNode + 2 * up, currentNode + 2 * up + right);
                        break;

                    case "l":
                        inductorDrawer(currentNode + 2 * up, currentNode + 2 * up + right);
                        break;
                    case "vs":
                        voltageSourceDrawer(currentNode + 2 * up, currentNode + 2 * up + right);
                        break;
                    case "cs":
                        currentSourceDrawer(currentNode + 2 * up, currentNode + 2 * up + right);
                        break;

                }
                wireDrawer(currentNode + down, currentNode + down + right);
                currentNode += up;
                x++;
            }
        }
        currentNode-=x*up;
        for (int i = 0; i < elements.size(); i++) {
            type = elements.get(i).type;
            if ((elements.get(i).node2.equals(node.get(2).name) && elements.get(i).node1.equals("0"))
                    || (elements.get(i).node1.equals(node.get(2).name) && elements.get(i).node2.equals("0"))) {

                System.out.println("didididdidididi 3333");
                wireDrawer(currentNode, currentNode + down);
                wireDrawer(currentNode + up, currentNode + 2 * up);
                wireDrawer(currentNode + down, currentNode + down + left);

                if (flag1)
                    wireDrawer(currentNode + 2 * up, currentNode + 2 * up + left);

                flag1 = true;

                switch (type) {
                    case "c":
                        capacitorDrawer(currentNode, currentNode + up);
                        break;

                    case "r":
                        resistorDrawer(currentNode, currentNode + up);
                        break;
                    case "l":
                        inductorDrawer(currentNode, currentNode + up);
                        break;
                    case "vs":
                        voltageSourceDrawer(currentNode, currentNode + up);
                        break;
                    case "cs":
                        currentSourceDrawer(currentNode, currentNode + up);
                        break;

                }
                currentNode += right;
                System.out.println("didididdidididi 3333");
            }


        }

    }


}
