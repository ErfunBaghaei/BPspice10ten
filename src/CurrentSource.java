public class CurrentSource extends Element{
    String input;
    public CurrentSource(String input){
        super.type="cs";
        this.input=input;
        String [] info =input.split("\\s+");
        name = info[0];
        node1 = info[1];
        node2 = info[2];
        if (info[3].charAt(info[3].length()-1)>='0'&&info[3].charAt(info[3].length()-1)<='9') dc = Integer.parseInt(info[3]);
        else dc = Integer.parseInt(info[3].substring(0,info[3].length()-1));
        switch (info[3].charAt(info[3].length()-1)){
            case 'p': dc*=10e-12; break;
            case 'n': dc*=10e-9; break;
            case 'u': dc*=10e-6; break;
            case 'm': dc*=10e-3; break;
            case 'k': dc*=10e3; break;
            case 'M': dc*=10e6; break;
            case 'G': dc*=10e9; break;
        }
        if (info[4].charAt(info[4].length()-1)>='0'&&info[4].charAt(info[4].length()-1)<='9') ac = Integer.parseInt(info[4]);
        else ac = Integer.parseInt(info[4].substring(0,info[4].length()-1));
        switch (info[4].charAt(info[4].length()-1)){
            case 'p': ac*=10e-12; break;
            case 'n': ac*=10e-9; break;
            case 'u': ac*=10e-6; break;
            case 'm': ac*=10e-3; break;
            case 'k': ac*=10e3; break;
            case 'M': ac*=10e6; break;
            case 'G': ac*=10e9; break;
        }
        frequncey = Integer.parseInt(info[5]);
        phase = Integer.parseInt(info[6]);

    }
}
