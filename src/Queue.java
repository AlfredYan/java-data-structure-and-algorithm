import java.util.Arrays;

/**
 * Queue (like a line of people)
 * 1.Used to complete a task
 * 2.Allow only a single item to be added or removed at a time （fast）
 * 3.Allow access to the first item inserted (First In First Out)
 * @author Alfred
 *
 */
public class Queue {

	private String[] queueArray;
	private int queueSize;
	
	/*
	 * set queue as empty
	 * front: the first item of the queue,
	 * rear: the last item of the queue,
	 */
	private int front, rear, numberOfItems = 0;
	
	public Queue(int size){
		
		queueSize = size;
		queueArray = new String[size];
		
		/*
		 * Initial Queue:
		 * assign "-1" to every value in the array
		 * to control what will be printed to screen
		 */
		Arrays.fill(queueArray, "-1");
	}
	
	//insert item to the rear of the queue
	public void insert(String input){
		
		if(numberOfItems + 1 < queueSize){
			
			queueArray[rear] = input;
			rear++;
			numberOfItems++;
			
			System.out.println("INSERT "+input
					+" was added to the queue");
			
		}else{
			System.out.println("The queue is full");
		}
	}
	
	/*
	 * insert item to priority queue
	 * the items in priority queue is ordered form high to low
	 */
	public void priorityInsert(String input){
		
		//item is default inserted at rear of queue
		int indexToInsert = rear;
		
		if(numberOfItems == 0){
			
			insert(input);
			
		}else{
			
			for(int i=numberOfItems-1; i>=0; i--){
				
				/* 
				 * if input greater than the items in queue,
				 * find the appropriate index to insert,
				 * in order to get a DESC
				 */
				if(Integer.parseInt(input) > Integer.parseInt(queueArray[i])){
					
					queueArray[i+1] = queueArray[i];
					
					indexToInsert = i;
				
				}else{
					
					break;
				}	
			}
			
			queueArray[indexToInsert] = input;
			rear++;
			numberOfItems++;
		}
		
	}
	
	/*
	 * remove the first item from queue
	 * the item is not in the queue, but still in the memory
	 */
	public void remove(){
		
		if(numberOfItems > 0){
			
			System.out.println("REMOVE "+queueArray[front]
					+" was removed from the queue");
			
			queueArray[front] = "-1";
			front++;
			numberOfItems--;
			
		}else{
			
		}
	}
	
	//print the front item of queue
	public void peek() {
		System.out.println("The first item is "+queueArray[front]);
	}
	
	/*
	 * print the queue
	 * when the value is "-1", ignore that item
	 * COPY from Derek Banas's tutorial
	 */
	public void displayTheQueue(){
		
		for(int n = 0; n < 61; n++)System.out.print("-");
		
		System.out.println();
		
		for(int n = 0; n < queueSize; n++){
			
			System.out.format("| %2s "+ " ", n);
			
		}
		
		System.out.println("|");
		
		for(int n = 0; n < 61; n++)System.out.print("-");
		
		System.out.println();
		
		for(int n = 0; n < queueSize; n++){
			
			
			if(queueArray[n].equals("-1")) System.out.print("|     ");
			
			else System.out.print(String.format("| %2s "+ " ", queueArray[n]));
			
		}
		
		System.out.println("|");
		
		for(int n = 0; n < 61; n++)System.out.print("-");
		
		System.out.println();
		
		// Number of spaces to put before the F
		
		int spacesBeforeFront = 3*(2*(front+1)-1);
		
		for(int k = 1; k < spacesBeforeFront; k++)System.out.print(" ");
		
		System.out.print("F");
		
		// Number of spaces to put before the R
		
		int spacesBeforeRear = (2*(3*rear)-1) - (spacesBeforeFront);
		
		for(int l = 0; l < spacesBeforeRear; l++)System.out.print(" ");
		
		System.out.print("R");
		
		System.out.println("\n");
	
	}
	
	public static void main(String[] args) {
		
		Queue newQueue = new Queue(10);
		
		newQueue.priorityInsert("10");
		newQueue.priorityInsert("25");
		newQueue.priorityInsert("10");
		newQueue.priorityInsert("30");
		newQueue.priorityInsert("5");
		
		newQueue.displayTheQueue();
		
		newQueue.remove();
		
		newQueue.displayTheQueue();

		newQueue.peek();
	}

}
