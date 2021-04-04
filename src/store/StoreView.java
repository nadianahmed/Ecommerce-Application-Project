// Team Cup O' Java
// Nadia Ahmed 101172713
// Esraa Alaa Aldeen 101151604
// Milestone 4

package store;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
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
    private static JFrame frame;
    private static CardLayout cardsLayout = new CardLayout();
    private static JPanel parentPanel = new JPanel(cardsLayout);

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
     * Initializes the JFrame for the program's GUI
     * @param sm StoreManager class instance
     * @return JFrame for main program
     */
    private static JFrame initializeFrame(StoreManager sm) {
        JFrame frame = new JFrame();
        frame.setTitle("The Virtual Bakery");
        frame.setExtendedState(Frame.MAXIMIZED_BOTH); // make full screen
        frame.setResizable(false);
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

        Product croissant = new Product("Croissant", 01, 3.25, "croissant.png");  // creating each new product
        Product baguette = new Product("Baguette", 02, 3.90, "baguette.png");
        Product cheesecake = new Product("Cheesecake", 03, 4.15, "cheesecake.jpeg");
        Product banana = new Product("Banana Bread", 04, 2.75, "banana.png");
        Product sourdough = new Product("Sourdough Loaf", 05, 3.50, "sourdough.png");
        Product focaccia = new Product("Focaccia", 06, 3.25, "focaccia.png");

        ArrayList<ProductStock> bakeryStock = new ArrayList<ProductStock>();

        bakeryStock.add(new ProductStock(croissant, 22));
        bakeryStock.add(new ProductStock(baguette, 8));
        bakeryStock.add(new ProductStock(cheesecake, 20));
        bakeryStock.add(new ProductStock(banana, 22));
        bakeryStock.add(new ProductStock(sourdough, 14));
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

        JPanel welcomePanel = new JPanel(new GridBagLayout());

        JButton startButton = new JButton("Shop");
        startButton.setFont(new Font("", Font.PLAIN, 15));

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

        welcomePanel.add(welcomeLabel, c);
        welcomePanel.add(startButton, b);
        welcomePanel.setVisible(true);
        return welcomePanel;
    }

    /**
     * Private helper method that manages the JPanel layout with JButtons for a given Product
     * that let a user add/remove items from their cart
     * @param id int for desired product
     * @param sm StoreManager instance
     * @param textLabel JLabel containing product info
     * @return JPanel with JButtons
     */
    private JPanel makeButtonPanel(int id, StoreManager sm, JLabel textLabel) {
        Inventory inv = sm.getInventory();
        Product prod = inv.getProductInfo(id);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton plusButton = new JButton("+");
        JButton minusButton = new JButton("-");
        minusButton.setEnabled(false);

        JLabel countLabel = new JLabel("0"); // cart always starts off empty

        plusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                plusButton.setEnabled(true);
                storeManager.addItem(shoppingCart, id, 1);
                countLabel.setText(String.valueOf(Integer.parseInt(countLabel.getText()) + 1)); // re-displays counter
                textLabel.setText(String.format("<html><p style=\"font-size:12px\">$%.2f<br>Stock: %d<br><br></p></html>",
                                                 prod.getPrice(), inv.getStock(prod.getId()))); // re-displays stock

                minusButton.setEnabled(true);           // removing enabled (cart has > 0 items)

                if (inv.getStock(prod.getId()) <= 0) {
                    plusButton.setEnabled(false);       // adding disabled if inv insufficient
                }
            }
        });

        minusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                storeManager.removeItem(shoppingCart, id, 1);
                countLabel.setText(String.valueOf(Integer.parseInt(countLabel.getText()) - 1)); // re-displays counter
                textLabel.setText(String.format("<html><p style=\"font-size:12px\">$%.2f<br>Stock: %d<br><br></p></html>",
                                                 prod.getPrice(), inv.getStock(prod.getId()))); // re-displays stock

                plusButton.setEnabled(true);            // adding enabled (inv becomes sufficient)

                if (shoppingCart.getQuantity(id) < 1) {
                    minusButton.setEnabled(false);      // removing disabled if cart has > 0 items
                }
            }
        });

        buttonPanel.add(minusButton);
        buttonPanel.add(countLabel);
        buttonPanel.add(plusButton);
        return buttonPanel;
    }

    /**
     * Private helper method to make the JPanel layout for every product in a given StoreManager's Inventory
     * Creates JPanels with a title, an image, product info, and add/remove buttons
     * @param sm StoreManager instance
     * @return JPanel[] array of JPanels
     */
    private JPanel[] makeFoodPanels(StoreManager sm) {

        Inventory inv = sm.getInventory();
        JPanel[] foodPanels = new JPanel[inv.getProductStocks().size()];

        // set up a JPanel for each product
        for (int i = 0; i < inv.getProductStocks().size(); i++) {
            Product prod = inv.getProductStocks().get(i).getProduct();

            JPanel bigPanel = new JPanel(new GridBagLayout());
            JPanel textPanel = new JPanel(new BorderLayout());

            // product name (title)
            JLabel productLabel = new JLabel(String.format("<html><p style=\"font-size:21px\"><br><br>%s</p></html>",
                                                              prod.getName()));
            productLabel.setBorder(new EmptyBorder(0, 10, 10, 0));

            // product info (price and stock)
            JLabel textLabel = new JLabel(String.format("<html><p style=\"font-size:12px\">$%.2f<br>Stock: %d<br><br></p></html>",
                                                            prod.getPrice(), inv.getStock(prod.getId())));
            textLabel.setBorder(new EmptyBorder(0, 10, 0, 0));

            // product image
            JLabel imageLabel = new JLabel();
            imageLabel.setIcon(new ImageIcon(new javax.swing.ImageIcon(StoreView.class.getResource(prod.getPictureFile())).
                    getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));

            // add/remove buttons and counter
            JPanel buttonPanel = makeButtonPanel(prod.getId(), sm, textLabel);

            textPanel.add(productLabel, BorderLayout.PAGE_START);
            textPanel.add(textLabel, BorderLayout.CENTER);
            textPanel.add(buttonPanel, BorderLayout.PAGE_END);

            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;    // imageLabel constraints
            bigPanel.add(imageLabel, c);
            c.gridx = 1;    // textPanel constraints
            c.ipadx = 50;
            bigPanel.add(textPanel, c);

            foodPanels[i] = bigPanel;
        }
        return foodPanels;
    }

    /**
     * Private helper method to make the JPanel layout for the programs "main page"
     * (works best given sm's inventory contains 6 products or less)
     * @param sm StoreManager instance
     * @return JPanel for the program's "main page"
     */
    private JPanel mainPage(StoreManager sm) {

        JButton backButton = new JButton("Back");
        JButton cartButton = new JButton("View Cart");

        JPanel panel = new JPanel(new GridBagLayout());

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                cardsLayout.first(parentPanel); // go to previous page
            }
        });

        cartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Object[] options1 = {"Checkout", "Continue Shopping", "Quit"};
                int response1 = JOptionPane.showOptionDialog(parentPanel,
                                sm.printCartItems(shoppingCart),
                                "My Cart",
                                JOptionPane.PLAIN_MESSAGE,
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                options1,
                                options1[0]);

                if(response1 == 0) {    // if user chooses to checkout
                    double checkoutTotal = 0.0;
                    for (ProductStock item : shoppingCart.getCartItems()) {
                        checkoutTotal += item.getQuantity() * item.getProduct().getPrice(); // get cart total
                    }

                    Object[] options2 = {"Yes", "No", "Quit"};
                    int response2 = JOptionPane.showOptionDialog(parentPanel,
                            String.format("Your total is: $%.2f\nAre you ready to checkout?", checkoutTotal),
                            "Checkout",
                            JOptionPane.PLAIN_MESSAGE,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options2,
                            options2[0]);
                    if(response2 == 0) {    // user confirmed checking out, disconnecting user
                        sm.processTransaction(shoppingCart, "Y");
                        Object[] options = {"OK"};
                        int n = JOptionPane.showOptionDialog(frame,
                                "Order confirmed! Curbside pickup will be ready within 15 mins.\n" +
                                        "Have a nice day :)",
                                "See you soon!",
                                JOptionPane.PLAIN_MESSAGE,
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                options,
                                options[0]);
                        frame.setVisible(false);
                        frame.dispose();


                    } else if (response2 == 1){ // continue shopping
                        sm.processTransaction(shoppingCart, "N");
                    } else if (response2 == 2) { // quit
                        sm.processTransaction(shoppingCart, "Q");
                        frame.setVisible(false);
                        frame.dispose();
                    }
                } else if (response1 == 1){ // continue shopping
                    sm.processTransaction(shoppingCart, "N");
                } else if (response1 == 2){ // quit
                    sm.processTransaction(shoppingCart, "Q");
                    frame.setVisible(false);
                    frame.dispose();
                }
            }
        });

        GridBagConstraints c = new GridBagConstraints();

        JLabel titleLabel = new JLabel("<html><div face='Georgia' style='text-align: center;'>" +
                "<p style=\"font-size:35px\">The Virtual Bakery</p></div></html>");
        JPanel titlePanel = new JPanel(new BorderLayout(400, 0));
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        titlePanel.add(cartButton, BorderLayout.LINE_END);
        titlePanel.add(backButton, BorderLayout.LINE_START);

        JPanel[] foodPanels = makeFoodPanels(sm);

        c.weightx = 0.5;    // titlePanel constraints
        c.weighty = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.CENTER;
        panel.add(titlePanel, c);

        // adding foodPanels to main panel in two rows
        for(int i = 0; i < foodPanels.length; i++){
            if (i < 2) {
                c.insets = new Insets(0, 20, 0, 0);
            } else {
                c.insets = new Insets(0, 0, 0, 0);
            }
            c.gridwidth = 1;
            c.anchor = GridBagConstraints.FIRST_LINE_START;
            c.gridx = i/2;
            c.gridy = i%2 + 1;
            panel.add(foodPanels[i], c);
        }

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
            System.out.println(storeManager.printCartItems(shoppingCart));
            return false;
        }

        if (input.equalsIgnoreCase("C")) {
            System.out.println("-------------------- CHECKOUT --------------------");
            System.out.print(storeManager.printCartItems(shoppingCart));
            double checkoutTotal = 0;
            for (ProductStock item : shoppingCart.getCartItems()) {
                checkoutTotal += item.getQuantity() * item.getProduct().getPrice();
            }
            System.out.println(String.format("Your total is: $%.2f\n", checkoutTotal));
            System.out.println("Are you ready to checkout?");
            System.out.println("(Y)es\t(N)o\t(Q)uit");
            input = sc.nextLine();  // Read user input
            return storeManager.processTransaction(shoppingCart, input);
        }
        return false;
    }

    public static void main(String[] args) {

        StoreManager sm = new StoreManager(bakeryInventory());

        StoreView sv1 = new StoreView(sm);
        StoreView sv2 = new StoreView(sm);
        StoreView sv3 = new StoreView(sm);

        frame = initializeFrame(sm);

        JPanel welcomePage = welcomePage();
        JPanel mainPage = sv1.mainPage(sm);     // GUI only supports one StoreView

        parentPanel.add(welcomePage);
        parentPanel.add(mainPage);

        frame.add(parentPanel);
        frame.pack();
        frame.setVisible(true); // displays JFrame

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