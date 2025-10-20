/** @author Nick Balkcom, ITEC 6400-01*/

import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Comparator;

class SMuser {
															//5.1.a
	/**Private data fields */
	/**String value to hold first name*/
	private String fName = "";
	/**String value to hold last name*/
	private String lName = "";
	/**String value to hold user name*/
	private String username = "";
	/**String value to hold password*/
	private String password = "";
	/**Queue of posts that are String values*/				//5.1.b
	private Queue<String> postList = new LinkedList<>();		//I chose a Queue since it needs to be in chronological order and it's easy to append more posts to the end of it
	/**Double value of Average Index of First Occurrence*/	//5.1.c
	private double aifo;
	
	/**
	 * Constructor to make a Social Media User
	 * 
	 * @param f		Holds the String value of first name
	 * @param l		Holds the String value of last name
	 * @param u		Holds the String value of user name
	 * @param p		Holds the String value of password
	 */
	public SMuser (String f, String l, String u, String p) {
		fName = f;
		lName = l;
		username = u;
		password = p;
	}
	
	/**
	 * Adds a post to the Queue that holds posts
	 * 
	 * @param s		= Holds the String value of the post
	 */
	 public void addPost(String s) {
		postList.add(s);		//Adds post
	 }
	 /**
	  * Adds multiple posts to the Queue that holds posts
	  * 
	  * @param posts		= Holds the list of String values
	  */
	 public void addPosts(ArrayList<String> posts) {
		 for(int i = 0; i < posts.size(); i++) {		//For each String value in the list
			 this.addPost(posts.get(i));		//Calls for addPost method for each index in the list
		 }
	 }
	 /**Getter for String value first name*/
	 public String getFname() {
		 return fName;
	 }
	 /**Getter for String value last name*/
	 public String getLname() {
		 return lName;
	 }
	 /**Getter for String value user name*/
	 public String getUsername() {
		 return username;
	 }
	 /**Getter for String value password*/
	 public String getPassword() {
		 return password;
	 }
	 /**Getter for Queue of posts*/
	 public Queue<String> getPosts(){
		 return postList;
	 }
	 /**Getter for Double value AIFO*/
	 public double getAIFO() {
		 return aifo;
	 }
	 													//5.2
	 /**
	  * Method that computes,stores, and returns the value of the Average Index of First Occurrence(AIFO) for a word chosen
	  * <br><br>
	  * This overall complexity in Big Oh notation is O(m*n<sup>2</sup>) since the running time for the first for loop runs at O(n) where n is for each element in the list, 
	  * the second for loop runs at O(n) because it would be executed at most at (n - (m + 1)) times for each character in the post and where m represents character comparisons, 
	  * and the inner while loop runs at O(m) for each character comparison made.
	  * 
	  * @param posts		//Holds a String Queue of posts from a Social Media User
	  * @param word			//Holds the word used to find the AIFO
	  */
	 public double makeAIFO(Queue<String> posts,String word) {
		 ArrayList<String> temp = new ArrayList<String>();			//Creates temporary LinkedList to hold the String values from the queue for indexing
		 temp.addAll(posts);										//Adds each String value from the queue to the list
		 double sum = 0.0;											//Holds the sum of the indexes
		 double count = 0.0;											//Holds the count of times found in the posts
		 for(int index = 0; index < temp.size(); index++) {			//For each post in the list. This is O(n) since it's accounting for each element(n) in the list
			char[] post = temp.get(index).toCharArray();		//Converts current String post into Character Array
			char[] search = word.toCharArray();					//Converts String word into Character Array
			int n = post.length;								//Holds length of current post Character Array
			int m = search.length;								//Holds length of search word Character Array
			for (int i = 0; i <= n - m; i++) {				//For every starting index within the post
	            int k = 0;									//Holds search word index
	            while (k < m && post[i + k] == search[k])//While this character of the search matches the character of the post
	            	k++;								 //Keeps checking
	            if (k == m) {		//If it reaches the last character of the post
	                sum += i;		//Increases sum by the index
	                count++;		//Search word found count increases
	            }
	        }
	        sum += 0;			//If search fails, add 0 to the sum
		 }
		 if(count == 0)
			 count = 1.0;
		 aifo = sum/count;	//Get the AIFO by dividing the sum of the indexes by the size of the queue(total posts)
		 return aifo;		//Return the AIFO
	 }

	 

}
class SortbyAIFO implements Comparator<SMuser>{
		//5.3
	/**
	 * Comparator object for comparing the Double values of 2 Social Media Users AIFO
	 * 
	 * @param a		Holds the first Social Media User for comparison
	 * @param b		Holds the second Social Media User for comparison
	 */
	public int compare(SMuser a,SMuser b) {
		return Double.compare(b.getAIFO(), a.getAIFO());	//Returns an Integer value less than 0, equal to 0, or greater than 0 after comparing the 2 Double value AIFOs
	}
}
public class BalkcomNA7 {
	/**
	  * Method used to merge 2 queues into one based off of the comparator.
	  * Adapted to use the java.util.Queue interface instead of the textbook's ADT of Queue.
	  * <br><br>
	  * The Big Oh complexity of this method is O(n1 + n2), as in the number of the elements in the first queue(n1) and the number of the elements in the second queue(n2).
	  * Since in the while loop, one element is placed in the final queue for both n1 and n2, the resulting running time would be O(n1 + n2).
	  * 
	  * @param queue		First queue that holds Social Media Users to be merged by comparator
	  * @param queue2		Second queue that holds Social Media Users to be merged by comparator
	  * @param queue3		Final queue of the 2 merged queues
	  * @param comparator	Comparator used to compare 2 AIFO Double values
	  * @author Textbook author
	  */
	 public static Queue<SMuser> merge(Queue<SMuser> queue, Queue<SMuser> queue2, Queue<SMuser> queue3, Comparator<SMuser> comparator) {
	        while (!queue.isEmpty() && !queue2.isEmpty()) {		//While the two queues aren't empty
	            if (comparator.compare(queue.peek(), queue2.peek()) < 0) {		//If the Integer value from the comparator is less than 0, based on the first element from the 2 queues
	                queue3.add(queue.poll());		//Adds the first element from the first queue to the final queue
	            }
	            else {		//If the Integer value from the comparator is equal to or greater than 0, based on the first element from the 2 queues
	                queue3.add(queue2.poll());		//Adds the first element from the second queue to the final queue
	            }
	        }
	        while (!queue.isEmpty()) {		//While the first queue isn't empty, runs n1 amount of times where n1 is the number of elements in the first queue
	            queue3.add(queue.poll());		//Adds all elements from the first queue
	        }
	        while (!queue2.isEmpty()) {		//While the second queue isn't empty, runs n2 amount of times where n2 is the number of elements in the first queue
	            queue3.add(queue2.poll());		//Adds all elements from the second queue
	        }
	        return queue3;
	 }
	 /**
	  * Method used for merge-sorting a queue based off of the comparator.
	  * Adapted to use the java.util.Queue interface instead of the textbook's ADT of Queue.
	  * <br><br>
	  * The Big Oh complexity of this method would be O(<i>n</i>  * log <i>n</i>). Since the height of the recursions will increase at log <i>n</i>
	  * and at each level of the height there's a n/2<sup>i</sup> step being made, where i is the height's level starting from 0 and n is the number of elements in that queue, 
	  * it's running time would be O(<i>n</i> * log <i>n</i>).
	  *
	  * @param queue		Original queue that holds Social Media Users to be sorted by comparator
	  * @param comparator	Comparator used to compare 2 AIFO Double values
	  * @author Textbook author
	  */
	 public static <K> void mergeSort(Queue<SMuser> queue, Comparator<SMuser> comparator) {
	        int size = queue.size();		//Records size of the original queue
	        if (size < 2) {		//If the current size of this queue is less than 2
	            return;		//Return back to the previous call
	        }
	        Queue<SMuser> queue1 = new LinkedList<SMuser>();		//Creates the first empty queue
	        Queue<SMuser> queue2 = new LinkedList<SMuser>();		//Creates the second empty queue
	        while (queue1.size() < size / 2) {		//While the size of the first queue is less than half the size of the original queue, first half of the original queue, n/2
	            queue1.add(queue.poll());		//Add those elements to the first queue
	        }
	        while (!queue.isEmpty()) {		//While the original queue isn't empty, remaining half of the original queue, n/2
	            queue2.add(queue.poll());		//Add the remaining elements to the second queue
	        }
	        mergeSort(queue1, comparator);		//Recursively repeat for the first queue until the last call's queue size is less than 2, has log(n) running time
	        mergeSort(queue2, comparator);		//Recursively repeat for the second queue until the last call's queue size is less than 2, has log(n) running time
	        Queue<SMuser> Fqueue = merge(queue1, queue2, queue, comparator);		//Merge the final sorted version of the first and second queues into one final queue
	        ArrayList<SMuser> temp = new ArrayList<SMuser>();
	        for(int i =0; i < Fqueue.size(); i++) {
	        	System.out.println(Fqueue.peek().getFname() + " has an AIFO of : " + Fqueue.peek().getAIFO());
	        	temp.add(Fqueue.poll());
	        }
	        
	 }
	 
