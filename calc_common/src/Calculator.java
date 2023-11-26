//Консольный калькулятор
//Реализовать калькулятор для четырех базовых арифметических действий, который на вход получает строку вида 2 * 6.5 и выдает результат вычисления на консоль.


import java.util.Scanner;


public class Calculator {
    public static void main(String[] args) {
        System.out.println("Для выхода используйте 'q'\n Для вывода данных используйте '?'\n Введите данные:");
        Scanner sc = new Scanner(System.in);
        double a = 0;
        double b = 0;
        String op = "";
        do {
            String input = sc.next();
            if (input.equals("q")) {
                break;}
            if (input.equals("?")) {
                System.out.println("Вы можете использовать такие функции как\n Сложение +\n Вычитание -\n Деление /\n Умножение *\n Корень %\n Степень ^\n" +
                        "Пример ввода числа: 34+6.");}
            String[] strings = input.split("\\W");
            String[] operator = input.split("\\w");
            try {
                a = Integer.parseInt(strings[0]);
                b = Integer.parseInt(strings[1]);
                op = operator[operator.length - 1];
                System.out.println("Результат = " + operation(a, b, op));
            } catch (Exception e) {
                System.out.println("Ошибка 404. Попробуйте ещё раз. Пример ввода числа: 34+6.");
            }
        } while (true);
    }
    //Этот код представляет метод operation, который принимает два числа и строку, представляющую операцию математическую операцию (+, -, *, /, ^, или %). 
    //В зависимости от значения строки op, метод выполняет соответствующую операцию с числами a и b и возвращает результат. 
    //Если значение строки op не соответствует ни одной из операций, метод возвращает 0.
    private static double operation(double a, double b, String op) {
        switch (op) {
            case "*":
                return (a * b);
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "/":
                return a / b;
            case "^":
                return Math.pow(a, b);
            case "%":
                return Math.pow(a, 1.0/b);
            default:
                return 0;
        }
    }
}
