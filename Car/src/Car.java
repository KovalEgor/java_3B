//Приведите пример реализации принципа инкапсуляции
//Ваш пример инкапсуляции, ее полезности или вреда


    //Класс Car инкапсулирует свои поля model и year, делая их закрытыми (private) и предоставляя методы для доступа к ним (getModel(), setModel(), getYear(), setYear()).
    //Таким образом, другие классы не могут напрямую изменять значения этих полей, что обеспечивает контроль над доступом и защиту данных.
    //Полезность инкапсуляции заключается в том, что она позволяет скрыть сложность внутренней реализации класса от внешнего использования, обеспечивает контроль доступа к данным и повышает безопасность программы.
    //Однако, излишняя инкапсуляция может затруднить доступ к данным и создать излишнюю сложность в коде.
public class Car {
    //Свойства:
    //- private String model - модель автомобиля
    //- private int year - год выпуска автомобиля
    //- private boolean isEngineOn - флаг, определяющий включен ли двигатель автомобиля
    private String model;
    private int year;
    private boolean isEngineOn;
    // Конструктор:
    // public Car(String model, int year) - конструктор класса.
    // Принимает параметры модель и год выпуска, инициализирует соответствующие свойства объекта класса.
    // Также инициализирует свойство isEngineOn значением false (двигатель выключен).
    public Car(String model, int year) {
        this.model = model;
        this.year = year;
        this.isEngineOn = false;
    }
    //Методы:
    //public String getModel() - возвращает значение свойства model (модель автомобиля)
    public String getModel() {
        return model;
    }
    //public void setModel(String model) - устанавливает значение свойства model (модель автомобиля)
    public void setModel(String model) {
        this.model = model;
    }
    //public int getYear() - возвращает значение свойства year (год выпуска автомобиля)
    public int getYear() {
        return year;
    }
    //public void setYear(int year) - устанавливает значение свойства year (год выпуска автомобиля)
    public void setYear(int year) {
        this.year = year;
    }
    //public void start() - проверяет, включен ли двигатель автомобиля.
    // Если двигатель не включен, то выводит сообщение "Starting the car's engine."
    // и устанавливает значение свойства isEngineOn в true (двигатель включен).
    // Если двигатель уже включен, то выводит сообщение "The car's engine is already on."
    public void start() {
        if(!isEngineOn) {
            System.out.println("Starting the car's engine.");
            isEngineOn = true;
        } else {
            System.out.println("The car's engine is already on.");
        }
    }
    //public void stop() - проверяет, выключен ли двигатель автомобиля.
    // Если двигатель включен, то выводит сообщение "Stopping the car's engine."
    // и устанавливает значение свойства isEngineOn в false (двигатель выключен).
    // Если двигатель уже выключен, то выводит сообщение "The car's engine is already off."
    public void stop() {
        if(isEngineOn) {
            System.out.println("Stopping the car's engine.");
            isEngineOn = false;
        } else {
            System.out.println("The car's engine is already off.");
        }
    }
    //public void accelerate() - проверяет, включен ли двигатель автомобиля.
    // Если двигатель включен, то выводит сообщение "The car is accelerating".
    // Если двигатель выключен, то выводит сообщение "Cannot accelerate, the engine is off."
    public void accelerate() {
        if(isEngineOn) {
            System.out.println("The car is accelerating.");
        } else {
            System.out.println("Cannot accelerate, the engine is off.");
        }
    }
    //public void brake() - проверяет, включен ли двигатель автомобиля.
    // Если двигатель включен, то выводит сообщение "The car is braking."
    // Если двигатель выключен, то выводит сообщение "Cannot brake, the engine is off."
    public void brake() {
        if(isEngineOn) {
            System.out.println("The car is braking.");
        } else {
            System.out.println("Cannot brake, the engine is off.");
        }
    }

    public static void main(String[] args) {
        Car myCar = new Car("Tesla", 2024);

        System.out.println("Model: " + myCar.getModel());
        System.out.println("Year: " + myCar.getYear());

        myCar.start();
        myCar.accelerate();
        myCar.brake();
        myCar.stop();
        }
}