	public static void main(String[] args) {
															//Making 5 Social Media Users
		SMuser userA = new SMuser("Aaron","Baker","","");
		SMuser userB = new SMuser("Betty","Crockett","","");
		SMuser userC = new SMuser("Charles","Danish","","");
		SMuser userD = new SMuser("Danny","Eggland","","");
		SMuser userE = new SMuser("Emily","Fondue","","");
															//Making lists for each post
		ArrayList<String> list1 = new ArrayList<>() {
			{
				add("Making bread today!");
				add("Making croissants today!");
				add("Making biscuits today!");
				add("Making croissants today!");
				add("Making more croissants today!");
				add("Bought some bread today.");
				add("Also bought some icing!");
				add("Making a cake!");
				add("Looking for recipes on danishes...");
				add("Thanks Charles!");
			}
		};
		//System.out.println(list1);		Testing...
		
		ArrayList<String> list2 = new ArrayList<>() {
			{
				add("Easy cake recipe right here");
				add("Check my website for new recipes");
				add("Try adding icing to danishes. They're great!");
				add("Check my website for new recipes");
				add("Visit Danny Eggland's Farm for fresh produce in this area");
				add("Check my website for new recipes");
				add("Check out Emily's Hot Fondue! Looks very creamy");
				add("Check my website for new recipes");
				add("Try using Charles's new danish recipe for Blueberry Danishes");
				add("Check my website for new recipes");
			}
		};
		//System.out.println(list2);		Testing...
		
		ArrayList<String> list3 = new ArrayList<>() {
			{
				add("Trying to make a new danish.");
				add("Blueberry Danishes came out great!");
				add("Maybe I should try adding icing on top?");
				add("Betty Crokett recommends it.");
				add("Need to buy some more eggs.");
				add("Danny, when does your farm open today?");
				add("The icing works great with these danishes!");
				add("Here's the recipe if anyone want to try it!");
				add("Replying to Aaron Baker: Use my recipe Aaron!");
				add("Replying to Aaron Baker: You're welcome!");
			}
		};
		//System.out.println(list3);		Testing...
		
		ArrayList<String> list4 = new ArrayList<>() {
			{
				add("Come visit our farm today");
				add("Now Hiring: Cashiers and Produce Specialists");
				add("A special guest is coming in a couple days! Look out for a special message.");
				add("We're having maintainence come in this Sunday. Apologies to our customers.");
				add("Special deal: Cake section has a 20% off clearance items!");
				add("Special guest Betty Crockett visiting today!");
				add("Special deal: Eggs 20% off today!");
				add("Replying to Charles Danish: We're open from 9AM to 7PM!");
				add("A customer says we say special alot on social media. That's because everyday is special!");
				add("Welcome Emily Fondue, our new Produce Specialist!");
			}
		};
		//System.out.println(list4);		Testing...
		
		ArrayList<String> list5 = new ArrayList<>() {
			{
				add("Almost dodged a bullet with rent this month");
				add("Working in my mom's shop is fine, but it's also kinda boring...");
				add("I might to find a new job. Any recommendations?");
				add("I'm trying this recipe I created a couple months ago today.");
				add("Check out this new Cheese Fondue I made!");
				add("My special ingredient is a little cream cheese.");
				add("Woah, I got featured by Betty Crockett");
				add("Someone just tipped me about a new job. Wish me luck in my interview!");
				add("I think the interview went well!");
				add("Replying to Danny Eggland: I'm glad to be on the team!");
			}
		};
		//System.out.println(list5);		Testing...
															//Putting lists as queues for respective Social Media Users while making AIFO for the word "special"
		userA.addPosts(list1);
		userA.makeAIFO(userA.getPosts(),"special");
	
		userB.addPosts(list2);
		userB.makeAIFO(userB.getPosts(),"special");

		userC.addPosts(list3);
		userC.makeAIFO(userC.getPosts(),"special");

		userD.addPosts(list4);
		userD.makeAIFO(userD.getPosts(),"special");

		userE.addPosts(list5);
		userE.makeAIFO(userE.getPosts(),"special");

		
		Comparator<SMuser> comp = new SortbyAIFO();
															//Putting each Social Media into a queue
		Queue<SMuser> queue1 = new LinkedList<>();
		queue1.add(userA);
		queue1.add(userB);
		queue1.add(userC);
		queue1.add(userD);
		queue1.add(userE);
															//Should sort and print information but doesn't print each user correctly, only prints 4 users
		mergeSort(queue1,comp);
		
		userA.makeAIFO(userA.getPosts(),"recipe");
		userB.makeAIFO(userB.getPosts(),"recipe");
		userC.makeAIFO(userC.getPosts(),"recipe");
		userD.makeAIFO(userD.getPosts(),"recipe");
		userE.makeAIFO(userE.getPosts(),"recipe");
															//Putting each Social Media into a queue
		Queue<SMuser> queue2 = new LinkedList<>();
		queue2.add(userA);
		queue2.add(userB);
		queue2.add(userC);
		queue2.add(userD);
		queue2.add(userE);
															//Should sort and print information but doesn't print each user correctly, only prints 4 users
		mergeSort(queue2,comp);
	}

}
