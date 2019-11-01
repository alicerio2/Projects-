
package Lab5;

/*************************************************************************
 * This class is the blueprint of a loaner dossier. 
 * It describes its attributes and 
 * how to operate it (its methods).
 * YOU CANNOT MODIFY THIS FILE
 *************************************************************************/ 

public class Dossier {

	private Student student;
	private int dateBorrowed; // YYYYMMDD
	private int dateReturned; // YYYYMMDD
	
	/* CONSTRUCTORS ************************************/
	
	public Dossier() {}
	
	public Dossier(Student student, int date) {
		super();
		this.student = student;
		this.dateBorrowed = date;
        /* date returned is set to -1 by default because when 
         * a dossier is created, it is because a laptop has 
         * just been borrowed, hence not returned. 
         */
		this.dateReturned = -1; 
	}

	/* SETTERS  *****************************************/

	/**
	 * @param student the student to set
	 */
	public void setStudent(Student student) {
		this.student = student;
	}

	/**
	 * @param date the date to set
	 */
	public void setDateBorrowed(int date) {
		this.dateBorrowed = date;
	}
	
	/**
	 * @param date the date to set
	 */
	public void setDateReturned(int date) {
		this.dateReturned = date;
	}

	/* GETTERS ******************************************/

	/**
	 * @return the student
	 */
	public Student getStudent() {
		return student;
	}

	/**
	 * @return the dateBorrowed
	 */
	public int getDateBorrowed() {
		return dateBorrowed;
	}
	
	/**
	 * @return the dateReturned
	 */
	public int getDateReturned() {
		return dateReturned;
	}
	
    /* OTHER METHODS ************************************/
    public String toString() {
        String toReturn = (student.toString() + "Date Borrowed: " + dateBorrowed + "\n");
        if (dateReturned == - 1) toReturn += "Laptop not returned yet. \n";
        else toReturn += "Date Returned: " + dateReturned + "\n";
        return toReturn;
    }
}
