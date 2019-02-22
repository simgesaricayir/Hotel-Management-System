package HotelSystem;

import com.sun.java.accessibility.util.GUIInitializedListener;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GuestTest {

    Guest guest1 = new Guest("Ayşe","Female",30,"5350653944","2018/06/10","2018/06/12");
    Hotel hotel1 = new Hotel("testHotel",2);
    Room room1 = new Room("single","Empty",hotel1,guest1);




    @Test
    public void writeData() {
        try {
            guest1.readData(hotel1,"Unit_test.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        guest1.writeData("Unit_test.csv");
    }

    @Test
    public void readData() {
        try {
            guest1.readData(hotel1,"Unit_test.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void bookingRoom() {
        guest1.bookingRoom(room1,hotel1,"Unit_test.csv");
    }

    @Test
    public void canselBooking() {
        room1.setState("Reserved");
        guest1.canselBooking(room1,hotel1,3,"Unit_test.csv");
    }


}