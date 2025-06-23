package controllers;

import entities.User;
import models.WorkoutsDTO;
import services.BookingService;

public class UserController {

    private BookingService bookingService;

    public UserController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public User registerUser(String name, String email, Long phone, String password) {
        User user = new User(name, email, phone, password);
        user.setUserId(email);
        return user;
    }

    public WorkoutsDTO getUserPlanForADay(User user, String date) {
        return bookingService.getUserWorkoutDetailsForDay(user, date);
    }
}
