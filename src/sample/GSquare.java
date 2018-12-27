package sample;// Welcome to javafx, the land of imports :p
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;
import java.util.Collections;

/**
 * The main driver for the GSquare application. Support classes not included.
 *
 * @author 1331 TA's
 * @version 1.0
 */
public class GSquare extends Application {
    private static Random rand = new Random();
    private ArrayList<Course> courses = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Instructor> instructors = new ArrayList<>();
    private ArrayList<Alumnus> alumni = new ArrayList<>();
    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<Undergrad> undergrads = new ArrayList<>();
    private ArrayList<Grad> grads = new ArrayList<>();

    // Course Names
    private static String[] prefixes = {"Intro to ", "Intermediate ",
        "Advanced ", "Remedial "};
    private static String[] adjectives = {"Molecular ", "Watercolor ",
        "Astro-", "Organic ", "Theoretical ", "Quantum "};
    private static String[] core = {"Java", "Biology", "Physics", "Painting",
        "Python", "Chemistry", "History", "Memeology"};


    // User Names
    private static String[] firstNames = {"Sophia", "Liam", "Olivia",
        "Aiden", "Emma", "Timothy", "Ava", "Arnold"};
    private static String[] lastNames = {"Clark", "Hall", "Miller", "Smith",
        "Walker", "Adams", "Scott"};

    // Schools
    private static String[] schools = {"Georgia Tech", "MIT", "Stanford",
        "UCSD", "Yale", "UGA", "USC", "Harvard"};

    /**
     * Creates a random instructor, and stores in appropriate instance variable
     * ArrayLists
     *
     * @return A new random instructor
     */
    private Instructor genInstructor() {
        String name = lastNames[rand.nextInt(lastNames.length)] + ", "
            + firstNames[rand.nextInt(firstNames.length)];
        String school = schools[rand.nextInt(schools.length)];
        // If you get an error / exception here, check your constructor!
        Instructor instructor = new Instructor(name, rand.nextInt(30) + 1,
            rand.nextBoolean(), school, rand.nextInt(48) + 1960);
        instructors.add(instructor);
        alumni.add(instructor);
        users.add(instructor);
        return instructor;
    }

    /**
     * Creates a random student, and stores in appropriate instance variable
     * ArrayLists
     *
     * @return A new random student
     */
    private Student genStudent() {
        String name = lastNames[rand.nextInt(lastNames.length)] + ", "
            + firstNames[rand.nextInt(firstNames.length)];
        Student student;
        if (rand.nextDouble() > .2) {
            Year standing = Year.values()[rand.nextInt(Year.values().length)];
            // If you get an error / exception here, check your constructor!
            student = new Undergrad(name, rand.nextInt(150),
                rand.nextBoolean(), standing);
            undergrads.add((Undergrad) student);
        } else {
            String school = schools[rand.nextInt(schools.length)];
            // If you get an error / exception here, check your constructor!
            student = new Grad(name, rand.nextInt(150), rand.nextBoolean(),
                school, rand.nextInt(38) + 1980);
            grads.add((Grad) student);
            alumni.add((Grad) student);
        }
        users.add(student);
        students.add(student);
        return student;
    }

    /**
     * Creates a set of random courses (along with students and instructors),
     * and stores them in the backing ArrayList.
     */
    private void genCourses() {
        // Course info
        for (int i = 0; i < rand.nextInt(10) + 10; i++) {
            String name = prefixes[rand.nextInt(prefixes.length)]
                + adjectives[rand.nextInt(adjectives.length)]
                + core[rand.nextInt(core.length)];
            int code = rand.nextInt(3000) + 1000;
            Student[] newStudents = new Student[rand.nextInt(20) + 1];
            for (int j = 0; j < newStudents.length; j++) {
                newStudents[j] = genStudent();
            }
            // If you get an error / exception here, check your constructor!
            courses.add(new Course(name, code, genInstructor(), newStudents));
        }
    }



