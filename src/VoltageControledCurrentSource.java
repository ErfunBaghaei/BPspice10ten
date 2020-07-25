public class VoltageControledCurrentSource extends Element{
    String input;
    double gain=0;
    public VoltageControledCurrentSource(String input)
    {
        super.type="vcc";
        this.input=input;
    }
}
