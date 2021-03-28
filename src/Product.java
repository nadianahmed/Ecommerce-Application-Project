// Team Cup O' Java
// Nadia Ahmed 101172713
// Esraa Alaa Aldeen 101151604

public class Product {
    private final String name;
    private final int id;
    private final double price;

    /**
     * Product constructor
     * @param name String name for product
     * @param id int id for product
     * @param price double price value of product
     */
    public Product(String name, int id, double price) {
        this.name = name;
        this.id = id;
        this.price = price;
    }

    /**
     * Name accessor
     * @return String name of attribute product
     */
    public String getName() {
        return name;
    }

    /**
     * ID accessor
     * @return int id for instance of Product
     */
    public int getId() {
        return id;
    }

    /**
     * Price accessor
     * @return double price value of product
     */
    public double getPrice() {
        return price;
    }
}
