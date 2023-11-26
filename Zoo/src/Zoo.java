//Разработать иерархию классов для игры "Зоопарк"

//Разработать иерархию классов, описывающих сущности предполагаемой игры "Зоопарк". 
//Должны быть использованы абстрактные, конкретные классы и интерфейсы. Классы должны быть организованы в пакеты.

import java.util.ArrayList;
import java.util.List;

// Класс "Зоопарк" (композитный класс)
public class Zoo {
    private List<Animal> animals;

    public Zoo() {
        this.animals = new ArrayList<>();
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void makeAllSounds() {
        for (Animal animal : animals) {
            animal.makeSound();
        }
    }

    public static void main(String[] args) {
        Zoo myZoo = new Zoo();
        // Добавление разных животных в зоопарк
        myZoo.addAnimal(new Lion("Симба"));
        myZoo.addAnimal(new Eagle());
        // Воспроизведение звуков всех животных в зоопарке
        myZoo.makeAllSounds();
    }
}
