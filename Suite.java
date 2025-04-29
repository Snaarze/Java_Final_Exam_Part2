

public class Suite  extends Room{

    

    public Suite(String roomType, double price, boolean isAvailable) {
        super(roomType,  price,  isAvailable);
    }
    @Override
    void getDescription(){
        if(isAvailable()){
            System.out.println("This room is a suite bedroom "  + "price : " + getPrice() + " is available");
        }else {
            System.out.println("This room is a suite bedroom " + "price : " + getPrice() + " is not available " ) ;
        }
       
    }
}

