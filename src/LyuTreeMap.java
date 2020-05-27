import java.util.*;

public class LyuTreeMap<K extends Comparable<K>, V> {

    static public class Node<K, V> {
        K key;
        V value;
        Node<K, V> left;
        Node<K, V> right;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }

        Node(K key) {
            this.key = key;
            this.value = null;
            this.left = null;
            this.right = null;
        }
    }

    Node<K, V> root;
    int size;

    public LyuTreeMap() {
        root = null;
        size = 0;
    }


    public V put(K key, V value) {
        if (root == null) {
            root = new Node<>(key, value);
            size++;
            return null;
        }
        Node<K, V> curr = root;
        Node<K, V> previous = root;
        while (curr != null) {
            if (curr.key.compareTo(key) == 0) {
                V temp = curr.value;
                curr.value = value;
                return temp;
            } else if (curr.key.compareTo(key) < 0) {
                previous = curr;
                curr = curr.right;

            } else {
                previous = curr;
                curr = curr.left;
            }
        }
        if (previous.key.compareTo(key) < 0) {
            previous.right = new Node<>(key, value);
        } else {
            previous.left = new Node<>(key, value);
        }
        size++;
        return null;
    }

    public boolean containsKey(K k) {
        Node<K, V> curr = root;
        while (curr != null) {
            if (curr.key.compareTo(k) == 0) {
                return true;
            } else if (curr.key.compareTo(k) < 0) {
                curr = curr.right;
            } else {
                curr = curr.left;
            }
        }
        return false;
    }

    public V get(K key) {
        Node<K, V> curr = root;
        while (curr != null) {
            if (curr.key.equals(key)) {
                return curr.value;
            } else if (curr.key.compareTo(key) < 0) {
                curr = curr.right;
            } else {
                curr = curr.left;
            }
        }
        return null;
    }

    public int size(){
        return size;
    }

    public void printInOrder(Node<K, V> root) {
        if (root != null) {
            printInOrder(root.left);
            System.out.println("Key: " + root.key + " Value: " + root.value);
            printInOrder(root.right);
        }
    }
    public void print() {
        printInOrder(root);
    }

    public void all(Node<K, V> root, List<Map.Entry<K, V>> res) {
        if (root != null) {
            all(root.left, res);
            Map.Entry<K, V> entry = new Map.Entry<K, V>() {
                @Override
                public K getKey() {
                    return root.key;
                }

                @Override
                public V getValue() {
                   return root.value;
                }

                @Override
                public V setValue(V value) {
                    V temp = root.value;
                    root.value = value;
                    return temp;
                }
            };
            res.add(entry);
            all(root.right, res);
        }
    }
    public List<Map.Entry<K, V>> getAll() {
        List<Map.Entry<K, V>> res = new LinkedList<>();
        all(root, res);
        return res;
    }

    public static void main(String[] args) {
        LyuTreeMap<Integer, Integer> test = new LyuTreeMap<>();
        test.put(2, 20);
        test.put(3, 30);
        test.put(4, 40);
        test.put(5, 50);
        test.put(6, 60);
        test.put(7, 70);
        test.put(8, 80);
        test.put(9, 90);
        test.put(2, 15);
        System.out.println("size: " + test.size());
        System.out.println("search: " + test.containsKey(15));
        System.out.println("search: " + test.containsKey(2));
        test.print();
        List<Map.Entry<Integer, Integer>> getAllBBB = test.getAll();
        for (Map.Entry<Integer, Integer> e : getAllBBB) {
            System.out.printf("Key: %d, Value: %d\n", e.getKey(), e.getValue());
        }

        LyuTreeMap<Integer, Integer> tmap = new LyuTreeMap<>();
        Map<Integer, Integer> golden = new TreeMap<>();

        Random random = new Random();
        int range = 10;
        for (int i = 0; i < 1000; i++) {
            int dice = random.nextInt(4);
            if (dice == 0) {
                int toAddKey = random.nextInt(range);
                int toAddValue = random.nextInt(range);
                System.out.println("Adding " + toAddKey + ": " + toAddValue);
                Integer goldenRet = golden.put(toAddKey, toAddValue);
                Integer ret = tmap.put(toAddKey, toAddValue);
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
                Integer ret = tmap.get(toGet);
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
                boolean ret = tmap.containsKey(toSearch);
                if (goldenRet != ret) {
                    throw new RuntimeException();
                }
            }
        }
        tmap.print();
    }
}
