import java.util.Arrays;

/**
 * Print Tree
 * 
 * Indent & Spaces:
 * _______1
 * ___1_______1
 * _1___1___1___1
 * 1_1_1_1_1_1_1_1
 *
 * 1st Row Indent 7 Spaces 0
 * 2nd Row Indent 3 Spaces 7
 * 3rd Row Indent 1 Spaces 3
 * 4th Row Indent 0 Spaces 1
 * 
 * Not Dynamic Indent : -2^-n * (-16+2^n) (n>=1), used in printTree()
 * Dynamic Indent: 0.5 * (-2 + 2^n) (n>=1), used in printTreeWithDynamicIndent()
 * Spaces : start at 0 and then whatever Indent was
 * 
 * 
 * First Index Per Row:
 * 0
 * 1 2
 * 3 4 5 6
 * 7 8 9 10 11 12 13 14
 * First Index Per Row: 0.5 * (-2 + (Math.pow(2, iteration)))
 * 
 * 
 * Items Per Row:
 * 1, 2, 4, 8
 * Items Per Row: Math.pow(2, iteration - 1)
 * 
 * 
 * Max Index Per Row: indexToPrint + itemsPerRow
 * 
 * All the formula get from: www.wolframalpha.com
 * 
 * @author Alfred
 *
 */
public class PrintTree {

	private Data[] theHeap;
	
	private int itemsInArray = 0;
	
	private int maxSize;
	
	public PrintTree(int maxSize){
		
		this.maxSize = maxSize;
		
		theHeap = new Data[maxSize];
	}
	
	public void insert(int index, Data newData){
		
		theHeap[index] = newData;
		
		itemsInArray++;
	}
	
	/*
	 * fill the heap with random numbers
	 */
	public void generateFilledArray(int randomNum){
		
		Data randomData;
		
		for(int i=0; i<maxSize; i++){
			
			randomData = new Data((int)(Math.random()*randomNum) + 1);
			
			insert(i, randomData);
		}
	}
	
	/* 
	 * print tree without dynamic indent. 
	 * when rows greater than 4, the tree fail to print.
	 */
	public void printTree(int rows){
		
		//number of spaces between items in the tree
		int spaces = 0;
		
		int iteration = 1;
		
		while(iteration <= rows){
			
			int indent = (int) Math.abs(Math.pow(-2, -iteration) * 
					(-16 + Math.pow(2, iteration)));
			
			//first Index : .5 * (-2 + 2^n)
			int indexToPrint = (int) (0.5 * (-2 + Math.pow(2, iteration)));
			
			//Number of Items per Row : 2^(n - 1)
			int itemPerRow = (int) Math.pow(2, iteration - 1);
			
			int maxIndexToPrint = indexToPrint + itemPerRow;
			
			for(int i=0; i<indent; i++)
				System.out.print(" ");
			
			for(int j=indexToPrint; j<maxIndexToPrint; j++){
				
				System.out.print(theHeap[j].key);
				
				for(int k=0; k<spaces; k++)
					System.out.print(" ");
			}
			
			spaces = indent;
			
			iteration++;
			
			System.out.println();
			
		}
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
	
	public static void main(String[] args) {
		
		PrintTree theTree = new PrintTree(70);
		theTree.generateFilledArray(9);
		
		//print tree without dynamic indent 
		System.out.println("4 ROW TREE");
		theTree.printTree(4);
		System.out.println();
		
		//print tree with dynamic indent
		System.out.println("Dynamic Indent TREE");
		theTree.printTreeWithDynamicIndent(6);
	}
}

class Data{
	
	public int key;
	
	public Data(int key){
		
		this.key = key;
	}
}
