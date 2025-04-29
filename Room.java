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

    public double applyDiscount(String membershipType) {
        if(membershipType.equalsIgnoreCase("Silver")){
            this.price *= 0.9;
        }else if(membershipType.equalsIgnoreCase("Gold")){
            this.price *= 0.8;
        }

        return price;
    }

    void getDescription(){

    }
}
