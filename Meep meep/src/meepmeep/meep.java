package meepmeep;
import java.io.DataInputStream;


public class meep {


	public static void main (String args[]) throws Exception
	{
		DataInputStream in = new DataInputStream (System.in);
	try
	{
		String s = in.readLine();
		System.out.println ("Meep meep!");
	}
	catch (Exception e)
	{}
	}	
}
