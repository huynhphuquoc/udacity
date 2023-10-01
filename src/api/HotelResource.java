package api;

import exception.HotelReservationException;
import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.Data;
import service.ReservationService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Date;
import java.util.stream.Collectors;

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

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date CheckOutDate) throws HotelReservationException {
        Customer customer = customerService.getCustomer(customerEmail);

        if (customer == null) {
            throw new HotelReservationException("Customer not found. Please create new account with this Email.");
        }

        return reservationService.reserveARoom(customer,room, checkInDate, CheckOutDate);
    }

    public Collection<Reservation> getCustomersReservations(String customerEmail) throws HotelReservationException {
        Customer customer = customerService.getCustomer(customerEmail);

        if (customer == null) {
            throw new HotelReservationException("Customer not found. Please create new account with this Email.");
        }

        return reservationService.getCustomersReservation(customer);
    }

    public Collection<IRoom> findARoom(Date checkIn, Date checkOut) {
        return reservationService.findRooms(checkIn, checkOut);
    }

}
