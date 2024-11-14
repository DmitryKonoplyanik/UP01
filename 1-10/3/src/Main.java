import java.util.ArrayList;
import java.util.List;

class Door {
    private boolean isLocked;

    public void lock() {
        isLocked = true;
    }

    public boolean isLocked() {
        return isLocked;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Door door = (Door) obj;
        return isLocked == door.isLocked;
    }

    @Override
    public int hashCode() {
        return isLocked ? 1 : 0;
    }

    @Override
    public String toString() {
        return "Door{" +
                "isLocked=" + isLocked +
                '}';
    }
}

class Window {
    private boolean isOpen;

    public void open() {
        isOpen = true;
    }

    public boolean isOpen() {
        return isOpen;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Window window = (Window) obj;
        return isOpen == window.isOpen;
    }

    @Override
    public int hashCode() {
        return isOpen ? 1 : 0;
    }

    @Override
    public String toString() {
        return "Window{" +
                "isOpen=" + isOpen +
                '}';
    }
}

class House {
    private List<Window> windows;
    private List<Door> doors;

    public House() {
        windows = new ArrayList<>();
        doors = new ArrayList<>();
    }

    public void addWindow(Window window) {
        windows.add(window);
    }

    public void addDoor(Door door) {
        doors.add(door);
    }

    public void lockDoor(int index) {
        if (index >= 0 && index < doors.size()) {
            doors.get(index).lock();
        }
    }

    public int getWindowCount() {
        return windows.size();
    }

    public int getDoorCount() {
        return doors.size();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        House house = (House) obj;
        return windows.equals(house.windows) && doors.equals(house.doors);
    }

    @Override
    public int hashCode() {
        int result = windows.hashCode();
        result = 31 * result + doors.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "House{" +
                "windows=" + windows +
                ", doors=" + doors +
                '}';
    }
}

public class Main {
    public static void main(String[] args) {
        House house = new House();
        house.addWindow(new Window());
        house.addWindow(new Window());
        house.addDoor(new Door());
        house.addDoor(new Door());

        house.lockDoor(0);

        System.out.println("Количество окон: " + house.getWindowCount());
        System.out.println("Количество дверей: " + house.getDoorCount());
        System.out.println(house);
    }
}
