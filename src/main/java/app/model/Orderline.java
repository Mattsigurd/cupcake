package app.model;

public class Orderline {
    private int id;
    private int quantity;
    private int totalPrice;

    public Orderline(){
        Tops top;
        Bottoms bottom;
    }
    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

}
