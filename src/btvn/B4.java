package btvn;

import java.util.PriorityQueue;
import java.util.Comparator;

public class B4{

    static class EmergencyPatient {
        private String id;
        private String name;
        private int priority;
        private long arrivalOrder;
        private static long counter = 0;

        public EmergencyPatient(String id, String name, int priority) {
            this.id = id;
            this.name = name;
            this.priority = priority;
            this.arrivalOrder = counter++;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getPriority() {
            return priority;
        }

        public long getArrivalOrder() {
            return arrivalOrder;
        }

        @Override
        public String toString() {
            return "ID: " + id + " | Tên: " + name +
                    " | Mức độ: " + (priority == 1 ? "Cấp cứu" : "Thông thường");
        }
    }

    static class EmergencyQueue {

        private PriorityQueue<EmergencyPatient> queue =
                new PriorityQueue<>(Comparator
                        .comparingInt(EmergencyPatient::getPriority)
                        .thenComparingLong(EmergencyPatient::getArrivalOrder));

        public void addPatient(EmergencyPatient p) {
            queue.offer(p);
            System.out.println("\nĐã thêm bệnh nhân:");
            System.out.println(p);
            displayQueue();
        }

        public EmergencyPatient callNextPatient() {
            if (queue.isEmpty()) {
                System.out.println("\nKhông có bệnh nhân nào.");
                return null;
            }

            EmergencyPatient p = queue.poll();
            System.out.println("\nĐang khám:");
            System.out.println(p);
            displayQueue();
            return p;
        }

        public void displayQueue() {
            System.out.println("\n=== DANH SÁCH CHỜ KHÁM ===");
            if (queue.isEmpty()) {
                System.out.println("Không có bệnh nhân.");
            } else {
                for (EmergencyPatient p : queue) {
                    System.out.println(p);
                }
            }
            System.out.println("==========================");
        }
    }

    public static void main(String[] args) {

        EmergencyQueue eq = new EmergencyQueue();

        eq.addPatient(new EmergencyPatient("BN01", "Nguyễn Văn A", 2));
        eq.addPatient(new EmergencyPatient("BN02", "Trần Thị B", 1));
        eq.addPatient(new EmergencyPatient("BN03", "Lê Văn C", 2));
        eq.addPatient(new EmergencyPatient("BN04", "Phạm Thị D", 1));

        eq.callNextPatient();
        eq.callNextPatient();
    }
}
