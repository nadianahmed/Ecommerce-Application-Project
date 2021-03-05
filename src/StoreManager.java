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
     * @param productID int unique id for instance of Product class
     * @param quantity int quantity to be added
     */
    public void addItem(ShoppingCart shoppingCart, int productID, int quantity) {

        if (!inventory.inInventory(productID)) {
            System.out.println("Cannot add to cart. This item is not in our inventory.");
            return;
        }
        if (quantity < 0) {     // user cannot add a negative number
            System.out.println("Cannot remove negative amount.");
            return;
        }
        Product product = inventory.getProductInfo(productID);
        if (inventory.getStock(productID) >= quantity) {
            shoppingCart.addToCart(product, quantity);
            inventory.removeStock(productID, quantity);
            System.out.println(quantity + " "
                    + product.getName()
                    + "(s) was added to your cart.");
        } else System.out.println("Cannot add to cart. Insufficient inventory.");
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
     * Calculates total cost of items in shoppingCart and displays it. Awaits user input to checkout, continue shopping,
     * or quit. If a user decides to quit, all items are returned to inventory
     * @param shoppingCart instance of shoppingCart class
     * @return boolean true if transaction is completed successfully or if user decides to quit, either way indicates
     * user has left the store, otherwise returns false
     */
    public boolean processTransaction(ShoppingCart shoppingCart) {
        this.printCartItems(shoppingCart);
        Scanner sc = new Scanner(System.in);

        double checkoutTotal = 0;
        for (ProductStock item : shoppingCart.getCartItems()) {
            checkoutTotal += item.getQuantity() * item.getProduct().getPrice();
        }
        System.out.println("Your total is: $" + checkoutTotal + "\n");
        System.out.println("Are you ready to checkout?");
        System.out.println("(Y)es\t(N)o\t(Q)uit");


        String input = sc.nextLine();  // Read user input

        if (input.equalsIgnoreCase("Y")) {
            System.out.println("Proceeding to checkout");
            System.out.println("Order confirmed! Curbside pickup will be ready 15 within mins.");
            System.out.println("User has been disconnected. Have a nice day :)\n");
            return true;
        }
        else if (input.equalsIgnoreCase("N")) {
            System.out.println("Returning to store");
            return false;
        }
        else if (input.equalsIgnoreCase("Q")) {
            for (ProductStock item : shoppingCart.getCartItems()) {
                removeItem(shoppingCart, item.getProductID(), item.getQuantity());
                System.out.println("Cart reset.");
                System.out.println("User has been disconnected. Have a nice day :)\n");
                return true;
            }
        }
        System.out.println("!ERROR! Unrecognized input. Try again.");
        this.processTransaction(shoppingCart);
        return false;
    }


    /**
     * Prints all products in inventory and their respective quantities
     */
    public void printInventory() {
        System.out.println("--------------- " + inventory.getStoreName() + " ---------------");

        System.out.printf("(#) %-22s $Unit Price  \t Stock\n", "Product");
        for (ProductStock item : this.inventory.getProductStocks()) {
            if (item.getQuantity() > 0) {               // will only display items with available quantity in inventory
                System.out.printf("(%d) %-22s $%.2f\t\t\t %02d\n", item.getProductID(),
                        item.getProductName(), item.getPrice(), item.getQuantity());
            }
        }
        System.out.println("");
    }


    /**
     * Prints all products in the shopping cart and their respective quantities
     * @param shoppingCart instance of shoppingCart class
     */
    public void printCartItems(ShoppingCart shoppingCart) {
        System.out.println("------------------ Shopping Cart -----------------");
        System.out.printf("(%s) %-22s $%s %s\n", "#",
                "Product", "" + "Unit Price", "\t\tAmount");
        for (ProductStock item : shoppingCart.getCartItems()) {
            System.out.printf("(%d) %-22s $%.2f\t\t\t%02d\n", item.getProductID(),
                    item.getProductName(), item.getPrice(), item.getQuantity());
        }
        System.out.println("");
    }
}
