public class CustomerInfo {

    private PersonalInfo personalInfo;
    private CarInfo CarInfo;
    private  Date Date;

    public CustomerInfo() { }

    public String getPersonalInfo() { return personalInfo.toString(); }
    public String getCarInfo(){
        return CarInfo.toString();
    }
    public String getDate(){
        return Date.toString();
    }
    // the code above is doing this methods function but this method returns Static information

    public String getCustomerInfo(){
        return ( " customer Information : \n" +
                "  Name : Ali \n " +
                "  Phone Number : 05426500830 \n" +
                "  ID : 99809845912 \n" +
                " Car Information : \n" +
                " Black GMC Yukun XL Larg 4444 \n" +
                " Date Information : \n" +
                " 03.10.2019 20:20 " );
    }
}

