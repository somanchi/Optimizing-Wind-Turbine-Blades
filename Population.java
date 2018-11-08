 import java.util.Random; 
 
public class Population {
	 
	
	
	public double [][] generate_population(int pop_size)
	{
			return random_population(pop_size);
	}

	double[][] random_population(int pop_size)
	{
		double[][] pop = new double[pop_size][5];
		for(int i =0; i<pop_size; i++)
		{
			for(int j = 0; j<5; j++)
			{
				switch(j){
				case 0 : pop[i][0] =  randomNumberInRange(12,14)*1000;       // Weight
						 break;
				case 1 : pop[i][1] =  randomNumberInRange(3,25);			// Velocity
				 		 break;
				 		 
				case 2 : pop[i][2] =  randomNumberInRange(0,1);				// Avg Thickness
				         break;
				
				case 3 : pop[i][3] =  randomNumberInRange(28,82);			// Radius
				         break;
				 
				case 4 : pop[i][4] =  randomNumberInRange(0,4);			//Pitch
				 		 break;
				}
			}
		}
		return pop;
	}

    public static double randomNumberInRange(int min, int max) {
        return (Math.random() * ((max - min) + 1)) + min;
    }

}
