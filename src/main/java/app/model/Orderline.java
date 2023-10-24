package app.model;

public class Orderline {
    private int id;
    private int orderID;
    private int quantity;
    Tops tops;
    Bottoms bottoms;
    private int totalPrice;

    public Orderline(int id, int orderID, int quantity, Tops tops, Bottoms bottoms, int totalPrice) {
        this.id = id;
        this.orderID = orderID;
        this.quantity = quantity;
        this.tops = tops;
        this.bottoms = bottoms;
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public int getOrderID() {
        return orderID;
    }

    public int getQuantity() {
        return quantity;
    }

    public Tops getTops() {
        return tops;
    }

    public Bottoms getBottoms() {
        return bottoms;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return "Orderline{" +
                "id=" + id +
                ", orderID=" + orderID +
                ", quantity=" + quantity +
                ", tops=" + tops +
                ", bottoms=" + bottoms +
                ", totalPrice=" + totalPrice +
                '}';
    }
}