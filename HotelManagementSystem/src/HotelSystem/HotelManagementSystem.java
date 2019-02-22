package HotelSystem;

import java.io.FileNotFoundException;
import java.io.IOException;


public interface HotelManagementSystem {

    /**
     *
     * @param newRoom Room class's object that is gonna use for book a room and hold it room's information.
     * @param hotelOb   Hotel class's object that holds number of rooms and full rooms.
     * @param filename  file name for reading and writing data to csv file for hold data
     * @throws IOException  exception that readData or writeData methods throws
     */
    void bookingRoom(Room newRoom,Hotel hotelOb,String filename) throws IOException;

    /**
     *
     * @param deleteRoom Room class's object that holds canseling room's and its guest's information
     * @param hotelOb Hotel class's object for calling readData method to updating database
     * @param roomId id for cansel operations : check the right room or not
     * @param filename  filename for reading database with name of file from the file
     * @return  return value is 1 for right situation, otherwise it is -1 for checking process
     * @throws IOException  exception that readData or writeData methods throws
     */
    int canselBooking(Room deleteRoom,Hotel hotelOb,int roomId,String filename) throws IOException;

    /**
     *
     * @param filename filename for update database on the file
     */
    void writeData(String filename);

    /**
     *
     * @param hotelOb Hotel class's object that sets hotel's number of room's and full room's
     * @param filename filename for reading database with name of file from the file
     * @throws IOException   exception that readData or writeData methods throws
     */
    void readData(Hotel hotelOb,String filename) throws IOException;

}
