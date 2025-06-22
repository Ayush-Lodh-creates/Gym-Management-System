package services;

import entities.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class BookingService {

    private Map<String, List<Booking>> bookings;
    private GymService gymService;
    private AtomicInteger bookingIdCounter;

    public BookingService(GymService gymService) {
        this.bookings = new HashMap<>();
        this.gymService = gymService;
        this.bookingIdCounter = new AtomicInteger(1);
    }

    public String createBooking(User user, String gymId, String workoutId, String slotId, String date) throws Exception {
        Boolean isAvailable = gymService.checkAvailability(gymId, workoutId, slotId, date);
        if(isAvailable) {
            throw new Exception("All slots have been booked");
        }
        UserSlot userSlot = gymService.saveBooking(gymId, workoutId, slotId, date, user);
        int bookingId = bookingIdCounter.getAndIncrement();
        Booking booking = new Booking(bookingId, userSlot);
        if(!bookings.containsKey(date)) {
            List<Booking> bookingList = new ArrayList<>();
            bookingList.add(booking);
            bookings.put(date, bookingList);
        }
        else {
            bookings.get(date).add(booking);
        }
        return Integer.toString(bookingId);
    }
}
