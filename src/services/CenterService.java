package services;

import entities.Center;
import entities.Slot;
import entities.Workout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class CenterService {

    private Map<String, Center> centers;
    private Map<String, Workout> workouts;
    private Map<String, Slot> slots;
    private AtomicInteger centerIdCounter;
    private AtomicInteger workoutIdCounter;
    private AtomicInteger slotIdCounter;

    public CenterService() {
        centers = new HashMap<>();
        workouts = new HashMap<>();
        slots = new HashMap<>();
    }

    public String createCenter(String name) {
        String centerId = Integer.toString(centerIdCounter.getAndIncrement());
        Center center = new Center(centerId, name);
        centers.put(centerId, center);
        return centerId;
    }

    public Center getCenterDetails(String id) throws Exception {
        if(!centers.containsKey(id)) {
            throw new Exception("This center does not exist");
        }
        return centers.get(id);
    }

    public void addWorkouts(String centerId, List<Workout> workouts) throws Exception {
        Center center = getCenterDetails(centerId);
        center.setWorkouts(workouts);
    }

    public String createWorkout(String name) {
        String workoutId = Integer.toString(workoutIdCounter.getAndIncrement());
        Workout workout = new Workout(workoutId, name);
        workouts.put(workoutId, workout);
        return workoutId;
    }

    public Workout getWorkoutDetails(String id) throws Exception {
        if(!workouts.containsKey(id)) {
            throw new Exception("This workout does not exist");
        }
        return workouts.get(id);
    }

    public void addSlotsToWorkout(String workoutId, List<Slot> slots) throws Exception {
        Workout workout = getWorkoutDetails(workoutId);
        workout.setSlots(slots);
    }

    public String createSlot(String name) {
        String slotId = Integer.toString(slotIdCounter.getAndIncrement());
        Slot slot = new Slot(slotId, name);
        slots.put(slotId, slot);
        return slotId;
    }

    public Slot getSlotDetails(String id) throws Exception {
        if(!slots.containsKey(id)) {
            throw new Exception("This slot does not exist");
        }
        return slots.get(id);
    }

}
