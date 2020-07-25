public class CurrentControledCurrentSource extends Element{
    String input;
    public CurrentControledCurrentSource(String input){

        super.type="ccc";
        this.input=input;

        String[] info = input.split("\\s+");
        name = info[0];
        node1 = info[1];
        node2 = info[2];
       controlelement = info[3];
        if (info[4].charAt(info[4].length() - 1) >= '0' && info[4].charAt(info[4].length() - 1) <= '9')
            gain = Double.parseDouble(info[4]);
        else gain = Double.parseDouble(info[4].substring(0, info[4].length() - 1));
        switch (info[4].charAt(info[4].length() - 1)) {
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
