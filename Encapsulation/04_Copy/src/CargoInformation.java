import org.w3c.dom.ls.LSOutput;

public class CargoInformation {
    private final int dimensions; // In centimeters
    private final int weight; // In grams
    private final String deliveryAddress;
    private final boolean canFlip;
    private final boolean fragile;


    public CargoInformation(int dimensions, int weight, String deliveryAddress, boolean canFlip, boolean fragile) {
        this.dimensions = dimensions;
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.canFlip = canFlip;
        this.fragile = fragile;
    }

    public CargoInformation setDeliveryAddress(String deliveryAddress){
        return new CargoInformation(dimensions, weight, deliveryAddress, canFlip, fragile);
    }

    public CargoInformation setDimensions(int dimensions){
        return new CargoInformation(dimensions, weight, deliveryAddress, canFlip, fragile);
    }

    public CargoInformation setWeight(int weight){
        return new CargoInformation(dimensions, weight, deliveryAddress, canFlip, fragile);
    }
}
