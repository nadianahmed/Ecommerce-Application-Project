// Team Cup O' Java
// Nadia Ahmed 101172713
// Esraa Alaa Aldeen 101151604

import java.util.Scanner;

public class StoreManager {
    private Inventory inventory;
    private int lastCartID = 0;

    /**
     * StoreManager constructor
     * @param inventory instance of Inventory class
     */
    public StoreManager(Inventory inventory) { this.inventory = inventory; }

    /**
     * StoreManager constructor
     */
    public StoreManager() { this.inventory = null; }

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
    public void addItem(ShoppingCart shoppingCart, Product product, int quantity) {
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
    public void removeItem(ShoppingCart shoppingCart, int id, int quantity) {
        if (shoppingCart.removeFromCart(id, quantity)) {
            inventory.addStock(inventory.getProductInfo(id), quantity);
        } else return;
    }


    /**
     *
     * @param shoppingCart
     * @return
     */
    public void processTransaction(ShoppingCart shoppingCart) {
        shoppingCart.printCartItems();
        Scanner input = new Scanner(System.in);

        double checkoutTotal = 0;
        for (ProductStock item : shoppingCart.getCartItems()) {
            checkoutTotal += item.getQuantity() * item.getProduct().getPrice();
        }
        System.out.println("Your total is: $" + checkoutTotal);
        System.out.println("");
        System.out.println("Are you ready to checkout?");
        System.out.println("Enter 'Y' to checkout or 'N' to continue shopping. Enter 'quit' if you wish to exit.");
        System.out.println("");

        String ready = input.nextLine();  // Read user input

        if (ready.equalsIgnoreCase("Y")) System.out.println("Proceeding to checkout");
        else if (ready.equalsIgnoreCase("N")) System.out.println("Returning to store");
        else if (ready.equalsIgnoreCase("quit")) {
            for (ProductStock item : shoppingCart.getCartItems()) {
                removeItem(shoppingCart, item.getProductID(), item.getQuantity());
                System.out.println("Cart reset.");
            }
        } else System.out.println("Unrecognized input. Try again in a few seconds");
    }
}
