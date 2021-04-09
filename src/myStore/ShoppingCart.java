// Team Cup O' Java
// Nadia Ahmed 101172713
// Esraa Alaa Aldeen 101151604
// Milestone 5

package myStore;

import java.util.ArrayList;

public class ShoppingCart extends ProductStockContainer {

    /**
     * ShoppingCart constructor
     * @param items ArrayList of wrapper class of Product instance and its int quantity
     */
    public ShoppingCart(ArrayList<ProductStock> items) {
        this.productStocks = items;
    }

    /**
     * ShoppingCart constructor -> empty shopping cart
     * */
    public ShoppingCart() { this(new ArrayList<>()); }

    /**
     * cartItems accessor
     * @return ArrayList cartItems attribute of this instance
     */
    public ArrayList<ProductStock> getProductStocks() {return this.productStocks; }

    /**
     * Removes an amount of stock from shopping cart for a specific product
     * if appropriate amount of product is in cart and returns true, otherwise returns false
     * @param product instance of Product class
     * @param quantity int quantity to be removed
     * @return true if items were successfully removed, false otherwise
     */
    @Override
    public boolean removeProductQuantity(Product product, int quantity) {
        if (quantity <= 0) {     // user cannot remove a negative number
            System.out.println("Cannot remove negative or zero amount.");
            return false;
        }

        if (isAvailable(product.getId())){
            int i = find(product.getId());
            int newQuantity = productStocks.get(i).getQuantity() - quantity;
            if (newQuantity < 0) {                     // removes stock iff new # items in inventory is non-negative
                System.out.println("Your cart does not contain this many "
                        + productStocks.get(i).getProductName()
                        + "(s). Nothing was removed.");
                return false;
            } else {
                productStocks.get(i).setQuantity(newQuantity);
                System.out.println(quantity + " "
                        + productStocks.get(i).getProductName()
                        + "(s) was removed from your cart.");
                return true;
            }
        } else {
            System.out.println("This product is not in your cart.");
            return false;
        }
    }

    /**
     * Removes an amount of stock from shopping cart for a specific product
     * if appropriate amount of product is in cart and returns true, otherwise returns false
     * @param id int id for product
     * @param quantity int quantity to be removed
     * @return true if items were successfully removed, false otherwise
     */
    public boolean removeProductQuantity(int id, int quantity) {
     return removeProductQuantity(getProductInfo(id), quantity);
    }


    /**
     * Adds to an amount of stock for a specific product if in shopping cart,
     * otherwise adds a new product to the shopping cart and initializes quantity.
     * @param product instance of Product class
     * @param quantity int quantity to be added
     */
    @Override
    public void addProductQuantity(Product product, int quantity) {
        if (isAvailable(product.getId())) {                     // iff products exists in cart, we add new quantity
            int i = find(product.getId());
            int newQuantity = productStocks.get(i).getQuantity() + quantity;
            productStocks.get(i).setQuantity(newQuantity);
        } else if (quantity >= 1                                     // New products must have quantity greater than 0,
                && !(product.getPrice() < 0)) {                     // and have a non-negative price,
            productStocks.add(new ProductStock(product, quantity));
        }
    }


}
