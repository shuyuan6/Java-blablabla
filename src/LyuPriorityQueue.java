import java.util.*;

public class LyuPriorityQueue<T extends Comparable<T>> {
    public class Node {
        T i;
        Node next;
    }

    Node head;
    Node tail;
    int count;

    public LyuPriorityQueue() {
        head = null;
        tail = null;
        count = 0;
    }

    public boolean add(T t) {
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
        return true;
    }

    public T remove() {
        if (head == null) {
            return null;
        }

        Node minValueNode = head;
        Node curr = head;
        Node minNodePrevious = head;
        Node currPrevious = head;
        while (curr != null) {
            if (curr.i.compareTo(minValueNode.i) < 0) {
                minValueNode = curr;
                minNodePrevious = currPrevious;
            }
            currPrevious = curr;
            curr = curr.next;
        }
        minNodePrevious.next = minValueNode.next;
        count--;
        if (head == minValueNode) {
            head = head.next;
        }
        if (tail == minValueNode) {
            tail = minNodePrevious;
        }
        return minValueNode.i;
    }

    public void printPQArray() {
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
        LyuPriorityQueue<Integer> lPQ = new LyuPriorityQueue<>();
        PriorityQueue<Integer> golden = new PriorityQueue<>();

        Random random = new Random();
        int range = 20000;
        for (int i = 0; i < 1000; i++) {
            int dice = random.nextInt(3);
            if (dice < 2) {
                Integer toAdd = random.nextInt(range);
                System.out.println("Adding " + toAdd);
                boolean goldenRet = golden.add(toAdd);
                boolean ret = lPQ.add(toAdd);
                if (goldenRet != ret) {
                    throw new RuntimeException();
                }
            } else {
                Integer goldenRet = golden.poll();
                System.out.println("Removed " + goldenRet);
                Integer ret = lPQ.remove();
                if (goldenRet != ret) {
                    throw new RuntimeException();
                }
            }
        }
    }
}
