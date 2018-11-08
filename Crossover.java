import java.util.*;
public class Crossover {
int k;
int k2;
Crossover(int val){
	this .k = val;
}

Crossover(int val , int val2){
	this.k = val;
	this.k2 = val2;
}

	public double[] applycrossover(double[]p1, double[] p2) {
		double[] c1 = new double[5];
		double[] c2 = new double[5];

		for(int i = 0 ; i < 5 ; i++ ){
				if(i<k)
				{
					c1[i]=p1[i];
					c2[i]=p2[i];
				}
				else if(i>=k)
				{
					c1[i]=p2[i];
					c2[i]=p1[i];
				}
			}
		double []commonchild = new double [10];
		for (int j=0;j<10;j++)
		{
			if(j<5)
			{
				commonchild[j] = c1[j];
			}
			else
				commonchild[j] = c2[j-5];
		}
		
		return commonchild;
			
	}
	
	
	
	public double[] doublepointcrossover(double[]p1, double[]p2){
		double[] c1 = new double[5];
		double[] c2 = new double[5];

		for(int i = 0 ; i < 5 ; i++ ){
				if(i<k)
				{
					c1[i]=p1[i];
					c2[i]=p2[i];
				}
				else if(i>=k && i<k2)
				{
					c1[i]=p2[i];
					c2[i]=p1[i];
				}
				else if(i>=k2)  {
					c1[i]=p2[i];
					c2[i]=p1[i];
				}
			}
		double []commonchild = new double [10];
		for (int j=0;j<10;j++)
		{
			if(j<5)
			{
				commonchild[j] = c1[j];
			}
			else
				commonchild[j] = c2[j-5];
		}
		
		return commonchild;
			
	}
	
}

	