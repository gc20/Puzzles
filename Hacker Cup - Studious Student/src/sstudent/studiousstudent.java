package sstudent;

import java.io.DataInputStream;
import java.util.Vector;

public class studiousstudent {
	
	public static void main (String args[]) throws Exception
	{
		DataInputStream in = new DataInputStream (System.in);
		Vector <String> v = new Vector <String> ();
		try
		{
		int N = Integer.parseInt (in.readLine());
		
		for (int i = 1; i <= N; i++)
		{
			String s = in.readLine();
			s = s.trim() + " ";
			int check_2 = s.indexOf(" ", 0);
			int check_1 = 0;
			int m = Integer.parseInt (s.substring(0, check_2));
			String arr [] = new String [m];
			for (int j = 0; j<m; j++)
			{
				check_1 = check_2;
				check_2 = s.indexOf(" ", check_1+1);
				arr[j] = s.substring (check_1+1, check_2);
			}
			
			for (int j = 0; j<m; j++)
			{
				for (int k = j; k<m;k++)
				{
					if (arr[j].compareTo(arr[k]) > 0)
					{
						String swap = arr[j];
						arr[j] = arr[k];
						arr[k] = swap;
					}
					
					int len = 0;
					if (arr[j].length() < arr[k].length())
					{
						len = arr[j].length();
						for (int l = 1;l<=len;l++)
						{
							if ((arr[k].substring(0, l)).equals(arr[j]) == true)
							{
								if (arr[k].substring(l,arr[j].length()).compareTo(arr[j]) < 0)
								{
									String swap = arr[j];
									arr[j] = arr[k];
									arr[k] = swap;
									break;
								}
							}
						}
					}
					
					
				}
			}
			s = "";
			for (int j = 0; j<m;j++)
				s+=arr[j];
			v.add(s);
					
			}
			
		for (int i = 0; i<v.size(); i++)
			System.out.println (v.elementAt (i));
		
			
		}
		catch (Exception e)
		{}
		
	
		
}}
