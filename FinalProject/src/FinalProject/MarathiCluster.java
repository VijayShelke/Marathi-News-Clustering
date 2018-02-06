package FinalProject;

import java.io.IOException;

public class MarathiCluster 
{
	
	
	public static void main(String[] args) throws Exception {
		Stemmer stemmer = new Stemmer();
		//stemmer.stemmerOperation();
		stemmer.stemmerOperation1("/home/ashwini/Desktop/sports.txt");
		

		StopWords s= new StopWords("/PrgmIPFile/stopword.txt");
		//StopWords.stopWordOperation();
		StopWords.stopWordOperation1();
		
		
		
		TermFrequency termFrequency =new TermFrequency();
		termFrequency.termFrequencyOperation();
		
		KMeans kMeans = new KMeans();
		kMeans.kmeansOperation();
		
		
	}
	
	
}
