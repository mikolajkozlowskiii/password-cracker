package components.doubleword;

import components.WordFormater;
import components.strategies.CapitalizeStrategy;
import components.strategies.NumberStrategy;
import components.strategies.PunctuationStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NumBothWordFormatterTest {
    private WordFormater wordFormater;

    @BeforeEach
    void setUp() {
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.BOTH_SIDES);
    }

    @Test
    void getFormatted_DoubleWordWithPunctuationAtFirstPositiveIteration_createdWord(){
        final String firstWord= "password";
        final String secondWord= "car";
        final int iteration = 2;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.FIRST;
        

        final String actualFormattedWord = wordFormater
                .getFormattedWord(firstWord, secondWord, punctuation,position,iteration);
        final String expectedFormatteddWord = "0_passwordcar1";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_DoubleWordWithPunctuationAtMiddlePositiveIteration_createdWord(){
        final String firstWord = "password";
        final String secondWord = "car";
        final int iteration = 2;
        final PunctuationStrategy punctuation = PunctuationStrategy.HYPHEN;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.MIDDLE;
        

        final String actualFormattedWord = wordFormater.getFormattedWord(firstWord,secondWord,punctuation,position,iteration);
        final String expectedFormatteddWord = "0password-car1";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_DoubleWordWithPunctuationAtLastPositiveIteration_createdWord(){
        final String firstWord = "password";
        final String secondWord = "car";
        final int iteration = 2;
        final PunctuationStrategy punctuation = PunctuationStrategy.DOLLAR;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        

        final String actualFormattedWord = wordFormater.getFormattedWord(firstWord, secondWord, punctuation,position,iteration);
        final String expectedFormatteddWord = "0passwordcar$1";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_DoubleWordWithPunctuationPositiveIterationOneDigit_createdWord(){
        final String firstWord = "password";
        final String secondWord = "car";
        final int iteration = 2;
        final PunctuationStrategy punctuation = PunctuationStrategy.DOLLAR;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        

        final String actualFormattedWord = wordFormater.getFormattedWord(firstWord, secondWord, punctuation,position,iteration);
        final String expectedFormatteddWord = "0passwordcar$1";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_DoubleWordWithPunctuationPositiveIterationTwoDigits_createdWord(){
        final String firstWord = "password";
        final String secondWord = "car";
        final int iteration = 21;
        final PunctuationStrategy punctuation = PunctuationStrategy.DOLLAR;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        

        final String actualFormattedWord = wordFormater.getFormattedWord(firstWord, secondWord, punctuation,position,iteration);
        final String expectedFormatteddWord = "2passwordcar$0";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_DoubleWordWithPunctuationPositiveIterationThreeDigits_createdWord(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = 321;
        final PunctuationStrategy punctuation = PunctuationStrategy.DOLLAR;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        

        final String actualFormattedWord = wordFormater.getFormattedWord(firstWord, secondWord, punctuation,position,iteration);
        final String expectedFormatteddWord = "03passwordcar$20";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_DoubleWordWithPunctuationPositiveIterationFourDigits_createdWord(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = 4321;
        final PunctuationStrategy punctuation = PunctuationStrategy.DOLLAR;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        

        final String actualFormattedWord = wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration);
        final String expectedFormatteddWord = "43passwordcar$20";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_DoubleWordWithoutPunctuationPositiveIteration_createdWord(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = 21;
        

        final String actualFormattedWord = wordFormater.getFormattedWord(firstWord, secondWord,iteration);
        final String expectedFormatteddWord = "2passwordcar0";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_DoubleWordFirstNullWithPunctuationPositiveIteration_thrownIllegalArgEx(){
        final String firstWord= null;
        final String secondWord = "car";
        final int iteration = 4;
        final PunctuationStrategy punctuation = PunctuationStrategy.DOLLAR;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_DoubleWordSecondNullWithPunctuationPositiveIteration_thrownIllegalArgEx(){
        final String firstWord= "password";
        final String secondWord = null;
        final int iteration = 4;
        final PunctuationStrategy punctuation = PunctuationStrategy.DOLLAR;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_DoubleWordBothNullWithPunctuationPositiveIteration_thrownIllegalArgEx(){
        final String firstWord= null;
        final String secondWord = null;
        final int iteration = 4;
        final PunctuationStrategy punctuation = PunctuationStrategy.DOLLAR;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_DoubleWordWithNullPunctuationPositiveIteration_thrownIllegalArgEx(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = 4321;
        final PunctuationStrategy punctuation = null;
        final PunctuationStrategy.Position position = null;
        

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_DoubleWordWithPunctuationAllParamsNullPositiveIteration_thrownIllegalArgEx(){
        final String firstWord= null;
        final String secondWord = null;
        final int iteration = 4321;
        final PunctuationStrategy punctuation = null;
        final PunctuationStrategy.Position position = null;
        

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getFormatted_DoubleWordWithPunctuationAtFirstZeroIteration_createdWord(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = 0;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.FIRST;
        

        final String actualFormattedWord = wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration);
        final String expectedFormatteddWord = "_passwordcar";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_DoubleWordWithPunctuationAtMiddleZeroIteration_createdWord(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = 0;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.MIDDLE;
        

        final String actualFormattedWord = wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration);
        final String expectedFormatteddWord = "password_car";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_DoubleWordWithPunctuationAtLastZeroIteration_createdWord(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = 0;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        

        final String actualFormattedWord = wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration);
        final String expectedFormatteddWord = "passwordcar_";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_DoubleWordWithoutPunctuationZeroIteration_createdWord(){
        final String firstWord = "password";
        final String secondWord = "car";
        final int iteration = 0;
        

        final String actualFormattedWord = wordFormater.getFormattedWord(firstWord, secondWord,iteration);
        final String expectedFormatteddWord = "passwordcar";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_DoubleWordFirstNullWithPunctuationZeroIteration_thrownIllegalArgEx(){
        final String firstWord= null;
        final String secondWord = "car";
        final int iteration = 0;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_DoubleWordSecondNullWithPunctuationZeroIteration_thrownIllegalArgEx(){
        final String firstWord= "password";
        final String secondWord = null;
        final int iteration = 0;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_DoubleWordBothNullWithPunctuationZeroIteration_thrownIllegalArgEx(){
        final String firstWord= null;
        final String secondWord = null;
        final int iteration = 0;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_DoubleWordWithNullPunctuationZeroIteration_thrownIllegalArgEx(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = 0;
        final PunctuationStrategy punctuation = null;
        final PunctuationStrategy.Position position = null;
        

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_DoubleWordWithPunctuationAllParamsNullZeroIteration_thrownIllegalArgEx(){
        final String firstWord= null;
        final String secondWord = "car";
        final int iteration = 0;
        final PunctuationStrategy punctuation = null;
        final PunctuationStrategy.Position position = null;
        

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getFormatted_DoubleWordWithPunctuationAtFirstNegativeIteration_thrownIllegalArgEx(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = -1;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.FIRST;
        

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getFormatted_DoubleWordWithPunctuationAtMiddleNegativeIteration_thrownIllegalArgEx(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = -4;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.MIDDLE;
        

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_DoubleWordWithPunctuationAtLastNegativeIteration_thrownIllegalArgEx(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = -3;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_DoubleWordWithoutPunctuationNegativeIteration_thrownIllegalArgEx(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = -2;
        

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_DoubleWordNullWithPunctuationNegativeIteration_thrownIllegalArgEx(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = -1;
        final PunctuationStrategy punctuation = null;
        final PunctuationStrategy.Position position =null;
        

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_DoubleWordWithNullPunctuationNegativeIteration_thrownIllegalaArgEx(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = -4;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.MIDDLE;
        

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
