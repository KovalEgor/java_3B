import java.util.Scanner;

public class LeapYearChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int year;

        try {
            System.out.print("Введите год: ");
            year = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Ошибка ввода данных!");
            return;
        }
        if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) {
            System.out.println(year + " - високосный год");
        } else {
            System.out.println(year + " - невисокосный год");
        }
    }
}