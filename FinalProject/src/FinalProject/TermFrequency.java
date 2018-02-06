package FinalProject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;


public class TermFrequency {

	public void termFrequencyOperation() throws FileNotFoundException, IOException {

		//File file = new File("/home/ashwini/demo.txt");
		File file = new File("/home/ashwini/PrgmIPFile/stopwordOPT.txt");
		
		BufferedReader bufferedReader = null;
		bufferedReader = new BufferedReader(new FileReader(file));
		String inputLine = null;
		// Map<String, Integer> crunchifyMap = new HashMap<>();
		Map<String, Integer> unique = new HashMap<String, Integer>();
		boolean content_flag = false, title_flag = false, category_flag = false, place_flag = false, poli_flag = false,
				sport_flag = false, enter_flag = false, eco_flag = false;
		int seq_word_complete_doc = 0;

		try {
			while ((inputLine = bufferedReader.readLine()) != null) {
				inputLine = inputLine.toLowerCase();

				title_flag = false;
				category_flag = false;
				place_flag = false;

				String[] words1 = inputLine.split("[_ ,.:;+-@$#^&*?!%|(){}'\"\\s+]");

				for (int counter = 0; counter < words1.length; counter++) {
					String key = words1[counter].toLowerCase();
					if (key.length() > 0) {

						if (key.contentEquals("contents")) {
							content_flag = true;
							continue;
						}

						if (key.contentEquals("place")) {
							// ignore
							place_flag = true;
							continue;
						}
						if (key.contentEquals("title")) {
							// ignore
							title_flag = true;
							continue;
						}

						if (key.contentEquals("category")) {
							// ignore
							category_flag = true;
							continue;
						}
						if (key.contentEquals("politics")) {
							// ignore
							poli_flag = true;
							continue;
						}

						if (key.contentEquals("entertainment")) {
							// ignore
							enter_flag = true;
							continue;
						}
						if (key.contentEquals("sports")) {
							// ignore
							sport_flag = true;
							continue;
						}
						if (key.contentEquals("economy")) {
							// ignore
							eco_flag = true;
							continue;
						}
						if (content_flag == true) {
							if (unique.get(key) == null) {

								unique.put(key, seq_word_complete_doc++);
							}
						}
					}
				}
			}
			bufferedReader.close();
			/*
			 * Set<Map.Entry<String, Integer>> entrySet = unique.entrySet();
			 * System.out.println("@RELATION News"); for (Map.Entry<String,
			 * Integer> entry : entrySet) { System.out.println("@ATTRIBUTE \t" +
			 * entry.getKey() + "\t\t" + "NUMERIC" +entry.getValue()); }
			 * System.out.println(
			 * "@ATTRIBUTE Category {politics,entertainment,sports,economy }");
			 * System.out.println("@ATTRIBUTE place STRING");
			 * System.out.println("@DATA");
			 */

			bufferedReader = new BufferedReader(new FileReader(file));
			title_flag = false;
			category_flag = false;
			content_flag = false;
			String category = new String();

			// Map<String, Integer> tempMap = new HashMap<>();
			///////////////////////////////////////////////////////////////////////////////////
			/// ARRAY///////////
			int[] tempMap = new int[unique.size()];
			String[] tempKeys = new String[unique.size()];
			// tempMap.putAll(unique);

			for (String key : unique.keySet()) {
				tempKeys[unique.get(key)] = key;
				tempMap[unique.get(key)] = 0;
			}
			//////////////////////////////////////////////////////////////
			// Set<Map.Entry<String, Integer>> entrySet = unique.entrySet();

			try {
				//PrintStream myconsole = new PrintStream("/home/ashwini/kmeans.arff");
				PrintStream myconsole = new PrintStream("/home/ashwini/PrgmIPFile/termfrequencyOPT.arff");
				
				System.setOut(myconsole);
				// myconsole.print("@RELATION News");
			} catch (FileNotFoundException fe) {
				System.out.println(fe);
			}

			System.out.println("@RELATION News");
			for (int count = 0; count < unique.size(); count++) {
				System.out.println("@ATTRIBUTE \t" + tempKeys[count] + "\t\t" + "NUMERIC");
			}
			System.out.println("@ATTRIBUTE Category { politics,entertainment,sports,economy }");
			System.out.println("@ATTRIBUTE place NUMERIC");
			System.out.println("@DATA");

			// Set<Map.Entry<String, Integer>> entrySet1 =
			// null;//tempMap.entrySet();

			while ((inputLine = bufferedReader.readLine()) != null) {

				inputLine = inputLine.toLowerCase();
				category_flag = false;

				String[] words1 = inputLine.split("[ \n\t\r.,;:!-?(){}]");

				for (int counter = 0; counter < words1.length; counter++) {
					String key = words1[counter].toLowerCase();
					if (key.length() > 0) {
						if (key.contentEquals("contents")) {
							content_flag = true;
							continue;
						}
						if (key.contentEquals("title")) {

							if (title_flag) {
								// entrySet1 = tempMap.entrySet();
								System.out.print("{ ");
								int i = 0;
								for (i = 0; i < unique.size(); i++) {
									if (tempMap[i] != 0)
										System.out.print(i + " " + tempMap[i] + ",");
								}
								/*
								 * for (Map.Entry<String, Integer> entry :
								 * entrySet1) {
								 * System.out.print(entry.getValue() + " ,"); }
								 */
								System.out.println((i) + " " + category + "," + (i + 1) + "  1 }");
								content_flag = false;
								category_flag = false;

							}
							// tempMap.clear();
							int count = 0;
							for (String temp_key : unique.keySet()) {
								tempMap[count++] = 0;
							}
							title_flag = true;
						}
						if (key.contentEquals("place")) {
							// ignore
							place_flag = true;
							continue;
						}

						if (key.contentEquals("category")) {
							// ignore
							category_flag = true;
							continue;
						}
						if (category_flag == true) {
							category = key;
							continue;
						}
						if (content_flag == true) {
							if (unique.get(key) == null) {
								System.out.println("Error in unique hashmap");
								System.exit(0);
							} else {
								// System.out.println("key="+key+"value =
								// "+unique.get(key));
								tempMap[unique.get(key)]++;

							}
						}
					}
				}
			}
			// entrySet1 = tempMap.entrySet();
			// System.out.println("@DATA");
			System.out.print("{ ");
			// for (Map.Entry<String, Integer> entry : entrySet1) {
			// System.out.print(entry.getValue() + " ,");
			// }
			int i = 0;
			for (i = 0; i < unique.size(); i++) {
				if (tempMap[i] != 0)
					System.out.print(i + " " + tempMap[i] + ",");
			}
			System.out.println((i) + " " + category + "," + (i + 1) + "  1 }");
			bufferedReader.close();
		} catch (IOException error) {
			System.out.println("Invalid File");
		} finally {
			bufferedReader.close();
		}
	}

}
