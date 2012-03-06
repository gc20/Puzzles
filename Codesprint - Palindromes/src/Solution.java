import java.util.Vector;


public class Solution {

	static int len;
	static int halflen;
	static int target = 48;
	static int count = 1;
	static int count2 = 0;
	static Vector <pallObject> vect = new Vector <pallObject> ();

	
	public static void main (String args[]) {
		
		String s = "cbaabbb";
		len = s.length();
		halflen = len/2;
		recurse (s, 0);
		
		int countAgain = 0;
		for (int j =2; j<7; j++) {
			countAgain = 0;
			for (int i = 0; i<vect.size(); i++) {
				if (vect.elementAt(i).degree == j)
				{
					//System.out.println (vect.elementAt(i).degree + " " + vect.elementAt(i).s);
					countAgain++;
				}
			}
			System.out.println (j + " " + countAgain);
		}
		//System.out.println (vect.size());
		//System.out.println (count2);
		
	}
	
	public static void recurse (String s, int degree) {
		
		if (degree == 5)
			return;
		
		if (degree == 2)
			count2++;
		
		if (isPallindrome(s))
		{
			vect.add(new pallObject(s, degree));
			//System.out.println (s + "\t" + degree + "\t" + count++);
			target--;
			return;
		}
		//System.out.println (target);
		
		int i, j;
		for (i=0; i<len; i++) {
			for (j=(i+1); j<len; j++) {
				//if (degree == 0)
					//System.out.println (degree + s + "\t" + s.substring(0,i) + s.charAt(j) + s.substring(i+1, j) + s.charAt(i) + s.substring(j+1));
				recurse (s.substring(0,i) + s.charAt(j) + s.substring(i+1, j) + s.charAt(i) + s.substring(j+1) , (degree+1));
			}
		}
		
	}
	
	public static boolean isPallindrome(String s) {
		
		for (int i =0; i<len; i++) {
			if (s.charAt(i) != s.charAt(len-(i+1)))
				return false;
		}
		return true;
	}
	
}
