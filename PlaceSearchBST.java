import java.util.Scanner;
import java.io.IOException;

/**
 * Interactive command-line application for searching place names using a PlaceNameBST.
 * Allows the user to load a dataset from a CSV file and search for place names.
 */
public class PlaceSearchBST {

    /**
     * Entry point for the PlaceSearchBST application.
     * Presents a menu allowing the user to load data, search, or quit.
     *
     * @param args not used
     */
        public static void main(String[] args) {
            PlaceNameBST storageBST = new PlaceNameBST();
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
            int max = Integer.parseInt(s.nextLine());
                
            try {
                storageBST.load(filename,max);
                System.out.println("File loaded, " + storageBST.getSize() + " records read in.");
            }
            catch (IOException e) {System.out.println("There was a prolem reading this file");}
                }
            else if (option == 2) {
                System.out.println("What name would you like to search for?");
                String name = s.nextLine();
                PlaceNameEntry result = storageBST.find(name);
                if (result != null) {
                    System.out.println("Result: " + result);
                } else {
                    System.out.println("Not found.");
                }
                System.out.println("Comparison count: " + storageBST.getComparisonCount());
            }
        }
        System.out.println("Goodbye");
    }
}