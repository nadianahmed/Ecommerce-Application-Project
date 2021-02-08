// Nadia Ahmed 101172713 //
// Esraa Alaa Aldeen 101151604//

import java.util.ArrayList;

public class Inventory {
    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<int> quantity = new ArrayList<int>();
    int Stock;


    protected int getStock(String id) {                 // the quantity of stock for a given Product ID//
        for (Product q : products) {
            if (q.getId() == id) {
                return (quantity.get(products.indexOf(q)));
            }
        }
        return 0;
    }

    protected void addStock(String id, int NewStock) {      //a specified amount of stock added for a given Product to the inventory//
        for (Product q : products) {
            if (q.getId() == id) {
                this.Stock = quantity.get(products.indexOf(q));
                quantity.set(products.indexOf(q), NewStock + Stock);

            }
        }

        // new Products can be added//
        // system.out.....//
        // products.add(new Product(name, id, price)){}
        // quantity.add(NewStock);

    }

    protected void deleteStock( int DeleteStock,String id) {

        for (Product q : products) {
            if (q.getId() == id) {
                this.Stock = quantity.get(products.indexOf(q));
                this.Stock -= DeleteStock;     //Remove a specified amount of stock for a given Product ID from the inventory//
            }
            else {
                this.Stock = 0;
            }
        }

    }

    protected Product InfoProduct(String id){
        for (Product q : products) {
            if (q.getId() == id) {
                return q;
            }


        }

        return null;
    }


}

