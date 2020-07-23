import java.util.ArrayList;
import java.util.Arrays;


public class Solver {
    int[][] sgraph;
    double di, dv, dt, time = 0, endtime;
    ArrayList<Union> sunions = new ArrayList();
    ArrayList<Element> selements = new ArrayList<Element>();
    ArrayList<Node> snodes = new ArrayList();
    ArrayList<Node> nodesFP = new ArrayList();
    ArrayList<Node> nodesInOrder = new ArrayList();

    Solver(InitialTextProccesor initialTextProccesor) {
        int i, j;
        endtime = initialTextProccesor.time;
        di = initialTextProccesor.deltai;
        dv = initialTextProccesor.deltav;
        dt = initialTextProccesor.deltat;

        this.sunions = initialTextProccesor.unions;
        this.selements = initialTextProccesor.elements;

        this.snodes = initialTextProccesor.nodes;
        this.sgraph = initialTextProccesor.graph;


    }

    void resetVoltage(int j) {
        int i, flag, k, m;
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
                                    if (selements.get(m).node1.equals(sunions.get(j).nod.get(i).name) && selements.get(m).node2.equals(sunions.get(j).nod.get(k).name)) {
                                        sunions.get(j).nod.get(k).voltageDef = true;
                                        sunions.get(j).nod.get(k).voltage = sunions.get(j).nod.get(i).voltage - selements.get(m).dc;
                                    }
                                    if (selements.get(m).node2.equals(sunions.get(j).nod.get(i).name) && selements.get(m).node1.equals(sunions.get(j).nod.get(k).name)) {
                                        sunions.get(j).nod.get(k).voltageDef = true;
                                        sunions.get(j).nod.get(k).voltage = sunions.get(j).nod.get(i).voltage + selements.get(m).dc;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    void mainsolver() {
        int i, j, k, p, e, solveflag = 0;
        double kclfirst, kclnext;
        for (i = 1; i <= endtime / dt; i++) {
                   System.out.println();

            solveflag = 1;
            while (solveflag == 1) {
                System.out.println("time1  " + i);
                //down tex2//
                solveflag = 0;
                for (j = 1; j < sunions.size(); j++) {
                    resetVoltage(j);
                    Kcl(j);
                    kclfirst = sunions.get(j).kcl;
                    sunions.get(j).nod.get(0).voltage += dv;
                    resetVoltage(j);
                    Kcl(j);
                    kclnext = sunions.get(j).kcl;
                    sunions.get(j).nod.get(0).voltage += (dv * (Math.abs(kclfirst) - Math.abs(kclnext)) / di) - dv;/////////////////////////////////////////notice here
                    resetVoltage(j);
                }
                for (k = 0; k < sunions.size(); k++) if (Math.abs(sunions.get(k).kcl) >= di) solveflag = 1;
            }
            for (e = 0; e < sunions.size(); e++)
                for (p = 0; p < sunions.get(e).nod.size(); p++) {
                    sunions.get(e).nod.get(p).voltageValues[i] = sunions.get(e).nod.get(p).voltage;
                }
            for (e = 0; e < selements.size(); e++) {
                if (selements.get(e).type.equals("r")) {
                    selements.get(e).voltageValues[i] = findNode(selements.get(e).node1) - findNode(selements.get(e).node2);
                    selements.get(e).currentValues[i] = selements.get(e).voltageValues[i] / selements.get(e).resistance;
                }
                if (selements.get(e).equals("c")) {
                    selements.get(e).voltageValues[i - 1] = findNode(selements.get(e).node1) - findNode(selements.get(e).node2);


                }
            }
            time += dt;
        }
        printResults();
    }


    void Kcl(int i) {
        int j, k;
        sunions.get(i).kcl = 0;
        for (j = 0; j < sunions.get(i).nod.size(); j++) {
            for (k = 0; k < selements.size(); k++) {
                if (selements.get(k).type.equals("c")) {
                    if (selements.get(k).node1.equals(sunions.get(i).nod.get(j).name))
                        sunions.get(i).kcl += selements.get(k).capacity * ((sunions.get(i).nod.get(j).voltage - findNode(selements.get(k).node2)) - selements.get(k).voltageValues[(int) (time / dt)]) / dt;
                    if (selements.get(k).node2.equals(sunions.get(i).nod.get(j).name))
                        sunions.get(i).kcl += selements.get(k).capacity * ((sunions.get(i).nod.get(j).voltage - findNode(selements.get(k).node1)) + selements.get(k).voltageValues[(int) (time / dt)]) / dt;
                }
                if (selements.get(k).type.equals("r")) {
                    if (selements.get(k).node1.equals(sunions.get(i).nod.get(j).name))
                        sunions.get(i).kcl += (sunions.get(i).nod.get(j).voltage - findNode(selements.get(k).node2)) / selements.get(k).resistance;
                    if (selements.get(k).node2.equals(sunions.get(i).nod.get(j).name))
                        sunions.get(i).kcl += (sunions.get(i).nod.get(j).voltage - findNode(selements.get(k).node1)) / selements.get(k).resistance;
                }
                if (selements.get(k).equals("cs") || selements.get(k).equals("ccc") || selements.get(k).equals("vcc")) {
                    if (selements.get(k).node1.equals(sunions.get(i).nod.get(j).name))
                        sunions.get(i).kcl -= selements.get(k).dc;
                    if (selements.get(k).node2.equals(sunions.get(i).nod.get(j).name))
                        sunions.get(i).kcl += selements.get(k).dc;
                }
            }
        }
    }


    double findNode(String name) {
        int i, j;
        double volt = 0;
        for (i = 0; i < sunions.size(); i++) {
            for (j = 0; j < sunions.get(i).nod.size(); j++) {
                if (sunions.get(i).nod.get(j).name.equals(name)) {
                    volt = sunions.get(i).nod.get(j).voltage;
                    break;
                }
            }
        }
        return volt;
    }


    public void printResults() {
        int[] nodeNames = new int[nodesFP.size()];
        for (int k = 0; k < nodesFP.size(); k++) {
            nodeNames[k] = Integer.parseInt(nodesFP.get(k).name);
        }
        Arrays.sort(nodeNames);

        for (int k = 0; k < nodesFP.size(); k++) {
            for (int h = 0; h < nodesFP.size(); h++) {
                if (nodeNames[k] == Integer.parseInt(nodesFP.get(h).name)) {
                    nodesInOrder.add(nodesFP.get(h));
                }
            }
        }
    }
}




