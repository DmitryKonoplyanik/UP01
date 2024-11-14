// ���������� ����� ��� �������� ���������� �� ������������ �������
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

// ���������� ����� ��� �������� ���������� � ����������
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

// ���������� ����� ��� �������� ���������� �� ����������� ������
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

// �������� ����� ��� �������� ���������� � ����������
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
        System.out.println("������������ �������: " + os.getName() + " " + os.getVersion());
        System.out.println("���������: " + processor.getModel() + ", ����: " + processor.getCores());
        System.out.println("����������� ������: " + ram.getSize() + " " + ram.getType());
    }
}

// ������ ������������� ������ Computer
public class Main {
    public static void main(String[] args) {
        // ������� ������� ���������� �������
        OS windows = new OS("Windows", "10");
        Processor intel = new Processor("Intel Core i7", 4);
        RAM ddr4 = new RAM(16, "DDR4");

        // ������� ������ ��������� ������ Computer
        Computer pc = new Computer(windows, intel, ddr4);

        // ������� ���������� � ����������
        pc.displayInfo();
    }
}
