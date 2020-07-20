import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class InitialTextProccesor {

    File file;
    int [] [] graph=new int[1000][1000] ;
    ArrayList<Union> unions = new ArrayList();
    ArrayList<Element> elements=new ArrayList<Element>();
    ArrayList<Node> nodes= new ArrayList();
    ArrayList<Node> nodes2 = new ArrayList();
    ArrayList<Node> nodesInOrder= new ArrayList();





    double deltai,deltav,deltat,time;
    Node node1= new Node();
    Union union;
    InitialTextProccesor(File file){
        this.file=file;
    }
    boolean end=false;


    int lineNumber=1,flag=0,i,j,k,first=0,next=0;


    String currentLineInput;
    public boolean start() {

        for (int hh = 0; hh < 1000; hh++)
            for (int kk = 0; kk < 1000; kk++)
                graph[hh][kk] = 0;


        try {
            Scanner fileScan = new Scanner(file);
            currentLineInput = fileScan.nextLine();


            while (!currentLineInput.equals("END")) {
                flag = 0;



                if (graph[next][first] != 12) graph[next][first] = 1;
                if (graph[first][next] != 12) graph[first][next] = 1;

                if (currentLineInput.charAt(0) == '*') {
                } else {

                    if (currentLineInput.charAt(0) == 'i' || currentLineInput.charAt(0) == 'I')
                        createCurrentSource(currentLineInput);

                    else if (currentLineInput.charAt(0) == 'c' || currentLineInput.charAt(0) == 'C')
                        createCapacitor(currentLineInput);
                    else if (currentLineInput.charAt(0) == 'r' || currentLineInput.charAt(0) == 'R')
                        createResistor(currentLineInput);
                    else if (currentLineInput.charAt(0) == 'l' || currentLineInput.charAt(0) == 'L')
                        createInductor(currentLineInput);

                    else if (currentLineInput.charAt(0) == 'g' || currentLineInput.charAt(0) == 'G')
                        createCurrentControledCurrentSource(currentLineInput);

                    else if (currentLineInput.charAt(0) == 'f' || currentLineInput.charAt(0) == 'F')
                        createVoltageControledCurrentSource(currentLineInput);

                    else if (currentLineInput.charAt(0) == 'v' || currentLineInput.charAt(0) == 'V') {
                        createVoltageSource(currentLineInput);
                        graph[next][first] = 12;
                        graph[first][next] = 12;
                    } else if (currentLineInput.charAt(0) == 'h' || currentLineInput.charAt(0) == 'H') {
                        createCurrentControledVoltageSource(currentLineInput);
                        graph[next][first] = 12;
                        graph[first][next] = 12;
                    } else if (currentLineInput.charAt(0) == 'e' || currentLineInput.charAt(0) == 'E') {
                        createVoltageControledVoltageSource(currentLineInput);
                        graph[next][first] = 12;
                        graph[first][next] = 12;
                    } else if ((currentLineInput.charAt(0) == 'd' || currentLineInput.charAt(0) == 'D')
                            && !(currentLineInput.charAt(1) == 'v' || currentLineInput.charAt(1) == 'V')
                            && !(currentLineInput.charAt(1) == 'i' || currentLineInput.charAt(1) == 'I')
                            && !(currentLineInput.charAt(1) == 't' || currentLineInput.charAt(1) == 'T'))
                        createIdealDiode(currentLineInput);

                    else if (currentLineInput.indexOf("DV") == 0 || currentLineInput.indexOf("dV") == 0
                            || currentLineInput.indexOf("dv") == 0 || currentLineInput.indexOf("Dv") == 0)
                        deltav = setVoltagetick(currentLineInput);

                    else if (currentLineInput.indexOf("di") == 0 || currentLineInput.indexOf("dI") == 0
                            || currentLineInput.indexOf("Di") == 0 || currentLineInput.indexOf("DI") == 0)
                        deltai = setCurrenttick(currentLineInput);

                    else if (currentLineInput.indexOf("dt") == 0 || currentLineInput.indexOf("dT") == 0
                            || currentLineInput.indexOf("Dt") == 0 || currentLineInput.indexOf("DT") == 0)
                        deltat = setTimetick(currentLineInput);

                    else if (currentLineInput.indexOf(".tran") == 0)
                        time = setEndTime(currentLineInput);
                    else {
                        System.out.println("ERROR: UNKNOWN CHARCTERS IN LINE " + i);
                        System.out.println("  >>> :  "+currentLineInput);
                        return false;
                    }

                }
                currentLineInput = fileScan.nextLine();
                lineNumber++;


            }

            int i = 0;
            boolean flag=false;
            while (!nodes.get(i).name.equals("0")) {
                i++;
                flag=true;
            }
            if(flag)
             Collections.swap(nodes,i,0);
            int [] nodeNames=new int[nodes.size()];

            for(int k=0;k<nodes.size();k++){
                nodeNames[k]=Integer.parseInt(nodes.get(k).name);
            }

            Arrays.sort(nodeNames);

            for(int k=0;k<nodes.size();k++){
                for(int h=0;h<nodes.size();h++){
                    if(nodeNames[k]==Integer.parseInt(nodes.get(h).name)){

                        nodesInOrder.add(k,nodes.get(h));
                    }

                }

            }



        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        return true;

    }





    public void solve(){
        Solver solver= new Solver(unions,elements,nodes,graph,deltav,deltat,deltai,time);
        solver.mainsolver();
    }
    public void set_union(){

        int i;
        while (nodes2.size()>0){
            union= new Union();
            union.name= nodes2.get(0).union;
            for (i=0;i<nodes2.size();i++){
                if (nodes2.get(i).union==union.name){
                    union.nod.add(nodes2.get(i));
                    nodes2.remove(nodes2.get(i));
                }
            }
            unions.add(union);
        }
    }
    public void create_union() {
        int i,n=0,j;
        nodes.get(0).added=true;
        nodes2.add(nodes.get(0));
        for (i=0;i<nodes.size();i++){
            if (nodes.get(i).added==false){
                if (graph[0][i]==1) {
                    nodes.get(i).added=true;
                    nodes2.add(nodes.get(i));
                }
                if (graph[0][i]==12){
                    nodes.get(i).added=true;
                    nodes.get(i).union=0;
                    nodes2.add(nodes.get(i));
                }
            }
        }
        while (nodes2.size()<nodes.size()){
            for (i=nodes2.size()-1;i>=0;i--){
                for (j=0;j<nodes.size();j++) if (nodes.get(j).name.equals(nodes2.get(i).name)) n=j;
                for (j=0;j<nodes.size();j++){
                    if (nodes.get(j).added==false) {
                        if (graph[n][j] == 1) {
                            nodes.get(j).added = true;
                            nodes2.add(nodes.get(j));
                        }
                    }
                    if (graph[n][j]==12){
                        nodes.get(j).added=true;
                        nodes.get(j).union=nodes2.get(i).union;
                        nodes2.add(nodes.get(j));
                    }

                }
            }
        }
    }

    public void createCurrentSource(String currentLineInput){
        nodesNameSetter(currentLineInput);
        elements.add(new CurrentSource(currentLineInput));

    }

    public void createCapacitor(String currentLineInput){
        nodesNameSetter(currentLineInput);
        elements.add(new Capacitor(currentLineInput));
    }

    public void createResistor(String currentLineInput){
        nodesNameSetter(currentLineInput);
        elements.add(new Resistor(currentLineInput));
    }
    public void createInductor(String currentLineInput){
        nodesNameSetter(currentLineInput);
        elements.add(new Inductor(currentLineInput));
    }
    public void createCurrentControledCurrentSource(String currentLineInput){
        nodesNameSetter(currentLineInput);
        elements.add(new CurrentControledCurrentSource(currentLineInput));
    }
    public void createVoltageControledCurrentSource(String currentLineInput){
        nodesNameSetter(currentLineInput);
        elements.add(new VoltageControledCurrentSource(currentLineInput));
    }
    public void createVoltageSource(String currentLineInput){
        nodesNameSetter(currentLineInput);
        elements.add(new VoltageSource(currentLineInput));
    }
    public void createCurrentControledVoltageSource(String currentLineInput){
        nodesNameSetter(currentLineInput);
        elements.add(new CurrentControledVoltageSource(currentLineInput));
    }
    public void createVoltageControledVoltageSource(String currentLineInput){
        nodesNameSetter(currentLineInput);
        elements.add(new VoltageControledCurrentSource(currentLineInput));
    }
    public void createIdealDiode(String currentLineInput){
        nodesNameSetter(currentLineInput);
        elements.add(new IdealDiode(currentLineInput));
    }
    public double setCurrenttick(String currentLineInput){
        double i;
        if (currentLineInput.charAt(currentLineInput.length()-1)>='0'&&(currentLineInput.charAt(currentLineInput.length()-1)<='9')) return Double.parseDouble(currentLineInput.substring(currentLineInput.indexOf(' ')+1));
        else i=Double.parseDouble(currentLineInput.substring(currentLineInput.indexOf(' ')+1,currentLineInput.length()-1));
        switch (currentLineInput.charAt(currentLineInput.length()-1)){
            case 'p': i*=1e-12; break;
            case 'n': i*=1e-9; break;
            case 'u': i*=1e-6; break;
            case 'm': i*=1e-3; break;
            case 'k': i*=1e3; break;
            case 'M': i*=1e6; break;
            case 'G': i*=1e9; break;
        }
        return i;
    }
    public double setVoltagetick(String currentLineInput){
        double i;
        if (currentLineInput.charAt(currentLineInput.length()-1)>='0'&&(currentLineInput.charAt(currentLineInput.length()-1)<='9')) i= Double.parseDouble(currentLineInput.substring(currentLineInput.indexOf(' ')+1));
        else i=Double.parseDouble(currentLineInput.substring(currentLineInput.indexOf(' ')+1,currentLineInput.length()-1));
        switch (currentLineInput.charAt(currentLineInput.length()-1)){
            case 'p': i*=1e-12; break;
            case 'n': i*=1e-9; break;
            case 'u': i*=1e-6; break;
            case 'm': i*=1e-3; break;
            case 'k': i*=1e3; break;
            case 'M': i*=1e6; break;
            case 'G': i*=1e9; break;

        }
        return i;
    }
    public double setTimetick(String currentLineInput){
        double i;
        if (currentLineInput.charAt(currentLineInput.length()-1)>='0'&&(currentLineInput.charAt(currentLineInput.length()-1)<='9')) return Double.parseDouble(currentLineInput.substring(currentLineInput.indexOf(' ')+1));
        else i=Double.parseDouble(currentLineInput.substring(currentLineInput.indexOf(' ')+1,currentLineInput.length()-1));
        switch (currentLineInput.charAt(currentLineInput.length()-1)){
            case 'p': i*=1e-12; break;
            case 'n': i*=1e-9; break;
            case 'u': i*=1e-6; break;
            case 'm': i*=1e-3; break;
            case 'k': i*=1e3; break;
            case 'M': i*=1e6; break;
            case 'G': i*=1e9; break;

        }
        return i;
    }
    public double setEndTime(String currentLineInput){
        double i;
        if (currentLineInput.charAt(currentLineInput.length()-1)>='0'&&(currentLineInput.charAt(currentLineInput.length()-1)<='9')) return Double.parseDouble(currentLineInput.substring(currentLineInput.indexOf(' ')+1));
        else i=Double.parseDouble(currentLineInput.substring(currentLineInput.indexOf(' ')+1,currentLineInput.length()-1));
        switch (currentLineInput.charAt(currentLineInput.length()-1)){
            case 'p': i*=1e-12; break;
            case 'n': i*=1e-9; break;
            case 'u': i*=1e-6; break;
            case 'm': i*=1e-3; break;
            case 'k': i*=1e3; break;
            case 'M': i*=1e6; break;
            case 'G': i*=1e9; break;

        }
        return i;
    }

    public void nodesNameSetter(String currentLineInput){

        for (j=currentLineInput.indexOf(' ')+1;;j++) if (currentLineInput.charAt(j)==' ') break;
        node1=new Node();
        node1.name=currentLineInput.substring(currentLineInput.indexOf(' ')+1,j);
        for (i=0;i<nodes.size();i++) if (nodes.get(i).name.equals(node1.name)){
            first=i;
            flag=1;
        }
        if (flag==0) {
            nodes.add(node1);
            first=nodes.size()-1;
            nodes.get(nodes.size()-1).union=first;
        }
        else flag=0;

        for (k=j+1;;k++) if (currentLineInput.charAt(k)==' ') break;
        node1=new Node();
        node1.name=currentLineInput.substring(j+1,k);
        for (i=0;i<nodes.size();i++) if (nodes.get(i).name.equals(node1.name)) {
            next=i;
            flag=1;
        }
        if (flag==0){
            nodes.add(node1);
            next=nodes.size()-1;
            nodes.get(nodes.size()-1).union=next;
        }
        else flag=0;

    }

}
