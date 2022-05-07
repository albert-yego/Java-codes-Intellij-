public class Date {
    public int Date;
    public int Time;


    public int getDate() {
        return Date;
    }
    public int getTime() {return Time; }

    public void setDate (int Date) { this.Date = Date; }
    public void setTime (int Time ) { this.Time = Time; }


    public String toString () {
        return ( "" + Date + Time );
    }

}
