// Team Cup O' Java
// Nadia Ahmed 101172713
// Esraa Alaa Aldeen 101151604
// Milestone 4

package myStore;

public class ProductStock {
    private Product product;
    private int quantity;


    /**
     * ProductStock wrapper constructor
     * @param product instance of Product class
     * @param quantity int quantity of products for this instance
     */
    public ProductStock(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    /**
     * quantity accessor
     * @return attribute int quantity of products
     */
    public int getQuantity() { return this.quantity; }

    /**
     * Product accessor
     * @return attribute instance of Product class
     */
    public Product getProduct() { return this.product; }

    /**
     * Product name accessor
     * @return String name of attribute product
     */
    public String getProductName() { return this.product.getName(); }

    /**
     * Product ID accessor
     * @return int ID of attribute product
     */
    public int getProductID() { return this.product.getId(); }

    /**
     * quantity modifier
     * @param quantity int quantity of products for this instance
     */
    public void setQuantity(int quantity) {this.quantity = quantity; }

    /**
     * Product price accessor
     * @return double price value of product
     */
    public double getPrice() {return this.product.getPrice(); }
}
