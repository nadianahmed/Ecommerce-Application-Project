public class ProductStock {
    private Product product;
    private int quantity;

    /** Creates ProductStock wrapper given Product and quantity*/
    ProductStock(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    /** Creates ProductStock wrapper given Product, sets quantity to zero */
    ProductStock(Product product) {this(product, 0); }

    public int getQuantity() { return quantity; }

    public Product getProduct() { return product; }

    public int getProductID() { return product.getId(); }

    public void setQuantity(int quantity) {this.quantity = quantity; }

}
