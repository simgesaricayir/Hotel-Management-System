package HotelSystem;

import java.util.Scanner;

public class Room {

    private int roomID;
    private String type;
    private String state;
    private int priceOfRoom;
    private String name;
    private String gender;
    private String phone;
    private int age;
    private String InDate, outDate;

    /**
     *
     */
    public Room(){
        this.type = "single";
        this.state = state;
        this.priceOfRoom = 50;

    }

    /**
     *
     * @param state of room (empty,checkın,booked)
     * @param hotelName name of hotel
     * @param guestOb Guest class's object for initilization of room's guest information
     */
    public Room(String type,String state, Hotel hotelName, Guest guestOb){

        this.type = type;
        this.state = state;
        this.priceOfRoom = 50;
        this.age=guestOb.getAge();
        this.name=guestOb.getName();
        this.phone=guestOb.getPhone();
        this.gender=guestOb.getGender();
        this.InDate=guestOb.getInDate();
        this.outDate=guestOb.getOutDate();
    }
    /**
     *
     * @return ID of room
     */
    public int getRoomID(){
        return roomID;
    }

    /**
     *
     * @return type of room
     */
    public String getRoomType(){
        return type;
    }

    /**
     *
     * @return type of room's state(empty,reserved or full)
     */
    public String getState(){
        return state;
    }

    /**
     *
     * @return price of each room
     */
    public int getPriceOfRoom(){
        return priceOfRoom;
    }

    /**
     *
     * @return name of room's guest
     */
    public String getName(){
        return name;
    }

    /**
     *
     * @return age of room's guest
     */
    public int getAge(){
        return age;
    }

    /**
     *
     * @param state set state of room for changing (reserved to empty)
     */
    public void setState(String state){
        this.state = state;
    }

    /**
     *
     * @param type set type of room(single or double)
     */
    public void setType(String type){
        this.type = type;
    }

    /**
     *
     * @param name set name for room's guest
     */
    public void setName(String name){
        this.name=name;
    }

    /**
     *
     * @param gender set gender for room's guest
     */
    public void setGender(String gender){
        this.gender=gender;
    }
    public void setAge(int age){
        this.age = age;
    }
    public void setPhone(String phone){
        this.phone=phone;
    }
    public void setRoomID(int roomID){
        this.roomID=roomID;
    }

    public void setInDate(String inDate) {
        this.InDate = inDate;
    }

    public void setOutDate(String outDate) {
        this.outDate = outDate;
    }

    public void setPriceOfRoom(int priceOfRoom) {
        this.priceOfRoom=priceOfRoom;

    }

    public String getPhone() {
        return phone;
    }

    public String getOutDate() {
        return outDate;
    }

    public String getGender() {
        return gender;
    }
    public String getInDate(){
        return InDate;
    }
}

