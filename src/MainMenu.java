import api.HotelResource;
import model.IRoom;
import model.Reservation;
import util.MenuUtil;
import util.MessageConstant;

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
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
                            //
                            break;
                        case '2':
                            //
                            break;
                        case '3':
                            //
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
}
