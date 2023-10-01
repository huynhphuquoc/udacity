package service;

import model.Customer;
import model.FreeRoom;
import model.IRoom;
import model.Reservation;
import org.w3c.dom.ls.LSInput;

import java.util.*;
import java.util.stream.Collectors;

public class ReservationService {
    public void addRoom(final IRoom room) {
        Data.rooms.add(room);
    }

    public IRoom getARoom(final String roomNumber) {
        List<IRoom> room = Data.rooms.stream().map(r -> {
            if (r.getRoomNumber().equals(roomNumber)) {
                return r;
            }
            return null;
        }).collect(Collectors.toList());

        if(!room.isEmpty()) {
            return room.get(0);
        } else {
            return null;
        }
    }

    public Collection<IRoom> getAllRooms() {
        return Data.rooms;
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date  checkInDate, Date checkOutDate) {
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        Data.reservations.add(reservation);
      return reservation;
    }

    public Collection<Reservation> getCustomersReservation(Customer customer) {
        return Data.reservations.stream().map(reservation -> {
            if (reservation.getCustomer().getEmail().equals(customer.getEmail())) {
                return reservation;
            }
            return null;
        }).collect(Collectors.toList());
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        // get list room has reserve.
        List<String> reserveRoom = findReserveRoom(checkInDate, checkOutDate);

        if (reserveRoom.isEmpty()) {
            return Data.rooms;
        }

        // Get free room.
        return Data.rooms.stream().map(room ->{
            if (!reserveRoom.contains(room.getRoomNumber())) {
                return room;
            }
            return null;
        }).collect(Collectors.toList());
    }

    public void printAllReservation() {
        Data.reservations.forEach(reservation -> System.out.println(reservation));
    }

    private List<String> findReserveRoom(final Date startDate, final Date endDate) {
        List<String> reserveRoom = new ArrayList<>();
        Data.reservations.stream().forEach( reservation -> {
            if(reservation.getCheckInDate().compareTo(startDate) >= 0
                    && reservation.getCheckOutDate().compareTo(endDate) <= 0
            ) {
                reserveRoom.add(reservation.getRoom().getRoomNumber());
            }
        });

        return reserveRoom;
    }
}
