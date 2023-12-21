package animals;

public class Bear extends Carnivore {
    public Bear(String name) {
        super(name);
    }

    @Override
    public void feed() {
        increaseSatiety(30); // ����������� �������
        System.out.println(name + " is fed. Satiety: " + getSatiety());
    }

    @Override
    public void pet() {
        decreaseSatiety(5); // ��������� �������
        System.out.println(name + " enjoys petting. Satiety: " + getSatiety());
    }

    @Override
    public void sleep() {
        decreaseSatiety(45); // ��������� �������
        System.out.println(name + " sleeps. Satiety: " + getSatiety());
    }
}