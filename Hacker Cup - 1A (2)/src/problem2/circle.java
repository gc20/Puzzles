// heap space problem

package problem2;

import java.io.DataInputStream;
import java.util.Vector;

public class circle {
	
	public static void main (String args []) throws Exception
	{
		DataInputStream in = new DataInputStream (System.in);
		Vector v1 = new Vector <Integer> ();
		
		int count = Integer.parseInt(in.readLine().trim());
		Vector vx1 = new Vector <Integer> ();
		Vector vx2 = new Vector <Integer> ();
		
		
		for (int i = 1; i<=count; i++)
		{
			String s = in.readLine().trim() + " ";
			
			int start_index = 0;
			int end_index = s.indexOf(' ', 0);
			long N = Long.parseLong (s.substring(start_index, end_index));
			
			start_index = end_index + 1;
			end_index = s.indexOf(' ', start_index);
			long K = Long.parseLong (s.substring(start_index, end_index));
			System.out.println (N + " " + K);
			
			for (int j = 0; j<N/2; j++)
				vx1.add (j+1);
			
			for (int j = (int) (N/2); j<N; j++)
				vx2.add ((j+1) - (N/2));
			
			System.out.println ("Filled");
			
			long control = 0;
			while ( (vx1.size() + vx2.size()) > 1)
			{
				control = (control + K) % ((long)(vx1.size() + vx2.size()));
				if (control < vx1.size())
					vx1.removeElementAt((int) control);
				else
					vx2.removeElementAt((int) (control - vx1.size()));
			}
			if (vx1.isEmpty() == true)
				v1.add(vx2.firstElement());
			else
				v1.add(vx1.firstElement());
			
			vx1.removeAllElements();
			vx2.removeAllElements();
		}
		
		for (int i = 0; i<v1.size(); i++)
			System.out.println (v1.elementAt(i));
	}
	
}
