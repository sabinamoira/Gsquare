package sample; /**
 * CS 1331 Section A3
 * HomeWork 7
 * I worked on the homework assignment alone, using only course materials
 */

/**
 * This class represents a Course.
 * A Course is a class available to at Gatech
 * @author monwonga3 (Moraa Onwonga)
 * @version 1
 */
public class Course implements Comparable<Course> {
    //name of the Course (e.g. Intro to Java)
    private String name;
    //code that corresponds to the Course name (e.g. 1331)
    private int courseCode;
    // the instructor of the Course
    private Instructor instructor;
    // an array representing the students in the Course
    private Student[] students;

    /**
    * This constructor creates a course available at Gatech.
    * It sets the Course's name
    * It sets the Course's code number
    * It sets the instructor for the course
    * It sets the students enrolled in the Course
    * @param name the name of the class
    * @param courseCode the radius of the Planet
    * @param instructor the instructor of the course
    * @param students an array representing the students in the course
    */
    public Course(String name, int courseCode,
                  Instructor instructor, Student[] students) {
        this.name = name;
        this.courseCode = courseCode;
        this.instructor = instructor;
        this.students = students;
    }

    /**
    * @return the instructor for the Course
    */
    public String getInstructorName() {
        return instructor.getName();
    }

    /**
    * @return the name of the Course
    */
    public String getName() {
        return name;
    }

    /**
    * @return the number representing the Course
    */
    public int getCourseCode() {
        return courseCode;
    }

    /**
    * @return the students enrolled in the Course
    */
    public Student[] getStudents() {
        return students;
    }

    /**
    * Compares two Courses.
    * Courses with larger courseCodes are "greater".
    * If the courseCodes are the same, Courses with instructor
    * names later in the alphabet are "greater" (e.g
    * "Sophia Miller" > "Liam Hall"). Returns a positive int
    * @param other the 2nd course being compared
    * @return an int that is positive if the 1st value is greter
    * 0 if equal and negative if lesser
    */
    public int compareTo(Course other) {
        if (0 != ((Integer) this.getCourseCode())
                .compareTo((Integer) other.getCourseCode())) {
            return ((Integer) this.getCourseCode())
                   .compareTo((Integer) other.getCourseCode());
        } else {
            return this.getInstructorName()
                   .compareTo(other.getInstructorName());
        }
    }
}