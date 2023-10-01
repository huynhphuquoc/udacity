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
    private static final String DATE_FORMAT = "MM/dd/yyyy";
    private static final HotelResource hotelResource = new HotelResource();

    public static void mainMenu() {
        MenuUtil.printMainMenu();

        String option  = "";
        Scanner scanner = new Scanner(System.in);

        try {
            do {
                option = scanner.nextLine();

                if (option.length() == 1) {
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
                            System.out.println("Exit");
                            break;
                        default:
                            System.out.println("Unknown action\n");
                            break;
                    }
                } else {
                    System.out.println(MessageConstant.INVALID_OPTION);
                }
            } while (option.charAt(0) != '5' || option.length() != 1);
        } catch (StringIndexOutOfBoundsException ex) {
            System.out.println("Empty input received. Exiting program...");
        }
    }
}
