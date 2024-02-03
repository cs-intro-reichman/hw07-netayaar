
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
		if (tail.length() == 0) {
			return "";

		}
		return tail;
	}

	public static int levenshtein(String word1, String word2) {
		// Your code goes here
		String w1 = word1.toLowerCase();
		String w2 = word2.toLowerCase();
		int lengthW1 = w1.length();
		int lengthW2 = w2.length();
		int count = 0;
		if (lengthW1 == 0 && lengthW2 == 0) {
			return 0;
		}
		// if one string is empty- returns the length of the word that is not empty
		if (lengthW1 == 0 && lengthW2 != 0) {
			return lengthW2;
		}
		if (lengthW2 == 0 && lengthW1 != 0) {
			return lengthW1;

		}
		if (w1.charAt(0) == w2.charAt(0)) {
			return levenshtein(tail(w1), tail(w2));
		}
		// cost specifically relates to the substitution operation
		int cost = 1;
		// one, two and three are the deletation, insertation and substitution
		int deletation = levenshtein(tail(w1), w2);
		int insertation = levenshtein(w1, tail(w2));
		int substitution = levenshtein(tail(w1), tail(w2)) + cost;
		// calculates the minimum between the operations
		int min = Math.min(deletation, insertation);
		int finalMin = Math.min(min, substitution);
		return finalMin;
	}

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
	String bestMath = word;
	for (int i = 0; i < dictionary.length; i++) {
		int currentValue = levenshtein(word, dictionary[i]);
		if (currentValue < bestValue) {
		bestValue = currentValue;
		bestMath = dictionary[i];}
			
		}if (bestValue > threshold) {
			return bestMath;
		} else{ return word;} }
			
	}
	
