import java.util.*;

public class LyuTreeSet<T extends Comparable<T>> {
    static public class Node<T> {
        T value;
        Node<T> left;
        Node<T> right;

        Node(T value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    Node<T> root;

    public LyuTreeSet() {
        root = null;
    }

    public Node<T> insert(Node<T> root, T key) {
        if (root == null) {
            root = new Node<T>(key);
            return root;
        }

        if (key.compareTo(root.value) < 0) {
            root.left = insert(root.left, key);
        }
        else if (key.compareTo(root.value) > 0) {
            root.right = insert(root.right, key);
        }
        return root;
    }

    public boolean add(T t) {
        if (contains(t)) {
            return false;
        }
        root = insert(root, t);
        return true;
    }

    int getSize(Node<T> node) {
        if (node == null)
            return 0;
        else
            return(getSize(node.left) + 1 + getSize(node.right));
    }
    public int size() {
        return getSize(root);
    }

    public Node<T> search(Node<T> root, T key) {
        if (root == null || root.value.equals(key))
            return root;

        if (root.value.compareTo(key) > 0) {
            return search(root.left, key);
        }

        return search(root.right, key);
    }

    public boolean contains(T t) {
        Node<T> res = search(root, t);
        if (res == null) {
            return false;
        }

        return res.value.equals(t);
    }

    public void printInOrder(Node<T> root) {
        if (root != null) {
            printInOrder(root.left);
            System.out.println(root.value);
            printInOrder(root.right);
        }
    }
    public void print() {
        printInOrder(root);
    }

    public void all(Node<T> root, List<T> res) {
        if (root != null) {
            all(root.left, res);
            res.add(root.value);
            all(root.right, res);
        }
    }
    public List<T> getAll() {
        List<T> res = new LinkedList<>();
        all(root, res);
        return res;
    }

    public static void main(String[] args) {
        LyuTreeSet<Integer> test = new LyuTreeSet<>();

        test.add(20);
        test.add(30);
        test.add(40);
        test.add(50);
        test.add(60);
        test.add(70);
        test.add(80);
        test.add(90);
        test.add(45);
        test.add(35);
        test.add(35);
        test.add(2);
        test.add(20);
        System.out.println("size: " + test.size());
        System.out.println("search: " + test.contains(15));
        System.out.println("search: " + test.contains(35));
        test.print();
        List<Integer> res = test.getAll();
        System.out.println("all elements are: ");
        for (Integer i : res) {
            System.out.print(i + " ");
        }

        LyuTreeSet<Integer> tset = new LyuTreeSet<>();
        TreeSet<Integer> golden = new TreeSet<>();

        Random random = new Random();
        int range = 200;
        for (int i = 0; i < 1000; i++) {
            int dice = random.nextInt(3);
            if (dice == 0) {
                Integer toAdd = random.nextInt(range);
                System.out.println("Adding " + toAdd);
                boolean goldenRet = golden.add(toAdd);
                boolean ret = tset.add(toAdd);
                if (goldenRet != ret) {
                    System.out.println("golden: " + goldenRet);
                    System.out.println("tset: " + ret);
                    throw new RuntimeException();
                }
            }
            else if (dice == 1) {
                if (tset.size() != golden.size()) {
                    throw new RuntimeException();
                }
            } else {
                Integer toSearch = random.nextInt(range);
                System.out.println("Searching " + toSearch);
                boolean goldenRet = golden.contains(toSearch);
                boolean ret = tset.contains(toSearch);
                if (goldenRet != ret) {
                    System.out.println("golden: " + goldenRet);
                    System.out.println("tset: " + ret);
                    throw new RuntimeException();
                }
            }
        }
    }
}
