package entities;

public class Slot {

    private String slotId;
    private String slotTiming;
    private Integer remainingSlots;

    public Slot(String slotId, String slotTiming) {
        this.slotId = slotId;
        this.slotTiming = slotTiming;
        this.remainingSlots = 3;
    }

    public String getSlotId() {
        return slotId;
    }

    public void setSlotId(String slotId) {
        this.slotId = slotId;
    }

    public String getSlotTiming() {
        return slotTiming;
    }

    public void setSlotTiming(String slotTiming) {
        this.slotTiming = slotTiming;
    }
}
