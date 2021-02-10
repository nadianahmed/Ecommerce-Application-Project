// Nadia Ahmed 101172713 //
// Esraa Alaa Aldeen 101151604//

import java.util.ArrayList;

public class Inventory {

    private ArrayList<ProductStock> productStocks;

    /** Initializing Inventory */
    public Inventory(ArrayList<ProductStock> productStocks) {
        this.productStocks = productStocks;
    }

    public Inventory() { this(new ArrayList<>()); }

    /** Private helper method to add new products */
    private void addProduct(Product product, int quantity) {
        if (quantity >= 1                                            // Invalid if quantity less than 1
                && !inInventory(product.getId())                     // Invalid if same ID product exists in inventory
                && !(product.getPrice() < 0)) {                      // Invalid if price is negative
            productStocks.add(new ProductStock(product, quantity));
        } else return;                                                // Do nothing if invalid products
    }

    /** Checks if product is in inventory, given ID */
    public boolean inInventory(int id) {
        for (ProductStock productStock : productStocks) {
            int productID = productStock.getProductID();
            if (productID == id) { return true; }
        } return false;
    }

    /** Finds index i of product that is in inventory, given ID */
    private int findPlace(int id) {
        if (inInventory(id)) {
            return productStocks.indexOf(id);
        } else { return -1; }
    }

    /** Private gets quantity for a product, given its ID */
    public int getStock(int id) {
        if (inInventory(id)) {
            int i = this.findPlace(id);
            return (productStocks.get(i).getQuantity());
        } else {
            System.out.println("This product is not in our inventory.");            //temp for testing
            return -1;
        }
    }

    /** Adds to stock if in inventory, adds new product if not. Given Product and quantity */
    protected void addStock(Product product, int quantity) {
        removeStock(product.getId(), -quantity);
    }

    /** Removes stock given ID and less quantity */
    public void removeStock(int id, int quantity) {
        if (inInventory(id)){
            int i = findPlace(id);
            int newQuantity = productStocks.get(i).getQuantity() - quantity;
            if (newQuantity < 0) {
                System.out.println("Insufficient inventory to complete.");
                return;
            } else {
                System.out.println("Previous quantity: " + productStocks.get(i).getQuantity());          // temp to test if values change
                productStocks.get(i).setQuantity(newQuantity);
                System.out.println("Current quantity: " + productStocks.get(i).getQuantity());         // temp to test if values change
            }
        } else {
            System.out.println("This product is not in our inventory.");            //temp for testing
        }
    }

    public Product getProductInfo(int id){
        int i = findPlace(id);
        return productStocks.get(i).getProduct();
    }


}

