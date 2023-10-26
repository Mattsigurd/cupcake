package app.persistence;

public class Quantity {

    public static int quantity(String quantity) {
        try {
            return Integer.parseInt(quantity);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}