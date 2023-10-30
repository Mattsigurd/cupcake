package app.model;


import java.sql.Date;

public class Orders {


    private int id;
    private Date date;
    private boolean paid;
    private int user_id;


    public Orders(int id, Date date, boolean paid, int user_id) {
        this.id = id;
        this.date = date;
        this.paid = paid;
        this.user_id = user_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public java.sql.Date getDate() {
        return (java.sql.Date) date;
    }

    public boolean isPaid() {
        return paid;
    }

    public int getUser_id() {
        return user_id;
    }
}