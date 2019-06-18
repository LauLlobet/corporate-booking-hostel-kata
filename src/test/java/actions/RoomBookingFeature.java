package actions;

import model.Hotels;
import model.errors.RoomOfKindDontExistForHotel;
import org.junit.jupiter.api.Test;

import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

public class RoomBookingFeature {

    private HotelsCheckAndChange hotelsCheckAndChange;
    BookingPolicyService bookingPolicyService;
    private Hotels hotels = new Hotels();
    private BookRoom bookRoom;
    private CompanyService companyService = new CompanyService();

    /*
    void setCompanyPolicy(Long companyId, <?> roomTypes);

            void setEmployeePolicy(Long employeeId, <?> roomTypes);
  public class CompanyService {

        // Collaborators(?)

        void addEmployee(Long companyId, Long employeeId);

        void deleteEmployee(Long employeeId);

    }
     */
    @Test
    void allow_room_booking_when_possible() throws RoomOfKindDontExistForHotel {
        hotelsCheckAndChange = new HotelsCheckAndChange(hotels);
        bookingPolicyService = new BookingPolicyService(companyService);
        bookRoom = new BookRoom(hotelsCheckAndChange,bookingPolicyService);

        companyService.addEmployee(1234l,12340000l);
        companyService.addEmployee(1234l,10203040l); //will be deleted to check he cannot book
        companyService.addEmployee(1234l,100200300); //will be deleted to free up bookings made by him
        companyService.deleteEmployee(100200300l);

        //HotelAvailablity logioc
        hotelsCheckAndChange.setRoomType(200l,"suite",1);
        hotelsCheckAndChange.setRoomType(200l,"suite",2);
        hotelsCheckAndChange.setRoomType(300l,"suite",2);

        //Company policies logic
        bookingPolicyService.setEmployeePolicy(12340000l,"suite",true);
        bookingPolicyService.setCompanyPolicy(10203040l,"suite",false);

        //Booking schedule logic
        assertTrue(bookRoom.book(12340000l, 200l, "suite",
                new GregorianCalendar(2001, 1, 1).getTime(), new GregorianCalendar(2002, 2, 2).getTime()));

        assertTrue(bookRoom.book(12340000l, 200l, "suite",
                new GregorianCalendar(2001, 1, 1).getTime(), new GregorianCalendar(2002, 2, 2).getTime()));

        assertFalse(bookRoom.book(12340000l, 200l, "suite",
                new GregorianCalendar(2001, 1, 1).getTime(), new GregorianCalendar(2002, 2, 2).getTime()));

        assertTrue(bookRoom.book(12340000l, 200l, "suite",
                new GregorianCalendar(2002, 2, 3).getTime(), new GregorianCalendar(2002, 2, 3).getTime()));

        assertTrue(bookRoom.book(12340000l, 300l, "suite",
                new GregorianCalendar(2001, 1, 1).getTime(), new GregorianCalendar(2002, 2, 2).getTime()));

        assertThrows(UnexistentEmployee.class, () -> {
            bookRoom.book(100200300l, 300l, "unexisting_room_type", new GregorianCalendar(2001, 1, 1).getTime(), new GregorianCalendar(2002, 2, 2).getTime());
        });

        assertThrows(ForbiddenByCompanyPolicy.class, () -> {
            bookRoom.book(102030l, 300l, "suite", new GregorianCalendar(2003, 3, 3).getTime(), new GregorianCalendar(2004, 4, 4).getTime());
        });

        assertThrows(RoomOfKindDontExistForHotel.class, () -> {
            bookRoom.book(1234l, 300l, "unexisting_room_type", new GregorianCalendar(2001, 1, 1).getTime(), new GregorianCalendar(2002, 2, 2).getTime());
        });
    }
}
