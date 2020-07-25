import javax.swing.*;
import java.awt.desktop.SystemEventListener;
import java.util.ArrayList;
import java.util.HashMap;

public class DrawKit {

    DrawKit() {
    }

    InitialTextProccesor initialTextProccesor;
    JPanel circuit;
    PixelCoordinate[] nodeCoordiantes = new PixelCoordinate[100];
    HashMap<Integer, PixelCoordinate> nodeCoordinates = new HashMap<Integer, PixelCoordinate>();
    HashMap<Integer, String> nodeCodes = new HashMap<Integer, String>();
    HashMap<Integer, Node> nodeIndex = new HashMap<Integer, Node>();


    public DrawKit(InitialTextProccesor initialTextProccesor, JPanel circuit) {
        this.initialTextProccesor = initialTextProccesor;
        this.circuit = circuit;
        for (int i = 1; i < 12; i++) {
            for (int j = 0; j < 9; j++) {
                nodeCoordiantes[i + j * 11] = new PixelCoordinate(82 * i - 30, 615 - j * 82);
                nodeCoordinates.put(i + j * 11, nodeCoordiantes[i + j * 11]);
            }
        }
    }

    public void circuitDrawer() {


        node = initialTextProccesor.nodes;
        elements = initialTextProccesor.elements;


        circuit.revalidate();
        circuit.repaint();


        if (node.size() == 1) {
            oneNode(node, 16);
            groundDrawer(16);
        } else if (node.size() == 2) {
            twoNode(node, 15);
            groundDrawer(15);
        } else if (node.size() == 3) {
            threeNode(node, 14);
            groundDrawer(14);
        }
        if (node.size() == 4) {

            fourNode(node, 2);
            groundDrawer(14);
        }


    }


