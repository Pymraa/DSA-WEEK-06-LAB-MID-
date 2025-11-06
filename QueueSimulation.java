package labmid;

public class QueueSimulation {
    static class CircularQueue {
        int[] q;
        int front, rear, size, capacity;
        CircularQueue(int cap) {
            capacity = cap;
            q = new int[cap];
            front = -1;
            rear = -1;
            size = 0;
        }
        boolean isFull() { return size == capacity; }
        boolean isEmpty() { return size == 0; }
        void enqueue(int val) {
            if (isFull()) return;
            if (isEmpty()) front = 0;
            rear = (rear + 1) % capacity;
            q[rear] = val;
            size++;
        }
        Integer dequeue() {
            if (isEmpty()) return null;
            int val = q[front];
            front = (front + 1) % capacity;
            size--;
            if (size == 0) { front = -1; rear = -1; }
            return val;
        }
        String contents() {
            if (isEmpty()) return "[]";
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (int i = 0; i < size; i++) {
                int idx = (front + i) % capacity;
                sb.append(q[idx]);
                if (i < size - 1) sb.append(", ");
            }
            sb.append("]");
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        CircularQueue cq = new CircularQueue(5);
        step("enqueue(10)", () -> { cq.enqueue(10); printState(cq); });
        step("enqueue(20)", () -> { cq.enqueue(20); printState(cq); });
        step("enqueue(30)", () -> { cq.enqueue(30); printState(cq); });
        step("dequeue()", () -> { cq.dequeue(); printState(cq); });
        step("enqueue(40)", () -> { cq.enqueue(40); printState(cq); });
        step("enqueue(50)", () -> { cq.enqueue(50); printState(cq); });
        step("dequeue()", () -> { cq.dequeue(); printState(cq); });
        step("enqueue(60)", () -> { cq.enqueue(60); printState(cq); });
    }

    static void printState(CircularQueue cq) {
        System.out.println("Front index: " + cq.front + "  Rear index: " + cq.rear + "  Contents: " + cq.contents());
    }

    interface Action { void run(); }
    static void step(String label, Action a) {
        System.out.println("Operation: " + label);
        a.run();
        System.out.println();
    }
}
