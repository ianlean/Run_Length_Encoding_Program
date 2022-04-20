/*
 * TCSS 342 - Assignment 1
 */


import java.io.PrintStream;

/*
 * A class that contains methods for encoding
 * and decoding lists.
 */
public class RLE {
    /*
     * A PrintStream object to write to files
     */
    private final PrintStream writer;

    /*
     * The constructor for this class.
     *
     * @param theWriter - the PrintStream to be used
     */
    public RLE(PrintStream theWriter) {
        this.writer = theWriter;
    }

    /*
     * Takes in a list and encodes it into a RLE string
     * then prints it out.
     *
     * @param list - the list to be encoded.
     */
    public void encode(LinkedList list) {
        String convert = list.pureString();
        int count = 1;
        StringBuilder encodedStr = new StringBuilder();
        for (int i = 0; i < convert.length(); i++) {
            if (i > 0) {
                if (convert.charAt(i) == convert.charAt(i - 1)
                        && i < convert.length() - 1) {
                    count++;
                } else {
                    encodedStr.append(count).append(convert.charAt(i-1));
                    count = 1;
                }
            }
        }
        writer.print("["+encodedStr+"] ["+
                String.format("%1$,.2f",
                        ((double)list.size()/encodedStr.length()))+"]"+"\n");
    }

    /*
     * Takes in an encoded string and decodes it into a linked
     * list and prints said list.
     *
     * @param encStr - The encoded string which will be decoded
     */
    public void decode(String encStr) {
        LinkedList decodedList = new LinkedList();
        int n = 0;
        int base = 1;
        int index = 0;
        while (n < encStr.length()) {
            if (Character.isDigit(encStr.charAt(n))) {
                index = base*index + Character.getNumericValue(encStr.charAt(n));
                base*=10;
            } else {
                int itr = 0;
                while (itr < index) {
                    decodedList.add(encStr.charAt(n));
                    itr++;
                }
                base = 1;
                index = 0;
            }
            n+=1;
        }
        writer.print("["+decodedList+"]"+"\n");
    }

    /*
     * The main method for this program, and starting point.
     *
     * @param l1 - first list we are going to compare.
     * @param l2 - second list we are going to compare.
     */
    public boolean equal(LinkedList l1, LinkedList l2) {
        if (l1 == null || l2 == null) {
            return false;
        }
        LinkedList.ListNode n1 = l1.head;
        LinkedList.ListNode n2 = l2.head;
        while (n1 != null && n2 != null) {
            if (n1.data != n2.data) {
                return false;
            }
            n1 = n1.next;
            n2 = n2.next;
        }
        return true;
    }
}