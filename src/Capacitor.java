public class Capacitor extends Element {
    String input;
    public Capacitor(String input){
        super.type="c";
        this.input=input;
        String [] info =input.split("\\s+");
        name = info[0];
        node1 = info[1];
        node2 = info[2];
        if (info[3].charAt(info[3].length()-1)>='0'&&info[3].charAt(info[3].length()-1)<='9') capacity = Integer.parseInt(info[3]);
        else capacity = Integer.parseInt(info[3].substring(0,info[3].length()-1));
        switch (info[3].charAt(info[3].length()-1)){
            case 'p': capacity*=10e-12; break;
            case 'n': capacity*=10e-9; break;
            case 'u': capacity*=10e-6; break;
            case 'm': capacity*=10e-3; break;
            case 'k': capacity*=10e3; break;
            case 'M': capacity*=10e6; break;
            case 'G': capacity*=10e9; break;
            // System.out.print("yeeeees");

            ///check this

        }
    }
}