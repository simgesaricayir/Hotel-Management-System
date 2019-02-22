package HotelSystem;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws IOException {

        FileInputStream is = new FileInputStream("testInput.txt");
        System.setIn(is);

        Hotel myHotel = new Hotel("myHotel",2);
        Guest guestt = new Guest();
        guestt.readData(myHotel,"otel.csv");
        //myHotel.writeCsvFile("otel.csv");

        System.out.print("Welcome to ");
        System.out.print(myHotel.getHotelName());
        System.out.print(".Please enter one of them G/R(Guest or Receptionist): ");
        Scanner scanner = new Scanner(System.in);
        String login = scanner.next();
        while (!(login.equals("G") || login.equals("g") || login.equals("R") || login.equals("r"))) {
            System.out.println("Please enter valid character G/R(Guest or Receptionist): ");
            login = scanner.next();
        }
        System.out.println(login);
        if (login.equals("g") || login.equals("G")) {

            while (true) {
                System.out.print("What do you want to do?Booking or cancel booking(B/C): ");
                String choice = scanner.next();
                while (!(choice.equals("B") || choice.equals("C"))) {
                    System.out.println("Wrong entry,Please enter book or cancel(B/C)");
                    choice = scanner.next();
                }
                System.out.println(choice);
                System.out.print("Name: ");
                String name = scanner.next();
                System.out.println(name);

                System.out.print("Age: ");
                int age = scanner.nextInt();
                System.out.println(age);

                System.out.print("Gender(Female/Male): ");
                String gender = scanner.next();

                while (!(gender.equals("Female") || gender.equals("Male"))) {
                    System.out.println("Wrong entry.Enter a valid gender of room(Female/Male) :");
                    gender = scanner.next();
                }
                System.out.println(gender);
                System.out.print("Please enter your phone number(5*********): ");
                String phone = scanner.next();
                while (phone.length() != 10) {
                    System.out.print("You entered a invalid phone number!");
                    System.out.print("Please enter your phone number(5*********): ");
                    phone = scanner.next();
                }
                System.out.println(phone);
                String InDate;
                System.out.print("Please enter your in date to hotel(yyyy/mm/dd): ");
                String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
                while (true){
                    InDate=scanner.next();
                    System.out.println(InDate);

                    if(date.compareTo(InDate) <= 0 && InDate.length()==10)
                        break;
                    else
                        System.out.print("You entered a in date after your in date! "+
                        "Please enter a valid in date:");

                }

                System.out.print("Please enter your out date to hotel(yyyy/mm/dd): ");
                String outDate;
                while (true){
                    outDate=scanner.next();
                    System.out.println(outDate);

                    if(date.compareTo(outDate) <= 0 && InDate.compareTo(outDate)<=0)
                        break;
                    else
                        System.out.print("You entered a in date after your out date!" +
                        "Plese enter a valid out date: ");

                }

                Guest guest = new Guest(name, gender, age, phone, InDate, outDate);
                System.out.println(guest.toString());
                if (choice.equals("B")) {
                    System.out.print("Please enter a room type(single/double):");
                    String type = scanner.next();
                    while (!(type.equals("single") || type.equals("double"))) {
                        System.out.println("Wrong entry.Enter a valid type of room(single/double) :");
                        type = scanner.next();
                    }
                    System.out.println(type);
                    Room room = new Room(type, "Empty",myHotel, guest);
                    guest.bookingRoom(room, myHotel,"otel.csv");
                }
                else if (choice.equals("C")) {
                    System.out.println("Please enter your room type(single/double):");
                    String type = scanner.next();
                    while (!(type.equals("single") || type.equals("double"))) {
                        System.out.println("Wrong entry.Enter a valid type of room(single/double) :");
                        type = scanner.next();
                    }
                    Room room = new Room(type, "Reserved", myHotel, guest);
                    System.out.println("Please enter your room id:");
                    int roomId = scanner.nextInt();
                    while (roomId > myHotel.getNumberOfRooms() || roomId <= 0) {
                        System.out.println("Wrong entry.Enter a valid id of your room:");
                        roomId = scanner.nextInt();
                    }
                    guest.canselBooking(room, myHotel, roomId,"otel.csv");

                }
                System.out.print("Do you want to continue processing, yes or no (Y/N):");
                String select = scanner.next();
                while (!(select.toLowerCase().equals("y") || select.toLowerCase().equals("n"))) {
                    System.out.println("Wrong entry,Please enter yes or no(y/n)");
                    select = scanner.next();
                }
                System.out.println(select);
                if (select.toLowerCase().equals("n"))
                    System.exit(1);
            }

        }
        //receptionist password = 1!!!!
        else if (login.equals("r") || login.equals("R")) {
            Scanner input = new Scanner(System.in);
            Receptionist recept = new Receptionist("Recept1");
            int passwordCounter = 0;
            System.out.println( recept.toString());

            System.out.println("Please enter your password for privacy: ");
            int password = scanner.nextInt();
            while(password!=(recept.getPassword())){
                if(passwordCounter==5) {
                    System.out.print("You entered password wrong five times.");
                    System.exit(1);
                }
                System.out.print("Invalid password please try again: ");
                password = scanner.nextInt();
            }
            while (true) {
                System.out.print("Welcome our hotel system!What do you want to do?" +
                        "Book , cancel ,check in,check out (B/C/I/O):");
                String getInput = scanner.next();
                while (!(getInput.equals("B") || getInput.equals("C")
                        || getInput.equals("O") || getInput.equals("I"))) {
                    System.out.print("You entered a invalid character,Please enter valid character " +
                            "(B(booking)/C(cansel)/I(check-in)/O(check-out)):");
                    getInput = scanner.next();
                }
                System.out.println(getInput);
                System.out.print("Name: ");
                String name = scanner.next();
                System.out.println(name);

                System.out.print("Age: ");
                int age = scanner.nextInt();
                System.out.println(age);

                System.out.print("Gender(Female/Male): ");
                String gender = scanner.next();
                while (!(gender.equals("Female") || gender.equals("Male"))) {
                    System.out.print("Wrong entry.Enter a valid gender of room(Female/Male) :");
                    gender = scanner.next();
                }
                System.out.println(gender);

                System.out.print("Please enter your phone number(5*********): ");
                String phone = scanner.next();
                while (phone.length() != 10) {
                    System.out.print("You entered a invalid phone number!");
                    System.out.print("Please enter your phone number(5*********): ");
                    phone = scanner.next();
                }
                System.out.println(phone);

                String InDate;
                System.out.print("Please enter your in date to hotel(yyyy/mm/dd): ");
                String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
                while (true){
                    InDate=scanner.next();
                    System.out.println(InDate);

                    if(date.compareTo(InDate) <= 0 && InDate.length()==10)
                        break;
                    else
                        System.out.print("You entered a in date after your in date! "+
                                "Please enter a valid in date:");

                }

                System.out.print("Please enter your out date to hotel(yyyy/mm/dd): ");
                String outDate;
                while (true){
                    outDate=scanner.next();
                    System.out.println(outDate);

                    if(date.compareTo(outDate) <= 0 && InDate.compareTo(outDate)<=0)
                        break;
                    else
                        System.out.print("You entered a in date after your out date!" +
                                "Plese enter a valid out date: ");

                }

                Guest guest = new Guest(name, gender, age, phone, InDate, outDate);
                System.out.print("which room type you are going to process (single or double):");
                String type = scanner.next();
                while (!(type.toLowerCase().equals("single") || type.toLowerCase().equals("double"))) {
                    System.out.print("Wrong entry.Enter a right type of room:");
                    type = scanner.next();
                }
                System.out.println(type);

                if(getInput.equals("B")) {
                    Room room = new Room(type, "Empty", myHotel, guest);
                    recept.bookingRoom(room, myHotel,"otel.csv");
                }
                else if (getInput.equals("C")) {
                    Room room = new Room(type, "Reserved", myHotel, guest);
                    System.out.println("Please enter your room id:");
                    int roomId = scanner.nextInt();
                    while (roomId > myHotel.getNumberOfRooms() || roomId <= 0) {
                        System.out.println("Wrong entry.Enter a valid id of your room:");
                        roomId = scanner.nextInt();
                    }
                    recept.canselBooking(room, myHotel, roomId,"otel.csv");
                }
                else if (getInput.equals("I")) {
                    System.out.print("Are you going to a check-in a new room?(Y/N):");
                    String choice = scanner.next();
                    while (!(choice.equals("Y") || choice.equals("N"))) {
                        System.out.print("Wrong entry.Enter a right character (Y/N):");
                        choice = scanner.next();
                    }
                    System.out.println(choice);
                    if (choice.equals("Y")) {
                        System.out.println("YES");
                        Room room = new Room(type, "Empty", myHotel, guest);
                        recept.checkIn(room, myHotel,"otel.csv");
                    }
                    else if (choice.equals("N")) {
                        System.out.println("NO");
                        Room room = new Room(type, "Reserved", myHotel, guest);
                        recept.checkIn(room, myHotel,"otel.csv");
                    }
                    System.out.println(choice);
                }
                else if (getInput.equals("O")) {
                    Room room = new Room(type, "Check-in", myHotel, guest);
                    System.out.print("Please enter your room id:");
                    int roomId = scanner.nextInt();
                    while (roomId > myHotel.getNumberOfRooms() || roomId <= 0) {
                        System.out.print("Wrong entry.Enter a valid id of your room:");
                        roomId = scanner.nextInt();
                    }
                    System.out.println(roomId);
                    recept.checkOut(room, myHotel, roomId,"otel.csv");
                }
                System.out.print("Do you want to continue processing, yes or no (Y/N):");
                String select;
                select = scanner.next();
                while (!(select.equals("Y") || select.equals("N")) ) {
                    System.out.println("Wrong entry,Please enter yes or no(Y/N)");
                    select = scanner.next();
                }
                System.out.println(select);
                if (select.equals("N"))
                    System.exit(1);
            }
        }
    }
}
