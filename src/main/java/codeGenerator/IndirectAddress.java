package codeGenerator;

public class IndirectAddress implements TypeAddress {
    @Override
    public String toString(int num) {
        return "@" + num;
    }
}