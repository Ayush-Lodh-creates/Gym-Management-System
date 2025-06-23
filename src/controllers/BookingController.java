package controllers;

import entities.User;
import models.WorkoutsDTO;
import services.BookingService;

import java.util.List;

public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public String createBooking(User user, String gymId, String workoutId, String slotId, String date) throws Exception {
        return bookingService.createBooking(user, gymId, workoutId, slotId, date);
    }

    public List<WorkoutsDTO> getAllWorkoutsForADay(String date) throws Exception {
        return bookingService.getAllWorkoutsForDay(date);
    }
}
