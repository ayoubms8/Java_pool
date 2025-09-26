package Module01.ex00;

public class User {
    private int id;
    private String name;
    private int balance;

    public User(int id, String name, int balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "User{id=" + id + ", name='" + name + "', balance=" + balance + "}";
    }
}
