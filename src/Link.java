import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

/**
 * Link
 * It is an object
 * the item in the LinkList
 * @author Alfred
 *
 */
public class Link {

	public String bookName;
	public int millionsSold;
	
	/*
	 * reference to the next link made in the LinkList
	 * initial "next" to null until it connects to another link 
	 */
	public Link next;
	
	public Link(String bookName, int millionsSold){
		
		this.bookName = bookName;
		this.millionsSold = millionsSold;
	}
	
	public void display(){
		System.out.println(bookName + ": "
				+ millionsSold + ",000,000 Sold");
	}
	
	public String toString(){
		return bookName;
	}
	
	public static void main(String[] args) {
		
		LinkList thelinkedList = new LinkList();
		
		System.out.println("Strat insert links and display them...");
		System.out.println();
		thelinkedList.insertFirstLink("BOOK ONE", 500);
		thelinkedList.insertFirstLink("BOOK TWO", 200);
		thelinkedList.insertFirstLink("BOOK THREE", 300);
		thelinkedList.insertFirstLink("BOOK FOUR", 100);
		
		thelinkedList.display();
		System.out.println("End insert links and display them...");
		System.out.println();
		
		System.out.println(thelinkedList.find("BOOK TWO").bookName
				+" was found");
		
		System.out.println();
		System.out.println("Value of firstLink is: "+thelinkedList.firstLink);
		System.out.println();
		
		thelinkedList.removeLink("BOOK TWO");
		
		System.out.println();
		System.out.println("BOOK THREE was removed");
		System.out.println();
		
		thelinkedList.display();
		
	}
}
