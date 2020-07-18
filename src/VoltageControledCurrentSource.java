public class VoltageControledCurrentSource extends Element{
    String input;
    public VoltageControledCurrentSource(String input)
    {
        super.type="vcc";
        this.input=input;
    }
}
