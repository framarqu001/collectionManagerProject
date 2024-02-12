package albumcollection;

/**
 * The Rating class represents a rating for an Album on a 1-5 scale.
 * Each rating is a node. An Album will contain a linked list of ratings by
 * storing a reference to a head node.
 * 
 * @author Francisco Marquez
 */
public class Rating {
    private int star;
    private Rating next;

    /**
     * Initializes a Rating object
     * @param star int representing a rating's value
     */
    public Rating(int star) {
        this.star = star;
        this.next = null;
    }

    /**
     * @return The next rating in the list.
     */
    public Rating getNext() {
        return next;
    }

    /**
     * Sets the next rating node in the list
     * @param nextNode a rating to be added to the list.
     */
    public void setNext(Rating nextNode) {
        next = nextNode;
    }

    /**
     * @return The value of a rating. 1-5
     */
    public int getStar() {
        return star;
    }

}
