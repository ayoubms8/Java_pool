package Module01.ex04;

import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        TransactionsService service = new TransactionsService();

        // Create users
        User alice = new User("Alice", 1000);
        User bob = new User("Bob", 500);
        User charlie = new User("Charlie", 200);

        // Add users to service
        service.addUser(alice);
        service.addUser(bob);
        service.addUser(charlie);

        System.out.println("=== Initial State ===");
        System.out.println("Alice balance: " + service.getUserBalance(alice.getId()));
        System.out.println("Bob balance: " + service.getUserBalance(bob.getId()));
        System.out.println("Charlie balance: " + service.getUserBalance(charlie.getId()));

        // Perform transfers
        System.out.println("\n=== Performing Transfers ===");
        try {
            service.performTransfer(alice.getId(), bob.getId(), 200);
            System.out.println("Transfer: Alice -> Bob (200)");
            
            service.performTransfer(bob.getId(), charlie.getId(), 150);
            System.out.println("Transfer: Bob -> Charlie (150)");
        } catch (IllegalTransactionException e) {
            System.out.println("Transfer failed: " + e.getMessage());
        }

        System.out.println("\n=== After Transfers ===");
        System.out.println("Alice balance: " + service.getUserBalance(alice.getId()));
        System.out.println("Bob balance: " + service.getUserBalance(bob.getId()));
        System.out.println("Charlie balance: " + service.getUserBalance(charlie.getId()));

        // Show user transactions
        System.out.println("\n=== Alice's Transactions ===");
        Transaction[] aliceTransactions = service.getUserTransactions(alice.getId());
        for (Transaction t : aliceTransactions) {
            System.out.println(t);
        }

        System.out.println("\n=== Bob's Transactions ===");
        Transaction[] bobTransactions = service.getUserTransactions(bob.getId());
        for (Transaction t : bobTransactions) {
            System.out.println(t);
        }

        // Try invalid transfer
        System.out.println("\n=== Attempting Invalid Transfer ===");
        try {
            service.performTransfer(charlie.getId(), alice.getId(), 1000);
        } catch (IllegalTransactionException e) {
            System.out.println("Transfer failed: " + e.getMessage());
        }

        // Check transaction validity
        System.out.println("\n=== Checking Transaction Validity ===");
        Transaction[] unpairedTransactions = service.checkTransactionValidity();
        if (unpairedTransactions.length == 0) {
            System.out.println("All transactions are properly paired");
        } else {
            System.out.println("Found unpaired transactions:");
            for (Transaction t : unpairedTransactions) {
                System.out.println(t);
            }
        }

        // Remove a transaction
        System.out.println("\n=== Removing Transaction ===");
        if (aliceTransactions.length > 0) {
            UUID transactionToRemove = aliceTransactions[0].getIdentifier();
            try {
                service.removeTransactionForUser(alice.getId(), transactionToRemove);
                System.out.println("Removed transaction: " + transactionToRemove);
                
                // Check validity again
                Transaction[] newUnpairedTransactions = service.checkTransactionValidity();
                System.out.println("Unpaired transactions after removal: " + newUnpairedTransactions.length);
            } catch (TransactionNotFoundException e) {
                System.out.println("Failed to remove transaction: " + e.getMessage());
            }
        }
    }
}
