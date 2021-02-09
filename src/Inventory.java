// Nadia Ahmed 101172713 //
// Esraa Alaa Aldeen 101151604//

import java.util.ArrayList;

public class Inventory {
    private ArrayList<Product> products;
    private ArrayList<Integer> quantities;


    public Inventory(ArrayList<Product> products, ArrayList<Integer> quantity) {
        this.products = products;
        this.quantities = quantity;
    }

    public Inventory() { this(new ArrayList<>(), new ArrayList<>()); }      // ASK is this initializing inventory?

    private void addProduct(Product product, int quantity) {
        products.add(product);
        if (quantity < 0) { quantity = 0; }
        quantities.add(quantity);
    }

    private boolean inInventory(String id) {
        for (Product product : products) {
            if (product.getId().equals(id)) { return true; }
        }
        return false;
    }

    private int findPlace(String id) {                                     //Assume product exists in inventory
        for (Product product : products) {
            if (product.getId().equals(id)) { return products.indexOf(product); }
        } return -1;
    }

    private int getQuantity(String id) {                                            // ASK do i need both?
        if (inInventory(id)) {
            return (quantities.get(findPlace(id)));
        } else {
            System.out.println("This product is not in our inventory.");            //temp for testing
            return 0;
        }
    }

    protected int getStock(String id) {
        return getQuantity(id);
    }

    protected void addStock(String id, int addStock) {                              // ASK how to add new product
        if (inInventory(id)){
            int i = findPlace(id);
            Integer newStock = quantities.get(i) + addStock;                        // more readable or redundant??
            System.out.println("Previous quantity: " + quantities.get(i));          // temp to test if values change
            quantities.add(i, newStock);
            System.out.println("Increased quantity: " + quantities.get(i));         // temp to test if values change
        } else {
            System.out.println("This product is not in our inventory.");            //temp for testing
        }
    }

    protected void removeStock(String id, int lessStock) {
        if (inInventory(id)){
            int i = findPlace(id);
            Integer newStock = quantities.get(i) - lessStock;                       // more readable or redundant??
            System.out.println("Previous quantity: " + quantities.get(i));          // temp to test if values change
            quantities.add(i, newStock);
            System.out.println("Decreased quantity: " + quantities.get(i));         // temp to test if values change
        } else {
            System.out.println("This product is not in our inventory.");            //temp for testing
        }
    }

    protected void InfoProduct(String id){                                          // What is this supposed to do?

    }


}

