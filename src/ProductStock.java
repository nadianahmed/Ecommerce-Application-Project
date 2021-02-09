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

    protected int getQuantity() { return quantity; }

    protected Product getProduct() { return product; }

    protected String getProductID() { return product.getId(); }

    protected void setQuantity(int quantity) {this.quantity = quantity; }

}
