// Team Cup O' Java
// Nadia Ahmed 101172713
// Esraa Alaa Aldeen 101151604

public class StoreManager {
    private Inventory inventory;
    private int lastCartID = 0;

    /**
     * StoreManager constructor
     * @param inventory instance of Inventory class
     */
    public StoreManager(Inventory inventory) { this.inventory = inventory; }

    /**
     * Finds the quantity of a given product within the inventory of this store manager
     * @param product instance of Product class
     * @return int quantity of product
     */
    public int getProductQuantity(Product product) {
        return inventory.getStock(product.getId());
    }

    /**
     * Generates a unique cart ID incrementally
     * @return int cart ID
     */
    public int assignNewCartID(){
        lastCartID++;
        return lastCartID;
    }

    /**
     * Adds to a number of products for a specific product to the shopping cart
     * and removes that amount from the inventory if inventory is sufficient,
     * otherwise no change
     * @param shoppingCart instance of shoppingCart class
     * @param product instance of Product class
     * @param quantity int quantity to be added
     */
    public void addCart(ShoppingCart shoppingCart, Product product, int quantity) {
        if (inventory.inInventory(product.getId())) {
            if (inventory.getStock(product.getId()) >= quantity) {
                shoppingCart.addToCart(product, quantity);
                inventory.removeStock(product.getId(), quantity);
                System.out.println(quantity + " "
                        + product.getName()
                        + "(s) was added from your cart.");
            } else System.out.println("Cannot add to cart. Insufficient inventory.");
        } else System.out.println("Cannot add to cart. This product is not in our inventory.");
    }


    /**
     * Removes a number of products for a specific product from the shopping cart
     * and adds the removed quantity back to inventory if inventory is sufficient,
     * otherwise no change
     * @param shoppingCart instance of shoppingCart class
     * @param id instance of Product class
     * @param quantity int quantity to be added
     */
    public void removeCart(ShoppingCart shoppingCart, int id, int quantity) {
        if (shoppingCart.removeFromCart(id, quantity)) {
            inventory.addStock(inventory.getProductInfo(id), quantity);
        } else return;
    }


    /**
     *
     * @param shoppingCart
     * @return
     */
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
