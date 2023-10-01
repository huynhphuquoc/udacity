import api.AdminResource;
import model.Customer;
import model.IRoom;
import model.Room;
import model.RoomType;
import service.ReservationService;
import util.MenuUtil;
import util.MessageConstant;

import java.util.*;

public class AdminMenu {
    private static final AdminResource adminResource = new AdminResource();
    private static final ReservationService reservationService = new ReservationService();

    public static void adminMenu() {

        boolean isLeave = false;
        final Scanner scanner = new Scanner(System.in);

            do {
                MenuUtil.printAdminMenu();
                String option = scanner.nextLine();

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
                            isLeave = true;
                            MenuUtil.printMainMenu();
                            break;
                        default:
                            isLeave = true;
                            System.out.println(MessageConstant.INVALID_OPTION);
                            break;
                    }
            } while (!isLeave);
    }

    private static void showAllRooms() {
        Collection<IRoom> rooms = adminResource.getAllRooms();

        if(rooms.isEmpty()) {
            System.out.println("There are no room in the system. please create room");
        } else {
            rooms.forEach(room -> System.out.println(room));
        }
    }

    private static void showAllCustomers() {
        Collection<Customer> customers = adminResource.getAllCustomers();

        if (customers.isEmpty()) {
            System.out.println("There are no customers in the system.");
        } else {
            customers.forEach(customer -> System.out.println(customer));
        }
    }

    private static void showAllReservations() {
        adminResource.displayAllReservations();
    }

    private static void addRoom() {
        final Scanner scanner = new Scanner(System.in);
        final List<IRoom> rooms = new ArrayList<>();

        System.out.println("Enter room number:");
        final String roomNumber = scanner.nextLine();

        if (reservationService.getARoom(roomNumber) != null) {
            System.out.println("Room number is exist");
            addRoom();
        } else {
            System.out.println("Enter price:");
            final double roomPrice = Double.parseDouble(scanner.nextLine());

            System.out.println("Enter room type: 1 for single bed, 2 for double bed:");
            final RoomType roomType = RoomType.valueOfLabel(scanner.nextLine());

            final Room room = new Room(roomNumber, roomPrice, roomType);
            rooms.add(room);

            System.out.println("Would like to add another room? Y/N");
            String isAddOtherRoom = scanner.nextLine();

            if ("Y".equals(isAddOtherRoom)) {
                addRoom();
            } else {
                adminResource.addRoom(rooms);
                System.out.println("Create rooms success");
            }
        }
    }
}
