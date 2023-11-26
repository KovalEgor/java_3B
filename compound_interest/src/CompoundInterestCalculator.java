//Сложный процент
//Реализовать программу, вычисляющую сложный процент на основе введенных данных:
//1. Числа
//2. Процента
//3. Количества периодов
//Реализовать обратное действие. Посчитать каким должен быть процент, чтобы от Числа1 дойти до Числа2 за N периодов.



import java.util.Scanner;
public class CompoundInterestCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите действие:");
        System.out.println("1. Вычислить будущую сумму по введенным данным");
        System.out.println("2. Вычислить процент для достижения заданной суммы");
        int choice = scanner.nextInt();
        if (choice == 1) {
            calculateFutureValue();
        } else if (choice == 2) {
            calculateRequiredRate();
        } else {
            System.out.println("Некорректный выбор.");
        }
    }
    private static void calculateFutureValue() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите начальную сумму (Число): ");
        double principal = scanner.nextDouble();
        System.out.print("Введите годовой процент (Процент): ");
        double rate = scanner.nextDouble() / 100.0;
        System.out.print("Введите количество периодов (лет): ");
        int periods = scanner.nextInt();
        double futureValue = principal * Math.pow(1 + rate, periods);
        System.out.println("Будущая сумма: " + futureValue);
    }
    private static void calculateRequiredRate() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите начальную сумму (Число1): ");
        double principal1 = scanner.nextDouble();
        System.out.print("Введите конечную сумму (Число2): ");
        double principal2 = scanner.nextDouble();
        System.out.print("Введите количество периодов (лет): ");
        int periods = scanner.nextInt();
        double rate = Math.pow(principal2 / principal1, 1.0 / periods) - 1;
        double percentageRate = rate * 100;
        System.out.println("Необходимый процент: " + percentageRate + "%");
    }
}
