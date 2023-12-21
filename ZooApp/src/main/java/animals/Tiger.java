package animals;

public class Tiger extends Carnivore {
    public Tiger(String name) {
        super(name);
    }

    @Override
    public void feed() {
        increaseSatiety(30); // Увеличиваем сытость
        System.out.println(name + " is fed. Satiety: " + getSatiety());
    }

    @Override
    public void pet() {
        decreaseSatiety(5); // Уменьшаем сытость
        System.out.println(name + " enjoys petting. Satiety: " + getSatiety());
    }

    @Override
    public void sleep() {
        decreaseSatiety(45); // Уменьшаем сытость
        System.out.println(name + " sleeps. Satiety: " + getSatiety());
    }
}