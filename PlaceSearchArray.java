import java.util.Scanner;
import java.io.IOException;
public class PlaceSearchArray {
    public static void main(String[] args) {
    PlaceNameArray storageArray = new PlaceNameArray(15000);

        Scanner s = new Scanner(System.in);
        int option = 0;
        while(option!=3) {
            System.out.println("1. Load Data from file");
            System.out.println("2. Search Dataset");
            System.out.println("3. Quit");
            System.out.println("Enter choice: ");
            option = Integer.parseInt(s.nextLine());
        
        if (option == 1) {
            System.out.println("What is the name of file to be read in?");
            String filename=s.nextLine();
            
            System.out.println("How many records would you like read in?");
            int max= Integer.parseInt(s.nextLine());
            try {
            storageArray.load(filename,max);
            System.out.println("File loaded, " + storageArray.getSize() + " records read in.");
            } catch (IOException e) {
                System.out.println("There was a prolem reading this file");
            }
            

            } 
        else if (option == 2) {
            System.out.println("What name would you like to search for?");
            String name = s.nextLine();
            PlaceNameEntry result = storageArray.search(name);
            if (result != null) {
            System.out.println("Result: " + result);
                } 
            else {
                System.out.println("Result: Null");
                }
            System.out.println("Comparison count: " + storageArray.getComparisonCount());
        } 
    
    }
    System.out.println("Goodbye");
}
}