import java.util.ArrayList;


public class Solver {
    int [] [] sgraph ;
    double di,dv,dt,time=0,endtime;
    ArrayList<Union> sunions = new ArrayList();
    ArrayList<Element> selements=new ArrayList<Element>();
    ArrayList<Node> snodes= new ArrayList();
    Solver(ArrayList<Union> a, ArrayList<Element> b,ArrayList<Node> c, int [] [] d,double deltav
            ,double deltat,double deltai,double time){
        int i,j;
        endtime=time;
        di= deltai;
        dv=deltav;
        dt= deltat;

        this.sunions=a;
        this.selements=b;

        this.snodes=c;
        this.sgraph=d;
        System.out.println(d[0][0]+" "+d[0][1]+" "+d[1][0]+" "+d[1][1]);
        System.out.println(di+"    "+dt+"   "+dv+"    "+a.size()+"    "+sunions.size()+"    "+selements.size()+"    "+snodes.size());


    }
    void resetVoltage() {
        int i, flag, j,k,m;
        for (j = 0; j < sunions.size(); j++){
            System.out.println("llll  "+j);
            sunions.get(j).nod.get(0).voltageDef = true;
            for (i = 1; i < sunions.get(j).nod.size(); i++) sunions.get(j).nod.get(i).voltageDef = false;
           // while (true) {
                flag = 0;
                for (i = 0; i < sunions.get(j).nod.size(); i++)
                    if (sunions.get(j).nod.get(i).voltageDef == false)
                        flag = 1;

                if (flag == 1) break;
                for (i=0;i<sunions.get(j).nod.size();i++){
                    if (sunions.get(j).nod.get(i).voltageDef==true){
                        for (k=0;k<sunions.get(j).nod.size();k++){
                            if (sunions.get(j).nod.get(k).voltageDef==false){
                                for (m=0;m<selements.size();m++){
                                    if (selements.get(m).name.charAt(0)=='v'||selements.get(m).name.charAt(0)=='V'||selements.get(m).name.charAt(0)=='e'||selements.get(m).name.charAt(0)=='E'||selements.get(m).name.charAt(0)=='h'||selements.get(m).name.charAt(0)=='H'){
                                        if (selements.get(m).node1.equals(sunions.get(j).nod.get(i).name)&&selements.get(m).node2.equals(sunions.get(j).nod.get(k).name)){
                                            sunions.get(j).nod.get(k).voltageDef=true;
                                            sunions.get(j).nod.get(k).voltage=sunions.get(j).nod.get(i).voltage-selements.get(m).dc;
                                        }
                                        if (selements.get(m).node2.equals(sunions.get(j).nod.get(i).name)&&selements.get(m).node1.equals(sunions.get(j).nod.get(k).name)){
                                            sunions.get(j).nod.get(k).voltageDef=true;
                                            sunions.get(j).nod.get(k).voltage=sunions.get(j).nod.get(i).voltage+selements.get(m).dc;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
           // }
        }
    }


    void mainsolver() {
        int i, j, k;
        double skcl = 0, skcl2 = 0;
        System.out.println("step="+endtime/dt);
        resetVoltage();
        Kcl();
        for (j = 0; j < sunions.size(); j++) skcl += sunions.get(j).kcl * sunions.get(j).kcl;
        System.out.println("hierfan");
        skcl = Math.sqrt(skcl);
        for (i = 1; i <= endtime / dt; i++) {
            System.out.println("time"+i);
            for (j = 1; j < sunions.size(); j++) {
                sunions.get(j).nod.get(0).voltage += dv;
                resetVoltage();
                Kcl();
                for (k = 0; k < sunions.size(); k++) skcl2 += sunions.get(k).kcl * sunions.get(k).kcl;
                skcl2 = Math.sqrt(skcl2);
                if (skcl2 < skcl) skcl = skcl2;
                else {
                    sunions.get(j).nod.get(0).voltage -= 2 * dv;
                    resetVoltage();
                    Kcl();
                    for (k = 0; k < sunions.size(); k++) skcl2 += sunions.get(k).kcl * sunions.get(k).kcl;
                    skcl2 = Math.sqrt(skcl2);
                    if (skcl2 < skcl) skcl = skcl2;
                    else sunions.get(j).nod.get(0).voltage += dv;
                    resetVoltage();
                }
            }
            time += dt;
        }

        printResults();
    }
    double findNode(String name){
        int i,j;
        double volt=0;
        for (i=0;i<sunions.size();i++){
            for (j=0;j<sunions.get(i).nod.size();j++){
                if (sunions.get(i).nod.get(j).name.equals(name)) volt= sunions.get(i).nod.get(j).voltage;
            }
        }
        return volt;
    }
    void Kcl(){
        int i,j,k;
        for (i=0;i<sunions.size();i++){
            for (j=0;j<sunions.get(i).nod.size();j++){
                for (k=0;k<selements.size();k++){
                    if (selements.get(k).name.charAt(0)=='r'||selements.get(k).name.charAt(0)=='R'){
                        if (selements.get(k).node1.equals(sunions.get(i).nod.get(j).name)) sunions.get(i).kcl+=(sunions.get(i).nod.get(j).voltage-findNode(selements.get(k).node2))/selements.get(k).resistance;
                        if (selements.get(k).node2.equals(sunions.get(i).nod.get(j).name)) sunions.get(i).kcl+=(sunions.get(i).nod.get(j).voltage-findNode(selements.get(k).node1))/selements.get(k).resistance;
                    }
                    if (selements.get(k).name.charAt(0)=='i'||selements.get(k).name.charAt(0)=='I'){
                        if (selements.get(k).node1.equals(sunions.get(i).nod.get(j).name)) sunions.get(i).kcl-=selements.get(k).dc;
                        if (selements.get(k).node2.equals(sunions.get(i).nod.get(j).name)) sunions.get(i).kcl+=selements.get(k).dc;
                    }
                }
            }
        }
    }

    public void printResults(){
        for(int i=0;i<sunions.size();i++)
            for(int j=0;j<sunions.get(i).nod.size();j++)
                System.out.println("result"+sunions.get(i).nod.get(j).voltage);


    }


}
/// resualts >>sunoions  >> node  >> voltage