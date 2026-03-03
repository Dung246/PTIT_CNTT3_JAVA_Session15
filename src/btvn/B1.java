package btvn;

import java.util.Stack;

public class B1 {

    // ===== LỚP EDIT ACTION =====
    static class EditAction {
        private String description;
        private String time;

        public EditAction(String description, String time) {
            this.description = description;
            this.time = time;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        @Override
        public String toString() {
            return "Thời gian: " + time + " | Nội dung: " + description;
        }
    }

    // ===== LỚP QUẢN LÝ STACK =====
    static class MedicalRecordHistory {
        private Stack<EditAction> history = new Stack<>();

        public void addEdit(EditAction action) {
            history.push(action);
            System.out.println("\nĐã thêm chỉnh sửa:");
            System.out.println(action);
            displayHistory();
        }

        public EditAction undoEdit() {
            if (history.isEmpty()) {
                System.out.println("\nKhông có chỉnh sửa nào để Undo.");
                return null;
            }

            EditAction removed = history.pop();
            System.out.println("\nĐã Undo chỉnh sửa:");
            System.out.println(removed);
            displayHistory();
            return removed;
        }

        public EditAction getLatestEdit() {
            if (history.isEmpty()) {
                System.out.println("\nStack đang rỗng.");
                return null;
            }
            return history.peek();
        }

        public boolean isEmpty() {
            return history.isEmpty();
        }

        public void displayHistory() {
            System.out.println("\n=== LỊCH SỬ CHỈNH SỬA (Từ cũ → mới) ===");
            if (history.isEmpty()) {
                System.out.println("Không có chỉnh sửa nào.");
            } else {
                for (EditAction action : history) {
                    System.out.println(action);
                }
            }
            System.out.println("======================================");
        }
    }

    // ===== MAIN TEST =====
    public static void main(String[] args) {

        MedicalRecordHistory history = new MedicalRecordHistory();

        history.addEdit(new EditAction("Thêm chẩn đoán: Cảm cúm", "10:00"));
        history.addEdit(new EditAction("Cập nhật đơn thuốc", "10:15"));
        history.addEdit(new EditAction("Sửa thông tin bệnh nhân", "10:30"));

        System.out.println("\nChỉnh sửa gần nhất:");
        System.out.println(history.getLatestEdit());

        history.undoEdit();
        history.undoEdit();

        System.out.println("\nChỉnh sửa gần nhất sau Undo:");
        System.out.println(history.getLatestEdit());
    }
}
