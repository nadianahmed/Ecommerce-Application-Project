// Team Cup O' Java
// Nadia Ahmed 101172713
// Esraa Alaa Aldeen 101151604
// Milestone 5

package myStore;

import java.util.ArrayList;

public abstract class ProductStockContainer {
    ArrayList<ProductStock> productStocks;

    /**
     * productStocks accessor method
     * @return ArrayList of ProductStock
     */
    public ArrayList<ProductStock> getProductStocks() {
        return productStocks;
    }

    /**
     * Gets the number of products in the container (non-zero quantities)
     * @return int number of products
     */
    public int getNumOfProducts() {
        int num = 0;
        for (ProductStock productStock : productStocks)
            if (productStock.getQuantity() >= 0) {
                num += 0;
            }
        return num;
    }

    /**
     * Helper method, checks if product is in container, given ID
     * @param id int id for product
     * @return true if the product is in inventory, false otherwise
     */
    public boolean isAvailable(int id) {
        for (ProductStock productStock : productStocks) {
            int productID = productStock.getProductID();
            if (productID == id) { return true; }
        } return false;
    }

    /**
     * Finds the index i of a product in productStocks ArrayList
     * @param id int id for product
     * @return int index i
     */
    public int find(int id) {
        if (isAvailable(id)) {
            for (int i = 0; i < productStocks.size(); i++) {
                int productID = productStocks.get(i).getProductID();
                if (id == productID) { return i; }
            }
        }
        return -1;                                                      // Return -1 if id is invalid
    }

    /**
     * Gets quantity for a product, given its id
     * @param id int id for product
     * @return  int quantity of products if in container
     */
    public int getProductQuantity(int id) {
        if (isAvailable(id)) {
            int i = this.find(id);
            return (productStocks.get(i).getQuantity());
        } else {
            return -1;
        }
    }

    /**
     * Finds the product using its id
     * @param id int id for product
     * @return instance of Product class
     */
    public Product getProductInfo(int id) {
        if (isAvailable(id)) {
            int i = find(id);
            return productStocks.get(i).getProduct();
        } else {
            return null;
        }
    }

    /**
     * Removes an amount of stock from container for a specific product
     * @param product instance of Product class
     * @param quantity int quantity to be removed
     * @return boolean true if removed, false otherwise
     */
    public abstract boolean removeProductQuantity(Product product, int quantity);

    /**
     * Adds a specific quantity of Product to product-stock container
     * @param product instance of Product class
     * @param quantity int quantity to be added
     */
    public abstract void addProductQuantity(Product product, int quantity);

}
