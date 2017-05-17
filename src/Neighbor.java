/**
 * Neighbor
 * the item in the DoubleEndedLinkedList
 * @author Alfred
 *
 */
public class Neighbor {

	public String homeOwnerName;
	public int houseNumber;
	
	//reference to the next Neighbor(link) which is inserted before this
	public Neighbor next;
	
	//reference to the previous Neighbor(link) which is insert after this
	public Neighbor previous;
	
	public Neighbor(String homeOwnerName, int houseNumber){
		
		this.homeOwnerName = homeOwnerName;
		this.houseNumber = houseNumber;
	}
	
	public void display(){
		
		System.out.println(homeOwnerName + ": NO." 
				+ houseNumber);
		System.out.println("Previous Link: "+ previous);
		System.out.println("Next Link: "+ next);
		
	}
	
	public String toString(){
		
		return homeOwnerName;
	}
}
