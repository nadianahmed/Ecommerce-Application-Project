// Team Cup O' Java
// Nadia Ahmed 101172713
// Esraa Alaa Aldeen 101151604

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class StoreView {
    private StoreManager storeManager;
    private int cartID;

    /**
     * StoreView Constructor
     * @param storeManager instance of StoreManager
     */
    public StoreView(StoreManager storeManager) {
        this.storeManager = storeManager;
        cartID = storeManager.assignNewCartID();
    }

    public static Inventory bakeryInventory() {

        Product multigrain = new Product("Multigrain Loaf", 01, 4.15);
        Product sourdough = new Product("Sourdough Loaf", 02, 3.50);
        Product baguette = new Product("Baguette", 03, 3.99);
        Product ciabatta = new Product("Ciabatta", 04, 2.75);
        Product focaccia = new Product("Focaccia", 05, 3.25);

        ProductStock multigrainPair = new ProductStock(multigrain, 20);
        ProductStock sourdoughPair = new ProductStock(sourdough, 14);
        ProductStock baguettePair = new ProductStock(baguette, 8);
        ProductStock ciabattaPair = new ProductStock(ciabatta, 22);
        ProductStock focacciaPair = new ProductStock(focaccia, 6);

        Inventory bakery = new Inventory(new ArrayList<ProductStock>(Arrays.asList(multigrainPair, sourdoughPair, baguettePair,
                ciabattaPair, focacciaPair)), "The Virtual Bakery");

        return bakery;
    }

    public boolean displayGUI() {
        Scanner sc = new Scanner(System.in);

        System.out.println("YOUR CART NUMBER IS " + this.cartID);
        System.out.println("Enter a command...\n(B)rowse (C)heckout (H)elp");

        String input = sc.nextLine();  // Read user input

        if (input.equalsIgnoreCase("B")) storeManager.printInventory();
        return true;
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
            System.out.print("CHOOSE YOUR STOREVIEW >>> ");
            int choice = sc.nextInt();
            if (choice < users.length && choice >= 0) {
                if (users[choice] != null) {
                    String chooseAnother = "";
                    while (!chooseAnother.equals("y") && !chooseAnother.equals("Y")) {
                        // this implementation of displayGUI waits for input and displays the page
// corresponding to the user's input. it does this once, and then returns
// true if the user entered 'checkout' or 'quit'.
                        if (users[choice].displayGUI()) {
                            users[choice] = null;
                            activeSV--;
                            break;
                        }
                        System.out.print("GO TO ANOTHER STOREVIEW? (y) >>> ");
                        chooseAnother = sc.next();
                    }
                } else {
                    System.out.println("MAIN > ERROR > BAD CHOICE\nTHAT STOREVIEW WAS DEACTIVATED");
                }
            } else {
                System.out.println(
                        String.format("MAIN > ERROR > BAD CHOICE\nPLEASE CHOOSE IN RANGE [%d, %d]",
                                0, users.length - 1)
                );
            }
        }
        System.out.println("ALL STOREVIEWS DEACTIVATED");
    }
}
