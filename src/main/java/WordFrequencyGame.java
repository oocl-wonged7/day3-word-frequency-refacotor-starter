import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String SPACE = "\\s+";
    public static final String LINE_BREAK = "\n";

    public String getWordFrequency(String sentence) {
        if (sentence.split(SPACE).length == 1) {
            return sentence + " 1";
        } else {
            try {
                //split the input string with 1 to n pieces of spaces
                List<WordFrequency> wordFrequencies = getInitialWordFrequencies(sentence);

                //get the map for the next step of sizing the same word
                wordFrequencies = getWordFrequencies(wordFrequencies);

                return joinResult(wordFrequencies);
            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }

    private static String joinResult(List<WordFrequency> wordFrequencies) {
        return wordFrequencies.stream().map(wordFrequency -> wordFrequency.getWord() + " " + wordFrequency.getWordCount()).collect(Collectors.joining(LINE_BREAK)).toString();
    }

    private List<WordFrequency> getWordFrequencies(List<WordFrequency> wordFrequencies) {
        Map<String, List<WordFrequency>> wordToWordFrequencies = getListMap(wordFrequencies);
        return wordToWordFrequencies.entrySet().stream().map(entry -> new WordFrequency(entry.getKey(), entry.getValue().size())).sorted((word, nextWord)->nextWord.getWordCount() - word.getWordCount()).toList();
    }

    private static List<WordFrequency> getInitialWordFrequencies(String sentence) {
        String[] words = sentence.split(SPACE);

        List<WordFrequency> wordFrequencies = Arrays.stream(words).map(word-> new WordFrequency(word,1)).toList();
        return wordFrequencies;
    }

    private Map<String, List<WordFrequency>> getListMap(List<WordFrequency> wordFrequencyList) {
        return wordFrequencyList.stream()
                .collect(Collectors.groupingBy(WordFrequency::getWord));
    }
}
