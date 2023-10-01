import api.AdminResource;
import model.Customer;
import model.IRoom;
import model.Room;
import model.RoomType;
import util.MenuUtil;
import util.MessageConstant;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {
    private static final AdminResource adminResource = new AdminResource();

    public static void adminMenu() {
        MenuUtil.printAdminMenu();
        String option = "";
        final Scanner scanner = new Scanner(System.in);

        try {
            do {
                option = scanner.nextLine();

                if (option.length() == 1) {
                    switch (option.charAt(0)) {
                        case '1':
                            showAllCustomers();
                            break;
                        case '2':
                            showAllRooms();
                            break;
                        case '3':
                            showAllReservations();
                            break;
                        case '4':
                            addRoom();
                            break;
                        case '5':
                            MenuUtil.printMainMenu();
                            break;
                        default:
                            System.out.println(MessageConstant.INVALID_OPTION);
                            break;
                    }
                } else {
                    System.out.println(MessageConstant.INVALID_OPTION);
                }
            } while (option.charAt(0) != '5' || option.length() != 1);
        } catch (Exception ex) {
            System.out.println("Empty input received. Exiting program...");
        }
    }

    private static void addRoom() {
        final Scanner scanner = new Scanner(System.in);

        System.out.println("Enter room number:");
        final String roomNumber = scanner.nextLine();

        System.out.println("Enter price per night:");
        final double roomPrice = enterRoomPrice(scanner.nextLine());

        System.out.println("Enter room type: 1 for single bed, 2 for double bed:");
        final RoomType roomType = enterRoomType(scanner.nextLine());

        final Room room = new Room(roomNumber, roomPrice, roomType);

        adminResource.addRoom(Collections.singletonList(room));
        System.out.println("Room added successfully!");

        System.out.println("Would like to add another room? Y/N");
        addAnotherRoom();
    }

    private static double enterRoomPrice(String roomId) {
        try {
            return Double.parseDouble(roomId);
        } catch (Exception exp) {
            System.out.println("Invalid room price! Please, enter a valid double number. " +
                    "Decimals should be separated by point (.)");
            return enterRoomPrice(roomId);
        }
    }

    private static RoomType enterRoomType(String roomType) {
        try {
            return RoomType.valueOfLabel(roomType);
        } catch (Exception exp) {
            System.out.println("Invalid room type! Please, choose 1 for single bed or 2 for double bed:");
            return enterRoomType(roomType);
        }
    }

    private static void addAnotherRoom() {
        final Scanner scanner = new Scanner(System.in);

        try {
            String anotherRoom;

            anotherRoom = scanner.nextLine();

            while ((anotherRoom.charAt(0) != 'Y' && anotherRoom.charAt(0) != 'N')
                    || anotherRoom.length() != 1) {
                System.out.println("Please enter Y (Yes) or N (No)");
                anotherRoom = scanner.nextLine();
            }

            if (anotherRoom.charAt(0) == 'Y') {
                addRoom();
            } else if (anotherRoom.charAt(0) == 'N') {
                MenuUtil.printAdminMenu();
            } else {
                addAnotherRoom();
            }
        } catch (StringIndexOutOfBoundsException ex) {
            addAnotherRoom();
        }
    }

    private static void showAllRooms() {
        List<IRoom> rooms = adminResource.getAllRooms();

        if(rooms.isEmpty()) {
            System.out.println("No rooms found.");
        } else {
            adminResource.getAllRooms().forEach(System.out::println);
        }
    }

    private static void showAllCustomers() {
        Collection<Customer> customers = adminResource.getAllCustomers();

        if (customers.isEmpty()) {
            System.out.println("No customers found.");
        } else {
            adminResource.getAllCustomers().forEach(System.out::println);
        }
    }

    private static void showAllReservations() {
        adminResource.displayAllReservations();
    }
}
