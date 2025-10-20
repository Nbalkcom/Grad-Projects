/**
 * Class used to replicate a Character holding a data field for the name of the character, two different numeric data types that are comparable, and a method for a descriptive message
 * 
 * Implementation of the SortedPriorityQueue class(added messages to record each step in the insert method) and LinkedBinaryTree class along with the needed classes for them to function
 * 
 * And demonstration that creates an ordered binary tree data structure to hold at least five Character objects, uses the inorder traversal methods, 
 * and processes them into two Sorted Priority Queues with different keys
 * 
 * @author Nick Balkcom, ITEC 6400-01*/

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.lang.Comparable;

/**Class used to make a Character object that holds a name and amount of HP and mana*/
class Character{
	//Default data types
	private String name;
	private int hp = 0;
	private int mana = 0;
	/**
	 * Constructor for Character object
	 * @param n		Holds String of the Character's name
	 * @param h		Holds Integer of the Character's HP
	 * @param m		Holds Integer of the Character's mana
	 */
	public Character(String n,int h,int m) {
		name = n;
		hp = h;
		mana = m;
	}
	/**Getter of Character object's name*/
	public String getName() {
		return name;
	}
	/**Getter of Character object's HP*/
	public int getHP() {
		return hp;
	}
	/**Getter of Character object's mana*/
	public int getMana() {
		return mana;
	}
	/**Used to display Character's information*/
	public void info() {
		System.out.println(name + " has " + hp + " HP and " + mana + " mana.");
	}
}

//Beginning of textbook code needed typed by hand unfortunately								\/Should make it easier to find classes\/
																								//Position interface
interface Position<E> {
	E getElement() throws IllegalStateException;
}
																								//PositionalList interface
interface PositionalList<E> {
	int size();
	boolean isEmpty();
	
	Position<E> first();
	Position<E> last();
	Position<E> before(Position<E> p) throws IllegalArgumentException;
	Position<E> after(Position<E> p) throws IllegalArgumentException;
	Position<E> addFirst(E e);
	Position<E> addLast(E e);
	Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException;
	Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException;
	E set(Position<E> p, E e) throws IllegalArgumentException;
	E remove(Position<E> p) throws IllegalArgumentException;
}
																								//LinkedPositionalList class
class LinkedPositionalList<E> implements PositionalList<E> {
	private static class Node<E> implements Position<E> {
		private E element;
		private Node<E> prev;
		private Node<E> next;
		public Node(E e, Node<E> p, Node<E> n) {
			element = e;
			prev = p;
			next = n;
		}
		public E getElement() throws IllegalStateException {
			if(next == null)
				throw new IllegalStateException("Position no longer valid");
			return element;
		}
		public Node<E> getPrev() {
			return prev;
		}
		public Node<E> getNext() {
			return next;
		}
		public void setElement(E e) {
			element = e;
		}
		public void setPrev(Node<E> p) {
			prev = p;
		}
		public void setNext(Node<E> n) {
			next = n;
		}
	}
	
	private Node<E> header;
	private Node<E> trailer;
	private int size = 0;
	
	public LinkedPositionalList() {
		header = new Node<>(null,null,null);
		trailer = new Node<>(null,header,null);
		header.setNext(trailer);
	}
	
	private Node<E> validate(Position<E> p) throws IllegalArgumentException {
		if(!(p instanceof Node)) throw new IllegalArgumentException("Invalid p");
		Node<E> node = (Node<E>)p;
		if(node.getNext() == null)
			throw new IllegalArgumentException("p is no longer in the list");
		return node;
	}
	private Position<E> position(Node<E> node) {
		if(node == header || node == trailer) 
			return null;
		return node;
	}
	public int size() {return size;}
	public boolean isEmpty() {return size == 0;}
	public Position<E> first() {
		return position(header.getNext());
	}
	public Position<E> last() {
		return position(trailer.getPrev());
	}
	public Position<E> before(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		System.out.println("Searching..");		//Message used when searching through each element in the SortedPriorityQueue insert method
		return position(node.getPrev());
	}
	public Position<E> after(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return position(node.getNext());
	}
	private Position<E> addBetween(E e, Node<E> pred, Node<E> succ) {
		Node<E> newest = new Node<>(e,pred,succ);
		pred.setNext(newest);
		succ.setPrev(newest);
		size++;
		return newest;
	}
	public Position<E> addFirst(E e){
		return addBetween(e, header, header.getNext());
	}
	public Position<E> addLast(E e){
		return addBetween(e, trailer.getPrev(), trailer);
	}
	public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return addBetween(e, node.getPrev(), trailer);
	}
	public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return addBetween(e, node, node.getNext());
	}
	public E set(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> node = validate(p);
		E answer = node.getElement();
		node.setElement(e);
		return answer;
	}
	public E remove(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		Node<E> predecessor = node.getPrev();
		Node<E> successor = node.getNext();
		predecessor.setNext(successor);
		successor.setPrev(predecessor);
		size--;
		E answer = node.getElement();
		node.setElement(null);
		node.setNext(null);
		node.setPrev(null);
		return answer;
	}
}
																								//Entry interface
