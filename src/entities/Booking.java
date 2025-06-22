package entities;

public class Booking {

    private int bookingId;
    private UserSlot userSlot;

    public Booking(int bookingId, UserSlot userSlot) {
        this.bookingId = bookingId;
        this.userSlot = userSlot;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public UserSlot getUserSlot() {
        return userSlot;
    }

    public void setUserSlot(UserSlot userSlot) {
        this.userSlot = userSlot;
    }
}
