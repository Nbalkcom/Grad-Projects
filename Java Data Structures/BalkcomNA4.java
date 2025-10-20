/**
 * Implements a method which opens two plain English text files whose filenames are passed in the 
 * Java program’s first two command line parameters, and stores the contents of each file in a list of String objects
 * 
 * Implements a method that processes two String lists, finding all posts in which a single search word occurs in the 
 * textual part of a post, in both lists. Adds each found post to a stack data structure. 
 * Also print out the contents of each target post as it is found.
 * 
 * Implements a modified version of the text chapter’s dynamic array list ArrayList class type
 * and explains in the method comments the complexity of the resize operation
 * 
 * @author Nick Balkcom, ITEC 6400-01
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Stack;

/**Class used to replicate a Dynamic Array List from the textbook with changes to the "add" and "resize" methods with comments
 * @author Textbook Author, Nick Balkcom*/
class DynArrayList<E> implements List<E>{

	  // instance variables
	  public static final int CAPACITY=16;         // default array capacity
	  private E[ ] data;                           // generic array used for storage
	  private int size = 0;                        // current number of elements
	  private int copy = 0;						//records number of copies in the resizing method
	  // constructors
	  public DynArrayList() { this(CAPACITY); } // constructs list with default capacity
	  public DynArrayList(int capacity) {             // constructs list with given capacity
	     data = (E[]) new Object[capacity];       // safe cast; compiler may give warning
	     }
	  // public methods

	   //Resize method to dynamically add capacity to the list. The capacity
	   // of the new list is passed in by the parameter 
	   /** Resizes internal array to have given capacity >= size. */
	   protected void resize(int capacity) {
	     E[ ] temp = (E[ ]) new Object[capacity]; // safe cast; compiler may give warning
	     
	     for (int k=0; k < size; k++)
	       temp[k] = data[k];

	      data = temp;                                  // start using the new array
	      //Changes to the resize method
	      copy++;										// adds to the number of copies that happened in the resize method
	      System.out.println("The Dynamic Array has been resized " + copy + " time(s).");//Prints how many times the array has been copied
	      //This complexity added to the resize method would be O(n) since the number of times the message would be printed
	      //would be dependent on the number times the array would be copied
	     }

	   /** Returns the number of elements in the array list. */
	   public int size( ) { return size; }

	   /** Returns whether the array list is empty. */
	   public boolean isEmpty( ) { return size == 0; }

	   /** Returns (but does not remove) the element at index i. */
	   public E get(int i) throws IndexOutOfBoundsException {
	     checkIndex(i, size);
	     return data[i];
	   }

	   /** Replaces the element at index i with e, and returns the replaced element. */
	   public E set(int i, E e) throws IndexOutOfBoundsException {
	     checkIndex(i, size);
	     E temp = data[i];
	     data[i] = e;
	     return temp;
	   }

	   /** Inserts element e to be at index i, shifting all subsequent elements later. */
	   public void add(int i, E e) throws IndexOutOfBoundsException, IllegalStateException {
	     checkIndex(i, size + 1);
	     //Changes to the add method
	     if (size == data.length){                    
	    	 if(size == 1) {							// if the size of the array is equal to 1
	    		 resize(4 * data.length);				// then it should quadruple the size of the array
	    	 }
	    	 else {
	    		 resize(data.length + 4);				// otherwise, it should add 4 to the size of the array
	    	 }

	     }

	     //This loop makes the add(i,e) operation O(n): it 
	     // potentially must shift n-1 elements to the 'right'
	     for (int k=size-1; k >= i; k--)             // start by shifting rightmost
	        data[k+1] = data[k];

	     data[i] = e;                                // ready to place the new element
	     size++;
	   }

	   /** Removes/returns the element at index i, shifting subsequent elements earlier. */
	   public E remove(int i) throws IndexOutOfBoundsException {
	     checkIndex(i, size);
	     E temp = data[i];
	     for (int k=i; k < size-1; k++)              // shift elements to fill hole
	        data[k] = data[k+1];
	     data[size-1] = null;                        // help garbage collection
	     size--;
	     return temp;
	   }

