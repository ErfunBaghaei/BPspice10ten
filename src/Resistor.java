public class Resistor extends Element {
    String input;
    public Resistor(String input){
        this.input=input;
        String [] info =input.split("\\s+");
        name = info[0];
        node1 = info[1];
        node2 = info[2];
        if (info[3].charAt(info[3].length()-1)>='0'&&info[3].charAt(info[3].length()-1)<='9') resistance = Integer.parseInt(info[3]);
        else resistance = Integer.parseInt(info[3].substring(0,info[3].length()-1));
        switch (info[3].charAt(info[3].length()-1)){
            case 'p': resistance*=10e-12; break;
            case 'n': resistance*=10e-9; break;
            case 'u': resistance*=10e-6; break;
            case 'm': resistance*=10e-3; break;
            case 'k': resistance*=10e3; break;
            case 'M': resistance*=10e6; break;
            case 'G': resistance*=10e9; break;
           // System.out.print("salmress");
        }
    }
}
