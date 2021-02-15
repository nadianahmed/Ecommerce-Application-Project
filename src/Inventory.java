// Team Cup O' Java
// Nadia Ahmed 101172713
// Esraa Alaa Aldeen 101151604

import java.util.ArrayList;

public class Inventory {

    private ArrayList<ProductStock> productStocks;

    /** Inventory Constructor */
    public Inventory(ArrayList<ProductStock> productStocks) {
        this.productStocks = productStocks;
    }

    /** Inventory Constructor */
    public Inventory() { this(new ArrayList<>()); }

    /** Checks if product is in inventory, given ID */
    public boolean inInventory(int id) {
        for (ProductStock productStock : productStocks) {
            int productID = productStock.getProductID();
            if (productID == id) { return true; }
        } return false;
    }

    /** Returns the index i of in productStocks ArrayList, given a Product's ID */
    private int findPlace(int id) {
        if (inInventory(id)) {
            for (int i = 0; i < productStocks.size(); i++) {
                int productID = productStocks.get(i).getProductID();
                if (id == productID) { return i; }
            }
        }
        return -1;                                                      // Return -1 if id is invalid
    }

    /** Private gets quantity for a product, given its ID */
    public int getStock(int id) {
        if (inInventory(id)) {
            int i = this.findPlace(id);
            return (productStocks.get(i).getQuantity());
        } else {
            return -1;
        }
    }

    /** Removes from stock given Product's ID and the quantity to be removed */
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

    /** Increases stock if given product is in inventory,
     *  if it is not, adds a new product to the inventory and initializes quantity.
     *  Given parameters are Product and quantity
     *  */
    public void addStock(Product product, int quantity) {
        if (inInventory(product.getId())) {                     // iff products exists in inventory, we "remove" -ve quantity
            removeStock(product.getId(), -quantity);
        } else if (quantity >= 1                                     // New products must have quantity greater than 0,
                && !(product.getPrice() < 0)) {                     // and have a non-negative price,
            productStocks.add(new ProductStock(product, quantity));
        }
    }

    /** Returns Product given ID */
    public Product getProductInfo(int id){
        int i = findPlace(id);
        return productStocks.get(i).getProduct();
    }


}

