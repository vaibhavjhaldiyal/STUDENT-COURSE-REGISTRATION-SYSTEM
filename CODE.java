import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Course{
     String code;
     String title;
     String description;
     int capacity;
     int enrolled;

     Course(String code, String title, String description, int capacity) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.enrolled = 0;
    }

     String getCode() {
        return code;
    }

     String getTitle() {
        return title;
    }

     String getDescription() {
        return description;
    }

     int getCapacity() {
        return capacity;
    }

     int getEnrolled() {
        return enrolled;
    }

     boolean isFull() {
        return enrolled >= capacity;
    }

     boolean enroll() {
        if (!isFull()) {
            enrolled++;
            return true;
        }
        return false;
    }

     boolean drop() {
        if (enrolled > 0) {
            enrolled--;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("Course Code: %s\nTitle: %s\nDescription: %s\nCapacity: %d\nEnrolled: %d\n",
                code, title, description, capacity, enrolled);
    }
}
 class Student {
 String id;  
    String name;
    Set<Course> registeredCourses;

    Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.registeredCourses = new HashSet<>();
    }

     String getId() {
        return id;
    }

    String getName() {
        return name;
    }

     Set<Course> getRegisteredCourses() {
        return registeredCourses;
    }

     boolean registerCourse(Course course) {
        if (registeredCourses.contains(course)) {
            System.out.println("Already registered for this course.");
            return false;
        }
        if (course.enroll()) {
            registeredCourses.add(course);
            return true;
        } else {
            System.out.println("Course is full.");
            return false;
        }
    }

     boolean dropCourse(Course course) {
        if (registeredCourses.remove(course)) {
            course.drop();
            return true;
        }
        System.out.println("Not registered for this course.");
        return false;
    }
}

 class CourseManager {
     Map<String, Course> courses;

     CourseManager() {
        this.courses = new HashMap<>();
    }

     void addCourse(Course course) {
        courses.put(course.getCode(), course);
    }

     Course getCourse(String code) {
        return courses.get(code);
    }

     void listCourses() {
        for (Course course : courses.values()) {
            System.out.println(course);
        }
    }
}

 class StudentManager {
     Map<String, Student> students;

     StudentManager() {
        this.students = new HashMap<>();
    }

     void addStudent(Student student) {
        students.put(student.getId(), student);
    }

     Student getStudent(String id) {
        return students.get(id);
    }
}

    public class Studentcoursesystem{
    public static void main(String[] args) {
        CourseManager courseManager = new CourseManager();
        StudentManager studentManager = new StudentManager();
        Scanner scanner = new Scanner(System.in);

        // Adding some courses
        courseManager.addCourse(new Course("CS101", "Introduction to Computer Science", "Learn the basics of computer science.", 30));
        courseManager.addCourse(new Course("CS102", "Data Structures", "Learn about data structures.", 25));

        // Adding some students
        studentManager.addStudent(new Student("S001", "Alice"));
        studentManager.addStudent(new Student("S002", "Bob"));

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. List Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    courseManager.listCourses();
                    break;
                case 2:
                    System.out.print("Enter Student ID: ");
                    String studentId = scanner.nextLine();
                    Student student = studentManager.getStudent(studentId);

                    if (student == null) {
                        System.out.println("Student not found.");
                        break;
                    }

                    System.out.print("Enter Course Code: ");
                    String courseCode = scanner.nextLine();
                    Course course = courseManager.getCourse(courseCode);

                    if (course == null) {
                        System.out.println("Course not found.");
                        break;
                    }

                    if (student.registerCourse(course)) {
                        System.out.println("Registered successfully.");
                    }
                    break;
                case 3:
                    System.out.print("Enter Student ID: ");
                    studentId = scanner.nextLine();
                    student = studentManager.getStudent(studentId);

                    if (student == null) {
                        System.out.println("Student not found.");
                        break;
                    }

                    System.out.print("Enter Course Code: ");
                    courseCode = scanner.nextLine();
                    course = courseManager.getCourse(courseCode);

                    if (course == null) {
                        System.out.println("Course not found.");
                        break;
                    }

                    if (student.dropCourse(course)) {
                        System.out.println("Dropped successfully.");
                    }
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
