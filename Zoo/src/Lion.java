// Класс "Лев" (конкретный класс, наследуется от абстрактного класса "Млекопитающее")
public class Lion extends Mammal {
    public Lion(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println("Рррр!");
    }

    @Override
    public void giveBirth() {
        System.out.println("Львица рожает детенышей.");
    }
}
