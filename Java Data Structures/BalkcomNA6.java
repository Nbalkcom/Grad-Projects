/**
 * Instruments the AVLTreeMap implementation to print out the overall tree’s height (maximum leaf depth) before and after each rebalance(in TreeClasses.java)
 * 
 * Implements a social media user class to represent data about such a user including name, id number, floating point activity level, String values hobby and pet peeves, 
 * and friend list holding other id numbers
 * 
 * Implements a main method to demonstrate Creating a collection of at least six social media user objects to represent users containing unique data, 
 * Implements an unsorted map to hold map entries that consist of the user ID number as key and a list of ID numbers of the friends of the user as the value,
 * Add entries to the id to friend list map for each user with the standard map operations(Ensures some, but not all, of the users are friends with each other and that only some users have friends in common),
 * For each user, use the id to friend list map to obtain the ID numbers of the friends and create a set data structure to hold these ID numbers,
 * Uses standard set operations and accessing the user object data, determines the friends in common between all pairs of users and print our their names,
 * Creates method to compute the compatibility of two social media users for friendship (based on their hobbies and pet peeves),
 * Creates for each user an AVLTreeMap to hold mappings of Social Media User ID to compatibility score (floating point number) and insert the entries into the map,
 * And for each user, prints out the information of the other users for which has the highest friend compatibility.
 * 
 * @author Nick Balkcom, ITEC 6400-01
 */

import java.util.*;

/**
 * Class for representing data about a Social Media User
 * @author Nick Balkcom
 */
class SMUser{
	/** Private data fields*/
	/**Holds String value Name of Social Media User*/
	private String name;
	/**Holds Integer value ID number of Social Media User*/
	private int id;
	/**Holds Float value Activity of Social Media User*/
	private float activity;
	/**Holds String value Hobby of Social Media User*/
	private String hobby;
	/**Holds String value Pet Peeves of Social Media User*/
	private String peeve;
	/**Holds list of friend ID numbers */
	private ArrayList<Integer> friendList = new ArrayList<>();
	/**
	 * Constructor for Social Media User
	 * 
	 * @param n		Holds String Name of Social Media User
	 * @param i		Holds Integer value ID number of Social Media User
	 * @param a		Holds Float value Activity of Social Media User
	 * @param h		Holds String Hobby of Social Media User
	 * @param p		Holds String Pet Peeves of Social Media User
	 */
	SMUser(String n,int i, float a,String h,String p){
		name = n;
		id = i;
		activity = a;
		hobby = h;
		peeve = p;
	}
	
	/**
	 * Adds another SMUser's id to this one's friend list
	 * 
	 * @param i		Holds Integer value ID number of Social Media User
	 */
	public void addFriendList(int i){
		if(i != id) {			//if id was not this SMUser's id
			friendList.add(i);	//add to list
		}else {
			System.out.println(name + " cannot friend theirself!");		// Prints message that they can't add their id
		}
	}
	
	/**Getter for friend list ArrayList*/
	public ArrayList<Integer> getList(){
		return friendList;
	}
	
	/**Getter for name String*/
	public String getName() {
		return name;
	}
	
	/**Getter for id Integer*/
	public int getID() {
		return id;
	}
	
	/**Getter for activity Float*/
	public float getActivity() {
		return activity;
	}
	
	/**Getter for hobby String*/
	public String getHobby() {
		return hobby;
	}
	
	/**Getter for pet peeve String*/
	public String getPeeve() {
		return peeve;
	}
}

