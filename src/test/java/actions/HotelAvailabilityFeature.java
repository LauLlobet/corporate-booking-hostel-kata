package actions;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HotelAvailabilityFeature {

    private int CORRECT_HOTEL_ID = 1;
    private int ANOTHER_HOTEL_ID = 5000;

    @Test
    public void store_describe_and_override_hotels_to_prevent_unexisting_room_bookings() {
        HotelService hotelService = new HotelService();

        hotelService.setRoomType(CORRECT_HOTEL_ID,"suite",400);
        hotelService.setRoomType(CORRECT_HOTEL_ID,"suite",2);
        hotelService.setRoomType(CORRECT_HOTEL_ID,"normal",3);
        hotelService.setRoomType(ANOTHER_HOTEL_ID,"normal",4);

        assertThat(hotelService.findHotelBy(CORRECT_HOTEL_ID),
                containsInAnyOrder(
                        new RoomSetOfKind("suite",2),
                        new RoomSetOfKind("standard",3)) );

        assertThrows(RoomOfKindDontExistForHotel.class, () -> {
            BookingService bookingService = new BookingService();
            bookingService.book(null,CORRECT_HOTEL_ID,"double",null,null);
        });
    }
}
