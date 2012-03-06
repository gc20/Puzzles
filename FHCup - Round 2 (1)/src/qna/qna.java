package qna;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Vector;

public class qna 
{
	public static void main (String args [])
	{
		try
		{
			int i, N;
			BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
			Vector vAns = new Vector <Integer> ();
			  
			N = Integer.parseInt (br.readLine());
			
			for (i=0; i<N; i++)
			{
				int ans = 0;
				Vector<Integer> aiSet = new Vector <Integer> ();
				
				String str = br.readLine().trim();
				long p = Long.parseLong (str.substring(0, str.indexOf(" ")));
				long l = Long.parseLong (str.substring(str.indexOf(" ") + 1, str.length()));
				
				
				// Obtain and parse input
				String entry = br.readLine().trim();
				String StruserEntry [] = entry.split(" ");
				long userEntry [] = new long [StruserEntry.length];
				for (int k=0; k<StruserEntry.length; k++)
					userEntry[k] = Integer.parseInt (StruserEntry[k]);
				
				// Obtain and parse input again
				String entry2 = br.readLine().trim();
				String StruserEntry2 [] = entry2.split(" ");
				long userEntry2 [] = new long [StruserEntry2.length];
				for (int k=0; k<StruserEntry2.length; k++)
					userEntry2[k] = Integer.parseInt (StruserEntry2[k]);
				
				long a_prev_prev = userEntry [1];
				aiSet.add((int) a_prev_prev);
				long a_prev = userEntry [2];
				aiSet.add((int) a_prev);
				int count = 0;
				
				for (int j=3; j<=userEntry[0]; j++)
				{
					int ai = (int) (((a_prev_prev * userEntry [3]) + (a_prev * userEntry[4]) + userEntry[5]) % p);
					a_prev_prev = a_prev;
					a_prev = ai;
					count++;
					aiSet.add(ai);
					if (count == 100000)
					{
						ans = check (aiSet, userEntry2, ans, p, l);
						aiSet.removeAllElements();
						count = 0;
						System.out.println (count);
					}
				}
				ans = check (aiSet, userEntry2, ans, p, l);
				
				aiSet.removeAllElements();
				//System.out.println (p + " " + l);
				//System.out.println (userEntry[0] + " "+ userEntry[1] + " " + userEntry[2] + " " + userEntry[3] + " " + userEntry[4] + " " + userEntry[5]);
				//System.out.println (userEntry2[0] + " "+ userEntry2[1] + " " + userEntry2[2] + " " + userEntry2[3] + " " + userEntry2[4] + " " + userEntry2[5]);
				
				// Store solution
				vAns.add(ans);
			}
		
			for (int z=0; z<vAns.size(); z++)
				System.out.println (vAns.elementAt(z));
			
		} // End Try
		catch (Exception e)
		{System.out.println ("Oh no. Exception Thown!");}
	} // End Main
	
	public static int check (Vector <Integer> aiSet, long userEntry2 [], int ans, long p, long l)
	{
		long b_prev_prev = userEntry2[0];
		long b_prev = userEntry2[1];
		long bi=0;
		for (int j=1; j<=userEntry2[0]; j++)
		{

			if (j>=3)
			{bi = ((b_prev_prev * userEntry2 [3]) + (b_prev * userEntry2[4]) + userEntry2[5]) % p;
			b_prev_prev = b_prev;
			b_prev = bi;
			}
			if (j==1)
				bi = b_prev_prev;
			if (j==2)
				bi = b_prev;
			
			// Check condition
			for (int xx=0; xx < aiSet.size(); xx++)
			{
				long qqq = ((aiSet.elementAt(xx)) * bi)%p;
				if (qqq < l)
					ans++;
			}
			//System.out.println ("Done" + j);
		}
		return ans;
		
	}
	
	
} // End Class
