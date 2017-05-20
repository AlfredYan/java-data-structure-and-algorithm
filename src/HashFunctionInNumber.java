import java.util.ArrayList;
import java.util.Arrays;

/**
 * Hash Table:
 * It is a data structure, offers fast insertion and searching.
 * It is limited in size because it based on arrays.
 * It is hard to order and not do well in which duplicate data is stored.
 * The array should be big enough to avoid collisions but not too big.
 * 
 * Hash Function(Hash in Number):
 * It generate a unique key for every item in the array.
 * 
 * Collision:
 * We put item in an index in the array that already has item in it.
 * Set the size of hash table in prime number can help to avoid Collisions.
 * 
 * clustering:
 * It occurs because if there is a collision we just move to the next index.
 * Some items are inserted in a continuous index.
 * Double Hash can help avoid clusterings
 * 
 * @author Alfred
 *
 */
public class HashFunctionInNumber {

	String [] theArray;
	int arraySize;
	int itemsInArray = 0;
	
	public static void main(String[] args) {
		
		//Simplest Hash Function
		System.out.println("Simplest Hash Table:");
		HashFunctionInNumber simpleFunc = new HashFunctionInNumber(30);
		String [] elementsToAdd = {"1", "5", "7", "16", "25",};
		simpleFunc.hashFunctionSimplest(elementsToAdd, simpleFunc.theArray);
		simpleFunc.displayTheHashTable();
		System.out.println();
		
		String[] elementsToAdd2 = { "100", "510", "170", "214", "268", "398",
				"235", "802", "900", "723", "699", "1", "16", "999", "890",
				"725", "998", "978", "988", "990", "989", "984", "320", "321",
				"400", "415", "450", "50", "660", "624" };
		
		//Modulus Hash Function in prime sized array
		System.out.println("Modulus Hash Table(size=31):");
		HashFunctionInNumber modFunc = new HashFunctionInNumber(31);
		modFunc.hashFunctionMod(elementsToAdd2, modFunc.theArray);
		modFunc.displayTheHashTable();
		modFunc.findKey("624");
		System.out.println();
		
		//increase size of hash table
		modFunc.increaseArraySize(60);
		System.out.println("Modulus Hash Table(size=61):");
		modFunc.displayTheHashTable();
		modFunc.findKey("624");
		System.out.println();
		
		//double hash table
		System.out.println("Double Hash Table(size=61):");
		HashFunctionInNumber doubleFunc = new HashFunctionInNumber(61);
		doubleFunc.doubleHashFunction(elementsToAdd2, doubleFunc.theArray);
		doubleFunc.displayTheHashTable();
		doubleFunc.findKeyDoubleHashed("624");
	}
	
	/*
	 * Simple Hash Function that puts values
	 * in the same index that matches their value
	 */
	public void hashFunctionSimplest(String[] stringsForArray, String[] theArray){
		
		for(int n=0; n<stringsForArray.length; n++){
			
			String newElementVal = stringsForArray[n];
			
			theArray[Integer.parseInt(newElementVal)] = newElementVal;
		}
	}
	
	/*
	 * Use mod function to insert item to hash table.(value mod arraySize)
	 * Hash table should be big enough to avoid collisions
	 */
	public void hashFunctionMod(String[] stringsForArray, String[] theArray) {
		
		for(int n=0; n<stringsForArray.length; n++){
			
			String newElementVal = stringsForArray[n];
			
			//create an index to store the value in by taking the modulus
			int arrayIndex = Integer.parseInt(newElementVal) % arraySize;
			
//			System.out.println("Modulus Index= " + arrayIndex + " for value "
//					+ newElementVal);
			
			while(theArray[arrayIndex] != "-1"){
				
				++arrayIndex;
				
//				System.out.println("Collision Try " + arrayIndex + " Instead");
				
				//if we get to the end of the array, go back to index 0
				arrayIndex %= arraySize;
			}
			
			theArray[arrayIndex] = newElementVal;
		}
	}
	
	//return the value stored in hash table
	public String findKey(String key){
		
		//find key original hash key
		int arrayIndexHash = Integer.parseInt(key) % arraySize;
		
		while(theArray[arrayIndexHash] != "-1"){
			
			if(theArray[arrayIndexHash] == key){
				
				//find the key and return it
				System.out.println(key + " was found in index " + arrayIndexHash);
				
				return theArray[arrayIndexHash];
			}
			
			++arrayIndexHash;
			
			arrayIndexHash %= arraySize;
			
		}
		
		//not find the key
		return null;
	}
	