    @Override
    public void start(Stage stage) {
        genCourses();
        BorderPane root = new BorderPane();

        // Sort Everything
        // If you get exceptions from these lines, check your
        // compareTo implementation
        Collections.sort(courses);
        Collections.sort(users);
        Collections.sort(instructors);
        // Don't do this normally, this is a hack for this specific situation
        Collections.sort(alumni, (a, b) -> ((User) a).compareTo((User) b));
        Collections.sort(students);
        Collections.sort(undergrads);
        Collections.sort(grads);

        // User Table
        TableView<User> userTable = createUserTable(users);

        // Instructor Table
        TableView<Instructor> instructorTable =
            createInstructorTable(instructors);

        // Alumnus Table
        TableView<Alumnus> alumniTable = createAlumnusTable(alumni);

        // Student Table
        TableView<Student> studentTable = createStudentTable(students);

        // Undergrad Table
        TableView<Undergrad> undergradTable = createUndergradTable(undergrads);

        // Grad Table
        TableView<Grad> gradTable = createGradTable(grads);

        // Course Table
        TableView<Course> courseTable = createCourseTable(courses);

        Button viewButton = new Button("View Selected");
        viewButton.setOnAction(e -> {
                Course c = courseTable.getSelectionModel().getSelectedItem();
                if (c == null) {
                    return;
                }
                ArrayList<Student> studentList = new ArrayList<>();
                studentList.addAll(Arrays.asList(c.getStudents()));
                Collections.sort(studentList);
                root.setCenter(createStudentTable(studentList));
            });

        HBox buttonBox = new HBox();
        buttonBox.getChildren().addAll(viewButton);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(courseTable, buttonBox);

        // Control Pane
        HBox control = new HBox();
        Button courseButton = new Button("View Courses");
        courseButton.setOnAction(e -> root.setCenter(vbox));
        Button userButton = new Button("View Users");
        userButton.setOnAction(e -> root.setCenter(userTable));
        Button instructorButton = new Button("View Instructors");
        instructorButton.setOnAction(e -> root.setCenter(instructorTable));
        Button alumniButton = new Button("View Alumnus");
        alumniButton.setOnAction(e -> root.setCenter(alumniTable));
        Button studentButton = new Button("View Students");
        studentButton.setOnAction(e -> root.setCenter(studentTable));
        Button undergradButton = new Button("View Undergrads");
        undergradButton.setOnAction(e -> root.setCenter(undergradTable));
        Button gradButton = new Button("View Grads");
        gradButton.setOnAction(e -> root.setCenter(gradTable));
        control.getChildren().addAll(courseButton, userButton,
            instructorButton, alumniButton, studentButton, undergradButton,
            gradButton);

        // Setup window
        root.setTop(control);
        root.setCenter(vbox);
        Scene main = new Scene(root, 800, 400);
        stage.setScene(main);
        stage.setTitle("GSquare");
        stage.show();
    }

