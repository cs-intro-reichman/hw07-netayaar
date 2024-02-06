public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		str = str.substring(1);

		return str;
	}

	public static char head(String str) {
		char strC = str.charAt(0);

		return strC;
	}

	public static int levenshtein(String word1, String word2) {
		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();

		int w1Length = word1.length();
		int w2Length = word2.length();

		if (w1Length == 0) {
			return w2Length;
		}

		if (w2Length == 0) {
			return w1Length; 
		}

		if (head(word1) == head(word2)) {
			return levenshtein(tail(word1), tail(word2));
		}

		else {
			int one = levenshtein(tail(word1), word2);
			int two = levenshtein(word1, tail(word2));
			int three = levenshtein (tail(word1), tail(word2));

			int min = 1 + Math.min(one, Math.min(two, three));
			return min; 

		}

	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);

		for (int i = 0; i < dictionary.length; i++) {
			dictionary[i] = in.readString();
		}

		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		word = word.toLowerCase();
		String wordNow = word;
		boolean found = false;
		for (int i = 0; i < dictionary.length; i++) {
			int threshNew = levenshtein(word, dictionary[i]);
			
			if ((!found && threshNew <= threshold) || (found && threshNew < threshold)) {
				threshold = threshNew;
				found = true;
				wordNow = dictionary[i];
			}
		}
		return wordNow;
	}
}
