public class Program {
	public static void main (String args[]){
		int num = 12345;
		int tmp = num;
		int acc = 0;

		while (tmp > 0) {
			acc += tmp % 10;
			tmp /= 10;
		}
		System.out.println(acc);

	}
}
