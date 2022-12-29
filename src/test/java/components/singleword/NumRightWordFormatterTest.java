package components.singleword;

import components.WordFormater;
import components.strategies.CapitalizeStrategy;
import components.strategies.NumberStrategy;
import components.strategies.PunctuationStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NumRightWordFormatterTest {
    private WordFormater wordFormater;

    @BeforeEach
    void setUp() {
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.BOTH_SIDES);
    }
    @Test
    void getFormatted_SingleWordWithPunctuationAtFirstPositiveIteration_createdWord(){
        final String word = "password";
        final int iteration = 2;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.FIRST;

        final String actualFormattedWord = wordFormater.getFormattedWord(word,punctuation,position,iteration);
        final String expectedFormatteddWord = "_password2";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_SingleWordWithPunctuationAtMiddlePositiveIteration_createdWord(){
        final String word = "password";
        final int iteration = 2;
        final PunctuationStrategy punctuation = PunctuationStrategy.HYPHEN;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.MIDDLE;

        final String actualFormattedWord = wordFormater.getFormattedWord(word,punctuation,position,iteration);
        final String expectedFormatteddWord = "pass-word2";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_SingleWordWithPunctuationAtLastPositiveIteration_createdWord(){
        final String word = "password";
        final int iteration = 2;
        final PunctuationStrategy punctuation = PunctuationStrategy.DOLLAR;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;

        final String actualFormattedWord = wordFormater.getFormattedWord(word,punctuation,position,iteration);
        final String expectedFormatteddWord = "password$2";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_SingleWordWithPunctuationPositiveIterationOneDigit_createdWord(){
        final String word = "password";
        final int iteration = 2;
        final PunctuationStrategy punctuation = PunctuationStrategy.DOLLAR;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;

        final String actualFormattedWord = wordFormater.getFormattedWord(word,punctuation,position,iteration);
        final String expectedFormatteddWord = "password$2";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_SingleWordWithPunctuationPositiveIterationTwoDigits_createdWord(){
        final String word = "password";
        final int iteration = 21;
        final PunctuationStrategy punctuation = PunctuationStrategy.DOLLAR;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;

        final String actualFormattedWord = wordFormater.getFormattedWord(word,punctuation,position,iteration);
        final String expectedFormatteddWord = "password$21";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_SingleWordWithPunctuationPositiveIterationThreeDigits_createdWord(){
        final String word = "password";
        final int iteration = 321;
        final PunctuationStrategy punctuation = PunctuationStrategy.DOLLAR;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;

        final String actualFormattedWord = wordFormater.getFormattedWord(word,punctuation,position,iteration);
        final String expectedFormatteddWord = "password$321";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_SingleWordWithPunctuationPositiveIterationFourDigits_createdWord(){
        final String word = "password";
        final int iteration = 4321;
        final PunctuationStrategy punctuation = PunctuationStrategy.DOLLAR;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;

        final String actualFormattedWord = wordFormater.getFormattedWord(word,punctuation,position,iteration);
        final String expectedFormatteddWord = "password$4321";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_SingleWordWithoutPunctuationPositiveIteration_createdWord(){
        final String word = "password";
        final int iteration = 21;

        final String actualFormattedWord = wordFormater.getFormattedWord(word,iteration);
        final String expectedFormatteddWord = "password21";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_SingleWordNullWithPunctuationPositiveIteration_thrownIllegalArgEx(){
        final String word = null;
        final int iteration = 4;
        final PunctuationStrategy punctuation = PunctuationStrategy.DOLLAR;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;

        assertThatThrownBy(()->wordFormater.getFormattedWord(word,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_SingleWordWithNullPunctuationPositiveIteration_thrownIllegalArgEx(){
        final String word = "password";
        final int iteration = 4321;
        final PunctuationStrategy punctuation = null;
        final PunctuationStrategy.Position position = null;

        assertThatThrownBy(()->wordFormater.getFormattedWord(word,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_SingleWordWithPunctuationAllParamsNullPositiveIteration_thrownIllegalArgEx(){
        final String word = null;
        final int iteration = 4321;
        final PunctuationStrategy punctuation = null;
        final PunctuationStrategy.Position position = null;

        assertThatThrownBy(()->wordFormater.getFormattedWord(word,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getFormatted_SingleWordWithPunctuationAtFirstZeroIteration_createdWord(){
        final String word = "password";
        final int iteration = 0;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.FIRST;

        final String actualFormattedWord = wordFormater.getFormattedWord(word,punctuation,position,iteration);
        final String expectedFormatteddWord = "_password0";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_SingleWordWithPunctuationAtMiddleZeroIteration_createdWord(){
        final String word = "password";
        final int iteration = 0;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.MIDDLE;

        final String actualFormattedWord = wordFormater.getFormattedWord(word,punctuation,position,iteration);
        final String expectedFormatteddWord = "pass_word0";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_SingleWordWithPunctuationAtLastZeroIteration_createdWord(){
        final String word = "password";
        final int iteration = 0;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;

        final String actualFormattedWord = wordFormater.getFormattedWord(word,punctuation,position,iteration);
        final String expectedFormatteddWord = "password_0";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_SingleWordWithoutPunctuationZeroIteration_createdWord(){
        final String word = "password";
        final int iteration = 0;

        final String actualFormattedWord = wordFormater.getFormattedWord(word,iteration);
        final String expectedFormatteddWord = "password0";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_SingleWordNullWithPunctuationZeroIteration_thrownIllegalArgEx(){
        final String word = null;
        final int iteration = 0;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;

        assertThatThrownBy(()->wordFormater.getFormattedWord(word,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_SingleWordWithNullPunctuationZeroIteration_thrownIllegalArgEx(){
        final String word = "password";
        final int iteration = 0;
        final PunctuationStrategy punctuation = null;
        final PunctuationStrategy.Position position = null;

        assertThatThrownBy(()->wordFormater.getFormattedWord(word,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_SingleWordWithPunctuationAllParamsNullZeroIteration_thrownIllegalArgEx(){
        final String word = null;
        final int iteration = 0;
        final PunctuationStrategy punctuation = null;
        final PunctuationStrategy.Position position = null;

        assertThatThrownBy(()->wordFormater.getFormattedWord(word,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getFormatted_SingleWordWithPunctuationAtFirstNegativeIteration_thrownIllegalArgEx(){
        final String word = "password";
        final int iteration = -1;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.FIRST;

        assertThatThrownBy(()->wordFormater.getFormattedWord(word,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getFormatted_SingleWordWithPunctuationAtMiddleNegativeIteration_thrownIllegalArgEx(){
        final String word = "password";
        final int iteration = -4;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.MIDDLE;

        assertThatThrownBy(()->wordFormater.getFormattedWord(word,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_SingleWordWithPunctuationAtLastNegativeIteration_thrownIllegalArgEx(){
        final String word = "password";
        final int iteration = -3;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;

        assertThatThrownBy(()->wordFormater.getFormattedWord(word,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_SingleWordWithoutPunctuationNegativeIteration_thrownIllegalArgEx(){
        final String word = "password";
        final int iteration = -2;

        assertThatThrownBy(()->wordFormater.getFormattedWord(word,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_SingleWordNullWithPunctuationNegativeIteration_thrownIllegalArgEx(){
        final String word = "password";
        final int iteration = -1;
        final PunctuationStrategy punctuation = null;
        final PunctuationStrategy.Position position =null;

        assertThatThrownBy(()->wordFormater.getFormattedWord(word,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_SingleWordWithNullPunctuationNegativeIteration_thrownIllegalaArgEx(){
        final String word = "password";
        final int iteration = -4;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.MIDDLE;

        assertThatThrownBy(()->wordFormater.getFormattedWord(word,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }


}
