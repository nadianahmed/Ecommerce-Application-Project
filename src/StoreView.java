// Team Cup O' Java
// Nadia Ahmed 101172713
// Esraa Alaa Aldeen 101151604

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *  This class represents a store view contains a StoreManager and a unique ShoppingCart
 */
public class StoreView {
    private StoreManager storeManager;
    private ShoppingCart shoppingCart;
    private int cartID;

    /**
     * StoreView Constructor
     * @param storeManager instance of StoreManager
     */
    public StoreView(StoreManager storeManager) {
        this.storeManager = storeManager;
        this.shoppingCart = new ShoppingCart();
        this.cartID = storeManager.assignNewCartID();
    }

    /**
     * CartID accessor
     * @return int unique cartID for this shopping cart
     */
    public int getCartID() {
        return cartID;
    }

    /**
     * Initializes new bakery inventory with the name, id, price, and quantity of the products
     * @return the available bakery inventory
     */
    public static Inventory bakeryInventory() {

        Product multigrain = new Product("Multigrain Loaf", 01, 4.15);
        Product sourdough = new Product("Sourdough Loaf", 02, 3.50);
        Product baguette = new Product("Baguette", 03, 3.99);
        Product ciabatta = new Product("Ciabatta", 04, 2.75);
        Product croissant = new Product("Croissant", 05, 3.25);
        Product focaccia = new Product("Focaccia", 06, 3.25);

        ProductStock multigrainPair = new ProductStock(multigrain, 20);
        ProductStock sourdoughPair = new ProductStock(sourdough, 14);
        ProductStock baguettePair = new ProductStock(baguette, 8);
        ProductStock ciabattaPair = new ProductStock(ciabatta, 22);
        ProductStock croissantPair = new ProductStock(croissant, 22);
        ProductStock focacciaPair = new ProductStock(focaccia, 6);

        Inventory bakery = new Inventory(new ArrayList<ProductStock>(Arrays.asList(multigrainPair, sourdoughPair,
                baguettePair, croissantPair, ciabattaPair, focacciaPair)), "The Virtual Bakery");

        return bakery;
    }

    /**
     * Displays a textual user interface and prompts user input
     * prints a command for the user to enter a certain subroutine then it prints all
     * the total and summary of the items in the cart
     * @return boolean
     */
    public boolean displayGUI() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter a command...\n(B)rowse\t(A)dd to cart\t(R)emove from cart\t" +
                "(V)iew cart\t(C)heckout\t(E)xit\nTo toggle storeview enter any other character");
        String input = sc.nextLine();  // Read user input

        if (input.equalsIgnoreCase("B")) {
            System.out.println("--------------------- BROWSE ---------------------");
            storeManager.printInventory();
            return false;
        }
        if (input.equalsIgnoreCase("A")) {
            System.out.println("------------------- ADD TO CART ------------------");
            storeManager.printInventory();

            System.out.println("Option #: ");
            int id = Integer.parseInt(sc.nextLine());

            System.out.println("Quantity: ");
            int quantity = Integer.parseInt(sc.nextLine());

            storeManager.addItem(shoppingCart, id, quantity);

            return false;
        }
        if (input.equalsIgnoreCase("R")) {
            System.out.println("---------------- REMOVE FROM CART ----------------");
            storeManager.printCartItems(this.shoppingCart);

            System.out.println("Option #: ");
            int id = Integer.parseInt(sc.nextLine());

            System.out.println("Quantity: ");
            int quantity = Integer.parseInt(sc.nextLine());

            storeManager.removeItem(shoppingCart, id, quantity);
            return false;
        }
        if (input.equalsIgnoreCase("V")) {
            System.out.println("---------------------- VIEW ----------------------");
            storeManager.printCartItems(shoppingCart);
            return false;
        }
        if (input.equalsIgnoreCase("C")) {
            System.out.println("-------------------- CHECKOUT --------------------");
            return storeManager.processTransaction(shoppingCart);
        }
        if (input.equalsIgnoreCase("Q")) {
            return false;
        }
        return false;
    }

    public static void main(String[] args) {

        StoreManager sm = new StoreManager(bakeryInventory());
        StoreView sv1 = new StoreView(sm);
        StoreView sv2 = new StoreView(sm);
        StoreView sv3 = new StoreView(sm);
        StoreView[] users = {sv1, sv2, sv3};
        int activeSV = users.length;

        Scanner sc = new Scanner(System.in);

        while (activeSV > 0) {
            System.out.printf("Choose a storeview in range [%d, %d]:\n",
                                                    0, users.length - 1);
            int choice = sc.nextInt();
            if (choice < users.length && choice >= 0) {
                if (users[choice] != null) {
                    String chooseAnother = "";

                    while (!chooseAnother.equalsIgnoreCase("Y")) {
                        System.out.println("Your cart number is " + users[choice].getCartID() +"\n");
                        // this implementation of displayGUI waits for input and displays the page
// corresponding to the user's input. it does this once, and then returns
// true if the user entered 'checkout' or 'quit'.
                        if (users[choice].displayGUI()) {   // if view deactivated
                            users[choice] = null;
                            activeSV--;
                            break;
                        }

                        System.out.print("\nGo to another storeview?\n" +
                                "To remain enter any other character\n(Y)es\t(Q)uit\n");
                        chooseAnother = sc.next();

                        if (chooseAnother.equalsIgnoreCase("Q")) { return; }
                    }
                } else {
                    System.out.println("\nERROR: This storeview was deactivated.");
                }
            } else {
                System.out.println(
                        String.format("ERROR: This storeview does not exist\nPlease choose in range [%d, %d]",
                                0, users.length - 1)
                );
            }
        }
        System.out.println("ALL STOREVIEWS DEACTIVATED");
    }
}
