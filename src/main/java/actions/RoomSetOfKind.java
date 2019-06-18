package actions;

import java.util.Collection;
import java.util.Objects;

public class RoomSetOfKind {
    private final String suite;
    private final int quantity;

    public RoomSetOfKind(String suite, int quantity) {
        this.suite = suite;
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomSetOfKind that = (RoomSetOfKind) o;
        return quantity == that.quantity &&
                Objects.equals(suite, that.suite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(suite, quantity);
    }
}
