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
public class Undergrad extends Student {
    // The class standing of the student (e.g. Sophomore)
    private Year year;

    /**
    * This constructor creates an Instructor
    * It sets the Student's name, creditHours, inState
    * @param name the name of the Student
    * @param creditHours the number of credit hours a Student has
    * @param inState whether the Student is in State or not
    * @param year the year/standing the student is in
    */
    public Undergrad(String name, int creditHours, boolean inState, Year year) {
        super(name, creditHours, inState);
        this.year = year;
    }

    /**
    * Gets the year.
    * @return the the class year the student is in
    */
    public Year getYear() {
        return year;
    }

    /**
    * Gets the standing of the student represented as a number 1-4.
    * @param standing the class year the student is in
    * @return the year the student is in represented as 1-4
    */
    public int getStanding(Year standing) {
        switch (standing) {
        case FRESHMAN :
            return 1;

        case SOPHOMORE:
            return 2;

        case JUNIOR   :
            return 3;

        case SENIOR   :
            return 4;

        default :
            return 0;
        }
    }

    /**
    * Compares two Undergrads.
    * Undergrads with a lower year are "greater"
    * Uses Students compareTo if the Years are the same
    * @param other the 2nd Undergrad being compared
    * @return an int that is positive if the 1st value is greter
    * 0 if equal and negative if lesser
    */
    public int compareTo(User other) {

        if (other instanceof Undergrad) {
            Undergrad casted = (Undergrad) other;

            if (0 != ((Integer) casted.getStanding(casted.getYear())).
                    compareTo((Integer) this.getStanding(this.getYear()))) {
                return ((Integer) casted.getStanding(casted.getYear())).
                       compareTo((Integer) this.getStanding(this.getYear()));
            } else {
                return super.compareTo(casted);
            }
        }
        return super.compareTo(other);
    }
}