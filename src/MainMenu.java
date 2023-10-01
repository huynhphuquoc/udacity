import api.HotelResource;
import exception.HotelReservationException;
import model.Customer;
import model.IRoom;
import model.Reservation;
import util.MenuUtil;
import util.MessageConstant;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

public class MainMenu {
    private static final HotelResource hotelResource = new HotelResource();

    public static void mainMenu() {

        boolean isLeave = false;
        final Scanner scanner = new Scanner(System.in);

        try {
            do {
                MenuUtil.printMainMenu();
                String option = scanner.nextLine();

                    switch (option.charAt(0)) {
                        case '1':
                            findAndReserveARoom();
                            break;
                        case '2':
                            seeReservations();
                            break;
                        case '3':
                            createCustomer();
                            break;
                        case '4':
                            AdminMenu.adminMenu();
                            break;
                        case '5':
                            isLeave = true;
                            System.out.println("Exit");
                            break;
                        default:
                            isLeave = true;
                            System.out.println(MessageConstant.INVALID_OPTION);
                            break;
                    }
            } while (!isLeave);
        } catch (Exception e) {
            System.out.println("Exiting program...");
        }
    }

    private static void findAndReserveARoom() {
        final Scanner scanner = new Scanner(System.in);

        System.out.println("please input check in date. Ex: 01/01/2023");
        Date checkInDate = new Date(scanner.nextLine());

        System.out.println("please input check out date. Ex: 01/01/2023");
        Date checkOutDate = new Date(scanner.nextLine());

        Collection freeRooms = hotelResource.findARoom(checkInDate, checkOutDate);

        if (freeRooms.isEmpty()) {
            freeRooms = hotelResource.findARoom(addDate(checkInDate), addDate(checkOutDate));

            if (freeRooms.isEmpty()) {
                System.out.println("Room not found with require Date");
                findAndReserveARoom();
            } else {
                System.out.println("No rooms were found for the requested date, below are the recommended rooms.");
                freeRooms.forEach(freeRoom -> System.out.println(freeRoom));
            }
        } else {
            freeRooms.forEach(freeRoom -> System.out.println(freeRoom));
        }

        bookRoom(checkInDate, checkOutDate);
    }

    private static void seeReservations() {
        final Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Customer Email");
        String customerEmail = scanner.nextLine();

        try {

            Collection<Reservation> reservations = hotelResource.getCustomersReservations(customerEmail);

            if (reservations.isEmpty()) {
                System.out.println("Reservation not found");
            } else {
                reservations.stream().forEach(reservation -> System.out.println(reservation));
            }

        } catch (HotelReservationException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void createCustomer() {
        final Scanner scanner = new Scanner(System.in);

        System.out.println("Enter first name.");
        String firstName = scanner.nextLine();

        System.out.println("Enter last name.");
        String lastName = scanner.nextLine();

        System.out.println("Enter Email:");
        String email = scanner.nextLine();

        try {
            Customer customer = hotelResource.getCustomer(email);
            if (customer != null) {
                System.out.println("Email has registered with other account.");
                createCustomer();
            } else {
                System.out.println("Create customer success.");
                hotelResource.createACustomer(email, firstName, lastName);
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static Date addDate(Date date) {

        LocalDate localDate = date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        // Add 7 days
        LocalDate futureDate = localDate.plusDays(7);

        return Date.from(futureDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    private static void bookRoom(final Date checkIn, final Date checkOut) {
        final Scanner scanner = new Scanner(System.in);

        System.out.println("Do you want reserve a room? Y/N:");
        String isBookRoom = scanner.nextLine();

        if ("Y".equals(isBookRoom)) {
            System.out.println("Please enter customer email.");
            String customerEmail = scanner.nextLine();

            System.out.println("Please enter room number.");
            String rooNumber = scanner.nextLine();

            IRoom room = hotelResource.getRoom(rooNumber);

            try {
                hotelResource.bookARoom(customerEmail, room, checkIn, checkOut);
            } catch (HotelReservationException e) {
                System.out.println(e.getMessage());
            }
        }
    }


}
