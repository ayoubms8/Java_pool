package Module01.ex04;

import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

public class TransactionsService {
    private UsersList usersList;

    public TransactionsService() {
        this.usersList = new UsersArrayList();
    }

    public void addUser(User user) {
        usersList.addUser(user);
    }

    public int getUserBalance(int userId) throws UserNotFoundException {
        User user = usersList.getUserById(userId);
        return user.getBalance();
    }

    public void performTransfer(int senderId, int recipientId, int amount) 
            throws UserNotFoundException, IllegalTransactionException {
        User sender = usersList.getUserById(senderId);
        User recipient = usersList.getUserById(recipientId);

        if (sender.getBalance() < amount) {
            throw new IllegalTransactionException("Insufficient balance for transfer");
        }

        UUID transactionId = UUID.randomUUID();

        Transaction debitTransaction = new Transaction(sender, recipient, Transaction.Category.DEBITS, amount);
        Transaction creditTransaction = new Transaction(sender, recipient, Transaction.Category.CREDITS, amount);

        // Set same identifier for paired transactions
        debitTransaction = new Transaction(sender, recipient, Transaction.Category.DEBITS, amount) {
            @Override
            public UUID getIdentifier() {
                return transactionId;
            }
        };
        creditTransaction = new Transaction(sender, recipient, Transaction.Category.CREDITS, amount) {
            @Override
            public UUID getIdentifier() {
                return transactionId;
            }
        };

        sender.getTransactions().addTransaction(debitTransaction);
        recipient.getTransactions().addTransaction(creditTransaction);

        sender.setBalance(sender.getBalance() - amount);
        recipient.setBalance(recipient.getBalance() + amount);
    }

    public Transaction[] getUserTransactions(int userId) throws UserNotFoundException {
        User user = usersList.getUserById(userId);
        return user.getTransactions().toArray();
    }

    public void removeTransactionForUser(int userId, UUID transactionId) 
            throws UserNotFoundException, TransactionNotFoundException {
        User user = usersList.getUserById(userId);
        user.getTransactions().removeTransactionById(transactionId);
    }

    public Transaction[] checkTransactionValidity() {
        List<Transaction> unpairedTransactions = new ArrayList<>();
        
        for (int i = 0; i < usersList.getNumberOfUsers(); i++) {
            try {
                User user = usersList.getUserByIndex(i);
                Transaction[] userTransactions = user.getTransactions().toArray();
                
                for (Transaction transaction : userTransactions) {
                    UUID transactionId = transaction.getIdentifier();
                    boolean hasPair = false;
                    
                    // Check if there's a paired transaction
                    for (int j = 0; j < usersList.getNumberOfUsers() && !hasPair; j++) {
                        try {
                            User otherUser = usersList.getUserByIndex(j);
                            if (otherUser.getId() != user.getId()) {
                                Transaction[] otherTransactions = otherUser.getTransactions().toArray();
                                for (Transaction otherTransaction : otherTransactions) {
                                    if (otherTransaction.getIdentifier().equals(transactionId)) {
                                        hasPair = true;
                                        break;
                                    }
                                }
                            }
                        } catch (UserNotFoundException e) {
                            // Continue checking
                        }
                    }
                    
                    if (!hasPair) {
                        unpairedTransactions.add(transaction);
                    }
                }
            } catch (UserNotFoundException e) {
                // Continue checking
            }
        }
        
        return unpairedTransactions.toArray(new Transaction[0]);
    }
}
