/**
 * @Author: small_double
 * @Date: 19-4-9 下午3:20
 * 两个单调递增的链表，输出两个链表合成后的链表，
 * 当然我们需要合成后的链表满足单调不减规则。
 */

public class 链表合并 {
    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode Merge(ListNode list1, ListNode list2) {
        ListNode listNode = null;
        ListNode head = null;
        while (list1 != null && list2 != null) {
            if (list1.val >= list2.val) {
                if (head == null) {
                    head = listNode = list2;
                } else {
                    listNode.next = list2;
                    listNode = listNode.next;
                }
                list2 = list2.next;
            } else {
                if (head == null) {
                    head = listNode = list1;
                } else {
                    listNode.next = list1;
                    listNode = listNode.next;
                }
                list1 = list1.next;
            }
        }if(list1==null){
            listNode.next = list2;
        }else listNode.next = list1;
        return head;
    }

}
