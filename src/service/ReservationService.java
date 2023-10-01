package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;
import java.util.stream.Collectors;

public class ReservationService {

    public void addRoom(final IRoom room) {
        rooms.put(room.getRoomNumber(), room);
    }

    public IRoom getARoom(final String roomNumber) {
        return rooms.get(roomNumber);
    }

    public List<IRoom> getAllRooms() {
        return rooms.values().stream().toList();
    }

    public Reservation reserveARoom(Customer customer, IRoom room,
                                    Date checkInDate, Date checkOutDate) {
        final Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);

        List<Reservation> customerReservations = getCustomersReservation(customer);

        if (customerReservations == null) {
            customerReservations = new LinkedList<>();
        }

        customerReservations.add(reservation);
        reservations.put(customer.getEmail(), customerReservations);

        return reservation;
    }

    public List<IRoom> findRooms(final Date checkInDate, final Date checkOutDate) {
        return findAvailableRooms(checkInDate, checkOutDate);
    }

    public List<IRoom> findAlternativeRooms(final Date checkInDate, final Date checkOutDate) {
        return findAvailableRooms(addDefaultPlusDays(checkInDate), addDefaultPlusDays(checkOutDate));
    }



    public Date addDefaultPlusDays(final Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, RECOMMENDED_ROOMS_DEFAULT_PLUS_DAYS);

        return calendar.getTime();
    }


    public List<Reservation> getCustomersReservation(final Customer customer) {
        return reservations.get(customer.getEmail());
    }

    public void printAllReservation() {
        final List<Reservation> reservations = getAllReservations();

        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
        } else {
            for (Reservation reservation : reservations) {
                System.out.println(reservation + "\n");
            }
        }
    }

    private List<Reservation> getAllReservations() {
        final List<Reservation> allReservations = new LinkedList<>();

        for(List<Reservation> reservations : reservations.values()) {
            allReservations.addAll(reservations);
        }

        return allReservations;
    }
    private static final int RECOMMENDED_ROOMS_DEFAULT_PLUS_DAYS = 7;

    private final Map<String, IRoom> rooms = new HashMap<>();
    private final Map<String, List<Reservation>> reservations = new HashMap<>();

    public ReservationService() {}
    private List<IRoom> findAvailableRooms(final Date checkInDate, final Date checkOutDate) {
        final List<Reservation> allReservations = getAllReservations();
        final List<IRoom> notAvailableRooms = new LinkedList<>();

        for (Reservation reservation : allReservations) {
            if (reservationOverlaps(reservation, checkInDate, checkOutDate)) {
                notAvailableRooms.add(reservation.getRoom());
            }
        }

        return rooms.values().stream().filter(room -> notAvailableRooms.stream()
                        .noneMatch(notAvailableRoom -> notAvailableRoom.equals(room)))
                .collect(Collectors.toList());
    }
    private boolean reservationOverlaps(final Reservation reservation, final Date checkInDate,
                                        final Date checkOutDate){
        return checkInDate.before(reservation.getCheckOutDate())
                && checkOutDate.after(reservation.getCheckInDate());
    }

}
