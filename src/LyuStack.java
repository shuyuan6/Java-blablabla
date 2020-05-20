import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LyuStack<T> {
    T[] arr;
    int tail;
    int capacity;
    int count;

    public LyuStack(int capacity) {
        arr = (T[])new Object[capacity];
        tail = 0;
        count = 0;
        this.capacity = capacity;
    }

    public void add(T t) {
        if (count == capacity) {
            arr = Arrays.copyOf(arr, 2 * capacity);
            arr[tail] = t;
            tail++;
            count++;
            capacity = 2 * capacity;
        } else {
            arr[tail] = t;
            tail++;
            count++;
        }
    }

    public T pop() {
        if (count == 0) {
            return null;
        }

        T ret = arr[tail - 1];
        tail--;
        count--;
        return ret;
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public void printSArray() {
        System.out.printf("Count: %d, Tail: %d\n", count, tail);
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

        LyuStack<Integer> s = new LyuStack(5);
        s.add(1);
        s.add(2);
        s.printSArray();

        Integer p1 = s.pop();
        System.out.println("p1: " + p1);
        s.printSArray();

        Integer p2 = s.pop();
        System.out.println("p2: " + p2);
        s.printSArray();

        Integer p3 = s.pop();
        System.out.println("p3: " + p3);
        s.printSArray();

        for (int i = 0; i < 6; i++) {
            s.add(i);
        }
        s.printSArray();

        for (int i = 0; i < 7; i++) {
            System.out.println("now pop: " + s.pop());
            s.printSArray();
        }

        /*
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
        */

    }

}
