package actions;

import model.errors.RoomOfKindDontExistForHotel;

import java.util.Date;

public class BookRoom {
    private HotelsCheckAndChange hotelsService;

    public BookRoom(HotelsCheckAndChange hotelsService, BookingPolicyService bookingPolicyService) {
        this.hotelsService = hotelsService;
    }

    public boolean book(Long employeeId, Long hotelId, String roomType, Date checkIn, Date checkOut) throws RoomOfKindDontExistForHotel {
        if(roomOfTypeDosentExistFor(hotelId, roomType)){
            throw new RoomOfKindDontExistForHotel();
        }
        throw new UnsupportedOperationException();
    }

    private boolean roomOfTypeDosentExistFor(Long hotelId, String roomType) {
        return hotelsService.findHotelBy(hotelId).stream().noneMatch(x-> x.getRoomType().equals(roomType));
    }
}
