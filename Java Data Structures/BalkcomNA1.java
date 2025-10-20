/**
 * Implements a model of a Baseball Batter
 * 
 * @author Nick Balkcom, ITEC 6400-01
 */

public class BalkcomNA1 {
	
		/** 
		 * Creates data field to hold three Strings
		 * 
		 * @param gear 		Private data field to hold an array
		 */
		private String[] gear = {"","",""};
		
		/** 
		 * Constructor initializing the class array data
		 */
		public BalkcomNA1(){
			gear[0] = "Baseball";	//Initializes first index
			gear[1] = "Bat";		//Initializes second index
			gear[2] = "Jersey";		//Initializes third index
		}
		
		/**
		 * Method for processing and printing data into an informative message
		 * 
		 * @param x			Integer parameter for receiving input for the method
		 * @param txt		String parameter holding first part of string
		 */
		void grab(int x) {
			String txt = "The player has grabbed their ";	//First part of string to be printed
			System.out.println(txt + gear[x - 1]);			//Concatenates with the item in the position of the array, not index 
		}
		
		/**
		 * Public method for printing item and displays error message while closing the program
		 * 
		 * @param x			Integer parameter for receiving input for the method
		 */
		public void get(int x) {
			if(x <= 0 || x >= 4) {													//If the inputed position does not exist
				System.out.println("No item at this position. Exiting program.");	//Display error message for out of bounds positions
				System.exit(0);														//and exits program
			}
			else {																	//Otherwise
				System.out.println(gear[x-1]);										//Prints item at position, not index
			}
		}
		
		/**
		 * Main method for constructing the class object and testing methods
		 * 
		 * @param b			Name of constructed class object
		 */
		public static void main(String[] args) {
			BalkcomNA1 b = new BalkcomNA1();
			b.get(1);
			b.grab(1);
			b.get(2);
			b.grab(2);
			b.get(3);
			b.grab(3);
			b.get(0);
			b.get(4);

		}
	
}
