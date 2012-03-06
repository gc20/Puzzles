package qnc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Vector;

public class qnc 
{
	public static void main (String args [])
	{
		try
		{
			int i, N;
			BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
			Vector vAns = new Vector <Integer> ();
			String entry = null;
		
			N = Integer.parseInt (br.readLine());
			System.out.println (N);
			
			for (i=0; i<N; i++)
			{
				// Obtain and parse input
				entry = br.readLine().trim();
				String StruserEntry [] = entry.split(" ");
				int userEntry [] = new int [StruserEntry.length];
				for (int k=0; k<StruserEntry.length; k++)
					userEntry[k] = Integer.parseInt (StruserEntry[k]);
				
				// Add logic
				
			}
		} // End Try
		catch (Exception e)
		{System.out.println ("Oh no. Exception Thown!");}
	} // End Main
} // End Class
