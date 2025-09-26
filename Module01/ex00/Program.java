package Module01.ex00;

public class Program {
	public static void main(String[] args) {
		User alice = new User(1, "Alice", 1000);
		User bob = new User(2, "Bob", 500);

		Transaction t1 = new Transaction(alice, bob, Transaction.Category.DEBITS, 200);
		Transaction t2 = new Transaction(bob, alice, Transaction.Category.CREDITS, 150);

		System.out.println(alice);
		System.out.println(bob);
		System.out.println(t1);
		System.out.println(t2);
	}
}
