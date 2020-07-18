public class IdealDiode extends Element {
    String input;
    public IdealDiode(String input){
        super.type="d";
        this.input=input;
        String [] info =input.split("\\s+");
        name = info[0];
        node1 = info[1];
        node2 = info[2];
    }
}
