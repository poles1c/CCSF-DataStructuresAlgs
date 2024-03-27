package module7Homework;

import java.util.*;

public class HomeworkM7Driver {

	//CP author 3/18/2024
	private static boolean qNotFollowedByUHelper(String word, int leftIndex, int rightIndex) {
		String lowerCaseWord = word.toLowerCase();

		// if string empty false
		if (lowerCaseWord.length() == 0) {
			return false;

			// if string 1 character and it's q true
		} else if (lowerCaseWord.length() == 1 && lowerCaseWord.charAt(0) == 'q') {
			return true;

			// if first character in string is q and not followed by u true
		} else if (lowerCaseWord.charAt(0) == 'q' && lowerCaseWord.charAt(1) != 'u') {
			return true;

			// if the last letter in the string is q true
		} else if (lowerCaseWord.charAt(lowerCaseWord.length() - 1) == 'q') {
			return true;

			// examine all letters from front to back for a q not followed by a u
		} else if (leftIndex < rightIndex) { // base case for Strings will multiple characters
			if (lowerCaseWord.charAt(leftIndex) == 'q' && lowerCaseWord.charAt(leftIndex + 1) != 'u') {
				return true;
			} else {
				// key take away here is that you can return a non boolean value here because ultimately if a q not followed
				//by a u is not found then else will return false
				return qNotFollowedByUHelper(word, leftIndex + 1, rightIndex);
				
			}

		} else {
			return false;
		}

	}
	//CP author 3/18/2024
	public static boolean qNotFollowedByU(String word) {
		return qNotFollowedByUHelper(word, 0, word.length() - 1);
	}
	//CP author 3/18/2024
	private static void arrayReverseHelper(int[] array, int leftIndex, int rightIndex) {
		if (array.length == 0 || array.length == 1) {
			// base case 1: if the length of the array is 0 or 1 end method nothing to move
		} else if (leftIndex < rightIndex) {
			int newRightVal = array[leftIndex];
			array[leftIndex] = array[rightIndex];
			array[rightIndex] = newRightVal;
			arrayReverseHelper(array, leftIndex + 1, rightIndex - 1);

		} else {
			// base case 2 implicit: if the leftIndex is > rightIndex end method
		}

	}
	//CP author 3/18/2024
	public static void arrayReverse(int[] array) {
		arrayReverseHelper(array, 0, array.length - 1);
	}

	public static int countPositives(Multiset<Integer> set) {
		// YOUR EXTRA CREDIT CODE HERE
		return 0; // placeholder: delete and replace when you write your own method
	}

	private static boolean allTestsPassed = true;

