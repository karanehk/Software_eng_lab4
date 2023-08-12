package codeGenerator;

/**
 * Created by mohammad hosein on 6/28/2015.
 */

public class Address {
    public int num;
    public TypeAddress typeAddress;
    public varType varType;

    public Address(int num, varType varType, TypeAddress typeAddress) {
        this.num = num;
        this.typeAddress = typeAddress;
        this.varType = varType;
    }

    public Address(int num, varType varType) {
        this.num = num;
        this.typeAddress = new DirectAddress();
        this.varType = varType;
    }

    public String toString() {
        return typeAddress.toString(num);
    }
}
