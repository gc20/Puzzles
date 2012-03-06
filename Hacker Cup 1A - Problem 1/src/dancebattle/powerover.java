package dancebattle;

import java.io.DataInputStream;
import java.util.Vector;

public class powerover {

	public static void main (String args []) throws Exception
	{
		DataInputStream in = new DataInputStream (System.in);
		Vector v1F = new Vector <Double> ();
		
		int N = Integer.parseInt(in.readLine().trim());
		
		
		for (int i = 1; i<=N; i++)
		{
			String s = in.readLine().trim();
			s += " ";
			
			int index = s.indexOf(' ');
			double G = Double.parseDouble (s.substring (0,index));
			
			int index_old = index+1;
			index = s.indexOf(' ', index+1);
			double W = Double.parseDouble (s.substring (index_old,index));
			
			index_old = index+1;
			index = s.indexOf(' ', index+1);
			double M = Double.parseDouble (s.substring (index_old,index));
			
			double pointer [] = new double [2];
			double xx = (M - (M%(2*W)))/(2*W);
			double yy = ((M-(xx*W)) - ((M-(xx*W))%G))/G;
			
			//System.out.println (G + " " + W + " " + M + " " + xx + " " + yy);
			findMax (G,W,M,yy,xx, pointer);
			v1F.add(pointer[0]);
			//System.out.println ("Here: " + pointer[1]);
		}
		for (int i = 0; i<v1F.size();i++)
			System.out.println (v1F.elementAt(i));
		
	} // main
	
	public static void findMax (double cost_SG, double cost_WA, double Money, double nos_SG, double nos_WA, double pointer [] )
	{
		
		double temp_nos_WA = nos_WA;
		double temp_nos_SG = nos_SG;
		
		for (int i = 0; i<10; i++)
		{
		nos_WA -= 1.0;
		double rem_money = Money - (cost_WA * nos_WA);
		nos_SG = (rem_money - (rem_money%cost_SG)) /cost_SG;
		//System.out.println (cost_SG + " " + cost_WA + " " + Money + " " + nos_SG + " " + nos_WA + " " + pointer [0] + " " + pointer [1]);
		
		if ((nos_WA * nos_SG) > (pointer [0]*pointer[1]))
		{
			pointer[0] = nos_SG;
			pointer[1] = nos_WA;
		}
		}
		
		for (int i = 0; i<100000; i++)
		{
		nos_WA += 1.0;
		double rem_money = Money - (cost_WA * nos_WA);
		if (rem_money < 0)
			break;
		nos_SG = (rem_money - (rem_money%cost_SG)) /cost_SG;
		//System.out.println (cost_SG + " " + cost_WA + " " + Money + " " + nos_SG + " " + nos_WA + " " + pointer [0] + " " + pointer [1]);
		
		if ((nos_WA * nos_SG) >= (pointer [0]*pointer[1]))
		{
			pointer[0] = nos_SG;
			pointer[1] = nos_WA;
		}
		}
	
	}//findmax
	
	
}//class
