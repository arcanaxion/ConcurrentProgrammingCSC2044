import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Teacher> teachers = new ArrayList<Teacher>();
        ArrayList<Student> students = new ArrayList<Student>();

        while (true) {
            System.out.println("\"1\" — Create teacher");
            System.out.println("\"2\" — Create student");
            System.out.println("\"3\" — Create postgraduate");
            System.out.println("\"4\" — List students");

            System.out.println("\"0\" — Exit");
            System.out.println("Enter number to select action: ");
            Scanner scan = new Scanner(System.in);
            String action = scan.nextLine();
            if (action.equals("0")) {
                System.exit(0);
            } else if (action.equals("1")) {
                teachers = createTeacher(teachers);
            } else if (action.equals("2")) {
                students = createStudent(students, teachers);
            } else if (action.equals("3")) {
                students = createPostgraduate(students, teachers);
            } else if (action.equals("4")) {
                listStudents(students);
            } else {
                System.out.println("Invalid input.");
            }
            System.out.println();
        }


    }

    public static ArrayList<Teacher> createTeacher(ArrayList<Teacher> teachers) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter name: ");
        String name = scan.nextLine();
        System.out.println("Enter email: ");
        String email = scan.nextLine();
        teachers.add(new Teacher(name, email));
        return teachers;
    }

    public static ArrayList<Student> createStudent(ArrayList<Student> students, ArrayList<Teacher> teachers) {
        if (teachers.size() == 0) {
            System.out.println("You cannot create a student without first creating a teacher.\n");
            return students;
        }
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter name: ");
        String name = scan.nextLine();
        System.out.println("Enter age: ");
        String age = scan.nextLine();
        System.out.println("Enter average marks: ");
        String averageMarks = scan.nextLine();

        for (int x = 0; x < teachers.size(); x++) {
            System.out.printf("Teacher #%d: %s \n", x + 1, teachers.get(x).getName());
        }
        System.out.println("Enter number to choose teacher: ");
        int choice = Integer.parseInt(scan.nextLine()) - 1;
        students.add(new Student(name, Integer.parseInt(age), Double.parseDouble(averageMarks), teachers.get(choice)));
        return students;
    }

    public static ArrayList<Student> createPostgraduate(ArrayList<Student> students, ArrayList<Teacher> teachers) {
        if (teachers.size() == 0) {
            System.out.println("You cannot create a postgraduate without first creating a teacher.\n");
            return students;
        }
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter name: ");
        String name = scan.nextLine();
        System.out.println("Enter age: ");
        String age = scan.nextLine();
        System.out.println("Enter average marks: ");
        String averageMarks = scan.nextLine();

        for (int x = 0; x < teachers.size(); x++) {
            System.out.printf("Teacher #%d: %s\n", x + 1, teachers.get(x).getName());
        }
        System.out.println("Enter number to choose teacher: ");
        int choice = Integer.parseInt(scan.nextLine()) - 1;

        System.out.println("Enter supervisor name: ");
        String supervisor = scan.nextLine();

        students.add(new Postgraduate(name, Integer.parseInt(age), Double.parseDouble(averageMarks),
                teachers.get(choice), supervisor));
        return students;
    }

    public static void listStudents(ArrayList<Student> students) {
        for (int i=0; i<students.size(); i++) {
            System.out.println("Student #" + (i+1));
            System.out.println("Name: " + students.get(i).getName());
            System.out.println("Age: " + students.get(i).getAge());
            System.out.println("Average marks: " + students.get(i).getAverageMarks());
            System.out.println("Teacher's name: " + students.get(i).getTeacher().getName());
            System.out.println();
        }
        if (students.size() == 0) {
            System.out.println("No students.");
        }
    }
}
