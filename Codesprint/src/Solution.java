import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Solution {


	public static void main (String args[]) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt (br.readLine());
		int i = 0;
		String input [];
		
		for (i=0; i<T; i++) {
			input = br.readLine().trim().split(" ");
			twos (Integer.parseInt(input[0]), Integer.parseInt(input[1]));
		}
		twos (-2147483638, 0);

	}
	
	public static void twos (int a, int b) {
		
		int ones = 0;
		int tmp = 0;
		
		if (a > b) {
			tmp = a;
			a = b;
			b = tmp;
		}
			
		int limit1 = a;
		int limit2 = b;
		int i;
		
		if (b < 0) {
			limit2 = (a*-1) - 1;
			limit1 = (b*-1) - 1;
			//System.out.println ("In: " + limit1 + " " + limit2);
			for (i=limit1; i<=limit2; i++) {
				ones += countOnes (i);
				//System.out.println (i + " " + ones);
			}
			//System.out.println ("out");
			ones = (32*(limit2-limit1+1)) - ones;
		}
		
		if (a < 0 && b >= 0) {
			
			limit2 = (a*-1) - 1;
			limit1 = 0;
			for (i=limit1; i<=limit2; i++) {
				ones += countOnes (i);
			}
			ones = (32*(limit2-limit1+1)) - ones;
			
			for (i=0; i<=b; i++)
				ones += countOnes (i);
		}
		
		if (a > 0){
			for (i=a; i<=b; i++)
				ones += countOnes(i);
		}
		
		System.out.println (ones);
		
	}
	
	
	public static int countOnes (int n) {

		  	n = n - ((n >> 1) & 033333333333)
		           - ((n >> 2) & 011111111111);
		   return ((n + (n >> 3)) & 030707070707) % 63;
		}

	
}

