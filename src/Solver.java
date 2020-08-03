import javax.print.attribute.standard.Finishings;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;


public class Solver {
    int[][] sgraph;
    double di, dv, dt, time = 0, endtime;
    ArrayList<Union> sunions = new ArrayList();
    ArrayList<Element> selements = new ArrayList<Element>();
    ArrayList<Node> snodes = new ArrayList();
    ArrayList<Node> nodesInOrder = new ArrayList();
    ErrorFinder errorFinder;
    JFrame circuit;
    InitialTextProccesor initialTextProccesor;

    Solver(InitialTextProccesor initialTextProccesor, JFrame circuit) {
        int i, j;
        this.circuit = circuit;

        endtime = initialTextProccesor.time;
        di = initialTextProccesor.deltai;
        dv = initialTextProccesor.deltav;
        dt = initialTextProccesor.deltat;

        this.sunions = initialTextProccesor.unions;
        this.selements = initialTextProccesor.elements;

        this.initialTextProccesor = initialTextProccesor;

        this.snodes = initialTextProccesor.nodes;
        this.sgraph = initialTextProccesor.graph;
        errorFinder = new ErrorFinder(circuit, initialTextProccesor);
    }


    //errorFinder.errorTwo(int(time/dt))


    void resetVoltage(int j) {
        int i, flag, k, m, w = 0;
        double volt = 0, control = 0;
        sunions.get(j).nod.get(0).voltageDef = true;
        for (i = 1; i < sunions.get(j).nod.size(); i++) sunions.get(j).nod.get(i).voltageDef = false;
        while (true) {
            flag = 0;
            for (i = 0; i < sunions.get(j).nod.size(); i++)
                if (sunions.get(j).nod.get(i).voltageDef == false)
                    flag = 1;
            if (flag == 0) break;
            for (i = 0; i < sunions.get(j).nod.size(); i++) {
                if (sunions.get(j).nod.get(i).voltageDef == true) {
                    for (k = 0; k < sunions.get(j).nod.size(); k++) {
                        if (sunions.get(j).nod.get(k).voltageDef == false) {
                            for (m = 0; m < selements.size(); m++) {
                                if (selements.get(m).type.equals("vs") || selements.get(m).type.equals("vcv") || selements.get(m).type.equals("ccv")) {
                                    if (selements.get(m).type.equals("vs"))
                                        volt = selements.get(m).dc + selements.get(m).ac * Math.sin(2 * Math.PI * selements.get(m).frequncey * time + selements.get(m).phase);
                                    if (selements.get(m).type.equals("vcv"))
                                        volt = selements.get(m).gain * (findNode(selements.get(m).node3) - findNode(selements.get(m).node4));
                                    if (selements.get(m).type.equals("ccv")) {
                                        for (w = 0; w < selements.size(); w++)
                                            if (selements.get(w).name.equals(selements.get(m).controlelement))
                                                control = selements.get(w).currentValues[(int) (time / dt)];
                                        volt = selements.get(m).gain * control;
                                    }
                                    selements.get(m).errorvoltageValues[(int)(time/dt)+1]=volt;
                                    if (selements.get(m).node1.equals(sunions.get(j).nod.get(i).name) && selements.get(m).node2.equals(sunions.get(j).nod.get(k).name)) {
                                        sunions.get(j).nod.get(k).voltageDef = true;
                                        sunions.get(j).nod.get(k).voltage = sunions.get(j).nod.get(i).voltage - volt;
                                    }
                                    if (selements.get(m).node2.equals(sunions.get(j).nod.get(i).name) && selements.get(m).node1.equals(sunions.get(j).nod.get(k).name)) {
                                        sunions.get(j).nod.get(k).voltageDef = true;
                                        sunions.get(j).nod.get(k).voltage = sunions.get(j).nod.get(i).voltage + volt;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    int mainsolver() {
       /* if (errorFour()==false) return -4;
        else if (errorThree()==false) return -3;*/
        int i, j, k, p, e, solveflag = 0;
        for (i = 0; i < sunions.size(); i++)
            for (j = 0; j < sunions.get(i).nod.size(); j++)
                System.out.println("union" + sunions.get(i).name + "node" + sunions.get(i).nod.get(j).name);
        double skcl = 0, skcl2 = 0, kclfirst, kclnext;
        for (j = 0; j < sunions.size(); j++) resetVoltage(j);
        for (j = 0; j < sunions.size(); j++) {
            skcl += sunions.get(j).kcl * sunions.get(j).kcl;
            System.out.println("kcl" + sunions.get(j).kcl);
        }
        skcl = Math.sqrt(skcl);
        for (i = 1; i <= endtime / dt; i++) {
           /* if (errorFourAndThree(i-1)==-3) return -3;
            else if (errorFourAndThree(i-1)==-4) return -4;
            else if (errorTwo(i-1)==false) return -2;*/
            System.out.println("time" + i);
            for (j = 0; j < sunions.size(); j++) resetVoltage(j);
            solveflag = 1;
            while (solveflag == 1) {
                //down text?//
                solveflag = 0;
                for (j = 1; j < sunions.size(); j++) {
                    resetVoltage(j);
                    Kcl(j);
                    kclfirst = sunions.get(j).kcl;
                    sunions.get(j).nod.get(0).voltage += dv;
                    resetVoltage(j);
                    Kcl(j);
                    kclnext = sunions.get(j).kcl;
                    sunions.get(j).nod.get(0).voltage += (dv * (Math.abs(kclfirst) - Math.abs(kclnext)) / di) - dv;
                    resetVoltage(j);
                    skcl = 0;
                    for (k = 0; k < sunions.size(); k++) {
                        skcl += sunions.get(k).kcl * sunions.get(k).kcl;
                    }
                    skcl = Math.sqrt(skcl);
                }
               // System.out.println("erfunkcl " + skcl);
                for (k = 0; k < sunions.size(); k++) if (Math.abs(sunions.get(k).kcl) >= di) solveflag = 1;
            }
            for (e = 0; e < sunions.size(); e++)
                for (p = 0; p < sunions.get(e).nod.size(); p++) {
                    System.out.println(sunions.get(e).nod.get(p).name + "voltage:" + sunions.get(e).nod.get(p).voltage);
                    sunions.get(e).nod.get(p).voltageValues[i] = sunions.get(e).nod.get(p).voltage;
                }
            for (e = 0; e < selements.size(); e++) {
                selements.get(e).voltageValues[i] = findNode(selements.get(e).node1) - findNode(selements.get(e).node2);
                if (selements.get(e).type.equals("r")) {
                    //selements.get(e).voltageValues[i] = findNode(selements.get(e).node1) - findNode(selements.get(e).node2);
                    selements.get(e).currentValues[i] = selements.get(e).voltageValues[i] / selements.get(e).resistance;
                }
                if (selements.get(e).type.equals("c")) {
                    //selements.get(e).voltageValues[i] = findNode(selements.get(e).node1) - findNode(selements.get(e).node2);
                    selements.get(e).currentValues[i] = selements.get(e).capacity * (selements.get(e).voltageValues[i] - selements.get(e).voltageValues[i-1]) / dt;
                }
                if (selements.get(e).type.equals("l")) {
                    selements.get(e).currentValues[i]=selements.get(e).currentValues[i-1]+dt*(findNode(selements.get(e).node1) - findNode(selements.get(e).node2)) / selements.get(e).inductance;
                    //selements.get(e).voltageValues[i] = findNode(selements.get(e).node1) - findNode(selements.get(e).node2);
                }

            }
            batterycurrent();

            time += dt;
        }
        /*for (i = 1; i <= time / dt; i++)
            System.out.println(i+":self" + selements.get(2).currentValues[i] + selements.get(2).name);*/
        printResults();
        return 0;
    }

    double findNode(String name) {
        int i, j;
        double volt = 0;
        for (i = 0; i < sunions.size(); i++) {
            for (j = 0; j < sunions.get(i).nod.size(); j++) {
                if (sunions.get(i).nod.get(j).name.equals(name)) volt = sunions.get(i).nod.get(j).voltage;
            }
        }
        return volt;
    }

    void Kcl(int i) {
        int j, k, e;
        double control = 0;
        sunions.get(i).kcl = 0;
        for (j = 0; j < sunions.get(i).nod.size(); j++) {
            for (k = 0; k < selements.size(); k++) {


                if (selements.get(k).type.equals("l")) {
                    if (selements.get(k).node1.equals(sunions.get(i).nod.get(j).name))
                        sunions.get(i).kcl += selements.get(k).currentValues[(int) (time / dt)] + dt * (sunions.get(i).nod.get(j).voltage - findNode(selements.get(k).node2)) / selements.get(k).inductance;
                    if (selements.get(k).node2.equals(sunions.get(i).nod.get(j).name))
                        sunions.get(i).kcl += -selements.get(k).currentValues[(int) (time / dt)] + dt * (sunions.get(i).nod.get(j).voltage - findNode(selements.get(k).node1)) / selements.get(k).inductance;
                }
                if (selements.get(k).type.equals("c")) {
                    if (selements.get(k).node1.equals(sunions.get(i).nod.get(j).name))
                        sunions.get(i).kcl += selements.get(k).capacity * ((sunions.get(i).nod.get(j).voltage - findNode(selements.get(k).node2)) - selements.get(k).voltageValues[(int) (time / dt)]) / dt;
                    if (selements.get(k).node2.equals(sunions.get(i).nod.get(j).name))
                        sunions.get(i).kcl += selements.get(k).capacity * ((sunions.get(i).nod.get(j).voltage - findNode(selements.get(k).node1)) + selements.get(k).voltageValues[(int) (time / dt)]) / dt;
                } else if (selements.get(k).type.equals("r")) {
                    if (selements.get(k).node1.equals(sunions.get(i).nod.get(j).name))
                        sunions.get(i).kcl += (sunions.get(i).nod.get(j).voltage - findNode(selements.get(k).node2)) / selements.get(k).resistance;
                    if (selements.get(k).node2.equals(sunions.get(i).nod.get(j).name))
                        sunions.get(i).kcl += (sunions.get(i).nod.get(j).voltage - findNode(selements.get(k).node1)) / selements.get(k).resistance;
                } else if (selements.get(k).type.equals("cs")) {
                    selements.get(k).currentValues[(int)(time/dt)+1]=selements.get(k).dc + selements.get(k).ac * Math.sin(2 * Math.PI * selements.get(k).frequncey * time + selements.get(k).phase);
                    if (selements.get(k).node1.equals(sunions.get(i).nod.get(j).name))
                        sunions.get(i).kcl -= selements.get(k).dc + selements.get(k).ac * Math.sin(2 * Math.PI * selements.get(k).frequncey * time + selements.get(k).phase);
                    if (selements.get(k).node2.equals(sunions.get(i).nod.get(j).name))
                        sunions.get(i).kcl += selements.get(k).dc + selements.get(k).ac * Math.sin(2 * Math.PI * time * selements.get(k).frequncey + selements.get(k).phase);
                }
                if (selements.get(k).type.equals("vcc")) {
                    selements.get(k).currentValues[(int)(time/dt)+1]=selements.get(k).gain * (findNode(selements.get(k).node3) - findNode(selements.get(k).node4));
                    if (selements.get(k).node1.equals(sunions.get(i).nod.get(j).name))
                        sunions.get(i).kcl -= selements.get(k).gain * (findNode(selements.get(k).node3) - findNode(selements.get(k).node4));
                    if (selements.get(k).node2.equals(sunions.get(i).nod.get(j).name))
                        sunions.get(i).kcl += selements.get(k).gain * (findNode(selements.get(k).node3) - findNode(selements.get(k).node4));
                }
                if (selements.get(k).type.equals("ccc")) {
                    for (e = 0; e < selements.size(); e++)
                        if (selements.get(e).name.equals(selements.get(k).controlelement))
                            control = selements.get(e).currentValues[(int) (time / dt)];
                    selements.get(k).currentValues[(int)(time/dt)+1]= control * selements.get(k).gain;
                    if (selements.get(k).node1.equals(sunions.get(i).nod.get(j).name))
                        sunions.get(i).kcl -= control * selements.get(k).gain;
                    if (selements.get(k).node2.equals(sunions.get(i).nod.get(j).name))
                        sunions.get(i).kcl += control * selements.get(k).gain;
                }

            }
        }
    }

    public void batterycurrent() {
        int i, j, k, flag, index = 0, n = 0, z = 0;
        double current = 0;
        for (i = 0; i < selements.size(); i++)
            if (selements.get(i).type.equals("vs") || selements.get(i).type.equals("ccv") || selements.get(i).type.equals("vcv")) {
                selements.get(i).currentdef = false;
                z++;
            }
        for (n = 1; n <= z; n++) {
            for (i = 0; i < sunions.size(); i++) {
                for (j = 0; j < sunions.get(i).nod.size(); j++) {
                    current = 0;
                    flag = 0;
                    for (k = 0; k < selements.size(); k++) {
                        if ((sunions.get(i).nod.get(j).name.equals(selements.get(k).node1) || sunions.get(i).nod.get(j).name.equals(selements.get(k).node2)) && (selements.get(k).type.equals("vs") || selements.get(k).type.equals("ccv") || selements.get(k).type.equals("vcv")) && selements.get(k).currentdef == false) {
                            flag++;
                            index = k;
                        } else {
                            if (selements.get(k).node1.equals(sunions.get(i).nod.get(j).name))
                                current += selements.get(k).currentValues[((int) (time / dt)) + 1];
                            if (selements.get(k).node2.equals(sunions.get(i).nod.get(j).name))
                                current -= selements.get(k).currentValues[((int) (time / dt)) + 1];
                        }
                    }
                    //  System.out.println("battery: " + flag + " " + index + " " + current+ " "+ sunions.get(i).nod.get(j).name );
                    //

                    if (flag == 1) {
                        if (selements.get(index).node1.equals(sunions.get(i).nod.get(j).name))
                            selements.get(index).currentValues[((int) (time / dt)) + 1] = -current;
                        if (selements.get(index).node2.equals(sunions.get(i).nod.get(j).name))
                            selements.get(index).currentValues[((int) (time / dt)) + 1] = current;
                        selements.get(index).currentdef = true;
                        //System.out.println("bat" + selements.get(index).currentValues[(int) (time / dt) + 1] + " " + selements.get(index).name);
                    }
                    // System.out.println("bat" + selements.get(index).currentValues[(int) (time / dt) + 1] + " " + selements.get(index).name);
                }
            }
        }
    }

    public void printResults() {

        int[] nodeNames = new int[snodes.size()];
        for (int k = 0; k < snodes.size(); k++) {
            nodeNames[k] = Integer.parseInt(snodes.get(k).name);
        }
        Arrays.sort(nodeNames);

        for (int k = 0; k < snodes.size(); k++) {
            for (int h = 0; h < snodes.size(); h++) {
                if (nodeNames[k] == Integer.parseInt(snodes.get(h).name)) {
                    nodesInOrder.add(snodes.get(h));
                }
            }
        }
    }





    public boolean errorTwo(int time) {
        Element[] currentSourceElements = new Element[5];
        double sumOfValeus = 0;
        int currentSourceNumbers = 0, allElements = 0;
        for (int i = 0; i < initialTextProccesor.nodes.size(); i++) {
            sumOfValeus = 0;
            currentSourceNumbers = 0;
            allElements = 0;
            for (int j = 0; j < initialTextProccesor.elements.size(); j++) {
                if (initialTextProccesor.elements.get(j).node1.equals(initialTextProccesor.nodes.get(i).name)
                        || initialTextProccesor.elements.get(j).node1.equals(initialTextProccesor.nodes.get(i).name)) {
                    allElements++;
                    if (initialTextProccesor.elements.get(j).type.equals("cs")
                            || initialTextProccesor.elements.get(j).type.equals("ccc")
                            || initialTextProccesor.elements.get(j).type.equals("vcc")) {
                        currentSourceElements[currentSourceNumbers] = initialTextProccesor.elements.get(j);
                        currentSourceNumbers++;
                    }
                }
            }
            if (currentSourceNumbers == allElements) {
                for (int k = 0; k < currentSourceNumbers; k++) {
                    if (currentSourceElements[k].node1.equals(initialTextProccesor.nodes.get(i).name))
                        sumOfValeus -= (currentSourceElements[k].currentValues[time]);
                    else
                        sumOfValeus += (currentSourceElements[k].currentValues[time]);
                }
                if (Math.abs(sumOfValeus)>di)
                    return false;
            }
        }
        return true;
    }



    public boolean errorThree() {
        for (int j = 0; j < initialTextProccesor.elements.size(); j++) {
            if (initialTextProccesor.elements.get(j).type.equals("vs")
                    || initialTextProccesor.elements.get(j).type.equals("vcv")
                    || initialTextProccesor.elements.get(j).type.equals("ccv")) {
                for (int i = 0; i < initialTextProccesor.elements.size(); i++) {
                }
            }
        }
        return true;
    }


    public boolean errorFour() {
        for(int i=0;i<initialTextProccesor.nodes.size();i++){
            if(initialTextProccesor.nodes.get(i).name.equals("0"))
                return false;
        }
        return true;

    }




    ///////////////////////shayad niaz beshe tagrib bezani daxele errore 4


    public int errorFourAndThree(int time) {
        boolean flag=true;
        for (int i = 0; i < selements.size(); i++) {
            if (selements.get(i).type.equals("vc") || selements.get(i).type.equals("vcv") || selements.get(i).type.equals("ccv")) {
                if (selements.get(i).voltageValues[time] != (findNode(selements.get(i).node1) - findNode(selements.get(i).node2))) {
                    for(int j=0;j<sunions.get(0).nod.size();j++)
                        if(selements.get(i).node1.equals(sunions.get(0).nod.get(i))||selements.get(i).node2.equals(sunions.get(0).nod.get(i))){
                            flag=false;
                            break;
                        }
                    if (flag)
                        return -3;

                    else
                        return -4;
                }
            }
        }
        return 0;
    }

}