interface Entry<K,V> {
	K getKey();
	V getValue();
}
																								//PriorityQueue interface
interface PriorityQueue<K,V> {
	int size();
	boolean isEmpty();
	Entry<K,V> insert(K key,V value) throws IllegalArgumentException;
	Entry<K,V> min();
	Entry<K,V> removeMin();
}
																								//DefaultComparator class
class DefaultComparator<E> implements Comparator<E> {
	public int compare(E a, E b) throws ClassCastException {
		return ((Comparable<E>) a).compareTo(b);
	}
}
																								//AbstractPriorityQueue abstract class
abstract class AbstractPriorityQueue<K,V> implements PriorityQueue<K,V> {
	protected static class PQEntry<K,V> implements Entry<K,V> {
		private K k;
		private V v;
		public PQEntry(K key, V value) {
			k = key;
			v = value;
		}
		
		public K getKey() {return k;}
		public V getValue() {return v;}
		
		protected void setKey(K key) {k = key;}
		protected void setValue(V value) {v = value;}
	}
	
	private Comparator<K> comp;
	protected AbstractPriorityQueue(Comparator<K> c) {comp = c;}
	protected AbstractPriorityQueue() {this(new DefaultComparator<K>());}
	protected int compare(Entry<K,V> a, Entry<K,V> b) {
		return comp.compare(a.getKey(), b.getKey());
	}
	protected boolean checkKey(K key) throws IllegalArgumentException {
		try {
			return (comp.compare(key,key) == 0);
		}catch (ClassCastException e) {
			throw new IllegalArgumentException("Incompatible key");
		}
	}
	public boolean isEmpty() {return size() == 0;}
}
																								//SortedPriorityQueue class
class SortedPriorityQueue<K,V> extends AbstractPriorityQueue<K,V>{
	private PositionalList<Entry<K,V>> list = new LinkedPositionalList<>();
	
	public SortedPriorityQueue() {super();}
	public SortedPriorityQueue(Comparator<K> comp) {super(comp);}
	
	public Entry<K,V> insert(K key, V value) throws IllegalArgumentException {
		checkKey(key);
		Entry<K,V> newest = new PQEntry<>(key, value);
		Position<Entry<K,V>> walk = list.last();
		System.out.println("Finding new player a spot");	//Message to show each step of the search to add a new element

		while(walk != null && compare(newest,walk.getElement()) < 0)
			walk = list.before(walk);
		if(walk == null) {
			list.addFirst(newest);
			System.out.println("Spot found!");			//Message when the search ends and an element is added
		}
		else {
			list.addAfter(walk,newest);
			System.out.println("Spot found!");			//Message when the search ends and an element is added
		}
		return newest;
	}
	public Entry<K,V> min(){
		if(list.isEmpty()) return null;
		return list.first().getElement();
	}
	public Entry<K,V> removeMin(){
		if(list.isEmpty()) return null;
		return list.remove(list.first());
	}
	public int size() {return list.size();}
}
																								//Tree interface
interface Tree<E> extends Iterable<E>{
	Position<E> root();
	Position<E> parent(Position<E> p) throws IllegalArgumentException;
	Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException;
	int numChildren(Position<E> p) throws IllegalArgumentException;
	boolean isInternal(Position<E> p) throws IllegalArgumentException;
	boolean isExternal(Position<E> p) throws IllegalArgumentException;
	boolean isRoot(Position<E> p) throws IllegalArgumentException;
	int size();
	boolean isEmpty();
	Iterator<E> iterator();
	Iterable<Position<E>> positions();
}
																								//AbstractTree abstract class
abstract class AbstractTree<E> implements Tree<E>{
	public boolean isInternal(Position<E> p) {return numChildren(p) > 0;}
	public boolean isExternal(Position<E> p) {return numChildren(p) == 0;}
	public boolean isRoot(Position<E> p) {return p == root();}
	public boolean isEmpty() {return size() == 0;}
	public int depth(Position<E> p) {
		if(isRoot(p))
			return 0;
		else
			return 1 + depth(parent(p));
	}
	public int heightBad() {
		int h = 0;
		for(Position<E> p : positions())
			if(isExternal(p))
				h = Math.max(h, depth(p));
		return h;
	}
	public int height(Position<E> p) {
		int h = 0;
		for(Position<E> c : children(p))
			h = Math.max(h, 1 + height(c));
		return h;
	}
}
																								//BinaryTree interface
