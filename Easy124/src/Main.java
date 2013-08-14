import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

/*

6
F 2 3
B 1 2
D 6 5
C 6 7
E 5 4
A 3 4

B F A E D C 
 */


public class Main {

	public static void main(String[] args) {
		TreeMap<Integer, String> edgeMap = new TreeMap<>();
		Scanner in = new Scanner(System.in);
		String edgeName;
		int num;
		
		num = Integer.parseInt(in.nextLine());
		for(int i = 0; i<num;i++){
			edgeName = in.next();
			edgeMap.put(Math.min(in.nextInt(), in.nextInt()), edgeName);
		}
		
		in.close();
		for(Entry<Integer, String> entry : edgeMap.entrySet()){
			System.out.print(entry.getValue() + " ");
		}
		System.out.println();
		
	}

}
