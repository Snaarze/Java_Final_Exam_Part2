
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class  Main {
    static ArrayList<Room> listOfRooms = new ArrayList<>();
    static ArrayList<Customer> listOfCustomer = new ArrayList<>();
    static ArrayList<Reservation> listOfReservations = new ArrayList<>();


    // could have used, for loop here instead of foreach, but before i forgot how to uses the ArrayList.
    static void displayRoom(){
        int  counter = 0;
        for(Room room : listOfRooms){
        if(!room.isAvailable){
            counter++;
            continue;
        }
        System.out.print("Room " + counter + " : ");
        room.getDescription();
        counter++;
        }
    }

    public static Room selectRoom(int roomIndex) {
        if (roomIndex >= 0 && roomIndex < listOfRooms.size()) {
            Room selectedRoom = listOfRooms.get(roomIndex);
            System.out.println("You selected: ");
            selectedRoom.getDescription();
            return selectedRoom;
        } else {
            System.out.println("Invalid room selection.");
            return null;
        }
    }

    public static List<Object> askUser(Scanner scn, boolean isReserve) {
        List<Object> userInfo = new ArrayList<>();
    
        System.out.print("Enter your name for customer verification: ");
        scn.nextLine();
        String customerName = scn.nextLine();
        userInfo.add(customerName);
        
        // if true ask user when to check in rooms, if not skip this ask
       if(isReserve){
            System.out.print("Enter the date of check-in (Format mm-dd-yy): ");
            String checkInReserve = scn.nextLine();
            userInfo.add(checkInReserve);
        
            System.out.print("Enter the date of check-out (Format mm-dd-yy): ");
            String checkOutReserve = scn.nextLine();
            userInfo.add(checkOutReserve);
       }
    
        //    ask the user which method is the payment
        System.out.print("Payment Method (Cash / Credit Card): ");
        String paymentMethodReserve = scn.nextLine();
        userInfo.add(paymentMethodReserve);
    
        if (paymentMethodReserve.equalsIgnoreCase("Credit Card")) {
            System.out.print("Enter Credit Card Number: ");
            String creditCardNumbers = scn.nextLine();
            userInfo.add(creditCardNumbers);
        }
    
        return userInfo;
    }
    
    

    public static void main(String[] args) {
        // create a 3 rooms each type of the room
        int singlePrice = 250;
        int doublePrice = 600;
        int suitePrice = 900;

        for(int i = 0; i < 3;i++){
            listOfRooms.add(new SingleRoom("Single", singlePrice, true));
            singlePrice += 50;
            listOfRooms.add(new DoubleRoom("Double", doublePrice, true));
            doublePrice += 100;

            listOfRooms.add(( new Suite("Suite", suitePrice, true)));
            suitePrice += 100;
        }
      
        listOfCustomer.add(new Customer("Jeremy", "Jeremy@gmail.com", "Regular"));
        listOfCustomer.add(new Customer("Jeremy123", "Jeremy1@gmail.com", "Silver"));
        listOfCustomer.add(new Customer("Jeremy43", "Jeremy2@gmail.com", "Gold"));
        listOfCustomer.add(new Customer("Jeremy63", "Jeremy3@gmail.com", "Silver"));
        
        Scanner scn =  new Scanner(System.in);

        boolean isExit = false;
        
        // keep looping as long as the isExit is false
        // to exit press 6
        while(!isExit){
            
            System.out.println("1. Display Available Rooms");
            System.out.println("2. Get customer info");
            System.out.println("3. Select room");
            System.out.println("4. Reserve a room");
            System.out.println("5. Reservation Summary");
            System.out.println("6. Release a room");
            System.out.println("7. Exit");
            System.out.print("Choose an option : ");
            int choice = scn.nextInt();

            switch (choice) {
                case 1:
                    displayRoom();
                    break;
                case 2 : 
                    scn.nextLine();
                    System.out.print("Enter Customer name ");
                    String name = scn.nextLine();

                    boolean isFound = false;

                    for(Customer customer : listOfCustomer){
                        if(customer.getName().equalsIgnoreCase(name)){
                            customer.getCustomerInfo();
                            isFound = true;
                        }
                    }
                    // if user is not found nor exist print
                    if(!isFound){
                        System.out.println("No Customer record found");
                    }

                    break;
                case 3 :
                    displayRoom();
                    System.out.print("Select a room: ");
                    int selectedRoomIndex = scn.nextInt();
                    Room selectedRoom = selectRoom(selectedRoomIndex);

                   System.out.println(selectedRoom.isAvailable());
                    if (selectedRoom.isAvailable() == true) {
                        List<Object>  userInfo = askUser(scn, false);
                        Customer matchedCustomer = null;
                    
                        for (Customer customer : listOfCustomer) {
                            if (customer.getName().equalsIgnoreCase((String) userInfo.get(0))){
                                matchedCustomer = customer;
                                break;
                            }
                        }
                    
                        if (matchedCustomer == null) {
                            System.out.println("Customer not found. Booking cannot proceed.");
                            break;
                        }
                    
                        selectedRoom.bookRoom();
                        // discounts may vary to memberships
                        Payment payment = new Payment(selectedRoom.applyDiscount(matchedCustomer.getMembership()),(String) userInfo.get(1),(String) userInfo.get(2));
                        payment.processPayment();

                    }
                    break;
                case 4 : 

                scn.nextLine(); 
                displayRoom();
                System.out.print("Select a room: ");
                int selectedRoomIndexReserve = scn.nextInt();
                Room selectedRoomReserve = selectRoom(selectedRoomIndexReserve);

                    // if the selectedRoom is available proceed of getting the user Information
                if (selectedRoomReserve.isAvailable() == true) {
                    List<Object>  userInfo = askUser(scn, true);
                   
                    Customer matchedCustomer = null;
                
                    System.out.print((String) userInfo.get(0));
                    for (Customer customer : listOfCustomer) {
                        if (customer.getName().equalsIgnoreCase((String)userInfo.get(0))) {
                            matchedCustomer = customer;
                            break;
                        }
                    }
                
                    if (matchedCustomer == null) {
                        System.out.println("Customer not found. Booking cannot proceed.");
                        break;
                    }

                   System.out.println("The Room is now reserved by " + matchedCustomer.getName());
                  
                   Reservation reserve = new Reservation(selectedRoomReserve, matchedCustomer, (String) userInfo.get(1),(String) userInfo.get(2) , selectedRoomReserve.getPrice());
                   double  discountedPrice = reserve.applyDiscount(matchedCustomer.getMembership());
                
                    //    add reserved room in the array
                   listOfReservations.add(reserve);
                    //    print the reserve room    
                   reserve.getReservation();
                    // isAvailable of room will be true even if it just reservation book
                   selectedRoomReserve.bookRoom();
                   Payment payment = new Payment(discountedPrice, (String) userInfo.get(4), (String) userInfo.get(4));
                   payment.processPayment();
                    }
                    
                    break;

                case 5 : 
                    for(Reservation reserveRoom : listOfReservations){
                        reserveRoom.getReservation();
                    }
                    break;
                case 6 : 
                    displayRoom();
                    scn.nextLine();

                    int indexOfRoom = scn.nextInt();

                    Room selecedRoom = selectRoom(indexOfRoom);

                    if(selecedRoom.isAvailable()){
                        System.out.println("The room that you are trying to released is already available");
                        return;
                    }else {
                        selecedRoom.releaseRoom();
                        System.out.println("Room availability is : " + selecedRoom.isAvailable());
                        return;
                    }

                case 7 :
                   System.out.println("Exiting....");
                   isExit = true;
                    break;

                default:
                    throw new AssertionError();
            }
        }

    }
}