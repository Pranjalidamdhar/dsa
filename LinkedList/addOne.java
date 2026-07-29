// Optimal Solution for addOne in linked list
//Difficulty: Medium

/* Structure of linked list Node
class Node{
    int data;
    Node next;

    Node(int x){
        data = x;
        next = null;
    }
}
*/
class Solution {
    public int helper(Node temp){
        if(temp == null){
            return 1;
        }
        int carry = helper(temp.next);
        temp.data = temp.data+carry;
        if(temp.data<10){
            return 0;
        }
        temp.data = 0;
        return 1;
        
    }
    public Node addOne(Node head) {
        int carry = helper(head);
        if(carry == 1){
            Node NewNode = new Node(1);
            NewNode.next = head;
            return NewNode;
        }
        return head;
        
    }
}
