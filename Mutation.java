import java.util.Random;

public class Mutation {

	public double[] mutate(double[] child,int Point){
		
		switch(Point){
		case 0 : child[0] =  randomNumberInRange(12,14)*1000;       // Weight
				break;
		case 1 : child[1] =  randomNumberInRange(3,25);			// Velocity
				break;
		 
		case 2 : child[2] =  randomNumberInRange(0,1);				// Avg Thickness
        		break;

		case 3 : child[3] =  randomNumberInRange(28,82);			// Radius
        		break;

		case 4 : child[4] =  randomNumberInRange(0,4);			//Pitch
				break;
		}
		
		return child;
	}
	
	private static int randInt(int min, int max) {
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
		}
	  public static double randomNumberInRange(int min, int max) {
	        return (Math.random() * ((max - min) + 1)) + min;
	    }


}
