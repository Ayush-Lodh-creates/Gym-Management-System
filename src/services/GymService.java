package services;

import entities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class GymService {

    private List<UserSlot> userSlots;
    private CenterService centerService;
    private AtomicInteger userSlotIdCounter;

    public GymService(CenterService centerService) {
        this.userSlots = new ArrayList<>();
        this.centerService = centerService;
        this.userSlotIdCounter = new AtomicInteger(1);
    }

    public Boolean checkAvailability(String gymId, String workoutId, String slotId, String date) {
        List<UserSlot> slotsFromCurrentDate = userSlots.stream().filter(userSlot -> userSlot.getDate().equalsIgnoreCase(date)).toList();
        List<UserSlot> slotsFromCurrentCentre = slotsFromCurrentDate.stream().filter(userSlot -> userSlot.getCenter().getCenterId().equalsIgnoreCase(gymId)).toList();
        List<UserSlot> slotsFromCurrentWorkout = slotsFromCurrentCentre.stream().filter(userSlot -> userSlot.getWorkout().getId().equalsIgnoreCase(workoutId)).toList();
        List<UserSlot> slotsFromCurrentSlots = slotsFromCurrentWorkout.stream().filter(userSlot -> userSlot.getSlot().getSlotId().equalsIgnoreCase(slotId)).toList();
        if(slotsFromCurrentSlots.size() == 3) {
            return false;
        }
        return true;
    }

    public Boolean checkAlreadyBookedSession(String userId, String date) {
        List<UserSlot> slotsFromCurrentDate = userSlots.stream().filter(userSlot -> userSlot.getDate().equalsIgnoreCase(date)).toList();
        List<UserSlot> slotsFromCurrentUser = slotsFromCurrentDate.stream().filter(userSlot -> userSlot.getUser().getUserId().equalsIgnoreCase(userId)).toList();
        if(slotsFromCurrentUser.isEmpty()) {
            return false;
        }
        return true;
    }

    public UserSlot saveBooking(String gymId, String workoutId, String slotId, String date, User user) throws Exception {
        if(!checkAvailability(gymId, workoutId, slotId, date)) {
            throw new Exception("No available slots left for current details");
        }
        if(checkAlreadyBookedSession(user.getUserId(), date)) {
            throw new Exception("User has already booked a session");
        }
        Center center = centerService.getCenterDetails(gymId);
        Workout workout = centerService.getWorkoutDetails(workoutId);
        Slot slot = centerService.getSlotDetails(slotId);
        Integer id = userSlotIdCounter.getAndIncrement();
        UserSlot userSlot = new UserSlot(id, user, center, workout, slot, date);
        userSlots.add(userSlot);
        return userSlot;
    }
}
