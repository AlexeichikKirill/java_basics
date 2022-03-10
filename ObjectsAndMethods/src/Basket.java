public class Basket {

    private static int count = 0;
    private static String items = "";
    private static int totalPrice = 0;
    private static int limit;
    private static double totalWeight = 0;
    private final static int defaultQuantity = 1;
    private final static int defaultZero = 0;
    private static int averagePriceAllBasket = 0;
    private static int averagePriceBasket = 0;
    private static int numberAllBaskets = 0;

    private static int totalPriseAllBasket = 0;
    private static int totalCountAllProduct = 0;

    public static int increasePrise(int price) {
        Basket.totalPriseAllBasket =  Basket.totalPriseAllBasket + price;
        return price;
    }

    public static int increaseTotalCount(int count) {
        Basket.totalCountAllProduct = Basket.totalCountAllProduct + count;
        return count;
    }
    
    public Basket() {
        increaseCount(1);
        items = "Список товаров:";
        this.limit = 1000000;
    }

    public Basket(int limit) {
        this();
        this.limit = limit;
    }

    public Basket(String items, int totalPrice) {
        this();
        this.items = this.items + items;
        this.totalPrice = totalPrice;
    }

    public static int averagePriceAllBasket() {
        averagePriceAllBasket = totalPriseAllBasket / totalCountAllProduct;
        return averagePriceAllBasket;
    }

    public static int averagePriceBasket() {
        averagePriceBasket = totalPriseAllBasket / numberAllBaskets;
        return averagePriceBasket;
    }

    public static int getCount() {
        return count;
    }

    public static void increaseCount(int count) {
        Basket.count = Basket.count + count;
    }

    public void add(String name, int price) { add(name, price, defaultQuantity ); }

    public void add(String name, int price, int count) { add(name, price, count, defaultZero); }

    public static void add(String name, int price, int count, double weight) {
        totalWeight = totalWeight + weight;
        totalCountAllProduct = totalCountAllProduct + count;
        totalPriseAllBasket = totalPriseAllBasket + price;
        boolean error = false;
        if (contains(name)) {
            error = true;
        }
        if (totalPrice + count * price >= limit) {
            error = true;
        }
        if (error) {
            System.out.println("Error occurred :(");
            return;
        }
        items = items + "\n" + name + " - " +
                count + " шт. - " + price + "руб.";
        totalPrice = totalPrice + count * price;
        numberAllBaskets = numberAllBaskets + 1;
    }

    public void clear() {
        items = "";
        totalPrice = 0;
        totalWeight = 0;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public double getTotalWeight(){
        return totalWeight;
    }


    public static boolean contains(String name) {
        return items.contains(name);
    }

    public void print(String title) {
        System.out.println(title);
        if (items.isEmpty()) {
            System.out.println("Корзина пуста");
        } else {
            System.out.println(items);
        }
    }
}
