import java.util.ArrayList;
import java.util.List;

public class LinkedListLyuQueue<T> {

    public class Node {
        T i;
        Node next;
    }

    Node head;
    Node tail;
    int count;

    public LinkedListLyuQueue() {
        head = null;
        tail = null;
        count = 0;
    }

    public void add(T t) {
        if (head == null) {
            head = new Node();
            head.i = t;
            head.next = null;
            tail = head;
        } else {
            tail.next = new Node();
            tail.next.i = t;
            tail = tail.next;
        }
        count++;
    }

    public T remove() {
        if (head == null) {
            return null;
        }
        T ret = head.i;
        if (head.next == null) {
            head = null;
            tail = null;
        } else {
            head = head.next;
        }
        count--;
        return ret;
    }

    public int size() {
        return 0;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public void printQArray() {
        Node curr = head;
        while(curr != null) {
            System.out.print(curr.i + " ");
            curr = curr.next;
        }
        System.out.println();
        String headValue = head == null ? "null" : String.valueOf(head.i);
        String tailValue = tail == null ? "null" : String.valueOf(tail.i);
        System.out.printf("Head: %s, tail: %s, count: %d\n\n", headValue, tailValue, count);
    }

    static public void main(String[] args) {
        LinkedListLyuQueue<String> q1 = new LinkedListLyuQueue<>();
        q1.remove();
        q1.printQArray();

        q1.add("a");
        q1.printQArray();

        q1.add("b");
        q1.printQArray();

        q1.add("c");
        q1.printQArray();

        String r1 = q1.remove();
        if (!r1.equals("a")) {
            throw new RuntimeException();
        }
        q1.printQArray();

        String r2 = q1.remove();
        if (!r2.equals("b")) {
            throw new RuntimeException();
        }
        q1.printQArray();

        String r3 = q1.remove();
        if (!r3.equals("c")) {
            throw new RuntimeException();
        }
        q1.printQArray();

        String r4 = q1.remove();
        if (r4 != null) {
            throw new RuntimeException();
        }
        q1.printQArray();

        q1.add("3");
        q1.printQArray();

        q1.add("c");
        q1.printQArray();


        /*
        int len = 100000;
        List<Integer> input = new ArrayList<>(len);

        long start = System.currentTimeMillis();
        for (int i = 0; i < len; i++) {
            q1.add(i);
        }

        for (int i = 0; i < len; i++) {
            q1.remove();
        }
        long elapsed = System.currentTimeMillis() - start;

        System.out.printf("Elasped: %d\n", elapsed);

        */
    }

}
