import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * A binary search tree data structure for storing PlaceNameEntry records.
 * Records are ordered alphabetically by place name.
 * Supports loading from a CSV file and BST search by place name.
 */
public class PlaceNameBST {
    private BSTnode root;
    private int comparisonCount;

    /**
     * Constructs an empty PlaceNameBST.
     */
    public PlaceNameBST ()
    {
        root = null;
        comparisonCount=0;
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
     * Returns the height of the BST.
     * An empty tree has height -1, a single node has height 0.
     *
     * @return the height of the tree
     */
    public int getHeight() {
        return getHeight (root);
    }

    /**
     * Recursively computes the height of the subtree rooted at the given node.
     *
     * @param node the root of the subtree
     * @return the height of the subtree
     */
    public int getHeight(BSTnode node) {
        if (node==null) 
            return -1;
        else
            return 1+Math.max(getHeight(node.left),getHeight(node.right));
    }

    /**
     * Returns the number of records currently stored in the BST.
     *
     * @return the total number of nodes in the tree
     */
    public int getSize ()
    {
        return getSize (root);
    }

    /**
     * Recursively counts the number of nodes in the subtree rooted at the given node.
     *
     * @param node the root of the subtree
     * @return the number of nodes in the subtree
     */
    public int getSize (BSTnode node) {
        if (node == null)
            return 0;
        else
            return 1+getSize(node.left)+getSize(node.right);            
        }


        /**
     * Prints the place name stored at the given node.
     *
     * @param node the node to visit
     */
    public void visit(BSTnode node) {
        System.out.println(node.entry.getPlaceName());
    }

    /**
     * Inserts a PlaceNameEntry into the BST.
     * Duplicate place names are ignored.
     *
     * @param data the PlaceNameEntry to insert
     */
    public void insert(PlaceNameEntry data) {
        if (root == null) 
            root = new BSTnode(data);
        else
            insert(data,root);
    }

    /**
     * Recursively inserts a PlaceNameEntry into the subtree rooted at the given node.
     * Duplicate place names are ignored.
     *
     * @param data the PlaceNameEntry to insert
     * @param node the root of the subtree to insert into
     */
    public void insert(PlaceNameEntry data, BSTnode node) {
        if (data.getPlaceName().compareTo(node.entry.getPlaceName()) < 0) 
            if (node.left==null)
                node.left=new BSTnode(data);
            else
                insert(data,node.left);
        else if (data.getPlaceName().compareTo(node.entry.getPlaceName()) > 0)
            if (node.right==null)
                node.right = new BSTnode(data);
            else 
                insert(data,node.right);
    }

    /**
     * Searches the BST for a place name.
     * Resets and updates the comparison count with each call.
     *
     * @param target the place name to search for
     * @return the matching PlaceNameEntry if found, null otherwise
     */
    public PlaceNameEntry find(String target) {
    comparisonCount = 0;
    BSTnode result = find(target, root);
    return (result == null) ? null : result.entry;
    }

    /**
     * Recursively searches the subtree rooted at the given node for a place name.
     *
     * @param target the place name to search for
     * @param node   the root of the subtree to search
     * @return the matching BSTnode if found, null otherwise
     */
    private BSTnode find(String target, BSTnode node) {
        if (target.compareTo(node.entry.getPlaceName())==0) {
            comparisonCount++;
            return node;
        }
        else if (target.compareTo(node.entry.getPlaceName())<0) {
            comparisonCount++;
            return (node.left==null) ? null : find(target,node.left);
        }
        else {
            comparisonCount++;
            return (node.right==null) ? null : find(target,node.right);
        }
    }

    /**
     * Loads records from a CSV file into the BST.
     * Resets the tree before loading. Reads up to maxRecords records,
     * skipping the header line. Expected CSV column order: id, placename,
     * municipality, province, population.
     *
     * @param filename   the path to the CSV file to read from
     * @param maxRecords the maximum number of records to load
     * @throws IOException if the file cannot be found or read
     */
    public void load(String filename, int maxRecords) throws IOException { //load in the filename and maximum amount of records along with an IOException in case anything goes wrong.
        root = null;
        int count = 0;
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        reader.readLine(); //this skips the headerline at the beginning of our file
        String line;
        while ((line=reader.readLine()) != null && count < maxRecords) {
            String[] lineParts = line.split(",",5); //split the line into 5 string parts at the commas
            String placeholderPlaceName = lineParts[1].trim(); //read values into placeholder variables for the time being while we get them into the array
            String placeholderMunicipality = lineParts[2].trim();
            String placeholderProvince     = lineParts[3].trim();
            int placeholderPopulation = Integer.parseInt(lineParts[4].trim()); //parseInt converts the String of the number into an actual integer that behaves like a number to java
            insert(new PlaceNameEntry(placeholderPlaceName, placeholderMunicipality, placeholderProvince, placeholderPopulation)); //this create a NamePlaceEntry object containing the data we just read in, and places this object in the next open spot in the PlaceNameArray which corresponds to the current size
            count++;
        }
        reader.close(); //close reader and allow file to be released
    }
}
