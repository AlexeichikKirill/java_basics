public class Main {

    public static void main(String[] args) {
        Basket basket = new Basket(10000);
        basket.add("Milk", 40,1,1000);
        basket.add("Bread",100,1, 500);
        basket.print("Ваша Корзина:");

    }
}
