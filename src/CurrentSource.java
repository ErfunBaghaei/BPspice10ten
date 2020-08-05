public class CurrentSource extends Element {
    String input;

    public CurrentSource(String input) {
        super.type = "cs";
        this.input = input;
        String[] info = input.split("\\s+");
        name = info[0];
        node1 = info[1];
        node2 = info[2];
        if (info[3].charAt(info[3].length() - 1) >= '0' && info[3].charAt(info[3].length() - 1) <= '9')
            dc = Double.parseDouble(info[3]);
        else dc = Double.parseDouble(info[3].substring(0, info[3].length() - 1));
        switch (info[3].charAt(info[3].length() - 1)) {
            case 'p':
                dc *= 1e-12;
                break;
            case 'n':
                dc *= 1e-9;
                break;
            case 'u':
                dc *= 1e-6;
                break;
            case 'm':
                dc *= 1e-3;
                break;
            case 'k':
                dc *= 1e3;
                break;
            case 'M':
                dc *= 1e6;
                break;
            case 'G':
                dc *= 1e9;
                break;
        }
        if (info[4].charAt(info[4].length() - 1) >= '0' && info[4].charAt(info[4].length() - 1) <= '9')
            ac = Double.parseDouble(info[4]);
        else ac = Double.parseDouble(info[4].substring(0, info[4].length() - 1));
        switch (info[4].charAt(info[4].length() - 1)) {
            case 'p':
                ac *= 1e-12;
                break;
            case 'n':
                ac *= 1e-9;
                break;
            case 'u':
                ac *= 1e-6;
                break;
            case 'm':
                ac *= 1e-3;
                break;
            case 'k':
                ac *= 1e3;
                break;
            case 'M':
                ac *= 1e6;
                break;
            case 'G':
                ac *= 1e9;
                break;
        }
        if (info[5].charAt(info[5].length() - 1) >= '0' && info[5].charAt(info[5].length() - 1) <= '9')
            frequncey = Double.parseDouble(info[5]);
        else frequncey = Double.parseDouble(info[5].substring(0, info[5].length() - 1));
        switch (info[5].charAt(info[5].length() - 1)) {
            case 'p':
                frequncey *= 1e-12;
                break;
            case 'n':
                frequncey *= 1e-9;
                break;
            case 'u':
                frequncey *= 1e-6;
                break;
            case 'm':
                frequncey *= 1e-3;
                break;
            case 'k':
                frequncey *= 1e3;
                break;
            case 'M':
                frequncey *= 1e6;
                break;
            case 'G':
                frequncey *= 1e9;
                break;
        }
        if (info[6].charAt(info[6].length() - 1) >= '0' && info[6].charAt(info[6].length() - 1) <= '9')
            phase = Double.parseDouble(info[6]);
        else phase = Double.parseDouble(info[6].substring(0, info[6].length() - 1));
        switch (info[6].charAt(info[6].length() - 1)) {
            case 'p':
                phase *= 1e-12;
                break;
            case 'n':
                phase *= 1e-9;
                break;
            case 'u':
                phase *= 1e-6;
                break;
            case 'm':
                phase *= 1e-3;
                break;
            case 'k':
                phase *= 1e3;
                break;
            case 'M':
                phase *= 1e6;
                break;
            case 'G':
                phase *= 1e9;
                break;
        }

    }
}
