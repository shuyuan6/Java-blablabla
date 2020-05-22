import java.util.PriorityQueue;
import java.util.Random;

public class LyuHeapPriorityQueue<T extends Comparable<T>> {
    Object[] arr;
    int end;
    int capacity;

    public LyuHeapPriorityQueue(int capacity){
        this.capacity = capacity;
        arr = new Object[capacity];
        end = 0;
    }

    public boolean add(T t) {
        arr[end] = t;
        int curr = end;
        end++;

        while (curr != 0) {
            int parent = (curr - 1) / 2;
            if (((T)arr[curr]).compareTo((T)arr[parent]) < 0) {
                T temp = (T)arr[parent];
                arr[parent] = arr[curr];
                arr[curr] = temp;
                curr = parent;
            } else {
                break;
            }
        }
        return true;
    }

    public T poll() {
        if (end == 0) {
            return null;
        }

        T ret = (T)arr[0];
        arr[0] = arr[end - 1];
        int curr = 0;
        end--;

        while (2 * curr + 1 < end) {
            int left = 2 * curr + 1;
            int right = 2 * curr + 2;
            if (right < end) {
                if (((T)arr[curr]).compareTo((T)arr[left]) > 0 || ((T)arr[curr]).compareTo((T)arr[right]) > 0) {
                    if (((T)arr[left]).compareTo((T)arr[right]) <= 0) {
                        T temp = (T)arr[left];
                        arr[left] = arr[curr];
                        arr[curr] = temp;
                        curr = left;
                    } else {
                        T temp = (T)arr[right];
                        arr[right] = arr[curr];
                        arr[curr] = temp;
                        curr = right;
                    }
                } else {
                    break;
                }
            } else {
                if (((T)arr[curr]).compareTo((T)arr[left]) > 0) {
                    T temp = (T)arr[left];
                    arr[left] = arr[curr];
                    arr[curr] = temp;
                    curr = left;
                } else {
                    break;
                }
            }
        }
        return ret;
    }

    public void printPQArray() {
        for (int i = 0; i < end; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.printf("End: %d", end);
        System.out.println();
    }

    static public void main(String[] args) {
        LyuHeapPriorityQueue<Integer> lPQ = new LyuHeapPriorityQueue<>(200);
        PriorityQueue<Integer> golden = new PriorityQueue<>();

        Random random = new Random();
        int range = 20000;
        for (int i = 0; i < 200; i++) {
            int dice = random.nextInt(3);
            if (dice < 1) {
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
                Integer ret = lPQ.poll();
                if (goldenRet != ret) {
                    throw new RuntimeException();
                }
            }
        }
    }
}
