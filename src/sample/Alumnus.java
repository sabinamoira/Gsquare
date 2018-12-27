package sample; /**
 * CS 1331 Section A3
 * HomeWork 7
 * I worked on the homework assignment alone, using only course materials
 */

/**
 * This interface represents an Alumni.
 * An Alumni is a graduate or former student, of a particular school
 * @author monwonga3 (Moraa Onwonga)
 * @version 1
 */

interface Alumnus {
    /**
    * Gets the alumni's almamater.
    * @return the almamater of the alumni
    */
    String getAlmaMater();

    /**
    * Gets alumni's graduating year.
    * @return the almamater of the alumni
    */
    int getGradYear();

    /**
    * Gets alumni's name.
    * @return the name of the alumni
    */
    String getName();

    /**
    * Gets alumni's type, the class furthest down the tree
    * that the alumni falls under.
    * @return the type of the alumni
    */
    String getType();
}