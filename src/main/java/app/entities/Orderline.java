package app.entities;

public class Orderline {
    private int id;
    private int quantity;
    private int totalPrice;

    public Orderline(){
        Top top;
        Bottom bottom;
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
