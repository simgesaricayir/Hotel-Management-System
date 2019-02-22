package HotelSystem;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Guest implements HotelManagementSystem {

    private int age;
    private String gender;
    private String phone;
    private String name;
    private String InDate, outDate;
    protected List<Room> roomList = new ArrayList<>();

    //Delimiter used in CSV file
    private static final String COMMA = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final String FILE_HEADER = "ID,State,Type,Price,In Date,Out Date,Gender,Name,Age,Phone";

    /**
     * No parameter constructor
     */
    public Guest() {
    }

    /**
     * Parameter constructor
     * Guest informations to keep records of guest when book , cansel methods worked
     * @param name for init instance string variable name
     * @param gender    for init instance int variable gender
     * @param age   for init instance int variable age
     * @param phone for init instance string variable phone
     * @param inDate    for init instance string variable in date to hotel
     * @param out   for init instance string variable name out date to hotel
     */

    public Guest(String name, String gender, int age, String phone, String inDate, String out) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.phone = phone;
        this.InDate = inDate;
        this.outDate = out;

    }

    @Override
    public String toString() {
        return "Name: " + getName() + "phone: " + getPhone() + "in date: " + getInDate() + "out date: " + getOutDate() ;
    }

    /**
     *
     * @param roomObject set roomList(hotel's database) list
     */
    public void setList(Room roomObject){
        roomList.add(roomObject);
    }


    /**
     * @return  guest's in date to hotel
     */
    public String getInDate() {
        return InDate;
    }

    /**
     * @return guest's out date from the hotel
     */
    public String getOutDate() {
        return outDate;
    }

    /**
     * @return age of guest
     */
    public int getAge() {
        return age;
    }

    /**
     * @return phone of guest
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @return name of guest
     */
    public String getName() {
        return name;
    }

    /**
     * @return gender of guest
     */
    public String getGender() {
        return gender;
    }

    /**
     *
     * @param filename  filename for update database on the file
     */
    @Override
    public void writeData(String filename) {

        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(filename);
            //Write the CSV file header

            fileWriter.append(FILE_HEADER.toString());
            //Add a new line separator after the header
            fileWriter.append(NEW_LINE_SEPARATOR);

            //Write a new student object list to the CSV file
            for (Room r : roomList) {
                fileWriter.append(String.valueOf(r.getRoomID()));
                fileWriter.append(COMMA);
                fileWriter.append(r.getState());
                fileWriter.append(COMMA);
                fileWriter.append(r.getRoomType());
                fileWriter.append(COMMA);
                fileWriter.append(String.valueOf(r.getPriceOfRoom()));
                fileWriter.append(COMMA);
                fileWriter.append(r.getInDate());
                fileWriter.append(COMMA);
                fileWriter.append(r.getOutDate());
                fileWriter.append(COMMA);
                fileWriter.append(r.getGender());
                fileWriter.append(COMMA);
                fileWriter.append(r.getName());
                fileWriter.append(COMMA);
                fileWriter.append(String.valueOf(r.getAge()));
                fileWriter.append(COMMA);
                fileWriter.append(String.valueOf(r.getPhone()));
                fileWriter.append(NEW_LINE_SEPARATOR);
            }
        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();
        } finally {

            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
            }

        }
    }

    /**
     *
     * @param hotelOb Hotel class's object that sets hotel's number of room's and full room's
     * @param filename filename for reading database with name of file from the file
     * @throws IOException   exception that readData or writeData methods throws
     */
    @Override
    public void readData(Hotel hotelOb,String filename) throws IOException {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        roomList = new ArrayList<>();
        // read file line by line
        int fullRoom = 0;
        String line = null;
        Scanner scanner = null;
        int column = 0;
        try {
            reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while ((line = reader.readLine()) != null) {
            Room room = new Room();
            scanner = new Scanner(line);
            scanner.useDelimiter(",");
            while (scanner.hasNext()) {
                String data = scanner.next();
                if (column == 0)
                    room.setRoomID(Integer.parseInt(data));
                else if (column == 1){
                    room.setState((data));
                    if(!(data.equals("Empty")))
                        fullRoom++;
                }
                else if (column == 2)
                    room.setType(data);
                else if (column == 3)
                    room.setPriceOfRoom(Integer.parseInt(data));
                else if (column == 4)
                    room.setInDate(data);
                else if (column == 5)
                    room.setOutDate(data);
                else if (column == 6)
                    room.setGender(data);
                else if (column == 7)
                    room.setName(data);
                else if (column == 8)
                    room.setAge(Integer.parseInt(data));
                else if (column == 9)
                    room.setPhone(data);
                else
                    System.out.println("invalid data:" + data);
                column++;
            }
            roomList.add(room);
            column = 0;
        }
        hotelOb.setNumberOfRooms(roomList.size());
        hotelOb.setFullRoom(fullRoom);

    }


    /**
     *
     * @param newRoom Room class's object that is gonna use for book a room and hold it room's information.
     * @param hotelOb   Hotel class's object that holds number of rooms and full rooms.
     * @param filename  file name for reading and writing data to csv file for hold data
     */
    @Override
    public void bookingRoom(Room newRoom, Hotel hotelOb,String filename) {

        int i = 0, equal = -1;
        try {
            readData(hotelOb,filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (i < roomList.size() - 1) {
            if ((roomList.get(i).getRoomType()).equals(newRoom.getRoomType()) && "Empty".equals(roomList.get(i).getState())) {
                equal = i;
                newRoom.setState("Reserved");
                newRoom.setRoomID(roomList.get(equal).getRoomID());
                newRoom.setPriceOfRoom(roomList.get(equal).getPriceOfRoom());
                roomList.set(equal, newRoom);
                hotelOb.setFullRoom(hotelOb.getFullRoom() + 1);
                writeData(filename);
                System.out.print(newRoom.getName());
                System.out.print("Room ");
                System.out.print(newRoom.getRoomID());
                System.out.println("(id) IS BOOKED FOR YOU.");
                System.out.print("NUMBER OF EMPTY ROOM:");
                System.out.println(hotelOb.getNumberOfRooms()-hotelOb.getFullRoom());
                break;
            }
            i++;
        }

        //if there is no empty room
        if (equal == -1){
            System.out.print(newRoom.getName());
            System.out.println(" YOUR BOOKING IS NOT SUCCESSFUL!!");
        }

    }

    /**
     *
     * @param deleteRoom Room class's object that holds canseling room's and its guest's information
     * @param hotelOb Hotel class's object for calling readData method to updating database
     * @param roomId id for cansel operations : check the right room or not
     * @param filename  filename for reading database with name of file from the file
     * @return  return value is 1 for right situation, otherwise it is -1 for checking process
     */
    @Override
    public int canselBooking(Room deleteRoom, Hotel hotelOb, int roomId,String filename) {
        int i = 0, equal = -1;
        try {
            readData(hotelOb,filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (i < roomList.size() - 1) {
            if ((roomList.get(i).getName()).equals(deleteRoom.getName()) && (roomList.get(i).getPhone().equals(roomList.get(i).getPhone()))) {
                if (roomList.get(i).getState().equals("Reserved") && roomId == roomList.get(i).getRoomID()) {
                    equal = i;

                    deleteRoom.setState("Empty");
                    deleteRoom.setPhone("0");
                    deleteRoom.setName(" ");
                    deleteRoom.setAge(0);
                    deleteRoom.setOutDate("0000/00/00");
                    deleteRoom.setInDate("0000/00/00");
                    deleteRoom.setPriceOfRoom(roomList.get(equal).getPriceOfRoom());
                    deleteRoom.setRoomID(roomList.get(equal).getRoomID());
                    deleteRoom.setGender(" ");
                    roomList.set(equal, deleteRoom);
                    writeData(filename);
                    hotelOb.setFullRoom(hotelOb.getFullRoom() - 1);
                    System.out.print("Room ");
                    System.out.print(deleteRoom.getRoomID());
                    System.out.println("(id) is canceled for you.");
                    System.out.print("NUMBER OF EMPTY ROOM:");
                    System.out.println(hotelOb.getNumberOfRooms()-hotelOb.getFullRoom());
                    return 1;
                }
            }
            i++;
        }


        System.out.println("There is no room reservation that you want to cansel");
        return -1;

    }


}



