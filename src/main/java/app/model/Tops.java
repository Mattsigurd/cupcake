package app.model;

public class Tops {

    private int top_id;
    private String top;
    private double top_price;


    public Tops(int top_id, String top, double top_price) {
        this.top_id = top_id;
        this.top = top;
        this.top_price = top_price;
    }




    public int getTop_id() {
        return top_id;
    }

    public String getTop() {
        return top;
    }

    public double getTop_price() {
        return top_price;
    }

    @Override
    public String toString() {
        return "Tops{" +
                "top_id=" + top_id +
                ", name='" + top + '\'' +
                ", price=" + top_price +
                '}';
    }
}
