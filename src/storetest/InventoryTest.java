package storetest;

import store.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

public class InventoryTest {
    private static Inventory inv;
    private static ArrayList<ProductStock> prodstocks;

    private static Product prod1;
    private static Product prod2;
    private static Product prod3;

    /**
     * Initializes test objects
     */
    @BeforeEach
    public void init(){

        prod1 = new Product("prod1", 01, 4.15);  // creating each new test product
        prod2 = new Product("prod2", 02, 3.50);
        prod3 = new Product("prod3", 03, 3.99);

        prodstocks = new ArrayList<ProductStock>();

        prodstocks.add(new ProductStock(prod1, 0));
        prodstocks.add(new ProductStock(prod2, 14));

        inv = new Inventory(prodstocks, "Test Store");

    }


    /**
     * Test for Inventory's productStocks accessor method
     */
    @Test
    void testGetProductStocks() {
        assertIterableEquals(prodstocks, inv.getProductStocks(), "Inventory's productStocks accessor " +
                "method returns incorrect ArrayList of ProductStocks (getProductStocks or constructor bug)");
    }

    /**
     * Test for Inventory's storeName accessor method
     */
    @Test
    void testStoreName() {
        assertEquals("Test Store", inv.getStoreName(), "Inventory's storeName accessor " +
                "method returns incorrect String (getProductStocks or constructor bug)");
    }

    /**
     * Test for Inventory's inInventory method
     */
    @Test
    void testInInventory() {

        assertEquals(true, inv.inInventory(1), "Inventory's inInventory method " +
                "does not successfully identify products already in inventory (inInventory bug)");

        assertEquals(true, inv.inInventory(2), "Inventory's inInventory method " +
                "does not successfully identify products in inventory if quantity is depleted to zero (inInventory bug)");

        assertEquals(false, inv.inInventory(3), "Inventory's inInventory method " +
                "incorrectly identifies products are in inventory when they are not (inInventory bug)");
    }
}
