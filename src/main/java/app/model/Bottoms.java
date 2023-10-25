package app.model;

public class Bottoms {

    private int bottom_id;
    private String bottom;
    private double price;

    public Bottoms(int bottom_id, String bottom, double price) {
        this.bottom_id = bottom_id;
        this.bottom = bottom;
        this.price = price;
    }

    public Bottoms(String bottom) {
        this.bottom = bottom;
    }



    public int getBottom_id() {
        return bottom_id;
    }

    public String getBottom() {
        return bottom;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Bottoms{" +
                "bottom_id=" + bottom_id +
                ", bottom='" + bottom + '\'' +
                ", price=" + price +
                '}';
    }
}
