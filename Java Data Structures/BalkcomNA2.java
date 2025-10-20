/**
 * Implements a model of a Pro Gamers facing each other in teams
 * 
 * @author Nick Balkcom, ITEC 6400-01
 */

/**Class used to replicate a Circularly Linked List
 * @author Textbook Author*/
class CircularlyLinkedList<E> {

	//(nested node class identical to that of the SinglyLinkedList class)


	//---------------- nested Node class ----------------
	private static class Node<E> {
		private E element;                     // reference to the element stored at this node
		private Node<E> next;                  // reference to the subsequent node in the list
		public Node(E e, Node<E> n) {
			element = e;
			next = n;
		}
		public E getElement( ) { return element; }
		public Node<E> getNext( ) { return next; }
		public void setNext(Node<E> n) { next = n; }
	} //----------- end of nested Node class -----------

	// instance variables of the CircularlyLinkedList
	private Node<E> tail = null;                  // we store tail (but not head)
	private int size = 0;                         // number of nodes in the list
	public CircularlyLinkedList( ) { }            // constructs an initially empty list

	// access methods
	public int size( ) { return size; }
	public boolean isEmpty( ) { return size == 0; }
	public E first( ) {                 // returns (but does not remove) the first element
		if (isEmpty( )) return null;
		return tail.getNext( ).getElement( );       // the head is *after* the tail
    }

	public E last( ) {                  // returns (but does not remove) the last element
		if (isEmpty( )) return null;
		return tail.getElement( );
	}

	//NOTE rotation relies on a simple update
	// of a reference variable value. The tail will refer to 
	// the node which formerly was the next node of the tail

	// update methods
	public void rotate( ) {             // rotate the first element to the back of the list
		if (tail != null)                           // if empty, do nothing
			tail = tail.getNext( );                  // the old head becomes the new tail
    }

	public void addFirst(E e) {                   // adds element e to the front of the list
		if (size == 0) {
			tail = new Node<>(e, null);
			tail.setNext(tail);                      // link to itself circularly
		} else {

        //NOTE a node is created which holds the new data
        //element, and whose next link refers to the node originally after the tail
        //The node at the tail reference will link to the new node
			Node<E> newest = new Node<>(e, tail.getNext( ));
			tail.setNext(newest);
		}

		size++;
	}

	//NOTE why use add first? If the new node is inserted 
	// between the tail and its successor, we can then move 
	// tail to next and cause the newest inserted node to be the tail
	public void addLast(E e) {                    // adds element e to the end of the list
		addFirst(e);                                // insert new element at front of list
		tail = tail.getNext( );                     // now new element becomes the tail
    }

	public E removeFirst( ) {                     // removes and returns the first element
		if (isEmpty( )) return null;                // nothing to remove
		Node<E> head = tail.getNext( );
		if (head == tail) tail = null;              // must be the only node left
		else tail.setNext(head.getNext( ));         // removes "head" from the list
		size--;
		return head.getElement( );
	}
}

/**Abstract class used to demonstrate a player in a game*/
abstract class Gamer {
	abstract void scoring(gameEntry g);
}

/**Class to represent an entry of information about a game-related event
 * @author Your Hurried Instructor, and the textbook author*/
class gameEntry {
	/**@param name 		Private data field to hold a String of a name */
	private String name;	
	/**@param score		Private data field to hold score*/
	private int score;		

	/**Constructor to create a Game Entry object*/
	public gameEntry(String n, int s){
		name = n;
		score=s;
	}
	/**Getter method to return the name*/
	public String getName(){
		return name;
	}
	/**Getter method to return the score*/
	public int getScore(){
		return score;
	}
	/**Method used to add to score by an amount
	 * @param i 		Used as additive for score
	 * @author Nick Balkcom*/
	public int newScore(int i) {
		score = score + i;
		return score;
	}
}

/**Class used as a subclass to the Gamer class*/
class proGamer extends Gamer{
	/**@param cLL 	Private data field to hold a Circular Linked List of gameEntry class objects*/
	private CircularlyLinkedList<gameEntry> cLL = new CircularlyLinkedList();
	/**@param name	Private data field to hold proGamer object's name*/
	private String name;
	/**Constructor to create proGamer object*/
	public proGamer(String n) {
		name = n;
	}
	/**Overridden method used to add new gameEntry objects into the Circularly Linked List and display information
	 * @param g		Used to gain access to the proper gameEntry object*/
	@Override
	void scoring(gameEntry g) {
		cLL.addFirst(g);
		System.out.println(name + " has scored for " + g.getName() + "! " + g.getName() + " Current points: " + g.newScore(100));
	}
}
/**Demonstration class*/
public class BalkcomNA2{
	public static void main(String[] args){
		proGamer G = new proGamer("George");
		proGamer N = new proGamer("Natalie");
		proGamer S = new proGamer("Steven");
		proGamer J = new proGamer("Jackson");
		
		CircularlyLinkedList<proGamer> team1 = new CircularlyLinkedList();
		CircularlyLinkedList<proGamer> team2 = new CircularlyLinkedList();
		gameEntry Team1 = new gameEntry("Team Mischevious Lions",0);
		gameEntry Team2 = new gameEntry("Team Bleach Bottles", 0);
		
		team1.addFirst(G);
		team2.addFirst(N);
		team1.addLast(S);
		team2.addLast(J);
		
		for(int i = 1; i <= 5; i++) {
			team1.first().scoring(Team1);
			team2.first().scoring(Team2);
			team1.rotate();
			team2.rotate();
			System.out.println("End of Round " + i);
		}
	}
}