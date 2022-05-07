public class Services {
    private final CarWash carWash = new CarWash();
    private final ExtraServices extraServices = new ExtraServices();

    public Services(boolean extraService){

        extraServices.setExtra(extraService);

    }

    public String getCarWash() {

        return carWash.getCarWash();

    }

    public boolean getExtraServices() {

        return extraServices.getExtraservices();
    }

    public double getprice(){
        if(getExtraServices()) {
            return carWash.getPrice() + extraServices.getPrice();
        } else {
            return carWash.getPrice();
        }
    }
}
