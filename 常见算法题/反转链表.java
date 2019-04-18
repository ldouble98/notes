/**
 * @Author: small_double
 * @Date: 19-4-9 下午2:41
 * 输入一个链表，反转链表后，输出新链表的表头
 */
public class 反转链表 {
    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
    public ListNode ReverseList(ListNode head) {
        // if(head==null)
        //     return null;
        // ListNode newHead = null;
        // ListNode pNode = head;
        // ListNode pPrev = null;
        // while(pNode!=null){
        //     ListNode pNext = pNode.next;
        //     if(pNext==null)
        //         newHead = pNode;
        //     pNode.next = pPrev;
        //     pPrev = pNode;
        //     pNode = pNext;
        // }
        // return newHead;
        ListNode q = null;
        ListNode p = null;
        while (head!=null){
            q = head.next;
            head.next = p;
            p = head;
            head = q;
        }
        return  head;
    }
    public static void main(String[] args) {
        // ListNode q = null;
        // ListNode p = null;
        // while (head!=null){
        //     q = head.next;
        //     head.next = p;
        //     p = head;
        //     head = q;
        //
        // }

    }
}
