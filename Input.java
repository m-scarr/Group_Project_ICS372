import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Input {

    public static char getChar(String prompt) {
        Scanner scanner = new Scanner(System.in);
        char result;

        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();

            if (input.length() == 1) {
                result = input.charAt(0);
                return result;
            } else {
                System.out.println("Invalid input. Please enter a single character.");
            }
        }
    }
    public static String getString(String prompt) {
        Scanner scanner = new Scanner(System.in);
        String result;

        while (true) {
            System.out.print(prompt);
            result = scanner.nextLine().trim(); // Read and trim the input

            if (!result.isEmpty()) {
                return result;
            } else {
                System.out.println("You didn't enter anything, try again.");
            }
        }
    }

    public static int getInt(String prompt) {
        Scanner scanner = new Scanner(System.in);
        int result;

        while (true) {
            System.out.print(prompt);

            if (scanner.hasNextInt()) {
                result = scanner.nextInt();
                scanner.nextLine();
                return result;
            } else {
                System.out.println("Invalid input, try again.");
                scanner.nextLine();
            }
        }
    }

    public static double getDouble(String prompt) {
        Scanner scanner = new Scanner(System.in);
        double result;

        while (true) {
            System.out.print(prompt);

            if (scanner.hasNextDouble()) {
                result = scanner.nextDouble();
                scanner.nextLine();
                return result;
            } else {
                System.out.println("Invalid input, try again.");
                scanner.nextLine();
            }
        }
    }
    public static Date getDate(String prompt) {
        Scanner scanner = new Scanner(System.in);
        Date result;
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        dateFormat.setLenient(false);

        while (true) {
            System.out.print(prompt);
            String dateString = scanner.nextLine().trim();
            try {
                result = dateFormat.parse(dateString);
                return result;
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please enter a date in mm/dd/yyyy format.");
            }
        }
    }


    public static File getFile(String prompt) {
        Scanner scanner = new Scanner(System.in);
        String filePath;
        File file;

        while (true) {
            System.out.print(prompt);
            filePath = scanner.nextLine();
            file = new File(filePath);

            if (file.exists() && !file.isDirectory()) {
                return file;
            } else {
                System.out.println("Not a valid file path, try again.");
            }
        }
    }
}
