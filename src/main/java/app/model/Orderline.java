package app.model;

public class Orderline {
    private int id;
    private int quantity;
    private int totalPrice;
    private int orderID;

    public Orderline(int id, int quantity, int totalPrice, int orderID){
        this.id = id;
        this.orderID = orderID;
        this.quantity = quantity;
        this.totalPrice = totalPrice;

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
