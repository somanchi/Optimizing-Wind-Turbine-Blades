import java.util.*;
import java.util.Arrays;
import java.util.Collections;
public class Main {
	public static void main(String arg[])
	{
		int sincechange = 0;
		int lastgen =0;
		int lim,Point2double = 0;
		int count = 0;
		double thebest = 0;
		double[] Overallfit = new double[5];
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Number of Generations : ");
		int gen = sc.nextInt();
		System.out.println("Enter population size : ");
		int size = sc.nextInt();
		System.out.println("Select type of crossove: \n 1.Singlepoint \n 2.DoublePoint ");
		lim = sc.nextInt();
		if (lim == 2){
			System.out.println("Enter 2 Point of crossover");
			Point2double= sc.nextInt();
		}
		System.out.println("CrossoverProbability : ");
		double p = sc.nextDouble();
		int crossoverlimit = (int) (size *p);
		System.out.println("Crossover Limit : "+crossoverlimit);
		/*float q =  (float) (1-p);*/
		System.out.println("MutationProbability : ");
		double q = sc.nextDouble();
		int mutationlimit = (int) (size *q);
		if (mutationlimit ==0){mutationlimit =mutationlimit+1;}
		System.out.println("Mutation Limit  : "+mutationlimit);
		System.out.println("ENTER CROSSOVER POINT");
		int Point = sc.nextInt();		
		System.out.println("ENTER Mutation POINT");
		int mutationPoint = sc.nextInt();
		
		double [] bestval = new double[gen];
		double[][] maxfitness = new double[gen][2];
		double[] fitnessmax = new double[gen];
		double[][] bestchromosome = new double[gen][5];
		double[] toptenavg = new double[gen];
int[] indexarr = new int[10];		
ArrayList<Double> fitnesslist = new ArrayList<Double>();



		double[][] pop = new double[size][5];
		Population P = new Population();
		pop = P.generate_population(size);
		//System.out.println("Stop Limit : ");
		//int Stop = sc.nextInt();
		/*for(int j = 0; j<size; j++)
		{
			System.out.print("\n Chromosome intial population: "+ j + " : ");
			for(int k=0; k<5; k++)
			{
				System.out.print("  "+pop[j][k]+"  ");
			}
			System.out.println();
		}*/
		FitnessCaliculation F = new FitnessCaliculation();
		double [] fitness = new double[size];
		fitness = F.calculate_fitness(pop,size);
		double[][] pop1 = new double[10][5];
		indexarr =kLargest(fitness,10,size);
		
		for(int v = 0 ; v <size;v++){
			
			/*int value = (int)fitness[v];
			value =(int) value/100;
			for(int y = 0 ; y <value;y++){*/
				fitnesslist.add(fitness[v]);
			//}
			
		}
		
		
		/*for (int w = 0 ; w < 10 ; w++){
			System.out.println(indexarr[w]);
		}*/
		for(int k=0; k < 10; k++)
		{
			int val =indexarr[k]-1;
			for(int i = 0 ; i <5 ; i++){
				pop1[k][i] = pop[val][i];
			}
		}
		
		/*for(int x = 0; x<10; x++)
		{
			System.out.print("\n TOP 10 Chromosomes: "+ x + " : ");
			for(int k=0; k<5; k++)
			{
				System.out.print("  "+pop1[x][k]+"  ");
			}
			System.out.println();
		}*/
		
		Selection s = new Selection();
		double[] fitness10 = new double[10];
		fitness10 = F.calculate_fitness(pop1,10);
		toptenavg[0] = s.AvgFitness(10,fitness10); 
		
	/*	for (int k=0; k<size; k++)
		{
			System.out.println();

			System.out.println(k+"  Chromosome Fitness - "+ fitness[k]);
		}*/
		
		double[] dup = new double[2];
		dup = cal_max_array(fitness);
		for(int r = 0 ; r < 2 ; r++){
			maxfitness[0][r] = dup[r];
		}
		
		fitnessmax[0] = maxfitness[0][1];
		thebest = fitnessmax[0];
		
		/*for(int r = 0 ; r<2 ; r++){
			System.out.println(maxfitness[0][r]);
		}*/
		
		int[] Newpopindex = new int[size];
		double[] avgfitness = new double[gen];
		avgfitness[0] = s.AvgFitness(size,fitness)/10;
		Newpopindex = s.rouletteWheelSelection2(size, fitnesslist);
		/*System.out.println("Selected Chromosomes Index ");
		for(int i = 0 ; i < size; i++){
			System.out.print("  " + Newpopindex[i]+ " ");
		}*/
		double[][] Newpopulation = new double[size][5];
			for(int k=0; k < size; k++)
			{
				int val = Newpopindex[k];
				for(int i = 0 ; i <5 ; i++){
					Newpopulation[k][i] = pop[val][i];
				}
			}
			
			for(int r = 0 ; r < 5 ; r++){
				double ind = maxfitness[0][0];
				bestchromosome[0][r] = pop[(int) ind][r];
			}
			
			Overallfit = bestchromosome[0];
			
			/*for(int j = 0; j<size; j++)
			{
				System.out.print("\n Chromosome after Selection: "+ j + " : ");
				for(int k=0; k<5; k++)
				{
					System.out.print("  "+Newpopulation[j][k]+"  ");
				}
				System.out.println();
			}*/
			double[][] newpop100 = new double[mutationlimit][5];
		Mutation m = new Mutation();
		double[] t10 = new double[5];      // Mutation only if there is a mutation limit
		if(mutationlimit != 0){
		for (int mutate = 0 ; mutate < mutationlimit ; mutate++){
			t10 = makeoneD(Newpopulation,mutate);
			t10 = m.mutate(t10,mutationPoint);
			for(int i = 0 ; i <5 ; i++){
				newpop100[mutate][i] = t10[i];
			}
		}
		/*System.out.println("Muatation");
		for(int j = 0; j<mutationlimit; j++)
		{
			System.out.print("\n children mutation: "+ j + " : ");
			for(int k=0; k<5; k++)
			{
				System.out.print("  "+newpop100[j][k]+"  ");
			}
			System.out.println();
		}
		}
		else 
		{
			System.out.println("No Mutation");
		}
		System.out.println();*/
		double[][] newpop10 = new double[crossoverlimit][10];
		double[][] newpop = new double[crossoverlimit*2][5];
	
		// 2 Point Crossover
		
		if (lim == 1){	
		Crossover c = new Crossover(Point);
		for (int num = 0 ; num < crossoverlimit  ; num++){
			double[] t1 = new double[5];
			double[] t2 = new double[5];
			t1 = makeoneD(Newpopulation,num);
			t2 = makeoneD(Newpopulation,num+1);
			double [] P1 = new double[10];
			P1 = c.applycrossover(t1,t2);
				for(int i = 0 ; i <10 ; i++){
					newpop10[num][i] = P1[i];
				}
		}
		//newpop=half(newpop10,crossoverlimit);
		/*for(int j = 0; j<crossoverlimit; j++)
		{
			System.out.print("\n 2 children Crossover: "+ j + " : ");
			for(int k=0; k<10; k++)
			{
				System.out.print("  "+newpop10[j][k]+"  ");
			}
			System.out.println();
		}*/
		
	for(int h =0 ; h < crossoverlimit ; h++){
		double c10 [] = new double[5];
		double c20 [] = new double[5];
		for(int l = 0 ; l < 5 ; l++){
			if(h<crossoverlimit){
			c10[l] = newpop10[h][l];
			c20[l] = newpop10[h][l+5];}
		}
		for(int com = 0 ; com <5 ; com++){
			if(h<crossoverlimit)
			newpop[h][com] = c10[com];
			newpop[h+crossoverlimit][com] = c20[com];
		}

	}
	}
	else{
		Crossover c = new Crossover(Point,Point2double);
		for (int num = 0 ; num < crossoverlimit  ; num++){
			double[] t1 = new double[5];
			double[] t2 = new double[5];
			t1 = makeoneD(Newpopulation,num);
			t2 = makeoneD(Newpopulation,num+1);
			double [] P1 = new double[10];
			P1 = c.applycrossover(t1,t2);
				for(int i = 0 ; i <10 ; i++){
					newpop10[num][i] = P1[i];
				}
		}
		//newpop=half(newpop10,crossoverlimit);
		/*for(int j = 0; j<crossoverlimit; j++)
		{
			System.out.print("\n 2 children Crossover: "+ j + " : ");
			for(int k=0; k<10; k++)
			{
				System.out.print("  "+newpop10[j][k]+"  ");
			}
			System.out.println();
		}*/
		
	for(int h =0 ; h < crossoverlimit ; h++){
		double c10 [] = new double[5];
		double c20 [] = new double[5];
		for(int l = 0 ; l < 5 ; l++){
			if(h<crossoverlimit){
			c10[l] = newpop10[h][l];
			c20[l] = newpop10[h][l+5];}
		}
		for(int com = 0 ; com <5 ; com++){
			if(h<crossoverlimit)
			newpop[h][com] = c10[com];
			newpop[h+crossoverlimit][com] = c20[com];
		}

	}
		
		
	}
	/*for(int j = 0; j<crossoverlimit*2; j++)
	{
		System.out.print("\n After Crossover: "+ j + " : ");
		for(int k=0; k<5; k++)
		{
			System.out.print("  "+newpop[j][k]+"  ");
		}
		System.out.println();
	}*/
	
	bestval[0] = maxfitness[0][1];

	
	int newpoplimit =  (crossoverlimit*2) + mutationlimit;
	double[][] LoopPopulation = new double[newpoplimit][5];

	double[][] loopNewpopulation = new double[size][5];
int itr;

// LOOP FOR ITERATIONS

for( itr = 1 ; itr < gen ; itr++){
	LoopPopulation = makepop(newpop,newpop100,newpoplimit,(crossoverlimit*2),Overallfit);
	fitness = F.calculate_fitness(LoopPopulation,newpoplimit);
	fitnesslist.clear();

	for(int v = 0 ; v <size;v++){
		
		/*int value = (int)fitness[v];
		value = (int)value/100;
		for(int y = 0 ; y <value;y++){*/
			fitnesslist.add(fitness[v]);
		//}
		
	}
	indexarr =kLargest(fitness,10,size);

	for(int k=0; k < 10; k++)
	{
		int val =indexarr[k]-1;
		for(int i = 0 ; i <5 ; i++){
			pop1[k][i] = LoopPopulation[val][i];
		}
	}
	
	fitness10 = F.calculate_fitness(pop1,10);
	toptenavg[itr]= s.AvgFitness(10,fitness10);
	dup = cal_max_array(fitness);
	for(int r = 0 ; r < 2 ; r++){
		maxfitness[itr][r] = dup[r];
	}
	fitnessmax[itr] = maxfitness[itr][1];
	if(bestval[itr-1]< fitnessmax[itr]){
		bestval[itr] = fitnessmax[itr];
	}
	else
		bestval[itr] = bestval[itr-1];
	
	int[] loopNewpopindex = new int[size];
	loopNewpopindex = s.rouletteWheelSelection2(size, fitnesslist);
	avgfitness[itr] = s.AvgFitness(size,fitness);
	
	
	
	
	if(avgfitness[itr] <= avgfitness[itr-1])
		count++;
	else
		count = 0;
	if (count == 3)
		break;
	for(int k=0; k < size; k++)
	{
		int val = loopNewpopindex[k];
		for(int i = 0 ; i <5 ; i++){
			loopNewpopulation[k][i] = LoopPopulation[val][i];
		}
	}
	for(int r = 0 ; r < 5 ; r++){
		double ind = maxfitness[itr][0];
		bestchromosome[itr][r] = LoopPopulation[(int) ind][r];
	}
	
		if(maxfitness[itr][1] > thebest){
			thebest = maxfitness[itr][1];
		}
	
	for (int mutate = 0 ; mutate < mutationlimit ; mutate++){
		t10 = makeoneD(loopNewpopulation,crossoverlimit +mutate);
		t10 = m.mutate(t10,mutationPoint);
		for(int i = 0 ; i <5 ; i++){
			newpop100[mutate][i] = t10[i];
		}
	}
	if (lim == 1){
		Crossover c = new Crossover(Point);
	for (int num = 0 ; num < crossoverlimit  ; num++){
		double[] t1 = new double[5];
		double[] t2 = new double[5];
		t1 = makeoneD(loopNewpopulation,num);
		t2 = makeoneD(loopNewpopulation,num+1);
		double [] P1 = new double[10];
		P1 = c.applycrossover(t1,t2);
			for(int i = 0 ; i <10 ; i++){
				newpop10[num][i] = P1[i];
			}
	}
	for(int h =0 ; h < crossoverlimit ; h++){
		double c10 [] = new double[5];
		double c20 [] = new double[5];
		for(int l = 0 ; l < 5 ; l++){
			if(h<crossoverlimit){
			c10[l] = newpop10[h][l];
			c20[l] = newpop10[h][l+5];}
		}
		for(int com = 0 ; com <5 ; com++){
			if(h<crossoverlimit)
			newpop[h][com] = c10[com];
			newpop[h+crossoverlimit][com] = c20[com];
		}

	}
	}
	else{
		Crossover c = new Crossover(Point,Point2double);
		for (int num = 0 ; num < crossoverlimit  ; num++){
			double[] t1 = new double[5];
			double[] t2 = new double[5];
			t1 = makeoneD(Newpopulation,num);
			t2 = makeoneD(Newpopulation,num+1);
			double [] P1 = new double[10];
			P1 = c.applycrossover(t1,t2);
				for(int i = 0 ; i <10 ; i++){
					newpop10[num][i] = P1[i];
				}
		}
		for(int h =0 ; h < crossoverlimit ; h++){
			double c10 [] = new double[5];
			double c20 [] = new double[5];
			for(int l = 0 ; l < 5 ; l++){
				if(h<crossoverlimit){
				c10[l] = newpop10[h][l];
				c20[l] = newpop10[h][l+5];}
			}
			for(int com = 0 ; com <5 ; com++){
				if(h<crossoverlimit)
				newpop[h][com] = c10[com];
				newpop[h+crossoverlimit][com] = c20[com];
			}
}

}
	}
/*for(int f = 0 ; f < itr;f++){
	System.out.print( "\nbest chromosome in "+f+"population");		
	for (int r = 0 ; r < 2 ; r++){
		System.out.print( "  "+maxfitness[f][r]+"  ");		
	}
	}*/
for(int f = 0 ; f < itr;f++){
	System.out.print( "\n avg of total population "+f+"population");		

		System.out.print( "  "+avgfitness[f]+"  ");		
	}
		
/*for(int f = 0 ; f < itr;f++){
	System.out.print( "\n Top Ten avg @ "+f+" population");		

		System.out.print( "  "+toptenavg[f]+"  ");		
	}*/

		System.out.print( "\n\n The BEST Fitness is "+thebest+"  ");		
		
	
double[] base = new double[gen];
for(int i=0;i<gen;i++) {
	base[i] = i;
}
XYLineChart_AWT.Draw( "GRAPH", "Generations", "avg fitness", "Varying Parameters" ,base,avgfitness,toptenavg,fitnessmax,bestval,itr);


	}}
	
