package entities;

public class UserSlot {

    private Integer id;
    private User user;
    private Center center;
    private Workout workout;
    private Slot slot;
    private String date;

    public UserSlot(Integer id, User user, Center center, Workout workout, Slot slot, String date) {
        this.id = id;
        this.user = user;
        this.center = center;
        this.workout = workout;
        this.slot = slot;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Workout getWorkout() {
        return workout;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    public Center getCenter() {
        return center;
    }

    public void setCenter(Center center) {
        this.center = center;
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