	public static void main(String[] args) {

		System.out.println("\n-----------------------------TESTING ARRAY REVERSER-----------------------------");
		// parameter 1: the array to be reversed
		// parameter 2: a description of the test
		testArrayReverse(new int[] { 1, 2, 3, 4, 5 }, "odd length array");
		testArrayReverse(new int[] { 1, 2, 3, 4, 5, 6 }, "even length array");
		testArrayReverse(new int[] { 7, 8 }, "array length 2");
		testArrayReverse(new int[] { 5 }, "array length 1");
		testArrayReverse(new int[] {}, "empty array");

		System.out.println("\n-----------------------------TESTING Q WITHOUT U-----------------------------");
		System.out
				.println("Does the word contain a \'q\' that is NOT immediately followed by a \'u\'? These words do!");
		System.out.println(
				"Note that they might *also* have a \"qu\" in them- they still pass the test because they have a \'q\' NOT followed by a \'u\'!");
		// parameter 1: the word to test
		// parameter 2: the expected result (true if the word contains a q that is NOT
		// immediately followed by a u)
		// parameter 3: a description of the test
		testQNotFollowedByU("qat", true, "q-not-followed-by-u at the beginning of the word");
		testQNotFollowedByU("cinq", true, "q-not-followed-by-u at the end of the word; even length");
		testQNotFollowedByU("drinq", true, "q-not-followed-by-u at the end of the word; odd length");
		testQNotFollowedByU("abqc", true, "q-not-followed-by-u in the middle of the word, even length");
		testQNotFollowedByU("abqcd", true, "q-not-followed-by-u in the middle of the word, odd length");
		testQNotFollowedByU("squaq", true, "q-not-followed-by-u in a word that also has a \"qu\" before it");
		testQNotFollowedByU("bubqb", true, "q-not-followed-by-u in a word that also has a \"qu\" before it");
		testQNotFollowedByU("qaqu", true, "q-not-followed-by-u in a word that also has a \"qu\" after it");
		testQNotFollowedByU("quqa", true, "q-not-followed-by-u in a word that also has a \"qu\" after it");
		testQNotFollowedByU("qiteu", true, "q-not-followed-by-u right away, but with a u later on, odd length");
		testQNotFollowedByU("qeut", true, "q-not-followed-by-u right away, but with a u later on, even length ");
		testQNotFollowedByU("q", true, "q all on its own- single letter");
		testQNotFollowedByU("qq", true, "q all on its own- single letter");
		testQNotFollowedByU("uq", true, "q all on its own- single letter");
		testQNotFollowedByU("QAT", true, "q-not-followed-by-u in caps");

		System.out.println(
				"\nThese words fail the test. They either have no \'q\' or they have a \'q\' that IS immediately followed by a \'u\'.");
		testQNotFollowedByU("hello", false, "no q; odd length");
		testQNotFollowedByU("cats", false, "no q; even length");
		testQNotFollowedByU("bulb", false, "no q but has a u; odd length");
		testQNotFollowedByU("puts", false, "no q but has a u; even length");
		testQNotFollowedByU("a", false, "single letter, not a q");
		testQNotFollowedByU("", false, "empty string");
		testQNotFollowedByU("quite", false, "q-followed-by-u at the beginning of the word");
		testQNotFollowedByU("equal", false, "q-followed-by-u in the middle of a word; odd length");
		testQNotFollowedByU("aqua", false, "q-followed-by-u in the middle of a word; even length");
		testQNotFollowedByU("abcqu", false, "q-followed-by-u q followed by u at the end of the word, odd length");
		testQNotFollowedByU("abcdqu", false, "q-followed-by-u q followed by u at the end of the word, even length");
		testQNotFollowedByU("qu", false, "q-followed-by-u and nothing else");
		testQNotFollowedByU("QUOTE", false, "q-followed-by-u in all-caps");

		System.out.println("\n-----------------------------TESTING GET OCCURRENCES OF OF-----------------------------");
		// parameter 1: the contents of the set
		// parameter 2: the target value
		// parameter 3: the expected return value- how often the target appears in the
		// set
		// parameter 4: a description of the test
		testGetOccurrencesOf(new Integer[] {}, 12, 0, "empty set");
		testGetOccurrencesOf(new Integer[] { 13 }, 13, 1, "singleton set that contains the target");
		testGetOccurrencesOf(new Integer[] { 13 }, 5, 0, "singleton set that does not contain the target");
		testGetOccurrencesOf(new Integer[] { 15, 2, 5, 4, 7, 5 }, 15, 1, "target is in the set at the beginning");
		testGetOccurrencesOf(new Integer[] { 5, 2, 5, 4, 7, 25 }, 25, 1, "target is in the set at the end");
		testGetOccurrencesOf(new Integer[] { 5, 2, 5, 4, 7, 5 }, 4, 1, "target is in the middle");
		testGetOccurrencesOf(new Integer[] { 5, 2, 5, 4, 7, 5 }, 5, 3, "target is in the set multiple times");
		testGetOccurrencesOf(new Integer[] { 5, 2, 5, 4, 7, 5 }, 8, 0, "target not in the set, even length");
		testGetOccurrencesOf(new Integer[] { 5, 2, 5, 4, 7, 5, 2 }, 8, 0, "target not in the set, odd length");
		testGetOccurrencesOf(new String[] { "a", "b", "c", "d", "e", "a" }, new String("a"), 2, "test with Strings");

		System.out.println(
				"\n-----------------------------TESTING EXTRA CREDIT COUNT POSITIVES-----------------------------");
		// parameter 1: the contents of the set
		// parameter 2: the expected result- the number of positives
		// parameter 3: a description of the test
		testCountPositives(new int[] { 2, -1, 3, 5, -2, 4, 1 }, 5, "odd number of elements");
		testCountPositives(new int[] { 1, -4, -2, 5, 3, -1, 2 }, 4, "even number of elements");
		testCountPositives(new int[] { -3, -4, -2, -5, -8 }, 0, "no positives");
		testCountPositives(new int[] { 6, 4, 3, 2, 1, 7, 9 }, 7, "all positives");
		testCountPositives(new int[] {}, 0, "empty set");
		testCountPositives(new int[] { 6 }, 1, "singleton set with positive");
		testCountPositives(new int[] { -6 }, 0, "singleton set without positive");

		System.out.println("\n\n-----------------------------TESTING COMPLETE-----------------------------");
		if (allTestsPassed) {
			System.out.println(
					"----------Summary---------- \nAll automated tests have passed. \nBe sure to manually look at the output.\nBe sure to manually review your code for style and efficiency.");
		} else {
			System.out.flush();
			System.err.println(
					"**********Summary********** ERROR: There is failure in at least one automated test. Review the output above for details.");
		}

	}

