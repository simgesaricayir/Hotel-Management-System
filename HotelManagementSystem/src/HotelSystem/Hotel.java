package HotelSystem;

import java.io.FileWriter;
import java.io.IOException;
public class Hotel {

    private String hotelName;
    private int numberOfRooms;
    private int numberOfRecept;
    private int fullRoom;

    /**
     *  No parameter constructor with defacult inits
     */

    public Hotel(){
        this.hotelName = "boutique hotel";
        this.numberOfRecept = 2;
        this.numberOfRooms = 10;
        this.fullRoom = 0;

    }

    /* error handling eksik */

    /**
     *
     * @param name  Hotel's name with String type
     * @param receptNum receptionist number in the hotel with int
     */
    public Hotel(String name,int receptNum){
        this.hotelName = name;
        this.numberOfRecept = receptNum;
    }

    //GETTERS

    /**
     *
     * @return hotel's name
     */
    public String getHotelName(){
        return hotelName;
    }

    /**
     *
     * @return number of rooms in the hotel
     */
    public int getNumberOfRooms(){
        return numberOfRooms;
    }

    /**
     *
     * @return number of receptionist in the hotel
     */
    public int getNumberOfRecept(){
        return numberOfRecept;
    }

    /**
     *
     * @return number of full room in the hotel
     */
    public int getFullRoom(){
        return fullRoom;
    }

    /**
     *
     * @param newNum for setting updated number of full rooms in the hotel
     */

    public void setFullRoom(int newNum){
        if(newNum>0)
            this.fullRoom = newNum;
    }

    /**
     *
     * @param numberOfRooms for setting number of rooms in the hotel after reading csv file
     */
    public void setNumberOfRooms(int numberOfRooms){
        if(numberOfRooms>0)
            this.numberOfRooms=numberOfRooms;
    }



}
