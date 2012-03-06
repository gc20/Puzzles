package hacker1A_1;

import java.io.DataInputStream;
import java.util.Vector;

public class problem1 {

	
	public static void main (String args []) throws Exception
	{
		DataInputStream in = new DataInputStream (System.in);
		Vector v1 = new Vector <Integer> ();
		Vector v2 = new Vector <Integer> ();
		Vector v3 = new Vector <Integer> ();
		
		int N = Integer.parseInt(in.readLine().trim());
		
		for (int i = 1; i<=N; i++)
		{
			
			String s = in.readLine().trim() + " ";
			int start_index = 0;
			int end_index = s.indexOf(' ');
			int characters = Integer.parseInt (s.substring(start_index, end_index));
			v3.add(characters);
			
			for (int j = 1; j <= characters; j++)
			{
				start_index = end_index + 1;
				end_index = s.indexOf(' ', start_index);
				int num = Integer.parseInt (s.substring(start_index, end_index));
				possible_secrets (num, v2, j);
			}
			
			if (v2.isEmpty() == true)
				v1.add (-2); // Wrong machine
			if (v2.size() > 1)
				v1.add (-1); // Not enough observations
			if (v2.size() == 1)
				v1.add (v2.firstElement());
			
			
			v2.removeAllElements(); // Clear vector			
		}
		
		for (int i = 0; i < v1.size(); i++)
		{
			int num = (Integer) v1.elementAt (i);
			//System.out.println (i + " " + num);
			if (num == -2)
				System.out.println ("Wrong machine");
			if (num == -1)
				System.out.println ("Not enough observations");
			if (num > -1)
				print_next_ten (num, (Integer) v3.elementAt (i));
		}
	}
	
	public static void possible_secrets (int num, Vector <Integer> v2, int j)
	{
		
		if (j == 1)
		{
			for (int i = 0; i <= 10000002; i++)
			{
				long x = ( ((long)(i)) * 5402147 + 54321) % 10000001;
				//System.out.print (x + " ");
				long xx = (x%1000);
				int y = (int) xx;
				if (y == num)
				{
					boolean check = true;
					for (int kk = 0; kk < v2.size(); kk++)
					{
						long a1 = (((long)(i)) * 5402147 + 54321) % 10000001;
						long a2 = (((long)(v2.elementAt(kk))) * 5402147 + 54321) % 10000001;
						if (a1 == a2)
						{
							check = false;
							break;
						}
					}
					if (check == true)
						v2.add (i);
				}
					
			}
		}
		else
		{
			for (int i = 0; i < v2.size(); i++)
			{
				int control = v2.elementAt(i);
				long x = (long) control;
				for (int k = 1; k<=j; k++)
					x = ((x * 5402147 + 54321) % 10000001);
				int y = (int) (x%1000);
				//System.out.println (x + "  " + y);
				if (y != num)
				{
					v2.remove(i);
					i--;
				}
			}		
		}
		/*
		System.out.print (num + " " + j + "   " + v2.size() + "     ");
		for (int i = 0; i < v2.size(); i++)
			System.out.print (v2.elementAt(i) + " ");
		System.out.println ();
		*/
		
	}
	
	public static void print_next_ten (int secret, int iter)
	{
		long x = (long) secret;
		for (int i = 1; i<=iter; i++)
			x = ((x * 5402147 + 54321) % 10000001);
		for (int i = 1; i<=10; i++)
		{
			x = ((x * 5402147 + 54321) % 10000001);
			System.out.print ( ((int) (x%1000)) + " ");
		}
		System.out.println ();
	}
		
		
}
	
