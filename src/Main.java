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

        Inventory store = new Inventory("test store");
        StoreManager manager = new StoreManager(store);

        Inventory shop = new Inventory("test store");
        StoreManager sm = new StoreManager(store);

        ShoppingCart cart = new ShoppingCart(new ArrayList<>());

        sm.addItem(cart, 1, 7);
        sm.printInventory();

        store.addStock(bag, 7);
        store.addStock(shoes, 3);
        store.addStock(hat, 3);
        store.removeStock(3, 2);

        sm.addItem(cart, 1, 7);
        sm.printInventory();

        sm.removeItem(cart, 1, 1);
        sm.printInventory();

        sm.processTransaction(cart);
    }

}
