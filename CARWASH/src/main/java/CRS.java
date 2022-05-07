
public class CRS {

    public static void main(String[] args) {
        calendar cal = new calendar();
        CustomerInfo customerinfo = new CustomerInfo();
        confirm confirm = new confirm();
        System.out.println("Choose your date:");
        cal.DisplaysCalendar();
        System.out.println("Fill in your details");
        customerinfo.getPersonalInfo();
        customerinfo.getCarInfo();
        customerinfo.getDate();
        //for the serviceoption true means yes and false means no
        //for paymentoption true means payment online while no means cash
        boolean serviceoption=true;
        Services services;
        if (serviceoption){

            services = new Services(true);
            System.out.println(services.getprice());
            System.out.println("Choose payment");
            boolean paymentoption = true;
            if (paymentoption){
                paymentonline payment = new paymentonline(services);
                payment.pay();
            } else {
                Cash cash = new Cash(services);
                cash.pay();
            }

        } else {

            services = new Services(false);
            System.out.println(services.getprice());
            System.out.println("Choose payment");
            boolean paymentoption = true;
            if (paymentoption){
                paymentonline payment = new paymentonline(services);
                payment.pay();
            } else {
                Cash cash = new Cash(services);
                cash.pay();
            }

        }
        System.out.println("This is your validation code: "+confirm.getValidationCode());


    }

}