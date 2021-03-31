// Team Cup O' Java
// Nadia Ahmed 101172713
// Esraa Alaa Aldeen 101151604
// Milestone 3

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

    private static JFrame initializeFrame(StoreManager sm) {
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
        JPanel mainPage = mainPage(sm);

        parentPanel.add(welcomePage);
        parentPanel.add(mainPage);

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

        Product cheesecake = new Product("Cheesecake", 01, 4.15);  // creating each new product
        Product sourdough = new Product("Sourdough Loaf", 02, 3.50);
        Product baguette = new Product("Baguette", 03, 3.90);
        Product banana = new Product("Banana Bread", 04, 2.75);
        Product croissant = new Product("Butter Croissant", 05, 3.25);
        Product focaccia = new Product("Focaccia", 06, 3.25);


        ArrayList<ProductStock> bakeryStock = new ArrayList<ProductStock>();

        bakeryStock.add(new ProductStock(cheesecake, 20));
        bakeryStock.add(new ProductStock(sourdough, 14));
        bakeryStock.add(new ProductStock(baguette, 8));
        bakeryStock.add(new ProductStock(banana, 22));
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

    private static JButton minusButton() {
        JButton minusButton = new JButton("-");
        minusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            }
        });
        return minusButton;
    }

    private static JPanel[] makeFoodPanels(StoreManager sm) {
        JPanel[] foodPanels = new JPanel[6];
        String[] foodPics = {"croissant.png", "baguette.png", "cheesecake.jpeg",
                                "banana.png", "sourdough.png", "focaccia.png"};
        String[] foodNames = {"Croissant", "Baguette", "Cheesecake", "Banana Bread", "Sourdough Loaf", "Focaccia"};

        float[][] foodInfo = sm.getInventoryInfo(); // prices and stock

        for (int i = 0; i < 6; i++) {
            int count = 0;
            JPanel bigPanel = new JPanel(new GridBagLayout());
            JPanel textPanel = new JPanel(new BorderLayout());
            JPanel buttonPanel = new JPanel(new FlowLayout());

            JButton plusButton = new JButton("+");
            JButton minusButton = new JButton("-");
            JLabel countLabel = new JLabel(String.valueOf(count));


            JLabel textLabel = new JLabel(String.format("<html><p style=\"font-size:12px\">$%.2f<br>Stock: %d<br><br></p></html>",
                                                                        foodInfo[i][1], (int)foodInfo[i][0]));
            textLabel.setBorder(new EmptyBorder(0, 10, 0, 0));


            JLabel productLabel = new JLabel(String.format("<html><p style=\"font-size:21px\"><br><br>%s</p></html>", foodNames[i]));
            productLabel.setBorder(new EmptyBorder(0, 10, 10, 0));

            JLabel imageLabel = new JLabel();
            imageLabel.setIcon(new ImageIcon(new javax.swing.ImageIcon(StoreView.class.getResource(foodPics[i])).
                    getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));


            GridBagConstraints c = new GridBagConstraints();    // bigPanel constraints
            buttonPanel.add(minusButton);
            buttonPanel.add(countLabel);
            buttonPanel.add(plusButton);

            textPanel.add(productLabel, BorderLayout.PAGE_START);
            textPanel.add(textLabel, BorderLayout.CENTER);
            textPanel.add(buttonPanel, BorderLayout.PAGE_END);

            c.gridx = 0;
            bigPanel.add(imageLabel, c);
            c.gridx = 1;
            c.ipadx = 50;
            bigPanel.add(textPanel, c);

            foodPanels[i] = bigPanel;
        }
        return foodPanels;
    }

    private static JPanel mainPage(StoreManager sm) {

        JButton backButton = new JButton("Back");
        JButton cartButton = new JButton("View Cart");

        JPanel panel = new JPanel(new GridBagLayout());

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                cardsLayout.first(parentPanel);
            }
        });

        GridBagConstraints c = new GridBagConstraints();    // welcomeLabel constraints

        JLabel titleLabel = new JLabel("<html><div face='Georgia' style='text-align: center;'>" +
                "<p style=\"font-size:35px\">The Virtual Bakery</p></div></html>");
        JPanel titlePanel = new JPanel(new BorderLayout(400, 0));
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        titlePanel.add(cartButton, BorderLayout.LINE_END);
        titlePanel.add(backButton, BorderLayout.LINE_START);

        JPanel[] foodPanels = makeFoodPanels(sm);

        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridx = 0;
        c.gridx = 0;
        c.gridwidth = 3;
        c.anchor = GridBagConstraints.CENTER;
        panel.add(titlePanel, c);

        c.gridwidth = 1;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.insets = new Insets(0, 20, 0, 0);
        c.gridx = 0;
        c.gridy = 1;
        panel.add(foodPanels[0], c);
        c.gridy = 2;
        panel.add(foodPanels[1], c);

        c.insets = new Insets(0, 0, 0, 0);
        c.gridx = 1;
        c.gridy = 1;
        panel.add(foodPanels[2], c);
        c.gridy = 2;
        panel.add(foodPanels[3], c);

        c.gridx = 2;
        c.gridy = 1;
        panel.add(foodPanels[4], c);
        c.gridy = 2;
        panel.add(foodPanels[5], c);

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

        StoreManager sm = new StoreManager(bakeryInventory());
        JFrame mainFrame = initializeFrame(sm);
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