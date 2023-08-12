package codeGenerator;

public class ImmediateAddress implements TypeAddress {
    @Override
    public String toString(int num) {
        return "#" + num;
    }
}
