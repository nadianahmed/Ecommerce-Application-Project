import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Product bag = new Product("bag", 1, 15.99);
        Product shoes = new Product("shoes", 2, 11.99);
        Product hat = new Product("hat", 3, 8.99);

        ProductStock trial = new ProductStock(shoes, 7);

        Inventory store = new Inventory();
        StoreManager manager = new StoreManager(store);

        store.addStock(bag, 7);
        store.addStock(shoes, 3);
        store.addStock(hat, 3);
        store.removeStock(3, 2);


        int[][] shoppingCart = {{01, 5}, {02, 3}, {03, 1}};

        double moneySpent = manager.processTransaction(shoppingCart);
        System.out.println("I spent " + moneySpent + " dollars!");

    }

}
