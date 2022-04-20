/*
 * TCSS 342 - Assignment 1
 */

/*
 * A class to represent a linked list
 */
public class LinkedList {

    /*
     * A pointer storing front of the list.
     */
    ListNode head = null;

    /*
     * A pointer storing rear of the list.
     */
    ListNode rear = null;

    /*
     * Stores the size of the list.
     */
    private int theSize;

    /*
     * Constructor for this class, no parameters needed.
     */
    public LinkedList() {
    }

    /*
     * A class the represents a list node.
     * The building blocks for a list.
     */
    public static class ListNode {

        /*
         * What the node stores.
         */
        Object data;

        /*
         * A pointer to its next node.
         */
        ListNode next;

        /*
         * The constructor for a list node.
         *
         * @param theData - the data it will store.
         */
        public ListNode(final Object theData) {
            this.data = theData;
            this.next = null;
        }
    } //end of node class

    /*
     * Adds and element to the linked list.
     *
     * @param theData - what we want to be stored
     * in the nex node we are adding.
     */
    public void add(Object theData) {
        if (isEmpty()) {
            head = new ListNode(theData);
            rear = head;
        } else {
            rear.next = new ListNode(theData);
            rear = rear.next;
        }
        theSize++;
    }

    /*
     * returns the size of the list.
     *
     * @return the size of the list.
     */
    public int size() {
        return theSize;
    }

    /*
     * returns true if the list is empty.
     *
     * @return if the list is empty
     */
    public boolean isEmpty() {
        return head == null;
    }

    /*
     * returns a string representation of this list.
     */
    public String toString() {
        if (head == null) {
            return "null";
        }
        ListNode cur = head.next;
        StringBuilder str = new StringBuilder("" + head.data);
        while (cur != null) {
            str.append("->").append(cur.data);
            cur = cur.next;
        }
        return str.toString();
    }

    /*
     * Returns a pure string of this list,
     * makes it easier to work with
     */
    public String pureString() {
        if (head == null) {
            return "null";
        }
        ListNode cur = head;
        StringBuilder str = new StringBuilder();
        while (cur != null) {
            str.append(cur.data);
            cur = cur.next;
        }
        return str.toString();
    }
}
