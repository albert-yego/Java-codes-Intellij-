import java.util.SortedMap;
import java.util.TreeMap;

public class Components {

        private String name ;
        private int ID;
        private int amountOnHand;
        private int lotSizingRule;
        private int ScheduledReceipts;
        private int ArrivalOnWeek;
        private int leadTime;
        private int parent ;
        private int multiples;
        private int parent2ID;
        private int multiples2;
        private boolean hasDuplicates;
        private final SortedMap<Integer, Integer> GrossRequirements = new TreeMap<>();
        private final SortedMap<Integer, Integer> PlannedOrderReleases = new TreeMap<>();

    public void setID(int ID) {
            this.ID = ID;
        }
        public int getID() {
            return ID;
        }

        public void setName(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }

        public void setAmountOnHand(int amountOnHand) {
            this.amountOnHand = amountOnHand;
        }
        public int getAmountOnHand() {
            return amountOnHand;
        }

        public void setLotSizingRule(String lotSizingRule) { this.lotSizingRule = Integer.parseInt(lotSizingRule); }
        public int getLotSizingRule() {
            return lotSizingRule;
        }

        public void setArrivalOnWeek(int arrivalOnWeek) { ArrivalOnWeek = arrivalOnWeek; }
        public int getArrivalOnWeek() {
            return ArrivalOnWeek;
        }

        public void setLeadTime(int leadTime) {
            this.leadTime = leadTime;
        }
        public int getLeadTime() {
            return leadTime;
        }

        public void setIDofParent(int parent) {
            this.parent = parent;
        }
        public int getIDofParent() {
            return parent;
        }

        public void setScheduledReceipts(int scheduledReceipts) {
            ScheduledReceipts = scheduledReceipts;
        }
        public int getScheduledReceipts() {
            return ScheduledReceipts;
        }

        public void setMultiples(int multiples) {
            this.multiples = multiples;
        }
        public int getMultiples() {
            return multiples;
        }

        public void setParent2ID(int parent2ID) { this.parent2ID = parent2ID; }
        public int getParent2ID() { return parent2ID; }

        public void setMultiples2(int multiples2) { this.multiples2 = multiples2; }
        public int getMultiples2() { return multiples2; }

        public void setHasDuplicates(boolean hasDuplicates) { this.hasDuplicates = hasDuplicates; }
        public boolean isHasDuplicates() { return hasDuplicates; }

        /** -------------------------------------------------------------------*/
        public void setGrossRequirement( Integer week, Integer GrossRequirement) { GrossRequirements.put( week, GrossRequirement) ; }
        public SortedMap<Integer, Integer> getGrossRequirement() { return GrossRequirements; }
        /** -------------------------------------------------------------------*/
        public void setPlannedOrderReleases( Integer week, Integer PlannedOrderRelease) { PlannedOrderReleases.put( week, PlannedOrderRelease) ; }
        public SortedMap<Integer, Integer> getPlannedOrderReleases() { return PlannedOrderReleases; }


}
