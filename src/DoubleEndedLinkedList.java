/**
 * Double Ended LinkedList
 * It has a reference to the first and last link in the list
 * which means it can go both forward and backward
 * 
 * @author Alfred
 *
 */
public class DoubleEndedLinkedList {

	//the first link in the list
	Neighbor firstLink;
	
	//the first link in the list
	Neighbor lastLink;
	
	//insert an item to the first of list
	public void insertInFirstPosition(String homeOwnerName, int houseNumber){
		
		Neighbor theNewLink = new Neighbor(homeOwnerName, houseNumber);
		
		//if no items in the list, add the new link to "lastLink"
		if(isEmpty()){
			
			lastLink = theNewLink;
			
		}else {
			
			/* if "firstLink" exist
			 * assign the new link to the "previous" of "firstLink"
			 */
			firstLink.previous = theNewLink;
		}
		
		//assign the "firstLink" to the "next" of the new link
		theNewLink.next = firstLink;
		
		//assign the new link to the "firstLink"
		firstLink = theNewLink;
	}
	
	//insert an item to the rear of list
	public void insertInLastPosition(String homeOwnerName, int houseNumber){
		
		Neighbor theNewLink = new Neighbor(homeOwnerName, houseNumber);
		
		//if no items in the list, add the new link to "firstLink"
		if(isEmpty()){
			
			firstLink = theNewLink;
			
		}else {
			
			//assign the new link to the "next" of the "lastLink"
			lastLink.next = theNewLink;
			
			//assign "lastLink" to the "previous" of the new link
			theNewLink.previous = lastLink;
		}
		
		//assign the new link to "lastLink"
		lastLink = theNewLink;
	}
	
	//insert an item after a special key to the list
	public boolean insertAfterKey(String homeOwnerName, int houseNumber, int key){
		
		Neighbor theNewLink = new Neighbor(homeOwnerName, houseNumber);
		
		//start search at "firstLink"
		Neighbor currentNeighor = firstLink;
		
		//while do not match the key, keep searching
		while(currentNeighor.houseNumber != key){
			
			currentNeighor.next = currentNeighor;
			
			//do not find the key in list, return false
			if(currentNeighor == null){
				return false;
			}
		}
		
		if(currentNeighor == lastLink){
			
			/*
			 * if the match is the last Neighbor(link) in list
			 * assign the new link to the "lastLink"
			 */
			theNewLink.next = null;
			lastLink = theNewLink;
		}else{
			
			/*
			 * if the match is not the last link
			 * assign the matched link to "next" of the new link
			 * assign the new link to the "previous" of matched link's next link
			 */
			theNewLink.next = currentNeighor;
			currentNeighor.next.previous = theNewLink;
		}
		
		theNewLink.previous = currentNeighor;
		currentNeighor.next = theNewLink;
		
		return true;
	}
	
	//insert an item in ASC(not a double ended linkedlist)
	public void insertInOrder(String homeOwnerName, int houseNumber) {
		
		Neighbor theNewLink = new Neighbor(homeOwnerName, houseNumber);
		
		Neighbor previousLink = null;
		Neighbor currentLink = firstLink; 
		
		while((currentLink != null) && (houseNumber > currentLink.houseNumber)){
			
			previousLink = currentLink;
			currentLink = currentLink.next;
			
		}
		
		if(previousLink == null){
			
			firstLink = theNewLink;
			
		}else {
			
			previousLink.next = theNewLink;
			
		}
		
		theNewLink.next = currentLink;
	}
	
	//return true if doublueEndedLinkList is empty
	public boolean isEmpty(){
		
		return (firstLink == null);
	}
	
	public void display(){
		
		Neighbor theLink = firstLink;
		
		while(theLink != null){
			
			theLink.display();
			theLink = theLink.next;
			System.out.println();
			
		}
	}
	
	public static void main(String[] args) {
		
		DoubleEndedLinkedList theLinkedList = new DoubleEndedLinkedList();
		
		theLinkedList.insertInFirstPosition("Smith", 3);
		theLinkedList.insertAfterKey("Alfred", 5, 3);
		theLinkedList.insertInFirstPosition("Bob", 2);
		theLinkedList.insertInFirstPosition("Tom", 1);
		theLinkedList.insertInLastPosition("Jack", 4);
		
		theLinkedList.display();
		
//		System.out.println("First Link: ");
//		theLinkedList.firstLink.display();
//		System.out.println();
//		
//		System.out.println("Last Link: ");
//		theLinkedList.lastLink.display();
//		System.out.println();
		
		NeighborIterator neighbors = new NeighborIterator(theLinkedList);
		
		neighbors.currentNeighbor.display();
		System.out.println();
		
		System.out.println(neighbors.hasNext());
		System.out.println();
		
		neighbors.remove();
		
		neighbors.currentNeighbor.display();
	}
}
