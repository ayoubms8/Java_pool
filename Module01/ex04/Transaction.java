package Module01.ex04;

import java.util.UUID;

public class Transaction {
    public enum Category {
        DEBITS, CREDITS
    }

    private Transaction next;
    private Transaction prev;
    private UUID identifier;
    private User recipient;
    private User sender;
    private Category category;
    private int amount;

    public Transaction(User sender, User recipient, Category category, int amount) {
        this.identifier = UUID.randomUUID();
        if (category == Category.DEBITS && sender.getBalance() < amount) {
            throw new IllegalArgumentException("Sender does not have enough balance for debit");
        }
        if (category == Category.CREDITS && amount < 0) {
            throw new IllegalArgumentException("Credit amount must be positive");
        }
        this.sender = sender;
        this.recipient = recipient;
        this.category = category;
        this.amount = amount;
    }

    public Transaction getNext() {
        return this.next;
    }
    
    public Transaction getPrev() {
        return this.prev;
    }

    public void setNext(Transaction next) {
        this.next = next;
    }

    public void setPrev(Transaction prev) {
        this.prev = prev;
    }

    public UUID getIdentifier() {
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
