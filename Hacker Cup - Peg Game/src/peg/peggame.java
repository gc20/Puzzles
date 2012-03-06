/*
 * Input File Answers
0 0.000000
0 0.000000
0 0.000000
0 0.500000
0 0.000000
0 0.093510
0 0.000000
0 0.000000
0 0.000000
0 0.375000
0 0.000021 // 2.121552825e-05
0 0.187500
0 0.000000
0 0.000000
0 0.000000
0 0.000000
0 0.000000
0 0.000000 // 2.901766306e-09
0 0.007813
0 0.375000
*/



package peg;

import java.io.DataInputStream;
import java.util.Vector;

public class peggame {
	public static void main (String args[]) throws Exception
	{
		DataInputStream in = new DataInputStream (System.in);
		Vector <String> v = new Vector <String> ();
		try
		{
		
		int check_old, check_new, row, column, target, missingno;
		int N = Integer.parseInt (in.readLine());
		
		for (int i = 1; i <= N; i++)
		{
			String s = in.readLine();
			s = s.trim() + " ";
			
			check_new = s.indexOf(" ", 0);
			row = Integer.parseInt (s.substring(0,check_new));
			check_old = check_new+1;
			
			check_new = s.indexOf(" ", check_new+1);
			column = Integer.parseInt (s.substring(check_old,check_new));
			check_old = check_new+1;
			
			check_new = s.indexOf(" ", check_new+1);
			target = Integer.parseInt (s.substring(check_old,check_new));
			check_old = check_new+1;
			
			check_new = s.indexOf(" ", check_new+1);
			missingno = Integer.parseInt (s.substring(check_old,check_new));
			check_old = check_new+1;
			
			//System.out.print (row + " " + column + " " + target + " " + missingno + "\n");
			double arr [][] = new double [row+1][(column*2)-1];
			double arr2 [][] = new double [row+1][(column*2)-1];
			for (int j = 0; j<row; j++)
			{
				for (int k = 0; k<(column*2-1);k++)
				{
					if (j%2 == k%2)
						arr[j][k] = 1;
				}
			}
			
			for (int j = 1; j<= missingno; j++)
			{
				check_new = s.indexOf(" ", check_new+1);
				int temp_row = Integer.parseInt (s.substring(check_old,check_new));
				check_old = check_new+1;
				
				check_new = s.indexOf(" ", check_new+1);
				int temp_col = Integer.parseInt (s.substring(check_old,check_new));
				check_old = check_new+1;
				
				arr[temp_row][temp_col*2 + temp_row%2] = 0;
			}
			
			
			double best_prob = -1.0;
			int best_column = 0;
			
			for (int j = 1; j <(column*2-1);j+=2)
			{
				arr2[0][j] = 1;
				//print_arr(arr,row,column);
				//check2 (arr, row, column, 1, j, 1); // Alternative method
				check (arr,row,column,1,arr2);
				//print_arr (arr2,row,column);
				double temp_sum = 0.0;
				for (int k = 0; k< (column*2-1); k++)
					temp_sum += (double) arr2[row][k];
				
				double temp_prob = arr2[row][(target*2) + row%2] / temp_sum;
				if (temp_prob > best_prob)
				{
					best_prob = temp_prob;
					best_column = (j-1)/2;
				}
				
				for (int k=0;k<(column*2-1);k++)
					arr[row][k] = 0;
				
				clear_arr(arr2,row,column);
				break;
			}
			
			s = Integer.toString(best_column) + " " + String.format("%.10g%n", best_prob); // Rounding to 6 places
			v.add(s);
		
		}
		System.out.println ("Answers: \n" );
		for (int i = 0; i<v.size(); i++)
			System.out.print (v.elementAt (i));
		}
		catch (Exception e)
		{}
	}
	/*
	public static void check2 (int arr[][], int row, int column, int temp_row, int temp_column, int weight)
	{
		//System.out.println (temp_row + "  " + temp_column);
		
		if (temp_row == row)
		{
			arr[temp_row][temp_column]+=weight;
			//System.out.println ("success");
			
		}
			else
		{ // If peg is missing
			if (arr[temp_row][temp_column] == 0)
			{
				check2 (arr, row, column, temp_row+1, temp_column, weight*2);
			}
			
			else
			{ // if peg is present
				if ((temp_row%2 == 0 && temp_column >= (column*2 - 2)) || (temp_row%2 == 1 && temp_column >= (column*2-3)))
				{
					check2 (arr,row,column,temp_row+1,temp_column-1, weight*2);
				}
				else
				{
					if (temp_row%2 == temp_column)
					{
					check2 (arr,row,column,temp_row+1,temp_column+1, weight*2);
					}
					else
					{
					check2 (arr,row,column,temp_row+1,temp_column+1, weight);
					check2 (arr,row,column,temp_row+1,temp_column-1, weight);
					}
				}	
			}
		}
	}
	*/
	
	
	public static void check (double arr[][], int row, int column, int temp_row, double arr2 [][])
	{
		if (temp_row != (row+1))
		{
			for (int i = (temp_row%2); i < (column*2 -1 - temp_row%2); i++)
			{
				if (arr[temp_row][i] == 0)
					arr2[temp_row][i] += arr2[temp_row-1][i]*2;
				
				else
				{
				if ((temp_row%2 == 0 && i >= (column*2 - 2)) || (temp_row%2 == 1 && i >= (column*2-3)))
					arr2[temp_row][i-1] += arr2[temp_row-1][i]*2;
				else
				{
					if (temp_row%2 == i)
						{
						arr2[temp_row][i+1] += arr2[temp_row-1][i]*2;
						//System.out.println ("cleee" + arr2[temp_row][i+1] + " " + temp_row + " " + (i+1));
						}
				
					else
					{
						arr2[temp_row][i+1] += arr2[temp_row-1][i];
						arr2[temp_row][i-1] += arr2[temp_row-1][i];
					}
				}
				}

			}
			
			//System.out.println(row + " " + column + " " + temp_row);
			//print_arr (arr2,row,column);
			check (arr,row,column,temp_row+1,arr2);
		}
	}
	
	public static void clear_arr (double arr2[][], int row, int column)
	{
		for (int i = 0; i<=row;i++)
		{
			for (int j = 0; j<(column*2-1);j++)
				arr2[i][j] = 0;
		}
	
	}
	
	public static void print_arr (double arr2[][], int row, int column)
	{
		for (int i = 0; i<=row;i++)
		{
			for (int j = 0; j<(column*2-1);j++)
				System.out.print (arr2[i][j] + "  ");
			System.out.println ();
		}
		System.out.println ();
	}
	
		
}
