import java.util.Stack;

/**
 * @Author: small_double
 * @Date: 19-4-9 下午2:26
 * 输入一个链表，输出该链表中倒数第k个结点。
 * 先让第一个指针和第二个指针都指向头结点，
 * 然后再让第一个指正走(k-1)步，到达第k个节点。
 */

public class 链表中倒数第k个结点 {
    class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
    public ListNode FindKthToTail2(ListNode head,int k) {
        ListNode listNode = head;
        ListNode listNode1;
        if(head==null||k<0){
            return  null;
        }
        for (int i = 0; i < k; i++) {
            if(head.next!=null){
                head = head.next;
            }else {
                return null;
            }

        }
        while (head.next!=null){
            listNode = listNode.next;
            head = head.next;
        }
        return  listNode;
    }
    public ListNode FindKthToTail(ListNode head,int k) {
        ListNode listNode =head;
        Stack<ListNode> stack = new Stack<>();
        while (head!=null){
            stack.push(head);
            head = head.next;
        }
        for (int i = 0; i < k; i++) {
            listNode = stack.pop();
        }
        return listNode;
    }
    public static void main(String[] args) {

    }
}
