public class Dimensions {
    private final int width;
    private final int height;
    private final int length;
    int result;

    public Dimensions(int width, int height, int length) {
        this.width = width;
        this.height = height;
        this.length = length;
    }

    public void calculateDimension() {
        result = width * height * length;
    }
}
