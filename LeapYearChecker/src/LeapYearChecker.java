//Високосный год

//Посчитать, является ли введенный год високосным. 
//В случае неверного ввода данных выбросить и обработать исключительную ситуацию


import java.util.Scanner;

public class LeapYearChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);// Создание объекта класса Scanner для чтения данных с консоли
        int year;// Объявление переменной для хранения года

        try {
            System.out.print("Введите год: "); // Вывод на экран приглашения для ввода года
            year = scanner.nextInt();// Чтение года с консоли и сохранение в переменную year
        } catch (Exception e) {// Обработка исключения в случае ошибки ввода данных
            System.out.println("Ошибка ввода данных!");
            return;
        }
        if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) {
            System.out.println(year + " - високосный год");// Вывод на экран сообщения о том, что год високосный
        } else {
            System.out.println(year + " - невисокосный год");// Вывод на экран сообщения о том, что год невисокосный 
        }
    }
}
