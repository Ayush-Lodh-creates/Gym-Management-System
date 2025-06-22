package entities;

import java.util.List;

public class Center {

    private String centerId;
    private String centerName;
    private List<Workout> workouts;

    public String getCenterId() {
        return centerId;
    }

    public void setCenterId(String centerId) {
        this.centerId = centerId;
    }

    public List<Workout> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(List<Workout> workouts) {
        this.workouts = workouts;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public Center(String centerId, String centerName) {
        this.centerId = centerId;
        this.centerName = centerName;
    }
}
