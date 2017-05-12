/**
 * Linear Search & Binary Search
 * Bubble, Selection & Insertion Sort
 * @author Alfred
 *
 */

public class SearchAndSortAlgorithm {
	
	private int[] theArray = new int[50];
	
	private int arraySize = 10;
	
	public void generateRandomArray(){
		for(int i=0; i<arraySize; i++){
			//insert random number between 10~19 to array
			theArray[i] = (int) (Math.random()*10) + 10;
		}
	}
	
	/*
	 * print theArray in horizontal
	 * in order to show the process of search and sort clearly
	 * COPY from Derek Banas's tutorial
	 */
	public void printHorzArray(int i, int j){
		
		for(int n = 0; n < 51; n++)System.out.print("-");
		
		System.out.println();
		
		for(int n = 0; n < arraySize; n++){
			
			System.out.print("| " + n + "  ");
			
		}
		
		System.out.println("|");
		
		for(int n = 0; n < 51; n++)System.out.print("-");
		
		System.out.println();
		
		for(int n = 0; n < arraySize; n++){
			
			System.out.print("| " + theArray[n] + " ");
			
		}
		
		System.out.println("|");
		
		for(int n = 0; n < 51; n++)System.out.print("-");
		
		System.out.println();
		
		// END OF FIRST PART
		
		
		// ADDED FOR BUBBLE SORT
		
		if(j != -1){
		
			// ADD THE +2 TO FIX SPACING
			
			for(int k = 0; k < ((j*5)+2); k++)System.out.print(" ");
			
			System.out.print(j);
			
		}
		
		
		// THEN ADD THIS CODE
		
		if(i != -1){
			
			// ADD THE -1 TO FIX SPACING
			
			for(int l = 0; l < (5*(i - j)-1); l++)System.out.print(" ");
			
			System.out.print(i);
			
		}
		
		System.out.println();
		
	}

	//swap values for two indexes
	public void swapValues(int indexOne, int indexTwo){
		
		int tempValue = theArray[indexOne];
		theArray[indexOne] = theArray[indexTwo];
		theArray[indexTwo] = tempValue;
	}
	
	public String linearSearch(int value){
		
		boolean valueInArray = false;
		String indexOfValue = "";
		
		for(int i=0; i<arraySize; i++){
			if(theArray[i] == value){
				valueInArray = true;
				
				//add " " can convert int to String
				indexOfValue += i + " ";
			}
			
			printHorzArray(i, -1);
		}
		
		if(!valueInArray){
			indexOfValue = "None";
		}
		
		System.out.println("The value was found in: " + indexOfValue);
		System.out.println();
		
		return indexOfValue;
	}
	
	public void binarySearch(int value){
		
		int lowIndex = 0;
		int highIndex = arraySize-1;
		boolean valueInArray = false;
		
		while(lowIndex <= highIndex){
			
			int middleIndex = (lowIndex + highIndex) / 2;
			if(theArray[middleIndex] < value){
				
				lowIndex = middleIndex + 1;
				
			}else if(theArray[middleIndex] > value){
				
				highIndex = middleIndex - 1;
				
			}else{
				valueInArray = true;
				System.out.println("Found a match for "+value+
						" at index "+middleIndex);
				
				//To break the loop
				lowIndex = highIndex+10;
			}
			printHorzArray(middleIndex, -1);
		}
		
		if(!valueInArray){
			System.out.println("No match in the array");
		}
				
	}
	
	//ASC the array by bubble sort
	public void bubbleSort(){
		
		/*
		 * i starts at the end of the array
		 * it is used to decrease the indexes which have been sorted
		 */
		for(int i=arraySize-1; i>0; i--){
			
			/*
			 * j starts at the beginning of the array
			 * the loop compares each value next to each other
			 * if value is greater, then swap them
			 */
			for(int j=0; j<i; j++){
				
				//To have a DESC sort, change ">" to "<"
				if(theArray[j] > theArray[j+1]){
					swapValues(j,j+1);
					printHorzArray(i, j);
				}
			}
		}
	}
	
	
	
	
	
	public static void main(String[] args) {
		SearchAndSortAlgorithm newArray = new SearchAndSortAlgorithm();
		
		newArray.generateRandomArray();
		
		newArray.printHorzArray(-1, -1);
		
//		System.out.println("Linear Search Start...");
//		newArray.linearSearch(15);
//		System.out.println("Linear Search End...");
		
		System.out.println("Bubble Sort Start...");
		newArray.bubbleSort();
		System.out.println("Bubble Sort End...");
		System.out.println();
		
		System.out.println("Binary Search Start...");
		newArray.binarySearch(15);;
		System.out.println("Binary Search End...");
	}
}

