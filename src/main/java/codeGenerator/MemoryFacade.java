package codeGenerator;

public class MemoryFacade {
    private final Memory memory;

    public MemoryFacade(Memory memory) {
        this.memory = memory;
    }

    public int getDateAddress() {
        return memory.getDateAddress();
    }
}