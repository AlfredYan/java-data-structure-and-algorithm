import java.util.Arrays;

/**
 * Shell Sort:
 * It is a special Insertion Sort, improves performance in the case
 * that small items are far to the right of array in the Insertion Sort.
 * Shell Sort uses partially sorting array before using the Insertion Sort
 * 
 * Insertion Sort is the same as Shell Sort when the Interval equals 1
 * 
 * Interval is the spaces between different indexes that will be compared
 * 
 * @author Alfred
 *
 */
public class ShellSort {
	
	private int[] theArray;
	
	private int arraySize;
	
	public ShellSort(int arraySize){
		
		this.arraySize = arraySize;
		
		theArray = new int[arraySize];
		
		generateRandomArray();
		
	}
	
	public void generateRandomArray() {
		
		for(int i=0; i<arraySize; i++){
			
			//generate a random array with values between 10~59
			theArray[i] = (int)(Math.random() * 50) + 10;
			
		}
		
	}
	
	public void sort(){
		
		int inner, outer, temp;
		int interval = 1;
		
		while(interval <= arraySize / 3){
			
			interval = interval * 3 + 1;
		}
		
		while(interval > 0){
			
			for(outer = interval; outer < arraySize; outer++){
				
				temp = theArray[outer];
				inner = outer;
				
				while(inner > interval - 1 && theArray[inner - interval] >= temp){
					
					theArray[inner] = theArray[inner - interval];
					inner -= interval;
				}
				
				theArray[inner] = temp;
			}
			
			interval = (interval - 1) / 3;
		}
	}
	
	public void sortWithPrint(){
		
		int inner, outer, temp;
		
		/* 
		 * the spaces between the different indexes that will be compared.
		 * it depends on the size of array to improve the performance
		 */
		int interval = 1;
		
		while(interval <= arraySize / 3){
			
			//define an interval sequence
			interval = interval * 3 + 1;
			
		}
		
		System.out.println("Interval begin with: " + interval);
		
		/* 
		 * Keep looping until the interval is 1
		 * when the interval equals 1, it becomes an insertion sort
		 */
		while(interval > 0){
			
			//increment outer until reach the end of the array
			for(outer = interval; outer < arraySize; outer++){
				
				/*
				 * store the value of the array in "temp"
				 * unless it has to be copied to a space
				 * occupied by a bigger number closer to
				 * the beginning of the array
				 */
				temp = theArray[outer];
				
				System.out.println("Copy "+theArray[outer]+" into temp");
				
				inner = outer;
				
				System.out.println("Checking if " + theArray[inner - interval]
						+ " in index " + (inner - interval)
						+ " is bigger than " + temp);
				
				/*
				 * while there is a number bigger than "temp"
				 * move it further up in the array
				 */
				while(inner > interval-1 && theArray[inner-interval] >= temp){
					
					System.out.println("In While Checking if "
							+ theArray[inner - interval] + " in index "
							+ (inner - interval) + " is bigger than " + temp);
					
					printHorzArray(inner, outer, interval);
					
					/*
					 * make room for the smaller "temp"
					 * by moving values in the array up one space
					 * if they are greater than "temp"
					 */
					theArray[inner] = theArray[inner - interval];
					
					System.out.println(theArray[inner - interval]
							+ " moved to index " + inner);
					
					inner -= interval;
					
					System.out.println("inner= " + inner);

					printHorzArray(inner, outer, interval);

					System.out.println("outer= " + outer);
					System.out.println("temp= " + temp);
					System.out.println("interval= " + interval);
					
				}
				
				/*
				 * assign the "temp" to the index
				 * which is the closest of the beginning of array
				 */
				theArray[inner] = temp;
				
				printHorzArray(inner, outer, interval);

				System.out.println("outer= " + outer);
				System.out.println("temp= " + temp);
				System.out.println("interval= " + interval);
			}
			
			interval = (interval - 1) / 3;
			
		}
	}
	
	//COPY from wikipedia
	public void sortTwo(){

		int gap = 1, i, j, len = arraySize;
		int temp;
		while (gap < len / 3){
			System.out.println("While Loop");
			gap = gap * 3 + 1; // <O(n^(3/2)) by Knuth,1973>: 1, 4, 13, 40, 121, ...
		}	
		for (; gap > 0; gap /= 3){
			System.out.println("For Loop 1");
			for (i = gap; i < len; i++) {
				System.out.println("For Loop 2");
				temp = theArray[i];
				for (j = i - gap; j >= 0 && theArray[j] > temp; j -= gap){
					System.out.println("For Loop 3");
					theArray[j + gap] = theArray[j];
				}	
				theArray[j + gap] = temp;
			}
		}	
	}
	
	/*
	 * print theArray in horizontal
	 * in order to show the process of Shell Sort clearly
	 * COPY from Derek Banas's tutorial
	 */
	public void printHorzArray(int i, int j, int h) {

		if (i == j)
			i = i - h;

		for (int n = 0; n < 51; n++)
			System.out.print("-");

		System.out.println();

		for (int n = 0; n < arraySize; n++) {

			System.out.print("| " + n + "  ");

		}

		System.out.println("|");

		for (int n = 0; n < 51; n++)
			System.out.print("-");

		System.out.println();

		for (int n = 0; n < arraySize; n++) {

			System.out.print("| " + theArray[n] + " ");

		}

		System.out.println("|");

		for (int n = 0; n < 51; n++)
			System.out.print("-");

		System.out.println();

		if (i != -1) {

			// Number of spaces to put before the F

			int spacesBeforeFront = 5 * i + 1;

			for (int k = 0; k < spacesBeforeFront; k++)
				System.out.print(" ");

			System.out.print("I");

			// Number of spaces to put before the R

			int spacesBeforeRear = (5 * j + 1 - 1) - spacesBeforeFront;

			for (int l = 0; l < spacesBeforeRear; l++)
				System.out.print(" ");

			System.out.print("O");

			System.out.println("\n");

		}

	}
	
	public static void main(String[] args) {
		
		ShellSort theSort = new ShellSort(10);
		
		System.out.println("Before sort: " + Arrays.toString(theSort.theArray));
		
		//without print the process
		//theSort.sort();
		
		theSort.sortWithPrint();
		
		System.out.println("After sort: " + Arrays.toString(theSort.theArray));
	}
}
