package storetest;

import store.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

public class StoreManagerTest {

    private static StoreManager sm;
    private static Inventory inv;
    private static ArrayList<ProductStock> prodstocks;
    private static ShoppingCart sc;

    private static Product prod1;
    private static Product prod2;
    private static Product prod3;

    @BeforeAll
    public static void init(){

        prod1 = new Product("prod1", 01, 4.15);  // creating each new test product
        prod2 = new Product("prod2", 02, 3.50);
        prod3 = new Product("prod3", 03, 3.99);

        prodstocks = new ArrayList<ProductStock>();

        prodstocks.add(new ProductStock(prod1, 20));
        prodstocks.add(new ProductStock(prod2, 14));
        prodstocks.add(new ProductStock(prod3, 10));


        inv = new Inventory(prodstocks, "Test Store");

        sm = new StoreManager(inv);

        sc = new ShoppingCart();
    }

    /**
     * Test for StoreManager's assignNewCartID method asserts IDs are always unique
     */
    @Test
    void testAssignNewCartID() {
        int id1 = sm.assignNewCartID();
        int id2 = sm.assignNewCartID();
        int id3 = sm.assignNewCartID();
        int id4 = sm.assignNewCartID();
        int id5 = sm.assignNewCartID();

        assertFalse((id1 == id2), "assignNewCartID method does not return a unique ID each time");
        assertFalse((id2 == id3), "assignNewCartID method does not return a unique ID each time");
        assertFalse((id3 == id4), "assignNewCartID method does not return a unique ID each time");
        assertFalse((id4 == id5), "assignNewCartID method does not return a unique ID each time");
    }

    /**
     * Test for StoreManager's addItem method asserts items are added to cart correctly
     * and removed from inventory accordingly
     */
    @Test
    void testAddItem() {

        sm.addItem(sc, 1, 1);

        assertEquals(1, sc.getCartItems().get(0).getQuantity(), "Incorrect amount added to cart (addItem method bug)");
        assertEquals(19, inv.getStock(1), "Incorrect removed from inventory when adding to cart (addItem method bug)");

        sm.addItem(sc, 1, 2);
        assertEquals(3, sc.getCartItems().get(0).getQuantity(), "Incorrect amount in cart. " +
                "Amount is not added correctly to items already in inventory (addItem method bug)");
        assertEquals(17, inv.getStock(1), "Incorrect removed from inventory when adding to cart " +
                "a second time(addItem method bug)");

        sm.addItem(sc, 2, 2);
        assertEquals(2, sc.getCartItems().get(1).getQuantity(), "Incorrect amount added to cart " +
                "when dealing with multiple products (addItem method bug)");
        assertEquals(12, inv.getStock(2), "Incorrect removed from inventory " +
                "when adding second item to cart (addItem method bug)");

        sm.addItem(sc, 3, 3);
        assertEquals(3, sc.getCartItems().get(2).getQuantity(), "Incorrect amount added to cart " +
                "when dealing with multiple products (addItem method bug)");
        assertEquals(7, inv.getStock(3), "Incorrect removed from inventory " +
                "when adding third item to cart (addItem method bug)");
    }

    /**
     * Test for StoreManager's addItem method asserts items are NOT added to cart
     * or removed from inventory when zero or negative input is entered
     */
    @Test
    void testAddItemInvalid() {
        sm.addItem(sc, 1, 0);
        assertEquals(3, sc.getCartItems().get(0).getQuantity(), "Incorrect amount in cart. " +
                "Zero amount should NOT be able to be added to inventory (addItem method bug)");
        assertEquals(17, inv.getStock(1), "Incorrect removed from inventory " +
                " adding when zero amount to cart (addItem method bug)");

        sm.addItem(sc, 1, -1);
        assertEquals(3, sc.getCartItems().get(0).getQuantity(), "Incorrect amount in cart. " +
                "Negative amount should NOT be able to be added to inventory (addItem method bug)");
        assertEquals(17, inv.getStock(1), "Incorrect removed from inventory " +
                "when adding negative amount to cart (addItem method bug)");
    }

    /**
     * Test for StoreManager's removeItem method asserts items are removed correctly

    @Test
    void testRemoveItem() {
        sm.removeItem(sc, 1, 1);
        assertEquals(2, sc.getCartItems().get(0).getQuantity(), "Incorrect amount removed from inventory" +
                "(removeItem method bug)");

        sm.removeItem(sc, 1, 1);
        assertEquals(1, sc.getCartItems().get(0).getQuantity(), "Incorrect amount in cart " +
                "when second amount is removed (removeItem method bug)");

        sm.removeItem(sc, 1, 0);
        assertEquals(1, sc.getCartItems().get(0).getQuantity(), "Incorrect amount in cart. " +
                "Zero amount should NOT be able to be removed from inventory (removeItem method bug)");

        sm.removeItem(sc, 1, -2);
        assertEquals(1, sc.getCartItems().get(0).getQuantity(), "Incorrect amount in cart. " +
                "Negative amount should NOT be able to be removed from inventory (removeItem method bug)");

        sm.removeItem(sc, 2, 2);
        assertEquals(0, sc.getCartItems().get(1).getQuantity(), "Incorrect amount removed from cart " +
                "when dealing with multiple products (removeItem method bug)");

        sm.removeItem(sc, 3, 3);
        assertEquals(0, sc.getCartItems().get(2).getQuantity(), "Incorrect amount removed from cart " +
                "when dealing with multiple products (removeItem method bug)");


    }
     */

}
