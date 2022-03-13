public class Ram {
     private String type;
     private int volume;
     private int weight;

    public Ram(String type, int volume, int weight) {
        this.type = type;
        this.volume = volume;
        this.weight = weight;
        Computer.totalWeightComputer = Computer.totalWeightComputer + weight;
    }

    public String toString() {
        return "\n  Тип: " + type +
               "\n  Обьём памяти: " + volume +
               "\n  Вес: " + weight;
    }
}
