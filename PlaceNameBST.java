import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PlaceNameBST {
    BSTnode root;
    int comparisonCount;

    public PlaceNameBST ()
    {
        root = null;
        comparisonCount=0;
    }
    
    public int getComparisonCount() {
        return comparisonCount;
    }

    public int getHeight() {
        return getHeight (root);
    }
    public int getHeight(BSTnode node) {
        if (node==null) 
            return -1;
        else
            return 1+Math.max(getHeight(node.left),getHeight(node.right));
    }

    public int getSize ()
    {
        return getSize (root);
    }
    public int getSize (BSTnode node) {
        if (node == null)
            return 0;
        else
            return 1+getSize(node.left)+getSize(node.right);            
        }

    public void visit(BSTnode node) {
        System.out.println(node.entry.getPlaceName());
    }

    public void insert(PlaceNameEntry data) {
        if (root == null) 
            root = new BSTnode(data);
        else
            insert(data,root);
    }
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

    public BSTnode find(String target) {
        comparisonCount = 0;
        if (root==null)
            return null;
        else
            return find(target,root);
    }
    public BSTnode find(String target, BSTnode node) {
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

    public void load(String filename, int maxRecords) throws IOException { //load in the filename and maximum amount of records along with an IOException in case anything goes wrong.
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        reader.readLine(); //this skips the headerline at the beginning of our file
        String line;
        while ((line=reader.readLine()) != null && getSize() < maxRecords) {
            String[] lineParts = line.split(",",5); //split the line into 5 string parts at the commas
            String placeholderPlaceName = lineParts[1].trim(); //read values into placeholder variables for the time being while we get them into the array
            String placeholderMunicipality = lineParts[2].trim();
            String placeholderProvince     = lineParts[3].trim();
            int placeholderPopulation = Integer.parseInt(lineParts[4].trim()); //parseInt converts the String of the number into an actual integer that behaves like a number to java
            insert(new PlaceNameEntry(placeholderPlaceName, placeholderMunicipality, placeholderProvince, placeholderPopulation)); //this create a NamePlaceEntry object containing the data we just read in, and places this object in the next open spot in the PlaceNameArray which corresponds to the current size
        }
        reader.close(); //close reader and allow file to be released
    }
}
