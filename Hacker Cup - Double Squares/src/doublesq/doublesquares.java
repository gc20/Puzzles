package doublesq;

import java.io.DataInputStream;
import java.util.Vector;

public class doublesquares {

	
	public static void main (String args[]) throws Exception
	{
		DataInputStream in = new DataInputStream (System.in);
		Vector <Integer> v = new Vector <Integer> ();
		try
		{
		int N = Integer.parseInt (in.readLine());
		
		for (int i = 1; i<=N; i++)
		{
			int x = Integer.parseInt (in.readLine());
			dsquare (x,v);
		}
		for (int i = 0; i<v.size(); i++)
			System.out.println (v.elementAt(i));
		
		}
		catch (Exception e)
		{}
	}
	
	public static void dsquare (int x, Vector <Integer> v)
	{
		int count = 0;
		int limit = (int) Math.ceil (Math.sqrt(x));
		for (int i = 0; i<=limit; i++)
		{
			for (int j = 0; j<=limit; j++)
			{
				if ( (i*i + j*j) == x)
					count++;
			}
		}
		v.add ((int) (Math.ceil (count / 2.0)));
	}
	
}
