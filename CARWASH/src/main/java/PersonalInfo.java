public class PersonalInfo {
    private int ID;
    private String FullName;
    private int PhoneN;

    public String getFullName() {
        return FullName;
    }
    public int getID() {return ID;}
    public int getPhoneN() {return PhoneN; }


    public void setID (int ID) { this.ID = ID; }
    public void setFullName (String FullName ) { this.FullName = FullName; }
    public void setPhoneN (int PhoneN ) { this.PhoneN = PhoneN; }

    public String toString () {return ID+" "+FullName+" "+PhoneN;}
}
