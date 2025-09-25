import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String result = new String();
        int expectedWeek = 1;
        try {
            while (true) {
                System.out.print("-> ");
                String input = scanner.nextLine();

                if (input.equals("42")) {
                    break;
                }

                if (input.startsWith("Week ")) {
                    int weekNumber = Integer.parseInt(input.substring(5));

                    if (weekNumber != expectedWeek) {
                        System.out.println("IllegalArgument");
                        System.exit(-1);
                    }

                    String scores = scanner.nextLine();
                    String[] scoreStrings = scores.split(" ");

                    int minGrade = 9;
                    for (String scoreStr : scoreStrings) {
                        int score = Integer.parseInt(scoreStr);
                        if (score < minGrade) {
                            minGrade = score;
                        }
                    }

                    result += "Week " + weekNumber + " ";
                    for (int i = 0; i < minGrade; i++) {
                        result += "=";
                    }
                    result += ">\n";

                    expectedWeek++;
                } else {
                    System.out.println("IllegalArgument");
                    System.exit(-1);
                }
            }
        } catch (Exception e) {
            System.out.println("IllegalArgument");
            System.exit(-1);
        }

        System.out.print(result);
        scanner.close();
    }
}