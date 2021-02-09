// Nadia Ahmed 101172713 //
// Esraa Alaa Aldeen 101151604//

import java.util.ArrayList;

public class Inventory {
    private ArrayList<Product> products;
    private ArrayList<Integer> quantity;


    public Inventory(ArrayList<Product> products, ArrayList<Integer> quantity) {
        this.products = products;
        this.quantity = quantity;
    }

    public Inventory() { this(new ArrayList<>(), new ArrayList<>()); }

    public int getStock(String id) {                 // the quantity of stock for a given Product ID//
        if (products.size() < 1) { System.out.println("No products in our inventory."); }
        for (Product product : products) {
            if (product.getId().equals(id)) {
                int i = products.indexOf(product);
                return (quantity.get(i));
            } else { System.out.println("This product is not in our inventory."); }
        }
        return 0;
    }

    protected void addStock(String id, int newStock) {      //a specified amount of stock added for a given Product to the inventory//
        Integer iNewStock = new Integer(newStock);

        if (products.size() < 1) { System.out.println("No products in our inventory."); }
        for (Product product : products) {
            int i = products.indexOf(product);
            if (product.getId().equals(id)) {
                System.out.println("Previous quantity: " + quantity.get(i)); // temp to test if values change
                quantity.add(i, iNewStock + quantity.get(i));
                System.out.println("Updated quantity: " + quantity.get(i));  // temp to test if values change
            }
        }
    }

//    protected void deleteStock( int DeleteStock,String id) {

  //      for (Product q : products) {
      //      if (q.getId() == id) {
    //            this.Stock = quantity.get(products.indexOf(q));
        //        this.Stock -= DeleteStock;     //Remove a specified amount of stock for a given Product ID from the inventory//
          //  }
            //else {
              //  this.Stock = 0;
      //      }
    //    }

  //  }

    protected Product InfoProduct(String id){
        for (Product q : products) {
            if (q.getId() == id) {
                return q;
            }


        }

        return null;
    }


}

