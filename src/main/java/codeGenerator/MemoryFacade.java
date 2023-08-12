package codeGenerator;

public class MemoryFacade {
    private final Memory memory;

    public MemoryFacade(Memory memory) {
        this.memory = memory;
    }

    public int getLastDataAddress() {
        return memory.getLastDataAddress();
    }

    public void incrementLastDateAddress() {
        memory.incrementLastDateAddress();
    }
}