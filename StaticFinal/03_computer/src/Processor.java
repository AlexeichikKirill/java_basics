public class Processor {
    public int frequency;
    public int numberCores;
    public String manufacturer;
    public int weight;

    public Processor(int frequency, int numberCores, String manufacturer, int weight) {
        this.frequency = frequency;
        this.numberCores = numberCores;
        this.manufacturer = manufacturer;
        this.weight = weight;
        Computer.totalWeightComputer = Computer.totalWeightComputer + weight;
    }

    public String toString() {
        return "\n  Частота: " + frequency +
        "\n  Количество ядер: " + numberCores +
        "\n  Производитель: " + manufacturer +
        "\n  Вес: " + weight;
    }
}
