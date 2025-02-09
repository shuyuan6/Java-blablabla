import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LyuHashMap<K, V> {
    public static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    public Node<K, V>[] buckets;
    public int count = 0;
    public int capacity;
    final public static double LOAD_THRESHOLD = 2.0;

    public LyuHashMap() {
        count = 0;
        capacity = 8;
        buckets = new Node[capacity];
    }

    public V put(K key, V value) {
        if (count / (double) capacity >= LOAD_THRESHOLD) {
            increaseCapacity();
        }
        return addInternal(key, value);
    }

    private void increaseCapacity() {
        Node<K, V>[] tmp = buckets;
        buckets = new Node[capacity * 2];
        count = 0;
        capacity = capacity * 2;
        for (Node<K, V> n : tmp) {
            while (n != null) {
                addInternal(n.key, n.value);
                n = n.next;
            }
        }
    }

    private V addInternal(K key, V value) {
        int index = key.hashCode() % buckets.length;
        if (buckets[index] == null) {
            buckets[index] = new Node<>(key, value);
            count++;
            return null;

        } else {
            Node curr = buckets[index];
            while (curr.next != null) {
                if (curr.key.equals(key)) {
                    System.out.println("key: " + curr.key + " previousValue: " + curr.value);
                    V oldValue = (V)curr.value;
                    curr.value = value;
                    System.out.println("currValue: " + curr.value);
                    return oldValue;
                }
                curr = curr.next;
            }
            if (!curr.key.equals(key)) {
                curr.next = new Node<>(key, value);
                count++;
                return null;
            } else {
                V oldValue = (V)curr.value;
                curr.value = value;
                return oldValue;
            }
        }
    }

    public V get(K key) {
        Node<K, V> n = buckets[key.hashCode() % buckets.length];
        while (n != null) {
            if (n.key.equals(key)) {
                return n.value;
            }
            n = n.next;
        }
        return null;
    }

    public boolean contains(K key) {
        int index = key.hashCode() % buckets.length;

        if (buckets[index] == null) {
            return false;
        }
        else {
            Node<K, V> curr = buckets[index];
            while (curr != null) {
                if (curr.key.equals(key)) {
                    return true;
                }
                curr = curr.next;
            }
        }
        return false;
    }

    public V remove(K key) {
        int index = key.hashCode() % buckets.length;
        Node<K, V> curr = buckets[index];

        if (buckets[index] == null) {
            return null;
        }

        if (buckets[index].key.equals(key)) {
            V oldValue = curr.value;
            buckets[index] = curr.next;
            count--;
            return oldValue;
        }

        Node<K, V> prev = curr;
        while (curr != null) {
            if (curr.key.equals(key)) {
                V oldValue = curr.value;
                prev.next = curr.next;
                count--;
                return oldValue;
            }
            prev = curr;
            curr = curr.next;
        }
        return null;
    }

    public void printHashMap() {
        System.out.printf("Count: %d, buckets length: %d\n", count, buckets.length);
        for (Node n : buckets) {
            if (n != null) {
                while (n != null) {
                    System.out.print(n.key + " ");
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

    public static void main(String[] args) {

        LyuHashMap<Integer, Integer> hmap = new LyuHashMap<>();
        Map<Integer, Integer> golden = new HashMap<>();

        Random random = new Random();
        int range = 10;
        for (int i = 0; i < 1000; i++) {
            int dice = random.nextInt(4);
            if (dice == 0) {
                int toAddKey = random.nextInt(range);
                int toAddValue = random.nextInt(range);
                System.out.println("Adding " + toAddKey + ": " + toAddValue);
                Integer goldenRet = golden.put(toAddKey, toAddValue);
                Integer ret = hmap.put(toAddKey, toAddValue);
                if (goldenRet == null && ret == null) {
                    continue;
                } else if (goldenRet == null) {
                    throw new RuntimeException();
                } else if (ret == null) {
                    throw new RuntimeException();
                } else {
                    if (!goldenRet.equals(ret)) {
                        throw new RuntimeException();
                    }
                }
            } else if (dice == 1) {
                Integer toGet = random.nextInt(range);
                System.out.println("Getting " + toGet);
                Integer goldenRet = golden.get(toGet);
                Integer ret = hmap.get(toGet);
                if (goldenRet == null && ret == null) {
                    continue;
                } else if (goldenRet == null) {
                    throw new RuntimeException();
                } else if (ret == null) {
                    throw new RuntimeException();
                } else {
                    if (!goldenRet.equals(ret)) {
                        throw new RuntimeException();
                    }
                }
            } else if (dice == 2) {
                Integer toRemove = random.nextInt(range);
                System.out.println("Removing " + toRemove);
                Integer goldenRet = golden.remove(toRemove);
                Integer ret = hmap.remove(toRemove);
                if (goldenRet == null && ret == null) {
                    continue;
                } else if (goldenRet == null) {
                    throw new RuntimeException();
                } else if (ret == null) {
                    throw new RuntimeException();
                } else {
                    if (!goldenRet.equals(ret)) {
                        throw new RuntimeException();
                    }
                }
            } else {
                Integer toSearch = random.nextInt(range);
                System.out.println("Searching " + toSearch);
                boolean goldenRet = golden.containsKey(toSearch);
                boolean ret = hmap.contains(toSearch);
                if (goldenRet != ret) {
                    throw new RuntimeException();
                }
            }
        }
        hmap.printHashMap();



/*
        LyuHashMap<Integer, Character> hashMap = new LyuHashMap<>();
        hashMap.put(1, 'a');
        hashMap.put(2, 'b');
        hashMap.put(1, 'c');
        hashMap.put(3, 'c');
        hashMap.put(4, 'd');

        System.out.println("contains 1: " + hashMap.contains(1));
        System.out.println("contains 5: " + hashMap.contains(5));
        hashMap.printHashMap();

        System.out.println("1: " + hashMap.get(1));
        System.out.println("2: " + hashMap.get(2));

 */
    }

}