	public void increaseArraySize(int minArraySize){
		
		//get a prime number bigger than the array requested
		int newArraySize = getNextPrimeNumber(minArraySize);
		
		//move the array into a bigger prime sized array
		moveOldArray(newArraySize);
		
	}
	
	public void moveOldArray(int newArraySize){
		
		//get an array with no empty spaces
		String[] clearArray = removeEmptySpacesInArray(theArray);
		
		//increase the size of array
		theArray = new String[newArraySize];
		
		arraySize = newArraySize;
		
		//fill array with "-1"
		fillArrayWithNeg1();
		
		//send values previously in "theArray" to larger array
		hashFunctionMod(clearArray, theArray);
	}
	
	//remove all empty spaces in the array
	public String[] removeEmptySpacesInArray(String[] arrayToClean){
		
		ArrayList<String> stringList = new ArrayList<String>();
		
		for(String item: arrayToClean){
			
			if(!item.equals("-1") && !item.equals("")){
				
				stringList.add(item);
			}
		}
		
		return stringList.toArray(new String[stringList.size()]);
	}
	
	//return the next prime number after the input
	public int getNextPrimeNumber(int number){
		
		for(int i=number; true; i++){
			
			if(isPrime(i)) return i;
		}
	}
	
	//check is prime?
	public boolean isPrime(int number){
		
		//eliminate the need to check versus even numbers
		if(number % 2 == 0){
			
			return false;
		}
		
		//check odd numbers
		for(int i=3; i*i<number; i+=2){
			
			if(number % i == 0) return false;
		}
		
		return true;
	}
	
	//use double hash to insert
	public void doubleHashFunction(String[] stringsForArray, String[] theArray){
		
		for(int n=0; n<stringsForArray.length; n++){
			
			String newElementVal = stringsForArray[n];
			
			int arrayIndex = Integer.parseInt(newElementVal) % arraySize;
			
			/*
			 * get the distance to skip the index after a collision occurs.
			 * it can help avoid clusters
			 */
			int stepDistance = 7 - (Integer.parseInt(newElementVal) % 7);
			
//			System.out.println("Modulus Index= " + arrayIndex + " for value "
//					+ newElementVal);
			
			while(theArray[arrayIndex] != "-1"){
				
				arrayIndex += stepDistance;
				
//				System.out.println("Collision Try " + arrayIndex + " Instead");
				System.out.println("step distance: " + stepDistance);
				
				//if we get to the end of the array, go back to index 0
				arrayIndex %= arraySize;
			}
			
			theArray[arrayIndex] = newElementVal;
		}
	}
	
	//return value in double hashed hash table
	public String findKeyDoubleHashed(String key){
		
		int arrayIndexHash = Integer.parseInt(key) % arraySize;
		
		//find the key original step distance
		int stepDistance = 7 - (Integer.parseInt(key) % 7);
		
		while(theArray[arrayIndexHash] != "-1"){
			
			if(theArray[arrayIndexHash] == key){
				
				//find the key and return it
				System.out.println(key + " was found in index " + arrayIndexHash);
				
				return theArray[arrayIndexHash];
			}
			
			arrayIndexHash += stepDistance;
			
			arrayIndexHash %= arraySize;
		}
		
		return null;
	}
	
	public HashFunctionInNumber(int size){
		
		arraySize = size;
		
		theArray = new String[size];
		
		fillArrayWithNeg1();
	}
	
	//full array with -1 for each empty space
	public void fillArrayWithNeg1() {
		
		Arrays.fill(theArray, "-1");
	}
	
	/*
	 * print the Hash Table
	 * when the value is "-1", ignore that item
	 * COPY from Derek Banas's tutorial
	 */
	public void displayTheHashTable() {

		int increment = 0;

		int numberOfRows = (arraySize / 10) + 1;

		for (int m = 0; m < numberOfRows; m++) {

			increment += 10;

			for (int n = 0; n < 71; n++)
				System.out.print("-");

			System.out.println();

			for (int n = increment - 10; n < increment; n++) {

				System.out.format("| %3s " + " ", n);

			}

			System.out.println("|");

			for (int n = 0; n < 71; n++)
				System.out.print("-");

			System.out.println();

			for (int n = increment - 10; n < increment; n++) {

				if (n >= arraySize)
					System.out.print("|      ");

				else if (theArray[n].equals("-1"))
					System.out.print("|      ");

				else
					System.out
							.print(String.format("| %3s " + " ", theArray[n]));

			}

			System.out.println("|");

			for (int n = 0; n < 71; n++)
				System.out.print("-");

			System.out.println();

		}
	}

}
