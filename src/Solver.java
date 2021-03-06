import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;


public class Solver {
    int timees;
    int[][] sgraph;
    double di, dv, dt, time = 0, endtime;
    ArrayList<Union> sunions = new ArrayList();
    ArrayList<Element> selements = new ArrayList<Element>();
    ArrayList<Node> snodes = new ArrayList();
    ArrayList<Node> nodesInOrder = new ArrayList();
    ErrorFinder errorFinder;
    JFrame circuit;
    InitialTextProccesor initialTextProccesor;
    JProgressBar percent;

    Solver(InitialTextProccesor initialTextProccesor, JFrame circuit, JProgressBar percent) {
        int i, j;
        this.circuit = circuit;
        this.percent = percent;
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
                                        volt = selements.get(m).dc + selements.get(m).ac * Math.sin(2 * Math.PI * selements.get(m).frequncey * (time + dt) + selements.get(m).phase);
                                    if (selements.get(m).type.equals("vcv"))
                                        volt = selements.get(m).gain * (findNode(selements.get(m).node3) - findNode(selements.get(m).node4));
                                    if (selements.get(m).type.equals("ccv")) {

                                        for (w = 0; w < selements.size(); w++)
                                            if (selements.get(w).name.equals(selements.get(m).controlelement))
                                                control = selements.get(w).currentValues[(int) (time / dt)];
                                        volt = selements.get(m).gain * control;
                                      //  System.out.println(selements.get(m).type + " gain  " + volt);
                                    }
                                    selements.get(m).errorvoltageValues[(int) (time / dt) + 1] = volt;
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

    int f = 0;

    int mainsolver() {
        double[] continouesKCL = new double[4];
        int i, j, k, p, e, solveflag = 0, sor = 0;
        timees = (int) (endtime / dt);
        double skcl = 0, skcl2 = 0, kclfirst, kclnext;
        for (j = 0; j < sunions.size(); j++) resetVoltage(j);
        for (i = 1; i <= timees; i++) {
           Arrays.fill(continouesKCL,0);
            if (errorFourAndThree(i - 1) == -3)
                return -3;
            else if (errorFourAndThree(i - 1) == -4)
                return -4;
          //  System.out.println("time" + (int) (100 * ((double) i / (double) timees)));
            if ((int) ((100 * ((double) i / (double) timees))) != f) {

                f = (int) (100 * ((double) i / (double) timees));
                percent.setValue(f);
                percent.update(percent.getGraphics());
            }
            for (j = 0; j < sunions.size(); j++) resetVoltage(j);
            solveflag = 1;
            sor = 0;
            while (solveflag == 1) {
                /////
                // if (sor==100000) break;
                sor++;
                ///////
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
                System.out.println("erfunkcl " + skcl);
                continouesKCL[0] = continouesKCL[1];
                continouesKCL[1] = continouesKCL[2];
                continouesKCL[2] = continouesKCL[3];
                continouesKCL[3] = skcl;
                if (continouesKCL[0] == continouesKCL[1] && continouesKCL[2] == continouesKCL[1] && continouesKCL[2] == continouesKCL[3]) {
                    return -2;
                }
                for (k = 0; k < sunions.size(); k++) if (Math.abs(sunions.get(k).kcl) >= di) solveflag = 1;
            }
            for (e = 0; e < sunions.size(); e++)
                for (p = 0; p < sunions.get(e).nod.size(); p++) {
                 //   System.out.println(sunions.get(e).nod.get(p).name + "voltage:" + sunions.get(e).nod.get(p).voltage);
                    sunions.get(e).nod.get(p).voltageValues[i] = sunions.get(e).nod.get(p).voltage;
                }
            for (e = 0; e < selements.size(); e++) {
                selements.get(e).voltageValues[i] = findNode(selements.get(e).node1) - findNode(selements.get(e).node2);
                if (selements.get(e).type.equals("r")) {
                    selements.get(e).currentValues[i] = selements.get(e).voltageValues[i] / selements.get(e).resistance;
                }
                if (selements.get(e).type.equals("c")) {
                    //selements.get(e).voltageValues[i] = findNode(selements.get(e).node1) - findNode(selements.get(e).node2);
                    selements.get(e).currentValues[i] = selements.get(e).capacity * (selements.get(e).voltageValues[i] - selements.get(e).voltageValues[i - 1]) / dt;
                }
                if (selements.get(e).type.equals("l")) {
                    selements.get(e).currentValues[i] = selements.get(e).currentValues[i - 1] + dt * (findNode(selements.get(e).node1) - findNode(selements.get(e).node2)) / selements.get(e).inductance;
                    //selements.get(e).voltageValues[i] = findNode(selements.get(e).node1) - findNode(selements.get(e).node2);
                }

            }
            batterycurrent();

            time += dt;
        }
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
                    selements.get(k).currentValues[(int) (time / dt) + 1] = selements.get(k).dc + selements.get(k).ac * Math.sin(2 * Math.PI * selements.get(k).frequncey * time + selements.get(k).phase);
                    if (selements.get(k).node1.equals(sunions.get(i).nod.get(j).name))
                        sunions.get(i).kcl -= selements.get(k).dc + selements.get(k).ac * Math.sin(2 * Math.PI * selements.get(k).frequncey * time + selements.get(k).phase);
                    if (selements.get(k).node2.equals(sunions.get(i).nod.get(j).name))
                        sunions.get(i).kcl += selements.get(k).dc + selements.get(k).ac * Math.sin(2 * Math.PI * time * selements.get(k).frequncey + selements.get(k).phase);
                }
                if (selements.get(k).type.equals("vcc")) {
                    selements.get(k).currentValues[(int) (time / dt) + 1] = selements.get(k).gain * (findNode(selements.get(k).node3) - findNode(selements.get(k).node4));
                    // System.out.println("vcc"+"gain:" + selements.get(k).gain+"node 3: "+selements.get(k).node3+" node 4 "+selements.get(k).node4);
                    if (selements.get(k).node1.equals(sunions.get(i).nod.get(j).name))
                        sunions.get(i).kcl -= selements.get(k).gain * (findNode(selements.get(k).node3) - findNode(selements.get(k).node4));
                    if (selements.get(k).node2.equals(sunions.get(i).nod.get(j).name))
                        sunions.get(i).kcl += selements.get(k).gain * (findNode(selements.get(k).node3) - findNode(selements.get(k).node4));
                }
                if (selements.get(k).type.equals("ccc")) {
                    for (e = 0; e < selements.size(); e++)
                        if (selements.get(e).name.equals(selements.get(k).controlelement))
                            control = selements.get(e).currentValues[(int) (time / dt)];
                    selements.get(k).currentValues[(int) (time / dt) + 1] = control * selements.get(k).gain;
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

                    if (flag == 1) {
                        if (selements.get(index).node1.equals(sunions.get(i).nod.get(j).name))
                            selements.get(index).currentValues[((int) (time / dt)) + 1] = -current;
                        if (selements.get(index).node2.equals(sunions.get(i).nod.get(j).name))
                            selements.get(index).currentValues[((int) (time / dt)) + 1] = current;
                        selements.get(index).currentdef = true;
                    }
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

    public int errorFourAndThree(int time) {
        boolean flag9 = true;
        for (int i = 0; i < selements.size(); i++) {
            if (selements.get(i).type.equals("vs") || selements.get(i).type.equals("vcv") || selements.get(i).type.equals("ccv")) {
                if (time > 0)
                    if (Math.abs(selements.get(i).errorvoltageValues[time] - selements.get(i).voltageValues[time]) > dv && Math.abs(selements.get(i).errorvoltageValues[time - 1] - selements.get(i).voltageValues[time]) > dv) {
                        for (int j = 0; j < sunions.get(0).nod.size(); j++) {
                            if (selements.get(i).node1.equals(sunions.get(0).nod.get(j).name) || selements.get(i).node2.equals(sunions.get(0).nod.get(j).name)) {
                                flag9 = false;
                                break;
                            }
                        }
                        if (flag9)
                            return -3;

                        else
                            return -4;
                    }
            }
        }
        return 0;
    }
}