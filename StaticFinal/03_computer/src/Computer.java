public class Computer {
    public final  Processor processor;
    public final Ram ram;
    public final Storage storage;
    public final Screen screen;
    public final Keyboard keyboard;
    public final String vendor;
    public final String name;
    public static int totalWeightComputer;

    public Computer(Processor processor, Ram ram, Storage storage, Screen screen, Keyboard keyboard, String vendor, String name) {
        this.processor = processor;
        this.ram = ram;
        this.storage = storage;
        this.screen = screen;
        this.keyboard = keyboard;
        this.vendor = vendor;
        this.name = name;
    }

    public Computer setProcessor (Processor processor) {
        return new Computer(processor, ram, storage, screen, keyboard, vendor, name);
    }
    public Computer setRam (Ram ram) {
        return new Computer(processor, ram, storage, screen, keyboard, vendor, name);
    }
    public Computer setStorage (Storage storage) {
        return new Computer(processor, ram, storage, screen, keyboard, vendor, name);
    }
    public Computer setScreen (Screen screen) {
        return new Computer(processor, ram, storage, screen, keyboard, vendor, name);
    }
    public Computer setKeyboard (Keyboard keyboard) {
        return new Computer(processor, ram, storage, screen, keyboard, vendor, name);
    }
    public Computer setVendor (String vendor) {
        return new Computer(processor, ram, storage, screen, keyboard, vendor, name);
    }
    public Computer setName (String name) {
        return new Computer(processor, ram, storage, screen, keyboard, vendor, name);
    }

    public Processor getProcessor() {
        return processor;
    }

    public Ram getRam() {
        return ram;
    }

    public Storage getStorage() {
        return storage;
    }

    public Screen getScreen() {
        return screen;
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public String toString() {
        return "Характеристики компьютера: " +
                "\nНазвание: " + name +
                "\nПроизводитель: " + vendor +
                "\nПроцессор: " + processor +
                "\nОперативная память: " + ram +
                "\nНакопитель информации: " + storage +
                "\nЭкран: " + screen +
                "\nКлавиатура: " + keyboard;
    }

}