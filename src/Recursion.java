/**
 * Recursion
 * A method that calls itself
 * Must have a condition that leads to the method no longer making 
 * another method call on itself
 * 
 * example with "Triangular Number" & "Factorial"
 * Triangular number: it can be visualized as triangles.
 * 					  equals itself plus every number before it to 1.
 * Factorial: equals itself multiplies every number before it to 1.
 * @author Alfred
 *
 */
public class Recursion {

	//Calculate triangular number without recursion
	public int getTriagularNum(int number){
		
		int triagularNum = 0;
		
		while(number > 0){
			
			triagularNum = triagularNum + number;
			number--;
			
		}
		
		return triagularNum;
	}
	
	//Calculate triangular number with recursion
	public int getTriagularNumR(int number){
		
		System.out.println("Method " + number);
		
		if(number == 1){
			
			System.out.println("Return 1");
			return 1;
			
		}else{
			
			int result = number + getTriagularNumR(number - 1);
			System.out.println("Return " + result + " : " + number
					+ " + getTriagularNumR(" + number + " - 1)");
			return result;	
		}
	}
	
	public int getFactorial(int number){
		
		System.out.println("Method "+number);
		
		if(number == 1){
			
			System.out.println("Return 1");
			return 1;
		
		}else{
			
			int result = number * getFactorial(number - 1);
			System.out.println("Return " + result + " : " + number
					+ " + getFactorial(" + number + " - 1)");
			return result;
			
		}
		
	}
	
	/*
	 *  USED TO DEMONSTRATE TRIANGULAR NUMBERS --------------------
	 *  Draws the number of squares that are passed in horizontally 
	 *  COPY from Derek Banas's tutorial
	 */
	public void drawSquares(int howManySquares){
		
		for(int i = 0; i < howManySquares; i++) System.out.print(" --  ");
		
		System.out.println();
			
		for(int i = 0; i < howManySquares; i++) System.out.print("|" + howManySquares + " | ");
		
		System.out.println();
			
		for(int i = 0; i < howManySquares; i++) System.out.print(" --  ");
			
		System.out.println("\n");
		
	}
	
	// Outputs the number of squares to print to represent a triangle
	public void calculateSquaresToPrint(int number){
		
		for(int i = 1; i <= number; i++){
			
			for(int j = 1; j < i; j++){
			
				drawSquares(j);

			}
			
			System.out.println("Triangular Number: " + calcTriangularNum(i));
			
		}
	}
	
	public double calcTriangularNum(int number){
		
		return .5 * number * (1 + number);
		
	}
	
	public static void main(String[] args) {
		
		Recursion recursionTool = new Recursion();
		
//		recursionTool.calculateSquaresToPrint(10);
//		System.out.println();
		
		System.out.println("GET TRIANGULAR NUMBER");
		System.out.println("Recursion Triangular Number: " 
				+ recursionTool.getTriagularNumR(6));
		System.out.println();

		System.out.println("GET FACTORIAL");
		System.out.println("Factorial: " + recursionTool.getFactorial(6));
		
	}
}
