import java.util.*;

public class WordFrequencyGame {
    public String getWordFrequency(String sentence) {
        if (sentence.split("\\s+").length == 1) {
            return sentence + " 1";
        } else {
            try {
                //split the input string with 1 to n pieces of spaces
                String[] words = sentence.split("\\s+");
//                create a input list from the arr
                List<WordFrequency> wordFrequencies = new ArrayList<>();
                for (String word : words) {
                    WordFrequency wordFrequency = new WordFrequency(word, 1);
                    wordFrequencies.add(wordFrequency);
                }
                //get the map for the next step of sizing the same word
                Map<String, List<WordFrequency>> wordToWordFrequencies = getListMap(wordFrequencies);
                List<WordFrequency> tempWordFrequencies = new ArrayList<>();
                for (Map.Entry<String, List<WordFrequency>> entry : wordToWordFrequencies.entrySet()) {
                    WordFrequency wordFrequency = new WordFrequency(entry.getKey(), entry.getValue().size());
                    tempWordFrequencies.add(wordFrequency);
                }
                wordFrequencies = tempWordFrequencies;
//                sort the list base on the frequency
                wordFrequencies.sort((word, nextWord) -> nextWord.getWordCount() - word.getWordCount());
                StringJoiner joiner = new StringJoiner("\n");
                for (WordFrequency wordFrequency : wordFrequencies) {
                    String wordFrequencyExpression = wordFrequency.getWord() + " " + wordFrequency.getWordCount();
                    joiner.add(wordFrequencyExpression);
                }
                return joiner.toString();
            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }

    private Map<String, List<WordFrequency>> getListMap(List<WordFrequency> wordFrequencyList) {
        Map<String, List<WordFrequency>> wordToWordFrequencies = new HashMap<>();
        for (WordFrequency wordFrequency : wordFrequencyList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!wordToWordFrequencies.containsKey(wordFrequency.getWord())) {
                ArrayList wordFrequencies = new ArrayList<>();
                wordFrequencies.add(wordFrequency);
                wordToWordFrequencies.put(wordFrequency.getWord(), wordFrequencies);
            } else {
                wordToWordFrequencies.get(wordFrequency.getWord()).add(wordFrequency);
            }
        }
        return wordToWordFrequencies;
    }
}