interface BinaryTree<E> extends Tree<E>{
	Position<E> left(Position<E> p) throws IllegalArgumentException;
	Position<E> right(Position<E> p) throws IllegalArgumentException;
	Position<E> sibling(Position<E> p) throws IllegalArgumentException;
}
																								//AbstractBinaryTree abstract class
abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTree<E>{
	public Position<E> sibling(Position<E> p){
		Position<E> parent = parent(p);
		if(parent == null) return null;
		if(p == left(parent))
			return right(parent);
		else
			return left(parent);
	}
	public int numChildren(Position<E> p) {
		int count = 0;
		if(left(p) != null)
			count++;
		if(right(p) != null)
			count++;
		return count;
	}
	public Iterable<Position<E>> children(Position<E> p){
		List<Position<E>> snapshot = new ArrayList<>(2);
		if(left(p) != null)
			snapshot.add(left(p));
		if(right(p) != null)
			snapshot.add(left(p));
		return snapshot;
	}
}
																								//LinkedBinaryTree class
class LinkedBinaryTree<E> extends AbstractBinaryTree<E>{
	protected static class Node<E> implements Position<E>{
		private E element;
		private Node<E> parent;
		private Node<E> left;
		private Node<E> right;
		public Node(E e,Node<E> above, Node<E> leftChild, Node<E> rightChild) {
			element = e;
			parent = above;
			left = leftChild;
			right = rightChild;
		}
		public E getElement() {return element;}
		public Node<E> getParent(){return parent;}
		public Node<E> getLeft(){return left;}
		public Node<E> getRight(){return right;}
		public void setElement(E e) {element = e;}
		public void setParent(Node<E> parentNode) {parent = parentNode;}
		public void setLeft(Node<E> leftChild) {left = leftChild;}
		public void setRight(Node<E> rightChild) {right = rightChild;}
		}
	protected Node<E> createNode(E e, Node<E> parent,Node<E> left,Node<E> right){
		return new Node<E>(e,parent,left,right);
	}
	
	protected Node<E> root = null;
	private int size = 0;
	public LinkedBinaryTree() {}
	protected Node<E> validate(Position<E> p) throws IllegalArgumentException{
		if(!(p instanceof Node))
			throw new IllegalArgumentException("Not valid position type");
		Node<E> node = (Node<E>)p;
		if(node.getParent() == node)
			throw new IllegalArgumentException("p is no longer in the tree");
		return node;
	}
	public int size() {
		return size;
	}
	public Position<E> root(){
		return root;
	}
	public Position<E> parent(Position<E> p) throws IllegalArgumentException{
		Node<E> node = validate(p);
		return node.getParent();
	}
	public Position<E> left(Position<E> p) throws IllegalArgumentException{
		Node<E> node = validate(p);
		return node.getLeft();
	}
	public Position<E> right(Position<E> p) throws IllegalArgumentException{
		Node<E> node = validate(p);
		return node.getRight();
	}
	public Position<E> addRoot(E e) throws IllegalArgumentException{
		if(!isEmpty()) throw new IllegalArgumentException("Tree is not empty");
		root = createNode(e,null,null,null);
		size = 1;
		return root;
	}
	public Position<E> addLeft(Position<E> p,E e) throws IllegalArgumentException{
		Node<E> parent = validate(p);
		if(parent.getLeft() != null)
			throw new IllegalArgumentException("p already has a left child");
		Node<E> child = createNode(e,parent,null,null);
		parent.setLeft(child);
		size++;
		return child;
	}
	public Position<E> addRight(Position<E> p,E e) throws IllegalArgumentException{
		Node<E> parent = validate(p);
		if(parent.getRight() != null)
			throw new IllegalArgumentException("p already has a right child");
		Node<E> child = createNode(e,parent,null,null);
		parent.setRight(child);
		size++;
		return child;
	}
	public E set(Position<E> p, E e) throws IllegalArgumentException{
		Node<E> node = validate(p);
		E temp = node.getElement();
		node.setElement(e);
		return temp;
	}
	public void attach(Position<E> p, LinkedBinaryTree<E> t1,LinkedBinaryTree<E> t2) throws IllegalArgumentException{
		Node<E> node = validate(p);
		if (isInternal(p)) throw new IllegalArgumentException("p must be a leaf");
		size += t1.size() + t2.size();
		if(!t1.isEmpty()) {
			t1.root.setParent(node);
			node.setLeft(t1.root);
			t1.root = null;
			t1.size = 0;
		}
		if(!t2.isEmpty()) {
			t2.root.setParent(node);
			node.setRight(t2.root);
			t2.root = null;
			t2.size = 0;
		}
	}
	public E remove(Position<E> p) throws IllegalArgumentException{
		Node<E> node = validate(p);
		if(numChildren(p) == 2)
			throw new IllegalArgumentException("p has two children");
		Node<E> child = (node.getLeft() != null ? node.getLeft() : node.getRight());
		if(child != null)
			child.setParent(node.getParent());
		if(node == root)
			root = child;
		else {
			Node<E> parent = node.getParent();
			if(node == parent.getLeft())
				parent.setLeft(child);
			else
				parent.setRight(child);
		}
		size--;
		E temp = node.getElement();
		node.setElement(null);
		node.setLeft(null);
		node.setRight(null);
		node.setParent(node);
		return temp;
	}
	private class ElementIterator implements Iterator<E>{
		Iterator<Position<E>> posIterator = positions().iterator();
		public boolean hasNext() {return posIterator.hasNext();}
		public E next() {return posIterator.next().getElement();}
		public void remove() {posIterator.remove();}
	}
	public Iterator<E> iterator(){return new ElementIterator();}
	//Inorder traversal used for this tree
	private void inorderSubtree(Position<E> p, List<Position<E>> snapshot) {
		if(left(p) != null)
			inorderSubtree(left(p), snapshot);
		snapshot.add(p);
		if(right(p) != null)
			inorderSubtree(right(p), snapshot);
	}
	public Iterable<Position<E>> inorder(){
		List<Position<E>> snapshot = new ArrayList<>();
		if(!isEmpty())
			inorderSubtree(root(), snapshot);
		return snapshot;
	}
	public Iterable<Position<E>> positions(){
		return inorder();
	}
}
//End of textbook code

