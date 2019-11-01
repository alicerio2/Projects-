package Lab5;

/*************************************************************************
 * This class is the blueprint of any single (forward) link node. 
 * It describes what a generic node is (its attributes) and 
 * how to operate it (its methods).
 * YOU CANNOT MODIFY THIS FILE
 *************************************************************************/ 

public class Node<T> {

    private T data;
    private Node<T> next;
    
    // Constructors ****************************************************************
    public Node() {}
    
    public Node(T d) {
        data = d;
        next = null;
    }
    
    // Setters *********************************************************************
    public void setData(T d) {
        data = d;   
    }
    
    public void setNext(Node<T> N) {
        next = N;
    }
    
    // Getters **********************************************************************
    public T getData() {
        return data;   
    }
    
    public Node<T> getNext() {
        return next;   
    }
    
    // Other methods ***************************************************************
    /* Size from node computes the length of the string of nodes starting at the 
     * current node
     */
    public int sizeFromNode() {
        Node<T> iter = this;
        int size = 0;
        while (iter != null) {
            size++;
            iter = iter.getNext();
        }
        return size;
    }
    
    /* printNode prints the content of the data of this node
     */
    public void printNode() {
        System.out.println(data.toString());   
    }
}