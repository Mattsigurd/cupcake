package app.entities;

public class Orders {

    private int id;
    private int date;
    private boolean paid;
    private int user_id;


    public Orders(int id, int date, boolean paid, int user_id) {
        this.id = id;
        this.date = date;
        this.paid = paid;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public int getDate() {
        return date;
    }

    public boolean isPaid() {
        return paid;
    }

    public int getUser_id() {
        return user_id;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", date=" + date +
                ", paid=" + paid +
                ", user_id=" + user_id +
                '}';
    }
}
