import javax.swing.*;

public class ErrorFinder {
    JFrame mainPage;
    InitialTextProccesor initialTextProccesor;

    ErrorFinder(JFrame mainPage, InitialTextProccesor initialTextProccesor) {
        this.mainPage = mainPage;
        this.initialTextProccesor = initialTextProccesor;
    }

    public boolean errorTwo(int time) {
        Element[] currentSourceElements = new Element[5];
        double sumOfValeus = 0;
        int currentSourceNumbers = 0, allElements = 0;
        for (int i = 0; i < initialTextProccesor.nodes.size(); i++) {
            sumOfValeus = 0;
            currentSourceNumbers = 0;
            allElements = 0;
            for (int j = 0; j < initialTextProccesor.elements.size(); j++) {
                if (initialTextProccesor.elements.get(j).node1.equals(initialTextProccesor.nodes.get(i).name)
                        || initialTextProccesor.elements.get(j).node1.equals(initialTextProccesor.nodes.get(i).name)) {
                    allElements++;
                    if (initialTextProccesor.elements.get(j).type.equals("cs")
                            || initialTextProccesor.elements.get(j).type.equals("ccc")
                            || initialTextProccesor.elements.get(j).type.equals("vcc")) {
                        currentSourceElements[currentSourceNumbers] = initialTextProccesor.elements.get(j);
                        currentSourceNumbers++;
                    }
                }
            }
            if (currentSourceNumbers == allElements) {
                for (int k = 0; k < currentSourceNumbers; k++) {
                    if (currentSourceElements[k].node1.equals(initialTextProccesor.nodes.get(i).name))
                        sumOfValeus -= (currentSourceElements[k].currentValues[time]);
                    else
                        sumOfValeus += (currentSourceElements[k].currentValues[time]);
                }
                if (sumOfValeus != 0)
                    return false;
            }
        }
        return true;
    }

    public void errorThree() {
        for (int j = 0; j < initialTextProccesor.elements.size(); j++) {
            if (initialTextProccesor.elements.get(j).type.equals("vs")
                    || initialTextProccesor.elements.get(j).type.equals("vcv")
                    || initialTextProccesor.elements.get(j).type.equals("ccv")) {
                for (int i = 0; i < initialTextProccesor.elements.size(); i++) {
                }
            }
        }
    }


    public boolean errorFour() {
        for(int i=0;i<initialTextProccesor.nodes.size();i++){
            if(initialTextProccesor.nodes.get(i).name.equals("0"))
                return false;
        }
        return true;

    }

    public void errorFive() {

    }

}
