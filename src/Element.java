public class Element {

    double resistance,dc,ac,capacity,inductance,phase,frequncey,gain;
    String name,node1,node2,node3=null,node4=null,type,controlelement=null;
    boolean currentdef=false,hasdraw=false;
    double[] voltageValues=new double[100000];
    double[] currentValues=new double[100000];
    double[] errorvoltageValues=new double[100000];
}
