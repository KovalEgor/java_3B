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
//класс "Университет" содержит список студентов, которые являются членами университета
class University {
    private List<Student> students;
    public University() {
        this.students = new ArrayList<>();
    }
    //Метод enrollStudent добавляет нового студента в университет
    public void enrollStudent(String name) {
        Student student = new Student(name);
        students.add(student);
    }
    //метод displayStudents отображает список всех студентов в университете
    public void displayStudents() {
        System.out.println("Студенты в университете:");
        for (Student student : students) {
            System.out.println(student.getName());
        }
    }
}
// Класс "КомпозицияАгрегацияПример"
//В методе main создается объект университета и добавляются два студента. Затем создается список внешних студентов и создается внешний университет. 
//Внешние студенты добавляются во внешний университет с помощью метода enrollStudent, и затем отображается список студентов во внешнем университете.
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
