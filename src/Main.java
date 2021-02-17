import java.util.ArrayList;
// Team Cup O' Java
// Nadia Ahmed 101172713
// Esraa Alaa Aldeen 101151604

public class Main {
    public static void main(String[] args) {

        Product bag = new Product("bag", 1, 15.99);
        Product shoes = new Product("shoes", 2, 11.99);
        Product hat = new Product("hat", 3, 8.99);

        ProductStock shoePair = new ProductStock(shoes, 5);
        ProductStock bagPair = new ProductStock(bag, 4);

        Inventory store = new Inventory();
        StoreManager manager = new StoreManager(store);

        Inventory shop = new Inventory();
        StoreManager sm = new StoreManager(store);

        ShoppingCart trolley = new ShoppingCart(new ArrayList<>());

        sm.addCart(trolley, bag, 3);
        trolley.printCartItems();
        store.printInventory();

        store.addStock(bag, 7);
        store.addStock(shoes, 3);
        store.addStock(hat, 3);
        store.removeStock(3, 2);

        sm.addCart(trolley, bag, 3);
        trolley.printCartItems();
        store.printInventory();

        sm.removeCart(trolley, 1, 1);
        trolley.printCartItems();
        store.printInventory();

        double price = sm.processTransaction(trolley);
        System.out.println(price);
    }

}
