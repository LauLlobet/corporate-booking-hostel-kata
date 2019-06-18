package actions;

import model.Hotels;
import model.RoomSetOfKind;

import java.util.List;

public class HotelsCheckAndChange {
    private Hotels hotels;

    public HotelsCheckAndChange(Hotels hotels) {
        this.hotels = hotels;
    }

    public List<RoomSetOfKind> findHotelBy(int hotelId) {
        return hotels.getBy(hotelId);
    }

    public void setRoomType(int hotelId, String kindOfRoom, int numberOfRooms) {
        hotels.upsert(hotelId,new RoomSetOfKind(kindOfRoom,numberOfRooms));
    }
}
