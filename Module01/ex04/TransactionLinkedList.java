package Module01.ex04;

import java.util.UUID;

public class TransactionLinkedList implements TransactionsList {

    private Transaction head;
    private Transaction tail;
    private int size;

    public TransactionLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public void addTransaction(Transaction transaction) {
        if (head == null) {
            head = transaction;
            tail = transaction;
            transaction.setNext(null);
            transaction.setPrev(null);
        } else {
            transaction.setNext(head);
            transaction.setPrev(null);
            head.setPrev(transaction);
            head = transaction;
        }
        size++;
    }

    @Override
    public void removeTransactionById(UUID id) throws TransactionNotFoundException {
        if (head == null) {
            throw new TransactionNotFoundException("Transaction with ID " + id + " not found");
        }

        Transaction current = head;
        while (current != null) {
            if (current.getIdentifier().equals(id)) {
                // Update previous node's next pointer
                if (current.getPrev() != null) {
                    current.getPrev().setNext(current.getNext());
                } else {
                    head = current.getNext();
                }
                
                // Update next node's previous pointer
                if (current.getNext() != null) {
                    current.getNext().setPrev(current.getPrev());
                } else {
                    tail = current.getPrev();
                }
                
                size--;
                return;
            }
            current = current.getNext();
        }

        throw new TransactionNotFoundException("Transaction with ID " + id + " not found");
    }

    @Override
    public Transaction[] toArray() {
        Transaction[] array = new Transaction[size];
        Transaction current = head;
        int index = 0;

        while (current != null) {
            array[index++] = current;
            current = current.getNext();
        }

        return array;
    }

    public int getSize() {
        return size;
    }
}
