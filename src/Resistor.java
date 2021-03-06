public class Resistor extends Element {
    String input;

    public Resistor(String input) {
        super.type = "r";
        this.input = input;
        String[] info = input.split("\\s+");
        name = info[0];
        node1 = info[1];
        node2 = info[2];
        if (info[3].charAt(info[3].length() - 1) >= '0' && info[3].charAt(info[3].length() - 1) <= '9')
            resistance = Double.parseDouble(info[3]);
        else resistance = Double.parseDouble(info[3].substring(0, info[3].length() - 1));
        switch (info[3].charAt(info[3].length() - 1)) {
            case 'p':
                resistance *= 1e-12;
                break;
            case 'n':
                resistance *= 1e-9;
                break;
            case 'u':
                resistance *= 1e-6;
                break;
            case 'm':
                resistance *= 1e-3;
                break;
            case 'k':
                resistance *= 1e3;
                break;
            case 'M':
                resistance *= 1e6;
                break;
            case 'G':
                resistance *= 1e9;
                break;
        }
    }
}