    private TableView<User> createUserTable(ArrayList<User> tableUsers) {
        // Setup table
        TableView<User> table = new TableView<User>();
        ObservableList<User> obsUsers =
            FXCollections.observableArrayList(tableUsers);
        table.setItems(obsUsers);

        // Create table columns
        TableColumn<User, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory("name"));
        TableColumn<User, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory("id"));
        TableColumn<User, String> typeCol =
            new TableColumn<>("Type");
        typeCol.setCellValueFactory(new PropertyValueFactory("type"));

        table.getColumns().setAll(nameCol, idCol, typeCol);
        return table;
    }

    private TableView<Instructor> createInstructorTable(
            ArrayList<Instructor> tableInstructors) {

        // Setup table
        TableView<Instructor> table = new TableView<Instructor>();
        ObservableList<Instructor> obsInstructors =
            FXCollections.observableArrayList(tableInstructors);
        table.setItems(obsInstructors);

        // Create table columns
        TableColumn<Instructor, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory("name"));
        TableColumn<Instructor, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory("id"));
        TableColumn<Instructor, Integer> teachingCol =
            new TableColumn<>("Years Teaching");
        teachingCol.setCellValueFactory(
            new PropertyValueFactory("yearsTeaching"));
        TableColumn<Instructor, Boolean> tenureCol =
            new TableColumn<>("Tenured");
        tenureCol.setCellValueFactory(new PropertyValueFactory("hasTenure"));
        TableColumn<Instructor, String> almaMaterCol =
            new TableColumn<>("Alma Matter");
        almaMaterCol.setCellValueFactory(
            new PropertyValueFactory("almaMater"));
        TableColumn<Instructor, Integer> gradYearCol =
            new TableColumn<>("Graduation Year");
        gradYearCol.setCellValueFactory(new PropertyValueFactory("gradYear"));

        table.getColumns().setAll(nameCol, idCol, teachingCol, tenureCol,
            almaMaterCol, gradYearCol);
        return table;
    }

    private TableView<Alumnus> createAlumnusTable(
            ArrayList<Alumnus> tableAlumnus) {
        // Setup table
        TableView<Alumnus> table = new TableView<Alumnus>();
        ObservableList<Alumnus> obsAlumnuss =
            FXCollections.observableArrayList(tableAlumnus);
        table.setItems(obsAlumnuss);

        // Create table columns
        TableColumn<Alumnus, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory("name"));
        TableColumn<Alumnus, String> almaMaterCol =
            new TableColumn<>("Alma Matter");
        almaMaterCol.setCellValueFactory(
            new PropertyValueFactory("almaMater"));
        TableColumn<Alumnus, Integer> gradYearCol =
            new TableColumn<>("Graduation Year");
        gradYearCol.setCellValueFactory(new PropertyValueFactory("gradYear"));
        TableColumn<Alumnus, String> typeCol =
            new TableColumn<>("Type");
        typeCol.setCellValueFactory(new PropertyValueFactory("type"));

        table.getColumns().setAll(nameCol, almaMaterCol, gradYearCol, typeCol);
        return table;
    }

    private TableView<Student> createStudentTable(
            ArrayList<Student> tableStudents) {

        // Setup table
        TableView<Student> table = new TableView<Student>();
        ObservableList<Student> obsStudents =
            FXCollections.observableArrayList(tableStudents);
        table.setItems(obsStudents);

        // Create table columns
        TableColumn<Student, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory("name"));
        TableColumn<Student, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory("id"));
        TableColumn<Student, Integer> creditCol =
            new TableColumn<>("Credit Hours");
        creditCol.setCellValueFactory(new PropertyValueFactory("creditHours"));
        TableColumn<Student, Boolean> inStateCol =
            new TableColumn<>("In State");
        inStateCol.setCellValueFactory(new PropertyValueFactory("inState"));
        TableColumn<Student, String> typeCol =
            new TableColumn<>("Type");
        typeCol.setCellValueFactory(new PropertyValueFactory("type"));


        table.getColumns().setAll(nameCol, idCol, creditCol,
            inStateCol, typeCol);
        return table;
    }

    private TableView<Undergrad> createUndergradTable(
            ArrayList<Undergrad> tableUndergrads) {

        // Setup table
        TableView<Undergrad> table = new TableView<Undergrad>();
        ObservableList<Undergrad> obsUndergrads =
            FXCollections.observableArrayList(tableUndergrads);
        table.setItems(obsUndergrads);

        // Create table columns
        TableColumn<Undergrad, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory("name"));
        TableColumn<Undergrad, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory("id"));
        TableColumn<Undergrad, Integer> creditCol =
            new TableColumn<>("Credit Hours");
        creditCol.setCellValueFactory(new PropertyValueFactory("creditHours"));
        TableColumn<Undergrad, Boolean> inStateCol =
            new TableColumn<>("In State");
        inStateCol.setCellValueFactory(new PropertyValueFactory("inState"));
        TableColumn<Undergrad, Year> yearCol = new TableColumn<>("Year");
        yearCol.setCellValueFactory(new PropertyValueFactory("year"));

        // Add table columns and return
        table.getColumns().setAll(nameCol, idCol, creditCol, inStateCol,
            yearCol);
        return table;
    }

    private TableView<Grad> createGradTable(ArrayList<Grad> tableGrads) {
        // Setup table
        TableView<Grad> table = new TableView<Grad>();
        ObservableList<Grad> obsGrads =
            FXCollections.observableArrayList(tableGrads);
        table.setItems(obsGrads);

        // Create table columns
        TableColumn<Grad, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory("name"));
        TableColumn<Grad, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory("id"));
        TableColumn<Grad, Integer> creditCol =
            new TableColumn<>("Credit Hours");
        creditCol.setCellValueFactory(new PropertyValueFactory("creditHours"));
        TableColumn<Grad, Boolean> inStateCol = new TableColumn<>("In State");
        inStateCol.setCellValueFactory(new PropertyValueFactory("inState"));
        TableColumn<Grad, String> almaMaterCol =
            new TableColumn<>("Alma Matter");
        almaMaterCol.setCellValueFactory(
            new PropertyValueFactory("almaMater"));
        TableColumn<Grad, Integer> gradYearCol =
            new TableColumn<>("Graduation Year");
        gradYearCol.setCellValueFactory(new PropertyValueFactory("gradYear"));

        // Add table columns and return
        table.getColumns().setAll(nameCol, idCol, creditCol, inStateCol,
            almaMaterCol, gradYearCol);
        return table;
    }

    private TableView<Course> createCourseTable(
        ArrayList<Course> tableCourses) {
        // Setup table
        TableView<Course> table = new TableView<Course>();
        ObservableList<Course> obsCourses =
            FXCollections.observableArrayList(tableCourses);
        table.setItems(obsCourses);

        // Create table columns
        TableColumn<Course, String> nameCol = new TableColumn<Course,
            String>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory("name"));
        TableColumn<Course, String> courseCol = new TableColumn<Course,
            String>("Course Code");
        courseCol.setCellValueFactory(new PropertyValueFactory("courseCode"));
        TableColumn<Course, String> instCol = new TableColumn<Course,
            String>("Instructor");
        instCol.setCellValueFactory(
            new PropertyValueFactory("instructorName"));

        // Add table columns and return
        table.getColumns().setAll(nameCol, courseCol, instCol);
        return table;
    }
}