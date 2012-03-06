package dancebattle;

import java.io.DataInputStream;
import java.util.Vector;

public class dancebattle {

	
	public static void main (String args []) throws Exception
	{
		DataInputStream in = new DataInputStream (System.in);
		Vector v1F = new Vector <Integer> ();
		
		int N = Integer.parseInt(in.readLine().trim());
		
		
		for (int i = 1; i<=N; i++)
		{
			String s = null;
			do
			{
			s = in.readLine();
			}
			while (s.trim().isEmpty() == true);
			s = s.trim() + " ";

			int index = s.indexOf(' ');
			int R = Integer.parseInt (s.substring(0,index));
			
			int index_old = index;
			index = s.indexOf(' ', index+1);
			int C = Integer.parseInt (s.substring(index_old+1,index));
			
			char arr [][] = new char [R][C];
			int start_row = -1;
			int start_col = -1;
			int end_row = -1;
			int end_col = -1;
			
			//System.out.println (R + " " + C);
			
			for (int j = 0; j<R; j++)
			{
				index = 0;
				s = in.readLine();
				for (int k = 0; k<C;k++)
				{
					arr[j][k] = s.charAt(index+k);
					//System.out.print (arr[j][k] + " ");
					if (arr[j][k] == 'S' || arr[j][k] == 's')
					{
						start_row = j;
						start_col = k;
					}
					if (arr[j][k] == 'E' || arr[j][k] == 'e')
					{
						end_row = j;
						end_col = k;
					}
				}
				//index = s.indexOf(' ', index+1);
				
			}
			//System.out.println ("exe");
			v1F.add(manage(arr,R,C,start_row,start_col,end_row,end_col));
		}
		
		for (int i = 0; i<v1F.size();i++)
			System.out.println (v1F.elementAt(i));
		
	}//main
	
	
	public static int manage (char arr[][], int R, int C, int s_row, int s_col, int e_row, int e_col)
	{
		int pointer [] = new int [2];
		pointer [0] = 0;
		pointer [1] = 0;
		
		Vector Vx1 = new Vector <Integer> ();
		Vector Vx2 = new Vector <Integer> ();
		
		Vx1.add(s_row);
		Vx2.add(s_col);
		
		populate (Vx1, Vx2, pointer, R,C, arr);
		
		return (pointer[0]);
		
	}
	
	public static void populate (Vector <Integer> Vx1, Vector <Integer> Vx2, int pointer [], int R, int C, char arr[][])
	{
		//System.out.println ();
		for (int i = 0; i<Vx1.size(); i++)
		{
			//System.out.print (arr[Vx1.elementAt(i)][Vx2.elementAt(i)] + " ");
			if ((arr[Vx1.elementAt(i)][Vx2.elementAt(i)] == 'E') || (arr[Vx1.elementAt(i)][Vx2.elementAt(i)] == 'e'))
			{
				pointer [1] = 1;
				return;
			}
		}
		//System.out.println ();
		
		Vector Vx1_new = new Vector <Integer> ();
		Vector Vx2_new = new Vector <Integer> ();
		
		for (int i = 0; i<Vx1.size(); i++)
		{
			int rr = Vx1.elementAt(i);
			int cc = Vx2.elementAt(i);
			
			if ( (rr-1) >= 0)
			{
				if (arr[rr-1][cc] != 'W' && presence (Vx1_new, Vx2_new, rr-1, cc) == false)
				{// 2
				Vx1_new.add(rr-1);
				Vx2_new.add(cc);
				}
			
			}
			
				// 8
				if ( (cc-1) >= 0)
				{
					if (arr[rr][cc-1] != 'W' && presence (Vx1_new, Vx2_new, rr, cc-1) == false)
					{
					Vx1_new.add(rr);
					Vx2_new.add(cc-1);
					}
				}
				
				// 4
				if ( (cc+1) < C)
				{
					if (arr[rr][cc+1] != 'W'  && presence (Vx1_new, Vx2_new, rr, cc+1) == false)
					{
					Vx1_new.add(rr);
					Vx2_new.add(cc+1);
					}
				}	
			
				if ( (rr+1) < R)
				{
					if (arr[rr+1][cc] != 'W'  && presence (Vx1_new, Vx2_new, rr+1, cc) == false)
					{
					// 6
					Vx1_new.add(rr+1);
					Vx2_new.add(cc);
					}
				}
				
			// Colours
				if (arr[rr][cc] != 'W' && arr[rr][cc] != '0' && arr[rr][cc] != 'S' && arr[rr][cc] != 'W')
				{
					for (int qq1 = 0; qq1<R;qq1++)
					{
						for (int qq2 = 0; qq2<C; qq2++)
						{
							if ((arr[qq1][qq2] == arr[rr][cc]) && (presence (Vx1_new, Vx2_new, qq1, qq2) == false))
							{
								Vx1_new.add(qq1);
								Vx2_new.add(qq2);
							}
							}
						}
					}//colours
				
			} // for
		
		 pointer [0]++;
		
		 populate (Vx1_new, Vx2_new, pointer, R, C, arr);
			
		} // populate 
		
	public static boolean presence (Vector <Integer> v1, Vector <Integer> v2, int r, int c)
	{

		for (int i = 0; i<v1.size(); i++)
		{
			if ((v1.elementAt(i) == r) && (v2.elementAt(i) == c))
				return true;
		}
		return false;
	}
	
	
	} // class