	/*----------------------------------------------------------------------------------------------------------*/
	/* TESTER METHODS */
	/*----------------------------------------------------------------------------------------------------------*/
	/*
	 * The methods below are designed to help support the tests cases run from main.
	 * You don't need to use, modify, or understand these methods. You can safely
	 * ignore them. :)
	 * 
	 * Also, you can ignore the use of generics in the tester methods. These methods
	 * use generics at a level beyond which we use in our class. I only use them
	 * here to make this a robust and useful testing file. You are NOT required to
	 * understand the use of generics in this way.
	 */
	public static void testArrayReverse(int[] array, String testDescription) {
		int[] arrayToReverse = Arrays.copyOf(array, array.length);
		int[] originalArray = Arrays.copyOf(array, array.length);
		int[] correctReverseArray = new int[array.length];
		for (int i = array.length - 1, j = 0; i >= 0; i--, j++) {
			correctReverseArray[j] = originalArray[i];
		}
		arrayReverse(arrayToReverse);
		System.out.println("\nOriginal array  = " + Arrays.toString(originalArray));
		System.out.println("Expected result = " + Arrays.toString(correctReverseArray));
		System.out.println("  Actual result = " + Arrays.toString(arrayToReverse));

		if (!Arrays.equals(correctReverseArray, arrayToReverse)) {
			allTestsPassed = false;
			System.out.println("**********TEST FAILED: " + testDescription + "\n");
		}
	}

	public static void testQNotFollowedByU(String word, boolean expectedResult, String testDescription) {
		boolean actualResult = qNotFollowedByU(word);
		System.out.println("\n\t   Word = " + word);
		System.out.println("Expected result = " + expectedResult + "\n  Actual result = " + actualResult);
		if (actualResult != expectedResult) {
			allTestsPassed = false;
			System.out.println("**********TEST FAILED: " + testDescription + "\n");
		}
	}

	public static <T> LinkedMultiset<T> createCopySet(LinkedMultiset<T> set) {
		LinkedMultiset<T> copy = new LinkedMultiset<T>();
		LinkedMultiset<T> tmp = new LinkedMultiset<T>();

		while (!set.isEmpty()) {
			T element = set.remove();
			copy.add(element);
			tmp.add(element);
		}
		while (!tmp.isEmpty()) {
			set.add(tmp.remove());
		}
		return copy;
	}

	public static <T> T[] setToArray(LinkedMultiset<T> set) {
		LinkedMultiset<T> copy = createCopySet(set);
		T[] array = (T[]) new Comparable[set.size()];
		int i = 0;
		while (!copy.isEmpty()) {
			array[i] = copy.remove();
			i++;
		}
		return array;
	}

	public static <T> void testGetOccurrencesOf(T[] setContents, T value, int expectedResult, String testDescription) {
		LinkedMultiset<T> originalMultiset = new LinkedMultiset<T>();
		LinkedMultiset<T> copyMultiset = new LinkedMultiset<T>();
		for (T n : setContents) {
			originalMultiset.add(n);
			copyMultiset.add(n);
		}
		int actualResult = originalMultiset.getOccurrencesOf(value);
		System.out.println(
				"\nMultiset contains: " + Arrays.toString(setContents) + "\nCounting occurrences of: " + value);
		System.out.println("Expected result=" + expectedResult + "\n  Actual result=" + actualResult);

		if (actualResult != expectedResult) {
			allTestsPassed = false;
			System.out.println("**********TEST FAILED: " + testDescription + "\n");
		}
		Object[] originalMultisetContentsAfterRemove = setToArray(originalMultiset);
		Arrays.sort(originalMultisetContentsAfterRemove);
		Object[] copyMultisetContents = setToArray(copyMultiset);
		Arrays.sort(copyMultisetContents);

		if (!Arrays.equals(originalMultisetContentsAfterRemove, copyMultisetContents)) {
			allTestsPassed = false;
			System.out.println("**********TEST FAILED: " + testDescription);
			System.out.println("   The set has been modified.\n\t   Sorted contents after invoking getOccurrencesOf: "
					+ Arrays.toString(originalMultisetContentsAfterRemove) + "\n");
		}
	}

	public static void testCountPositives(int[] setContents, int expectedResult, String testDescription) {
		LinkedMultiset<Integer> originalMultiset = new LinkedMultiset<Integer>();
		LinkedMultiset<Integer> copyMultiset = new LinkedMultiset<Integer>();
		for (int n : setContents) {
			originalMultiset.add(n);
			copyMultiset.add(n);
		}
		int actualResult = countPositives(originalMultiset);
		Object[] originalMultisetContentsAfterCount = setToArray(originalMultiset);
		Arrays.sort(originalMultisetContentsAfterCount);
		Object[] copyMultisetContents = setToArray(copyMultiset);
		Arrays.sort(copyMultisetContents);

		System.out.println("\n    Multiset contains: " + Arrays.toString(copyMultisetContents));
		System.out.println("Expected result = " + expectedResult + "\n  Actual result = " + actualResult);

		if (actualResult != expectedResult) {
			allTestsPassed = false;
			System.out.println("**********TEST FAILED: " + testDescription + "\n");
		}
		if (!Arrays.equals(originalMultisetContentsAfterCount, copyMultisetContents)) {
			allTestsPassed = false;
			System.out.println("**********TEST FAILED: " + testDescription);
			System.out.println("   The set has been modified.\n   Sorted contents after invoking countPositives: "
					+ Arrays.toString(originalMultisetContentsAfterCount) + "\n");
		}
	}
}
