import java.util.Arrays;

/**
 * Stack
 * 1.Used to complete a task
 * 2.Allow only a single item to be added or removed at a time （fast）
 * 3.Allow access to the last item inserted (Last In First Out)
 * @author Alfred
 *
 */
public class Stack {
	
	private String[] stackArray;
	private int stackSize;
	
	//set stack as empty
	private int topOfStack = -1;
	
	public Stack(int size){
		
		stackSize = size;
		
		stackArray = new String[size];
		
		/*
		 * assign "-1" to every value in the array
		 * to control what will be printed to screen
		 */
		Arrays.fill(stackArray,"-1");
	}
	
	//insert item to the top of stack
	public void push(String input){
		
		if(topOfStack+1 < stackSize){
			
			topOfStack ++;
			stackArray[topOfStack] = input;
		}else {
			
			System.out.println("The stack is full.");
		}
		
		displayTheStack();
		System.out.println("PUSH " + input 
				+ " was added to the stack.");
	}
	
	/*
	 * remove the top item of the stack and return this item
	 * this item is no longer in the stack but still in the memory
	 */
	public String pop(){
		
		if(topOfStack >= 0){
			String popValue = stackArray[topOfStack];
			
			stackArray[topOfStack] = "-1";
			topOfStack--;
			displayTheStack();
			System.out.println("POP "+popValue
					+" was removed from the stack.");
			return popValue;
		}else{
			
			System.out.println("The stack is empty.");
			return "-1";
		}
	}
	
	//return the item at the top of the stack
	public String peek(){
		
		displayTheStack();
		System.out.println("PEEK "+stackArray[topOfStack]
				+" is at the top of the stack");
		return stackArray[topOfStack];
	}
	
	//insert multiple items to the stack
	public void multiPush(String multiValues){
		
		String[] tempString = multiValues.split(" ");
		for(int i=0; i<tempString.length; i++){
			push(tempString[i]);
		}
	}
	
	//pop(remove) all the items in the stack
	public void popAll(){
		for(int i=topOfStack; i>=0; i--){
			pop();
		}
	}
	
	//print the items in the stack, then remove all
	public void displayPopAll(){
		
		String theReverse = "";
		for(int i=topOfStack; i>=0; i--){
			
			theReverse += stackArray[i];
		}
		System.out.println("The Reverse: "+theReverse);
		popAll();
	}
	
	/*
	 * print the stack
	 * when the value is "-1", ignore that item
	 * COPY from Derek Banas's tutorial
	 */
	public void displayTheStack(){
		
		for(int n = 0; n < 61; n++)System.out.print("-");
		
		System.out.println();
		
		for(int n = 0; n < stackSize; n++){
			
			System.out.format("| %2s "+ " ", n);
			
		}
		
		System.out.println("|");
		
		for(int n = 0; n < 61; n++)System.out.print("-");
		
		System.out.println();
		
		for(int n = 0; n < stackSize; n++){
			
			if(stackArray[n].equals("-1")) System.out.print("|     ");
			
			else System.out.print(String.format("| %2s "+ " ", stackArray[n]));
			
		}
		
		System.out.println("|");
		
		for(int n = 0; n < 61; n++)System.out.print("-");
		
		System.out.println();
	
	}
	
	public static void main(String[] args) {
		
		Stack newStack = new Stack(10);
		
		//insert single item
		newStack.push("1");
		
		//find the top item of stack and print it
		newStack.peek();
		
		//pop single item
		newStack.pop();

		//insert multiple items, split by " "
		newStack.multiPush("1 2 3 4 5 6 7");
		
		//remove all items in stack and print them
		newStack.displayPopAll();
		
	}
}
