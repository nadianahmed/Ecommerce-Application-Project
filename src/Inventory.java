// Nadia Ahmed 101172713 //
// Esraa Alaa Aldeen 101151604//

import java.util.ArrayList;

public class Inventory {

    private ArrayList<ProductStock> productStocks;

    /** Initializing Inventory */
    protected Inventory(ArrayList<ProductStock> productStocks) {
        this.productStocks = productStocks;
    }

    protected Inventory() { this(new ArrayList<>()); }

    /** Private helper method to add new products */
    private void addProduct(Product product, int quantity) {
        if (quantity < 0) { quantity = 0; }
        productStocks.add(new ProductStock(product, quantity));
    }

    /** Checks if product is in inventory, given ID */
    private boolean inInventory(String id) {
        for (ProductStock productStock : productStocks) {
            String productID = productStock.getProductID();
            if (productID.equals(id)) { return true; }
        } return false;
    }

    /** Finds index i of product that is in inventory, given ID */
    private int findPlace(String id) {
        for (int i = 0; i < productStocks.size(); i++) {
            String productID = productStocks.get(i).getProductID();
            if (productID.equals(id)); { return i; }
        } return -1;
    }

    /** Private gets quantity for a product, given its ID */
    private int getQuantity(String id) {
        if (inInventory(id)) {
            int i = this.findPlace(id);
            return (productStocks.get(i).getQuantity());
        } else {
            System.out.println("This product is not in our inventory.");            //temp for testing
            return -1;
        }
    }

    /** Gets quantity for a product, given its ID */
    protected int getStock(String id) {
        return getQuantity(id);
    }

    /** Adds to stock if in inventory, adds new product if not. Given Product and quantity */
    protected void addStock(Product product, int quantity) {
        removeStock(product.getId(), -quantity);
    }

    /** Removes stock given ID and less quantity */
    protected void removeStock(String id, int quantity) {
        if (inInventory(id)){
            int i = findPlace(id);
            int newQuantity = productStocks.get(i).getQuantity() - quantity;                   // more readable or redundant??
            if (newQuantity < 0) {
                System.out.println("Insufficient inventory to complete.");
            } else {
                System.out.println("Previous quantity: " + productStocks.get(i).getQuantity());          // temp to test if values change
                productStocks.get(i).setQuantity(newQuantity);
                System.out.println("Current quantity: " + productStocks.get(i).getQuantity());         // temp to test if values change
            }
        } else {
            System.out.println("This product is not in our inventory.");            //temp for testing
        }
    }

    protected Product InfoProduct(String id){
        int i = findPlace(id);
        return productStocks.get(i).getProduct();
    }


}

