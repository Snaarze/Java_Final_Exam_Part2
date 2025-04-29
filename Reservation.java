public class Reservation {
    Room room;
    Customer customer;
    String checkInDate;
    String checkOutDate;
    double totalPrice;

    public Reservation(Room room, Customer customer, String checkInDate, String checkOutDate, double totalPrice) {
        this.room = room;
        this.customer = customer;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalPrice = totalPrice;
    }

    void getReservation(){
        System.out.println("Room type : " + this.room.getRoomType());
        System.out.println("Customer Info : " + this.customer.getName());
        System.out.println("Check in Date  : " + this.checkInDate);
        System.out.println("Check out Date  : " + this.checkOutDate);
        System.out.println("Price  : " + this.totalPrice);
    }

    public void applyDiscount(String membershipType) {
        if(membershipType.equalsIgnoreCase("Silver")){
            
        }
    }
}
