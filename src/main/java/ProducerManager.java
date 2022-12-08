public class ProducerManager {
    private WordStrategy producerStrategy;

    public ProducerManager(WordStrategy strategy) {
        this.producerStrategy = strategy;
    }

    public String getSingleWordToHash(String singleWord, PunctuationStrategy punctuation,
                                      PunctuationStrategy.POSITION position, int iteration){
        String word = connectOneWordByPunctuation(singleWord, punctuation, position);
        switch (producerStrategy){
            case WORD_TO_lOWER_CASE -> word = getWordToLowerCase(word);
            case WORD_TO_UPPER_CASE -> word =getWordToUpperCase(word);
            case WORD_FIRST_LETTER_UP -> word = getFirstLetterToUpperCase(word);
            default -> word = getWordToLowerCase(word);
        }
        return getWordWithNumbers(word,iteration);
    }

    public String getDoubleWordToHash(String firstWord, String secondWord, PunctuationStrategy punctuation,
                                      PunctuationStrategy.POSITION position, int iteration){
        String word = connectTwoWordsByPunctuation(firstWord,secondWord,punctuation,position);
        switch (producerStrategy){
            case WORD_TO_lOWER_CASE -> word = getWordToLowerCase(word);
            case WORD_TO_UPPER_CASE -> word =getWordToUpperCase(word);
            case WORD_FIRST_LETTER_UP -> word = getFirstLetterToUpperCase(word);
            default -> word = getWordToLowerCase(word);
        }
        return getWordWithNumbers(word,iteration);
    }

    private String connectOneWordByPunctuation(String word, PunctuationStrategy punctuation,
                                               PunctuationStrategy.POSITION position){
        String resultWord = "";
        switch (position){
            case FIRST -> resultWord = punctuation.toString() + word;
            case MIDDLE -> resultWord = word + punctuation.toString(); //tutaj trzerba substringa
            case LAST -> resultWord = word + punctuation.toString();
            default -> resultWord = word;
        }
        return resultWord;
    }
    private String connectTwoWordsByPunctuation(String firstWord, String secondWord, PunctuationStrategy punctuation,
                                                PunctuationStrategy.POSITION position){
        String word = "";
        switch (position){
            case FIRST -> word = punctuation.toString() + firstWord + secondWord;
            case MIDDLE -> word = firstWord + punctuation.toString() + secondWord;
            case LAST -> word = firstWord + secondWord + punctuation.toString();
            default -> word = firstWord + secondWord;
        }
        return word;
    }
    private String getWordWithNumbers(String word, int iteration){
        if(iteration == 0){ //if first iteration, we dont want to add any numbers
            return word;
        }
        final int numberToAddToWord = iteration-1; //starting adding numbers from 0
        final int numOfDigits = getNumOfDigits(numberToAddToWord);
        String leftNum;
        String rightNum;
        String numberStr = String.valueOf(numberToAddToWord);
        final int middleOfNum = numberStr.length()/2;
        if(numOfDigits%2==0){
            leftNum = numberStr.substring(0,middleOfNum);
        }
        else{
            leftNum = "0" + numberStr.substring(0,middleOfNum);
        }
        rightNum = numberStr.substring(middleOfNum);
        return leftNum + word + rightNum;
    }
    private String getFirstLetterToUpperCase(String word){
        if(word.length()>1){
            return word.substring(0,1).toUpperCase()+word.substring(1);
        }
        return word.toUpperCase();
    }

    private String getWordToLowerCase(String word){
        return word.toLowerCase();
    }

    private String getWordToUpperCase(String word){
        return word.toUpperCase();
    }

    private int getNumOfDigits(int number){
        return (int)(Math.log10(number)+1);
    }
}
