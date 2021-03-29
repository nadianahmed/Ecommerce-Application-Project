// Team Cup O' Java
// Nadia Ahmed 101172713
// Esraa Alaa Aldeen 101151604
// Milestone 3

package store;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.awt.Font;

/**
 *  This class represents a store view contains a StoreManager and a unique ShoppingCart
 */
public class StoreView {
    private StoreManager storeManager;
    private ShoppingCart shoppingCart;
    private int cartID;
    private static CardLayout cardsLayout = new CardLayout();
    private static JPanel parentPanel = new JPanel(cardsLayout);
    private static JFrame mainFrame = initializeFrame();


    /**
     * StoreView Constructor
     * @param storeManager instance of StoreManager
     */
    public StoreView(StoreManager storeManager) {
        this.storeManager = storeManager;
        this.shoppingCart = new ShoppingCart();
        this.cartID = storeManager.assignNewCartID();
    }

    private static JFrame initializeFrame() {
        JFrame frame = new JFrame();
        frame.setTitle("The Virtual Bakery");
        frame.setExtendedState(Frame.MAXIMIZED_BOTH); // make full screen
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                if (JOptionPane.showConfirmDialog(frame, "Are you sure you want to quit?")
                        == JOptionPane.OK_OPTION) {
                    frame.setVisible(false);
                    frame.dispose();
                }
            }
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel welcomePage = welcomePage();
        JPanel mainPage = mainPage();

        parentPanel.add(welcomePage, "Welcome Page");
        parentPanel.add(mainPage, "MainPage");

        frame.add(parentPanel);
        frame.pack();
        frame.setVisible(true);

        return frame;
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

        Product multigrain = new Product("Multigrain Loaf", 01, 4.15);  // creating each new product
        Product sourdough = new Product("Sourdough Loaf", 02, 3.50);
        Product baguette = new Product("Baguette", 03, 3.99);
        Product ciabatta = new Product("Ciabatta", 04, 2.75);
        Product croissant = new Product("Croissant", 05, 3.25);
        Product focaccia = new Product("Focaccia", 06, 3.25);

        ArrayList<ProductStock> bakeryStock = new ArrayList<ProductStock>();

        bakeryStock.add(new ProductStock(multigrain, 20));
        bakeryStock.add(new ProductStock(sourdough, 14));
        bakeryStock.add(new ProductStock(baguette, 8));
        bakeryStock.add(new ProductStock(ciabatta, 22));
        bakeryStock.add(new ProductStock(croissant, 22));
        bakeryStock.add(new ProductStock(focaccia, 6));

        Inventory bakery = new Inventory(bakeryStock, "The Virtual Bakery");

