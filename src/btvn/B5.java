package btvn;
import java.util.*;

public class B5 {

    static class Patient {
        private String id;
        private String name;
        private int age;

        public Patient(String id, String name, int age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "ID: " + id + " | Tên: " + name + " | Tuổi: " + age;
        }
    }

    static class TreatmentStep {
        private String description;
        private String time;

        public TreatmentStep(String description, String time) {
            this.description = description;
            this.time = time;
        }

        @Override
        public String toString() {
            return "Thời gian: " + time + " | Bước: " + description;
        }
    }

    static class EmergencyCase {
        private Patient patient;
        private Stack<TreatmentStep> steps = new Stack<>();

        public EmergencyCase(Patient patient) {
            this.patient = patient;
        }

        public void addStep(TreatmentStep step) {
            steps.push(step);
            System.out.println("\nĐã thêm bước xử lý cho " + patient);
            System.out.println(step);
        }

        public TreatmentStep undoStep() {
            if (steps.isEmpty()) {
                System.out.println("\nKhông có bước nào để Undo.");
                return null;
            }
            TreatmentStep removed = steps.pop();
            System.out.println("\nĐã Undo bước:");
            System.out.println(removed);
            return removed;
        }

        public void displaySteps() {
            System.out.println("\n=== Các bước xử lý của bệnh nhân ===");
            if (steps.isEmpty()) {
                System.out.println("Chưa có bước xử lý.");
            } else {
                for (TreatmentStep step : steps) {
                    System.out.println(step);
                }
            }
            System.out.println("=====================================");
        }

        public Patient getPatient() {
            return patient;
        }
    }

    static class EmergencyCaseQueue {
        private Queue<EmergencyCase> cases = new LinkedList<>();

        public void addCase(EmergencyCase c) {
            cases.offer(c);
            System.out.println("\nĐã tiếp nhận bệnh nhân:");
            System.out.println(c.getPatient());
        }

        public EmergencyCase getNextCase() {
            if (cases.isEmpty()) {
                System.out.println("\nKhông còn ca cấp cứu nào.");
                return null;
            }
            EmergencyCase c = cases.poll();
            System.out.println("\nĐang xử lý bệnh nhân:");
            System.out.println(c.getPatient());
            return c;
        }
    }

    public static void main(String[] args) {

        EmergencyCaseQueue queue = new EmergencyCaseQueue();

        EmergencyCase case1 = new EmergencyCase(
                new Patient("BN01", "Nguyễn Văn A", 45));

        EmergencyCase case2 = new EmergencyCase(
                new Patient("BN02", "Trần Thị B", 30));

        queue.addCase(case1);
        queue.addCase(case2);

        EmergencyCase current = queue.getNextCase();

        if (current != null) {
            current.addStep(new TreatmentStep("Tiếp nhận bệnh nhân", "08:00"));
            current.addStep(new TreatmentStep("Chẩn đoán ban đầu", "08:05"));
            current.addStep(new TreatmentStep("Tiêm thuốc giảm đau", "08:10"));

            current.displaySteps();

            current.undoStep();

            current.displaySteps();
        }

        EmergencyCase next = queue.getNextCase();

        if (next != null) {
            next.addStep(new TreatmentStep("Tiếp nhận bệnh nhân", "08:20"));
            next.addStep(new TreatmentStep("Chụp X-quang", "08:25"));
            next.displaySteps();
        }
    }
}
