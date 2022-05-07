import java.util.*;

class Galtonboard implements Runnable {
    private int[] bins;
    Random r = new Random();
    public Galtonboard(int[] bins){
        this.bins = bins;
    }

    public void run() {
        try {
            int pos = 0, choice;
            for (int j = 0; j< bins.length; j++) {
                choice = r.nextInt(2);
                if (choice==1 && pos < bins.length-1) {
                    pos += 1;
                }
            }
            bins[pos] += 1;
        }
        catch (Exception e) {
            System.out.println("Exception");
        }
    }
}

// Main Class
class Main {
    public static void main(String[] args) {

        if (Objects.equals(args[0], "-numThread") && Objects.equals(args[2], "-numBins")) {
            int[] arr = new int[Integer.parseInt(args[3])];
            int n = Integer.parseInt(args[1]);
            for (int i = 0; i < n; i++) {
                Thread thrd = new Thread(new Galtonboard(arr));
                thrd.start();
            }
            int sum = 0;
            for (int k = 0; k < arr.length; k++) {
                System.out.println(k + " " + arr[k]);
                sum += arr[k];
            }
            System.out.println("Number of requested threads: " + n);
            System.out.println("Sum of Bin Values: " + sum);
            if (sum == n) {
                System.out.println("Nice Work! Both of them are equal");
            }
        }
        else {
            System.out.println("Wrongly written. Write like the pdf");
        }
    }
}
