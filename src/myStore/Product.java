// Team Cup O' Java
// Nadia Ahmed 101172713
// Esraa Alaa Aldeen 101151604
// Milestone 5

package myStore;

public class Product {
    private  String name;
    private  int id;
    private  double price;
    private String pictureFile;

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
     * Product constructor
     * @param name String name for product
     * @param id int id for product
     * @param price double price value of product
     * @param pictureFile String pictureFile name
     */
    public Product(String name, int id, double price, String pictureFile) {
        this(name, id, price);
        this.pictureFile = pictureFile;
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

    /**
     * pictureFile accessor
     * @return String pictureFile name
     */
    public String getPictureFile() {
        return pictureFile;
    }
}
