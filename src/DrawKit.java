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
        nodeDrawer(3);
        gnd.setBounds(nodeCoordinates.get(10).x + 53, nodeCoordinates.get(10).y + 83, 54, 34);
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
            oneNode(node);
        else if (node.size() == 2)
            twoNode(node);


        else if (node.size() == 3)
            threeNode(node);

        if (node.size() == 4) {

            fourNode(node);

        }


    }

    boolean flag1 = false;

    public void oneNode(ArrayList<Node> node1) {


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

    public void twoNode(ArrayList<Node> node2) {
        type = elements.get(0).type;

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
            wireDrawer(currentNode + up, currentNode + 2 * up);
            wireDrawer(currentNode, currentNode + down);


            if (i != elements.size() - 1) {
                wireDrawer(currentNode + 2 * up, currentNode + 2 * up + right);
                wireDrawer(currentNode + down, currentNode + down + right);
            }

            currentNode += right;


        }

    }





    public void threeNode(ArrayList<Node> node3) {
        boolean flag = false;
        for (int i = 0; i < elements.size(); i++) {
            type = elements.get(i).type;
            if ((elements.get(i).node2.equals(node3.get(1).name) && elements.get(i).node1.equals("0"))
                    || (elements.get(i).node1.equals(node3.get(1).name) && elements.get(i).node2.equals("0"))) {
                wireDrawer(currentNode, currentNode + down);
                wireDrawer(currentNode + up, currentNode + 2 * up);


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

                if (flag) {
                    wireDrawer(currentNode + 2 * up, currentNode + 2 * up + left);
                    wireDrawer(currentNode + down, currentNode + down + left);
                }
                flag = true;
                currentNode += right;

            }
        }

        currentNode -= right;
        int x = 0;
        boolean flag2 = false;
        for (int i = 0; i < elements.size(); i++) {
            type = elements.get(i).type;
            if (elements.get(i).node2.equals(node3.get(2).name) && elements.get(i).node1.equals(node3.get(1).name)
                    || elements.get(i).node1.equals(node3.get(2).name) && elements.get(i).node2.equals(node3.get(1).name)) {
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
                if(!flag2)
                    wireDrawer(currentNode+down,currentNode+down+right);
                if (flag2) {
                    wireDrawer(currentNode + up, currentNode + 2 * up);
                    wireDrawer(currentNode + up+right , currentNode + 2*up + right);
                }
                /////////////////////////////////////////////////////pp
                flag2 = true;
                currentNode += up;
                x++;
            }
        }
        currentNode -= x * up;
        currentNode += right;
        for (int i = 0; i < elements.size(); i++) {
            type = elements.get(i).type;
            if ((elements.get(i).node2.equals(node3.get(2).name) && elements.get(i).node1.equals("0"))
                    || (elements.get(i).node1.equals(node3.get(2).name) && elements.get(i).node2.equals("0"))) {
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
            }


        }

    }




    public void fourNode(ArrayList<Node> node4) {
        ArrayList<Node> tempNodes = new ArrayList();
        for (int i = 0; i < 3; i++) {
            tempNodes.add(node4.get(i));
        }
        threeNode(tempNodes);

        int savedNode = currentNode;
        currentNode++;////////////////////////////////////////////////////////////////pp
        boolean flag3 = false;
        boolean flag2 = false;
        Node lastNode = node4.get(node4.size() - 1);

        for (int i = 0; i < elements.size(); i++) {
            type = elements.get(i).type;
            if (elements.get(i).node1.equals(lastNode.name) || elements.get(i).node2.equals(lastNode.name)) {
                int temp = Integer.parseInt(elements.get(i).node1) + Integer.parseInt(elements.get(i).node2) - Integer.parseInt(lastNode.name);
                String elseNode = Integer.toString(temp);
                if (elseNode.equals("0")) {

                    System.out.println("done with 000");

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
                    wireDrawer(currentNode + down, currentNode + down + left);
                    if (flag2)
                        wireDrawer(currentNode + 2 * up, currentNode + 2 * up + left);
                    flag2 = true;

                    currentNode += right;

                }
            }
        }
            currentNode+=left;
        savedNode=currentNode;
        for (int i = 0; i < elements.size(); i++) {
            type = elements.get(i).type;
            if (elements.get(i).node1.equals(lastNode.name) || elements.get(i).node2.equals(lastNode.name)) {
                int temp = Integer.parseInt(elements.get(i).node1) + Integer.parseInt(elements.get(i).node2) - Integer.parseInt(lastNode.name);
                String elseNode = Integer.toString(temp);
                if (elseNode.equals(node4.get(2).name)) {
                    //currentNode+=left;
                    System.out.println("done with 222  "+node4.get(2).name+"   "+type);
                    switch (type) {
                        case "c":
                            capacitorDrawer(currentNode + 2 * up, currentNode + 2 * up + right);//////////////////////////////////////////////////////////pp
                            break;

                        case "r":
                            resistorDrawer(currentNode + 2 * up, currentNode + 2 * up + right);
                            break;
                        case "l":
                            inductorDrawer(currentNode + 2 * up, currentNode + 2 * up + left);
                            break;
                        case "vs":
                            voltageSourceDrawer(currentNode + 2 * up, currentNode + 2 * up + right);
                            break;
                        case "cs":
                            currentSourceDrawer(currentNode + 2 * up, currentNode + 2 * up + right);
                            break;
                    }
                    if (flag3) {
                        wireDrawer(currentNode + up, currentNode + 2 * up);
                        wireDrawer(currentNode + up + right, currentNode + 2 * up + right);
                    }
                    flag3 = true;
                    currentNode += up;
                }
            }
        }
        currentNode=savedNode+right;

        boolean threeTOone = false;
        currentNode += left;
        boolean flag4 = false;
        boolean flag5 = true;
        for (int i = 0; i < elements.size(); i++) {
            type = elements.get(i).type;
            if (elements.get(i).node1.equals(lastNode.name) || elements.get(i).node2.equals(lastNode.name)) {
                int temp = Integer.parseInt(elements.get(i).node1) + Integer.parseInt(elements.get(i).node2) - Integer.parseInt(lastNode.name);
                String elseNode = Integer.toString(temp);
                if (elseNode.equals(node4.get(1).name)) {
                    System.out.println("done with 111  "+node4.get(1).name);
                    if (flag5)
                        currentNode = currentNode + 2 * up + right;
                    flag5 = false;
                    switch (type) {
                        case "c":
                            capacitorDrawer(currentNode, currentNode + left);
                            break;
                        case "r":
                            resistorDrawer(currentNode, currentNode + left);
                            break;
                        case "l":
                            inductorDrawer(currentNode, currentNode + left);
                            break;
                        case "vs":
                            voltageSourceDrawer(currentNode, currentNode + left);
                            break;
                        case "cs":
                            currentSourceDrawer(currentNode, currentNode + left);
                            break;
                    }
                    if (flag4) {
                        wireDrawer(currentNode, currentNode + down);
                        wireDrawer(currentNode + left, currentNode + down + left);
                    }
                    currentNode += up;
                    flag4 = true;
                    threeTOone = true;
                }
                if (threeTOone) {
                    currentNode += down;
                    wireDrawer(26, 34);
                    wireDrawer(34, 42);
                    wireDrawer(42, 43);
                    int tempp=currentNode,yy=0;
                    while(tempp<44) {
                        tempp += 8;
                        yy++;
                    }
                    for(int ii=43;ii<tempp;ii++)
                        wireDrawer(ii,ii+1);
                    for(int ii=0;ii<yy;ii++)
                        wireDrawer(tempp-ii*8,tempp-ii*8-8);

                }


            }
        }
    }
}
