package HotelSystem;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Receptionist  extends Guest {
    private String  receptName;
    private int password=1;

    /**
     *
     * @param name of Receptinoist
     */
    public Receptionist(String name){
        this.receptName = name;
    }

    /**
     * @return instance password for checking privacy
     */
    public int getPassword(){
      return password;
    }


    /**
     *
     * @return receptionist name
     */
    @Override
    public String toString() {
        return "Receptionist name: "+receptName;
    }

    /**
     *
     * @param newRoom Room class object that is gonna be useful for check the right room from the comparing csv
     * @param hotelOb Hotel class's object for reading and writing to csv , update database
     */

    public void checkIn(Room newRoom,Hotel hotelOb,String filename){

        int i=0,equal=-1;
        try {
            readData(hotelOb,filename);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while(i<roomList.size()-1){
            if((roomList.get(i).getRoomType()).equals(newRoom.getRoomType()) && "Reserved".equals(roomList.get(i).getState())) {
                if(roomList.get(i).getPhone().equals(newRoom.getPhone())) {
                    equal = i;
                    break;
                }
            }
            i++;
        }
        //if there is no suitable room
        if(equal==-1){
            i=0;
            while(i<roomList.size()-1){
                if((roomList.get(i).getRoomType()).equals(newRoom.getRoomType()) && newRoom.getState().equals(roomList.get(i).getState())) {
                    equal = i;
                    break;
                }
                i++;
            }
        }
        if(equal!=-1){
            newRoom.setState("Check-in");
            newRoom.setRoomID(roomList.get(equal).getRoomID());
            roomList.set(equal,newRoom);
            hotelOb.setFullRoom(hotelOb.getFullRoom()+1);
            writeData(filename);
            System.out.print(newRoom.getName());
            System.out.println(" CHECK-IN SUCCESSFUL!!");
            System.out.print("NUMBER OF EMPTY ROOM:");
            System.out.println(hotelOb.getNumberOfRooms()-hotelOb.getFullRoom());
        }
        else{
            System.out.print(newRoom.getName());
            System.out.println(" THERE İS NO SUITABLE ROOM FOR YOUR CHECK-IN...");
        }

    }

    /**
     *
     * @param outRoom   Room class's object that holds outing room's and its guest's information
     * @param hotelOb   Hotel class's object for calling readData method to updating database
     * @param roomID    id for check-out : check the right room or not
     * @param filename  filename for reading and writing database with name of file and update csv file after the processing
     */
    public void checkOut(Room outRoom, Hotel hotelOb,int roomID,String filename) {

        int i=0,equal=-1;
        try {
            readData(hotelOb,filename);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while(i<roomList.size()-1){
            if((roomList.get(i).getName()).equals(outRoom.getName()) && (roomList.get(i).getPhone()).equals(outRoom.getPhone())){
                if(roomList.get(i).getRoomID()==roomID && (roomList.get(i).getState().equals("Check-in"))) {
                    equal = i;
                    break;
                }
            }
            i++;
        }
        if(equal==-1){
            System.out.print(outRoom.getName());
            System.out.println(" YOU DON'T HAVE A ROOM TO CHECK-OUT...");
        }
        else {
            payment(outRoom);
            outRoom.setState("Empty");
            outRoom.setPhone("0");
            outRoom.setName(" ");
            outRoom.setAge(0);
            outRoom.setOutDate("0000/00/00");
            outRoom.setInDate("0000/00/00");
            outRoom.setPriceOfRoom(roomList.get(equal).getPriceOfRoom());
            outRoom.setRoomID(roomList.get(equal).getRoomID());
            outRoom.setGender(" ");
            roomList.set(equal, outRoom);
            hotelOb.setFullRoom(hotelOb.getFullRoom()-1);
            writeData( filename);
            System.out.print(outRoom.getName());
            System.out.println(" YOUR CHECK-IN SUCCESSFFUL!!");
            System.out.print("NUMBER OF EMPTY ROOM:");
            System.out.println(hotelOb.getNumberOfRooms()-hotelOb.getFullRoom());
        }
    }

    /**
     * @param roomObject for calculating number of days room's guest to payment
     */
    private void payment(Room roomObject)  {
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy/MM/dd");
        String inDate = roomObject.getInDate();
        String outDate = roomObject.getOutDate();
        Date before,after;
        float daysBetween=0;
        try {
            before = myFormat.parse(inDate);
            after = myFormat.parse(outDate);
            long difference = after.getTime() - before.getTime();
            daysBetween = (difference / (1000*60*60*24));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.print("YOU MUST PAY YOUR ROOM PRİCE:");
        if(daysBetween==0){
            System.out.print(roomObject.getPriceOfRoom());
            System.out.println(" tl");
        }
        else{
            System.out.print(roomObject.getPriceOfRoom()*daysBetween);
            System.out.println(" tl");

        }

    }


}
