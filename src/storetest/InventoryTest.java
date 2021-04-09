// Team Cup O' Java
// Nadia Ahmed 101172713
// Esraa Alaa Aldeen 101151604
// Milestone 3

package storetest;

import myStore.Inventory;
import myStore.Product;
import myStore.ProductStock;

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

        prodstocks = new ArrayList<>();

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
     * Test for Inventory's isAvailable method
     */
    @Test
    void testIsAvailable() {

        assertEquals(true, inv.isAvailable(1), "Inventory's isAvailable method " +
                "does not successfully identify products already in inventory (isAvailable bug)");

        assertEquals(true, inv.isAvailable(2), "Inventory's isAvailable method " +
                "does not successfully identify products in inventory if quantity is depleted to zero (isAvailable bug)");

        assertEquals(true, inv.isAvailable(3), "Inventory's isAvailable method " +
                "does not successfully identify products in inventory if quantity is depleted to zero (isAvailable bug)");


        assertEquals(false, inv.isAvailable(4), "Inventory's isAvailable method " +
                "incorrectly identifies products are in inventory when they are not (isAvailable bug)");

        assertEquals(false, inv.isAvailable(5), "Inventory's isAvailable method " +
                "incorrectly identifies products are in inventory when they do not exist (isAvailable bug)");

        assertEquals(false, inv.isAvailable(100), "Inventory's isAvailable method " +
                "incorrectly identifies products are in inventory when they do not exist (isAvailable bug)");

    }
    /**
     * Test for Inventory's getProductQuantity method
     */
    @Test
    void testGetProductQuantity() {
        assertEquals(0, inv.getProductQuantity(1), "The Inventory's getProductQuantity method "+
                "does not successfully return the quantity of products that already in isAvailable(getProductQuantity method bug)");

        assertEquals(14, inv.getProductQuantity(2),"The Inventory's getProductQuantity method "+
                "does not successfully return the quantity of products that already in isAvailable(getProductQuantity method bug)");

        assertEquals(5, inv.getProductQuantity(3),"The Inventory's getProductQuantity method "+
                "does not successfully return the quantity of products that already in inventory(getProductQuantity method bug)");

        assertEquals(-1 ,inv.getProductQuantity(5),"The Inventory's getProductQuantity method "+
                "returns incorrect quantity of products that are NOT in inventory(getProductQuantity method bug)");
    }

    /**
     * Test for Inventory's removeProductQuantity method
     */
    @Test
    void testRemoveProductQuantity() {

        inv.removeProductQuantity(1, 0);
        assertEquals(0, inv.getProductQuantity(1),"The Inventory's removeProductQuantity method " +
                " Incorrect product amount removed from inventory (removeProductQuantity method bug)");

        inv.removeProductQuantity(2,12);
        assertEquals(2, inv.getProductQuantity(2),"The Inventory's removeProductQuantity method "+
                " Incorrect product amount removed from inventory (removeProductQuantity method bug)");

        inv.removeProductQuantity(2,2);
        assertEquals(0, inv.getProductQuantity(2),"The Inventory's removeProductQuantity method " +
                "Incorrect amount removed from inventory when dealing with multiple products (removeProductQuantity method bug )");

        inv.removeProductQuantity(3,5);
        assertEquals(0, inv.getProductQuantity(3),"Incorrect amount removed from inventory "+
                "when dealing with multiple products (removeProductQuantity method bug )");
    }

    /**
     * Test for inventory's removeProductQuantity method asserts product stock are NOT removed from the inventory
     */
    @Test
    void testRemoveProductQuantityInvalid(){
        inv.removeProductQuantity(1, -1);
        assertEquals(0, inv.getProductQuantity(1), "Incorrect removed from inventory " +
                "when adding negative amount to inventory (addProductQuantity method bug)");

        inv.removeProductQuantity( 3,0);
        assertEquals(5, inv.getProductQuantity(3), "Incorrect quantity in Inventory. " +
                "Zero amount should NOT be able to be added to inventory (addProductQuantity method bug)");
    }


    /**
     * Test for Inventory's addProductQuantity method
     */
    @Test
    void testAddProductQuantity() {

        inv.addProductQuantity(prod1, 2);
        assertEquals(2, inv.getProductQuantity(1), "Incorrect product amount added to inventory " +
                " when adding new stock to the inventory (addProductQuantity method bug)");

        inv.addProductQuantity(prod1,3);
        assertEquals(5,inv.getProductQuantity(1), "Incorrect amount added to inventory " +
                "when adding second product to inventory (addProductQuantity method bug)");

        inv.addProductQuantity(prod2, 2);
        assertEquals(16, inv.getProductQuantity(2), "Incorrect amount added to inventory " +
                "when adding second product to inventory (addProductQuantity method bug)");

        inv.addProductQuantity(prod3, 5);
        assertEquals(10, inv.getProductQuantity(3),"Incorrect amount added to inventory " +
                "when adding second product to inventory (addProductQuantity method bug)");

        inv.addProductQuantity(prod4, 5);
        assertEquals(5, inv.getProductQuantity(4),"Incorrect amount added to inventory " +
                "when adding new product to inventory (addProductQuantity method bug)");
    }

    /**
     * Test for inventory's addProductQuantity method asserts product stock are NOT added to the inventory
     *
     */
    @Test
    void testAddProductQuantityInvalid(){
        inv.addProductQuantity( prod2, -1);
        assertEquals(0, inv.getProductQuantity(1), "Incorrect quantity in Inventory " +
                "when adding negative amount to inventory (addProductQuantity method bug)");

        inv.addProductQuantity( prod3,0);
        assertEquals(5, inv.getProductQuantity(3), "Incorrect quantity in Inventory. " +
                "Zero amount should NOT be able to be added to inventory (addProductQuantity method bug)");

        inv.addProductQuantity( prod5,0);
        assertEquals(-1, inv.getProductQuantity(5), "Incorrect quantity in Inventory. " +
                "Products with negative amount should NOT be able to be added to inventory (addProductQuantity method bug)");
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
