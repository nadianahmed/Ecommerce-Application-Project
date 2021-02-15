// Team Cup O' Java
// Nadia Ahmed 101172713
// Esraa Alaa Aldeen 101151604

import java.util.ArrayList;

public class ShoppingCart {
    private ArrayList<ProductStock> items;

    /** ShoppingCart Constructor */
    public ShoppingCart(ArrayList<ProductStock> items) {
        this.items = items;
    }

    /** ShoppingCart Constructor */
    public ShoppingCart() { this(new ArrayList<>()); }

    /** Checks if product is in inventory, given ID */
    private boolean inCart(int id) {
        for (ProductStock item : items) {
            int productID = item.getProductID();
            if (productID == id) { return true; }
        } return false;
    }

    /** Returns the index i of in items ArrayList, given a Product's ID */
    private int findPlace(int id) {
        if (inCart(id)) {
            for (int i = 0; i < items.size(); i++) {
                int productID = items.get(i).getProductID();
                if (id == productID) { return i; }
            }
        }
        return -1;                                                      // Return -1 if id is invalid
    }

    /** Removes item(s) from cart given it's ID and quantity to be removed */
    public void removeFromCart(int id, int quantity) {
        if (inCart(id)){
            int i = findPlace(id);
            int newQuantity = items.get(i).getQuantity() - quantity;
            if (newQuantity < 0) {
                System.out.println("Insufficient stock in inventory. No products were removed."); // removes stock iff inventory is sufficient
            } else { items.get(i).setQuantity(newQuantity); }
        } else { System.out.println("This product is not in our inventory.");}
    }

    /** Increases stock if given product is in inventory,
     *  if it is not, adds a new product to the inventory and initializes quantity.
     *  Given parameters are Product and quantity
     *  */
    public void addToCart(Product product, int quantity) {
        if (inCart(product.getId())) {                     // iff products exists in inventory, we "remove" -ve quantity
            removeFromCart(product.getId(), -quantity);
        } else if (quantity >= 1                                     // New products must have quantity greater than 0,
                && !(product.getPrice() < 0)) {                     // and have a non-negative price,
            items.add(new ProductStock(product, quantity));        // TO EDIT: new instance of ProductStock be made or no??
        }
    }


}
