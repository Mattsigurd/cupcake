package app.model;


public class Orderline {
    private int id;
    private int order_id;
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
    public Orderline(int id, int order_id, int quantity, Tops tops, Bottoms bottoms, int totalPrice) {
        this.id = id;
        this.order_id = order_id;
        this.quantity = quantity;
        this.tops = tops;
        this.bottoms = bottoms;
        this.totalPrice = totalPrice;
    }

    public Orderline(int quantity, Tops tops, Bottoms bottoms, int top_id, int bottom_id) {
        this.quantity = quantity;
        this.tops = tops;
        this.bottoms = bottoms;
        this.top_id = top_id;
        this.bottom_id = bottom_id;
    }

    public Orderline(int quantity) {
        this.quantity = quantity;

    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
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

    public int getOrder_id() {
        return order_id;
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

    public double getTotalPrice() {
        if (tops != null && bottoms != null) {
            return quantity * (tops.getTop_price() + bottoms.getBottom_price());
        } else {
            return 0; // Return 0 if tops or bottoms are not set
        }
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Orderline{" +
                "id=" + id +
                ", orderID=" + order_id +
                ", quantity=" + quantity +
                ", tops=" + tops +
                ", bottoms=" + bottoms +
                '}';
    }

}