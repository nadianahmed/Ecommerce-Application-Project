// Team Cup O' Java
// Nadia Ahmed 101172713
// Esraa Alaa Aldeen 101151604

public class StoreManager {
    private Inventory inventory;

    /** StoreManager constructor */
    public StoreManager(Inventory inventory) { this.inventory = inventory; }

    /** Return quantity of a given Product in inventory */
    public int getProductStock(Product product) {
        return inventory.getStock(product.getId());
    }

    /** Given a 2D array shoppingCart as [[productID1, quantity], [productID2, quantity]]
     *  of product IDs and quantities, returns the total price  */
    public double processTransaction(int[][] shoppingCart) {

        for (int[] value : shoppingCart) {                  // if not in inventory or insufficient quantity, return -1
            if (!inventory.inInventory(value[0])
                    || value[1] > inventory.getStock(value[0])) { return -1; }
        }
        double checkoutTotal = 0;
        for (int[] ints : shoppingCart) {
            checkoutTotal += ints[1] * inventory.getProductInfo(ints[0]).getPrice();    // calculate total at checkout
            inventory.removeStock(ints[0], ints[1]);
            }
        return checkoutTotal;
    }
}
