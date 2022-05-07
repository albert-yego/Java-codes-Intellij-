import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Inputs {

        private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        public void exitMethod() throws Exception {

            System.out.println("Are you sure you want to exit? (Y/N)");
            String input = reader.readLine();
            if (input.equalsIgnoreCase("Y")) {
                System.out.println("Exiting system");
                System.exit(0);
            } else if (input.equalsIgnoreCase("N")) {
                Test.main(null);
            } else {
                System.out.print(" Please type in a valid answer.. Y/N");
                exitMethod();
            }

        }

        public void newProject() throws Exception {
            System.out.println("let's start our journey ^_^ ");
            System.out.println("lets start by the End product..");
            CompleteProject project = addEndProduct();
            System.out.println("Now lets dive into End product components..");
            System.out.println("Lets add a new component..");
            FrontEndAddNormalComponents(project);
            System.out.println(" Printing MRP project information");
            Calculations backEnd = new Calculations(project);
            for ( int i = 0 ; i < project.getComponents().size() ; i ++){
                backEnd.printCalculateScheduleForEachComponent(project.getComponents().get(i));
            }
        }

        public CompleteProject addEndProduct() throws Exception {

            System.out.print("Please specify the name of the End product: ");
            String input = reader.readLine();
            Components component = new Components();
            component.setName(input);
            component.setIDofParent(0);

            System.out.print("Please specify the ID of the  End product: ");
            input = reader.readLine();
            component.setID(Integer.parseInt(input));

            System.out.print("Please specify the number of weeks included in the project's period.. ");
            input = reader.readLine();
            CompleteProject project = new CompleteProject();
            project.setEndProduct();
            project.setNumberOfWeeks(Integer.parseInt(input));

            System.out.println("Now we will take more information regarding the End product.");
            System.out.print("Please specify the Amount On Hand for the " + component.getName() + ": ");
            input = reader.readLine();
            component.setAmountOnHand(Integer.parseInt(input));

            System.out.println("Please specify the Lot Sizing Rule for the " + component.getName() );
            System.out.println("Remember L4L is multiples of one!");
            System.out.print("Lot Sizing Rule for "+ component.getName() + " Multiples of: ");
            input = reader.readLine();
            component.setLotSizingRule(input);

            System.out.print("Please specify the Scheduled receipt for the " + component.getName() + ": ");
            input = reader.readLine();
            component.setScheduledReceipts(Integer.parseInt(input));

            System.out.print("Please specify the Arrival on week for the " + component.getName() + ": ");
            input = reader.readLine();
            component.setArrivalOnWeek(Integer.parseInt(input));

            System.out.print("Please specify the Lead time for the " + component.getName() + ": ");
            input = reader.readLine();
            component.setLeadTime(Integer.parseInt(input));

            System.out.println("Please specify the Demand for the " + component.getName() + ": ");
            for (int i = 1; i <= project.getNumberOfWeeks(); i++) {
                System.out.print("For week: " + i + " We need: ");
                input = reader.readLine();
                component.setGrossRequirement(i, Integer.parseInt(input));
            }
            project.setComponents(component);
            return project;
        }

        public CompleteProject FrontEndAddNormalComponents(CompleteProject project) throws Exception {
            while (true) {
                System.out.println("press C to add a new component or press F to finish adding components ");
                String input = reader.readLine();
                if (input.equalsIgnoreCase("c")) {
                    project = addNormalComponents(project);
                } else if (input.equalsIgnoreCase("f")) {
                    System.out.println("Are you sure all components are finished?! y/n ");
                    input = reader.readLine();
                    while (true) {
                        if (input.equalsIgnoreCase("y"))
                            return project;
                        else if (input.equalsIgnoreCase("n")) {
                            System.out.println("Then lets add more components... ");
                            return FrontEndAddNormalComponents(project);
                        } else {
                            System.out.println("Please type in a valid input");
                            input = reader.readLine();
                        }
                    }
                } else {
                    System.out.println("please type in a valid input");
                }
            }
        }

        public CompleteProject addNormalComponents(CompleteProject project) throws Exception {
            System.out.print("Please specify the name of the new Component: ");
            String input = reader.readLine();
            Components component = new Components();
            component.setName(input);

            System.out.print("Please specify the ID of the Component: ");
            input = reader.readLine();
            component.setID(Integer.parseInt(input));
            if (project.findGivenComponentWithSimilarId(Integer.parseInt(input))) {
                System.out.println("you have added this component before ");
                System.out.print("Are you sure you want to add it again ?! Y/N ");
                input = reader.readLine();
                while (true) {
                    if (input.equalsIgnoreCase("y")){
                        addDuplicatedComponent(component , project);
                        return project;
                    }
                    else if (input.equalsIgnoreCase("n")) {
                        System.out.println("Deleting component... ");
                        return project;
                    } else {
                        System.out.println("Please type in a valid input");
                        input = reader.readLine();
                    }
                }
            }

            System.out.print("Please specify the Amount On Hand for the " + component.getName() + ": ");
            input = reader.readLine();
            component.setAmountOnHand(Integer.parseInt(input));

            System.out.println("Please specify the Lot Sizing Rule for the " + component.getName() );
            System.out.println("Remember L4L is multiples of one!");
            System.out.print("Lot Sizing Rule for "+ component.getName() + " Multiples of: ");
            input = reader.readLine();
            component.setLotSizingRule(input);

            System.out.print("Please specify the Scheduled receipt for the " + component.getName() + ": ");
            input = reader.readLine();
            component.setScheduledReceipts(Integer.parseInt(input));

            System.out.print("Please specify the Arrival on week for the " + component.getName() + ": ");
            input = reader.readLine();
            component.setArrivalOnWeek(Integer.parseInt(input));

            System.out.print("Please specify the Lead time for the " + component.getName() + ": ");
            input = reader.readLine();
            component.setLeadTime(Integer.parseInt(input));

            System.out.print("Please specify the ID of the Parent for the " + component.getName() + ": ");
            input = reader.readLine();
            component.setIDofParent(Integer.parseInt(input));

            try{
            }
            catch (NullPointerException e){
                System.out.println(" Couldn't find a component with given ID ");
                System.out.print("Please specify the ID of the Parent for the " + component.getName() + ": ");
                input = reader.readLine();
                component.setIDofParent(Integer.parseInt(input));
            }


            System.out.print("Please specify How many " + component.getName() + " required to make one "
                    + project.searchComponentsById(component.getIDofParent()).getName() + ": ");
            input = reader.readLine();
            component.setMultiples(Integer.parseInt(input));

            project.setComponents(component);

            System.out.println(" Thank you for the information, component has been added");

            return project;
        }

        public void addDuplicatedComponent(Components component , CompleteProject project) throws Exception {
            Components temp = project.searchComponentsById(component.getID());
            String input ;
            System.out.print("Please specify the parent ID of this component: ");
            input = reader.readLine();
            temp.setParent2ID(Integer.parseInt(input));
            System.out.print("Please specify How many " + temp.getName() + " required to make one "
                    + project.searchComponentsById(temp.getParent2ID()).getName() + ": ");
            input = reader.readLine();
            temp.setMultiples2(Integer.parseInt(input));
            temp.setHasDuplicates(true);
        }

}
