package HotelSystem;

import org.junit.Test;

import java.io.IOException;
import java.util.Scanner;

import static org.junit.Assert.*;

public class ReceptionistTest {
    Guest guest1 = new Guest("Ayşe","Female",30,"5350653944","2018/06/10","2018/06/12");
    Hotel hotel1 = new Hotel("testHotel",2);
    Room room1 = new Room("single","Empty",hotel1,guest1);
    Receptionist recept = new Receptionist("test recept ");


    @Test
    public void readData() {
        try {
            recept.readData(hotel1,"Unit_test.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void writeData() {
        try {
            recept.readData(hotel1,"Unit_test.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        recept.writeData("Unit_test.csv");
    }

    @Test
    public void bookingRoom() {
        recept.bookingRoom(room1,hotel1,"Unit_test.csv");
    }

    @Test
    public void canselBooking() {
        recept.canselBooking(room1,hotel1,1,"Unit_test.csv");
    }
    @Test
    public void checkIn() {
        recept.checkIn(room1,hotel1,"Unit_test.csv");
    }

    @Test
    public void checkOut() {
        recept.checkOut(room1,hotel1,3,"Unit_test.csv");
    }




}