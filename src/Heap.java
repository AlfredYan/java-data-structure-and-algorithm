import java.util.Arrays;

/**
 * Heap
 * A heap is kind of like a tree.
 * It is normally implemented as an array.
 * Every row is complete except the last low.
 * Parent keys are bigger than children.
 * The left child is not always less than the right.
 * Very fast at insertion and deletion.
 * Very slow at traversal and searching
 * 
 * @author Alfred
 *
 */
public class Heap {

	private TreeData[] theHeap;
	
	private int itemsInArray = 0;
	
	private int maxSize;
	
	public Heap(int maxSize){
		
		this.maxSize = maxSize;
		
		theHeap = new TreeData[maxSize];
	}
	
	public void insert(int index, TreeData newData){
		
		theHeap[index] = newData;
	}
	
	public void incrementTheArray(){
		
		itemsInArray++;
	}
	
	//remove and return the first item(root) from the heap
	public TreeData pop(){
		
		if(itemsInArray !=0){
			
			TreeData root = theHeap[0];
			
			/*
			 * decrement items in the array.
			 * save the last item in heap to the root.
			 */
			theHeap[0] = theHeap[--itemsInArray];
			
			heapTheArray(0);
			
			return root;
		}
		
		return null;
	}
	
	//move larger item to parent, and smaller item to child 
	public void heapTheArray(int index){
		
		int largestChild;
		
		TreeData root = theHeap[index];
		
		while(index < itemsInArray / 2){
			
			//get the index of left child
			int leftChild = 2 * index + 1;
			
			//get index of right child
			int rightChild = leftChild + 1;
			
			if(rightChild < itemsInArray 
					&& theHeap[leftChild].key < theHeap[rightChild].key){
				
				//right > left, save right child to largestChild
				largestChild = rightChild;
				
			}else{
				
				largestChild = leftChild;
			}
			
			if(root.key > theHeap[largestChild].key) break;
			
			//set value in largestChild to the top index
			theHeap[index] = theHeap[largestChild];
			
			index = largestChild;
			
//			System.out.println();
//			
//			printTreeWithDynamicIndent(4);
//			
//			System.out.println();
		}
		
		theHeap[index] = root;
	}
	
	/*
	 * print tree with dynamic indent, 
	 * to fix the mistake when rows greater than 4
	 */
	public void printTreeWithDynamicIndent(int rows){
		
		//number of spaces between items in the tree
		int spaces = 0;
		
		int iteration = 1;
		
		//generate all the indents that are needed depending on rows
		int[] indents = getIndentArray(rows);
		
		while(iteration <= rows){
			
			//first Index : .5 * (-2 + 2^n)
			int indexToPrint = (int) (0.5 * (-2 + Math.pow(2, iteration)));
			
			//Number of Items per Row : 2^(n - 1)
			int itemPerRow = (int) Math.pow(2, iteration - 1);
			
			int maxIndexToPrint = indexToPrint + itemPerRow;
			
			for(int i=0; i<indents[iteration-1]; i++)
				System.out.print(" ");
			
			for(int j=indexToPrint; j<maxIndexToPrint; j++){
				
				//do not print the indexes that not exist
				if(j < itemsInArray){
					
					//left padded with zeros up to 2 digits
					System.out.print(String.format("%02d", theHeap[j].key));
					
					for(int k=0; k<spaces; k++)
						System.out.print(" ");
				}
			}
			
			spaces = indents[iteration - 1];
			
			iteration++;
			
			System.out.println();
		}
	}
	
	/*
	 * cycle through the array and pop off each,
	 * the array goes from smallest to largest
	 */
	public void heapSort(){
		
		for(int k=itemsInArray - 1; k>=0; k--){
			
			TreeData largestNode = pop();
			
			insert(k, largestNode);
			
			System.out.println(Arrays.toString(theHeap));
		}
	}
	
	/*
	 * calculate each indent per row for tree.
	 * then reverse the order to go form biggest to smallest
	 */
	public int[] getIndentArray(int rows) {
		
		int[] indentArray = new int[rows];
		
		for(int i=0; i<rows; i++){
			
			indentArray[i] = (int) Math.abs(-2 + Math.pow(2, i + 1));
			
		}
		
		Arrays.sort(indentArray);
		
		indentArray = reverseArray(indentArray);
		
		return indentArray;
	}
	
	//order the values in the array from biggest to smallest
	public int[] reverseArray(int[] theArray){
		
		int leftIndex = 0;
		int rightIndex = theArray.length - 1;
		
		while(leftIndex < rightIndex){
			
			int temp = theArray[leftIndex];
			theArray[leftIndex] = theArray[rightIndex];
			theArray[rightIndex] = temp;
			
			leftIndex++;
			rightIndex--;
		}
		
		return theArray;
	}
	
	/*
	 * fill the heap with random numbers
	 */
	public void generateFilledArray(int randomNum){
		
		TreeData randomData;
		
		for(int i=0; i<maxSize; i++){
			
			randomData = new TreeData((int)(Math.random()*randomNum) + 1);
			
			insert(i, randomData);
			
			incrementTheArray();
		}
	}

	public static void main(String[] args) {
		
		Heap newHeap = new Heap(7);
		newHeap.generateFilledArray(90);
		
		System.out.println("Original Array");
		System.out.println(Arrays.toString(newHeap.theHeap));
		System.out.println();
		newHeap.printTreeWithDynamicIndent(4);
		System.out.println();
		
		//heap every parent, ignore the last row
		for(int i=newHeap.maxSize / 2 - 1; i>=0; i--){
			
			newHeap.heapTheArray(i);
		}
		
		System.out.println("Heaped Array");
		System.out.println(Arrays.toString(newHeap.theHeap));
		System.out.println();
		newHeap.printTreeWithDynamicIndent(4);
		
//		System.out.println("Heaped Array After pop");
//		newHeap.pop();
//		System.out.println(Arrays.toString(newHeap.theHeap));
//		System.out.println();
//		newHeap.printTreeWithDynamicIndent(4);
//		System.out.println();
		
		System.out.println();
		newHeap.heapSort();
		System.out.println("Heap Sorted");
		System.out.println(Arrays.toString(newHeap.theHeap));
		
	}
}

class TreeData{
	
	public int key;
	
	public TreeData(int key){
		
		this.key = key;
	}
	
	public String toString(){
		
		return Integer.toString(key);
	}
}
