// Класс "Орел" (конкретный класс, реализует интерфейс "Птица")
public class Eagle implements Bird {
    @Override
    public void makeSound() {
        System.out.println("Кар!");
    }

    @Override
    public void fly() {
        System.out.println("Орел летит высоко в небе.");
    }
}
