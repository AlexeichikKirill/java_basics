public class Storage {
    private StorageType type;
    private int memory;
    private int weight;

    public Storage(StorageType type, int memory, int weight) {
        this.type = type;
        this.memory = memory;
        this.weight = weight;
        Computer.totalWeightComputer = Computer.totalWeightComputer + weight;
    }

    public String toString() {
        return "\n  Тип: " + type +
               "\n  Обьём памяти: " + memory +
               "\n  Вес: " + weight;
    }
}