        return bakery;
    }

    /**
     * Initializes JPanel for a welcome page
     */
    private static JPanel welcomePage() {

        JLabel welcomeLabel = new JLabel("<html><div face='Georgia' style='text-align: center;'><p style=\"font-size:15px\"> " +
                "WELCOME TO</p><br><p style=\"font-size:50px\">The Virtual Bakery</p></div></html>");

        GridBagConstraints c = new GridBagConstraints();    // welcomeLabel constraints
        c.anchor = GridBagConstraints.PAGE_START;
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 3;
        c.gridheight = 1;
        c.ipady = 400;

        JPanel panel = new JPanel(new GridBagLayout());

        JButton startButton = new JButton("Shop");
        startButton.setFont(new Font("Georgia", Font.PLAIN, 25));


        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                cardsLayout.next(parentPanel);
            }
        });

        GridBagConstraints b = new GridBagConstraints(); // startButton constraints
        b.anchor = GridBagConstraints.CENTER;
        b.gridx = 0;
        b.gridy = 0;
        b.weighty = 1;
        b.weightx = 1;
        b.gridwidth = 3;
        b.gridheight = 1;
        b.ipady = 20;
        b.ipadx = 30;

        panel.add(welcomeLabel, c);
        panel.add(startButton, b);

        panel.setVisible(true);

        return panel;
    }

    private static JPanel mainPage(){

        JButton cartButton = new JButton("My Cart");
        cartButton.setFont(new Font("Georgia", Font.PLAIN, 25));

        JPanel panel = new JPanel(new GridBagLayout());

        cartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                cardsLayout.first(parentPanel);
            }
        });

        GridBagConstraints c = new GridBagConstraints();    // welcomeLabel constraints


        JLabel titleLabel = new JLabel("<html><div face='Georgia' style='text-align: center;'><p style=\"font-size:50px\">" +
                "The Virtual Bakery</p></div></html>");


        JLabel top_left = new JLabel("Top Left");
        JLabel middle_left = new JLabel("Middle Left");
        JLabel bottom_left = new JLabel("Bottom Left");
        JLabel middle_right = new JLabel("Middle Right");
        JLabel bottom_right = new JLabel("Bottom Right");
        JLabel middle_centre= new JLabel("Middle Centre");
        JLabel bottom_centre= new JLabel("Bottom Centre");

        c.weightx = 0.5;
        c.weighty = 0.5;
        c.insets = new Insets(40, 40, 40, 40);

        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        panel.add(top_left, c);
        c.gridx = 1;
        c.anchor = GridBagConstraints.PAGE_START;
        panel.add(titleLabel, c);
        c.gridx = 2;
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        panel.add(cartButton, c);

        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 1;
        panel.add(middle_left, c);
        c.gridx = 1;
        panel.add(middle_centre, c);
        c.gridx = 2;
        panel.add(middle_right, c);

        c.gridx = 0;
        c.gridy = 2;
        panel.add(bottom_left, c);
        c.gridx = 1;
        panel.add(bottom_centre, c);
        c.gridx = 2;
        panel.add(bottom_right, c);





        return panel;

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
            try {
                storeManager.printInventory();
                System.out.println("Option #: ");
                int id = Integer.parseInt(sc.nextLine());

                System.out.println("Quantity: ");
                int quantity = Integer.parseInt(sc.nextLine());

                storeManager.addItem(shoppingCart, id, quantity);
                return false;

            } catch (NumberFormatException e) {
                System.out.println("Input Mismatch Exception ==> Numerical value expected");
            }
        }
        if (input.equalsIgnoreCase("R")) {
            System.out.println("---------------- REMOVE FROM CART ----------------");
            try {
                storeManager.printCartItems(this.shoppingCart);
                System.out.println("Option #: ");
                int id = Integer.parseInt(sc.nextLine());

                System.out.println("Quantity: ");
                int quantity = Integer.parseInt(sc.nextLine());

                storeManager.removeItem(shoppingCart, id, quantity);
                return false;
            }
            catch (NumberFormatException e) {
                System.out.println("Input Mismatch Exception ==> Numerical value expected");
            }
        }
        if (input.equalsIgnoreCase("V")) {
            System.out.println("---------------------- VIEW ----------------------");
            storeManager.printCartItems(shoppingCart);
            return false;
        }

        if (input.equalsIgnoreCase("C")) {
            System.out.println("-------------------- CHECKOUT --------------------");
            return storeManager.processTransaction(shoppingCart, sc);
        }
        if (input.equalsIgnoreCase("E")) {
            return false;
        }
        return false;
    }

    public static void main(String[] args) {

        mainFrame.setVisible(true);

        StoreManager sm = new StoreManager(bakeryInventory());
        StoreView sv1 = new StoreView(sm);
        StoreView sv2 = new StoreView(sm);
        StoreView sv3 = new StoreView(sm);
        StoreView[] users = {sv1, sv2, sv3};
        int activeSV = users.length;
        try {
            Scanner sc = new Scanner(System.in);
            while (activeSV > 0) {
                System.out.printf("Choose a storeview in range [%d, %d]:\n",
                        0, users.length - 1);
                int choice = sc.nextInt();
                if (choice < users.length && choice >= 0) {
                    if (users[choice] != null) {
                        String chooseAnother = "";

                        while (!chooseAnother.equalsIgnoreCase("Y")) {
                            System.out.println("Your cart number is " + users[choice].getCartID() + "\n");
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

                            if (chooseAnother.equalsIgnoreCase("Q")) {
                                return;
                            }

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
        catch (InputMismatchException e){
            System.out.println("Input Mismatch Exception ==> Numerical value expected");
            StoreView.main(null);
        }
    }
}