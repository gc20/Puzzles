// Try hashmaps for searching
// BufferedReader repetition (mark-reset inefficient)
package liarliar;
/*
/Users/Govind/Desktop/test/liarliar.txt
/Users/Govind/Documents/Eclipse Workspace/Facebook Puzzles/Liar, Liar/liarliar.txt
*/
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileReader;
import java.util.Vector;

public class liar {


	public static void main (String args[])
	{
		DataInputStream in;
		String filePath = null; 
		BufferedReader br1 = null;
		BufferedReader br2 = null;
		int size, i,j,k,row,listcount,typeSub;
		String subname = null, name = null;
		Vector<String> v1;
		Vector<Integer> v2;
		
		try
		{
			in  = new DataInputStream (System.in);
			filePath = in.readLine();
			br1 = new BufferedReader(new FileReader(filePath));

			size = Integer.parseInt(br1.readLine());
			
			v1 = new Vector <String> ();
			v2 = new Vector <Integer> ();
			
			for (k=1;k<=size;k++)
			{
				br1 = new BufferedReader(new FileReader(filePath));
				br2 = new BufferedReader(new FileReader(filePath));
				br1.readLine();
				br2.readLine();
				
			for (i=1;i<=size;i++)
			{
				
				// Extract Interviewee Info
				name = br1.readLine().trim();
				br2.readLine();
				listcount = Integer.parseInt (name.substring(name.indexOf(" "),name.length()).trim());
				name = name.substring(0, name.indexOf (" "));

				//Add to Array if Required
				row = isPresent (v1,name);

				if (row == -1)
				{
					addNameToList (v1, v2, name);
					row = v1.size() - 1;
				}
				
				//Determine interviewee type from subname if possible
				for (j=0;j<listcount;j++)
					{
					subname = br1.readLine().trim();
					if (v2.elementAt(row) == 3) // If no type has been assigned for interviewee
						checkSubname (v1, v2, row, subname); // Sets type of assignee, if possible
					}
				
				typeSub = v2.elementAt(row);
				if (typeSub != 3) // If interviewee type has been assigned, determine subtype
					{
					if (typeSub == 1)
						typeSub = 2;			
					else
						typeSub = 1;
					}
				
				for (j=0;j<listcount;j++)
					{
					subname = br2.readLine().trim();
					includeSubname (v1, v2, subname, typeSub);
					}
				
				
				/*
				System.out.println (i+":");
				printAll (v1, v2);
				System.out.println ("\n");
				*/
			
			}//first for
			
			if (v2.contains(3) == false)
				break;
			
			}//second for
			
		j = 0; // number of type A members
		k = 0; // number of type B members
		for (i=0;i<v2.size();i++)
		{
			if (v2.elementAt(i) == 1)
				j++;
			if (v2.elementAt(i) == 2)
				k++;
		}
		
		if (k>j)
		{
			i=k;
			k=j;
			j=i;
		}
			
		System.out.println (j + " " + k);
		
		}// try
		
		catch (Exception e)
		{e.printStackTrace();}
		
		finally
        {
            try
            {
                if (br1 != null)
                    br1.close();
                if (br2 != null)
                	br2.close();
            } 
            catch (Exception e)
            {e.printStackTrace();}
        }
		
	}//main
	
	
	public static int isPresent (Vector <String> v1, String s)
	{
		return (v1.indexOf(s));
	}
	
	public static void addNameToList (Vector <String> v1, Vector <Integer> v2, String name)
	{
		v1.add(name);
		if (v1.size() == 1) // Assign the very first name as "type 1"
			v2.add(1); // Type 1
		else
			v2.add(3); // Garbage type
	}
	
	public static void checkSubname (Vector <String> v1, Vector <Integer> v2, int row, String subname)
	{
		int subrow = isPresent (v1, subname);

		if (subrow!=-1)
		{
			if (v2.elementAt(subrow) == 2)
				v2.setElementAt(1, row);
			if (v2.elementAt(subrow) == 1)
				v2.setElementAt(2, row);
		}
	}
	
	
	public static void includeSubname (Vector <String> v1, Vector <Integer> v2, String subname, int typeSub)
	{
		int subrow = isPresent (v1, subname);
		
		if (subrow == -1)
		{
			addNameToList (v1, v2, subname);
			subrow = v2.size() - 1;
		}
		v2.setElementAt(typeSub, subrow);
	}
	
	/*
	 * public static void printAll (Vector <String> v1, Vector <Integer> v2)
	{
		int i;
		for (i=0;i<v1.size();i++)
			System.out.println (v1.elementAt(i) + " . " + v2.elementAt(i));
	}
	*/
	
}//class
