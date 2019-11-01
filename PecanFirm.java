/* CS1101 – Intro to Computer Science
Instructor: Villanueva
Comprehensive Lab 2
By including my name below, I confirm that:
- I am submitting my original work.
- If I include code obtained from another source or I received help I am giving attribution to those sources as comments.
- This submission does not incur in any academic dishonesty practice as described in the course syllabus.
 Modified and submitted by: Alan Licerio.
*/

import java.io.*;
import java.util.*;

public class PecanFirm {
    private Pecan[] trees; // Array of type Pecan.
    private int numOfTrees = 0; // Holds the number of trees.
    private double sizeOfFirm; // Stores the size of the firm.

    /*
     * Default Constructor.
     */
    public PecanFirm(int capacity) {
        trees = new Pecan[capacity];
    }

    /*
     * This method is in charge of adding each tree from the .txt file to the array,
     * by iterating through the file and splitting at a given point.
     */
    public void readFromFile() throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the name of the file that you want to open:");

        String openFile = input.nextLine();
        File fileName = new File(openFile);
        Scanner fileInput = new Scanner(fileName);
        String eachLine;

        sizeOfFirm = fileInput.nextDouble();
        fileInput.nextLine();

        while (fileInput.hasNextLine()) {
            eachLine = fileInput.nextLine();
            String[] temp = eachLine.split(", ");

            int id = Integer.parseInt(temp[0]);
            int age = Integer.parseInt(temp[1]);
            boolean health = !temp[2].equals("No");
            int spread = Integer.parseInt(temp[3]);
            double yield = Double.parseDouble(temp[4]);

            Pecan myTree = new Pecan(id, age, health, spread, yield);
            addMyTree(myTree);
        }
    }

    /*
     * This method checks if there is enough space to add a new tree to the PecanFirm array.
     * If there is space, it adds it to the firm.
     * If there is no space, it will throw an exception.
     */
    public void addMyTree(Pecan myTree) {
        try {
            trees[numOfTrees] = myTree;
            numOfTrees++;
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ERROR " + e);
            System.out.println("Can't add tree.");
        }
    }


    /*
     * This method calculates the yield per acre by adding all of the yield values from all the
     * trees. It then divides the result by the size of the firm.
     */
    public double yieldPerAcre() {
        double totalYield = 0;
        double totalYieldAcre = 0;
        for (int i = 0; i < numOfTrees; i++) {
            totalYield += trees[i].getYield();
            totalYieldAcre = (totalYield/sizeOfFirm);
        }
        return totalYieldAcre;
    }

    /*
     * This method prints all the trees who have a spread of 80 or more.
     */
    public void displayPruning() {
        boolean flag = true;
        for (int i = 0; i < numOfTrees; i++) {
            if (trees[i].needsPruning()) {
                trees[i].printTree();
                flag = false;
            }
        }
        if (flag) {
            System.out.println("No trees need pruning.");
        }
    }

    /*
     * This method changes the information of a given tree by asking the user for the ID of the tree to-be-updated.
     * It then asks the user to select which characteristic to change and to enter the new value of that selection.
     * Finally, it prints the updated tree.
     */
    public void updateInformation() {
        Scanner input = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            System.out.println("Enter the ID of the tree you want to update");
            int id = input.nextInt();
            for (int i = 0; i < numOfTrees; i++) {
                if (trees[i].getid() == id) {
                    System.out.println("Select what you would like to update:");
                    System.out.println(" a) age\n b) condition\n c) spread\n d) yield");
                    String option = input.next();
                    if (option.equals("a")) {
                        System.out.println("Enter the new age of the tree");
                        int newAge = input.nextInt();
                        trees[i].setAge(newAge);
                        trees[i].printTree();
                        flag = false;
                    }
                    else if (option.equals("b")) {
                        if (trees[i].getHealthy()) {
                            trees[i].setHealthy(false);
                            trees[i].printTree();
                        } else {
                            trees[i].setHealthy(true);
                            trees[i].printTree();
                        }
                        flag = false;
                    }
                    else if (option.equals("c")) {
                        System.out.println("Enter the new spread of the tree. (In whole numbers).");
                        int newSpread = input.nextInt();
                        trees[i].setSpread(newSpread);
                        trees[i].printTree();
                        flag = false;
                    }
                    else if (option.equals("d")) {
                        System.out.println("Enter the new yield of the tree");
                        double newYield = input.nextDouble();
                        trees[i].setYield(newYield);
                        trees[i].printTree();
                        flag = false;
                    } else {
                        System.out.println("Going back to Main Menu.");
                    }
                }
            }
        }
    }

    /*
     * It asks the user for a number and prints the trees whose yield is greater than or equal to that number.
     */
    public void findYield() {
        boolean flag = true;
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a number to find trees with the same or greater yield.");
        double findYield = input.nextDouble();
        for (int i = 0; i < numOfTrees; i++) {
            if (trees[i].getYield() >= findYield) {
                trees[i].printTree();
                flag = false;
            }
        }
        if (flag) {
            System.out.println("No trees found.");
        }
    }

    /*
     * It asks the user for a number and prints the trees whose spread is greater than or equal to that number.
     * If the user enters a double, an exception will be thrown.
     */
    public void findSpread() {
        boolean flag = true;
        int findSpread;
        Scanner input = new Scanner(System.in);
        Scanner newInput = new Scanner(System.in);
        System.out.println("Enter a whole number to find trees with the same or greater spread.");
        try {
            findSpread = input.nextInt();
        }
        catch (InputMismatchException e) {
            System.out.println("ERROR!! PLEASE ENTER A WHOLE NUMBER!!!");
            findSpread = newInput.nextInt();
            for (int i = 0; i < numOfTrees; i++) {
                if (trees[i].getSpread() >= findSpread) {
                    trees[i].printTree();
                    flag = false;
                }
            }
        }
        if (flag) {
            System.out.println("No trees found.");
        }
    }

    /*
     * This method prints all the healthy trees.
     */
    public void findhealty() {
        boolean flag = true;
        for (int i = 0; i < numOfTrees; i++) {
            if (trees[i].getHealthy()) {
                trees[i].printTree();
                flag = false;
            }
        }
        if (flag) {
            System.out.println("No trees found.");
        }
    }

    /*
     * This method prints all the unhealthy trees.
     */
    public void findUnhealthy() {
        boolean flag = true;
        for (int i = 0; i < numOfTrees; i++) {
            if (!trees[i].getHealthy()) {
                trees[i].printTree();
                flag = false;
            }
        }
        if (flag) {
            System.out.println("No trees found.");
        }
    }

    /*
     * This method is in charge of printing all the trees that are in the PecanFirm array.
     */
    public void printCollection() {
        for (int i = 0; i < numOfTrees; i++) {
            trees[i].printTree();
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        String option;

        PecanFirm myfirm = new PecanFirm(25);
        myfirm.readFromFile();

        while (true) {
            System.out.println("Hello. Please enter one of the following menu options: ");
            System.out.println("\ta). Display the firm's yield per acre\n\tb). Display all the trees that need pruning");
            System.out.println("\tc). Update tree's information\n\td). Find types of trees\n\te). Display all the trees");
            System.out.println("[Enter any other key to exit the program.]");
            option = input.nextLine();

            if (option.equals("a")) {
                double r = myfirm.yieldPerAcre();
                System.out.println(r);
            }
            else if (option.equals("b")) {
                myfirm.displayPruning();
            }
            else if (option.equals("c")) {
                myfirm.updateInformation();
            }
            else if (option.equals("d")) {
                System.out.println("Choose one of the following options: ");
                System.out.println("\ta). Find yield\n\tb). Find spread\n\tc). Find healthy\n\td). Find unhealthy");
                String findOption = input.nextLine();
                if (findOption.equals("a")) {
                    myfirm.findYield();
                }
                else if (findOption.equals("b")) {
                    myfirm.findSpread();
                }
                else if (findOption.equals("c")) {
                    myfirm.findhealty();
                }
                else if (findOption.equals("d")) {
                    myfirm.findUnhealthy();
                }
            }
            else if (option.equals("e")) {
                myfirm.printCollection();
            } else {
                System.out.println("Exiting program. Goodbye!!!");
                System.exit(0);
            }
        }
    }
}
