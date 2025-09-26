import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] students = new String[10];
        int studentCount = 0;
        while (true) {
            String line = sc.nextLine();
            if (line.equals(".")) break;
            students[studentCount++] = line;
        }

        int[] classHour = new int[10];
        String[] classDay = new String[10];
        int classCount = 0;
        while (true) {
            String line = sc.nextLine();
            if (line.equals(".")) break;
            String[] parts = line.split(" ");
            classHour[classCount] = Integer.parseInt(parts[0]);
            classDay[classCount] = parts[1];
            classCount++;
        }

        int[] slotHour = new int[31*10];
        String[] slotDay = new String[31*10];
        int[] slotDate = new int[31*10];
        int slotCount = 0;
        String[] weekDays = {"MO","TU","WE","TH","FR","SA","SU"};

        for (int d = 1; d <= 30; d++) {
            String weekday = weekDays[d % 7];
            for (int i = 0; i < classCount; i++) {
                if (classDay[i].equals(weekday)) {
                    slotHour[slotCount] = classHour[i];
                    slotDay[slotCount] = classDay[i];
                    slotDate[slotCount] = d;
                    slotCount++;
                }
            }
        }

        String[][] attStatus = new String[studentCount][slotCount];
        for (int i = 0; i < studentCount; i++)
            for (int j = 0; j < slotCount; j++)
                attStatus[i][j] = " ";

        while (true) {
            String line = sc.nextLine();
            if (line.equals(".")) break;
            String[] parts = line.split(" ");
            String name = parts[0];
            int hour = Integer.parseInt(parts[1]);
            int date = Integer.parseInt(parts[2]);
            String status = parts[3];
            int sIdx = -1;
            for (int i = 0; i < studentCount; i++) {
                if (students[i].equals(name)) { sIdx = i; break; }
            }
            int cIdx = -1;
            for (int i = 0; i < slotCount; i++) {
                if (slotHour[i] == hour && slotDate[i] == date) { cIdx = i; break; }
            }
            if (sIdx != -1 && cIdx != -1) {
                attStatus[sIdx][cIdx] = status.equals("HERE") ? "1" : "-1";
            }
        }
        sc.close();
        // Calculate column width based on header length
        int colWidth = 0;
        for (int i = 0; i < slotCount; i++) {
            String header = slotHour[i] + ":00 " + slotDay[i] + " " + slotDate[i];
            if (header.length() > colWidth) colWidth = header.length();
        }
        colWidth += 1; // Add space for separator
        
        // Print header with proper padding
        for (int i = 0; i < slotCount; i++) {
            String header = slotHour[i] + ":00 " + slotDay[i] + " " + slotDate[i];
            System.out.printf("%-" + colWidth + "s|", header);
        }
        System.out.println();
        
        // Print student rows with proper padding
        for (int i = 0; i < studentCount; i++) {
            System.out.printf("%-" + colWidth + "s|", students[i]);
            for (int j = 0; j < slotCount; j++) {
                System.out.printf("%-" + colWidth + "s|", attStatus[i][j]);
            }
            System.out.println();
        }
    }
}
