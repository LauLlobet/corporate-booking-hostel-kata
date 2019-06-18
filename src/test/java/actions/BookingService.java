package actions;

import model.RoomOfKindDontExistForHotel;

import java.util.Date;

public class BookingService {
    private HotelService hotelsService;

    public BookingService(HotelService hotelsService) {
        this.hotelsService = hotelsService;
    }

    public void book(Long employeeId, int hotelId, String roomType, Date checkIn, Date checkOut) throws RoomOfKindDontExistForHotel {
        if(! hotelsService.findHotelBy(hotelId).stream().anyMatch(x-> x.getRoomType().equals(roomType))){
            throw new RoomOfKindDontExistForHotel();
        }
        throw new UnsupportedOperationException();
    }
}
