package labmid;

public class DoublyLinkedList {
    static class Node {
        int data;
        Node prev, next;
        Node(int d) { data = d; prev = next = null; }
    }

    Node head;

    public void createInitial() {
        head = new Node(10);
        Node n2 = new Node(20);
        Node n3 = new Node(30);
        Node n4 = new Node(40);
        head.next = n2; n2.prev = head;
        n2.next = n3; n3.prev = n2;
        n3.next = n4; n4.prev = n3;
    }

    public void insertAtBeginning(int v) {
        Node newNode = new Node(v);
        if (head == null) { head = newNode; return; }
        newNode.next = head;
        head.prev = newNode;
        head = newNode;
    }

    public void insertAfterValue(int target, int v) {
        Node curr = head;
        while (curr != null && curr.data != target) curr = curr.next;
        if (curr == null) return;
        Node newNode = new Node(v);
        Node nxt = curr.next;
        curr.next = newNode;
        newNode.prev = curr;
        newNode.next = nxt;
        if (nxt != null) nxt.prev = newNode;
    }

    public void deleteLast() {
        if (head == null) return;
        if (head.next == null) { head = null; return; }
        Node curr = head;
        while (curr.next != null) curr = curr.next;
        Node prev = curr.prev;
        prev.next = null;
    }

    public String toStringForward() {
        StringBuilder sb = new StringBuilder();
        Node curr = head;
        while (curr != null) {
            sb.append(curr.data);
            if (curr.next != null) sb.append(" ⇄ ");
            curr = curr.next;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        DoublyLinkedList m = new DoublyLinkedList();
        m.createInitial();
        System.out.println("Initial: " + m.toStringForward());
        m.insertAtBeginning(5);
        System.out.println("After insert 5 at beginning: " + m.toStringForward());
        m.insertAfterValue(20, 25);
        System.out.println("After insert 25 after 20: " + m.toStringForward());
        m.deleteLast();
        System.out.println("After delete last: " + m.toStringForward());
    }
}

