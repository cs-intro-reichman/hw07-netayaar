
public class SpellChecker {

	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		// Your code goes here
		String tail = str.substring(1);
		
		return tail;
	}

	public static int levenshtein(String word1, String word2) {
		// Your code goes here
		String w1 = word1.toLowerCase();
		String w2 = word2.toLowerCase();
		int lengthW1 = w1.length();
		int lengthW2 = w2.length();
		if (word1.isEmpty() && word2.isEmpty()) {
			return 0;
		}
		// if one string is empty- returns the length of the word that is not empty
		if (word1.isEmpty() && !word2.isEmpty()) {
			return lengthW2;
		}
		if (!word1.isEmpty() && word2.isEmpty()) {
			return lengthW1;

		}
		if (w1.charAt(0) == w2.charAt(0)) {
			return levenshtein(tail(w1), tail(w2));
		}
		else {
			int cost = (word1.charAt(0) == word2.charAt(0)) ? 0 : 1;
		// cost specifically relates to the substitution operation
		
		// one, two and three are the deletation, insertation and substitution
			int deletation = levenshtein(tail(w1), w2);
			int insertation = levenshtein(w1, tail(w2));
			int substitution = levenshtein(tail(w1), tail(w2)) + cost;
		// calculates the minimum between the operations
			return Math.min(deletation, Math.min(insertation, substitution));
	}}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);

		// Your code here
		for (int i = 0; i < dictionary.length; i++) {
			String word = in.readString();
			dictionary[i] = word;
		}

		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
				// Your code goes here
	int bestValue = threshold;
	String bestMatch = word.toLowerCase();
	for (int i = 0; i < dictionary.length; i++) {
		int currentValue = levenshtein(word, dictionary[i]);
		if (currentValue < bestValue) {
			bestValue = currentValue;
			bestMatch = dictionary[i];}
			
		}if (bestValue >= threshold) {
			return bestMatch;
		} else{
			 return word;
		} 
		}
			
	}

