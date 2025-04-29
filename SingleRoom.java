

public class SingleRoom  extends Room{
    SingleRoom(String roomType, double price, boolean isAvailable){
        super(roomType, price, isAvailable);
    }
    @Override
    void getDescription(){
        if(isAvailable()){
            System.out.println("This room is a single bedroom " + "price : " + getPrice() + " is available, ");
        }else {
            System.out.println("This room is a single bedroom " + "price : " + getPrice() + " is not available " ) ;
        }
       
    }
}
