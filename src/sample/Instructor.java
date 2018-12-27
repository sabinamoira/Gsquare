package sample; /**
 * CS 1331 Section A3
 * HomeWork 7
 * I worked on the homework assignment alone, using only course materials
 */

/**
 * This class represents an Instructor.
 * An Instructor teaches the courses at Gatech
 * @author monwonga3 (Moraa Onwonga)
 * @version 1
 */
public class Instructor extends User implements Alumnus {
    // The number of years the instructor has been teaching
    private int yearsTeaching;
    // Whether the instructor is tenured
    private boolean hasTenure;
    // Where the instructor graduated from
    private String almaMater;
    // The year the instructor graduated
    private int gradYear;

    /**
    * This constructor creates an Instructor
    * It sets the Instructor's name, yearsTeaching, hasTenure,
    * almaMater, gradYear
    * @param name the name of the Instructor
    * @param yearsTeaching the number of years an instructor has taught
    * @param hasTenure whether the Instructor has tenure or not
    * @param almaMater the Instructor's almamator
    * @param gradYear the year the instructor graduated
    */
    public Instructor(String name, int yearsTeaching,
                      boolean hasTenure, String almaMater, int gradYear) {
        super(name);
        this.yearsTeaching = yearsTeaching;
        this.hasTenure = hasTenure;
        this.almaMater = almaMater;
        this.gradYear = gradYear;
    }

    /**
    * Gets the yearsTeching.
    * @return the number of years an instructor has taught
    */
    public int getYearsTeaching() {
        return yearsTeaching;
    }

    /**
    * Gets hasTenure.
    * @return whether the Instructor has tenure (true) or not (false)
    */
    public boolean getHasTenure() {
        return hasTenure;
    }

    /**
    * Gets the almaMater.
    * @return the name of the school the instructor graduated from
    */
    public String getAlmaMater() {
        return almaMater;
    }

    /**
    * Gets the GradYear.
    * @return the year the instructor graduated
    */
    public int getGradYear() {
        return gradYear;
    }

    /**
    * Compares two Instructors.
    * Instructors with tenure are greater
    * In instructors have the same value for hasTenure, they
    * are considered greater if they have been teaching longer.
    * Uses Users compareTo if both attributes are the same
    * @param other the 2nd Instructor being compared
    * @return an int that is positive if the 1st value is greter
    * 0 if equal and negative if lesser
    */
    public int compareTo(User other) {
        if (other instanceof Instructor) {
            Instructor casted = (Instructor) other;

            if (0 != ((Boolean) this.getHasTenure()).
                    compareTo((Boolean) casted.getHasTenure())) {
                return ((Boolean) this.getHasTenure()).
                       compareTo((Boolean) casted.getHasTenure());

            } else if (0 != ((Integer) this.getYearsTeaching()).
                       compareTo((Integer) casted.getYearsTeaching())) {
                return ((Integer) this.getYearsTeaching()).
                       compareTo((Integer) casted.getYearsTeaching());

            } else {
                return super.compareTo(casted);
            }
        }
        return super.compareTo(other);
    }
}