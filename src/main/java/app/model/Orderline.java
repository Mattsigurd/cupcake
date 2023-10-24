package app.model;

public class Orderline {
    private int id;
    private int quantity;
    private int totalPrice;
    private int orderID;

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
    public int getOrderID(){
        return orderID;
    }

}
