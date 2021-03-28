// Team Cup O' Java
// Nadia Ahmed 101172713
// Esraa Alaa Aldeen 101151604
// Milestone 3

package store;

import java.util.ArrayList;

public class ShoppingCart {
    private ArrayList<ProductStock> cartItems;

    /**
     * ShoppingCart constructor
     * @param items ArrayList of wrapper class of Product instance and its int quantity
     */
    public ShoppingCart(ArrayList<ProductStock> items) {
        this.cartItems = items;
    }

    /**
     * ShoppingCart constructor -> empty shopping cart
     * */
    public ShoppingCart() { this(new ArrayList<>()); }

    /**
     * cartItems accessor
     * @return ArrayList cartItems attribute of this instance
     */
    public ArrayList<ProductStock> getCartItems() {return cartItems; }

    /**
     * Helper method, checks if product is in cart
     * @param id int id for product
     * @return true if the product is in shopping cart, false otherwise
     */
    public boolean inCart(int id) {
        for (ProductStock item : cartItems) {
            int productID = item.getProductID();
            if (productID == id) { return true; }
        } return false;
    }

    /**
     * Finds the index i of a product in cartItems ArrayList
     * @param id int id for product
     * @return int index i
     */
    private int findInCart(int id) {
        if (inCart(id)) {
            for (int i = 0; i < cartItems.size(); i++) {
                int productID = cartItems.get(i).getProductID();
                if (id == productID) { return i; }
            }
        }
        return -1;                                                      // Return -1 if id is invalid
    }
    /**
     * Gets quantity for a product, given its id
     * @param id int id for product
     * @return int quantity of products if in inventory, -1 otherwise
     */
    public int getQuantity(int id) {
        if (inCart(id)) {
            int i = this.findInCart(id);
            return (cartItems.get(i).getQuantity());
        } else {
            return -1;
        }
    }

    /**
     * Removes an amount of stock from shopping cart for a specific product
     * if appropriate amount of product is in cart and returns true, otherwise returns false
     * @param id int id for product
     * @param quantity int quantity to be removed
     * @return true if items were successfully removed, false otherwise
     */
    public boolean removeFromCart(int id, int quantity) {
        if (quantity <= 0) {     // user cannot remove a negative number
            System.out.println("Cannot remove negative or zero amount.");
            return false;
        }

        if (inCart(id)){
            int i = findInCart(id);
            int newQuantity = cartItems.get(i).getQuantity() - quantity;
            if (newQuantity < 0) {                     // removes stock iff new # items in inventory is non-negative
                System.out.println("Your cart does not contain this many "
                        + cartItems.get(i).getProductName()
                        + "(s). Nothing was removed.");
                return false;
            } else {
                cartItems.get(i).setQuantity(newQuantity);
                System.out.println(quantity + " "
                        + cartItems.get(i).getProductName()
                        + "(s) was removed from your cart.");
                return true;
            }
        } else {
            System.out.println("This product is not in your cart.");
            return false;
        }
    }


    /**
     * Adds to an amount of stock for a specific product if in shopping cart,
     * otherwise adds a new product to the shopping cart and initializes quantity.
     * @param product instance of Product class
     * @param quantity int quantity to be added
     */
    public void addToCart(Product product, int quantity) {
        if (inCart(product.getId())) {                     // iff products exists in cart, we add new quantity
            int i = findInCart(product.getId());
            int newQuantity = cartItems.get(i).getQuantity() + quantity;
            cartItems.get(i).setQuantity(newQuantity);
        } else if (quantity >= 1                                     // New products must have quantity greater than 0,
                && !(product.getPrice() < 0)) {                     // and have a non-negative price,
            cartItems.add(new ProductStock(product, quantity));
        }
    }


}
