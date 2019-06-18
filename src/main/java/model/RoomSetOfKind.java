package model;

import java.util.Objects;

public class RoomSetOfKind {
    private final String roomType;
    private final int quantity;

    public RoomSetOfKind(String roomKind, int quantity) {
        this.roomType = roomKind;
        this.quantity = quantity;
    }

    public String getRoomType() {
        return roomType;
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
                Objects.equals(roomType, that.roomType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomType, quantity);
    }
}
