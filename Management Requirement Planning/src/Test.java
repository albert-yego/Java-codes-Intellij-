import java.util.*;

public class Test {

        public static void main(String[] args) throws Exception {

            System.out.println("-------------- Welcome to MRP programme ---------------");
            System.out.println("if you want to start a new project press N or Press X if you want to exit");
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            run(input);

        }
        public static void run(String scan) throws Exception{
            Inputs printing = new Inputs();
            while (true) {
                if (scan.equalsIgnoreCase("X")) {
                    printing.exitMethod();
                } else if (scan.equalsIgnoreCase("N")) {
                    printing.newProject();
                } else {
                    System.out.println("Please type in a valid answer.. ");
                    main(null);
                }
            }
        }

}
