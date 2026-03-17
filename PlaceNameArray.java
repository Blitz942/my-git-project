import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * A data structure that stores PlaceNameEntry records in an unsorted array.
 * Supports loading from a CSV file and linear search by place name.
 */
public class PlaceNameArray {
    private PlaceNameEntry[] info;
    private int size;
    private int comparisonCount;

    /**
     * Constructs a PlaceNameArray with a given maximum capacity.
     *
     * @param maxSize the maximum number of records the array can hold
     */
    public PlaceNameArray (int maxSize) {
        info = new PlaceNameEntry[maxSize];
        size = 0;
        comparisonCount = 0;
        }

    /**
     * Loads records from a CSV file into the array.
     * Resets the array before loading. Reads up to maxRecords records,
     * skipping the header line. Expected CSV column order: id, placename,
     * municipality, province, population.
     *
     * @param filename   the path to the CSV file to read from
     * @param maxRecords the maximum number of records to load
     * @throws IOException if the file cannot be found or read
     */
    public void load(String filename, int maxRecords) throws IOException { 
        size = 0; //reset

        BufferedReader reader = new BufferedReader(new FileReader(filename));
        reader.readLine(); 

        String line;
        while ((line=reader.readLine()) != null && size < maxRecords) {
            String[] lineParts = line.split(",",5); 
            String placeholderPlaceName = lineParts[1].trim(); 
            String placeholderMunicipality = lineParts[2].trim();
            String placeholderProvince     = lineParts[3].trim();
            int placeholderPopulation = Integer.parseInt(lineParts[4].trim()); 

            info[size] = new PlaceNameEntry(placeholderPlaceName, placeholderMunicipality, placeholderProvince, placeholderPopulation);
            size++;
        }
        reader.close(); 
    }

    /**
     * Performs a linear search for a place name in the array.
     * Resets and updates the comparison count with each call.
     *
     * @param targetPlacename the place name to search for
     * @return the matching PlaceNameEntry if found, null otherwise
     */
    public PlaceNameEntry search (String targetPlacename) {
        comparisonCount = 0; 
        for (int i = 0; i < size;i++) {
            comparisonCount++;
            if (info[i].getPlaceName().compareTo(targetPlacename)==0) {
                return info[i];
            } 
        }
        return null;
    }

    /**
     * Returns the number of comparisons made during the last search.
     *
     * @return the comparison count from the most recent search
     */
    public int getComparisonCount() {
        return comparisonCount;
    }
    
    /**
     * Returns the number of records currently stored in the array.
     *
     * @return the current number of records
     */
    public int getSize() {
        return size;
    }
}