// Team Cup O' Java
// Nadia Ahmed 101172713
// Esraa Alaa Aldeen 101151604

import java.util.ArrayList;

public class Inventory {

    private ArrayList<ProductStock> productStocks;

    /**
     * Inventory constructor
     * @param productStocks ArrayList of wrapper class of Product instance and its int quantity
     */
    public Inventory(ArrayList<ProductStock> productStocks) {
        this.productStocks = productStocks;
    }

    /**
     * Inventory constructor -> empty inventory
     */
    public Inventory() { this(new ArrayList<>()); }

    /**
     * Helper method, checks if product is in inventory, given ID
     * @param id int id for product
     * @return true if the product is in inventory, false otherwise
     */
    public boolean inInventory(int id) {
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
    private int findPlace(int id) {
        if (inInventory(id)) {
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
     * @return int quantity of products if in inventory, -1 otherwise
     */
    public int getStock(int id) {
        if (inInventory(id)) {
            int i = this.findPlace(id);
            return (productStocks.get(i).getQuantity());
        } else {
            return -1;
        }
    }

    /**
     * Removes an amount of stock from inventory for a specific product
     * @param id int id for product
     * @param quantity int quantity to be removed
     */
    public void removeStock(int id, int quantity) {
        if (inInventory(id)){
            int i = findPlace(id);
            int newQuantity = productStocks.get(i).getQuantity() - quantity;
            if (newQuantity < 0) {
                System.out.println("Insufficient stock in inventory. No products were removed.");
                return;                               // removes stock iff inventory is sufficient
            } else { productStocks.get(i).setQuantity(newQuantity); }
        } else { System.out.println("This product is not in our inventory.");}
    }

    /**
     * Adds to an amount of stock for a specific product if in inventory,
     * otherwise adds a new product to the inventory and initializes quantity.
     * @param product instance of Product class
     * @param quantity int quantity to be added
     */
    public void addStock(Product product, int quantity) {
        if (inInventory(product.getId())) {                     // iff products exists in inventory, we "remove" -ve quantity
            removeStock(product.getId(), -quantity);
        } else if (quantity >= 1                                     // New products must have quantity greater than 0,
                && !(product.getPrice() < 0)) {                     // and have a non-negative price,
            productStocks.add(new ProductStock(product, quantity));
        }
    }

    /**
     * Finds the product using its id
     * @param id int id for product
     * @return instance of Product class
     */
    public Product getProductInfo(int id){
        int i = findPlace(id);
        return productStocks.get(i).getProduct();
    }

    /**
     * Prints all products in inventory and their respective quantities
     */
    public void printInventory() {
        System.out.println("---------- INVENTORY ----------");
        for (ProductStock item : productStocks) {
            System.out.println("Product: " + item.getProductName() + " --> Quantity: " + item.getQuantity());
        }
        System.out.println("-------------------------------");
        System.out.println("");
    }


}

