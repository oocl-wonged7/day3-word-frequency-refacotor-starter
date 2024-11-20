import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String SPACE_REGEX = "\\s+";
    public static final String LINE_BREAK = "\n";
    public static final String SPACE = " ";

    public String getWordFrequency(String sentence) {
        if (sentence.split(SPACE_REGEX).length == 1) {
            return sentence + " 1";
        } else {
            try {
                List<WordFrequency> wordFrequencies = getInitialWordFrequencies(sentence);
                wordFrequencies = getWordFrequencies(wordFrequencies);
                return joinResult(wordFrequencies);
            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }

    private static String joinResult(List<WordFrequency> wordFrequencies) {
        return wordFrequencies.stream()
                .map(wordFrequency -> wordFrequency.getWord() + SPACE + wordFrequency.getWordCount()).collect(Collectors.joining(LINE_BREAK))
                .toString();
    }

    private List<WordFrequency> getWordFrequencies(List<WordFrequency> wordFrequencies) {
        Map<String, List<WordFrequency>> wordToWordFrequencies = getWordToWordFrequencies(wordFrequencies);
        return wordToWordFrequencies.entrySet().stream()
                .map(wordToWordFrequencyEntry -> new WordFrequency(wordToWordFrequencyEntry.getKey(), wordToWordFrequencyEntry.getValue().size()))
                .sorted((word, nextWord) -> nextWord.getWordCount() - word.getWordCount())
                .toList();
    }

    private static List<WordFrequency> getInitialWordFrequencies(String sentence) {
        String[] words = sentence.split(SPACE_REGEX);
        return Arrays.stream(words)
                .map(word -> new WordFrequency(word, 1))
                .toList();
    }

    private Map<String, List<WordFrequency>> getWordToWordFrequencies(List<WordFrequency> wordFrequencyList) {
        return wordFrequencyList.stream()
                .collect(Collectors.groupingBy(WordFrequency::getWord));
    }
}
