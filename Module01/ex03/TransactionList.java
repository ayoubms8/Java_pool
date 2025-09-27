package Module01.ex03;

import java.util.UUID;

public interface TransactionList {
    public void addTransaction(Transaction transaction);
    public void removeTransaction(UUID id) throws TransactionNotFoundException;
    public Transaction[] toArray();
}