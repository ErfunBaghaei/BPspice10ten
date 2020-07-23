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
       // for (j = 0; j < sunions.size(); j++) {
            System.out.println("llllx  " + j);
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
                                    if (selements.get(m).name.charAt(0) == 'v' || selements.get(m).name.charAt(0) == 'V' || selements.get(m).name.charAt(0) == 'e' || selements.get(m).name.charAt(0) == 'E' || selements.get(m).name.charAt(0) == 'h' || selements.get(m).name.charAt(0) == 'H') {
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
      //  }
    }


    void mainsolver() {
        int i, j, k, p, e, solveflag = 0;
        double skcl = 0, skcl2 = 0, kclfirst, kclnext;
        System.out.println("step=" + endtime / dt);
       for (j=0;j<sunions.size();j++) resetVoltage(j);
        //for (i=0;i<sunions.size();i++) for (j=0;j<sunions.get(i).nod.size();j++) System.out.println(sunions.get(i).nod.get(j).name+"e+ "+sunions.get(i).nod.get(j).union+"v+ "+sunions.get(i).nod.get(j).voltage);
        // Kcl();
        for (j = 0; j < sunions.size(); j++) {
            skcl += sunions.get(j).kcl * sunions.get(j).kcl;
            System.out.println("kcl" + sunions.get(j).kcl);
        }
        skcl = Math.sqrt(skcl);
        for (i = 1; i <= endtime / dt; i++) {
            System.out.println("time" + i);

            solveflag = 1;
            while (solveflag == 1) {
           /*solveflag=0;
            for (j = 1; j < sunions.size(); j++) {
                sunions.get(j).nod.get(0).voltage += dv;
                resetVoltage();
                Kcl();
                skcl2=0;
                for (k = 0; k < sunions.size(); k++) {
                   skcl2 += sunions.get(k).kcl * sunions.get(k).kcl;
                }
               skcl2 = Math.sqrt(skcl2);
                if (skcl2 < skcl) {
                    skcl = skcl2;
                    solveflag=1;
                }
                else {
                    sunions.get(j).nod.get(0).voltage -= 2 * dv;
                    resetVoltage();
                    Kcl();
                    skcl2=0;
                    for (k = 0; k < sunions.size(); k++)  skcl2 += sunions.get(k).kcl * sunions.get(k).kcl;
                    skcl2 = Math.sqrt(skcl2);
                    if (skcl2 < skcl){
                        skcl = skcl2;
                        solveflag=1;
                    }
                    else sunions.get(j).nod.get(0).voltage += dv;
                    resetVoltage();
                }
                System.out.println("erfunkcl "+ skcl);
                for (e=0;e<sunions.size();e++) for (p=0;p<sunions.get(e).nod.size();p++) {
                    System.out.println(sunions.get(e).nod.get(p).name+"voltage:"+sunions.get(e).nod.get(p).voltage);
                }
              for (e=0;e<sunions.size();e++){
                  System.out.println(sunions.get(e).name+"kcl :"+sunions.get(e).kcl);
              }
            }
            if (solveflag==0) break;*/
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
                for (k = 0; k < sunions.size(); k++) if (Math.abs(sunions.get(k).kcl) >= di) solveflag = 1;
            }
            for (e = 0; e < sunions.size(); e++)
                for (p = 0; p < sunions.get(e).nod.size(); p++) {
                    System.out.println(sunions.get(e).nod.get(p).name + "voltage:" + sunions.get(e).nod.get(p).voltage);
                    sunions.get(e).nod.get(p).voltageValues[i] = sunions.get(e).nod.get(p).voltage;
                }
            for (e = 0; e < selements.size(); e++) {
                if (selements.get(e).name.charAt(0) == 'r' || selements.get(e).name.charAt(0) == 'R') {
                    selements.get(e).voltageValues[i] = findNode(selements.get(e).node1) - findNode(selements.get(e).node2);
                    selements.get(e).currentValues[i] = selements.get(e).voltageValues[i] / selements.get(e).resistance;
                }
                if (selements.get(e).name.charAt(0) == 'c' || selements.get(e).name.charAt(0) == 'C') {
                    selements.get(e).voltageValues[i] = findNode(selements.get(e).node1) - findNode(selements.get(e).node2);
                }
            }
            time += dt;
        }

        printResults();
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
        int j, k;
        // for (i=0;i<sunions.size();i++){
        sunions.get(i).kcl = 0;
        for (j = 0; j < sunions.get(i).nod.size(); j++) {
            for (k = 0; k < selements.size(); k++) {
                if (selements.get(k).name.charAt(0) == 'c' || selements.get(k).name.charAt(0) == 'C') {
                    if (selements.get(k).node1.equals(sunions.get(i).nod.get(j).name))
                        sunions.get(i).kcl += selements.get(k).capacity * ((sunions.get(i).nod.get(j).voltage - findNode(selements.get(k).node2)) - selements.get(k).voltageValues[(int) (time / dt)]) / dt;
                    if (selements.get(k).node2.equals(sunions.get(i).nod.get(j).name))
                        sunions.get(i).kcl += selements.get(k).capacity * ((sunions.get(i).nod.get(j).voltage - findNode(selements.get(k).node1)) + selements.get(k).voltageValues[(int) (time / dt)]) / dt;
                }
                if (selements.get(k).name.charAt(0) == 'r' || selements.get(k).name.charAt(0) == 'R') {
                    if (selements.get(k).node1.equals(sunions.get(i).nod.get(j).name))
                        sunions.get(i).kcl += (sunions.get(i).nod.get(j).voltage - findNode(selements.get(k).node2)) / selements.get(k).resistance;
                    if (selements.get(k).node2.equals(sunions.get(i).nod.get(j).name))
                        sunions.get(i).kcl += (sunions.get(i).nod.get(j).voltage - findNode(selements.get(k).node1)) / selements.get(k).resistance;
                }
                if (selements.get(k).name.charAt(0) == 'i' || selements.get(k).name.charAt(0) == 'I') {
                    if (selements.get(k).node1.equals(sunions.get(i).nod.get(j).name))
                        sunions.get(i).kcl -= selements.get(k).dc;
                    if (selements.get(k).node2.equals(sunions.get(i).nod.get(j).name))
                        sunions.get(i).kcl += selements.get(k).dc;
                }
            }
        }
        // }
    }

    public void printResults() {

        for (int i = 0; i < sunions.size(); i++) {
            for (int j = 0; j < sunions.get(i).nod.size(); j++) {
                System.out.println("result" + sunions.get(i).nod.get(j).voltage);
                nodesFP.add(sunions.get(i).nod.get(j));


            }
        }

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
/// resualts >>sunoions  >> node  >> voltage