//Demonstration
public class BalkcomNA5 {
	public static void main(String[] args) {
		Character first = new Character("Aaron",10,16);		//first character object 
		Character second = new Character("Adam",8,10);		//second character object
		Character third = new Character("Beatrice",16,20);	//third character object
		Character fourth = new Character("Carol",20,8);		//fourth character object
		Character fifth = new Character("Darren",12,12);	//fifth character object
		LinkedBinaryTree<Character> cLBT = new LinkedBinaryTree<>();	//Create empty Linked Binary Tree
		//System.out.println("Adding root");
		cLBT.addRoot(fourth);							//Sets fourth character object as root
		//System.out.println("Adding root's right child");
		cLBT.addRight(cLBT.root(), fifth);				//Sets fifth character object as right child of root
		//System.out.println("Adding root's left child");
		cLBT.addLeft(cLBT.root(), second);				//Sets second character object as left child of root
		//System.out.println("Adding left parent's left child");
		cLBT.addLeft(cLBT.left(cLBT.root()), first);	//Sets first character object as left child of the previously added left child of root
		//System.out.println("Adding right parent's right child");
		cLBT.addRight(cLBT.left(cLBT.root()), third);	//Sets third character object as right child of the previously added left child of root
		SortedPriorityQueue<Integer,Character> spq1 = new SortedPriorityQueue<>();	//Creates first Sorted Priority Queue taking integer as key and character object as value
		for(Position<Character> p: cLBT.inorder())					//for each element formed by the list of the inorder traversal method
			spq1.insert(p.getElement().getHP(), p.getElement());	//Insert in the Sorted Priority Queue as (Character object's HP, Character object)
		int i = 0;
		while(i <= spq1.size()+3) {				//For some reason, it stopped recording the size after 2, but for each element in the Sorted Priority Queue
			spq1.removeMin().getValue().info();	//Removes and returns the Character object to call out the Character's info method, by minimum HP, from Sorted Priority Queue
			i++;
		}
		SortedPriorityQueue<Integer,Character> spq2 = new SortedPriorityQueue<>();	//Creates second Sorted Priority Queue taking integer as key and character object as value
		for(Position<Character> p: cLBT.inorder())					//for each element formed by the list of the inorder traversal method
			spq2.insert(p.getElement().getMana(), p.getElement());	//Insert in the Sorted Priority Queue as (Character object's mana, Character object)
		int x = 0;
		while(x <= spq2.size()+3) {				//Same here
			spq2.removeMin().getValue().info();	//Removes and returns the Character object to call out the Character's info method, by minimum mana, from Sorted Priority Queue
			x++;
		}
	}
}
