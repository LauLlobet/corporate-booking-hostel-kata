package actions;

import model.Hotels;
import model.errors.RoomOfKindDontExistForHotel;
import model.RoomSetOfKind;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HotelAvailabilityFeature {

    private Long CORRECT_HOTEL_ID = 1l;
    private Long ANOTHER_HOTEL_ID = 5000l;

    @Test
    public void store_describe_and_override_hotels_to_prevent_unexisting_room_bookings() {
        HotelsCheckAndChange hotelsCheckAndChange = new HotelsCheckAndChange(new Hotels());

        hotelsCheckAndChange.setRoomType(CORRECT_HOTEL_ID,"suite",400);
        hotelsCheckAndChange.setRoomType(CORRECT_HOTEL_ID,"suite",2);
        hotelsCheckAndChange.setRoomType(CORRECT_HOTEL_ID,"normal",3);
        hotelsCheckAndChange.setRoomType(ANOTHER_HOTEL_ID,"normal",4);

        List<RoomSetOfKind> foundHotels = hotelsCheckAndChange.findHotelBy(CORRECT_HOTEL_ID);

        assertThat(foundHotels,
                containsInAnyOrder(
                        new RoomSetOfKind("suite",2),
                        new RoomSetOfKind("normal",3)) );

        assertThrows(RoomOfKindDontExistForHotel.class, () -> {
            BookRoom bookRoom = new BookRoom(hotelsCheckAndChange, null);
            bookRoom.book(null,CORRECT_HOTEL_ID,"double",null,null);
        });

        assertThrows(UnsupportedOperationException.class, () -> {
            BookRoom bookRoom = new BookRoom(hotelsCheckAndChange, null);
            bookRoom.book(null,CORRECT_HOTEL_ID,"suite",null,null);
        });
    }
}
