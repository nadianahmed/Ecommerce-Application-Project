// Team Cup O' Java
// Nadia Ahmed 101172713
// Esraa Alaa Aldeen 101151604

public class StoreManager {
    private Inventory inventory;
    private int lastCartID = 0;

    /** StoreManager constructor */
    public StoreManager(Inventory inventory) { this.inventory = inventory; }

    /** Return quantity of a given Product in inventory */
    public int getProductQuantity(Product product) {
        return inventory.getStock(product.getId());
    }

    /** Generates a unique ID incrementally */
    public int assignNewCartID(){
        lastCartID++;
        return lastCartID;
    }

    public void addCart(ShoppingCart shoppingCart, Product product, int quantity) {
        if (inventory.inInventory(product.getId())) {
            shoppingCart.addToCart(product, quantity);
            inventory.removeStock(product.getId(), quantity);
        } else { System.out.println("Insufficient inventory to add to your cart."); }
    }

    public void removeCart(ShoppingCart shoppingCart, int id, int quantity) {
        if (inventory.inInventory(id)) {
            shoppingCart.removeFromCart(id, quantity);
            inventory.addStock(inventory.getProductInfo(id), quantity);
        } else { System.out.println("Insufficient inventory to add to your cart."); }
    }


    public double processTransaction(ShoppingCart shoppingCart) {
        shoppingCart.printCartItems();
        //CHECKOUT
        double checkoutTotal = 0;
        for (ProductStock item : shoppingCart.getCartItems()) {
            checkoutTotal += item.getQuantity() * item.getProduct().getPrice();
        }
        return checkoutTotal;
    }

    /** Given a 2D array shoppingCart as [[productID1, quantity], [productID2, quantity]]
     *  of product IDs and quantities, returns the total price  */
    public double oldProcessTransaction(int[][] shoppingCart) {

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
