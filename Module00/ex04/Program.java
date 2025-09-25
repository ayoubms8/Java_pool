import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();

        int[] freq = new int[65536];
        char[] chars = input.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c != '\n') freq[c]++;
        }

        // Find top 10 most frequent characters
        char[] topChars = new char[10];
        int[] topFreqs = new int[10];
        for (int t = 0; t < 10; t++) {
            int bestFreq = 0;
            char bestChar = 0;
            for (int j = 0; j < 65536; j++) {
                if (freq[j] > bestFreq || (freq[j] == bestFreq && freq[j] != 0 && ((char)j < bestChar || bestChar == 0))) {
                    boolean already = false;
                    for (int k = 0; k < t; k++) {
                        if (topChars[k] == (char)j) { already = true; break; }
                    }
                    if (!already) {
                        bestFreq = freq[j];
                        bestChar = (char)j;
                    }
                }
            }
            if (bestFreq == 0) break;
            topChars[t] = bestChar;
            topFreqs[t] = bestFreq;
        }

        int topN = 0, maxTopFreq = 0;
        for (int i = 0; i < 10; i++) {
            if (topFreqs[i] > 0) {
                topN++;
                if (topFreqs[i] > maxTopFreq) maxTopFreq = topFreqs[i];
            }
        }
        if (topN == 0) return;

        double scale = maxTopFreq > 10 ? 10.0 / maxTopFreq : 1.0;
        int[] heights = new int[topN];
        for (int i = 0; i < topN; i++) heights[i] = (int)(topFreqs[i] * scale);

        for (int row = 11; row > 0; row--) {
            for (int i = 0; i < topN; i++) {
                if (heights[i] == row - 1)
                {
                    System.out.printf( "%-4d", topFreqs[i]);
                    continue;
                }
                System.out.print(heights[i] >= row ? "#   " : "    ");
            }
            System.out.println();
        }
        for (int i = 0; i < topN; i++) {
            System.out.printf("%-4c", topChars[i]);
        }
        System.out.println();
    }
}
