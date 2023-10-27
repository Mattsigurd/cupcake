package app.entities;

public class User
{
    private int id;
    private String email;
    private String password;
    private String role;
    private double balance;

    public User(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }
   public User(int id, String email, String password, String role, double balance)
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
    }

    public int getId()
    {
        return id;
    }

    public String getEmail()
    {
        return email;
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
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", balance=" + balance +
                '}';
    }
}
