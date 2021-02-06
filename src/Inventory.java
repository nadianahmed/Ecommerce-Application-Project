// Nadia Ahmed 101172713 //
// Esraa Alaa Eldin //

public class Inventory {
    Product product; // ?? array of products matched to their id
    String type;
    int stock;

    //hello i made change

    /** Change to protected ? only StoreManager should access */
    public int getStock() {
        return stock;
    }

    /** Change to protected ? only StoreManager should access */
    public void addStock(int stock) {
        this.stock += stock;
    }

    /** Change to protected ? only StoreManager should access */
    public void deleteStock(int stock) {
        if (stock <= this.stock) {
            this.stock -= stock;
        } else {
            this.stock = 0;
        }
    }

}
