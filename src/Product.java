// Nadia Ahmed 101172713 //
// Esraa Alaa Eldin //

public class Product {
    private String name;
    private String id;
    private double price;

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
