package problems.lc.easy;

import ds.ListNode;
import util.LinkedListUtil;

/*
Given the head of a sorted linked list, delete all duplicates such that each element appears only once. Return the linked list sorted as well.



Example 1:

Input: head = [1,1,2]
Output: [1,2]
Example 2:

Input: head = [1,1,2,3,3]
Output: [1,2,3]
*/

public class RemoveDuplicatesFromSortedList {
    public static void main(String[] args) {
        RemoveDuplicatesFromSortedList obj = new RemoveDuplicatesFromSortedList();
        ListNode list = LinkedListUtil.createList(new int[]{1,1,1,2,3,3});
        ListNode resultList = obj.deleteDuplicates(list);

        System.out.print("Sorted list with duplicates removed: ");
        while (resultList != null) {
            System.out.print(resultList.val);
            resultList = resultList.next;
            if (resultList != null) {
                System.out.print(", ");
            }
        }
    }
    private ListNode deleteDuplicates(ListNode head) {
        /*
        ListNode resultList = head;
        ListNode prevNode = head;
        while (head != null) {
            head = head.next;
            if (head == null) {
                break;
            }
            if (head.val == prevNode.val) {
                prevNode.next = head.next;
                continue;
            }
            prevNode = head;
        }
        return resultList;
        */

        if (head == null) {
            return null;
        }
        ListNode prevNode = head;
        ListNode currentNode = head.next;
        while (currentNode != null) {
            if (prevNode.val == currentNode.val) {
                currentNode = currentNode.next;
                prevNode.next = currentNode;
                continue;
            }
            prevNode = currentNode;
            currentNode = currentNode.next;
        }
        return head;
    }
}
