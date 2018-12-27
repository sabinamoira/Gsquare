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
public class Grad extends Student implements Alumnus {
    // The name of the school the Grad student graduated from
    private String almaMater;
    // The year the Grad student graduated
    private int gradYear;

    /**
    * This constructor creates an Instructor
    * It sets the Student's name, creditHours, inState
    * @param name the name of the Grad Student
    * @param creditHours the number of credit hours a Grad Student has
    * @param inState whether the Grad Student is in State or not
    * @param almaMater the name of the school the Grad student graduated from
    * @param gradYear the year/standing the student is in
    */
    public Grad(String name, int creditHours,
                boolean inState, String almaMater, int gradYear) {
        super(name, creditHours, inState);
        this.almaMater = almaMater;
        this.gradYear = gradYear;
    }

    /**
    * Gets the almaMater
    * @return the name of the school the Grad graduated from
    */
    public String getAlmaMater() {
        return almaMater;
    }

    /**
    * Gets the gradYear
    * @return the year the Grad student has graduated
    */
    public int getGradYear() {
        return gradYear;
    }

    /**
    * Compares two Grads.
    * Grads with a more recent gradYear are "lesser"
    * Use Students compareTo if the gradYears are the same
    * @param other the 2nd Grad being compared
    * @return an int that is positive if the 1st value is greter
    * 0 if equal and negative if lesser
    */
    public int compareTo(User other) {

        if (other instanceof Grad) {
            Grad casted = (Grad) other;

            if (0 != ((Integer) casted.getGradYear()).
                    compareTo((Integer) this.getGradYear())) {
                return ((Integer) casted.getGradYear()).
                       compareTo((Integer) this.getGradYear());
            } else {
                return super.compareTo(casted);
            }
        }
        return super.compareTo(other);
    }
}