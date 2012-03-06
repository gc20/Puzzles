// SLOWW (11th Jan, 2011)

/*
/Users/Govind/Documents/Eclipse Workspace/Facebook Puzzles/Breathalyzer/sampletest.txt
*/
package breath;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Vector;

public class breathalyzer {

	public static void main (String args[]) throws Exception
	{
		DataInputStream in;
		BufferedReader br = null;
		int errors = 0, i = 0, wordStart = 0, wordEnd = 0;
		String text = null, word = null;
		
		try
		{
			Vector dictWords = new Vector <String> ();
			String filePath = "/Users/Govind/Documents/Eclipse Workspace/Facebook Puzzles/Breathalyzer/twl06.txt";// /var/tmp/twl06.txt
			BufferedReader brx = new BufferedReader (new FileReader (filePath));
			while (true)
			{
				if (brx.ready() == false)
					break;	
				dictWords.add (brx.readLine().trim().toUpperCase());
			}
			
			
			in  = new DataInputStream (System.in);
			br = new BufferedReader(new FileReader(in.readLine().trim()));
			while (true)
			{
				if (br.ready() == false) // Break at end of file
					break;
				
				text = br.readLine();
				text.trim();
				if (text.isEmpty())
					continue;
				
				text = text.toUpperCase();
				text = text + " ";
				
				while (wordStart != (text.length()))
				{
					wordEnd = text.indexOf(' ', wordEnd + 1);
					word = text.substring(wordStart, wordEnd);
					wordStart = wordEnd + 1;
					System.out.println ("\n" + word);
					errors += checkWord (word, dictWords);
					System.out.println ("ERRR" + errors);
				
				}
			}

			//System.out.println (errors);
			
		}// try
		
		catch (Exception e)
		{e.printStackTrace();}
		
		finally
        {
            try
            {
                if (br != null)
                    br.close();
            } 
            catch (Exception e)
            {e.printStackTrace();}
        }
		
	}//main

	
	public static int checkWord (String word, Vector <String> dictWords) throws Exception
	{
		Vector v1 = new Vector <String> ();
		v1.add(word);
		
		int pointer [] = new int [3];
		pointer [0] = 0;
		pointer [1] = 0;
		pointer [2] = 0;
		
		assimilate (v1, pointer, dictWords);
		return (pointer[0]);
	}
	
	public static void assimilate (Vector <String> v1, int pointer [], Vector <String> dictWords) throws Exception
	{
		
		System.out.println ("Look! " + pointer[0]);
		Vector v1New = new Vector <String> ();
		

		System.out.print ("Presence");
		boolean check = isPresent (v1, dictWords);
			if (check == true)
			{
				pointer[2] = 1;
				return;
			}
		
		System.out.println ("...Done");
		
		if (pointer [2] == 1)
			return;
		System.out.print ("Populating");
		v1New = populate (v1);
		System.out.println ("...Done");
		pointer[0]++;
		
		if (pointer[0] == 2)
		{
			for (int qq = 0; qq < v1New.size(); qq++)
				System.out.print (v1New.elementAt(qq) + " ");
			System.out.println();
		}
		
		assimilate (v1New, pointer, dictWords);
	}
	
	
	public static Vector <String> populate (Vector <String> Vx)
	{
		Vector <String> vNew = new Vector <String> ();
		System.out.print (vNew.size() + " ");
		
		for (int i = 0; i< Vx.size(); i++)
		{
			String word = Vx.elementAt(i);
			char c = 'A';
			
			//Add
			for (int j = 0; j < word.length(); j++)
				vNew.add(word.substring (0,j) + "." + word.substring(j,word.length()));
			
			//Remove
			for (int j = 0; j < word.length(); j++)
			{
				if (word.charAt(j) != '.')
					vNew.add(word.substring(0, j) + word.substring(j+1, word.length()));
			}
			/*
			//Replace
			for (int j = 0; j < word.length();j++)
			{
				if (word.charAt(j) != '.')
					vNew.add (word.substring(0, j) + "." + word.substring(j+1, word.length()));
			}
			*/
		}
		return (vNew);
	}
	
	
	
	
	public static boolean isPresent (Vector <String> Vx, Vector <String> dictWords) throws Exception
	{
	
		for (int i = 0; i<Vx.size();i++)
			{
			for (int j = 0; j<dictWords.size(); j++)
			{
			
				//System.out.println (dictWords.elementAt(j) + "." + Vx.elementAt(i) + " ");
				if (dictWords.elementAt(j).length() == Vx.elementAt(i).length())
				{
					String s1 = dictWords.elementAt(j);
					String s2 = Vx.elementAt(i);
					boolean check = true;
					for (int k = 0; k<s1.length();k++)
					{
						if (!(s2.charAt(k) == '.' || (s1.charAt(k) == s2.charAt(k))))
							check = false;
					}
					if (check == true)
						return (true);
				}
			}
			}
		return (false);
	}
}

	
	





	
/*
 * 	public static int checkWord (String word) throws Exception
	{
		String filePath = "/Users/Govind/Documents/Eclipse Workspace/Facebook Puzzles/Breathalyzer/twl06.txt";// /var/tmp/twl06.txt
		BufferedReader br2 = new BufferedReader (new FileReader (filePath));
		int pointer [] = new int [1];
		
		pointer [0] = word.length(); // PROBLEM
		
		while (true)
		{
			if (br2.ready() == false)
				break;	
			twoWords (word, (br2.readLine().trim().toUpperCase()), 0, pointer);
			//twoWords2 (word, (br2.readLine().trim().toUpperCase()), 0, pointer);
			
		}
		return (pointer[0]);
	}
	
	public static void twoWords (String word, String dictWord, int err, int pointer [])
	{
		System.out.println (pointer[0] +  " " + dictWord + "  " + word + " " + err);
		int i = 0, j = 0;
		char c = 'A';
		String tempString;
		
		if (err < pointer[0])
		{

			// Adding a single letter in any position
			if (word.length() < dictWord.length())
			{
			for (i = 0; i < word.length(); i++)
			{
				for (j = 0; j < 26; j++)
				{
					tempString = word.substring (0,i) + Character.toString ((char)((int) c+j)) + word.substring(i,word.length());
					twoWords (tempString, dictWord, err+1, pointer);
				}
			}
			}
			
			// Removing any single letter
			if (word.length() > dictWord.length())
			{
			for (i = 0; i<word.length(); i++)
			{
				tempString = word.substring(0, i) + word.substring(i+1, word.length());
				twoWords (tempString, dictWord, err+1, pointer);
			}
			}
			if (word.length() == dictWord.length())
			{
			for (i = 0; i < word.length();i++)
			{
				for (j = 0; j<26; j++)
				{
					tempString = word.substring(0, i) + Character.toString ((char)((int) c+j)) + word.substring(i+1, word.length());
					if (word.equals(dictWord) == true)
						pointer[0] = err+1;
				}
			}
			}
	
		}
}*/