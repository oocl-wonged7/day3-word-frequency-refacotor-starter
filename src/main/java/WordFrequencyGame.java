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
                StringJoiner joiner = joinResult(wordFrequencies);


                return joiner.toString();
            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }

    private static StringJoiner joinResult(List<WordFrequency> wordFrequencies) {
        StringJoiner joiner = new StringJoiner(LINE_BREAK);
        for (WordFrequency wordFrequency : wordFrequencies) {
            String wordFrequencyExpression = wordFrequency.getWord() + " " + wordFrequency.getWordCount();
            joiner.add(wordFrequencyExpression);
        }
        return joiner;
    }

    private List<WordFrequency> getWordFrequencies(List<WordFrequency> wordFrequencies) {
        Map<String, List<WordFrequency>> wordToWordFrequencies = getListMap(wordFrequencies);
        List<WordFrequency> tempWordFrequencies = new ArrayList<>();


        for (Map.Entry<String, List<WordFrequency>> entry : wordToWordFrequencies.entrySet()) {
            WordFrequency wordFrequency = new WordFrequency(entry.getKey(), entry.getValue().size());
            tempWordFrequencies.add(wordFrequency);
        }


        wordFrequencies = tempWordFrequencies;
//                sort the list base on the frequency
        wordFrequencies.sort((word, nextWord) -> nextWord.getWordCount() - word.getWordCount());
        return wordFrequencies;
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
