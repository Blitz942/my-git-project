import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PlaceNameArray {
    private PlaceNameEntry[] info;
    private int size;
    private int comparisonCount;


    public PlaceNameArray (int maxSize) {
        info = new PlaceNameEntry[maxSize];
        size = 0;
        comparisonCount = 0;
        }

    public void load(String filename, int maxRecords) throws IOException { //load in the filename and maximum amount of records along with an IOException in case anything goes wrong.
        size = 0; //reset

        BufferedReader reader = new BufferedReader(new FileReader(filename));
        reader.readLine(); //this skips the headerline at the beginning of our file

        String line;
        while ((line=reader.readLine()) != null && size < maxRecords) {
            String[] lineParts = line.split(",",5); //split the line into 5 string parts at the commas
            String placeholderPlaceName = lineParts[1].trim(); //read values into placeholder variables for the time being while we get them into the array
            String placeholderMunicipality = lineParts[2].trim();
            String placeholderProvince     = lineParts[3].trim();
            int placeholderPopulation = Integer.parseInt(lineParts[4].trim()); //parseInt converts the String of the number into an actual integer that behaves like a number to java

            info[size] = new PlaceNameEntry(placeholderPlaceName, placeholderMunicipality, placeholderProvince, placeholderPopulation); //this create a NamePlaceEntry object containing the data we just read in, and places this object in the next open spot in the PlaceNameArray which corresponds to the current size
            size++; //move to the next size up, this should be an empty space in the array
        }
        reader.close(); //close reader and allow file to be released
    }

    public PlaceNameEntry search (String targetPlacename) {
        comparisonCount = 0; //initialize the count to 0, each time a comparison is done this will increment
        for (int i = 0; i < size;i++) {
            comparisonCount++;
            if (info[i].getPlaceName().compareTo(targetPlacename)==0) {
                return info[i];
            } 
        }
        return null;
    }

    public int getComparisonCount() {
        return comparisonCount;
    }

    public int getSize() {
        return size;
    }
}