package util;

import ds.ListNode;

public class LinkedListUtil {
    public static ListNode createList(int[] nodeValues) {
        ListNode list = null;
        ListNode currentNode = null;
        for (int nodeValue : nodeValues) {
            if (list == null) {
                list = new ListNode(nodeValue);
                list.next = null;
                currentNode = list;
                continue;
            }
            ListNode tempNode = new ListNode(nodeValue, null);
            currentNode.next = tempNode;
            currentNode = tempNode;
        }
        return list;
    }
}
