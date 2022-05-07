public class ExtraServices {
    String extraservice;
    double price;
    private boolean isExtra = false;


    public boolean getExtraservices() {
        return isExtra;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setExtra(boolean extra) {
        isExtra = extra;
    }
}
