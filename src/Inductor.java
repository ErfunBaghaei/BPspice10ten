public class Inductor extends Element {
    String input;
    public Inductor(String input){
        this.input=input;
        String [] info =input.split("\\s+");
        name = info[0];
        node1 = info[1];
        node2 = info[2];
        if (info[3].charAt(info[3].length()-1)>='0'&&info[3].charAt(info[3].length()-1)<='9') inductance = Integer.parseInt(info[3]);
        else inductance = Integer.parseInt(info[3].substring(0,info[3].length()-1));
        switch (info[3].charAt(info[3].length()-1)){
            case 'p': inductance*=10e-12; break;
            case 'n': inductance*=10e-9; break;
            case 'u': inductance*=10e-6; break;
            case 'm': inductance*=10e-3; break;
            case 'k': inductance*=10e3; break;
            case 'M': inductance*=10e6; break;
            case 'G': inductance*=10e9; break;
        }
    }
}
