package components;

import lombok.AllArgsConstructor;
import components.strategies.CapitalizeStrategy;
import components.strategies.NumberStrategy;
import components.strategies.PunctuationStrategy;

@AllArgsConstructor
public class WordFormater {
    private final CapitalizeStrategy capitalizeStrategy;
    private final NumberStrategy numberStrategy;

    public String getFormattedWord(String singleWord, PunctuationStrategy punctuation,
                                   PunctuationStrategy.Position position, int iteration){
        validatePunctuation(singleWord);
        final String capitalizedWord = capitalizeStrategy.doFormat(singleWord);
        final String connectedWord = getWordWithPunctuation(capitalizedWord, punctuation, position);
        return  numberStrategy.doFormat(connectedWord,iteration);
    }

    private void validatePunctuation(String singleWord) {
        if(singleWord == null){
            throw new IllegalArgumentException("word can't be null.");
        }
    }

    public String getFormattedWord(String firstWord, String secondWord, PunctuationStrategy punctuation,
                                   PunctuationStrategy.Position position, int iteration){
        validatePunctuation(firstWord, secondWord);
        final String capitalizedFirstWord = capitalizeStrategy.doFormat(firstWord);
        final String capitalizedSecondWord = (capitalizeStrategy.equals(CapitalizeStrategy.WORD_FIRST_LETTER_UP))?
                secondWord
                :capitalizeStrategy.doFormat(secondWord);
        final String connectedWord = getWordWithPunctuation(capitalizedFirstWord,capitalizedSecondWord,punctuation,position);
        final String capitalizedWord = capitalizeStrategy.doFormat(connectedWord);
        return numberStrategy.doFormat(capitalizedWord,iteration);
    }

    private void validatePunctuation(String firstWord, String secondWord) {
        if (firstWord == null || secondWord == null) {
            throw new IllegalArgumentException("word can't be null.");
        }
    }

    public String getFormattedWord(String singleWord, int iteration){
        final String capitalizedWord = capitalizeStrategy.doFormat(singleWord);
        return numberStrategy.doFormat(capitalizedWord,iteration);
    }

    public String getFormattedWord(String firstWord, String secondWord, int iteration){
        final String capitalizedFirstWord = capitalizeStrategy.doFormat(firstWord);
        final String capitalizedSecondWord = (capitalizeStrategy.equals(CapitalizeStrategy.WORD_FIRST_LETTER_UP))?
                secondWord
                :capitalizeStrategy.doFormat(secondWord);
        final String connectedWord = capitalizedFirstWord+capitalizedSecondWord;
        final String capitalizedWord = capitalizeStrategy.doFormat(connectedWord);
        return numberStrategy.doFormat(capitalizedWord,iteration);
    }

    private String getWordWithPunctuation(String word, PunctuationStrategy punctuation,
                                          PunctuationStrategy.Position position){
        validatePunctuation(word);
        validatePunctuation(punctuation, position);
        String resultWord;
        switch (position){
            case FIRST -> resultWord = punctuation.toString() + word;
            case MIDDLE -> {
                final int length = word.length();
                if(length<2){
                    return word;
                }
                else{
                    return word.substring(0,length/2) + punctuation.toString()
                            + word.substring(length/2);
                }
            }
            case LAST -> resultWord = word + punctuation.toString();
            default -> resultWord = word;
        }
        return resultWord;
    }

    private void validatePunctuation(PunctuationStrategy punctuation, PunctuationStrategy.Position position) {
        if(punctuation == null || position == null){
            throw new IllegalArgumentException("Strategy can't be null.");
        }
    }

    private String getWordWithPunctuation(String firstWord, String secondWord, PunctuationStrategy punctuation,
                                          PunctuationStrategy.Position position){
        validatePunctuation(firstWord,secondWord);
        validatePunctuation(punctuation,position);
        String word;
        switch (position){
            case FIRST -> word = punctuation.toString() + firstWord + secondWord;
            case MIDDLE -> word = firstWord + punctuation.toString() + secondWord;
            case LAST -> word = firstWord + secondWord + punctuation.toString();
            default -> word = firstWord + secondWord;
        }
        return word;
    }
}
