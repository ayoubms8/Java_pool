package Module01.ex03;

import java.util.UUID;
public class Program {
    public static void main(String[] args) {
        System.out.println("=== Testing TransactionsList Implementation ===");
        
        // Create users
        User alice = new User("Alice", 1000);
        User bob = new User("Bob", 500);
        User charlie = new User("Charlie", 800);
        
        // Create transactions
        Transaction t1 = new Transaction(bob, alice, Transaction.Category.CREDITS , 200);
        Transaction t2 = new Transaction(charlie, alice, Transaction.Category.DEBITS, 150);
        Transaction t3 = new Transaction(alice, bob, Transaction.Category.DEBITS, 100);
        
        // Create TransactionsList
        TransactionsList transactionsList = new TransactionLinkedList();
        
        // Test adding transactions (O(1) operation)
        System.out.println("\n--- Adding Transactions ---");
        transactionsList.addTransaction(t1);
        transactionsList.addTransaction(t2);
        transactionsList.addTransaction(t3);
        System.out.println("Added 3 transactions successfully");
        
        // Test toArray
        System.out.println("\n--- Converting to Array ---");
        Transaction[] transactions = transactionsList.toArray();
        System.out.println("Transactions array length: " + transactions.length);
        for (int i = 0; i < transactions.length; i++) {
            System.out.println((i + 1) + ". " + transactions[i]);
        }
        
        // Test removing transaction by ID
        System.out.println("\n--- Removing Transaction by ID ---");
        UUID idToRemove = t2.getIdentifier();
        System.out.println("Removing transaction with ID: " + idToRemove);
        
        try {
            transactionsList.removeTransactionById(idToRemove);
            System.out.println("Transaction removed successfully");
        } catch (TransactionNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        // Verify removal
        System.out.println("\n--- After Removal ---");
        Transaction[] remainingTransactions = transactionsList.toArray();
        System.out.println("Remaining transactions: " + remainingTransactions.length);
        for (int i = 0; i < remainingTransactions.length; i++) {
            System.out.println((i + 1) + ". " + remainingTransactions[i]);
        }
        
        // Test removing non-existent transaction
        System.out.println("\n--- Testing TransactionNotFoundException ---");
        try {
            UUID random = UUID.randomUUID();
            transactionsList.removeTransactionById(random);
        } catch (TransactionNotFoundException e) {
            System.out.println("Exception caught (expected): " + e.getMessage());
        }
        
        // Test user's transactions field
        System.out.println("\n--- Testing User with Transactions ---");
        alice.getTransactions().addTransaction(new Transaction(bob, alice, Transaction.Category.CREDITS, 300));
        alice.getTransactions().addTransaction(new Transaction(charlie, alice, Transaction.Category.DEBITS, 250));
        
        Transaction[] aliceTransactions = alice.getTransactions().toArray();
        System.out.println("Alice's transactions: " + aliceTransactions.length);
        for (Transaction t : aliceTransactions) {
            System.out.println("- " + t);
        }
    }
}