	private static double[][] makepop(double[][] newpop, double[][] newpop100,int newpoplimit, int crossoverlimit, double[] overallfit) {
		double[][] Pop = new double[newpoplimit][5];
		Pop[0] = overallfit;
		for (int i = 1;i < newpoplimit;i++ ){
			for (int j = 0 ; j<5;j++){
				if(i<crossoverlimit){
					Pop[i][j] = newpop[i][j];
				}
				else{
					Pop[i][j] = newpop100[i-crossoverlimit][j];
				}
			}
			
		}
		return Pop;
	}


	/*	private static double[][] half(double[][] newpopulation, int crossoverlimit) {
			double[][] t1 = new double[2*crossoverlimit][5];
			for(int i = 0 ; i < crossoverlimit*2 ; i++)
			{
				for(int j =0 ; j <5;j++){
					if(j<5 && i<crossoverlimit){
					t1[i][j] = newpopulation[i][j];}
					else if (j>=5 && i>=crossoverlimit)
					{
						t1[i][j-5] = newpopulation[i-crossoverlimit][j];
					}
				}
			}
		return t1;
	}*/
	
		private static double[] makeoneD(double[][] newpopulation, int limit) {
		double[] t1 = new double[5];
		for (int i = 0 ; i<5 ; i++)
		{
			t1[i] = newpopulation[limit][i];
		}
		return t1;
	}
		private static int randInt(int min, int max) {
		    Random rand = new Random();
		    int randomNum = rand.nextInt((max - min) + 1) + min;
		    return randomNum;
	}
	
	private static double[] cal_max_array(double[] fitness)
		{
			double max = fitness[0]; int m =0;
			for(int i=1; i<fitness.length; i++)
			{
				if(fitness[i]> max)
				{
					max = fitness[i];
					m=i;
				}
			}
			double[] val = new double[2];
			val[0] = m;
			val[1] = max;
			return val;
		}
	
	public static int[] kLargest(double[] fitness, int k,int size) 
    {
        Arrays.sort(fitness); 
     
     int [] index = new int[k];
   int val;
    for (int i = 0; i < 10; i++) {
    	val = size - i;
    	index[i] = val;
    }
    return index;
    } 
     

	
	
}