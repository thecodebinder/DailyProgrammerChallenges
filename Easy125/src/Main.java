import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.io.FileUtils;

public class Main {
	private static String letters = "abcdefghijklmnopqrstuvwxyz";
	private static int wordCount;
	private static int symbolCount = 0;
	private static int letterCount = 0;
	private static String[] topThreeLetters = {null,null,null};
	private static String[] topThreeWords = {null,null,null};
	private static Map<String, Integer> wordMap = new HashMap<>();
	private static Map<String, Integer> letterMap = new HashMap<>();
	
	public static void main(String[] args) {
		File file = new File(args[0]);
		try {
			// split into paragraphs
			String text = FileUtils.readFileToString(file);
			for (int i = 0; i < text.length(); i++) {
				text = text.replaceAll("\t", "");
				text = text.replaceAll("\r","");
				text = text.replaceAll("\n","");
			}
			//count number of words total and individual
			countWords(text);
			//count number of symbols and letters total and individual
			countSymbolsandLetters(text);
			//finds top three words and letters
			findTopThrees();
			
			System.out.println(wordCount + " words");
			System.out.println(letterCount + " letters");
			System.out.println(symbolCount + " symbols");
		
			System.out.print("Top three most common words: ");
			for (int i = 0; i < 3; i++) {
				System.out.print(topThreeWords[i] + " ");
			}
			System.out.println();
			
			System.out.print("Top three most common letters: ");
			for (int i = 0; i < 3; i++) {
				System.out.print(topThreeLetters[i]+ " ");
			}
			System.out.println();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

	private static void findTopThrees() {
		int highestCount = 0;
		for (int i = 0; i < 3; i++) {
			for (Iterator<String> iterator = letterMap.keySet().iterator(); iterator.hasNext();) {
				String word = iterator.next().toString();
				if (letterMap.get(word) > highestCount) {
					highestCount = letterMap.get(word);
					topThreeLetters[i] = word;
				}
			}
			highestCount = 0;
			letterMap.remove(topThreeLetters[i]);
		}
		
		for (int i = 0; i < 3; i++) {
			for (Iterator<String> iterator = wordMap.keySet().iterator(); iterator.hasNext();) {
				String word = iterator.next().toString();
				if (wordMap.get(word) > highestCount) {
					highestCount = wordMap.get(word);
					topThreeWords[i] = word;
				}
			}
			highestCount = 0;
			wordMap.remove(topThreeWords[i]);
		}
	}

	private static void countSymbolsandLetters(String text) {
//		for(String paragraph: text){
//			paragraph.trim();
			for(char symbol: text.toCharArray()){
				String glyph = Character.toString(symbol).toLowerCase();
				if(letters.contains(glyph)) {
					letterCount++;
					if(letterMap.containsKey(glyph)){
						int count = letterMap.get(glyph);
						count ++;
						letterMap.put(glyph, count);
					} else {
						letterMap.put(glyph, 1);
					}
				}else if(symbol != ' ') symbolCount++;
			}
		}
//	}

	private static void countWords(String text) {
		wordCount = 0;
//		for (String paragraph : text) {
			for (String word : text.split(" ")) {
				word = word.replaceAll("\\p{Punct}", "");
				word = word.replaceAll("\\s", "");
				if(!word.equals("")) wordCount++;
				if(wordMap.containsKey(word)){
					int count = wordMap.get(word);
					count++;
					wordMap.put(word, count);
				} else {
					wordMap.put(word, 1);
				}
			}	
		}
	}
//}
