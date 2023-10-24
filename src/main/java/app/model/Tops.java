package app.model;

public class Tops {

    private int top_id;
    private String top;
    private double price;

    public Tops(int top_id, String top, double price) {
        this.top_id = top_id;
        this.top = top;
        this.price = price;
    }

    public int getTop_id() {
        return top_id;
    }

    public String getTop() {
        return top;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Tops{" +
                "top_id=" + top_id +
                ", name='" + top + '\'' +
                ", price=" + price +
                '}';
    }
}
