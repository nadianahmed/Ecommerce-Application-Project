// Team Cup O' Java
// Nadia Ahmed 101172713
// Esraa Alaa Aldeen 101151604

package store;

public class ProductStock {
    private Product product;
    private int quantity;


    /**
     * store.ProductStock wrapper constructor
     * @param product instance of store.Product class
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
    public int getQuantity() { return quantity; }

    /**
     * store.Product accessor
     * @return attribute instance of store.Product class
     */
    public Product getProduct() { return product; }

    /**
     * store.Product name accessor
     * @return String name of attribute product
     */
    public String getProductName() { return product.getName(); }

    /**
     * store.Product ID accessor
     * @return int ID of attribute product
     */
    public int getProductID() { return product.getId(); }

    /**
     * quantity modifier
     * @param quantity int quantity of products for this instance
     */
    public void setQuantity(int quantity) {this.quantity = quantity; }

    /**
     * store.Product price accessor
     * @return double price value of product
     */
    public double getPrice() {return product.getPrice(); }
}
