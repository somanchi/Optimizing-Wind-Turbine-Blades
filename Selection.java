import java.util.*;
public class Selection {
	
    public int[] rouletteWheelSelection2(int popSize ,ArrayList<Double> fitness) {
        int[] populationindex = new int[popSize];
        double totalFitness = 0;

        for (int i = 0; i < popSize; i++) {
            totalFitness +=fitness.get(i);
        }
       for (int i = 0; i < popSize; i++) {
            double randomNumber =randomNumberInRange(0,(int)totalFitness);
            double runningSum = 0;
            int index = 0;
            int lastAddedIndex = 0;
            while (runningSum < randomNumber) {
                runningSum += fitness.get(index);
                lastAddedIndex = index;
                index++;
            }
            populationindex[i] =lastAddedIndex;
            runningSum = 0;
            index = 0;
            lastAddedIndex = 0;
        }
        return populationindex;
    }
    
    
    public double AvgFitness(int popsize,double[]fitness){
    	double avgfitness = 0;
    	double totalFitness = 0;

         for (int i = 0; i < popsize; i++) {
             totalFitness +=fitness[i];
         }
         avgfitness = totalFitness/popsize;
         return avgfitness;
    }
    public static double randomNumberInRange(int min, int max) {
        return (Math.random() * ((max - min) + 1)) + min;
    }

}
