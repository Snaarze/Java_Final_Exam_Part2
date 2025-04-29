class Customer {
    private String name;
    private String email;
    private String membershipStatus;

    Customer(String name,String email, String membershipStatus){
        this.name = name;
        this.email = email;
        this.membershipStatus = membershipStatus;
    }

    void getCustomerInfo(){
        System.out.println("Name : " + getName() + "Email : " + getEmail() + " Membership :" + getMembership());
    }

    public String getName(){
        return  this.name;
    }
    public String getEmail(){
        return this.email;
    }

    public String getMembership(){
        return  this.membershipStatus;
    }
}