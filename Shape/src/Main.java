//Приведите пример полиморфизма
//Ваш пример, иллюстрирующий полиморфизм, его пользу или вред с вашей точки зрения

////////////////////////////////////////////////////////////////////////////////////////////////////
//Польза полиморфизма заключается в том, что он позволяет использовать общий интерфейс
//для работы с различными объектами, что делает код более гибким и удобным для поддержки
//и расширения. Кроме того, полиморфизм помогает упростить код и делает его более читаемым.
//
//Однако излишний полиморфизм может усложнить понимание кода и повлечь за собой потерю
//производительности из-за дополнительных проверок типов. Поэтому важно уметь находить баланс
//и применять полиморфизм там, где это действительно необходимо.
///////////////////////////////////////////////////////////////////////////////////////////////////
// Интерфейс "Фигура"
interface Shape {
    void draw(); // Абстрактный метод для рисования фигуры
}
// Класс "Круг", реализующий интерфейс "Фигура"
class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Рисуем круг");
    }
}
// Класс "Прямоугольник", также реализующий интерфейс "Фигура"
class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Рисуем прямоугольник");
    }
}
// Класс "Треугольник", также реализующий интерфейс "Фигура"
class Triangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Рисуем треугольник");
    }
}
// Класс "Рисователь", который может рисовать любую фигуру
class Drawer {
    void drawShape(Shape shape) {
        shape.draw();
    }
}
// Пример использования полиморфизма
public class Main {
    public static void main(String[] args) {
        Drawer drawer = new Drawer();
        // Полиморфизм: объекты различных классов, реализующих интерфейс "Фигура"
        Shape circle = new Circle();
        Shape rectangle = new Rectangle();
        Shape triangle = new Triangle();
        // Рисование фигур без явного указания их типа
        drawer.drawShape(circle);
        drawer.drawShape(rectangle);
        drawer.drawShape(triangle);
    }
}