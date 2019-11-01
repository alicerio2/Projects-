package Lab5;

/*************************************************************************
 * This file is where you will have to complete your assignment.
 * Here, you will write methods that do the following:
 * 1/ read student loaner information from a file and build a linked list
 *      of dossiers from there
 * 2/ identify a node that contains the dossier of a student with a given ID
 *      Your method is expected to be RECURSIVE
 * 3/ transfer the data from a linked list to an array
 * 4/ sort the data in the array of loaner dossiers between returned and
 *      not returned laptops
 *      This method should be inspired by QUICK SORT
 * 5/ complete the main method as prompted
 * Note: in all your work below, you will be expected to use the methods
 * already defined in the files provided to you (Student.java, Dossier.java,
 * Node.java, LList.java). You are not to redefined functionalities that
 * already exist.
 *************************************************************************/

/*
 *
 * @author mceberio
 * @student Alan Licerio
 *
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class LoanerProgram {

    /*
     * TASK 1.
     * @param filename
     * @return a linked list of Loaner Dossiers whose information was read in the file called filename
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static LList<Dossier> readStudentInfo(String filename) throws FileNotFoundException, IOException {
		
    	LList<Dossier> loanerList = new LList<>();
    	FileReader fr = new FileReader(filename);
        BufferedReader textReader = new BufferedReader(fr);

        int id, dateBorrowed = 0 , dateReturned;
        String name;
        Student student = new Student();
        Dossier dossier = new Dossier(student, dateBorrowed);
        Node <Dossier> node = new Node<>(dossier);

        while (textReader.ready()) {
            String a = textReader.readLine();
            if (a.equals("Dossier")) {
                name = textReader.readLine();
                id = Integer.parseInt(textReader.readLine());
                dateBorrowed = Integer.parseInt(textReader.readLine());
                student = new Student();
                student.setName(name);
                student.setID(id);

                dossier = new Dossier(student, dateBorrowed);

                dossier.setStudent(student);
                dossier.setDateBorrowed(dateBorrowed);

                node = new Node<>(dossier);
                loanerList.addStart(node);
            }
            else if(a.equals("Update")) {
                id = Integer.parseInt(textReader.readLine());
                dateReturned = Integer.parseInt(textReader.readLine());
                Node<Dossier> returnNode = findStudentWithGivenID(node,id);
                Dossier returnDossier = returnNode.getData();
                returnDossier.setDateReturned(dateReturned);
            }
        }
        textReader.close();
        return loanerList;
    }
 
    /*
     * TASK 2.
     * Takes: a node of a Loaner Dossier and a student ID 
     * Returns: the node that corresponds to this given student's loan
     * This method MUST be used (called) within the above method: readStudentInfo
     */
    public static Node<Dossier> findStudentWithGivenID(Node<Dossier> myNode, int ID) {
        if (myNode == null) {
            return null;
        }
        Dossier dossier = myNode.getData();
        Student student = dossier.getStudent();
        int id = student.getID();
        if (id == ID) {
            return myNode;
        }
        return findStudentWithGivenID(myNode.getNext(),ID);
    }
    
    /*
     * TASK 3.
     * Takes: a linked list of Loaner Dossiers 
     * Returns: an array of the size of the linked list, and that contains the same data
     *      as the linked list
     */
    public static Dossier[] createArrayOfLoanerDossiers(LList<Dossier> myList) {
        Dossier[] array = new Dossier[myList.getSize()];
        Node <Dossier> head = myList.getHead();
        Dossier dossier = head.getData();
        for (int i = 0; i < array.length; i++) {
            array[i] = dossier;
            if (head.getNext() != null) {
                head = head.getNext();
                dossier = head.getData();
            }
        }
        return array;
    }
    
    /*
     * TASK 4.
     * Takes an array of Loaner Dossiers
     * Re-arranges this array so that all non-returned Dossiers are at the start of the array and all returned are at the end
     * Hint: use a Quick Sort like approach
     */
    public static void sortByNotReturned(Dossier[] myList) {
        int highIndex = myList.length -1;
        int lowIndex = 0;
        int pivot = myList[0].getDateReturned();

        while (lowIndex <= highIndex) {
            while (myList[lowIndex].getDateReturned() <= pivot) {
                lowIndex ++;
            }
            while (myList[highIndex].getDateReturned() > pivot) {
                highIndex--;
            }
            if (lowIndex <= highIndex) {
                    Dossier temp = myList[lowIndex];
                    myList[lowIndex] = myList[highIndex];
                    myList[highIndex] = temp;
                    lowIndex++;
                    highIndex--;
            }
        }
    }

    /*
     * TASK 5.
     * Takes an array of Loaner Dossiers and an I.D.
     * Checks if the I.D. exists within the array. If so, returns true
     * If the I.D. is not on the array it returns false
     */
    public static boolean hasBorrowed (int id, Dossier[] myArray) {
        for (int i = 0; i < myArray.length; i++) {
            Student student = myArray[i].getStudent();
            int arrayID = student.getID();
            if (arrayID == id) {
                return true;
            }
        }
        return false;
    }

    /*
     * TASK 6.
     * Takes an array of Dossiers
     * Checks if a Dossier has returned the laptop borrowed, if so it checks for the Dossier who kept it
     * the longest.
     * Returns that Student.
     */
    public static Student keptLaptopLongest (Dossier[] myArray) {
        Student student = new Student();
        int time, max = 0;
        for (int i = 0; i < myArray.length; i++) {
            if (myArray[i].getDateReturned() > 1) {
                time = myArray[i].getDateReturned() - myArray[i].getDateBorrowed();
                if (max < time) {
                    max = time;
                    student = myArray[i].getStudent();
                }
            }
        }
        return student;
    }

    /*
     * TASK 7.
     * Takes a Linked List of Dossiers and integer n
     * Returns a new LinkedList of n Nodes of Dossiers.
     */
    public static Dossier[] latestBorrowed (LList<Dossier> myList, int n) {
        Dossier[] array = new Dossier[n];
        Node<Dossier> node = myList.getHead();
        Dossier dossier = node.getData();
        for (int i = 0; i < array.length; i++) {
            array[i] = dossier;
            if (node.getNext() != null) {
                node = node.getNext();
                dossier = node.getData();
            }
        }
        return array;
    }
    
    /*
     * TASK 8.
     * Complete the main method as prompted below
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        String filename = "loanerInfo.txt";

        // Step 1. Create a linked list of dossiers from reading the file loanerInfo.txt
        LList <Dossier> list = readStudentInfo(filename);

        // Step 2. Print the content of this linked list
        System.out.println("---Printing content of Linked List---");
        list.print();

        // Step 3. Create an array of dossiers from this linked list
        Dossier[] array = createArrayOfLoanerDossiers(list);

        // Step 4. Sort the dossiers between not_returned (first part of the array) and returned (last part of the array)
        sortByNotReturned(array);

        // Step 5. Print the content of the array
        System.out.println("---Printing content of SORTED array---");
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }

        // Step 6. Check if a given Student has borrowed a laptop.
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the I.D. of a student to check if they borrowed a laptop.");
        int id = input.nextInt();
        boolean borrowed = hasBorrowed(id,array);
        if (borrowed) {
            System.out.println("Student " + id + " has borrowed a laptop.");
        }
        else {
            System.out.println("Student " + id + " has not borrowed a laptop.");
        }

        // Step 7. Print the student who kept a laptop the longest.
        System.out.println(" ");
        System.out.println("---The student who kept a laptop the longest is:");
        Student student = keptLaptopLongest(array);
        String longestLaptop = student.toString();
        System.out.println(longestLaptop);

        // Step 8. Print the latest n Dossiers.
        System.out.println("---Printing latest n Dossiers---");
        Dossier[] newArray = latestBorrowed(list,1);
        for (int i = 0; i < newArray.length; i++) {
            System.out.println(newArray[i]);
        }
    }
}
