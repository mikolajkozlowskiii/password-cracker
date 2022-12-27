package producers;

import lombok.AllArgsConstructor;
import producers.strategies.CapitalizeStrategy;
import producers.strategies.NumberStrategy;
import producers.strategies.PunctuationStrategy;

@AllArgsConstructor
public class WordFormater {
    private final CapitalizeStrategy capitalizeStrategy;
    private final NumberStrategy numberStrategy;

    public String getFormattedWord(String singleWord, PunctuationStrategy punctuation,
                                   PunctuationStrategy.Position position, int iteration){
        final String capitalizedWord = capitalizeStrategy.doFormat(singleWord);
        final String connectedWord = getWordWithPunctuation(capitalizedWord, punctuation, position);
        return  numberStrategy.doFormat(connectedWord,iteration);
    }

    public String getFormattedWord(String firstWord, String secondWord, PunctuationStrategy punctuation,
                                   PunctuationStrategy.Position position, int iteration){
        final String capitalizedFirstWord = capitalizeStrategy.doFormat(firstWord);
        final String capitalizedSecondWord = (capitalizeStrategy.equals(CapitalizeStrategy.WORD_FIRST_LETTER_UP))?
                secondWord
                :capitalizeStrategy.doFormat(secondWord);
        final String connectedWord = getWordWithPunctuation(capitalizedFirstWord,capitalizedSecondWord,punctuation,position);
        final String capitalizedWord = capitalizeStrategy.doFormat(connectedWord);
        return numberStrategy.doFormat(capitalizedWord,iteration);
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
    private String getWordWithPunctuation(String firstWord, String secondWord, PunctuationStrategy punctuation,
                                          PunctuationStrategy.Position position){
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
