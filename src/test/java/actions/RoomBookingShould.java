package actions;

import model.RoomSetOfKind;
import model.errors.RoomOfKindDontExistForHotel;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class RoomBookingShould {

    private static final Date CHECK_OUT_PLUS_ONE_DAY = new Date(2002, 2, 3);
    private static final Long OTHER_HOTEL_ID = 300l;
    private Long EMPLOYEE_ID = 100l;
    private Long HOTEL_ID = 200l;
    private String ROOM_TYPE = "suite";
    private Date CHECK_IN = new Date(2001, 1, 1);
    private Date CHECK_OUT = new Date(2002, 2, 2);
    @Mock
    private HotelsCheckAndChange hotelsCheckAndChange;

    @Mock
    BookingPolicyService bookingPolicyService;

    @Test
    void allow_room_booking_when_possible() throws RoomOfKindDontExistForHotel {
        BookRoom bookRoom = new BookRoom(hotelsCheckAndChange,bookingPolicyService);

        when(hotelsCheckAndChange.findHotelBy(HOTEL_ID))
                .thenReturn(Collections.singletonList(new RoomSetOfKind("suite", 3)));

        when(bookingPolicyService.isBookingAllowed(EMPLOYEE_ID,ROOM_TYPE)).thenReturn(true);

        assertTrue(bookRoom.book(EMPLOYEE_ID, HOTEL_ID, ROOM_TYPE, CHECK_IN, CHECK_OUT));
        assertTrue(bookRoom.book(EMPLOYEE_ID, HOTEL_ID, ROOM_TYPE, CHECK_IN, CHECK_OUT));
        assertTrue(bookRoom.book(EMPLOYEE_ID, HOTEL_ID, ROOM_TYPE, CHECK_IN, CHECK_OUT));
        assertFalse(bookRoom.book(EMPLOYEE_ID, HOTEL_ID, ROOM_TYPE, CHECK_IN, CHECK_OUT));

        assertTrue(bookRoom.book(EMPLOYEE_ID, OTHER_HOTEL_ID, ROOM_TYPE, CHECK_IN, CHECK_OUT));
        assertTrue(bookRoom.book(EMPLOYEE_ID, HOTEL_ID, ROOM_TYPE,CHECK_OUT_PLUS_ONE_DAY,CHECK_OUT_PLUS_ONE_DAY));
    }
}
