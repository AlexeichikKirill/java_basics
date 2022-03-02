public class Dimensions {
    private final int width;
    private final int height;
    private final int length;

    public Dimensions(int width, int height, int length) {
        this.width = width;
        this.height = height;
        this.length = length;
    }

    public void calculateDimension() {
        int result = width * height * length;
        System.out.println(result);
    }
}
