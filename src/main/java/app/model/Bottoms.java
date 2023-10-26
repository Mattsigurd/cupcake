package app.model;

public class Bottoms {

    private int bottom_id;
    private String bottom;
    private double bottom_price;

    public Bottoms(int bottom_id, String bottom, double bottom_price) {
        this.bottom_id = bottom_id;
        this.bottom = bottom;
        this.bottom_price = bottom_price;
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

    public double getBottom_price() {
        return bottom_price;
    }

    @Override
    public String toString() {
        return "Bottoms{" +
                "bottom_id=" + bottom_id +
                ", bottom='" + bottom + '\'' +
                ", price=" + bottom_price +
                '}';
    }
}
