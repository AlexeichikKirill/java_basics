public class Screen {
    private double diagonal;
    private ScreenMonitorType screenMonitorType;
    private int weight;

    public Screen(double diagonal, ScreenMonitorType screenMonitorType, int weight) {
        this.diagonal = diagonal;
        this.screenMonitorType = screenMonitorType;
        this.weight = weight;
        Computer.totalWeightComputer = Computer.totalWeightComputer + weight;
    }

    public String toString() {
        return "\n  Диагональ: " + diagonal +
               "\n  Тип: " + screenMonitorType +
               "\n  Вес: " + weight;
    }
}
