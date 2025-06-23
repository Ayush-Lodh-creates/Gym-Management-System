package services;

import entities.*;
import models.WorkoutsDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class BookingService {

    private Map<String, List<Booking>> bookings;
    private GymService gymService;
    private CenterService centerService;
    private AtomicInteger bookingIdCounter;

    public BookingService(GymService gymService, CenterService centerService) {
        this.bookings = new HashMap<>();
        this.gymService = gymService;
        this.centerService = centerService;
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

    public List<WorkoutsDTO> getAllWorkoutsForDay(String date) throws Exception {
        List<Booking> bookingsForDay = bookings.get(date);
        List<Center> allCenters = centerService.getAllCenters();
        List<WorkoutsDTO> workoutsDTOList = new ArrayList<>();
        for(Center center : allCenters) {
            List<Workout> allWorkoutsForCenter = centerService.getAllWorkoutsForCenter(center.getCenterId());
            for(Workout workout : allWorkoutsForCenter) {
                List<Slot> allSlotsForWorkout = centerService.getAllSlotsForWorkout(workout.getId());
                for(Slot slot : allSlotsForWorkout) {
                    List<Booking> filteredBooking = bookingsForDay.stream().filter(booking -> {
                        UserSlot userSlot = booking.getUserSlot();
                        return userSlot.getCenter().getCenterId().equalsIgnoreCase(center.getCenterId()) &&
                                userSlot.getWorkout().getId().equalsIgnoreCase(workout.getId()) &&
                                userSlot.getSlot().getSlotId().equalsIgnoreCase(slot.getSlotId());
                    }).toList();
                    WorkoutsDTO workoutsDTO = new WorkoutsDTO(center.getCenterId(), center.getCenterName(), workout.getId(), workout.getName(), slot.getSlotId(), slot.getSlotTiming(), 3 - filteredBooking.size());
                    workoutsDTOList.add(workoutsDTO);
                }
            }
        }
        return workoutsDTOList;
    }

    public WorkoutsDTO getUserWorkoutDetailsForDay(User user, String date) {
        List<Booking> bookingList = bookings.get(date);
        for(Booking booking : bookingList) {
            UserSlot userSlot = booking.getUserSlot();
            if(userSlot.getUser().getUserId().equalsIgnoreCase(user.getUserId())) {
                return new WorkoutsDTO(userSlot.getCenter().getCenterId(), userSlot.getCenter().getCenterName(), userSlot.getWorkout().getId(),
                        userSlot.getWorkout().getName(), userSlot.getSlot().getSlotId(), userSlot.getSlot().getSlotTiming(), 1);
            }
        }
        return new WorkoutsDTO();
    }
}
