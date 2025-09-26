package Module01.ex00;

public class Transaction {
    public enum Category {
        DEBITS, CREDITS
    }

    private static int nextId = 1;
    private int identifier;
    private User recipient;
    private User sender;
    private Category category;
    private int amount;

    public Transaction(User sender, User recipient, Category category, int amount) {
        if (category == Category.DEBITS && sender.getBalance() < amount) {
            throw new IllegalArgumentException("Sender does not have enough balance for debit");
        }
        if (category == Category.CREDITS && amount < 0) {
            throw new IllegalArgumentException("Credit amount must be positive");
        }
        this.identifier = nextId++;
        this.sender = sender;
        this.recipient = recipient;
        this.category = category;
        this.amount = amount;
    }

    public int getIdentifier() {
        return identifier;
    }

    public User getRecipient() {
        return recipient;
    }

    public User getSender() {
        return sender;
    }

    public Category getCategory() {
        return category;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Transaction{identifier=" + identifier + ", sender=" + sender.getName() + ", recipient=" + recipient.getName() + ", category=" + category + ", amount=" + amount + "}";
    }
}
