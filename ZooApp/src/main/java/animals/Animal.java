package animals;

public abstract class Animal {
    protected String name;
    protected int satiety;

    public Animal(String name) {
        this.name = name;
        this.satiety = 100; // начальное значение
    }

    public abstract void eat();

    public void decreaseSatiety(int amount) {
        this.satiety = Math.max(this.satiety - amount, 0);
    }

    public void increaseSatiety(int amount) {
        this.satiety = Math.min(this.satiety + amount, 100);
    }

    public int getSatiety() {
        return this.satiety;
    }
}