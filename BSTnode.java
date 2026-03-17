/**
 * Represents a single node in a binary search tree.
 * Each node holds a PlaceNameEntry and references to its left and right children.
 */
public class BSTnode {
    PlaceNameEntry entry;
    BSTnode left;
    BSTnode right;

    /**
     * Constructs a BSTnode with the given entry and child nodes.
     *
     * @param entry the PlaceNameEntry stored in this node
     * @param left  the left child node
     * @param right the right child node
     */

    public BSTnode (PlaceNameEntry entry, BSTnode left, BSTnode right) {
        this.entry = entry;
        this.left = left;
        this.right = right;
    }
    
/**
     * Constructs a BSTnode with the given entry and no children.
     *
     * @param entry the PlaceNameEntry stored in this node
     */
    public BSTnode (PlaceNameEntry entry) {
        this.entry = entry;
        this.left = null;
        this.right = null;
    }
} 