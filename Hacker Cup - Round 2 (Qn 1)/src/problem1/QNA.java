package problem1;

import java.io.DataInputStream;
import java.util.Vector;

public class QNA 
{
	public static void main ()
	{
		try
		{
			int i, N, ue;
			DataInputStream in = new DataInputStream (System.in);
			Vector vAns = new Vector <Integer> ();
			String entry = null;
			int temp;
			
		
			N = Integer.parseInt (in.readLine());
			System.out.println (N);
			/*
			for (i=0; i<N; i++)
			{
				entry = in.readLine().trim();
				String StruserEntry [] = entry.split("_");
				int userEntry [] = new int [StruserEntry.length];
				for (int k=0; k<StruserEntry.length; k++)
					userEntry[k] = Integer.parseInt (StruserEntry[k]);
				
				//
				temp = 0;
				for (int k=1; k<=userEntry[0];k++)
					temp += userEntry [k];
				System.out.println (temp);
			}*/
			
		}
		catch (Exception e)
		{System.out.println ("Oh no. Exception Thown!");}
	} // Main ends
}
