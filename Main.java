import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static class Student {
        int id;
        String name;
        double[] marks;

        Student(int id, String name, double[] marks) {
            this.id = id;
            this.name = name;
            this.marks = marks;
        }

        double average() {
            double total = 0;
            for (double mark : marks) total += mark;
            return marks.length == 0 ? 0 : total / marks.length;
        }

        String grade() {
            double avg = average();
            if (avg >= 90) return "A";
            if (avg >= 75) return "B";
            if (avg >= 60) return "C";
            if (avg >= 40) return "D";
            return "F";
        }

        void display() {
            System.out.printf("%-6d %-18s %-10.2f %-5s%n",
                    id, name, average(), grade());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        while (true) {
            System.out.println("\\n--- Student Result Manager ---");
            System.out.println("1. Add student");
            System.out.println("2. View all students");
            System.out.println("3. Search by ID");
            System.out.println("4. Show class average");
            System.out.println("5. Exit");
            System.out.print("Choose: ");

            int choice = readInt(sc);

            switch (choice) {
                case 1:
                    System.out.print("Student ID: ");
                    int id = readInt(sc);
                    if (findStudent(students, id) != null) {
                        System.out.println("That ID already exists.");
                        break;
                    }

                    System.out.print("Student name: ");
                    String name = sc.nextLine().trim();
                    double[] marks = new double[3];

                    for (int i = 0; i < marks.length; i++) {
                        System.out.print("Mark in subject " + (i + 1) + " (0-100): ");
                        marks[i] = readMark(sc);
                    }

                    students.add(new Student(id, name, marks));
                    System.out.println("Student added successfully.");
                    break;

                case 2:
                    if (students.isEmpty()) {
                        System.out.println("No student records available.");
                    } else {
                        System.out.printf("%-6s %-18s %-10s %-5s%n",
                                "ID", "NAME", "AVERAGE", "GRADE");
                        for (Student student : students) student.display();
                    }
                    break;

                case 3:
                    System.out.print("Enter student ID: ");
                    Student found = findStudent(students, readInt(sc));
                    if (found == null) System.out.println("Student not found.");
                    else {
                        System.out.printf("%-6s %-18s %-10s %-5s%n",
                                "ID", "NAME", "AVERAGE", "GRADE");
                        found.display();
                    }
                    break;

                case 4:
                    if (students.isEmpty()) {
                        System.out.println("No records to calculate.");
                    } else {
                        double total = 0;
                        for (Student student : students) total += student.average();
                        System.out.printf("Class average: %.2f%n", total / students.size());
                    }
                    break;

                case 5:
                    System.out.println("Thank you for using Student Result Manager.");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    static Student findStudent(ArrayList<Student> students, int id) {
        for (Student student : students) {
            if (student.id == id) return student;
        }
        return null;
    }

    static int readInt(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.print("Enter a valid number: ");
            sc.next();
        }
        int value = sc.nextInt();
        sc.nextLine();
        return value;
    }

    static double readMark(Scanner sc) {
        while (true) {
            if (sc.hasNextDouble()) {
                double value = sc.nextDouble();
                sc.nextLine();
                if (value >= 0 && value <= 100) return value;
            } else {
                sc.next();
                sc.nextLine();
            }
            System.out.print("Enter a mark between 0 and 100: ");
        }
    }
}
