package animals;

import behaviors.Feedable;
import behaviors.Pettable;
import behaviors.Sleepable;

public abstract class Carnivore extends Mammal implements Feedable, Pettable, Sleepable {
    public Carnivore(String name) {
        super(name);
    }

    @Override
    public void eat() {
        System.out.println(name + " is eating meat.");
    }
}