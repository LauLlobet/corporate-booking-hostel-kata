package actions;

import java.util.Objects;

public class RoomSetOfKind {
    private final String roomKind;
    private final int quantity;

    public RoomSetOfKind(String roomKind, int quantity) {
        this.roomKind = roomKind;
        this.quantity = quantity;
    }

    public String getRoomType() {
        return roomKind;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomSetOfKind that = (RoomSetOfKind) o;
        return quantity == that.quantity &&
                Objects.equals(roomKind, that.roomKind);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomKind, quantity);
    }
}
