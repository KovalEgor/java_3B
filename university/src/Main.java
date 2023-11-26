//Композиция и агрегация
//
//Ваш пример, иллюстрирующий композицию и агрегацию и показывающий отличия одного от другого

import java.util.ArrayList;
import java.util.List;
// Класс "Студент"
class Student {
    private String name;
    public Student(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
// Класс "Университет" с композицией студентов
class University {
    private List<Student> students;
    public University() {
        this.students = new ArrayList<>();
    }
    public void enrollStudent(String name) {
        Student student = new Student(name);
        students.add(student);
    }
    public void displayStudents() {
        System.out.println("Студенты в университете:");
        for (Student student : students) {
            System.out.println(student.getName());
        }
    }
}
// Класс "КомпозицияАгрегацияПример"
public class Main {
    public static void main(String[] args) {
        // Пример композиции
        University university = new University();
        university.enrollStudent("Сергей");
        university.enrollStudent("Матвей");
        university.displayStudents();
        // Пример агрегации
        List<Student> externalStudents = new ArrayList<>();
        externalStudents.add(new Student("Андрей"));
        externalStudents.add(new Student("Владислав"));
        University externalUniversity = new University();
        // На этом этапе внешний университет пустой
        externalUniversity.displayStudents();
        externalUniversity.enrollStudent(externalStudents.get(0).getName());
        externalUniversity.enrollStudent(externalStudents.get(1).getName());
        // Теперь студенты добавлены, но они могут существовать независимо от externalUniversity
        externalUniversity.displayStudents();
    }
}
