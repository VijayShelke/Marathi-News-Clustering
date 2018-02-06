package FinalProject;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

import weka.clusterers.ClusterEvaluation;
import weka.clusterers.SimpleKMeans;
import weka.core.Instances;
 
public class KMeans {

public void kmeansOperation()throws Exception
	{	
		//File file = new File("/home/vijay/kmeans.arff");
		File file = new File("/home/vijay/PrgmIPFile/termfrequencyOPT.arff");
		
		BufferedReader bufferedReader = null;
		try 
		{
			bufferedReader = new BufferedReader(new FileReader(file));
		} 
		catch (FileNotFoundException ex) 
		{
			System.err.println("File not found: " + file);
		}
		SimpleKMeans kmeans = new SimpleKMeans();
		Instances data = new Instances(bufferedReader);
		kmeans.setPreserveInstancesOrder(true);
		kmeans.setNumClusters(4);
		kmeans.buildClusterer(data);

		ClusterEvaluation eval = new ClusterEvaluation();
		eval.setClusterer(kmeans);
		eval.evaluateClusterer(data);
		try
	    {
	    	//PrintStream myconsole=new PrintStream("/home/vijay/kmeans.txt");

	    	PrintStream myconsole=new PrintStream("/home/vijay/PrgmIPFile/kmeansOPT.txt");
	    	System.setOut(myconsole);
	    	myconsole.print(eval.clusterResultsToString());
	    //	System.out.println("work fine...");
	    }
	    catch(FileNotFoundException fe)
	    {
	    System.out.println(fe);	
	    }
}
}