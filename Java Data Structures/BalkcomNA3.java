/**
 * Implements the model of a TV news of sports and weather
 * 
 * @author Nick Balkcom, ITEC 6400-01
 */


public class BalkcomNA3 {
	/**
	 * Method for processing pairs through a unsorted array that differ by at most 3
	 * @param unsorted		Holds an unsorted array with integer elements
	 */
	static void unsortP(int unsorted[]) {
		int pair = 0;						//Holds the number of pairs in the sequence
		int length = unsorted.length;		//Holds the length of the array
		for(int x = 0; x < length-1; x++) {				//Takes first element and every element after as the first pair
			for(int y = x + 1;y <= length-1;y++) {		//Takes the second element and every element after as the second pair
				if( (unsorted[x]-unsorted[y]) >= -3 &&(unsorted[x]-unsorted[y]) <= 3) {	//If the pairs differ by at most 3
					System.out.print("("+unsorted[x]+", "+unsorted[y]+") ");	//Prints the pairs in parentheses
					pair++;														//Adds to the number of pairs
				}
			}
		}
		System.out.println("These are the scores for the "+ pair + " games of last week.");//Prints how many pairs
	}
	
	/**
	 * Method for processing pairs through a sorted array that differ by at most 3
	 * @param sorted		Holds an sorted array with integer elements
	 */
	static void sortP(int sorted[]) {
		int pair = 0;					//Holds the number of pairs in the sequence
		int length = sorted.length;		//Holds the length of the array
		for(int x = 0; x < length-1; x++) {		//Takes first element and every element after as the first pair
			int y = x + 1;						//Takes the element after the first, less work steps since it's only checking the element after the last one instead of every element after the last one
			if( (sorted[x]-sorted[y]) >= -3 &&(sorted[x]-sorted[y]) <= 3) {		//If the pairs differ by at most 3
				System.out.print("("+sorted[x]+", "+sorted[y]+") ");			//Prints the pairs in parentheses
				pair++;															//Adds to the number of pairs
			}
		}
		System.out.println("These are the scores for the "+ pair + " games of last week.");//Prints how many pairs
	}
	
	/**
	 * Method for calculating the average of an even sized array recursively
	 * @param arr 		Array that holds double elements
	 * @param length	Integer that holds the length of the array
	 */
	static double average(double arr[], int length) {
		double sum;									//Holds the sum of all the double element
		double result;								//Holds the average result
		if(length == 1) {
			return arr[0];							//Returns the first element at the end of the recursion
		}else {
			sum = arr[length-1] + (length - 1)*average(arr,length-1); //Recursion for finding the sum of double elements
		}
		result = sum/length;						//Calculates average
		if(arr.length%2 == 1) {return result = 0.0;}//If the size is odd, returns 0.0 instead
		return result;								//Else returns the result
	}
	
	/**
	 * Demonstration method
	 */
	public static void main(String[] args) {
		int uArr[]= {13,8,15,9,14,7};
		unsortP(uArr);
		int sArr[]= {1,3,8,11,14,19};
		sortP(sArr);
		double arr1[] = {72.0,71.4,83.3};
		System.out.println(average(arr1,arr1.length));
		double arr2[] = {72.0,71.4,83.3,82.9};
		System.out.println("The average temperature in the next "+arr2.length+" days will be " + average(arr2,arr2.length)+" degrees Fahrenheit.");

	}

}
