public class BSTnode {
    PlaceNameEntry entry;
    BSTnode left;
    BSTnode right;

    public BSTnode (PlaceNameEntry entry, BSTnode left, BSTnode right) {
        this.entry = entry;
        this.left = left;
        this.right = right;
    }

    public BSTnode (PlaceNameEntry entry) {
        this.entry = entry;
        this.left = null;
        this.right = null;
    }

    public BSTnode getLeftNode(BSTnode node) {
        return node.left;
    }

    public BSTnode getRightNode(BSTnode node) { //not needed as all fields are public
        return node.right;
    }
} 