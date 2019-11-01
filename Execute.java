package Lab3;
/**
 *
 * @author mceberio
 * @student Alan Licerio
 *
 */


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Execute {

	/**
	 * 
	 * @param filename
	 * @return the number of students in the file called filename
	 * @throws FileNotFoundException
	 * @throws IOException
	 */

    public static int numStudents(String filename) throws FileNotFoundException, IOException {
        int numOfStudents = 0;
        FileReader fr = new FileReader(filename);
        BufferedReader textReader = new BufferedReader(fr);
        while (textReader.ready()) {
            textReader.readLine();
            numOfStudents++;
        }
        textReader.close();
        numOfStudents = numOfStudents/3;
    	return numOfStudents;
    }
    
    /**
     * 
     * @param filename
     * @return a 1D array of Students whose information was read in the file called filename
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static Student[] readStudentInfo(String filename) throws FileNotFoundException, IOException {

        int numStudents = numStudents(filename);
        Student[] roster = new Student[numStudents];

        FileReader fr = new FileReader(filename);
        BufferedReader textReader = new BufferedReader(fr);
        int i = 0;

        while (textReader.ready()) {
            String name = textReader.readLine();
            String major = textReader.readLine();
            String grades = textReader.readLine();
            String[] gradeSplit = grades.split(" ");
            int[] numbers = new int[gradeSplit.length];
            for (int j = 0; j < gradeSplit.length; j++) {
                numbers[j] = Integer.parseInt(gradeSplit[j]);
            }
            roster[i] = new Student(name, major, numbers);
            i++;
        }
        textReader.close();
        return roster;
    }
 
    /**
     * 
     * @param roster: 1D array of Students
     * @return a 2D array of grades (each row corresponds to a student's list of grades)
     */
    public static int[][] gradeSheet(Student[] roster) {
    	int[][] grades = new int[roster.length][6]; // CHANGE? HINT: IT DOES NOT MAKE SENSE TO ALWAYS HAVE ONLY 2 COLUMNS --> CHANGE THAT
        for(int i = 0; i < grades.length;i++) {
            int[] a = roster[i].getGrades();
            for (int j = 0; j < a.length; j++) {
                grades[i][j] = a[j];
            }
        }
    	return grades;
    }
    
    /**
     * 
     * @param gradeSheet: a 2D array of grades
     * @return the index of the row with the best average, 
     * which corresponds to the index of the Lab3.Student with best average in roster
     */
    public static int bestStudentAverage(int[][] gradeSheet) {
        double sumOfGrades = 0;
        double finalAverage = 0;
        double max = 0;
        int index = 0;

        for (int i = 0; i<gradeSheet.length; i++) {
            for (int j = 0; j<gradeSheet[i].length; j++) {
                sumOfGrades+= gradeSheet[i][j];
            }
            finalAverage = sumOfGrades/gradeSheet[i].length;
            sumOfGrades = 0;
            if (finalAverage > max) {
                max = finalAverage;
                index = i;
            }
        }
        return index;
    }

    /**
     * 
     * @param gradeSheet: a 2D array of grades
     * @param major: a String representing the major of the Lab3.Student
     * @return the index of the row with the best average, 
     * among the students with a major matching @param major,
     * which corresponds to the index of the Lab3.Student with best average in roster
     */
    public static int bestStudentAverage(int[][] gradeSheet, String major, Student[] roster) {
        double sumOfGrades = 0;
        double finalAverage = 0;
        double max = 0;
        int index = 0;

        for (int i = 0; i < roster.length; i++) {
            if (roster[i].getMajor().equals(major)) {
                for (int j = 0; j < gradeSheet[i].length; j++) {
                    sumOfGrades += gradeSheet[i][j];
                }
                finalAverage = sumOfGrades / gradeSheet[i].length;
                sumOfGrades = 0;
                if (finalAverage > max) {
                    max = finalAverage;
                    index = i;
                }
            }
        }
    	return index;
    }

    /**
     * 
     * @param grades: a 2D array of grades
     * @return the index of the column with the best average,
     * which corresponds to the assignment with the most success in the classroom
     */
    public static int bestAssignmentAverage(int[][] grades) {
        double sumOfGradesColumn = 0;
        double finalAverage = 0;
        double max = 0;
        int index = 0;

    	for (int i = 0; i < grades.length; i++) {
    	    for (int j = 0; j < grades[i].length; j++) {
    	        sumOfGradesColumn += grades[j][i];
            }
    	    finalAverage = sumOfGradesColumn/grades[i].length;
    	    sumOfGradesColumn = 0;
    	    if (finalAverage > max) {
    	        max = finalAverage;
    	        index = i;
            }
        }
    	return index+1;
    }
	
    /**
     * 
     * @param grades: a 2D array of grades
     * @param major: a String representing the major of the Lab3.Student
     * @return the index of the column with the best averageW, among only the rows 
     * that correspond to students with major @param major
     * which corresponds to the assignment with the most success in the classroom
     */
    public static int bestAssignmentAverage(int[][] grades, String major, Student[] roster) {
        double sumOfGradesColumn = 0;
        double finalAverage = 0;
        double max = 0;
        int index = 0;

        for (int i = 0; i < roster.length; i++) {
            if (roster[i].getMajor().equals(major)) {
                for (int j = 0; j < grades[i].length; j++) {
                    sumOfGradesColumn += grades[j][i];
                }
                finalAverage = sumOfGradesColumn/grades.length;
                sumOfGradesColumn = 0;
                if (finalAverage > max) {
                    max = finalAverage;
                    index = 5;
                }
            }
        }
    	return index+1;
    }
	
    /**
     * Main method: follow instructions within to test your above code 
     * @param args
     */
    public static void main(String[] args) throws IOException {
    	// 1. Read the file studentsInfo.txt and generate a 1D array of Students called roster
    	Student[] roster = readStudentInfo("studentsInfo.txt");

    	// 2. Create a gradeSheet and call it grades
    	int[][] grades = gradeSheet(roster);

    	// 3. Print out the gradeSheet
        System.out.println("--Gradesheet--");
        for (int i = 0; i<grades.length;i++) {
            System.out.print("- ");
            for (int j = 0; j< grades[i].length; j++) {
                System.out.print(grades[i][j] + " ");
            }
            System.out.println();
        }

    	// 4. Identify the student with best average and print out the student's information
    	int bestAverage = bestStudentAverage(grades);
            System.out.println("--Best Student Average--");
            roster[bestAverage].printAll();

    	// 5. Identify the computer science student with best average and print out the student's information
    	int bestAverageMajor = bestStudentAverage(grades, "Computer Science", roster);
            System.out.println("--Best Computer Science Average--");
            roster[bestAverageMajor].printAll();

    	// 6. Identify the assignment with most success and print out which assignment this was
    	int bestAverageAssignment = bestAssignmentAverage(grades);
            System.out.println("--Best Assignment Average--");
            System.out.println("Assignment: " + bestAverageAssignment);

    	// 7. Identify the assignment with most success among computer science students and print out which assignment this was
    	int bestAverageAssignmentMajor = bestAssignmentAverage(grades, "Computer Science",roster);
            System.out.println("--Best Assignment Average among Computer Science--");
            System.out.println("Assignment: " + bestAverageAssignmentMajor);

    	// 8. Print the Information of all Students
        System.out.println("--Information of every student--");
        for (int i = 0; i < roster.length; i++) {
            roster[i].printAll();
        }
    }
}
