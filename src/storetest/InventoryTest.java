// Team Cup O' Java
// Nadia Ahmed 101172713
// Esraa Alaa Aldeen 101151604

package storetest;

import store.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

/**
 * JUnit test harness class for Inventory class
 */
public class InventoryTest {
    private static Inventory inv;
    private static ArrayList<ProductStock> prodstocks;

    private static Product prod1;
    private static Product prod2;
    private static Product prod3;
    private static Product prod4;
    private static Product prod5;


    /**
     * Initializes test objects
     */
    @BeforeEach
    public void init(){

        prod1 = new Product("prod1", 1, 4.15);  // creating each new test product
        prod2 = new Product("prod2", 2, 3.50);
        prod3 = new Product("prod3", 3, 3.99);
        prod4 = new Product("prod4", 4, 1.99);
        prod5 = new Product("prod5", 3, -3.99);

        prodstocks = new ArrayList<ProductStock>();

        prodstocks.add(new ProductStock(prod1, 0));
        prodstocks.add(new ProductStock(prod2, 14));
        prodstocks.add(new ProductStock(prod3, 5));

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

        assertEquals(true, inv.inInventory(3), "Inventory's inInventory method " +
                "does not successfully identify products in inventory if quantity is depleted to zero (inInventory bug)");


        assertEquals(false, inv.inInventory(4), "Inventory's inInventory method " +
                "incorrectly identifies products are in inventory when they are not (inInventory bug)");

        assertEquals(false, inv.inInventory(5), "Inventory's inInventory method " +
                "incorrectly identifies products are in inventory when they do not exist (inInventory bug)");

        assertEquals(false, inv.inInventory(100), "Inventory's inInventory method " +
                "incorrectly identifies products are in inventory when they do not exist (inInventory bug)");

    }
    /**
     * Test for Inventory's getStock method
     */
    @Test
    void testGetStock() {
        assertEquals(0, inv.getStock(1), "The Inventory's getStock method "+
                "does not successfully return the quantity of products that already in inventory(getStock method bug)");

        assertEquals(14, inv.getStock(2),"The Inventory's getStock method "+
                "does not successfully return the quantity of products that already in inventory(getStock method bug)");

        assertEquals(5, inv.getStock(3),"The Inventory's getStock method "+
                "does not successfully return the quantity of products that already in inventory(getStock method bug)");

        assertEquals(-1 ,inv.getStock(5),"The Inventory's getStock method "+
                "returns incorrect quantity of products that are NOT in inventory(getStock method bug)");
    }

    /**
     * Test for Inventory's removeStock method
     */
    @Test
    void testRemoveStock() {

        inv.removeStock(1, 0);
        assertEquals(0, inv.getStock(1),"The Inventory's removeStock method " +
                " Incorrect product amount removed from inventory (removeStock method bug)");

        inv.removeStock(2,12);
        assertEquals(2, inv.getStock(2),"The Inventory's removeStock method "+
                " Incorrect product amount removed from inventory (removeStock method bug)");

        inv.removeStock(2,2);
        assertEquals(0, inv.getStock(2),"The Inventory's removeStock method " +
                "Incorrect amount removed from inventory when dealing with multiple products (removeStock method bug )");

        inv.removeStock(3,5);
        assertEquals(0, inv.getStock(3),"Incorrect amount removed from inventory "+
                "when dealing with multiple products (removeStock method bug )");
    }

    /**
     * Test for inventory's removeStock method asserts product stock are NOT removed from the inventory
     */
    @Test
    void testRemoveStockInvalid(){
        inv.removeStock(1, -1);
        assertEquals(0, inv.getStock(1), "Incorrect removed from inventory " +
                "when adding negative amount to inventory (addStock method bug)");

        inv.removeStock( 3,0);
        assertEquals(5, inv.getStock(3), "Incorrect quantity in Inventory. " +
                "Zero amount should NOT be able to be added to inventory (addStock method bug)");
    }


    /**
     * Test for Inventory's addStock method
     */
    @Test
    void testAddStock() {

        inv.addStock(prod1, 2);
        assertEquals(2, inv.getStock(1), "Incorrect product amount added to inventory " +
                " when adding new stock to the inventory (addStock method bug)");

        inv.addStock(prod1,3);
        assertEquals(5,inv.getStock(1), "Incorrect amount added to inventory " +
                "when adding second product to inventory (addStock method bug)");

        inv.addStock(prod2, 2);
        assertEquals(16, inv.getStock(2), "Incorrect amount added to inventory " +
                "when adding second product to inventory (addStock method bug)");

        inv.addStock(prod3, 5);
        assertEquals(10, inv.getStock(3),"Incorrect amount added to inventory " +
                "when adding second product to inventory (addStock method bug)");

        inv.addStock(prod4, 5);
        assertEquals(5, inv.getStock(4),"Incorrect amount added to inventory " +
                "when adding new product to inventory (addStock method bug)");
    }

    /**
     * Test for inventory's addStock method asserts product stock are NOT added to the inventory
     *
     */
    @Test
    void testAddStockInvalid(){
        inv.addStock( prod2, -1);
        assertEquals(0, inv.getStock(1), "Incorrect quantity in Inventory " +
                "when adding negative amount to inventory (addStock method bug)");

        inv.addStock( prod3,0);
        assertEquals(5, inv.getStock(3), "Incorrect quantity in Inventory. " +
                "Zero amount should NOT be able to be added to inventory (addStock method bug)");

        inv.addStock( prod5,0);
        assertEquals(-1, inv.getStock(5), "Incorrect quantity in Inventory. " +
                "Products with negative amount should NOT be able to be added to inventory (addStock method bug)");
    }

    /**
     * Test for Inventory's getProductInfo method
     */
    @Test
    void testGetProductInfo() {
        assertEquals(prod1, inv.getProductInfo(1), "The Inventory's getProductInfo method " +
                "does not successfully return the  Info of products that already in inventory (getProductInfo method bug)");

        assertEquals(prod2, inv.getProductInfo(2), "The Inventory's getProductInfo method " +
                "does not successfully return the  Info of products that already in inventory (getProductInfo method bug)");

        assertEquals(prod3, inv.getProductInfo(3), "The Inventory's getProductInfo method " +
                "does not successfully return the  Info of products that already in inventory (getProductInfo method bug)");

        assertEquals(null, inv.getProductInfo(4), "The Inventory's getProductInfo method " +
                "does not successfully return null for a product not in inventory (getProductInfo method bug)");

    }


}
