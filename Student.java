package Lab3;

import java.util.Arrays;

/**
 * 
 * @author mceberio
 * @student Alan Licerio
 *
 */

public class Student {

	private String name; // includes first and last names
	private String major;
	private int[] grades;
	
	/* CONSTRUCTORS ***********************************************/

	public Student(String name, String major, int[] grades) {
		this.name = name;
		this.major = major;
		this.grades = grades;
	}

	public Student() {

	}

	/* GETTERS ****************************************************/
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return the major
	 */
	public String getMajor() {
		return major;
	}
	
	/**
	 * @return the grades
	 */
	public int[] getGrades() {
		return grades;
	}
	
	/* SETTERS ****************************************************/
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @param major the major to set
	 */
	public void setMajor(String major) {
		this.major = major;
	}
	
	/**
	 * @param grades the grades to set
	 */
	public void setGrades(int[] grades) {
		this.grades = grades;
	}

	/* OTHER METHODS ***********************************************/
	/**
	 * prints out the name and major of the student
	 */
	public void print() {
    	System.out.println("Name: " +  this.name + "Major: " + this.major);
	}
	
	/**
	 * prints out the name, major, and grades of the student
	 */
	public void printAll() {
		System.out.println("Name: " +  this.name + ", Major: " + this.major + ", Grades: "+ Arrays.toString(this.grades));
	}
		
	/* BOTH METHODS BELOW NEED TO BE TESTED USING JUNIT TESTS: 3 tests per method ******/	
	/**
	 * Method average:
	 * @return the average of all grades in array grades (without weights)
	 */

	public double average() {
		double sumOfGrades = 0;
		double finalAverage = 0;
		for (int i = 0; i < grades.length; i++) {
			 sumOfGrades += grades[i];
			 finalAverage = sumOfGrades/grades.length;
		}
		return finalAverage;
	}

	/**
	 * Method averageW:
	 * @return the average of all grades in array grades (with weights)
	 */
	public double averageW(int[] weights) {
		double sumOfGrades = 0;
		double finalAverage = 0;
		for (int i = 0; i < weights.length; i++) {
			for (int j = 0; j < grades.length; j++) {
				sumOfGrades += ((grades[j] * weights[j])/100.0);
				finalAverage = sumOfGrades/grades.length;
			}
		}
		return finalAverage;
	}

}
