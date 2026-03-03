package btvn;

import java.util.Stack;

public class B3 {

    static class MedicationProcessChecker {

        private Stack<String> stack = new Stack<>();

        public boolean checkProcess(String[] actions) {
            for (int i = 0; i < actions.length; i++) {
                String action = actions[i];

                if (action.equals("PUSH")) {
                    stack.push(action);
                } else if (action.equals("POP")) {
                    if (stack.isEmpty()) {
                        System.out.println("Sai tại bước " + (i + 1) + ": POP khi Stack rỗng");
                        return false;
                    }
                    stack.pop();
                } else {
                    System.out.println("Sai tại bước " + (i + 1) + ": Thao tác không hợp lệ");
                    return false;
                }
            }

            if (!stack.isEmpty()) {
                System.out.println("Kết thúc ca trực nhưng vẫn còn thao tác chưa hoàn tất");
                return false;
            }

            return true;
        }

        public void reset() {
            stack.clear();
        }
    }

    public static void main(String[] args) {

        MedicationProcessChecker checker = new MedicationProcessChecker();

        String[] process1 = {"PUSH", "PUSH", "POP", "POP"};
        String[] process2 = {"PUSH", "POP", "POP"};
        String[] process3 = {"PUSH", "PUSH", "POP"};

        System.out.println("Quy trình 1: " + (checker.checkProcess(process1) ? "Hợp lệ" : "Không hợp lệ"));
        checker.reset();

        System.out.println("Quy trình 2: " + (checker.checkProcess(process2) ? "Hợp lệ" : "Không hợp lệ"));
        checker.reset();

        System.out.println("Quy trình 3: " + (checker.checkProcess(process3) ? "Hợp lệ" : "Không hợp lệ"));
    }
}
