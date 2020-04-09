import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class ListNode {
    int val;
    ListNode next;

    public ListNode(int x) {
        val = x;
    }

    public static ListNode mergeLists(ListNode[] lists){
        if(lists==null || lists.length==0) {
            return null;
        }

        PriorityQueue<ListNode> queue = new PriorityQueue(new Comparator<ListNode>() {
            public int compare(ListNode o1, ListNode o2) {
                return (o1.val - o2.val);
            }
        });

        for (int i=0; i < lists.length; i++) {
            while(lists[i] != null) {
                queue.add(lists[i]);
                lists[i] = lists[i].next;
            }
        }

        ListNode dummy = new ListNode(-1);
        ListNode head = dummy;

        while( !queue.isEmpty() ) {
            dummy.next = queue.poll();
            dummy = dummy.next;
        }

        dummy.next = null;
        return head.next;
    }


    public static void main(String[] args){

        ListNode[] input = new ListNode[3];

        input[0] = new ListNode(1);
        input[0].next = new ListNode(3);
        input[0].next.next = new ListNode(8);

        input[1] = new ListNode(2);
        input[1].next = new ListNode(4);
        input[1].next.next = new ListNode(9);
        input[1].next.next.next = new ListNode(10);
        input[1].next.next.next.next = new ListNode(11);

        input[2] = new ListNode(3);
        input[2].next = new ListNode(5);

        ListNode res = mergeLists(input);

        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }
}