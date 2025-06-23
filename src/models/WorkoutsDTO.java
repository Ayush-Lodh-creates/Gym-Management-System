package models;

public class WorkoutsDTO {

    private String centerId;
    private String centerName;
    private String workoutId;
    private String workoutName;
    private String slotId;
    private String slotTimings;
    private int slotsLeft;

    public WorkoutsDTO() {
        this.centerId = null;
        this.centerName = null;
        this.workoutId = null;
        this.workoutName = null;
        this.slotId = null;
        this.slotTimings = null;
        this.slotsLeft = 0;
    }

    public WorkoutsDTO(String centerId, String centerName, String workoutId, String workoutName, String slotId, String slotTimings, int slotsLeft) {
        this.centerId = centerId;
        this.centerName = centerName;
        this.workoutId = workoutId;
        this.workoutName = workoutName;
        this.slotId = slotId;
        this.slotTimings = slotTimings;
        this.slotsLeft = slotsLeft;
    }

    public String getCenterId() {
        return centerId;
    }

    public void setCenterId(String centerId) {
        this.centerId = centerId;
    }

    public String getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(String workoutId) {
        this.workoutId = workoutId;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public String getSlotId() {
        return slotId;
    }

    public void setSlotId(String slotId) {
        this.slotId = slotId;
    }

    public String getSlotTimings() {
        return slotTimings;
    }

    public void setSlotTimings(String slotTimings) {
        this.slotTimings = slotTimings;
    }

    public int getSlotsLeft() {
        return slotsLeft;
    }

    public void setSlotsLeft(int slotsLeft) {
        this.slotsLeft = slotsLeft;
    }
}
