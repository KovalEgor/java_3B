//Консольный калькулятор
//Реализовать калькулятор для четырех базовых арифметических действий, который на вход получает строку вида 2 * 6.5 и выдает результат вычисления на консоль.


import java.util.Scanner;


public class Calculator {
    public static void main(String[] args) {
        System.out.println("Для выхода используйте 'q'\n Для вывода данных используйте '?'\n Введите данные:");
        //создается новый объект класса Scanner для считывания ввода пользователя из командной строки.
        Scanner sc = new Scanner(System.in);
        //инициализация переменных a и b вещественным числом равным 0.
        double a = 0;
        double b = 0;
        //инициализация переменной op как пустой строки
        String op = "";
        do {
            //`String input = sc.next();` - считывает следующее введенное пользователем значение в переменную input
            String input = sc.next();
            if (input.equals("q")) {
                break;}
            if (input.equals("?")) {
                System.out.println("Вы можете использовать такие функции как\n Сложение +\n Вычитание -\n Деление /\n Умножение *\n Корень %\n Степень ^\n" +
                        "Пример ввода числа: 34+6.");}
            //код ниже разделяет строку input на массив строк strings и operator с использованием регулярных выражений
            String[] strings = input.split("\\W");
            String[] operator = input.split("\\w");
            try {
                //преобразует первое и второе значение из массива strings в целые числа и присваивает их переменным a и b.
                a = Integer.parseInt(strings[0]);
                b = Integer.parseInt(strings[1]);
                //присваивает переменной op последний элемент массива operator
                op = operator[operator.length - 1];
                //вызывает метод operation и выводит результат выполненной операции
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
