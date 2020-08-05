import javax.xml.crypto.dom.DOMCryptoContext;

public class Capacitor extends Element {
    String input;

    public Capacitor(String input) {
        super.type = "c";
        this.input = input;

        String[] info = input.split("\\s+");
        name = info[0];
        node1 = info[1];
        node2 = info[2];
        if (info[3].charAt(info[3].length() - 1) >= '0' && info[3].charAt(info[3].length() - 1) <= '9')
            capacity = Double.parseDouble(info[3]);
        else capacity = Double.parseDouble(info[3].substring(0, info[3].length() - 1));
        switch (info[3].charAt(info[3].length() - 1)) {
            case 'p':
                capacity *= 1e-12;
                break;
            case 'n':
                capacity *= 1e-9;
                break;
            case 'u':
                capacity *= 1e-6;
                break;
            case 'm':
                capacity *= 1e-3;
                break;
            case 'k':
                capacity *= 1e3;
                break;
            case 'M':
                capacity *= 1e6;
                break;
            case 'G':
                capacity *= 1e9;
                break;

        }
    }
}