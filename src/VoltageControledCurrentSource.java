public class VoltageControledCurrentSource extends Element{
    String input;
    double gain=0;
    public VoltageControledCurrentSource(String input)
    {
        super.type="vcc";
        this.input=input;
        String[] info = input.split("\\s+");
        name = info[0];
        node1 = info[1];
        node2 = info[2];
        node3=info[3];
        node4=info[4];
        if (info[5].charAt(info[5].length() - 1) >= '0' && info[5].charAt(info[5].length() - 1) <= '9')
            gain = Double.parseDouble(info[5]);
        else gain = Double.parseDouble(info[5].substring(0, info[5].length() - 1));
        switch (info[5].charAt(info[5].length() - 1)) {
            case 'p':
                gain *= 1e-12;
                break;
            case 'n':
                gain *= 1e-9;
                break;
            case 'u':
                gain *= 1e-6;
                break;
            case 'm':
                gain *= 1e-3;
                break;
            case 'k':
                gain *= 1e3;
                break;
            case 'M':
                gain *= 1e6;
                break;
            case 'G':
                gain *= 1e9;
                break;
        }

    }
}
