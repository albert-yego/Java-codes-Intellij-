import java.util.SortedMap;
import java.util.TreeMap;

public class Calculations {

        private final CompleteProject project ;
        private final SortedMap<Integer, Integer> calculatedGrossRequirements;

    {
        calculatedGrossRequirements = new TreeMap<>();
    }

    private final SortedMap<Integer, Integer> calculatedScheduledReceipts;

    {
        calculatedScheduledReceipts = new TreeMap<>();
    }

    private final SortedMap<Integer, Integer> calculatedOnHandFromPriorPeriod;

    {
        calculatedOnHandFromPriorPeriod = new TreeMap<>();
    }

    private final SortedMap<Integer, Integer> calculatedNetRequirements = new TreeMap<>();
        private final SortedMap<Integer, Integer> calculatedTimePhasedNetRequirements = new TreeMap<>();
        private final SortedMap<Integer, Integer> calculatedPlannedOrderReleases = new TreeMap<>();
        private final SortedMap<Integer, Integer> calculatedPlannedOrderDelivers = new TreeMap<>();

        public Calculations(CompleteProject project){ this.project = project; }

    public SortedMap<Integer, Integer> getCalculatedGrossRequirements() {
            return calculatedGrossRequirements;
        }

    public SortedMap<Integer, Integer> getCalculatedScheduledReceipts() {
            return calculatedScheduledReceipts;
        }

    public SortedMap<Integer, Integer> getCalculatedOnHandFromPriorPeriod() {
            return calculatedOnHandFromPriorPeriod;
        }

    public SortedMap<Integer, Integer> getCalculatedNetRequirements() {
            return calculatedNetRequirements;
        }

    /**--------------------------------------------------------------------------*/
        public SortedMap<Integer, Integer> getCalculatedPlannedOrderDelivers() {
            return calculatedPlannedOrderDelivers;
        }

    public SortedMap<Integer, Integer> getCalculatedPlannedOrderReleases() {
            return calculatedPlannedOrderReleases;
        }

        /**--------------------------------------------------------------------------*/




        public void calculateGrossRequirements (Components component , int i){
            int value;
            if ( component.isHasDuplicates()){
                calculateDuplicatedGrossRequirements(component , i);
            }
            else{
                int idOfParent = component.getIDofParent();
                if (idOfParent== 0 ){
                    value = component.getGrossRequirement().get(i);
                }
                else {
                    Components parentComponent = project.searchComponentsById(idOfParent);
                    value = parentComponent.getPlannedOrderReleases().get(i) * component.getMultiples();
                    component.setGrossRequirement(i, value);
                }
                this.calculatedGrossRequirements.put(i , value );
            }
        }

        public void calculateDuplicatedGrossRequirements(Components component , int i){
            int value;
            int idOfParent = component.getIDofParent();
            Components parentComponent = project.searchComponentsById(idOfParent);
            int grossRequirement1 = parentComponent.getPlannedOrderReleases().get(i) * component.getMultiples();

            int idOfParent2 = component.getParent2ID();
            Components parentComponent2 = project.searchComponentsById(idOfParent2);
            int grossRequirement2 = parentComponent2.getPlannedOrderReleases().get(i) * component.getMultiples2();
            value = (grossRequirement1 + grossRequirement2);
            this.calculatedGrossRequirements.put(i , value );
        }

        public void calculateScheduledReceipts (Components component , int i ){
            int value = 0;
            if( component.getArrivalOnWeek() == i)
                value= component.getScheduledReceipts();
            this.calculatedScheduledReceipts.put(i,value);
        }

        public void calculateOnHandFromPriorPeriod(Components component, int i){
            int value = component.getAmountOnHand();
            if (i != 1) {
                value = (this.getCalculatedOnHandFromPriorPeriod().get(i - 1) + this.getCalculatedScheduledReceipts().get(i - 1)
                        - (this.getCalculatedGrossRequirements().get(i - 1) - this.getCalculatedPlannedOrderDelivers().get(i - 1)));
            }
            this.calculatedOnHandFromPriorPeriod.put(i,value);
        }

        public void calculateNetRequirement(int i){
            int value;
            value= getCalculatedGrossRequirements().get(i) - (getCalculatedScheduledReceipts().get(i) +
                    getCalculatedOnHandFromPriorPeriod().get(i));
            if ( value <= 0 )
                value= 0;
            this.calculatedNetRequirements.put(i , value);
        }

