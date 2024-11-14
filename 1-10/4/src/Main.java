// Внутренний класс для хранения информации об операционной системе
class OS {
    private String name;
    private String version;

    public OS(String name, String version) {
        this.name = name;
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }
}

// Внутренний класс для хранения информации о процессоре
class Processor {
    private String model;
    private int cores;

    public Processor(String model, int cores) {
        this.model = model;
        this.cores = cores;
    }

    public String getModel() {
        return model;
    }

    public int getCores() {
        return cores;
    }
}

// Внутренний класс для хранения информации об оперативной памяти
class RAM {
    private int size;
    private String type;

    public RAM(int size, String type) {
        this.size = size;
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public String getType() {
        return type;
    }
}

// Основной класс для хранения информации о компьютере
class Computer {
    private OS os;
    private Processor processor;
    private RAM ram;

    public Computer(OS os, Processor processor, RAM ram) {
        this.os = os;
        this.processor = processor;
        this.ram = ram;
    }

    public OS getOs() {
        return os;
    }

    public Processor getProcessor() {
        return processor;
    }

    public RAM getRam() {
        return ram;
    }

    public void displayInfo() {
        System.out.println("Операционная система: " + os.getName() + " " + os.getVersion());
        System.out.println("Процессор: " + processor.getModel() + ", ядер: " + processor.getCores());
        System.out.println("Оперативная память: " + ram.getSize() + " " + ram.getType());
    }
}

// Пример использования класса Computer
public class Main {
    public static void main(String[] args) {
        // Создаем объекты внутренних классов
        OS windows = new OS("Windows", "10");
        Processor intel = new Processor("Intel Core i7", 4);
        RAM ddr4 = new RAM(16, "DDR4");

        // Создаем объект основного класса Computer
        Computer pc = new Computer(windows, intel, ddr4);

        // Выводим информацию о компьютере
        pc.displayInfo();
    }
}
