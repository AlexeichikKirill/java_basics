public class CargoInformation {
    private final Dimensions dimensions; // In centimeters
    private final int weight; // In grams
    private final String deliveryAddress;
    private final boolean canFlip;
    private final boolean fragile;

    public CargoInformation(Dimensions dimensions, int weight, String deliveryAddress, boolean canFlip, boolean fragile) {
        this.dimensions = dimensions;
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.canFlip = canFlip;
        this.fragile = fragile;
    }

    public CargoInformation setDeliveryAddress(String deliveryAddress){
        return new CargoInformation(dimensions, weight, deliveryAddress, canFlip, fragile);
    }

    public CargoInformation setDimensions(Dimensions dimensions){
        return new CargoInformation(dimensions, weight, deliveryAddress, canFlip, fragile);
    }

    public CargoInformation setWeight(int weight){
        return new CargoInformation(dimensions, weight, deliveryAddress, canFlip, fragile);
    }
}
