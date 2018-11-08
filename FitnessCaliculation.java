import static java.lang.Math.pow;
import java.io.*;
public class FitnessCaliculation
{
	
	double[] calculate_fitness(double [][] pop, int pop_size)
	{
		double [] fitness_value = new double [pop_size];
		for(int k=0; k<pop_size; k++)
		{
			fitness_value[k]=function(pop,pop_size,k);
		}
		return fitness_value ;
	}

	private double function(double[][] pop, int pop_size,int k) {
		
		double A = Area(pop,pop_size,k);
		double aep = AEP(pop,pop_size,k,A);
		double cost = pop[k][0]*3;
		double fit = fit(cost,aep);
		fit = 1/fit;
		fit = fit/10;
		return fit;
	}
	private double fit(double cost, double aep) {
		double z ;
		z = cost/aep;
		return z;
	}
	private double AEP(double[][] pop, int pop_size, int k , double a) {		
		double density = 1.19;
		double v  = pop[k][1];
		double s = pow(v,2);
		double aep = 0.5 * density * a * pop[k][2] * s * 8760;
		return aep;
	}
	private double Area(double[][] pop, int pop_size, int k) {		
		double area = 0;
		double val = 0.1;
		
		for (int i = 0 ; i < pop[k][4]; i++)
		{
			area = pop[k][3]*val + area;
			val = val + 0.0001;
		}
		return area;
	}
	
}