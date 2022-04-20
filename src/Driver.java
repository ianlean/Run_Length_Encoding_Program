/*
 * TCSS 342 - Assignment 1
 */

import java.io.*;
import java.util.Scanner;
/*
 * The driver class for using the RLE
 * conversion.
 */
public class Driver {
    private static PrintStream writeOut;
    private static RLE r;

    /*
     * The main method for this program, and starting point.
     *
     * @param args - command line arguments, ignored in this program
     */
    public static void main(String[] args) throws IOException {
        File f = new File("src/input.txt");
        File out = new File("src/output.txt");
        writeOut = new PrintStream(out);
        r = new RLE(writeOut);
        readFile(f);
    }

    /*
     * Reads the input file and sends it to the proper
     * methods to be tested on.
     *
     * @param f - the input file.
     */
    public static void readFile(File f) throws IOException {
       Scanner scan = new Scanner(f);
       File out = new File("src/output.txt");
       String testString = "";
       String s = null;
       while (scan.hasNextLine()) {
           s = scan.nextLine();
           if (s.equalsIgnoreCase("Test1:Input for Encoding")) {
               s = scan.nextLine();
               writeOut.println("Test 1:");
               while (!s.equalsIgnoreCase("Test2:Input for Decoding")) {
                   LinkedList listToEncode = stringToList(s);
                   writeOut.print("["+ listToEncode+"]"+listToEncode.size()+"\n");
                   r.encode(listToEncode);
                   s = scan.nextLine();
               }
               testString = "Test2:Input for Decoding";
           } else if (testString.equalsIgnoreCase("Test2:Input for Decoding")) {
               writeOut.println("Test2:");
               while (!s.equalsIgnoreCase("Test3:Input for Equality")) {
                   writeOut.print("["+s+"]"+" ");
                   r.decode(s);
                   s = scan.nextLine();
               }
               testString = "Test3:Input for Equality";
           } else if (testString.equalsIgnoreCase("Test3:Input for Equality")) {
               writeOut.println("Test3:");
               while (scan.hasNextLine()) {
                   writeOut.print(testEquality(s) + "\n");
                   s = scan.nextLine();
               }
           }
       }
       //for the very last line in the file
       writeOut.print(testEquality(s) + "\n");
       writeOut.close();
    }

    /*
     * Prepares the line that was scanned by splitting it
     * into two lists, so we can then send it to the equals'
     * method, lastly prints out expected output.
     * .
     *
     * @param s - the scanned line which contains
     * the two lists.
     */
    public static String testEquality(String s) {
        Scanner scan = new Scanner(s);
        int count = 1;
        LinkedList l1 = null;
        LinkedList l2 = null;
        String first = "";
        String second = "";
        // so we know which list is which
        // and get them into list form
        while (count <= 2 && scan.hasNext()) {
            if (count == 1) {
                String temp = scan.next();
                first = temp;
                l1 = decodeForCompare(temp);
            } else {
                String temp = scan.next();
                second = temp;
                l2 = decodeForCompare(temp);
            }
            count++;
        }

        //put both lists into string form
        //by themselves
        return "["+first+"]"+ " " +"["+second+"] "+r.equal(l1, l2);
    }

    public static LinkedList stringToList(String s) {
        LinkedList list = new LinkedList();
        for (int i = 0; i < s.length(); i++) {
            list.add(s.charAt(i));
        }
        return list;
    }

    public static LinkedList decodeForCompare(String encStr) {
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
        return decodedList;
    }
}
