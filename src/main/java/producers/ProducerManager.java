package producers;

import lombok.AllArgsConstructor;
import producers.strategies.CapitalizeStrategy;
import producers.strategies.PunctuationStrategy;

@AllArgsConstructor
public class ProducerManager {
    private final CapitalizeStrategy capitalizeStrategy;
    private final boolean isPunctuation;

    public String getHashedWord(String singleWord, PunctuationStrategy punctuation,
                                PunctuationStrategy.Position position, int iteration){
        final String capitalizedWord = capitalizeStrategy.doFormat(singleWord);
        final String connectedWord = (isPunctuation)?
                getWordWithPunctuation(capitalizedWord, punctuation, position):capitalizedWord;
        return getWordWithNumbers(connectedWord,iteration);
    }

    public String getHashedWord(String firstWord, String secondWord, PunctuationStrategy punctuation,
                                PunctuationStrategy.Position position, int iteration){
        final String capitalizedFirstWord = capitalizeStrategy.doFormat(firstWord);
        final String capitalizedSecondWord = (capitalizeStrategy.equals(CapitalizeStrategy.WORD_FIRST_LETTER_UP))?
                secondWord
                :capitalizeStrategy.doFormat(secondWord);
        final String connectedWord = (isPunctuation)?
                getWordWithPunctuation(capitalizedFirstWord,capitalizedSecondWord,punctuation,position)
                :capitalizedFirstWord+capitalizedSecondWord;
        final String capitalizedWord = capitalizeStrategy.doFormat(connectedWord);
        return getWordWithNumbers(capitalizedWord,iteration);
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
    private String getWordWithNumbers(String word, int iteration){
        if(iteration == 0){ //if first iteration, we don't want to add any numbers
            return word;
        }
        final int currentNum = iteration-1; //starting adding numbers from 0
        final String currentNumStr = String.valueOf(currentNum);
        final int numOfDigits = currentNumStr.length();
        String leftNum, rightNum;
        final int middleOfNum = numOfDigits/2;
        if(numOfDigits%2==0){
            leftNum = currentNumStr.substring(0,middleOfNum);
        }
        else{
            leftNum = "0" + currentNumStr.substring(0,middleOfNum);
        }
        rightNum = currentNumStr.substring(middleOfNum);
        return leftNum + word + rightNum;
    }
}
