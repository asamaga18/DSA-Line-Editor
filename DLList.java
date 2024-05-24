public class DLList {
  private Node head;
  // private LList next;

  static class Node {
    private Node next;
    private Node prev;
    private Object data;

    public Node(Object dataValue) {
      next = null;
      prev = null;
      data = dataValue;
    }
  }

  public DLList() {
    head = null;
  }
  
  public Node getNext(Node n) { return n.next; }

  public Node getPrev(Node n) { return n.prev; }

  public Object getData(Node n) { return n.data; }

/*
  I'm leaving these here, but I don't think we'll need them right now.

  public void setNext(Node nextValue) { 
    next = nextValue;
  }

  public void setPrev(Node prevValue) {
    prev = prevValue;
  }

  public void setData(Object info) {
    data = info;
  }
*/

  // Inserts a node at the head with 'info' as the value.
  public void insertAtHead(Object info) {
   Node temp = new Node(info);
   temp.next = head;
   if(head != null)
    head.prev = temp;
   head = temp;
 }


  // Inserts a node at the tail with 'info' as the value.
  public void insertAtTail(Object info) {
   Node temp = new Node(info);
 
   if (head == null) {
     head = temp;
   } else {
     Node iterator = head;
     while (iterator.next != null)
     iterator = iterator.next;
     iterator.next = temp;
     temp.prev = iterator;
   }
 }


  // Inserts an item immediately after the one labelled 'before'
  public void insertAfter(Node before, Object info) 
	{
	  Node temp = new Node(info);
    temp.prev = before;
    temp.next = before.next;
    before.next = temp;
    temp.next.prev = temp;
      
  }

  // Deletes node with 'info' as its data value, and returns true if successful, and returns false if node is not found
  public boolean deleteNode(Object info) {
   Node temp = find(info);
 
   if (temp == null)
     return false;
 
   Node iterator = head;

    if (head == temp)
    {
      head = head.next;
      head.prev = null;
      return true;
      
    }
   while (iterator != null && iterator.next != temp)
     iterator = iterator.next;
 
   iterator.next = temp.next;
   iterator.next.prev = iterator;

   return true;
 }


  // Searches for node with 'target' as its data value, and returns a pointer to that node, or null if no such node exists.
  public Node find(Object target) 
	{
		 Node temp = head;
    while (temp != null && temp.data != target)
		{
      //if(temp.data == target)
			//	return temp;
			temp = temp.next;
    }
    return temp;
  }

  // Returns a pointer to the first node.
  public Node getFirst() {
    return head;
  }

  // Returns a pointer to the last node. We won't need this just yet, but we might eventually.
  public Node getLast() 
	{
  	Node iterator = head; 
		while (iterator.next != null)
     	iterator = iterator.next;
  	return iterator;
	}
}