    public void groundDrawer(int node) {
        ImageIcon gndImage = new ImageIcon("gnd.png");
        JLabel gnd = new JLabel(gndImage);
        nodeDrawer(node - 10);
        gnd.setBounds(nodeCoordinates.get(node).x + 53, nodeCoordinates.get(node).y + 83, 54, 34);
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
        } else if (nodeP - nodeM == 11 || nodeM - nodeP == 11) {
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
        } else if (nodeP - nodeM == 11) {
            diodeDown = new JLabel(diodeDownimage);
            diodeDown.setBounds(nodeCoordinates.get(max).x - 42, nodeCoordinates.get(max).y, 80, 80);

            circuit.add(diodeDown);
        } else if (nodeM - nodeP == 11) {
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
        } else if (nodeP - nodeM == 11) {
            voltageSourceDown = new JLabel(voltageSourceDownimage);
            voltageSourceDown.setBounds(nodeCoordinates.get(max).x - 42, nodeCoordinates.get(max).y, 80, 80);

            circuit.add(voltageSourceDown);
        } else if (nodeM - nodeP == 11) {
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

    public void voltageControlledVoltageSourceDrawer(int nodeP, int nodeM) {

        nodeDrawer(nodeP);
        nodeDrawer(nodeM);

        ImageIcon voltageControledVoltageSourceUpimage = new ImageIcon("voltageControledVoltageSourceUp.png");
        ImageIcon voltageControledVoltageSourceDownimage = new ImageIcon("voltageControledVoltageSourceDown.png");
        ImageIcon voltageControledVoltageSourceRightimage = new ImageIcon("voltageControledVoltageSourceRight.png");
        ImageIcon voltageControledVoltageSourceLeftimage = new ImageIcon("voltageControledVoltageSourceLeft.png");
        JLabel voltageControledVoltageSourceUp;
        JLabel voltageControledVoltageSourceDown;
        JLabel voltageControledVoltageSourceRight;
        JLabel voltageControledVoltageSourceLeft;
        int min = minimum(nodeP, nodeM);
        int max = maximum(nodeP, nodeM);
        if (nodeM - nodeP == 1) {
            voltageControledVoltageSourceRight = new JLabel(voltageControledVoltageSourceRightimage);
            voltageControledVoltageSourceRight.setBounds(nodeCoordinates.get(min).x, nodeCoordinates.get(min).y - 40, 80, 80);

            circuit.add(voltageControledVoltageSourceRight);
        } else if (nodeP - nodeM == 11) {
            voltageControledVoltageSourceDown = new JLabel(voltageControledVoltageSourceDownimage);
            voltageControledVoltageSourceDown.setBounds(nodeCoordinates.get(max).x - 42, nodeCoordinates.get(max).y, 80, 80);

            circuit.add(voltageControledVoltageSourceDown);
        } else if (nodeM - nodeP == 11) {
            voltageControledVoltageSourceUp = new JLabel(voltageControledVoltageSourceUpimage);
            voltageControledVoltageSourceUp.setBounds(nodeCoordinates.get(max).x - 42, nodeCoordinates.get(max).y, 80, 80);

            circuit.add(voltageControledVoltageSourceUp);
        }
        if (nodeP - nodeM == 1) {
            voltageControledVoltageSourceLeft = new JLabel(voltageControledVoltageSourceLeftimage);
            voltageControledVoltageSourceLeft.setBounds(nodeCoordinates.get(min).x, nodeCoordinates.get(min).y - 40, 80, 80);

            circuit.add(voltageControledVoltageSourceLeft);
        }

    }

    public void currentControlledVoltageSourceDrawer(int nodeP, int nodeM) {
        nodeDrawer(nodeP);
        nodeDrawer(nodeM);

        ImageIcon currentControledVoltageSourceUpimage = new ImageIcon("currentControledVoltageSourceUp.png");
        ImageIcon currentControledVoltageSourceDownimage = new ImageIcon("currentControledVoltageSourceDown.png");
        ImageIcon currentControledVoltageSourceRightimage = new ImageIcon("currentControledVoltageSourceRight.png");
        ImageIcon currentControledVoltageSourceLeftimage = new ImageIcon("currentControledVoltageSourceLeft.png");
        JLabel currentControledVoltageSourceUp;
        JLabel currentControledVoltageSourceDown;
        JLabel currentControledVoltageSourceRight;
        JLabel currentControledVoltageSourceLeft;
        int min = minimum(nodeP, nodeM);
        int max = maximum(nodeP, nodeM);
        if (nodeM - nodeP == 1) {
            currentControledVoltageSourceRight = new JLabel(currentControledVoltageSourceRightimage);
            currentControledVoltageSourceRight.setBounds(nodeCoordinates.get(min).x, nodeCoordinates.get(min).y - 40, 80, 80);

            circuit.add(currentControledVoltageSourceRight);
        } else if (nodeP - nodeM == 11) {
            currentControledVoltageSourceDown = new JLabel(currentControledVoltageSourceDownimage);
            currentControledVoltageSourceDown.setBounds(nodeCoordinates.get(max).x - 42, nodeCoordinates.get(max).y, 80, 80);

            circuit.add(currentControledVoltageSourceDown);
        } else if (nodeM - nodeP == 11) {
            currentControledVoltageSourceUp = new JLabel(currentControledVoltageSourceUpimage);
            currentControledVoltageSourceUp.setBounds(nodeCoordinates.get(max).x - 42, nodeCoordinates.get(max).y, 80, 80);

            circuit.add(currentControledVoltageSourceUp);
        }
        if (nodeP - nodeM == 1) {
            currentControledVoltageSourceLeft = new JLabel(currentControledVoltageSourceLeftimage);
            currentControledVoltageSourceLeft.setBounds(nodeCoordinates.get(min).x, nodeCoordinates.get(min).y - 40, 80, 80);

            circuit.add(currentControledVoltageSourceLeft);
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
        } else if (nodeP - nodeM == 11) {
            currentSourceDown = new JLabel(currentSourceDownimage);
            currentSourceDown.setBounds(nodeCoordinates.get(max).x - 42, nodeCoordinates.get(max).y, 80, 80);

            circuit.add(currentSourceDown);
        } else if (nodeM - nodeP == 11) {
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


    public void voltageControlledCurrentSourceDrawer(int nodeP, int nodeM) {
        nodeDrawer(nodeP);
        nodeDrawer(nodeM);

        ImageIcon voltageControlledCurrentSourceUpimage = new ImageIcon("voltageControledCurrentSourceUp.png");
        ImageIcon voltageControlledCurrentSourceDownimage = new ImageIcon("voltageControledCurrentSourceDown.png");
        ImageIcon voltageControlledCurrentSourceRightimage = new ImageIcon("voltageControledCurrentSourceRight.png");
        ImageIcon voltageControlledCurrentSourceLeftimage = new ImageIcon("voltageControledCurrentSourceLeft.png");
        JLabel voltageControlledCurrentSourceUp;
        JLabel voltageControlledCurrentSourceDown;
        JLabel voltageControlledCurrentSourceRight;
        JLabel voltageControlledCurrentSourceLeft;
        int min = minimum(nodeP, nodeM);
        int max = maximum(nodeP, nodeM);

        if (nodeM - nodeP == 1) {
            voltageControlledCurrentSourceRight = new JLabel(voltageControlledCurrentSourceRightimage);
            voltageControlledCurrentSourceRight.setBounds(nodeCoordinates.get(min).x, nodeCoordinates.get(min).y - 40, 80, 80);

            circuit.add(voltageControlledCurrentSourceRight);
        } else if (nodeP - nodeM == 11) {
            voltageControlledCurrentSourceDown = new JLabel(voltageControlledCurrentSourceDownimage);
            voltageControlledCurrentSourceDown.setBounds(nodeCoordinates.get(max).x - 42, nodeCoordinates.get(max).y, 80, 80);

            circuit.add(voltageControlledCurrentSourceDown);
        } else if (nodeM - nodeP == 11) {
            voltageControlledCurrentSourceUp = new JLabel(voltageControlledCurrentSourceUpimage);
            voltageControlledCurrentSourceUp.setBounds(nodeCoordinates.get(max).x - 42, nodeCoordinates.get(max).y, 80, 80);

            circuit.add(voltageControlledCurrentSourceUp);
        }
        if (nodeP - nodeM == 1) {
            voltageControlledCurrentSourceLeft = new JLabel(voltageControlledCurrentSourceLeftimage);
            voltageControlledCurrentSourceLeft.setBounds(nodeCoordinates.get(min).x, nodeCoordinates.get(min).y - 40, 80, 80);

            circuit.add(voltageControlledCurrentSourceLeft);
        }

    }


    public void currentControlledCurrentSourceDrawer(int nodeP, int nodeM) {
        nodeDrawer(nodeP);
        nodeDrawer(nodeM);

        ImageIcon currentControlledCurrentSourceUpimage = new ImageIcon("currentControledCurrentSourceUp.png");
        ImageIcon currentControlledCurrentSourceDownimage = new ImageIcon("currrentControledCurrentSourceDown.png");
        ImageIcon currentControlledCurrentSourceRightimage = new ImageIcon("currentControledCurrentSourceRight.png");
        ImageIcon currentControlledCurrentSourceLeftimage = new ImageIcon("currentControledCurrentSourceLeft.png");
        JLabel currentControlledCurrentSourceUp;
        JLabel currentControlledCurrentSourceDown;
        JLabel currentControlledCurrentSourceRight;
        JLabel currentControlledCurrentSourceLeft;
        int min = minimum(nodeP, nodeM);
        int max = maximum(nodeP, nodeM);

        if (nodeM - nodeP == 1) {
            currentControlledCurrentSourceRight = new JLabel(currentControlledCurrentSourceRightimage);
            currentControlledCurrentSourceRight.setBounds(nodeCoordinates.get(min).x, nodeCoordinates.get(min).y - 40, 80, 80);

            circuit.add(currentControlledCurrentSourceRight);
        } else if (nodeP - nodeM == 11) {
            currentControlledCurrentSourceDown = new JLabel(currentControlledCurrentSourceDownimage);
            currentControlledCurrentSourceDown.setBounds(nodeCoordinates.get(max).x - 42, nodeCoordinates.get(max).y, 80, 80);

            circuit.add(currentControlledCurrentSourceDown);
        } else if (nodeM - nodeP == 11) {
            currentControlledCurrentSourceUp = new JLabel(currentControlledCurrentSourceUpimage);
            currentControlledCurrentSourceUp.setBounds(nodeCoordinates.get(max).x - 42, nodeCoordinates.get(max).y, 80, 80);

            circuit.add(currentControlledCurrentSourceUp);
        }
        if (nodeP - nodeM == 1) {
            currentControlledCurrentSourceLeft = new JLabel(currentControlledCurrentSourceLeftimage);
            currentControlledCurrentSourceLeft.setBounds(nodeCoordinates.get(min).x, nodeCoordinates.get(min).y - 40, 80, 80);

            circuit.add(currentControlledCurrentSourceLeft);
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
        } else if (nodeP - nodeM == 11 || nodeM - nodeP == 11) {
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
        } else if (nodeP - nodeM == 11 || nodeM - nodeP == 11) {
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
        } else if (max - min == 11) {
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

    int up = 11, down = -11, right = 1, left = -1, lastNode = 2, remainder = 1, arrow = 1;
    String type;
    ArrayList<Element> elements;
    ArrayList<Node> node;
    boolean flag1 = false;

    public void oneNode(ArrayList<Node> node1, int currentNode) {

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
                    if (elements.get(i).node1.equals("0"))
                        voltageSourceDrawer(currentNode, currentNode + up);
                    else
                        voltageSourceDrawer(currentNode + up, currentNode);
                    break;
                case "cs":
                    if (elements.get(i).node1.equals("0"))
                        currentSourceDrawer(currentNode, currentNode + up);
                    else
                        currentSourceDrawer(currentNode + up, currentNode);
                    break;
                case "d":
                    if (elements.get(i).node1.equals("0"))
                        diodeDrawer(currentNode, currentNode + up);
                    else
                        diodeDrawer(currentNode + up, currentNode);
                    break;
                case "vcv":
                    if (elements.get(i).node1.equals("0"))
                        voltageControlledVoltageSourceDrawer(currentNode, currentNode + up);
                    else
                        voltageControlledVoltageSourceDrawer(currentNode + up, currentNode);
                    break;
                case "ccv":
                    if (elements.get(i).node1.equals("0"))
                        currentControlledVoltageSourceDrawer(currentNode, currentNode + up);
                    else
                        currentControlledVoltageSourceDrawer(currentNode + up, currentNode);
                    break;
                case "vcc":
                    if (elements.get(i).node1.equals("0"))
                        voltageControlledCurrentSourceDrawer(currentNode, currentNode + up);
                    else
                        voltageControlledCurrentSourceDrawer(currentNode + up, currentNode);
                    break;
                case "ccc":
                    if (elements.get(i).node1.equals("0"))
                        currentControlledCurrentSourceDrawer(currentNode, currentNode + up);
                    else
                        currentControlledCurrentSourceDrawer(currentNode + up, currentNode);
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

    public void twoNode(ArrayList<Node> node2, int currentNode) {
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
                    if (elements.get(i).node1.equals("0"))
                        voltageSourceDrawer(currentNode + up, currentNode);
                    else
                        voltageSourceDrawer(currentNode, currentNode + up);
                    break;
                case "cs":
                    if (elements.get(i).node1.equals("0"))
                        currentSourceDrawer(currentNode + up, currentNode);
                    else
                        currentSourceDrawer(currentNode, currentNode + up);
                    break;
                case "d":
                    if (elements.get(i).node1.equals("0"))
                        diodeDrawer(currentNode + up, currentNode);
                    else
                        diodeDrawer(currentNode, currentNode + up);
                    break;
                case "vcv":
                    if (elements.get(i).node1.equals("0"))
                        voltageControlledVoltageSourceDrawer(currentNode, currentNode + up);
                    else
                        voltageControlledVoltageSourceDrawer(currentNode + up, currentNode);
                    break;
                case "ccv":
                    if (elements.get(i).node1.equals("0"))
                        currentControlledVoltageSourceDrawer(currentNode, currentNode + up);
                    else
                        currentControlledVoltageSourceDrawer(currentNode + up, currentNode);
                    break;
                case "vcc":
                    if (elements.get(i).node1.equals("0"))
                        voltageControlledCurrentSourceDrawer(currentNode, currentNode + up);
                    else
                        voltageControlledCurrentSourceDrawer(currentNode + up, currentNode);
                    break;
                case "ccc":
                    if (elements.get(i).node1.equals("0"))
                        currentControlledCurrentSourceDrawer(currentNode, currentNode + up);
                    else
                        currentControlledCurrentSourceDrawer(currentNode + up, currentNode);
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


    public int threeNode(ArrayList<Node> node3, int currentNode) {
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
                        if (elements.get(i).node1.equals("0"))
                            voltageSourceDrawer(currentNode + up, currentNode);
                        else
                            voltageSourceDrawer(currentNode, currentNode + up);
                        break;
                    case "cs":
                        if (elements.get(i).node1.equals("0"))
                            currentSourceDrawer(currentNode + up, currentNode);
                        else
                            currentSourceDrawer(currentNode, currentNode + up);
                        break;
                    case "d":
                        if (elements.get(i).node1.equals("0"))
                            diodeDrawer(currentNode + up, currentNode);
                        else
                            diodeDrawer(currentNode, currentNode + up);
                        break;
                    case "vcv":
                        if (elements.get(i).node1.equals("0"))
                            voltageControlledVoltageSourceDrawer(currentNode, currentNode + up);

                        else
                            voltageControlledVoltageSourceDrawer(currentNode + up, currentNode);

                        break;
                    case "ccv":
                        if (elements.get(i).node1.equals("0"))
                            currentControlledVoltageSourceDrawer(currentNode, currentNode + up);
                        else
                            currentControlledVoltageSourceDrawer(currentNode + up, currentNode);
                        break;
                    case "vcc":
                        if (elements.get(i).node1.equals("0"))
                            voltageControlledCurrentSourceDrawer(currentNode, currentNode + up);
                        else
                            voltageControlledCurrentSourceDrawer(currentNode + up, currentNode);
                        break;
                    case "ccc":
                        if (elements.get(i).node1.equals("0"))
                            currentControlledCurrentSourceDrawer(currentNode, currentNode + up);
                        else
                            currentControlledCurrentSourceDrawer(currentNode + up, currentNode);
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
        if (flag)
            currentNode -= right;
        int x = 0;
        boolean flag3 = false;
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
                        if (elements.get(i).node1.equals(node3.get(1).name))
                            voltageSourceDrawer(currentNode + 2 * up + right, currentNode + 2 * up);
                        else
                            voltageSourceDrawer(currentNode + 2 * up, currentNode + 2 * up + right);
                        break;
                    case "cs":
                        if (elements.get(i).node1.equals(node3.get(1).name))
                            currentSourceDrawer(currentNode + 2 * up + right, currentNode + 2 * up);
                        else
                            currentSourceDrawer(currentNode + 2 * up, currentNode + 2 * up + right);
                        break;
                    case "d":
                        if (elements.get(i).node1.equals(node3.get(1).name))
                            diodeDrawer(currentNode + 2 * up + right, currentNode + 2 * up);
                        else
                            diodeDrawer(currentNode + 2 * up, currentNode + 2 * up + right);
                        break;
                    case "vcv":
                        if (elements.get(i).node1.equals(node3.get(1).name))
                            voltageControlledVoltageSourceDrawer(currentNode + 2 * up + right, currentNode + 2 * up);
                        else
                            voltageControlledVoltageSourceDrawer(currentNode + 2 * up, currentNode + 2 * up + right);
                        break;
                    case "ccv":
                        if (elements.get(i).node1.equals(node3.get(1).name))
                            currentControlledVoltageSourceDrawer(currentNode + 2 * up + right, currentNode + 2 * up);
                        else
                            currentControlledVoltageSourceDrawer(currentNode + 2 * up, currentNode + 2 * up + right);
                        break;
                    case "vcc":
                        if (elements.get(i).node1.equals(node3.get(1).name))
                            voltageControlledCurrentSourceDrawer(currentNode + 2 * up + right, currentNode + 2 * up);
                        else
                            voltageControlledCurrentSourceDrawer(currentNode + 2 * up, currentNode + 2 * up + right);
                        break;
                    case "ccc":
                        if (elements.get(i).node1.equals(node3.get(1).name))
                            currentControlledCurrentSourceDrawer(currentNode + 2 * up + right, currentNode + 2 * up);
                        else
                            currentControlledCurrentSourceDrawer(currentNode + 2 * up, currentNode + 2 * up + right);
                        break;

                }
                if (!flag2)
                    wireDrawer(currentNode + down, currentNode + down + right);
                if (flag2) {
                    wireDrawer(currentNode + up, currentNode + 2 * up);
                    wireDrawer(currentNode + up + right, currentNode + 2 * up + right);
                }
                flag2 = true;
                currentNode += up;
                x++;
                flag3 = true;
            }
        }
        currentNode += x * down;
        if (flag3)
            currentNode += right;
        boolean flag6 = false;
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
                        if (elements.get(i).node1.equals("0"))
                            voltageSourceDrawer(currentNode + up, currentNode);
                        else
                            voltageSourceDrawer(currentNode, currentNode + up);
                        break;
                    case "cs":
                        if (elements.get(i).node1.equals("0"))
                            currentSourceDrawer(currentNode + up, currentNode);
                        else
                            currentSourceDrawer(currentNode, currentNode + up);
                        break;
                    case "d":
                        if (elements.get(i).node1.equals("0"))
                            diodeDrawer(currentNode + up, currentNode);
                        else
                            diodeDrawer(currentNode, currentNode + up);
                        break;
                    case "vcv":
                        if (elements.get(i).node1.equals("0"))
                            voltageControlledVoltageSourceDrawer(currentNode, currentNode + up);
                        else
                            voltageControlledVoltageSourceDrawer(currentNode + up, currentNode);
                        break;
                    case "ccv":
                        if (elements.get(i).node1.equals("0"))
                            currentControlledVoltageSourceDrawer(currentNode, currentNode + up);
                        else
                            currentControlledVoltageSourceDrawer(currentNode + up, currentNode);
                        break;
                    case "vcc":
                        if (elements.get(i).node1.equals("0"))
                            voltageControlledCurrentSourceDrawer(currentNode, currentNode + up);
                        else
                            voltageControlledCurrentSourceDrawer(currentNode + up, currentNode);
                        break;
                    case "ccc":
                        if (elements.get(i).node1.equals("0"))
                            currentControlledCurrentSourceDrawer(currentNode, currentNode + up);
                        else
                            currentControlledCurrentSourceDrawer(currentNode + up, currentNode);
                        break;
                }
                currentNode += right;
                flag6 = true;
            }


        }
        if (flag6)
            currentNode += left;
        return currentNode;
    }


    public void fourNode(ArrayList<Node> node4, int currentNode) {
        ArrayList<Node> tempNodes = new ArrayList();
        for (int i = 0; i < 3; i++) {
            tempNodes.add(node4.get(i));
        }
        currentNode = threeNode(tempNodes, 13);

        int savedNode = currentNode;
        currentNode += right;////////////////////////////////////////////////////////////////pp
        boolean flag3 = false;
        boolean flag2 = false;
        Node lastNode = node4.get(node4.size() - 1);

        for (int i = 0; i < elements.size(); i++) {
            type = elements.get(i).type;
            if (elements.get(i).node1.equals(lastNode.name) || elements.get(i).node2.equals(lastNode.name)) {
                int temp = Integer.parseInt(elements.get(i).node1) + Integer.parseInt(elements.get(i).node2) - Integer.parseInt(lastNode.name);
                String elseNode = Integer.toString(temp);
                if (elseNode.equals("0")) {

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
                            if (elements.get(i).node1.equals("0"))
                                voltageSourceDrawer(currentNode + up, currentNode);
                            else
                                voltageSourceDrawer(currentNode, currentNode + up);
                            break;
                        case "cs":
                            if (elements.get(i).node1.equals("0"))
                                currentSourceDrawer(currentNode + up, currentNode);
                            else
                                currentSourceDrawer(currentNode, currentNode + up);
                            break;
                        case "d":
                            if (elements.get(i).node1.equals("0"))
                                diodeDrawer(currentNode + up, currentNode);
                            else
                                diodeDrawer(currentNode, currentNode + up);
                            break;
                        case "vcv":
                            if (elements.get(i).node1.equals("0"))
                                voltageControlledVoltageSourceDrawer(currentNode, currentNode + up);
                            else
                                voltageControlledVoltageSourceDrawer(currentNode + up, currentNode);
                            break;
                        case "ccv":
                            if (elements.get(i).node1.equals("0"))
                                currentControlledVoltageSourceDrawer(currentNode, currentNode + up);
                            else
                                currentControlledVoltageSourceDrawer(currentNode + up, currentNode);
                            break;
                        case "vcc":
                            if (elements.get(i).node1.equals("0"))
                                voltageControlledCurrentSourceDrawer(currentNode, currentNode + up);
                            else
                                voltageControlledCurrentSourceDrawer(currentNode + up, currentNode);
                            break;
                        case "ccc":
                            if (elements.get(i).node1.equals("0"))
                                currentControlledCurrentSourceDrawer(currentNode, currentNode + up);
                            else
                                currentControlledCurrentSourceDrawer(currentNode + up, currentNode);
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
        if (flag2)
            currentNode += left;

        for (int i = 0; i < elements.size(); i++) {
            type = elements.get(i).type;
            if (elements.get(i).node1.equals(lastNode.name) || elements.get(i).node2.equals(lastNode.name)) {
                int temp = Integer.parseInt(elements.get(i).node1) + Integer.parseInt(elements.get(i).node2) - Integer.parseInt(lastNode.name);
                String elseNode = Integer.toString(temp);
                if (elseNode.equals(node4.get(2).name)) {
                    switch (type) {
                        case "c":
                            capacitorDrawer(savedNode + 2 * up, savedNode + 2 * up + right);
                            break;

                        case "r":
                            resistorDrawer(savedNode + 2 * up, savedNode + 2 * up + right);
                            break;
                        case "l":
                            inductorDrawer(savedNode + 2 * up, savedNode + 2 * up + right);
                            break;
                        case "vs":
                            if (elements.get(i).node1.equals(node4.get(2).name))
                                voltageSourceDrawer(savedNode + 2 * up + right, savedNode + 2 * up);
                            else
                                voltageSourceDrawer(savedNode + 2 * up, savedNode + 2 * up + right);
                            break;
                        case "cs":
                            if (elements.get(i).node1.equals(node4.get(2).name))
                                currentSourceDrawer(savedNode + 2 * up + right, savedNode + 2 * up);
                            else
                                currentSourceDrawer(savedNode + 2 * up, savedNode + 2 * up + right);
                            break;
                        case "d":
                            if (elements.get(i).node1.equals(node4.get(2).name))
                                diodeDrawer(savedNode + 2 * up + right, savedNode + 2 * up);
                            else
                                diodeDrawer(savedNode + 2 * up, savedNode + 2 * up + right);
                            break;
                        case "vcv":
                            if (elements.get(i).node1.equals(node4.get(2).name))
                                voltageControlledVoltageSourceDrawer(savedNode + 2 * up, savedNode + 2 * up + right);
                            else
                                voltageControlledVoltageSourceDrawer(savedNode + 2 * up + right, savedNode + 2 * up);
                            break;
                        case "ccv":
                            if (elements.get(i).node1.equals(node4.get(2).name))
                                currentControlledVoltageSourceDrawer(savedNode + 2 * up, savedNode + 2 * up + right);
                            else
                                currentControlledVoltageSourceDrawer(savedNode + 2 * up + right, savedNode + 2 * up);
                            break;
                        case "vcc":
                            if (elements.get(i).node1.equals(node4.get(2).name))
                                voltageControlledCurrentSourceDrawer(savedNode + 2 * up, savedNode + 2 * up + right);
                            else
                                voltageControlledCurrentSourceDrawer(savedNode + 2 * up + right, savedNode + 2 * up);
                            break;
                        case "ccc":
                            if (elements.get(i).node1.equals(node4.get(2).name))
                                currentControlledCurrentSourceDrawer(savedNode + 2 * up, savedNode + 2 * up + right);
                            else
                                currentControlledCurrentSourceDrawer(savedNode + 2 * up + right, savedNode + 2 * up);
                            break;
                    }
                    if (flag3) {
                        wireDrawer(savedNode + up, savedNode + 2 * up);
                        wireDrawer(savedNode + up + right, savedNode + 2 * up + right);
                    }
                    flag3 = true;
                    savedNode += up;
                }
            }
        }

        currentNode = currentNode + right;

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
                            if (elements.get(i).node1.equals(node4.get(1).name))
                                voltageSourceDrawer(currentNode, currentNode + left);
                            else
                                voltageSourceDrawer(currentNode + left, currentNode);
                            break;
                        case "cs":
                            if (elements.get(i).node1.equals(node4.get(1).name))
                                currentSourceDrawer(currentNode, currentNode + left);
                            else
                                currentSourceDrawer(currentNode + left, currentNode);
                            break;
                        case "d":
                            if (elements.get(i).node1.equals(node4.get(1).name))
                                diodeDrawer(currentNode, currentNode + left);
                            else
                                diodeDrawer(currentNode + left, currentNode);
                            break;
                        case "vcv":
                            if (elements.get(i).node1.equals(node4.get(1).name))
                                voltageControlledVoltageSourceDrawer(currentNode, currentNode + left);
                            else
                                voltageControlledVoltageSourceDrawer(currentNode + left, currentNode);
                            break;
                        case "ccv":
                            if (elements.get(i).node1.equals(node4.get(1).name))
                                currentControlledVoltageSourceDrawer(currentNode, currentNode + left);
                            else
                                currentControlledVoltageSourceDrawer(currentNode + left, currentNode);
                            break;
                        case "vcc":
                            if (elements.get(i).node1.equals(node4.get(1).name))
                                voltageControlledCurrentSourceDrawer(currentNode, currentNode + left);
                            else
                                voltageControlledCurrentSourceDrawer(currentNode + left, currentNode);
                            break;
                        case "ccc":
                            if (elements.get(i).node1.equals(node4.get(1).name))
                                currentControlledCurrentSourceDrawer(currentNode, currentNode + left);
                            else
                                currentControlledCurrentSourceDrawer(currentNode + left, currentNode);
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


            }
        }

        if (threeTOone) {
            currentNode += down;
            wireDrawer(35, 46);
            wireDrawer(46, 57);
            wireDrawer(57, 68);
            wireDrawer(68, 79);
            int tempp = currentNode, yy = 0;
            while (tempp < 78) {
                tempp += 11;
                yy++;
            }
            for (int ii = 79; ii < tempp; ii++)
                wireDrawer(ii, ii + 1);
            for (int ii = 0; ii < yy; ii++)
                wireDrawer(tempp - ii * 11, tempp - ii * 11 - 11);

        }
    }
}
