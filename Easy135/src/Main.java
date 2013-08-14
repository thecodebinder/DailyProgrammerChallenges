import java.util.Random;
import java.util.Scanner;


public class Main {


	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Random rand = new Random();
		int[] nums = new int[4];
		String[] ops = new String[3];
		int answer;
		boolean quit = false;
		
		int min, max;		
		min = in.nextInt();
		max = in.nextInt();
		
		while (quit == false) {
			// get the numbers
			for (int i = 0; i < 4; i++) {
				nums[i] = rand.nextInt(max - min) + min;
			}
			// get the operations and calculate answer
			answer = nums[0];
			for (int i = 0; i < ops.length; i++) {
				int r = rand.nextInt(2);
				switch (r) {
				case 0:
					answer += nums[i + 1];
					ops[i] = "+";
					break;
				case 1:
					answer -= nums[i + 1];
					ops[i] = "-";
					break;
				case 2:
					answer *= nums[i + 1];
					ops[i] = "*";
					break;
				default:
					break;
				}
			}
			//print the expression
			for (int i = 0; i < 3; i++) {
				System.out.print(nums[i] + " " + ops[i] + " ");
			}
			System.out.println(nums[3]);
			// get the users answer
			String userAnswer = in.next();
			// check to see if quitting
			if (userAnswer.equals("q") || userAnswer.equals("Q")){
				quit = true;
			}else{
				// check to see if the answer is correct
				while (Integer.parseInt(userAnswer) != answer) {
					System.out.println("Incorrect...");
					// reprint the expression
					for (int i = 0; i < 3; i++) {
						System.out.print(nums[i] + " " + ops[i] + " ");
					}
					System.out.println(nums[3]);
					// get new input
					userAnswer = in.next();
				}
				System.out.println("Correct");
			}
		}
		
		
	}
}
