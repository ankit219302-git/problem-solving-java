package problems.lc.easy;

import ds.ListNode;
import util.LinkedListUtil;

/*
You are given the heads of two sorted linked lists list1 and list2.

Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two lists.

Return the head of the merged linked list.


Example 1:

Input: list1 = [1,2,4], list2 = [1,3,4]
Output: [1,1,2,3,4,4]
Example 2:

Input: list1 = [], list2 = []
Output: []
Example 3:

Input: list1 = [], list2 = [0]
Output: [0]
*/

public class MergeTwoSortedLists {
    public static void main(String[] args) {
        MergeTwoSortedLists obj = new MergeTwoSortedLists();
        ListNode list1 = LinkedListUtil.createList(new int[]{-1,0,3,4,5,6});
        ListNode list2 = LinkedListUtil.createList(new int[]{0,4,7});
        ListNode resultList = obj.mergeTwoLists(list1, list2);

        System.out.print("Merged and sorted list: ");
        while (resultList != null) {
            System.out.print(resultList.val);
            resultList = resultList.next;
            if (resultList != null) {
                System.out.print(", ");
            }
        }
    }
    private ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        /*
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        ListNode tempList;
        if (list1.val <= list2.val) {
            tempList = list1;
            list1 = list1.next;
        } else {
            tempList = list2;
            list2 = list2.next;
        }
        ListNode mergedList = tempList;
        while (list1 != null) {
            if (list2 == null) {
                tempList.next = list1;
                list1 = list1.next;
                tempList = tempList.next;
                continue;
            }
            if (list1.val <= list2.val) {
                tempList.next = list1;
                list1 = list1.next;
            } else {
                tempList.next = list2;
                list2 = list2.next;
            }
            tempList = tempList.next;
        }
        while (list2 != null) {
            tempList.next = list2;
            list2 = list2.next;
            tempList = tempList.next;
        }
        return mergedList;
        */

        /* #### Optimised the above approach ####
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        ListNode tempList;
        if (list1.val <= list2.val) {
            tempList = list1;
            list1 = list1.next;
        } else {
            tempList = list2;
            list2 = list2.next;
        }
        ListNode mergedList = tempList;
        while (list1 != null) {
            if (list2 == null) {
                tempList.next = list1;
                break;
            }
            if (list1.val <= list2.val) {
                tempList.next = list1;
                list1 = list1.next;
            } else {
                tempList.next = list2;
                list2 = list2.next;
            }
            tempList = tempList.next;
        }
        if (list2 != null) {
            tempList.next = list2;
        }
        return mergedList;
        */

        ListNode resultList;
        ListNode prevNode;
        ListNode currentNode;
        if (list1 == null && list2 == null) {
            return null;
        }
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        if (list1.val < list2.val) {
            prevNode = list1;
            list1 = list1.next;
        } else {
            prevNode = list2;
            list2 = list2.next;
        }
        resultList = prevNode;
        while (list1 != null || list2 != null) {
            if (list1 == null) {
                currentNode = new ListNode(list2.val, null);
                prevNode.next = currentNode;
                prevNode = currentNode;
                list2 = list2.next;
                continue;
            }
            if (list2 == null) {
                currentNode = new ListNode(list1.val, null);
                prevNode.next = currentNode;
                prevNode = currentNode;
                list1 = list1.next;
                continue;
            }
            if (list1.val < list2.val) {
                currentNode = new ListNode(list1.val, null);
                list1 = list1.next;
            } else {
                currentNode = new ListNode(list2.val, null);
                list2 = list2.next;
            }
            prevNode.next = currentNode;
            prevNode = currentNode;
        }
        return resultList;
    }
}
