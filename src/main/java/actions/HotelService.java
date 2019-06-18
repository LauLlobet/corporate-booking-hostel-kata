package actions;

import model.Hotels;

import java.util.List;

public class HotelService {
    private Hotels hotels;

    public HotelService(Hotels hotels) {
        this.hotels = hotels;
    }

    public List<RoomSetOfKind> findHotelBy(int hotelId) {
        return hotels.getBy(hotelId);
    }

    public void setRoomType(int hotelId, String kindOfRoom, int numberOfRooms) {
        hotels.upsert(hotelId,new RoomSetOfKind(kindOfRoom,numberOfRooms));
    }
}
