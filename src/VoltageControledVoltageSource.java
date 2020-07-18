public class VoltageControledVoltageSource extends Element{
    String input;
    public VoltageControledVoltageSource(String input){
        super.type="vcv";
        this.input=input;
    }
}
