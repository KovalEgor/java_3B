import animals.*;
import java.util.Scanner;

public class ZooApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continueRunning = true;

        while (continueRunning) {
            System.out.println("Welcome to the Zoo App!");
            boolean animalSelected = false;
            Animal selectedAnimal = null;

            while (!animalSelected) {
                System.out.println("Choose an animal: 1 - Lion, 2 - Tiger, 3 - Bear, 4 - Exit");
                int animalChoice = scanner.nextInt();

                switch (animalChoice) {
                    case 1:
                        selectedAnimal = new Lion("Leo");
                        animalSelected = true;
                        break;
                    case 2:
                        selectedAnimal = new Tiger("Tiggy");
                        animalSelected = true;
                        break;
                    case 3:
                        selectedAnimal = new Bear("Baloo");
                        animalSelected = true;
                        break;
                    case 4:
                        System.out.println("Exiting Zoo App.");
                        continueRunning = false;
                        animalSelected = true; // To exit the animal selection loop
                        break;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                        break;
                }
            }

            if (!continueRunning) {
                break; // Exit the outer loop if the user chooses to exit
            }

            System.out.println("Selected: " + selectedAnimal.getClass().getSimpleName());

            boolean continueWithAnimal = true;
            while (continueWithAnimal) {
                System.out.println("What do you want to do? 1 - Feed, 2 - Pet, 3 - Put to sleep, 4 - Choose another animal");
                int action = scanner.nextInt();

                switch (action) {
                    case 1:
                        if (selectedAnimal instanceof Carnivore) {
                            ((Carnivore) selectedAnimal).feed();
                        }
                        break;
                    case 2:
                        if (selectedAnimal instanceof Carnivore) {
                            ((Carnivore) selectedAnimal).pet();
                        }
                        break;
                    case 3:
                        if (selectedAnimal instanceof Carnivore) {
                            ((Carnivore) selectedAnimal).sleep();
                        }
                        break;
                    case 4:
                        continueWithAnimal = false; // Breaks the inner loop, returns to animal selection
                        break;
                    default:
                        System.out.println("Invalid action. Please try again.");
                        break;
                }

                if (continueWithAnimal) {
                    System.out.println("Would you like to continue with " + selectedAnimal.getClass().getSimpleName() + "? (y/n)");
                    String continueChoice = scanner.next();
                    if (!continueChoice.equalsIgnoreCase("y")) {
                        continueWithAnimal = false;
                    }
                }
            }
        }

        System.out.println("Thank you for using Zoo App!");
        scanner.close();
    }
}
