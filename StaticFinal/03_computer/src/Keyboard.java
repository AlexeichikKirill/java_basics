public class Keyboard {
    private String type;
    private boolean backLight;
    private int weight;

    public Keyboard(String type, boolean backLight, int weight) {
        this.type = type;
        this.backLight = backLight;
        this.weight = weight;
        Computer.totalWeightComputer = Computer.totalWeightComputer + weight;
    }

    public String toString() {
        return "\n  Тип: " + type +
               "\n  Подсветка: " + backLight +
               "\n  Вес: " + weight;
    }
}
