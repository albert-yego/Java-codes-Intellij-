public class paymentonline {
    private int cardnumber;
    private String expirydate = "MM/YY";
    private String CardOwnerName;
    private double amount;
    private final Services services;

    public paymentonline(Services services){
        this.services = services;
    }


    public int getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(int cardnumber) {
        this.cardnumber = cardnumber;
    }

    public String getExpirydate() {
        return expirydate;
    }

    public void setExpirydate(String expirydate) {
        this.expirydate = expirydate;
    }

    public String getCardOwnerName() {
        return CardOwnerName;
    }

    public void setCardOwnerName(String cardOwnerName) {
        CardOwnerName = cardOwnerName;
    }


    public double getprice() {
        return services.getprice();
    }


    public void pay() {
        if (amount >= getprice()){
            amount = amount-getprice();
            System.out.println(amount +" is left in your account.");
            System.out.println("Service(s) has been paid.");
        } else {
            System.out.println("Insufficient amount");
        }
    }
}
