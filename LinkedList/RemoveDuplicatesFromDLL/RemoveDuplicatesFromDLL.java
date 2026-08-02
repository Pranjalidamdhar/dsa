/* Structure of a link list node
class Node {
	int data; // value stored in node
	Node next;
	Node prev;
	
	Node(int value) {
		data = value;
		next = null;
		prev = null;
	}
}
*/
class Solution {
	Node removeDuplicates(Node headRef) {
		// code here
		Node temp1 = headRef;
		Node temp2 = temp1.next;
		while (temp1 != null) {
			while (temp2 != null&&temp1.data == temp2.data) {
					temp2 = temp2.next;
				}
				temp1.next = temp2;
				 if(temp2!=null){
					temp2.prev = temp1;
				}
				temp1 = temp2;
				
		}
		return headRef;
		
	}
}
