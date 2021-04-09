// Team Cup O' Java
// Nadia Ahmed 101172713
// Esraa Alaa Aldeen 101151604
// Milestone 5

package myStore;

import java.util.ArrayList;

public class Inventory extends ProductStockContainer {

    private String storeName; // Name of the store containing this inventory

    /**
     * Inventory constructor
     * @param productStocks ArrayList of wrapper class of Product instance and its int quantity
     * @param storeName String name of store containing this inventory
     */
    public Inventory(ArrayList<ProductStock> productStocks, String storeName) {
        this.productStocks = productStocks;
        this.storeName = storeName;
    }

    /**
     * productStocks accessor used by StoreManager class
     * @return ArrayList of wrapper class of Product instance and its int quantity
     */
    public ArrayList<ProductStock> getProductStocks() {
        return productStocks;
    }

    /**
     * storeName accessor
     * @return String name of store containing this inventory
     */
    public String getStoreName() {
        return storeName;
    }

    /**
     * Removes an amount of stock from inventory for a specific product
     * @param product instance of Product class
     * @param quantity int quantity to be removed
     * @return boolean true if removed, false otherwise
     */
    @Override
    public boolean removeProductQuantity(Product product, int quantity) {
        if (isAvailable(product.getId())){
            int i = find(product.getId());
            if (quantity <= 0) {
                System.out.println("Insufficient stock in inventory. No products were removed.");
                return false;                               // removes stock iff inventory is sufficient
            } else {
                int newQuantity = productStocks.get(i).getQuantity() - quantity;
                productStocks.get(i).setQuantity(newQuantity);
                System.out.println(quantity + " " + getProductInfo(product.getId()).getName() +"(s) were removed from inventory.");
                return true;
            }
        } else {
            System.out.println("This product is not in our inventory.");
            return false;   }
    }

    /**
     * Removes an amount of stock from inventory for a specific product
     * @param id int id for product
     * @param quantity int quantity to be removed
     * @return boolean true if removed, false otherwise
     */
    public boolean removeProductQuantity(int id, int quantity) {
        return removeProductQuantity(getProductInfo(id), quantity);
    }

    /**
     * Adds to an amount of stock for a specific product if in inventory,
     * otherwise adds a new product to the inventory and initializes quantity.
     * @param product instance of Product class
     * @param quantity int quantity to be added
     */
    @Override
    public void addProductQuantity(Product product, int quantity) {
        if (isAvailable(product.getId())) {                     // iff products exists in inventory, we add new quantity
            int i = find(product.getId());
            int newQuantity = productStocks.get(i).getQuantity() + quantity;
            productStocks.get(i).setQuantity(newQuantity);
        } else if (quantity >= 1                                     // New products must have quantity greater than 0,
                && !(product.getPrice() < 0)) {                     // and have a non-negative price,
            productStocks.add(new ProductStock(product, quantity));
        }
    }
}

