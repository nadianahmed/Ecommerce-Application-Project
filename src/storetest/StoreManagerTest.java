// Team Cup O' Java
// Nadia Ahmed 101172713
// Esraa Alaa Aldeen 101151604

package storetest;

import store.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

/**
 * JUnit test harness class for StoreManager class
 */
public class StoreManagerTest {

    private static StoreManager sm;
    private static Inventory inv;
    private static ArrayList<ProductStock> prodstocks;

    private static ShoppingCart sc1;
    private static ShoppingCart sc2;

    private static Product prod1;
    private static Product prod2;
    private static Product prod3;
    private static Product prod4;

    /**
     * Initializes test objects before every test method
     */
    @BeforeEach
    public void init(){

        prod1 = new Product("prod1", 1, 4.15);  // creating each new test product
        prod2 = new Product("prod2", 2, 3.50);
        prod3 = new Product("prod3", 3, 3.99);
        prod4 = new Product("prod3", 4, 7.99);  // not added to inventory

        prodstocks = new ArrayList<>();

        prodstocks.add(new ProductStock(prod1, 20));
        prodstocks.add(new ProductStock(prod2, 14));
        prodstocks.add(new ProductStock(prod3, 10));


        inv = new Inventory(prodstocks, "Test Store");

        sm = new StoreManager(inv);

        sc1 = new ShoppingCart();

        sc2 = new ShoppingCart();
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
     * and removed from inventory accordingly for multiple items
     */
    @Test
    void testAddItem() {

        sm.addItem(sc1, 1, 1);

        assertEquals(1, sc1.getQuantity(1), "Incorrect amount added to cart (addItem method bug)");
        assertEquals(19, inv.getStock(1), "Incorrect removed from inventory when adding to cart (addItem method bug)");

        sm.addItem(sc1, 1, 2);
        assertEquals(3, sc1.getQuantity(1), "Incorrect amount in cart. " +
                "Amount is not added correctly to items already in inventory (addItem method bug)");
        assertEquals(17, inv.getStock(1), "Incorrect removed from inventory when adding to cart " +
                "a second time(addItem method bug)");

        sm.addItem(sc1, 2, 2);
        assertEquals(2, sc1.getQuantity(2), "Incorrect amount added to cart " +
                "when dealing with multiple products (addItem method bug)");
        assertEquals(12, inv.getStock(2), "Incorrect removed from inventory " +
                "when adding second item to cart (addItem method bug)");

        sm.addItem(sc2, 3, 3);
        assertEquals(3, sc2.getQuantity(3), "Incorrect amount added to cart " +
                "when dealing with multiple shopping carts (addItem method bug)");
        assertEquals(7, inv.getStock(3), "Incorrect removed from inventory " +
                "when dealing with multiple shopping carts (addItem method bug)");

        sm.addItem(sc1, 3, 3);
        assertEquals(3, sc1.getQuantity(3), "Incorrect amount added to cart " +
                "when dealing with multiple products (addItem method bug)");
        assertEquals(4, inv.getStock(3), "Incorrect removed from inventory " +
                "when adding third item to cart (addItem method bug)");
    }

    /**
     * Test for StoreManager's addItem method asserts items are NOT added to cart
     * or removed from inventory when zero or negative input is entered
     */
    @Test
    void testAddItemInvalid() {
        sm.addItem(sc1, 1, 3);

        sm.addItem(sc1, 1, 0);
        assertEquals(3, sc1.getCartItems().get(0).getQuantity(), "Incorrect amount in cart. " +
                "Zero amount should NOT be able to be added to cart (addItem method bug)");
        assertEquals(17, inv.getStock(1), "Incorrect amount in inventory " +
                "nothing should change when zero amount is added to cart (addItem method bug)");

        sm.addItem(sc1, 1, -1);
        assertEquals(3, sc1.getCartItems().get(0).getQuantity(), "Incorrect amount in cart. " +
                "Negative amount should NOT be able to be added to cart (addItem method bug)");
        assertEquals(17, inv.getStock(1), "Incorrect amount in inventory " +
                "when adding negative amount to cart (addItem method bug)");

        sm.addItem(sc1, 4, 3);
        assertEquals(-1, sc1.getQuantity(4), "Incorrect amount in cart. " +
                "products not in inventory should NOT be able to be added to cart (addItem method bug)");
        assertEquals(-1, inv.getStock(4), "Amount in inventory " +
                "should be -1 when product is not in inventory (addItem method bug)");
    }

    /**
     * Test for StoreManager's removeItem method asserts items are removed from cart correctly
     * and returned to inventory accordingly for multiple items
     */
    @Test
    void testRemoveItem() {
        sm.addItem(sc1, 1, 3);
        sm.addItem(sc1, 2, 2);
        sm.addItem(sc1, 3, 3);
        sm.addItem(sc2, 3, 3);

        sm.removeItem(sc1, 1, 1);
        assertEquals(2, sc1.getQuantity(1), "Incorrect amount removed from inventory " +
                "(removeItem method bug)");
        assertEquals(18, inv.getStock(1), "Incorrect returned to inventory " +
                "(removeItem method bug)");

        sm.removeItem(sc1, 1, 1);
        assertEquals(1, sc1.getQuantity(1), "Incorrect amount in cart " +
                "when second amount is removed (removeItem method bug)");
        assertEquals(19, inv.getStock(1), "Incorrect returned to inventory " +
                "when second amount is removed (removeItem method bug)");

        sm.removeItem(sc1, 2, 2);
        assertEquals(0, sc1.getQuantity(2), "Incorrect amount removed from cart " +
                "when dealing with multiple products (removeItem method bug)");
        assertEquals(14, inv.getStock(2), "Incorrect returned to inventory " +
                "when dealing with multiple products (removeItem method bug)");

        sm.removeItem(sc2, 3, 3);
        assertEquals(0, sc2.getQuantity(3), "Incorrect amount removed from cart " +
                "when dealing with multiple shopping carts (removeItem method bug)");
        assertEquals(7, inv.getStock(3), "Incorrect returned to inventory " +
                "when dealing with multiple shopping carts (removeItem method bug)");

        sm.removeItem(sc1, 3, 3);
        assertEquals(0, sc1.getQuantity(3), "Incorrect amount removed from cart " +
                "when dealing with multiple products (removeItem method bug)");
        assertEquals(10, inv.getStock(3), "Incorrect returned to inventory " +
                "when dealing with multiple products (removeItem method bug)");
    }

    /**
     * Test for StoreManager's removeItem method asserts items are NOT removed from cart
     * or returned to inventory when zero or negative input is entered
     */
    @Test
    void testRemoveItemInvalid() {
        sm.addItem(sc1, 1, 3);

        sm.removeItem(sc1, 1, 0);
        assertEquals(3, sc1.getQuantity(1), "Incorrect amount in cart. " +
                "Zero amount should NOT be able to be removed from inventory (removeItem method bug)");

        sm.removeItem(sc1, 3, -2);
        assertEquals(3, sc1.getQuantity(1), "Incorrect amount in cart. " +
                "Negative amount should NOT be able to be removed from inventory (removeItem method bug)");
    }

}
