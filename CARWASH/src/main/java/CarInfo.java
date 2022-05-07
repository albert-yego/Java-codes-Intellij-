public class CarInfo {

    public String Color;
    public String Model;
    public String Size;
    public int PlateNo;

    public CarInfo() { }

    public String getColor() { return Color; }
    public String getModel() { return  Model; }
    public String getSize() { return Size;}
    public int getPlateNo() { return PlateNo; }


    public void setColor ( String Color ) {this.Color = Color; }
    public void setModel ( String Model ) {this.Model = Model; }
    public void setSize ( String Size ) { this.Size = Size; }
    public void setPlateNo ( int PlateNo ) { this.PlateNo = PlateNo; }

    public String toString () {
        return Color + Model + Size + PlateNo;
    }

}
