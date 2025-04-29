public class Payment {
    double amount;
    String paymentMethod;
    String creditCardNumber;

    Payment(double amount, String paymentMethod, String creditCardNumber){
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.creditCardNumber = creditCardNumber;
    }

    void processPayment(){
        if(this.paymentMethod.equalsIgnoreCase("cash")){
            System.out.println("Processing payment using cash with the amount of " + this.amount);
        }else if(this.paymentMethod.equalsIgnoreCase("credit card")){
            System.out.println("Processing payment using credit card with the amount of " + this.amount + "Credit card number used " + maskCreditCard());
        }
    }

    public String maskCreditCard(){
        String maskedCard = "";
        for(int i = 0; i < this.creditCardNumber.length() - 4; i++){
            maskedCard  += "*";
        }
        String cardNumber = maskedCard + " " + this.creditCardNumber.substring(this.creditCardNumber.length() - 3 , this.creditCardNumber.length());
        return cardNumber;
    }
}
