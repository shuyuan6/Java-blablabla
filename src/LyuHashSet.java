import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Random;

public class LyuHashSet<T> {

    static public class Node<T> {
        T value;
        Node<T> next;

        Node(T value) {
            this.value = value;
            this.next = null;
        }
    }

    public Node<T>[] buckets;
    public int count;
    public int capacity;
    public static double load = 0.75;

    public LyuHashSet() {
        count = 0;
        capacity = 8;
        buckets = new Node[capacity];
    }

    public boolean add(T t) {
        if (count / (double) capacity >= load) {
            increaseCapacity();
        }
        return addInternal(t);
    }

    private void increaseCapacity() {
        Node<T>[] tmp = buckets;
        buckets = new Node[capacity * 2];
        count = 0;
        capacity = capacity * 2;
        for (Node<T> n : tmp) {
            while (n != null) {
                addInternal(n.value);
                n = n.next;
            }
        }
    }

    private boolean addInternal(T t) {
        int index = t.hashCode() % buckets.length;
        if (buckets[index] == null) {
            buckets[index] = new Node<>(t);
            count++;
            return true;

        } else {
            Node curr = buckets[index];
            while (curr.next != null) {
                if (curr.value.equals(t)) {
                    return false;
                }
                curr = curr.next;
            }
            if (!curr.value.equals(t)) {
                curr.next = new Node<>(t);
                count++;
                return true;
            }
            return false;
        }
    }

    public boolean contains(T t) {
        int index = t.hashCode() % buckets.length;

        if (buckets[index] == null) {
            return false;
        }
        else {
            Node<T> curr = buckets[index];
            while (curr != null) {
                if (curr.value.equals(t)) {
                    return true;
                }
                curr = curr.next;
            }
        }
        return false;
    }

    public boolean remove(T t) {
        int index = t.hashCode() % buckets.length;
        Node<T> curr = buckets[index];

        if (buckets[index] == null) {
            return false;
        }

        if (buckets[index].value.equals(t)) {
            buckets[index] = curr.next;
            count--;
            return true;
        }

        Node prev = curr;
        while (curr != null) {
            if (curr.value.equals(t)) {
                prev.next = curr.next;
                count--;
                return true;
            }
            prev = curr;
            curr = curr.next;
        }
        return false;
    }

    public int size() {
        return count;
    }

    public void printHashSetArray() {
        System.out.printf("Count: %d, buckets length: %d\n", count, buckets.length);
        for (Node n : buckets) {
            if (n != null) {
                while (n != null) {
                    System.out.print(n.value + " ");
                    n = n.next;
                }
                System.out.println();
            } else {
                System.out.print("x\n");
            }
        }
        System.out.println();
    }

    static public void main(String[] args) {
        LyuHashSet<Integer> hset = new LyuHashSet<>();
        HashSet<Integer> golden = new HashSet<>();

        Random random = new Random();
        int range = 20000;
        for (int i = 0; i < 1000; i++) {
            int dice = random.nextInt(3);
            if (dice == 0) {
                Integer toAdd = random.nextInt(range);
                System.out.println("Adding " + toAdd);
                boolean goldenRet = golden.add(toAdd);
                boolean ret = hset.add(toAdd);
                if (goldenRet != ret) {
                    throw new RuntimeException();
                }
            } else if (dice == 1) {
                Integer toRemove = random.nextInt(range);
                System.out.println("Removing " + toRemove);
                boolean goldenRet = golden.remove(toRemove);
                boolean ret = hset.remove(toRemove);
                if (goldenRet != ret) {
                    throw new RuntimeException();
                }
            } else {
                Integer toSearch = random.nextInt(range);
                System.out.println("Searching " + toSearch);
                boolean goldenRet = golden.contains(toSearch);
                boolean ret = hset.contains(toSearch);
                if (goldenRet != ret) {
                    throw new RuntimeException();
                }
            }
        }


        //hset.printHashSetArray();




        /*
        hset.printHashSetArray();
        for (int i = 0; i < 50; i++) {
            hset.add(String.valueOf(i));
        }
        hset.printHashSetArray();

        hset.remove("1");
        hset.printHashSetArray();
        hset.remove("49");
        hset.printHashSetArray();
        hset.remove("50");
        hset.printHashSetArray();
        hset.remove("23");
        hset.printHashSetArray();

        System.out.println("contains 23? " + hset.contains("23"));
        System.out.println("contains 246? " + hset.contains("246"));
        System.out.println("contains 2? " + hset.contains("2"));

         */
    }
}
