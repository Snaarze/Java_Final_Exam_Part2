abstract class Room {
    String roomType;
    double price;
    boolean isAvailable;
    

    Room(String roomType, double price, boolean  isAvailable){
        this.roomType = roomType;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    public String getRoomType(){
        return this.roomType;
    }

    public double getPrice(){
        return this.price;
    }
    
    public boolean isAvailable() {
        return  this.isAvailable;
    }

    public boolean  bookRoom(){
        return this.isAvailable = false;
    }

    public boolean releaseRoom(){
        return  this.isAvailable = true;
    }

    void getDescription(){

    }
}
