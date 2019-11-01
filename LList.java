package Lab5;

/*************************************************************************
 * This class is the blueprint of any singly linked list. 
 * It describes what a generic linkedlist is (its attributes) and 
 * how to operate it (its methods).
 * YOU CANNOT MODIFY THIS FILE
 *************************************************************************/ 

public class LList<T> {
    private Node<T> head;
    private int size;
    
    // CONSTRUCTORS ******************************************************
    public LList() {}
    
    public LList(Node<T> head) {
		super();
		this.head = head;
		size = head.sizeFromNode();
	}

	// SETTERS ***********************************************************
	/**
	 * @param head the head to set
	 */
	public void setHead(Node<T> head) {
		this.head = head;
        size = head.sizeFromNode();
	}

    // GETTERS ***********************************************************
    /**
	 * @return the head
	 */
	public Node<T> getHead() {
		return head;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}    
    
    // OTHER METHODS *****************************************************
    
	// Add single node at head:
	public void addStart(Node<T> N) {
		N.setNext(head);
		head = N;
		size++;
	}
	
    // PRINTING THE CONTENT OF A LIST //////////////////////////////////
    /* Method print: 
     *      Prints every element of the circle once
     *      Prints "There is nothing to print" if the list is empty
     ********************************************************************/
    public void print() {
    	Node<T> temp = head;
    	while (temp != null) {
    		temp.printNode();
    		temp = temp.getNext();
    	}
    }
    
}