        public void calculatePlannedOrderRelease(Components component , int i ){
            int value = 0;
            if(i <= (project.getNumberOfWeeks() - component.getLeadTime())){
                value = getCalculatedPlannedOrderDelivers().get(i + component.getLeadTime());
            }
            this.calculatedPlannedOrderReleases.put(i,value);
            component.setPlannedOrderReleases(i , getCalculatedPlannedOrderReleases().get(i));
        }

        public void calculatedTimePhasedNetRequirements(int i){
            int value = this.getCalculatedPlannedOrderReleases().get(i);
            this.calculatedTimePhasedNetRequirements.put(i , value);
        }

        public void calculatePlannedOrderDelivery(Components component , int i ){
            int value = ((getCalculatedNetRequirements().get(i)/component.getLotSizingRule()));
            if(getCalculatedNetRequirements().get(i) % component.getLotSizingRule() == 0 )
            {
                this.calculatedPlannedOrderDelivers.put(i , value);

            }
            else{
                this.calculatedPlannedOrderDelivers.put(i ,((value + 1)*component.getLotSizingRule()));
            }
        }


        public void printCalculateScheduleForEachComponent (Components component ){

            for(int i = 1 ; i <= project.getNumberOfWeeks() ; i ++ ) {
                this.calculateGrossRequirements(component , i);
                this.calculateScheduledReceipts(component , i);
                this.calculateOnHandFromPriorPeriod(component , i);
                this.calculateNetRequirement(i);
                this.calculatePlannedOrderDelivery(component , i);
            }
            for(int i = 1 ; i <= project.getNumberOfWeeks() ; i ++ ) {
                this.calculatePlannedOrderRelease(component, i);
                this.calculatedTimePhasedNetRequirements(i);
            }

            System.out.println(" For Component " + component.getName() + " off ID " + component.getID()+ " we need...");
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            System.out.printf("%10s %20s" , "Weeks", "|");
            for(int i = 1 ; i <= project.getNumberOfWeeks() ; i ++ ) {
                System.out.printf("%10d", i, "|"); }
            System.out.println();
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            System.out.printf("%10s %12s" , "Gross Requirements", "|");
            for(int i = 1 ; i <= project.getNumberOfWeeks() ; i ++ ) {
                System.out.printf("%10d",this.calculatedGrossRequirements.get(i) , "|"); }
            System.out.println();
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            System.out.printf("%10s %11s", "Scheduled Receipts ", "|");
            for(int i = 1 ; i <= project.getNumberOfWeeks() ; i ++ ) {
                System.out.printf("%10d", this.calculatedScheduledReceipts.get(i) , "|"); }
            System.out.println();
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            System.out.printf("%10s %4s", "On Hand From Prior Period ", "|");
            for(int i = 1 ; i <= project.getNumberOfWeeks() ; i ++ ) {
                System.out.printf("%10d", this.calculatedOnHandFromPriorPeriod.get(i) , "|"); }
            System.out.println();
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            System.out.printf("%10s %14s", "Net Requirements", "|");
            for(int i = 1 ; i <= project.getNumberOfWeeks() ; i ++ ) {
                System.out.printf("%10d",this.calculatedNetRequirements.get(i) , "|"); }
            System.out.println();
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            System.out.printf("%10s %2s", "Time-Phased Net Requirements", "|");
            for(int i = 1 ; i <= project.getNumberOfWeeks() ; i ++ ) {
                System.out.printf("%10d", this.calculatedTimePhasedNetRequirements.get(i), "|"); }
            System.out.println();
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            System.out.printf("%10s %8s", "Planned Order Releases", "|");
            for(int i = 1 ; i <= project.getNumberOfWeeks() ; i ++ ) {
                System.out.printf("%10d",this.calculatedPlannedOrderReleases.get(i) , "|"); }
            System.out.println();
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            System.out.printf("%10s %8s", "Planned Order Delivery", "|");
            for(int i = 1 ; i <= project.getNumberOfWeeks() ; i ++ ) {
                System.out.printf("%10d",this.calculatedPlannedOrderDelivers.get(i) , "|"); }
            System.out.println();
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        }



}
