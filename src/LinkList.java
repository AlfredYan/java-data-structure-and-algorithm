/**
 * LinkList
 * Each link has a reference to the next link(which added before it to the linklist)
 * Linklist has only a reference to the last link added to it
 * @author Alfred
 *
 */
public class LinkList {

	//the latest(last) link added to the linklist
	public Link firstLink;
	
	//initial "firstLink" to null
	public LinkList(){
		
		firstLink = null;
	}
	
	//return true when LinkList is empty  
	public boolean isEmpty(){
		return(firstLink == null);
	}
	
	//insert item to the linklist
	public void insertFirstLink(String bookName, int millionsSold){
		
		Link newLink = new Link(bookName, millionsSold);
		
		/*
		 * reference to the previous link
		 * assign firstLink(previous link) to the "next" of "newLink"
		 */
		newLink.next = firstLink;
		
		//assign the latest link to the "firstLink"
		firstLink = newLink;
	}
	
	//remove and return the firstlink
	public Link removeFirstLink(){
		
		Link referenceLink = firstLink;
		
		if(!(isEmpty())){
			
			//assign current firstLink's "next" to the new firstLink
			firstLink = firstLink.next;
		
		}else{
			
			System.out.println("Empty linklist");
		}
		
		return referenceLink;
		
	}
	
	//print all the links
	public void display(){
		
		Link theLink = firstLink;
		
		/*
		 * start at the reference stored in firstLink
		 * keep getting the reference stored in the "next"
		 * until "next" returns null
		 */
		while(theLink != null){
			
			theLink.display();
			
			System.out.println("Next link: "+theLink.next);
			
			theLink = theLink.next;
			
			System.out.println();
		}
	}
	
	/*
	 * find the link by bookName
	 * check data for the firstLink reference stored
	 * if don't match,
	 * continue searching every reference in the "next" 
	 */
	public Link find(String bookName) {
		
		Link theLink = firstLink;
		
		if(!(isEmpty())){
			
			while(theLink.bookName != bookName){
				
				if(theLink.next == null){
					
					return null;
				}else{
					
					theLink = theLink.next;
				}
			}
		}else{
			
			System.out.println("Empty linklist");
		}
		
		return theLink;
	}
	
	//remove link by book name
	public Link removeLink(String bookName){
		
		Link currentLink = firstLink;
		Link previousLink = firstLink;
		
		while(currentLink.bookName != bookName){
			
			if(currentLink.next == null){
				
				//do not match the link so leave the method
				return null;
			
			}else{
				
				//continue check the next link in the list
				previousLink = currentLink;
				
				currentLink = currentLink.next;
			}
		}
		
		if(currentLink == firstLink){
			
			/*
			 * the removed link is firstLink
			 * call removeFirstLink()
			 */
			return removeFirstLink();
		
		}else{
			
			System.out.println("Find a match: ");
			System.out.println("CurrentLink: " + currentLink);
			System.out.println("previousLink: " + previousLink);
			
			/*
			 * match in a link other than the first link
			 * 
			 * assign the value of next for the link you want to delete
			 * TO the previous link's "next" 
			 */
			previousLink.next = currentLink.next;
			
			return currentLink;
		}
		
	}
}
