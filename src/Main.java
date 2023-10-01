import model.Room;
import model.RoomType;
import service.Data;

public class Main {
    public static void main(String[] args) {
        intData();
        MainMenu.mainMenu();
    }

    private static void intData() {
        Data.rooms.add(new Room("1", 1200D, RoomType.SINGLE));
        Data.rooms.add(new Room("2", 1500D, RoomType.DOUBLE));
        Data.rooms.add(new Room("3", 2000D, RoomType.DOUBLE));
        Data.rooms.add(new Room("4", 1000D, RoomType.SINGLE));
        Data.rooms.add(new Room("5", 900D, RoomType.SINGLE));
        Data.rooms.add(new Room("6", 700D, RoomType.SINGLE));
        Data.rooms.add(new Room("7", 1700D, RoomType.DOUBLE));
        Data.rooms.add(new Room("8", 500D, RoomType.SINGLE));
        Data.rooms.add(new Room("9", 200D, RoomType.SINGLE));
        Data.rooms.add(new Room("10", 300D, RoomType.SINGLE));
    }
}