import java.util.ArrayList;

public class CompleteProject {

        private int numberOfWeeks ;
    private final ArrayList<Components> Components = new ArrayList<>();

    public void setEndProduct() {
    }

    public void setNumberOfWeeks(int numberOfWeeks) {
            this.numberOfWeeks = numberOfWeeks;
        }

        public int getNumberOfWeeks() {
            return numberOfWeeks;
        }

        public ArrayList<Components> getComponents() { return Components; }
        public void setComponents(Components endProductComponents) {
            this.Components.add(endProductComponents);
        }

        public Components searchComponentsById(int ID){
            for (int i = 0; i < this.getComponents().size(); i++) {
                if (this.getComponents().get(i).getID() == ID) {
                    return this.getComponents().get(i);
                }
            }
            return null ;
        }

        public boolean findGivenComponentWithSimilarId (int ID){
            for (int i = 0; i < this.getComponents().size(); i++) {
                if (this.getComponents().get(i).getID() == ID) {
                    return true;
                }
            }
            return false ;
        }

}
