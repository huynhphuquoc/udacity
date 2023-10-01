package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collections;
import java.util.List;
import java.util.Date;

public class HotelResource {

    public HotelResource() {}

    private final CustomerService customerService = new CustomerService();
    private final ReservationService reservationService = new ReservationService();

    public Customer getCustomer(String email) {
        return customerService.getCustomer(email);
    }

    public void createACustomer(String email, String firstName, String lastName) {
        customerService.addCustomer(email, firstName, lastName);
    }

    public IRoom getRoom(String roomNumber) {
        return reservationService.getARoom(roomNumber);
    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) {
        return reservationService.reserveARoom(getCustomer(customerEmail), room, checkInDate, checkOutDate);
    }

    public List<Reservation> getCustomersReservations(String customerEmail) {
        final Customer customer = getCustomer(customerEmail);

        if (customer == null) {
            return Collections.emptyList();
        }

        return reservationService.getCustomersReservation(getCustomer(customerEmail));
    }

    public List<IRoom> findARoom(final Date checkIn, final Date checkOut) {
        return reservationService.findRooms(checkIn, checkOut);
    }

    public List<IRoom> findAlternativeRooms(final Date checkIn, final Date checkOut) {
        return reservationService.findAlternativeRooms(checkIn, checkOut);
    }

    public Date addDefaultPlusDays(final Date date) {
        return reservationService.addDefaultPlusDays(date);
    }


}
