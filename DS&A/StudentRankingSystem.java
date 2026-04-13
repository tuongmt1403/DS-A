import java.util.*;

public class StudentRankingSystem {
    static class Student {
        private String id;
        private String name;
        private double gpa;

        public Student(String id, String name, double gpa) {
            this.id = id;
            this.name = name;
            this.gpa = gpa;
        }

        public double getGpa() { return gpa; }

        public String toString() {
            return String.format("ID: %s | %s | GPA: %.2f", id, name, gpa);
        }
    }

    static class StudentPriorityQueue {
        private PriorityQueue<StudentEntry> maxHeap;
        private int counter = 0;

        private static class StudentEntry implements Comparable<StudentEntry> {
            double negatedGpa;
            int counter;
            Student student;

            public StudentEntry(Student s, int counter) {
                this.negatedGpa = -s.getGpa();
                this.counter = counter;
                this.student = s;
            }

            @Override
            public int compareTo(StudentEntry other) {
                int cmp = Double.compare(this.negatedGpa, other.negatedGpa);
                return (cmp != 0) ? cmp : Integer.compare(this.counter, other.counter);
            }
        }

        public StudentPriorityQueue() {
            this.maxHeap = new PriorityQueue<>();
        }

        public void addStudent(String id, String name, double gpa) {
            if (gpa < 0 || gpa > 4.0) {
                throw new IllegalArgumentException("GPA must be between 0.0 and 4.0");
            }
            Student s = new Student(id, name, gpa);
            maxHeap.offer(new StudentEntry(s, counter++));
            System.out.println("✓ Added: " + s);
        }

        public void displayRanking() {
            if (maxHeap.isEmpty()) {
                System.out.println("No students in the system.");
                return;
            }
            PriorityQueue<StudentEntry> temp = new PriorityQueue<>(maxHeap);
            System.out.println("\n=== STUDENT RANKING (Highest GPA First) ===");
            int rank = 1;
            while (!temp.isEmpty()) {
                System.out.println("Rank " + rank++ + ": " + temp.poll().student);
            }
        }
    }

    public static void main(String[] args) {
        StudentPriorityQueue system = new StudentPriorityQueue();
        system.addStudent("BH02428", "Nguyen Duy Tuong", 3.85);
        system.addStudent("BH02415", "Tran Thi B", 3.45);
        system.addStudent("BH02390", "Le Van C", 3.95);
        system.addStudent("BH02450", "Pham Thi D", 3.70);
        system.displayRanking();
    }
}