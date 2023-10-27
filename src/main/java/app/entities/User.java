package app.entities;

public class User
{
    private int id;
    private String name;
    private String password;
    private String role;
    private double balance;

    public User(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }
   /* public User(int id, String email, String password, String role, double balance)
    {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role= role;
        this.balance= balance;
    }

    public User(int id, String email, String password, String role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
    }*/

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getPassword()
    {
        return password;
    }

    public String getRole() {
        return role;
    }
    public double getBalance() {
        return balance;
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + name + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", balance=" + balance +
                '}';
    }
}
