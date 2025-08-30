package org.ritwik.design_pattern.structural.facade;

public class FacadeDemo {
    public static void main(String[] args) {
        // Without facade - complex subsystem interactions
        System.out.println("=== Without Facade (Complex) ===");
        CPU cpu = new CPU();
        Memory memory = new Memory();
        HardDrive hardDrive = new HardDrive();

        cpu.freeze();
        memory.load(0, hardDrive.read(0, 1024));
        cpu.jump(0);
        cpu.execute();

        System.out.println("\n=== With Facade (Simple) ===");
        // With facade - simple interface
        ComputerFacade computer = new ComputerFacade();
        computer.start();
    }
}
