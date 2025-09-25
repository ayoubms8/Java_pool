import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = 0;
        
        while (true) {
            System.out.print("-> ");
            int number = scanner.nextInt();
            
            if (number == 42) {
                break;
            }
            
            int digitSum = sumOfDigits(number);
            
            if (isPrime(digitSum)) {
                count++;
            }
        }
        
        scanner.close();
        System.out.println("Count of coffee-request : " + count);
    }
    
    private static int sumOfDigits(int number) {
        int sum = 0;
        number = Math.abs(number);
        
        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }
        
        return sum;
    }
    
    private static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        
        if (number == 2) {
            return true;
        }
        
        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        
        return true;
    }
}
