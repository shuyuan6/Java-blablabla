import java.util.*;

public class MergeLists {

    public static class Node {
        public int val;
        public int id;
        public int cursor;

        public Node(int val, int id, int cursor) {
            this.val = val;
            this.id = id;
            this.cursor = cursor;
        }

        public int getVal() {
            return val;
        }

        public int getId() {
            return id;
        }

        public int getCursor() {
            return cursor;
        }
    }

    static int k = 5;
    static int n = 10;

    // O(n)
    // O(n^2)
    // O(log(n))

    //O(xlogx)

    //O(kn*logk)
    public static List<Integer> mergeSortedList3(List<List<Integer>> input) {
        List<Integer> res = new ArrayList<>();
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            public int compare(Node o1, Node o2) {
                return (o1.val - o2.val);
            }
        });

        for (int i = 0; i < input.size(); i++){
            pq.add(new Node(input.get(i).get(0), i, 0));
        }

        while(pq.size() > 0){
            Node curr = pq.poll();
            res.add(curr.val);
            if (curr.cursor < input.get(curr.id).size() - 1){
                Node next = new Node(
                        input.get(curr.id).get(curr.cursor + 1),
                        curr.id,
                        curr.cursor + 1);
                pq.add(next);
            }
        }
        return res;
    }
    //O(knlog(kn))
    public static List<Integer> mergeSortedLists2(List<List<Integer>> input) {
        List<Integer> res = new ArrayList<>(k*n);
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        //knlog(kn)
        for (int i = 0; i < input.size(); i++){
            for (int j = 0; j < input.get(i).size(); j++){
                pq.add(input.get(i).get(j));
            }
        }
        //knlog(kn)
        while (!pq.isEmpty()){
            res.add(pq.poll());
        }

        return res;
    }

    // add's time complexity is O(lg(n))
    // poll's time complexity is O(lg(n))
    // suppose input contains x elements
    // 1 + 2 + .... x = x(x+1)/2
    // x^2
    //lg(1) + log(2) + ...... + log(x)
    //O(xlogx)
    // O(k * x)

    // O(nk^2)
    public static List<Integer> mergeSortedList(List<List<Integer>> input) {
        int k = input.size();
        List<Integer> res = new ArrayList<>(k*n);

        int[] cursors = new int[k];

        boolean someInBound = true;

        while (someInBound){
            int curr_min = Integer.MAX_VALUE;
            int curr_min_index = -1;
            for (int i = 0; i < k; i++){
                if (cursors[i] < input.get(i).size() && input.get(i).get(cursors[i]) < curr_min){
                    curr_min = input.get(i).get(cursors[i]);
                    curr_min_index = i;
                }
            }
            res.add(curr_min);
            cursors[curr_min_index]++;

            someInBound = false;
            for (int i = 0; i < k; i++){
                if (cursors[i] < input.get(i).size()) {
                    someInBound = true;
                    break;
                }
            }
        }
        return res;
    }

    static public void main(String[] args) {


        Random r = new Random();
        List<List<Integer>> input = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            input.add(new ArrayList<>());
            for (int j = 0; j < n; j++) {
                input.get(i).add(j * 10 + r.nextInt(10));
            }
        }

        for (int i = 0; i < k; i++){
            for (int j = 0; j < n; j++){
                System.out.print(input.get(i).get(j)+ " ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");

        /*
        long t1 = System.currentTimeMillis();
        List<Integer> res = mergeSortedList(input);
        long t2 = System.currentTimeMillis();

        long t3 = System.currentTimeMillis();
        List<Integer> res2 = mergeSortedLists2(input);
        long t4 = System.currentTimeMillis();

         */

        List<Integer> res = mergeSortedList(input);
        List<Integer> res2 = mergeSortedLists2(input);
        List<Integer> res3 = mergeSortedList3(input);

        for (int i = 0; i < res.size(); i++){
            System.out.print(res.get(i) + " ");
        }
        System.out.print("\n");

        for (int i = 0; i < res2.size(); i++){
            System.out.print(res2.get(i) + " ");
        }
        System.out.print("\n");

        for (int i = 0; i < res3.size(); i++){
            System.out.print(res3.get(i) + " ");
        }
        System.out.print("\n");
        /*
        boolean answerCorrect = true;
        for (int i = 1; i < res3.size(); i++){
            if (res3.get(i-1) > res3.get(i)){
                answerCorrect = false;
                break;
            }
        }
        System.out.println("Answer Correct: " + answerCorrect);

         */

        //System.out.println("Time: " + (t2 - t1));
        //System.out.println("Time2: " +(t4 - t3));

        //Node test = new Node(-1);

        // I expect ret to be [1, 2, 4, 5, 6, 7, 9]
    }
}
