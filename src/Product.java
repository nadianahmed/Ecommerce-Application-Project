// Nadia Ahmed 101172713 //
// Esraa Alaa Aldeen 101151604//

public class Product {
    private final String name;
    private final int id;
    private final double price;

    /** Initializing Inventory */
    public Product(String name, int id, double price) {
        this.name = name;
        this.id = id;
        this.price = price;
    }

    /** Name accessor */
    public String getName() {
        return name;
    }

    /** ID accessor */
    public int getId() {
        return id;
    }

    /** Price accessor */
    public double getPrice() {
        return price;
    }
}
