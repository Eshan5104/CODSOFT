import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
    String code;
    String title;
    int capacity;
    int enrolled;

    Course(String code, String title, int capacity) {
        this.code = code;
        this.title = title;
        this.capacity = capacity;
        this.enrolled = 0;
    }

    boolean register() {
        if (enrolled < capacity) {
            enrolled++;
            return true;
        }
        return false;
    }

    void drop() {
        if (enrolled > 0) {
            enrolled--;
        }
    }

    @Override
    public String toString() {
        return code + ": " + title + " (Available slots: " + (capacity - enrolled) + ")";
    }
}

class Student {
    String id;
    String name;
    List<Course> registeredCourses;

    Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    void registerCourse(Course course) {
        if (course.register()) {
            registeredCourses.add(course);
            System.out.println(name + " successfully registered for " + course.title);
        } else {
            System.out.println("Course " + course.title + " is full.");
        }
    }

    void dropCourse(Course course) {
        if (registeredCourses.remove(course)) {
            course.drop();
            System.out.println(name + " dropped " + course.title);
        } else {
            System.out.println("Not registered for " + course.title);
        }
    }
}

public class S_Course_Register_System {
    private static List<Course> courses = new ArrayList<>();
    private static List<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Course and student registration loop
        while (true) {
            System.out.println("\n1. Add Course");
            System.out.println("2. Register Student");
            System.out.println("3. Display Available Courses");
            System.out.println("4. Enroll in Course");
            System.out.println("5. Drop Course");
            System.out.println("6. Exit");
            System.out.print("Select an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    addCourse(scanner);
                    break;
                case 2:
                    registerStudent(scanner);
                    break;
                case 3:
                    displayAvailableCourses();
                    break;
                case 4:
                    enrollInCourse(scanner);
                    break;
                case 5:
                    dropCourse(scanner);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addCourse(Scanner scanner) {
        System.out.print("Enter course code: ");
        String code = scanner.nextLine();
        System.out.print("Enter course title: ");
        String title = scanner.nextLine();
        System.out.print("Enter allowed student capacity: ");
        int capacity = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        courses.add(new Course(code, title, capacity));
        System.out.println("Course added successfully.");
    }

    private static void registerStudent(Scanner scanner) {
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        students.add(new Student(id, name));
        System.out.println("Student registered successfully.");
    }

    private static void displayAvailableCourses() {
        System.out.println("\nAvailable Courses:");
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    private static void enrollInCourse(Scanner scanner) {
        System.out.print("Enter student ID to enroll: ");
        String studentId = scanner.nextLine();
        Student student = findStudent(studentId);
        if (student != null) {
            System.out.print("Enter course code to enroll: ");
            String courseCode = scanner.nextLine();
            Course course = findCourse(courseCode);
            if (course != null) {
                student.registerCourse(course);
            } else {
                System.out.println("Course not found.");
            }
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void dropCourse(Scanner scanner) {
        System.out.print("Enter student ID to drop course: ");
        String studentId = scanner.nextLine();
        Student student = findStudent(studentId);
        if (student != null) {
            System.out.print("Enter course code to drop: ");
            String courseCode = scanner.nextLine();
            Course course = findCourse(courseCode);
            if (course != null) {
                student.dropCourse(course);
            } else {
                System.out.println("Course not found.");
            }
        } else {
            System.out.println("Student not found.");
        }
    }

    private static Student findStudent(String id) {
        for (Student student : students) {
            if (student.id.equals(id)) {
                return student;
            }
        }
        return null;
    }

    private static Course findCourse(String code) {
        for (Course course : courses) {
            if (course.code.equalsIgnoreCase(code)) {
                return course;
            }
        }
        return null;
    }
}
