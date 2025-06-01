import java.util.Scanner;

public class StudentMarksCalculator {

	public static void main(String args[]) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter: how many Subject?");
		int numSubject = scanner.nextInt();
		
		int totalmarks = 0;
		for(int i = 1; i <= numSubject; i++) {
			System.out.println("Enter Subject marks "+ i);
			int marks = scanner.nextInt();
			totalmarks += marks;
		}
		
		System.out.println("Enter Total number of Subjects");
		int totalnum = scanner.nextInt();
		
		double percentage = (double) totalmarks / totalnum * 100;
		
		System.out.println("Total Marks:" + totalmarks );
		System.out.println("Percentage :" + percentage + "%");
		
		if (percentage >= 90) {
            System.out.println("Grade: A+");
        } else if (percentage >= 80) {
        	System.out.println("Grade: A");
        } else if (percentage >= 70) {
        	System.out.println("Grade: B");
        } else if (percentage >= 60) {
        	System.out.println("Grade: C");
        } else if (percentage >= 50) {
        	System.out.println("Grade: D");
        } else {
        	System.out.println("Grade: F");
        }
	}
}
