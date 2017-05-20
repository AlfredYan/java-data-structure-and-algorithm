import java.util.Scanner;

/**
 * Hash Function(Hash in String):
 * Cycle through the String and run: (hashValue*27+charCode)%arraySize.
 * Then add the result to each other and hash until complete.
 * 
 * Separate Chaining
 * Instead of adding an item to the next index,
 * store a list in each index of the array
 * 
 * @author Alfred
 *
 */
public class HashFunctionInString {

	WordList[] theArray;
	
	int arraySize;
	
	int itemInArray = 0;
	
	public String[][] elementsToAdd = {
			{ "ace", "Very good" },
			{ "act", "Take action" },
			{ "add", "Join (something) to something else" },
			{ "age", "Grow old" },
			{ "ago", "Before the present" },
			{ "aid", "Help, assist, or support" },
			{ "aim", "Point or direct" },
			{ "air", "Invisible gaseous substance" },
			{ "all", "Used to refer to the whole quantity" },
			{ "amp",
					"Unit of measure for the strength of an electrical current" },
			{ "and", "Used to connect words" }, { "ant", "A small insect" },
			{ "any", "Used to refer to one or some of a thing" },
			{ "ape", "A large primate" },
			{ "apt", "Appropriate or suitable in the circumstances" },
			{ "arc", "A part of the circumference of a curve" },
			{ "are", "Unit of measure, equal to 100 square meters" },
			{ "ark", "The ship built by Noah" },
			{ "arm", "Two upper limbs of the human body" },
			{ "art", "Expression or application of human creative skill" },
			{ "ash", "Powdery residue left after the burning" },
			{ "ask", "Say something in order to obtain information" },
			{ "asp", "Small southern European viper" },
			{ "ass", "Hoofed mammal" },
			{ "ate", "To put (food) into the mouth and swallow it" },
			{ "atm", "Unit of pressure" },
			{ "awe", "A feeling of reverential respect" },
			{ "axe", "Edge tool with a heavy bladed head" },
			{ "aye", "An affirmative answer" } };

	//hash string to values
	public int stringHashFunction(String wordToHash){
		
		int hashKeyValue = 0;
		
		for(int i=0; i<wordToHash.length(); i++){
			
			//subtract 96 to make letters start at 1.(a=97)
			int charCode = wordToHash.charAt(i) - 96;
			
			int hKVTemp = hashKeyValue;
			
			//calculate the hash key using the 26 letters + blank
			hashKeyValue = (hashKeyValue * 27 + charCode) % arraySize;
			
			System.out.println("Hash Key Value " + hKVTemp
					+ " * 27 + Character Code " + charCode + " % arraySize "
					+ arraySize + " = " + hashKeyValue);
		}
		
		System.out.println();
		
		return hashKeyValue;
	}
	
	public HashFunctionInString(int size){
		
		arraySize = size;
		
		theArray = new WordList[size];
		
		for(int i=0; i<arraySize; i++){
			
			theArray[i] = new WordList();
		}
		
		addTheArray(elementsToAdd);
	}
	
	public Word find(String wordToFind){
		
		int hashKey = stringHashFunction(wordToFind);
		
		Word theWord = theArray[hashKey].find(hashKey, wordToFind);
		
		return theWord;
	}
	
	public void addTheArray(String[][] elementsToAdd){
		
		for(int i=0; i<elementsToAdd.length; i++){
			
			String word = elementsToAdd[i][0];
			String definition = elementsToAdd[i][1];
			
			Word newWord = new Word(word, definition);
			
			insert(newWord);
		}
	}
	
	public void insert(Word newWord){
		
		String WordToHash = newWord.theWord;
		
		//calculate the hashKey for the word
		int hashKey = stringHashFunction(WordToHash);
		
	}
	
	public void displayTheArray(){
		
		for(int i=0; i<arraySize; i++){
			
			System.out.println("theArray Index " + i);
			theArray[i].displayWordList();
		}
	}
	
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		HashFunctionInString wordHashTable = new HashFunctionInString(11);
		
		String wordLookUp = "a";
		
		//keep retrieve requests until "x" is entered
		while(!wordLookUp.equalsIgnoreCase("x")){
			
			System.out.println(": ");
			
			wordLookUp = input.nextLine();
			
			System.out.println(wordHashTable.find(wordLookUp));
		}
		
		wordHashTable.displayTheArray();
	}
}

class Word{
	
	public String theWord;
	public String definition;
	
	public int key;
	
	//reference to next word added in the WordList
	public Word next;
	
	public Word(String theWord, String definition){
		
		this.theWord = theWord;
		this.definition = definition;
	}
	
	public String toString(){
		
		return theWord + " : " + definition;
	}
}

class WordList{
	
	public Word firstWord = null;
	
	public void insert(Word newWord, int hashKey){
		
		Word previous = null;
		Word current = firstWord;
		
		newWord.key = hashKey;
		
		while(current != null && newWord.key > current.key){
			
			previous = current;
			
			current = current.next;
		}
		
		if(previous == null){
			
			firstWord = newWord;
		}else{
			
			previous.next = newWord;
		}
		
		newWord.next = current;
	}
	
	public void displayWordList(){
		
		Word current = firstWord;
		
		while(current != null){
			
			System.out.println(current);
			current = current.next;
		}
	}
	
	public Word find(int hashKey, String wordToFind){
		
		Word current = firstWord;
		
		/* 
		 * Search for key, and stop searching when
		 * hashKey < what we are searching.
		 * Because the list is sorted by hashKey.
		 */
		while(current != null && current.key <= hashKey){
			
			if(current.theWord.equals(wordToFind))
				return current;
			
			current = current.next;
		}
		
		return null;
	}
	
//	public static void main(String[] args) {
//		
//		Scanner input = new Scanner(System.in);
//		
//		HashFunctionInString wordHashTable = new HashFunctionInString(11);
//		
//		String wordLookUp = "a";
//		
//		//keep retrieve requests until "x" is entered
//		while(!wordLookUp.equalsIgnoreCase("x")){
//			
//			System.out.println(": ");
//			
//			wordLookUp = input.nextLine();
//			
//			System.out.println(wordHashTable.find(wordLookUp));
//		}
//		
//		wordHashTable.displayTheArray();
//	}
}