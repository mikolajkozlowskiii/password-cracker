package producers.singleword;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import producers.WordFormater;
import producers.strategies.CapitalizeStrategy;
import producers.strategies.NumberStrategy;
import producers.strategies.PunctuationStrategy;
import static org.assertj.core.api.Assertions.assertThat;

class SingleWordLowerCaseBothSidesFormaterTest {
    private WordFormater wordFormater;
    @BeforeEach
    void setUp() {
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.BOTH_SIDES);
    }

    @Test
    void getFormattedSingleWordWithPunctuationAtFirstPositiveIteration(){
        final String word = "password";
        final int iteration = 2;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.FIRST;

        final String actualFormattedWord = wordFormater.getFormattedWord(word,punctuation,position,iteration);
        final String expectedFormatteddWord = "0password_1";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormattedSingleWordWithPunctuationAtMiddlePositiveIteration(){
        final String word = "password";
        final int iteration = 2;
        final PunctuationStrategy punctuation = PunctuationStrategy.HYPHEN;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.MIDDLE;

        final String actualFormattedWord = wordFormater.getFormattedWord(word,punctuation,position,iteration);
        final String expectedFormatteddWord = "0pass-word1";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormattedSingleWordWithPunctuationAtLastPositiveIteration(){
        final String word = "password";
        final int iteration = 2;
        final PunctuationStrategy punctuation = PunctuationStrategy.DOLLAR;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;

        final String actualFormattedWord = wordFormater.getFormattedWord(word,punctuation,position,iteration);
        final String expectedFormatteddWord = "0password$1";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormattedSingleWordWithPunctuationPositiveIterationOneDigit(){

    }
    @Test
    void getFormattedSingleWordWithPunctuationPositiveIterationTwoDigits(){

    }
    @Test
    void getFormattedSingleWordWithPunctuationPositiveIterationThreeDigits(){

    }
    @Test
    void getFormattedSingleWordWithPunctuationPositiveIterationFourDigits(){

    }
    @Test
    void getFormattedSingleWordWithoutPunctuationPositiveIteration(){

    }
    @Test
    void getFormattedSingleWordNullWithPunctuationPositiveIteration(){

    }
    @Test
    void getFormattedSingleWordWithNullPunctuationPositiveIteration(){

    }
    @Test
    void getFormattedSingleWordWithPunctuationAllParamsNullPositiveIteration(){

    }

    @Test
    void getFormattedSingleWordWithPunctuationAtFirstZeroIteration(){

    }
    @Test
    void getFormattedSingleWordWithPunctuationAtMiddleZeroIteration(){

    }
    @Test
    void getFormattedSingleWordWithPunctuationAtLastZeroIteration(){

    }
    @Test
    void getFormattedSingleWordWithoutPunctuationZeroIteration(){

    }
    @Test
    void getFormattedSingleWordNullWithPunctuationZeroIteration(){

    }
    @Test
    void getFormattedSingleWordWithNullPunctuationZeroIteration(){

    }
    @Test
    void getFormattedSingleWordWithPunctuationAllParamsNullZeroIteration(){

    }

    @Test
    void getFormattedSingleWordWithPunctuationAtFirstNegativeIteration(){

    }

    @Test
    void getFormattedSingleWordWithPunctuationAtMiddleNegativeIteration(){

    }
    @Test
    void getFormattedSingleWordWithPunctuationAtLastNegativeIteration(){

    }
    @Test
    void getFormattedSingleWordWithoutPunctuationNegativeIteration(){

    }
    @Test
    void getFormattedSingleWordNullWithPunctuationNegativeIteration(){

    }
    @Test
    void getFormattedSingleWordWithNullPunctuationNegativeIteration(){

    }


}