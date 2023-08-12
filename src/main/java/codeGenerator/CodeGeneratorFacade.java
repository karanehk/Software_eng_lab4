package codeGenerator;

import scanner.token.Token;

public class CodeGeneratorFacade {
    private final CodeGenerator codeGenerator;

    public CodeGeneratorFacade() {
        this.codeGenerator = new CodeGenerator();
    }

    public void printMemory() {
        this.codeGenerator.printMemory();
    }

    public void semanticFunction(int func, Token next) {
        this.codeGenerator.semanticFunction(func, next);
    }
}