	   /** Checks whether the given index is in the range [0, n-1]. */
	   protected void checkIndex(int i, int n) throws IndexOutOfBoundsException {
	     if (i < 0 || i >= n)
	        throw new IndexOutOfBoundsException("Illegal index: " + i);
	   }
	   
	//Abstract methods from List
	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean add(E e) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean addAll(Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public ListIterator<E> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ListIterator<E> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}
}

public class BalkcomNA4 {
	/**
	 * Method for converting a file into a list
	 * @param file	String that contains the pathname of the text file
	 */
	public static ArrayList<String> fromFileToList(String file){
		ArrayList<String> fileList = new ArrayList<>();//Makes a new ArrayList
		BufferedReader read;//Creates a reader
		int i = 0;//Stores the index for the ArrayList
		
		try {
			read = new BufferedReader(new FileReader(file));//Reads the file passed as a parameter
			String line = read.readLine();//Stores the line of text from the file
			while(line != null) {
				fileList.add(i,line);//Adds line of text to the ArrayList
				i++;//Increases index for the ArrayList
			}
		}
		catch (FileNotFoundException e) {
			System.out.println("File Name Error!");//Prints file name exception error message
		}
		catch (IOException e) {
			System.out.println("IO Error!");//Prints input/output exception error message
		}
		
		return fileList;//Returns ArrayList
	}
	/**
	 * Method for converting two ArrayLists into a Stack data structure, the Stack contains lines of text that contain
	 * a certain word
	 * @param oneL		First ArrayList that contains the lines of text from a text file
	 * @param twoL		Second ArrayList that contains the lines of text from another text file
	 * @param word		The word that's used to check if the lines of text contain this word
	 */
	public static Stack<String> checkList(ArrayList<String> oneL, ArrayList<String> twoL, String word) {
		int size1 = oneL.size();//Size of the first ArrayList
		int size2 = twoL.size();//Size of the second ArrayList
		int idx1 = 0, idx2 = 0;	//Holds index of the first and second ArrayLists
		String[] tempArr = new String[(size1 + size2)];//Create a temporary array for holding
		int count = 0;			//Used to hold the index of the temporary array
		Stack<String> stringStack = new Stack<>();//Creates Stack data structure
		
		System.out.println("Checking first list for " + word);//Informative message
		while(idx1 < size1) {
			if(oneL.get(idx1).toLowerCase().contains(word.toLowerCase())) {
				System.out.println("Found " + word + "in " + oneL.get(idx1));//Prints message that in this index the String contains word
				System.out.println("Adding to the list...");//Informative message
				tempArr[count] = oneL.get(idx1);//Adds to the array
			}
			idx1++;//Increases first ArrayList index
			count++;//Increases temporary array index
		}
		
		System.out.println("Checking second list for " + word);//Informative message
		while(idx2 < size2) {
			if(twoL.get(idx2).toLowerCase().contains(word.toLowerCase())) {
				System.out.println("Found " + word + "in " + twoL.get(idx2));//Prints message that in this index the String contains word
				System.out.println("Adding to the list...");//Informative message
				tempArr[count] = twoL.get(idx2);//Adds to the array
			}
			idx2++;//Increases second ArrayList index
			count++;//Increases temporary array index
		}
		
		Arrays.sort(tempArr);//Sorts in chronological order, earliest to latest, based on number at the beginning of the messages
		for(int i = 0; i < tempArr.length; i++) {
			stringStack.push(tempArr[i]);//Pushes messages from earliest to latest in Stack, so it will be latest from earliest
		}
		
		return stringStack;//Returns the Stack data structure
	}
	/**
	 * Demonstration
	 */
	public static void main(String[] args) {
		ArrayList<String> file1 = new ArrayList<>(fromFileToList(args[0]));//First text converted to an ArrayList
		ArrayList<String> file2 = new ArrayList<>(fromFileToList(args[1]));//Second text converted to an ArrayList
		Stack<String> iList = checkList(file1,file2,"word");//Creates a Stack data structure with the two ArrayLists containing "word"
		DynArrayList<String> dList = new DynArrayList<>();//Creates a new Dynamic ArrayList
		for(int i = 0; i < iList.size(); i++) {			//For each element in the Stack
			dList.add(i, iList.pop());					//Pop each element from the Stack into the Dynamic ArrayList
		}
	}

}
