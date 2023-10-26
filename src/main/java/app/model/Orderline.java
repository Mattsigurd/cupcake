package app.model;


public class Orderline {
    private int id;
    private int orderID;
    private int quantity;
    Tops tops;
    Bottoms bottoms;
    private int totalPrice;

    private int top_id;
    private int bottom_id;



    public Orderline(int top_id, int bottom_id, int quantity) {
        this.top_id = top_id;
        this.bottom_id = bottom_id;
        this.quantity = quantity;
    }
    public Orderline(int id, int orderID, int quantity, Tops tops, Bottoms bottoms, int totalPrice) {
        this.id = id;
        this.orderID = orderID;
        this.quantity = quantity;
        this.tops = tops;
        this.bottoms = bottoms;
        this.totalPrice = totalPrice;
    }

    public Orderline(int quantity) {
        this.quantity = quantity;

    }

    public int getTop_id() {
        return top_id;
    }

    public int getBottom_id() {
        return bottom_id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTop_id(int top_id) {
        this.top_id = top_id;
    }

    public void setBottom_id(int bottom_id) {
        this.bottom_id = bottom_id;
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

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
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