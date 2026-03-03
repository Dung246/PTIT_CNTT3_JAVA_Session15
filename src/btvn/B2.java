package btvn;

import java.util.LinkedList;
import java.util.Queue;

public class B2 {

    // ===== LỚP PATIENT =====
    static class Patient {
        private String id;
        private String name;
        private int age;

        public Patient(String id, String name, int age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "ID: " + id + " | Tên: " + name + " | Tuổi: " + age;
        }
    }

    // ===== LỚP QUẢN LÝ QUEUE =====
    static class PatientQueue {
        private Queue<Patient> queue = new LinkedList<>();

        public void addPatient(Patient p) {
            queue.offer(p); // enqueue
            System.out.println("\nĐã thêm bệnh nhân:");
            System.out.println(p);
            displayQueue();
        }

        public Patient callNextPatient() {
            if (queue.isEmpty()) {
                System.out.println("\nKhông có bệnh nhân nào đang chờ.");
                return null;
            }

            Patient p = queue.poll(); // dequeue
            System.out.println("\nĐang khám bệnh nhân:");
            System.out.println(p);
            displayQueue();
            return p;
        }

        public Patient peekNextPatient() {
            if (queue.isEmpty()) {
                System.out.println("\nKhông có bệnh nhân nào đang chờ.");
                return null;
            }
            return queue.peek();
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }

        public void displayQueue() {
            System.out.println("\n=== DANH SÁCH BỆNH NHÂN ĐANG CHỜ (FIFO) ===");
            if (queue.isEmpty()) {
                System.out.println("Không có bệnh nhân nào.");
            } else {
                for (Patient p : queue) {
                    System.out.println(p);
                }
            }
            System.out.println("==========================================");
        }
    }

    // ===== MAIN TEST =====
    public static void main(String[] args) {

        PatientQueue patientQueue = new PatientQueue();

        patientQueue.addPatient(new Patient("BN01", "Nguyễn Văn A", 25));
        patientQueue.addPatient(new Patient("BN02", "Trần Thị B", 30));
        patientQueue.addPatient(new Patient("BN03", "Lê Văn C", 40));

        System.out.println("\nBệnh nhân tiếp theo sẽ được khám:");
        System.out.println(patientQueue.peekNextPatient());

        patientQueue.callNextPatient();
        patientQueue.callNextPatient();

        System.out.println("\nBệnh nhân tiếp theo sau khi đã gọi:");
        System.out.println(patientQueue.peekNextPatient());
    }
}
