//Приведите пример принципа наследования
//Ваш пример, иллюстрирующий пользу или вред принципа наследования в ООП


// Базовый класс "Транспортное средство"
//Класс Vehicle является базовым классом, который имеет поля brand (марка) и maxSpeed (максимальная скорость).
//У класса Vehicle есть конструктор, который принимает значения для этих полей, и метод displayInfo(), который выводит информацию о марке и максимальной скорости транспортного средства.
class Vehicle {
    private String brand;
    private int maxSpeed;
    public Vehicle(String brand, int maxSpeed) {
        this.brand = brand;
        this.maxSpeed = maxSpeed;
    }
    public void displayInfo() {
        System.out.println("Марка: " + brand);
        System.out.println("Максимальная скорость: " + maxSpeed + " км/ч");
    }
}
// Подкласс "Автомобиль", наследующий от базового класса "Транспортное средство"
//Класс Car является подклассом класса Vehicle и имеет дополнительное поле numberOfDoors (количество дверей).
// У класса Car также есть конструктор, который вызывает конструктор класса Vehicle с помощью ключевого слова super
// и устанавливает значение для поля numberOfDoors, а также метод displayCarInfo(), который вызывает метод displayInfo() из класса Vehicle
// и выводит информацию о количестве дверей.
class Car extends Vehicle {
    private int numberOfDoors;
    public Car(String brand, int maxSpeed, int numberOfDoors) {
        super(brand, maxSpeed);
        this.numberOfDoors = numberOfDoors;
    }
    public void displayCarInfo() {
        displayInfo();
        System.out.println("Количество дверей: " + numberOfDoors);
    }
}
// Подкласс "Мотоцикл", также наследующий от базового класса "Транспортное средство"
//Класс Motorcycle также является подклассом класса Vehicle и имеет дополнительное поле hasFairing (есть ли обтекатель).
// У класса Motorcycle также есть конструктор, который вызывает конструктор класса Vehicle с помощью ключевого слова super
// и устанавливает значение для поля hasFairing, а также метод displayMotorcycleInfo(), который вызывает метод displayInfo() из класса Vehicle
// и выводит информацию о наличии обтекателя.
class Motorcycle extends Vehicle {
    private boolean hasFairing;
    public Motorcycle(String brand, int maxSpeed, boolean hasFairing) {
        super(brand, maxSpeed);
        this.hasFairing = hasFairing;
    }
    public void displayMotorcycleInfo() {
        displayInfo();
        System.out.println("Есть обтекатель: " + hasFairing);
    }
}
// Пример использования классов
//В методе main класса Main создаются объекты классов Car и Motorcycle, их методы вызываются для вывода информации о каждом транспортном средстве.
public class Main {
    public static void main(String[] args) {
        Car car = new Car("Toyota", 200, 4);
        car.displayCarInfo();
        Motorcycle motorcycle = new Motorcycle("Harley-Davidson", 150, true);
        motorcycle.displayMotorcycleInfo();
    }
}
