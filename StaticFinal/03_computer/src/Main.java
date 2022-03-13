public class Main {
    public static void main(String[] args) {
        Computer computer = new Computer(
                new Processor(4, 8,"Intel", 50),
                new Ram("DDR4", 16, 200),
                new Storage(StorageType.HDD, 256, 450),
                new Screen(25, ScreenMonitorType.VA, 1400),
                new Keyboard("Mechanical", true, 600),
                "HP", "My Computer");
        System.out.println(computer);
        System.out.println("Общий вес Компьютера: " + Computer.totalWeightComputer);
    }
}