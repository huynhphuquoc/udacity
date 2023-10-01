package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Data {
    public static Map<String, Customer> customers = new HashMap<>();
    public static List<IRoom> rooms = new ArrayList<>();
    public static List<Reservation> reservations = new ArrayList<>();

}
