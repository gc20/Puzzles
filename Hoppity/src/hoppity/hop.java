package hoppity;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class hop
	{

	public static void main (String args[]) throws Exception
	{		
		
		File hop;
		Scanner s1;
		String text = null;
		int integer = -1;
		int count;
		
		try
		{
			
			//Extract number and store in string "text"
			hop = new File("/Users/Govind/Documents/Eclipse Workspace/Facebook Puzzles/Hoppity/hop.txt");
			s1 = new Scanner(hop);
			while(s1.hasNextLine())
			{
				text = s1.nextLine().trim();
				if (text.isEmpty() == true)
					continue;
				else
					integer = Integer.parseInt (text);
			}
			
			//Print desired results
			for (count = 1; count <= integer; count++)
			{
				if ( (count%3 == 0) && (count%5 == 0))
					System.out.println ("Hop");
				else
				{
					if (count%3 == 0)
						System.out.println ("Hoppity");
					if (count%5 == 0)
						System.out.println ("Hophop");
				}
			}
			
		}
		
		catch (IOException e)
        {
            e.printStackTrace();
        }
		
		
		
	}}
		
		/*
		
			Using FileReader
		
			System.out.println ("textttg");
	        File file = new File("/Users/Govind/Desktop/hop.rtf");
	        StringBuffer contents = new StringBuffer();
	        BufferedReader reader = null;

	        try
	        {
	            reader = new BufferedReader(new FileReader(file));
	            String text = null;

	            while ((text = reader.readLine()) != null)
	            	System.out.println ("\n"+text);

	        } catch (FileNotFoundException e)
	        {
	            e.printStackTrace();
	        } catch (IOException e)
	        {
	            e.printStackTrace();
	        } finally
	        {
	            try
	            {
	                if (reader != null)
	                {
	                    reader.close();
	                }
	            } catch (IOException e)
	            {
	                e.printStackTrace();
	            }
	        }
	    }}*/
