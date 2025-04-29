
import java.util.ArrayList;
import java.util.Scanner;
public class  Main {
    static ArrayList<Room> listOfRooms = new ArrayList<>();
    static ArrayList<Customer> listOfCustomer = new ArrayList<>();
    static ArrayList<Reservation> listOfReservations = new ArrayList<>();

    static void displayRoom(){
        int  counter = 0;
        for(Room room : listOfRooms){
        System.out.print("Room " + counter + " : ");
        room.getDescription();
        counter++;
        }
    }

    public static Room selectRoom(int roomIndex) {
        int counter = 0;
        for (Room room : listOfRooms) {
            System.out.print("Room " + counter + " : ");
            room.getDescription(); 
            counter++;
        }
    
        System.out.print("Select a room by number: ");
    
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
    

    public static void main(String[] args) {
        // create a 3 rooms each type of the room
        listOfRooms.add(new SingleRoom("Single", 200, true));
        listOfRooms.add(new SingleRoom("Single", 250, false));
        listOfRooms.add(new SingleRoom("Single", 300, false));
        listOfRooms.add(new DoubleRoom("Double", 600, false));
        listOfRooms.add(new DoubleRoom("Double", 700, false));
        listOfRooms.add(new DoubleRoom("Double",800, false));
        listOfRooms.add(( new Suite("Suite", 900, false)));
        listOfRooms.add(( new Suite("Suite", 1000, true)));
        listOfRooms.add(( new Suite("Suite", 1100, false)));
        
        listOfCustomer.add(new Customer("Jeremy", "Jeremy@gmail.com", "Regular"));
        listOfCustomer.add(new Customer("Jeremy123", "Jeremy1@gmail.com", "Silver"));
        listOfCustomer.add(new Customer("Jeremy43", "Jeremy2@gmail.com", "Gold"));
        listOfCustomer.add(new Customer("Jeremy63", "Jeremy3@gmail.com", "Silver"));
        

        Scanner scn =  new Scanner(System.in);

        boolean isExit = false;
       

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
                        System.out.print("Enter your name for customer verification: ");
                        scn.nextLine();
                        String customerName = scn.nextLine();
                        Customer matchedCustomer = null;
                    
                        for (Customer customer : listOfCustomer) {
                            if (customer.getName().equalsIgnoreCase(customerName)) {
                                matchedCustomer = customer;
                                break;
                            }
                        }
                        selectedRoom.bookRoom();
                    
                        if (matchedCustomer == null) {
                            System.out.println("Customer not found. Booking cannot proceed.");
                            break;
                        }
                    
                        double originalPrice = selectedRoom.getPrice();
                        double finalPrice = originalPrice;
                        String membership = matchedCustomer.getMembership();
                    
                        if (membership.equalsIgnoreCase("Silver")) {
                            finalPrice = originalPrice * 0.9;
                            System.out.println("Silver Member: 10% discount applied.");
                        } else if (membership.equalsIgnoreCase("Gold")) {
                            finalPrice = originalPrice * 0.8;
                            System.out.println("Gold Member: 20% discount applied.");
                        } else {
                            System.out.println("No discount applied.");
                        }
                    
                        System.out.printf("Final price after discount: $%.2f%n", finalPrice);
                    
                        System.out.print("Enter payment method (Cash or Credit Card): ");
                        String method = scn.nextLine();
                        String cardNumber = "";
                    
                        if (method.equalsIgnoreCase("Credit Card")) {
                            System.out.print("Enter credit card number: ");
                            cardNumber = scn.nextLine();
                        }
                        
                        Payment payment = new Payment(finalPrice, method, cardNumber);
                        payment.processPayment();

                    }
                    

                    break;
                case 4 : 

                scn.nextLine(); 
                displayRoom();
                System.out.print("Select a room: ");
                int selectedRoomIndexReserve = scn.nextInt();
                Room selectedRoomReserve = selectRoom(selectedRoomIndexReserve);


                if (selectedRoomReserve.isAvailable() == true) {
                    System.out.print("Enter your name for customer verification: ");
                    scn.nextLine(); 
                    String customerName = scn.nextLine();

                    System.out.print("Enter the date of check in : Format(mm/dd/yy) ");
                    String checkInReserve = scn.nextLine();
                    System.out.print("Enter the date of check out : Format(mm/dd/yy) ");
                    String checkOutReserve = scn.nextLine();
                    Customer matchedCustomer = null;
                
                    for (Customer customer : listOfCustomer) {
                        if (customer.getName().equalsIgnoreCase(customerName)) {
                            matchedCustomer = customer;
                            break;
                        }
                    }
                
                    if (matchedCustomer == null) {
                        System.out.println("Customer not found. Booking cannot proceed.");
                        break;
                    }
                    
                   System.out.println("The Room is now reserved by " + matchedCustomer.getName());

                   Reservation reserve = new Reservation(selectedRoomReserve, matchedCustomer, checkInReserve, checkOutReserve, 300);
                   listOfReservations.add(reserve);
                   System.err.println(listOfReservations);
                   reserve.getReservation();
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