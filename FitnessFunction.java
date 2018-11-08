import static java.lang.Math.pow;
import java.io.*;

public class FitnessFunction {
	double velocity;
	double cost,Radius;
	double pitch;
	double weight,thickness;
	double Density = 1.19;
	double function,f1,fo;
	
	FitnessFunction(double[] Chromosome){
		
		cost = Chromosome[0]*3;
		velocity = Chromosome[1];
		thickness = Chromosome[2];
		Radius = Chromosome[3];
		pitch = Chromosome[4];
	}

	public double CaliculateFitness(){
		int a = 0;
		double K = Area(Radius,a,pitch);
		 double aep = AEP(Density,K,thickness,velocity);
		function = cost / aep;
		return function;
	}
	
	private double AEP(double density, double k, double thickness, double velocity) {
		double v;
		v = pow(velocity,2);
		double aep = 0.5 * density * k * thickness * v * 8760;
		//System.out.println("AEP is" + aep);
		return aep;
	}

	private double Area(double radius, int a, double pitch) {
		double area;
		area = inegral(radius,a,pitch);
		return area;
		
	}

	private double inegral(double radius2, int a, double pitch) {
		double val = 0;
		double k = 0.1;
		for (int i = 0 ; i < pitch ; i++){
			val = val + (radius2*k);
			k = k+0.1;
		}
		//System.out.println("area is " + val);
		return val;
	}
}