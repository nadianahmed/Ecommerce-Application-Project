// Team Cup O' Java
// Nadia Ahmed 101172713
// Esraa Alaa Aldeen 101151604

import java.util.ArrayList;

public class ShoppingCart {
    private ArrayList<ProductStock> cartItems;

    /** ShoppingCart Constructor */
    public ShoppingCart(ArrayList<ProductStock> items) {
        this.cartItems = items;
    }

    /** ShoppingCart Constructor */
    public ShoppingCart() { this(new ArrayList<>()); }

    /** cartItems accessor */
    public ArrayList<ProductStock> getCartItems() {return cartItems; }

    /** Checks if product is in cart, given ID of a product */
    private boolean inCart(int id) {
        for (ProductStock item : cartItems) {
            int productID = item.getProductID();
            if (productID == id) { return true; }
        } return false;
    }

    /** Returns the index i of in items ArrayList, given a Product's ID */
    private int findInCart(int id) {
        if (inCart(id)) {
            for (int i = 0; i < cartItems.size(); i++) {
                int productID = cartItems.get(i).getProductID();
                if (id == productID) { return i; }
            }
        }
        return -1;                                                      // Return -1 if id is invalid
    }

    /** Removes item(s) from cart given it's ID and quantity to be removed */
    public void removeFromCart(int id, int quantity) {
        if (inCart(id)){
            int i = findInCart(id);
            int newQuantity = cartItems.get(i).getQuantity() - quantity;
            if (newQuantity < 0) {                                          // removes stock iff inventory is sufficient
                System.out.println("Your cart does not contain this many "
                        + cartItems.get(i).getProductName()
                        + "(s). Nothing was removed.");
            } else { cartItems.get(i).setQuantity(newQuantity); }
        } else { System.out.println("This product is not in your cart.");}
    }

    /** Increases number of an item if given product is in the shopping cart,
     *  if it is not, adds a new product to the cart and initializes quantity.
     *  Given parameters are Product and quantity
     *  */
    public void addToCart(Product product, int quantity) {
        if (inCart(product.getId())) {                     // iff products exists in cart, we "remove" -ve quantity
            removeFromCart(product.getId(), -quantity);
        } else if (quantity >= 1                                     // New products must have quantity greater than 0,
                && !(product.getPrice() < 0)) {                     // and have a non-negative price,
            cartItems.add(new ProductStock(product, quantity));
        }
    }

    public void printCartItems() {
        System.out.println("----- SHOPPING CART -----");
        for (ProductStock item : cartItems) {
            System.out.println("Product: " + item.getProductName() + " --> Quantity: " + item.getQuantity());
        }
        System.out.println(" ");
    }

}
