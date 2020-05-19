import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class CircularBufferLyuQueue<T extends Comparable<T>> {
    Object[] arr;
    int head;
    int tail;
    int capacity;
    int count;

    public CircularBufferLyuQueue(int capacity) {
        arr = new Object[capacity];
        head = 0;
        tail = 0;
        this.capacity = capacity;
        count = 0;
    }

    public void add(T t) {
        if (count == capacity) {
            Object[] arrcopy = new Object[2 * capacity];
            System.arraycopy(arr, head, arrcopy, 0, capacity - head);
            System.arraycopy(arr, 0, arrcopy, capacity - head, head + 1);
            arr = arrcopy;
            head = 0;
            tail = capacity;
            arr[tail] = t;
            tail++;
            count++;
            capacity = 2 * capacity;
        } else {
            arr[tail] = t;
            tail = (tail + 1) % capacity;
            count++;
        }
    }

    public T remove() {
        if (count == 0) {
            return null;
        }

        T ret = (T) arr[head];
        head = (head + 1) % capacity;
        count--;
        return ret;
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public void printQArray() {
        System.out.printf("Count: %d, Head: %d, Tail: %d\n", count, head, tail);
        for (Object i : arr) {
            if (i != null) {
                System.out.print(i + " ");
            } else {
                System.out.print("x ");
            }
        }
        System.out.println();
    }

    static public void main(String[] args) {

        CircularBufferLyuQueue<String> q1 = new CircularBufferLyuQueue(5);

        int len = 100;
        List<Integer> input = new ArrayList<>(len);

        long start = System.currentTimeMillis();
        for (int i = 0; i < len; i++) {
            q1.add(String.valueOf(i));
        }

        for (int i = 0; i < len; i++) {
            String s = q1.remove();
            System.out.println(s);
        }
        long elapsed = System.currentTimeMillis() - start;

        System.out.printf("Elasped: %d\n", elapsed);

    }

}
