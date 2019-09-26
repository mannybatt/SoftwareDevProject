import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class wordAnalyzer {

	public static void main(String args[]) {
		String fileName = "";
		Map<String, Integer> wordMap = new HashMap<>();

		Scanner scan = new Scanner(System.in);

		System.out.println("Text Analyzer\n");
		System.out.println("Please type a filename: ");
		fileName = scan.next();

		File file = new File(fileName);

		try {
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found!");
		}

		while (scan.hasNext()) {
			String word = scan.next().toLowerCase();
			word = word.replaceAll("\\p{Punct}", "");
			// System.out.println(word);

			if (wordMap.containsKey(word)) {
				wordMap.put(word, wordMap.get(word) + 1);
			} else {
				wordMap.put(word, 1);
			}
		}

		TreeMap<String, Integer> sortedWordMap = new TreeMap<>();
		sortedWordMap.putAll(wordMap);

		String[] top20 = new String[20];
		int[] top20Quantity = new int[20];
		int i = 0;

		for (int j = 10000; j > 0; j--) {
			for (Map.Entry<String, Integer> singleWord : sortedWordMap.entrySet()) {
				String key = singleWord.getKey();
				Integer value = singleWord.getValue();

				if (value == j) {
					top20[i] = key;
					top20Quantity[i] = value;
					i++;
				}
				if (i == 20) {
					j = 0;
					break;
				}
			}
		}

		System.out.println("\nHere are the 20 most commom words found in " + fileName + "\n\n");
		System.out.println("Word\tQuantity   Place");

		for (i = 0; i < 20; i++) {
			System.out.println(top20[i] + "\t  " + top20Quantity[i] + "\t    (" + (i + 1) + ")");
		}
	}
}