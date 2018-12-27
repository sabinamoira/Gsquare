package sample; /**
 * CS 1331 Section A3
 * HomeWork 7
 * I worked on the homework assignment alone, using only course materials
 */

/**
 * This class represents a User on the GSquare site.
 * A User can be a student (grad or undergrad), or an instructor
 * @author monwonga3 (Moraa Onwonga)
 * @version 1
 */
public abstract class User implements Comparable<User> {
    //The first and last name of the User (e.g. Stasko, John)
    private String name;
    //The unique identifier for the User.
    //This should be 1 for the 1st User created, 2 for the 2nd,...,
    private int id;
    //used to set the id for a new User keeps track of number of Users
    private static int idIndex = 1;

    /**
    * This constructor creates a User for the site GSquare.
    * It sets the User's name
    * It sets the User's id number
    * @param name the name of the User
    */
    public User(String name) {
        this.name = name;
        this.id = idIndex++;
    }

    /**
    * Gets the class furthest down the tree that the User falls under
    * This is used in GSquare to display the type of each class.
    * @return a string representation of the Class name
    */
    public String getType() {
        return this.getClass().getName();
    }

    /**
    * @return the name of the User
    */
    public String getName() {
        return name;
    }

    /**
    * Gets the id representing the course name
    * @return the course id number
    */
    public int getId() {
        return id;
    }

    /**
    * Compares two Users.
    * Users with larger ids are greater, or equivalent if ids are equal
    * If the User falls under a subclass than it compares their
    * types, their class names, togehter
    * @param other the 2nd User being compared
    * @return an int that is positive if the 1st value is greter
    * 0 if equal and negative if lesser
    */
    public int compareTo(User other) {
        if (other instanceof Student) {
            Student casted = (Student) other;
            return this.getClass().getName()
                   .compareTo(casted.getClass().getName());
        } else if (other instanceof Instructor) {
            Instructor casted = (Instructor) other;
            return this.getClass().getName()
                   .compareTo(casted.getClass().getName());
        } else {
            return ((Integer) this.getId()).compareTo((Integer) other.getId());
        }
    }
}