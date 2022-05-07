public class Cash implements payment {
    private double amount;
    private final Services services;

    public Cash (Services services){
        this.services = services;
    }

    @Override
    public double getprice() {
        return services.getprice();
    }

    @Override
    public void pay() {
        if (amount >= getprice()){
            amount = amount-getprice();
            System.out.println("Here is your balance: "+amount);
            System.out.println("Service(s) has been paid.");
        } else {
            System.out.println("Insufficient amount. Add more money");
        }
    }
}