package sample; /**
 * CS 1331 Section A3
 * HomeWork 7
 * I worked on the homework assignment alone, using only course materials
 */

/**
 * This class represents a Course.
 * A Course is class available to take at an college
 * @author monwonga3 (Moraa Onwonga)
 * @version 1
 */
public abstract class Student extends User {
    // The number of credit hours the student has taken
    private int creditHours;
    // Whether the student is from in state or not
    private boolean inState;

    /**
    * This constructor creates an Instructor
    * It sets the Student's name, creditHours, inState
    * @param name the name of the Student
    * @param creditHours the number of credit hours a Student has
    * @param inState whether the Student is in State or not
    */
    public Student(String name, int creditHours, boolean inState) {
        super(name);
        this.creditHours = creditHours;
        this.inState = inState;
    }

    /**
    * Gets the creditHours.
    * @return the number of credit hours a Student has
    */
    public int getCreditHours() {
        return creditHours;
    }

    /**
    * Gets the inState.
    * @return whether a student is in state (true) or out of state (false)
    */
    public boolean getInState() {
        return inState;
    }


    /**
    * Compares two Students.
    * Students with fewer creditHours are "greater".
    * If students have the same number of credit hours,
    * they are considered "greater" if they are inState.
    * Uses Users compareTo if both attributes are the same
    * @param other the 2nd Student being compared
    * @return an int that is positive if the 1st value is greter
    * 0 if equal and negative if lesser
    */
    public int compareTo(User other) {

        if (other instanceof Undergrad) {
            Undergrad casted = (Undergrad) other;
            return this.getClass().getName()
                   .compareTo(casted.getClass().getName());

        } else if (other instanceof Grad) {
            Grad casted = (Grad) other;
            return this.getClass().getName().
                   compareTo(casted.getClass().getName());
        }

        if (other instanceof Student) {
            Student casted = (Student) other;

            if (0 != ((Integer) casted.getCreditHours()).
                    compareTo((Integer) this.getCreditHours())) {
                return ((Integer) casted.getCreditHours()).
                       compareTo((Integer) this.getCreditHours());

            } else if (0 != ((Boolean) this.getInState()).
                       compareTo((Boolean) casted.getInState())) {
                return ((Boolean) this.getInState()).
                       compareTo((Boolean) casted.getInState());

            } else {
                return super.compareTo(casted);
            }
        }
        return super.compareTo(other);
    }
}