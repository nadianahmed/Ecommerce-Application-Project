// Team Cup O' Java
// Nadia Ahmed 101172713
// Esraa Alaa Aldeen 101151604

public class ProductStock {
    private Product product;
    private int quantity;

    /** ProductStock wrapper constructor */
    ProductStock(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    /** quantity accessor */
    public int getQuantity() { return quantity; }

    /** Product accessor */
    public Product getProduct() { return product; }

    /** Product ID accessor */
    public int getProductID() { return product.getId(); }

    /** quantity modifier */
    public void setQuantity(int quantity) {this.quantity = quantity; }

}
