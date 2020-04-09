import java.util.*;

public class Tree {

    public static class Bignode {
        public Node node;
        public int height;
    }

    public static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node(int val) {
            this.val = val;
        }

        public int getVal(){
            return val;
        }

        public Node getLeft(){
            return left;
        }

        public Node getRight(){
            return right;
        }

        public void setVal(int val){
            this.val = val;
        }

        public void setLeft(Node left){
            this.left = left;
        }

        public void setRight(Node right){
            this.right = right;
        }
    }

    public static int findLeafSum(Node root) {
        if (root == null){
            return 0;
        }

        int sum = 0;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()){
            Node curr = q.remove();
            Node r = curr.getRight();
            Node l = curr.getLeft();

            if (r == null &&  l == null){
                sum = sum + curr.getVal();
            }

            if (r != null){
                q.add(r);

            }
            if (l != null){
                q.add(l);
            }
        }
        return sum;
    }

    public static int findNonLeafSum(Node root) {
        if (root == null){
            return 0;
        }
        int sum = 0;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        sum = sum + root.getVal();

        while (!q.isEmpty()){
            Node curr = q.remove();
            Node r = curr.getRight();
            Node l = curr.getLeft();

            if (r != null){
                q.add(r);
                if (r.getLeft() != null || r.getRight() != null){
                    sum = sum + r.getVal();
                }
            }
            if (l != null){
                q.add(l);
                if (l.getLeft() != null || l.getRight() != null){
                    sum = sum + l.getVal();
                }
            }
        }
        return sum;
    }

    public static int findSideSum(Node root) {

        if (root == null){
            return 0;
        }

        int ret = root.getVal();
        Node curr = root.left;
        while (curr != null) {
            ret += curr.getVal();
            curr = curr.getLeft();
        }
        curr = root.right;
        while (curr != null) {
            ret += curr.getVal();
            curr = curr.getRight();
        }
        return ret;
    }
    /*
     *                  1
     *                 / \
     *                2   3
     *               /\  / \
     *              4 5 6  7
     *                \     \
     *                8     9
     */

    public static List<List<Integer> >outputInLayers(Node root) {
        if (root == null){
            return null;
        }
        //int layers = findHeight(root);
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        //for (int i = 0; i < layers; i++){
        //    ret.add(new ArrayList<>());
        //}

        Queue<Bignode> q = new LinkedList<Bignode>();
        Bignode bigroot = new Bignode();
        bigroot.height = 1;
        bigroot.node = root;
        q.add(bigroot);
        int currHeight = 1;
        ret.add(new ArrayList<>());

        while(!q.isEmpty()){
            Bignode curr = q.remove();
            if (curr.height > currHeight){
                ret.add(new ArrayList<>());
                currHeight = curr.height;
            }
            ret.get(curr.height-1).add(curr.node.getVal());
            Node r = curr.node.getRight();
            Node l = curr.node.getLeft();

            if (l != null){
                Bignode left = new Bignode();
                left.height = curr.height + 1;
                left.node = l;
                q.add(left);
            }

            if (r != null){
                Bignode right = new Bignode();
                right.height = curr.height + 1;
                right.node = r;
                q.add(right);
            }
        }
        return ret;
    }


    /*
        List<Integer> arr1 = new ArrayList<>();
        arr1.add(7);
        ret.add(arr1);

        List<Integer> arr2 = new ArrayList<>();
        arr2.add(5);
        arr2.add(9);
        ret.add(arr2);

        ret.add(new ArrayList<>());
        ret.add(new ArrayList<>());
        ret.add(new ArrayList<>());
        ret.add(new ArrayList<>());

        ret.get(0).add(7);
        ret.get(1).add(5);
        ret.get(1).add(9);
        ret.get(2).add(1);
        ret.get(2).add(6);
        ret.get(2).add(8);
        ret.get(2).add(10);
        ret.get(3).add(7);
        ret.get(3).add(9);
        // [[7], [5,9], [1,6,8,10], [7,9]]

        return ret;
    }
     */

    public static int findHeight(Node root) {
        if (root == null){
            return 0;
        }

        Queue<Bignode> q = new LinkedList<>(); // 6,1,
        Bignode bigroot = new Bignode();
        bigroot.height = 1;
        bigroot.node = root;
        q.add(bigroot);
        int res = 0;

        while(!q.isEmpty()){
            Bignode curr = q.remove();
            res = Math.max(res, curr.height);
            Node r = curr.node.getRight();
            Node l = curr.node.getLeft();

            if (r != null){
                Bignode right = new Bignode();
                right.height = curr.height + 1;
                right.node = r;
                q.add(right);
            }

            if (l != null){
                Bignode left = new Bignode();
                left.height = curr.height + 1;
                left.node = l;
                q.add(left);
            }
        }
        return res;
    }

    public static int findSum(Node root) {

        if (root == null){
            return 0;
        }
        int sum = 0;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()){
            Node curr = q.remove();
            sum = sum + curr.getVal();
            Node r = curr.getRight();
            Node l = curr.getLeft();

            if (r != null){
                q.add(r);
            }
            if (l != null){
                q.add(l);
            }
        }
        return sum;
    }


    public static void main(String[] args) {


        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        Node n8 = new Node(8);
        Node n9 = new Node(9);


        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;
        n3.right = n7;
        n5.right = n8;
        n7.right = n9;

        /**
         *  All left and right references are pointing to objects except for n4's, n5's ,n6's and n7's
         */

        int ret = findSum(n1);
        int ret2 = findLeafSum(n1);
        int ret3 = findNonLeafSum(n1);
        int ret4 = findSideSum(n1);
        int ret5 = findHeight(n6);

        List<List<Integer>> ret6 = outputInLayers(n1);
        System.out.println("number of layers: " + ret6.size());
        for (List<Integer> outlayer : ret6){
            for (Integer eachnode : outlayer){
                System.out.print(eachnode + " ");
            }
            System.out.print("\n");
        }
        /*
        System.out.println(ret);
        System.out.println(ret2);
        System.out.println(ret3);
        System.out.println(ret4);
        System.out.println(ret5);
        */

    }

}
