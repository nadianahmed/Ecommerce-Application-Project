// Nadia Ahmed 101172713 //
// Esraa Alaa Aldeen 101151604//

public class Product {
    private final String name;
    private final String id;
    private final double price;

    public Product(String name, String id, double price) {
        this.name = name;
        this.id = id;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }
}
