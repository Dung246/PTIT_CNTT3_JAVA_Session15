package btvn;
import java.util.*;

public class B6 {

    static class Patient {
        private String id;
        private String name;
        private int age;
        private String gender;

        public Patient(String id, String name, int age, String gender) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.gender = gender;
        }

        @Override
        public String toString() {
            return id + " | " + name + " | " + age + " | " + gender;
        }
    }

    static class PatientWaitingQueue {
        private Queue<Patient> waitingQueue = new LinkedList<>();
        private int totalPatients = 0;

        public void addPatient(Patient p) {
            waitingQueue.offer(p);
            totalPatients++;
        }

        public Patient callPatient() {
            if (waitingQueue.isEmpty()) return null;
            totalPatients--;
            return waitingQueue.poll();
        }

        public void display() {
            for (Patient p : waitingQueue) System.out.println(p);
        }
    }

    static class EditAction {
        private String description;
        private String editedBy;
        private String editTime;

        public EditAction(String description, String editedBy, String editTime) {
            this.description = description;
            this.editedBy = editedBy;
            this.editTime = editTime;
        }

        @Override
        public String toString() {
            return editTime + " | " + editedBy + " | " + description;
        }
    }

    static class MedicalRecordHistory {
        private Stack<EditAction> editStack = new Stack<>();
        private String recordId;

        public MedicalRecordHistory(String recordId) {
            this.recordId = recordId;
        }

        public void addEdit(EditAction action) {
            editStack.push(action);
        }

        public EditAction undoEdit() {
            if (editStack.isEmpty()) return null;
            return editStack.pop();
        }

        public void display() {
            for (EditAction e : editStack) System.out.println(e);
        }
    }

    static class Ticket {
        private int ticketNumber;
        private String issuedTime;

        public Ticket(int ticketNumber, String issuedTime) {
            this.ticketNumber = ticketNumber;
            this.issuedTime = issuedTime;
        }

        @Override
        public String toString() {
            return "Số: " + ticketNumber + " | " + issuedTime;
        }
    }

    static class TicketSystem {
        private Queue<Ticket> ticketQueue = new LinkedList<>();
        private int currentNumber = 0;

        public Ticket issueTicket(String time) {
            currentNumber++;
            Ticket t = new Ticket(currentNumber, time);
            ticketQueue.offer(t);
            return t;
        }

        public Ticket callNext() {
            return ticketQueue.poll();
        }

        public void display() {
            for (Ticket t : ticketQueue) System.out.println(t);
        }
    }

    static class InputAction {
        private String fieldName;
        private String oldValue;
        private String newValue;
        private String actionTime;

        public InputAction(String fieldName, String oldValue, String newValue, String actionTime) {
            this.fieldName = fieldName;
            this.oldValue = oldValue;
            this.newValue = newValue;
            this.actionTime = actionTime;
        }

        @Override
        public String toString() {
            return actionTime + " | " + fieldName + " | " + oldValue + " -> " + newValue;
        }
    }

    static class UndoManager {
        private Stack<InputAction> undoStack = new Stack<>();
        private int maxUndoSteps;

        public UndoManager(int maxUndoSteps) {
            this.maxUndoSteps = maxUndoSteps;
        }

        public void addAction(InputAction action) {
            if (undoStack.size() == maxUndoSteps) {
                undoStack.remove(0);
            }
            undoStack.push(action);
        }

        public InputAction undo() {
            if (undoStack.isEmpty()) return null;
            return undoStack.pop();
        }

        public void display() {
            for (InputAction a : undoStack) System.out.println(a);
        }
    }

    public static void main(String[] args) {

        PatientWaitingQueue waitingQueue = new PatientWaitingQueue();
        waitingQueue.addPatient(new Patient("BN01", "Nguyễn Văn A", 25, "Nam"));
        waitingQueue.addPatient(new Patient("BN02", "Trần Thị B", 30, "Nữ"));
        waitingQueue.display();

        MedicalRecordHistory history = new MedicalRecordHistory("HS01");
        history.addEdit(new EditAction("Thêm chẩn đoán", "BS Minh", "09:00"));
        history.addEdit(new EditAction("Cập nhật thuốc", "BS Minh", "09:10"));
        history.display();

        TicketSystem ticketSystem = new TicketSystem();
        ticketSystem.issueTicket("08:00");
        ticketSystem.issueTicket("08:05");
        ticketSystem.display();

        UndoManager undoManager = new UndoManager(5);
        undoManager.addAction(new InputAction("Tên", "A", "B", "10:00"));
        undoManager.addAction(new InputAction("Tuổi", "20", "21", "10:05"));
        undoManager.display();
    }
}

