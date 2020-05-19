import java.util.Arrays;

public class LyuQueue {

    Integer[] arr;
    int head;
    int tail;
    int capacity;

    public LyuQueue(int capacity) {
        arr = new Integer[capacity];
        head = 0;
        tail = 0;
        this.capacity = capacity;
    }

    public void add(Integer t) {
        if (capacity == tail) {
            System.out.println("The queue is full, new queue assigned");
            arr = Arrays.copyOf(arr, 2 * arr.length);
            arr[tail] = t;
            tail++;
            capacity = 2 * arr.length;
        }
        else {
            arr[tail] = t;
            tail++;
        }
    }

    public int remove() {
        int ret = -1;
        if (head == tail) {
            System.out.println("The queue is empty.");
        }
        else {
            ret = arr[head];
            for (int i = 0; i < tail - 1; i++) {
                arr[i] = arr[i + 1];
            }
            if (tail < capacity) {
                arr[tail] = 0;
            }
            tail--;
        }
        return ret;
    }

    public int size() {
        return tail - head;
    }

    public boolean isEmpty() {
        return (tail - head) == 0;
    }


    static public void main(String[] args) {
        LyuQueue q = new LyuQueue(5);
        for (int i = 0; i < 10; i++) {
            q.add(i);
            q.remove();
        }
        System.out.println("Size: " + q.size());
    }

}