//Demonstration 
public class BalkcomNA6 {
	/**
	 * Method for checking if one SMUser has friends in common with another SMUser and prints messages
	 * 
	 * @param set		Holds Set of IDs that are the friends of the first SMUser
	 * @param map		Holds Map of all the SMUsers IDs and their friends list
	 * @param list		Holds List of all the SMUsers
	 */
	public static String check(Set<Integer> set,UnsortedTableMap<Integer,ArrayList<Integer>> map,ArrayList<SMUser> list) {
		String message = "";		//Holds the message to be printed
		for(int i = 0; i < map.size(); i++) {		//For each key in the map
			if(set.containsAll(map.get(i))) {		//If this key holds a list that contains all the elements in the Set
				message = message + "";			//Print nothing
			}else {
				message += "\t has Friend User ID(s): ";	//Prints beginning line of message for new key
				for(int x = 0; x < map.get(i).size(); x++) {	//For each ID in the list of this key
					if(set.contains(map.get(i).get(x))){		//If this ID(s) matches with one in the Set
						message += map.get(i).get(x) + " ";		//Adds ID(s) to the message
					}
				}
				message += "in common with " + list.get(i).getName() + "\n";	//Adds ending line of the message for this key
			}
		}
		return message + "\n";		//Returns message block
	}
	
