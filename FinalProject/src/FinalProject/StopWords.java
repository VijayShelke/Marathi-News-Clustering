package FinalProject;

	import java.io.BufferedReader;
	import java.io.BufferedWriter;
	import java.io.FileReader;
	import java.io.FileWriter;
	import java.util.HashMap;


	class StopWords {
		
		private HashMap<String, Integer> stopword_map;
		StopWords(String file_address)
		{
			init_stopword_map(file_address);
		}
		public boolean check_stopword(String t)
		{
			return stopword_map.containsKey(t);
		}
		public void init_stopword_map(String file_address)
		{
			stopword_map = new HashMap<String, Integer>();
			String sCurrentLine;
			String homedir = System.getProperty("user.home");
			try{
			BufferedReader br = new BufferedReader(new FileReader(homedir+file_address));

				while ((sCurrentLine = br.readLine()) != null) {
					//System.out.println(sCurrentLine);
					sCurrentLine=sCurrentLine.toLowerCase();
					String[] a= sCurrentLine.split("[_ ,.:;+-@$#^&*?!%|(){}'\"\\s+]");
					sCurrentLine=a[0];
					if(sCurrentLine.isEmpty() || stopword_map.containsKey(sCurrentLine))
					{
						// do nothing
					}
					else
					{
						stopword_map.put(sCurrentLine, stopword_map.size());
					}
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		public void remove_stopwords(String input_file,String output_file)
		{
			String sCurrentLine;
			BufferedWriter writer=null;
			BufferedReader br=null;
			String homedir = System.getProperty("user.home");
			try{
			br = new BufferedReader(new FileReader(homedir+input_file));
			writer = new BufferedWriter( new FileWriter(homedir+output_file));
				while ((sCurrentLine = br.readLine()) != null) 
				{
					//System.out.println(sCurrentLine);
					sCurrentLine=sCurrentLine.toLowerCase();
					if(sCurrentLine.isEmpty()
							||sCurrentLine.contains("title")
							||sCurrentLine.contains("place")
							||sCurrentLine.contains("category"))
					{
						writer.write(sCurrentLine);
						writer.newLine();
					}
					else
					{
						if(sCurrentLine.contains("########"))
						{
							writer.newLine();
							writer.write(sCurrentLine);
							writer.newLine();
						}
						else
						{
							String[] words=sCurrentLine.split("[_ ,.:;+-@$#^&*?!%|(){}'\"\\s+]");
							for(String word : words)
							{
								if(stopword_map.containsKey(word))
								{
									//do not put stopword in output
								}
								else
								{
									writer.write(word+" ");
								}
							}
						}
						
					}
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception in file opening ");
				e.printStackTrace();
			}
			finally
			{
			    try
			    {
			    	if(br!=null)
			    		br.close();
			        if ( writer != null)
			        writer.close();
			    }
			    catch ( Exception e)
			    {
			    	System.out.println("Exception in writing file");
			    	e.printStackTrace();
			    }
			}
		}

		/**
		 * @param args
		 */
		/*public static void stopWordOperation() {
			// TODO Auto-generated method stub
			//System.out.println("Enter the relative address of stopword file");
			StopWords s= new StopWords("/stopword.txt");
			//System.out.println("Enter the relative address of input file");
			String input_file = "/stemoutput.txt";
			String output_file = "/stopwordop.txt";
			s.remove_stopwords(input_file,output_file);
			//System.out.println("Output File is ready");
		}
		*/
		
		public static void stopWordOperation1() {
			// TODO Auto-generated method stub
			//System.out.println("Enter the relative address of stopword file");
			StopWords s= new StopWords("/PrgmIPFile/stopword.txt");
			//System.out.println("Enter the relative address of input file");
			String input_file = "/PrgmIPFile/stemmerOPT.txt";
			//String output_file = "/stopwordop.txt";
			String output_file = "/PrgmIPFile/stopwordOPT.txt";
			
			s.remove_stopwords(input_file,output_file);
			//System.out.println("Output File is ready");
		}

	}

	
