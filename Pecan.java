/* CS1101 – Intro to Computer Science
Instructor: Villanueva
Comprehensive Lab 2
By including my name below, I confirm that:
- I am submitting my original work.
- If I include code obtained from another source or I received help I am giving attribution to those sources as comments.
- This submission does not incur in any academic dishonesty practice as described in the course syllabus.
 Modified and submitted by: Alan Licerio.
*/

public class Pecan {
    private int id;
    private int age;
    private boolean isHealthy;
    private int spread;
    private double yield;

    /*
     * Constructor
     */

    public Pecan (int id, int age, boolean isHealthy, int spread, double yield) {
        this.id = id;
        this.age = age;
        this.isHealthy = isHealthy;
        this.spread = spread;
        this.yield = yield;
    }

    /*
     * Getters
     */

    public int getid() {
        return this.id;
    }

    public int getAge() {
        return this.age;
    }

    public boolean getHealthy() {
        return this.isHealthy;
    }

    public int getSpread() {
        return this.spread;
    }

    public double getYield() {
        return this.yield;
    }

    /*
     * Setters
     */

    public void setid(int id) {
        this.id = id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHealthy(boolean healthy) {
        this.isHealthy = healthy;
    }

    public void setSpread(int spread) {
        this.spread = spread;
    }

    public void setYield(double yield) {
        this.yield = yield;
    }

    /*
     * This method returns true if the spread of a tree is greater than 80.
     */
    public boolean needsPruning() {
        return spread > 80;
    }

    /*
     * This method prints a single tree.
     */
    public void printTree() {
        System.out.println("[ID:" + this.id + ", age: " + this.age + ", healthy: " + this.isHealthy + ", spread: " + this.spread + ", yield: " + this.yield + "]");
    }

}