	/**
	 * Method for comparing the compatibility of two SMUsers, prints compatibility message, and returns Float compatibility score 
	 * 
	 * @param a		Holds first SMUser
	 * @param b		Holds second SMUser
	 */
	public static float compatable(SMUser a, SMUser b) {
		float comp = 50f;		//Starts at neutral compatibility level
		
		//Compares first SMUser's Hobby
		if(a.getHobby() == b.getHobby()) {		//If they share the same hobby
			comp += 25f;		//Increase compatibility level
		}
		else if(a.getHobby() == b.getPeeve()) {	//If their hobby is the other's pet peeve
			comp -= 25f;		//Decrease compatibility level
		}
		
		//Compares first SMUser's Pet Peeve
		if(a.getPeeve() == b.getPeeve()) {		//If they share the same pet peeve
			comp += 25f;		//Increase compatibility level
		}else if(a.getPeeve() == b.getHobby()) {//If their pet peeve is the other's hobby
			comp -= 25f;		//Decrease compatibility level
		}
		System.out.println(a.getName() + " is " + comp + "% compatible with " + b.getName());	//Prints compatibility message
		return comp;		//Return compatibility score
	}
	public static void main(String[] args) {
																//Creates 6 different SMUsers
		SMUser a = new SMUser("Aaron",0,4.1f,"Music","Bugs");
		SMUser b = new SMUser("Beatrice",1,2.4f,"Bugs","Beaches");
		SMUser c = new SMUser("Charles",2,3.6f,"Bugs","Fruit");
		SMUser d = new SMUser("Danelle",3,2.9f,"Fruit","Bugs");
		SMUser e = new SMUser("Eric",4, 6.2f,"Music","Candy");
		SMUser f = new SMUser("Fiona",5,1.8f,"Candy","Beaches");
																//Adds different SMUsers' IDs to the prior 6 SMUsers' friend lists
		a.addFriendList(2052);
		a.addFriendList(d.getID());
		b.addFriendList(f.getID());
		b.addFriendList(2027);
		c.addFriendList(2350);
		c.addFriendList(2897);
		d.addFriendList(a.getID());
		d.addFriendList(2801);
		e.addFriendList(2502);
		e.addFriendList(2027);
		f.addFriendList(b.getID());
		f.addFriendList(2052);
																//Creates Array List to hold the 6 SMUsers
		ArrayList<SMUser> smList = new ArrayList<>();
		smList.add(a);
		smList.add(b);
		smList.add(c);
		smList.add(d);
		smList.add(e);
		smList.add(f);
																//Creates Unsorted Map that store keys as a SMUser's ID and values as a list of that SMUser's friends' IDs
		UnsortedTableMap<Integer,ArrayList<Integer>> friendMap = new UnsortedTableMap<>();
		for(int i = 0; i < smList.size(); i++) {		//For each SMUser in the Array List
			friendMap.put(smList.get(i).getID(), smList.get(i).getList());		//Store entries as <SMUser's ID,SMUser's friends list>
		}
																//Creates a Set for each SMUser that stores the SMUser's friends list
		Set<Integer> aSet = new HashSet<Integer>();
		aSet.addAll(a.getList());
		Set<Integer> bSet = new HashSet<Integer>();
		bSet.addAll(b.getList());
		Set<Integer> cSet = new HashSet<Integer>();
		cSet.addAll(c.getList());
		Set<Integer> dSet = new HashSet<Integer>();
		dSet.addAll(d.getList());
		Set<Integer> eSet = new HashSet<Integer>();
		eSet.addAll(e.getList());
		Set<Integer> fSet = new HashSet<Integer>();
		fSet.addAll(f.getList());
																//Prints message blocks showing common friends between other SMUsers
		System.out.print(a.getName() + check(aSet,friendMap,smList));
		System.out.print(b.getName() + check(bSet,friendMap,smList));
		System.out.print(c.getName() + check(cSet,friendMap,smList));
		System.out.print(d.getName() + check(dSet,friendMap,smList));
		System.out.print(e.getName() + check(eSet,friendMap,smList));
		System.out.print(f.getName() + check(fSet,friendMap,smList));
																//Creates AVLTreeMap for the first SMUser, adding entries as <other SMUser's ID,their compatibility score>, prints messages
		AVLTreeMap<Integer,Float> aMap = new AVLTreeMap<>();
		aMap.put(b.getID(), compatable(a,b));
		aMap.put(c.getID(), compatable(a,c));
		aMap.put(d.getID(), compatable(a,d));
		aMap.put(e.getID(), compatable(a,e));
		aMap.put(f.getID(), compatable(a,f));
		System.out.println();	//Divider
																//Creates AVLTreeMap for the second SMUser, adding entries as <other SMUser's ID,their compatibility score>, prints messages
		AVLTreeMap<Integer,Float> bMap = new AVLTreeMap<>();
		bMap.put(a.getID(), compatable(b,a));
		bMap.put(c.getID(), compatable(b,c));
		bMap.put(d.getID(), compatable(b,d));
		bMap.put(e.getID(), compatable(b,e));
		bMap.put(f.getID(), compatable(b,f));
		System.out.println();	//Divider
																//Creates AVLTreeMap for the third SMUser, adding entries as <other SMUser's ID,their compatibility score>, prints messages
		AVLTreeMap<Integer,Float> cMap = new AVLTreeMap<>();
		cMap.put(a.getID(), compatable(c,a));
		cMap.put(b.getID(), compatable(c,b));
		cMap.put(d.getID(), compatable(c,d));
		cMap.put(e.getID(), compatable(c,e));
		cMap.put(f.getID(), compatable(c,f));
		System.out.println();	//Divider
																//Creates AVLTreeMap for the fourth SMUser, adding entries as <other SMUser's ID,their compatibility score>, prints messages
		AVLTreeMap<Integer,Float> dMap = new AVLTreeMap<>();
		dMap.put(a.getID(), compatable(d,a));
		dMap.put(b.getID(), compatable(d,b));
		dMap.put(c.getID(), compatable(d,c));
		dMap.put(e.getID(), compatable(d,e));
		dMap.put(f.getID(), compatable(d,f));
		System.out.println();	//Divider
																//Creates AVLTreeMap for the fifth SMUser, adding entries as <other SMUser's ID,their compatibility score>, prints messages
		AVLTreeMap<Integer,Float> eMap = new AVLTreeMap<>();
		eMap.put(a.getID(), compatable(e,a));
		eMap.put(b.getID(), compatable(e,b));
		eMap.put(c.getID(), compatable(e,c));
		eMap.put(d.getID(), compatable(e,d));
		eMap.put(f.getID(), compatable(e,f));
		System.out.println();	//Divider
																//Creates AVLTreeMap for the sixth SMUser, adding entries as <other SMUser's ID,their compatibility score>, prints messages
		AVLTreeMap<Integer,Float> fMap = new AVLTreeMap<>();
		fMap.put(a.getID(), compatable(f,a));
		fMap.put(b.getID(), compatable(f,b));
		fMap.put(c.getID(), compatable(f,c));
		fMap.put(d.getID(), compatable(f,d));
		fMap.put(e.getID(), compatable(f,e));


	}

}
