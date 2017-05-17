/**
 * Iterator
 * It provides an easy way 
 * to cycle through all the items in the Linkedlist
 * 
 * @author Alfred
 *
 */
public class NeighborIterator {

	Neighbor currentNeighbor;
	Neighbor previousNeighbor;
	
	DoubleEndedLinkedList theNeighbors;
	
	public NeighborIterator(DoubleEndedLinkedList theNeighbors){
		
		this.theNeighbors = theNeighbors;
		
		currentNeighbor = theNeighbors.firstLink;
		previousNeighbor = theNeighbors.lastLink;
		
		System.out.println("currentNeighbor: " + currentNeighbor);
		System.out.println("previousNeighbor: " + previousNeighbor);
	}
	
	//if has next item, return true 
	public boolean hasNext() {
		
		if(currentNeighbor.next != null){
			
			return true;
		}
		
		return false;
	}
	
	//return next item
	public Neighbor next(){
		
		if(hasNext()){
			
			previousNeighbor = currentNeighbor;
			currentNeighbor = currentNeighbor.next;
			
			return currentNeighbor;
		}
		
		return null;
	}
	
	public void remove(){
		
		//if at the beginning of the list
		if(previousNeighbor == null){
			
			theNeighbors.firstLink = currentNeighbor.next;
			
		}else{
			
			previousNeighbor.next = currentNeighbor.next;
			
			//if at the end of the list
			if(currentNeighbor.next == null){
				
				currentNeighbor = theNeighbors.firstLink;
				previousNeighbor = null;
			}else{
				
				currentNeighbor = currentNeighbor.next;
			}
		}
	}
}
