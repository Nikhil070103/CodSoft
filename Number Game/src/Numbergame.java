import java.util.Random;
import java.util.Scanner;

public class Numbergame {

	public static void main(String args[]) {
		
		Scanner scanner = new Scanner(System.in);
		Random random = new Random();
		int randomNum = random.nextInt(100) + 1;
		int attempts = 5;
		int wonScore = 0;
		
		System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("I have chosen a number between 1 to 100.");
        System.out.println("You have " + attempts + " attempts to guess it.");
        
        while(attempts > 0) {
        	
        	System.out.println("Enter Guess Number:");
        	int guess = scanner.nextInt();
        	
        	
        	if(guess == randomNum) {
        		
        		System.out.println("This number is correct: " + guess);
        		wonScore++;
        		
        	}if(guess < randomNum) {
        		
        		System.out.println("This is low!");
        	}else {
        		
        		System.out.println("This is High!");
        	}
        	attempts--;

        	System.out.println("Remaining Attempts: "+attempts);
        }
        
        if(attempts == 0) {
        	
        	System.out.println("Finish Your Attempts. The Number was: "+ randomNum);
        }
		
	}
}
