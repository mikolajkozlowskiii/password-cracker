package producers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import producers.WordFormater;
import producers.strategies.CapitalizeStrategy;
import producers.strategies.NumberStrategy;
import producers.strategies.PunctuationStrategy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DoubleWordFormaterTest {
    private WordFormater wordFormater;

    @Test
    void getFormatted_WordToLowerNumBothSides_DoubleWordWithPunctuationAtFirstPositiveIteration_createdWord(){
        final String firstWord= "password";
        final String secondWord= "car";
        final int iteration = 2;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.FIRST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.BOTH_SIDES);

        final String actualFormattedWord = wordFormater
                .getFormattedWord(firstWord, secondWord, punctuation,position,iteration);
        final String expectedFormatteddWord = "0_passwordcar1";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumBothSides_DoubleWordWithPunctuationAtMiddlePositiveIteration_createdWord(){
        final String firstWord = "password";
        final String secondWord = "car";
        final int iteration = 2;
        final PunctuationStrategy punctuation = PunctuationStrategy.HYPHEN;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.MIDDLE;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.BOTH_SIDES);

        final String actualFormattedWord = wordFormater.getFormattedWord(firstWord,secondWord,punctuation,position,iteration);
        final String expectedFormatteddWord = "0password-car1";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumBothSides_DoubleWordWithPunctuationAtLastPositiveIteration_createdWord(){
        final String firstWord = "password";
        final String secondWord = "car";
        final int iteration = 2;
        final PunctuationStrategy punctuation = PunctuationStrategy.DOLLAR;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.BOTH_SIDES);

        final String actualFormattedWord = wordFormater.getFormattedWord(firstWord, secondWord, punctuation,position,iteration);
        final String expectedFormatteddWord = "0passwordcar$1";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumBothSides_DoubleWordWithPunctuationPositiveIterationOneDigit_createdWord(){
        final String firstWord = "password";
        final String secondWord = "car";
        final int iteration = 2;
        final PunctuationStrategy punctuation = PunctuationStrategy.DOLLAR;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.BOTH_SIDES);

        final String actualFormattedWord = wordFormater.getFormattedWord(firstWord, secondWord, punctuation,position,iteration);
        final String expectedFormatteddWord = "0passwordcar$1";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumBothSides_DoubleWordWithPunctuationPositiveIterationTwoDigits_createdWord(){
        final String firstWord = "password";
        final String secondWord = "car";
        final int iteration = 21;
        final PunctuationStrategy punctuation = PunctuationStrategy.DOLLAR;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.BOTH_SIDES);

        final String actualFormattedWord = wordFormater.getFormattedWord(firstWord, secondWord, punctuation,position,iteration);
        final String expectedFormatteddWord = "2passwordcar$0";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumBothSides_DoubleWordWithPunctuationPositiveIterationThreeDigits_createdWord(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = 321;
        final PunctuationStrategy punctuation = PunctuationStrategy.DOLLAR;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.BOTH_SIDES);

        final String actualFormattedWord = wordFormater.getFormattedWord(firstWord, secondWord, punctuation,position,iteration);
        final String expectedFormatteddWord = "03passwordcar$20";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumBothSides_DoubleWordWithPunctuationPositiveIterationFourDigits_createdWord(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = 4321;
        final PunctuationStrategy punctuation = PunctuationStrategy.DOLLAR;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.BOTH_SIDES);

        final String actualFormattedWord = wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration);
        final String expectedFormatteddWord = "43passwordcar$20";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumBothSides_DoubleWordWithoutPunctuationPositiveIteration_createdWord(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = 21;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.BOTH_SIDES);

        final String actualFormattedWord = wordFormater.getFormattedWord(firstWord, secondWord,iteration);
        final String expectedFormatteddWord = "2passwordcar0";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumBothSides_DoubleWordFirstNullWithPunctuationPositiveIteration_thrownIllegalArgEx(){
        final String firstWord= null;
        final String secondWord = "car";
        final int iteration = 4;
        final PunctuationStrategy punctuation = PunctuationStrategy.DOLLAR;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.BOTH_SIDES);

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_WordToLowerNumBothSides_DoubleWordSecondNullWithPunctuationPositiveIteration_thrownIllegalArgEx(){
        final String firstWord= "password";
        final String secondWord = null;
        final int iteration = 4;
        final PunctuationStrategy punctuation = PunctuationStrategy.DOLLAR;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.BOTH_SIDES);

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_WordToLowerNumBothSides_DoubleWordBothNullWithPunctuationPositiveIteration_thrownIllegalArgEx(){
        final String firstWord= null;
        final String secondWord = null;
        final int iteration = 4;
        final PunctuationStrategy punctuation = PunctuationStrategy.DOLLAR;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.BOTH_SIDES);

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_WordToLowerNumBothSides_DoubleWordWithNullPunctuationPositiveIteration_thrownIllegalArgEx(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = 4321;
        final PunctuationStrategy punctuation = null;
        final PunctuationStrategy.Position position = null;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.BOTH_SIDES);

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_WordToLowerNumBothSides_DoubleWordWithPunctuationAllParamsNullPositiveIteration_thrownIllegalArgEx(){
        final String firstWord= null;
        final String secondWord = null;
        final int iteration = 4321;
        final PunctuationStrategy punctuation = null;
        final PunctuationStrategy.Position position = null;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.BOTH_SIDES);

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getFormatted_WordToLowerNumBothSides_DoubleWordWithPunctuationAtFirstZeroIteration_createdWord(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = 0;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.FIRST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.BOTH_SIDES);

        final String actualFormattedWord = wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration);
        final String expectedFormatteddWord = "_passwordcar";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumBothSides_DoubleWordWithPunctuationAtMiddleZeroIteration_createdWord(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = 0;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.MIDDLE;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.BOTH_SIDES);

        final String actualFormattedWord = wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration);
        final String expectedFormatteddWord = "password_car";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumBothSides_DoubleWordWithPunctuationAtLastZeroIteration_createdWord(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = 0;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.BOTH_SIDES);

        final String actualFormattedWord = wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration);
        final String expectedFormatteddWord = "passwordcar_";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumBothSides_DoubleWordWithoutPunctuationZeroIteration_createdWord(){
        final String firstWord = "password";
        final String secondWord = "car";
        final int iteration = 0;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.BOTH_SIDES);

        final String actualFormattedWord = wordFormater.getFormattedWord(firstWord, secondWord,iteration);
        final String expectedFormatteddWord = "passwordcar";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumBothSides_DoubleWordFirstNullWithPunctuationZeroIteration_thrownIllegalArgEx(){
        final String firstWord= null;
        final String secondWord = "car";
        final int iteration = 0;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.BOTH_SIDES);

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_WordToLowerNumBothSides_DoubleWordSecondNullWithPunctuationZeroIteration_thrownIllegalArgEx(){
        final String firstWord= "password";
        final String secondWord = null;
        final int iteration = 0;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.BOTH_SIDES);

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_WordToLowerNumBothSides_DoubleWordBothNullWithPunctuationZeroIteration_thrownIllegalArgEx(){
        final String firstWord= null;
        final String secondWord = null;
        final int iteration = 0;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.BOTH_SIDES);

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_WordToLowerNumBothSides_DoubleWordWithNullPunctuationZeroIteration_thrownIllegalArgEx(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = 0;
        final PunctuationStrategy punctuation = null;
        final PunctuationStrategy.Position position = null;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.BOTH_SIDES);

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_WordToLowerNumBothSides_DoubleWordWithPunctuationAllParamsNullZeroIteration_thrownIllegalArgEx(){
        final String firstWord= null;
        final String secondWord = "car";
        final int iteration = 0;
        final PunctuationStrategy punctuation = null;
        final PunctuationStrategy.Position position = null;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.BOTH_SIDES);

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getFormatted_WordToLowerNumBothSides_DoubleWordWithPunctuationAtFirstNegativeIteration_thrownIllegalArgEx(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = -1;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.FIRST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.BOTH_SIDES);

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getFormatted_WordToLowerNumBothSides_DoubleWordWithPunctuationAtMiddleNegativeIteration_thrownIllegalArgEx(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = -4;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.MIDDLE;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.BOTH_SIDES);

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_WordToLowerNumBothSides_DoubleWordWithPunctuationAtLastNegativeIteration_thrownIllegalArgEx(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = -3;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.BOTH_SIDES);

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_WordToLowerNumBothSides_DoubleWordWithoutPunctuationNegativeIteration_thrownIllegalArgEx(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = -2;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.BOTH_SIDES);

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_WordToLowerNumBothSides_DoubleWordNullWithPunctuationNegativeIteration_thrownIllegalArgEx(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = -1;
        final PunctuationStrategy punctuation = null;
        final PunctuationStrategy.Position position =null;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.BOTH_SIDES);

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_WordToLowerNumBothSides_DoubleWordWithNullPunctuationNegativeIteration_thrownIllegalaArgEx(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = -4;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.MIDDLE;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.BOTH_SIDES);

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }



    @Test
    void getFormatted_WordToLowerNumLeftSide_DoubleWordWithPunctuationAtFirstPositiveIteration_createdWord(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = 2;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.FIRST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.LEFT_SIDE);

        final String actualFormattedWord = wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration);
        final String expectedFormatteddWord = "2_passwordcar";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumLeftSide_DoubleWordWithPunctuationAtMiddlePositiveIteration_createdWord(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = 2;
        final PunctuationStrategy punctuation = PunctuationStrategy.HYPHEN;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.MIDDLE;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.LEFT_SIDE);

        final String actualFormattedWord = wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration);
        final String expectedFormatteddWord = "2password-car";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumLeftSide_DoubleWordWithPunctuationAtLastPositiveIteration_createdWord(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = 2;
        final PunctuationStrategy punctuation = PunctuationStrategy.DOLLAR;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.LEFT_SIDE);

        final String actualFormattedWord = wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration);
        final String expectedFormatteddWord = "2passwordcar$";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumLeftSide_DoubleWordWithPunctuationPositiveIterationOneDigit_createdWord(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = 2;
        final PunctuationStrategy punctuation = PunctuationStrategy.DOLLAR;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.LEFT_SIDE);

        final String actualFormattedWord = wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration);
        final String expectedFormatteddWord = "2passwordcar$";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumLeftSide_DoubleWordWithPunctuationPositiveIterationTwoDigits_createdWord(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = 21;
        final PunctuationStrategy punctuation = PunctuationStrategy.DOLLAR;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.LEFT_SIDE);

        final String actualFormattedWord = wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration);
        final String expectedFormatteddWord = "21passwordcar$";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumLeftSide_DoubleWordWithPunctuationPositiveIterationThreeDigits_createdWord(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = 321;
        final PunctuationStrategy punctuation = PunctuationStrategy.DOLLAR;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.LEFT_SIDE);

        final String actualFormattedWord = wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration);
        final String expectedFormatteddWord = "321passwordcar$";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumLeftSide_DoubleWordWithPunctuationPositiveIterationFourDigits_createdWord(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = 4321;
        final PunctuationStrategy punctuation = PunctuationStrategy.DOLLAR;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.LEFT_SIDE);

        final String actualFormattedWord = wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration);
        final String expectedFormatteddWord = "4321passwordcar$";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumLeftSide_DoubleWordWithoutPunctuationPositiveIteration_createdWord(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = 21;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.LEFT_SIDE);

        final String actualFormattedWord = wordFormater.getFormattedWord(firstWord, secondWord,iteration);
        final String expectedFormatteddWord = "21passwordcar";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumLeftSide_DoubleWordFirstNullWithPunctuationPositiveIteration_thrownIllegalArgEx(){
        final String firstWord= null;
        final String secondWord = "car";
        final int iteration = 4;
        final PunctuationStrategy punctuation = PunctuationStrategy.DOLLAR;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.LEFT_SIDE);

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_WordToLowerNumLeftSide_DoubleWordSecondNullWithPunctuationPositiveIteration_thrownIllegalArgEx(){
        final String firstWord= "car";
        final String secondWord = null;
        final int iteration = 4;
        final PunctuationStrategy punctuation = PunctuationStrategy.DOLLAR;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.LEFT_SIDE);

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_WordToLowerNumLeftSide_DoubleWordBothNullWithPunctuationPositiveIteration_thrownIllegalArgEx(){
        final String firstWord= null;
        final String secondWord = null;
        final int iteration = 4;
        final PunctuationStrategy punctuation = PunctuationStrategy.DOLLAR;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.LEFT_SIDE);

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_WordToLowerNumLeftSide_DoubleWordWithNullPunctuationPositiveIteration_thrownIllegalArgEx(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = 4321;
        final PunctuationStrategy punctuation = null;
        final PunctuationStrategy.Position position = null;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.LEFT_SIDE);

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_WordToLowerNumLeftSide_DoubleWordWithPunctuationAllParamsNullPositiveIteration_thrownIllegalArgEx(){
        final String firstWord= null;
        final String secondWord = null;
        final int iteration = 4321;
        final PunctuationStrategy punctuation = null;
        final PunctuationStrategy.Position position = null;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.LEFT_SIDE);

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getFormatted_WordToLowerNumLeftSide_DoubleWordWithPunctuationAtFirstZeroIteration_createdWord(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = 0;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.FIRST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.LEFT_SIDE);

        final String actualFormattedWord = wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration);
        final String expectedFormatteddWord = "0_passwordcar";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumLeftSide_DoubleWordWithPunctuationAtMiddleZeroIteration_createdWord(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = 0;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.MIDDLE;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.LEFT_SIDE);

        final String actualFormattedWord = wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration);
        final String expectedFormatteddWord = "0password_car";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumLeftSide_DoubleWordWithPunctuationAtLastZeroIteration_createdWord(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = 0;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.LEFT_SIDE);

        final String actualFormattedWord = wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration);
        final String expectedFormatteddWord = "0passwordcar_";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumLeftSide_DoubleWordWithoutPunctuationZeroIteration_createdWord(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = 0;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.LEFT_SIDE);

        final String actualFormattedWord = wordFormater.getFormattedWord(firstWord, secondWord,iteration);
        final String expectedFormatteddWord = "0passwordcar";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumLeftSide_DoubleWordFirstNullWithPunctuationZeroIteration_thrownIllegalArgEx(){
        final String firstWord= null;
        final String secondWord = "car";
        final int iteration = 0;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.LEFT_SIDE);

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_WordToLowerNumLeftSide_DoubleWordSecondNullWithPunctuationZeroIteration_thrownIllegalArgEx(){
        final String firstWord= "password";
        final String secondWord = null;
        final int iteration = 0;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.LEFT_SIDE);

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_WordToLowerNumLeftSide_DoubleWordBothNullWithPunctuationZeroIteration_thrownIllegalArgEx(){
        final String firstWord= null;
        final String secondWord = null;
        final int iteration = 0;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.LEFT_SIDE);

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_WordToLowerNumLeftSide_DoubleWordWithNullPunctuationZeroIteration_thrownIllegalArgEx(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = 0;
        final PunctuationStrategy punctuation = null;
        final PunctuationStrategy.Position position = null;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.LEFT_SIDE);

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_WordToLowerNumLeftSide_DoubleWordWithPunctuationAllParamsNullZeroIteration_thrownIllegalArgEx(){
        final String firstWord= null;
        final String secondWord = null;
        final int iteration = 0;
        final PunctuationStrategy punctuation = null;
        final PunctuationStrategy.Position position = null;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.LEFT_SIDE);

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getFormatted_WordToLowerNumLeftSide_DoubleWordWithPunctuationAtFirstNegativeIteration_thrownIllegalArgEx(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = -1;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.FIRST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.LEFT_SIDE);

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getFormatted_WordToLowerNumLeftSide_DoubleWordWithPunctuationAtMiddleNegativeIteration_thrownIllegalArgEx(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = -4;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.MIDDLE;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.LEFT_SIDE);

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_WordToLowerNumLeftSide_DoubleWordWithPunctuationAtLastNegativeIteration_thrownIllegalArgEx(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = -3;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.LEFT_SIDE);

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_WordToLowerNumLeftSide_DoubleWordWithoutPunctuationNegativeIteration_thrownIllegalArgEx(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = -2;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.LEFT_SIDE);

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_WordToLowerNumLeftSide_DoubleWordNullWithPunctuationNegativeIteration_thrownIllegalArgEx(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = -1;
        final PunctuationStrategy punctuation = null;
        final PunctuationStrategy.Position position =null;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.LEFT_SIDE);

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_WordToLowerNumLeftSide_DoubleWordWithNullPunctuationNegativeIteration_thrownIllegalaArgEx(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = -4;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.MIDDLE;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.LEFT_SIDE);

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getFormatted_WordToLowerNumRightSide_DoubleWordWithPunctuationAtFirstPositiveIteration_createdWord(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = 2;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.FIRST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.RIGHT_SIDE);

        final String actualFormattedWord = wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration);
        final String expectedFormatteddWord = "_passwordcar2";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumRightSide_DoubleWordWithPunctuationAtMiddlePositiveIteration_createdWord(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = 2;
        final PunctuationStrategy punctuation = PunctuationStrategy.HYPHEN;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.MIDDLE;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.RIGHT_SIDE);

        final String actualFormattedWord = wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration);
        final String expectedFormatteddWord = "password-car2";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumRightSide_DoubleWordWithPunctuationAtLastPositiveIteration_createdWord(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = 2;
        final PunctuationStrategy punctuation = PunctuationStrategy.DOLLAR;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.RIGHT_SIDE);

        final String actualFormattedWord = wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration);
        final String expectedFormatteddWord = "passwordcar$2";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumRightSide_DoubleWordWithPunctuationPositiveIterationOneDigit_createdWord(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = 1;
        final PunctuationStrategy punctuation = PunctuationStrategy.DOLLAR;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.RIGHT_SIDE);

        final String actualFormattedWord = wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration);
        final String expectedFormatteddWord = "passwordcar$1";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumRightSide_DoubleWordWithPunctuationPositiveIterationTwoDigits_createdWord(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = 21;
        final PunctuationStrategy punctuation = PunctuationStrategy.DOLLAR;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.RIGHT_SIDE);

        final String actualFormattedWord = wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration);
        final String expectedFormatteddWord = "passwordcar$21";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumRightSide_DoubleWordWithPunctuationPositiveIterationThreeDigits_createdWord(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = 321;
        final PunctuationStrategy punctuation = PunctuationStrategy.DOLLAR;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.RIGHT_SIDE);

        final String actualFormattedWord = wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration);
        final String expectedFormatteddWord = "passwordcar$321";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumRightSide_DoubleWordWithPunctuationPositiveIterationFourDigits_createdWord(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = 4321;
        final PunctuationStrategy punctuation = PunctuationStrategy.DOLLAR;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.RIGHT_SIDE);

        final String actualFormattedWord = wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration);
        final String expectedFormatteddWord = "passwordcar$4321";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumRightSide_DoubleWordWithoutPunctuationPositiveIteration_createdWord(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = 21;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.RIGHT_SIDE);

        final String actualFormattedWord = wordFormater.getFormattedWord(firstWord, secondWord,iteration);
        final String expectedFormatteddWord = "passwordcar21";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumRightSide_DoubleWordFirstNullWithPunctuationPositiveIteration_thrownIllegalArgEx(){
        final String firstWord= "password";
        final String secondWord = null;
        final int iteration = 4;
        final PunctuationStrategy punctuation = PunctuationStrategy.DOLLAR;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.RIGHT_SIDE);

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_WordToLowerNumRightSide_DoubleWordSecondNullWithPunctuationPositiveIteration_thrownIllegalArgEx(){
        final String firstWord= "password";
        final String secondWord = null;
        final int iteration = 4;
        final PunctuationStrategy punctuation = PunctuationStrategy.DOLLAR;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.RIGHT_SIDE);

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_WordToLowerNumRightSide_DoubleWordBothNullWithPunctuationPositiveIteration_thrownIllegalArgEx(){
        final String firstWord= null;
        final String secondWord = null;
        final int iteration = 4;
        final PunctuationStrategy punctuation = PunctuationStrategy.DOLLAR;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.RIGHT_SIDE);

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_WordToLowerNumRightSide_DoubleWordWithNullPunctuationPositiveIteration_thrownIllegalArgEx(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = 4321;
        final PunctuationStrategy punctuation = null;
        final PunctuationStrategy.Position position = null;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.RIGHT_SIDE);

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_WordToLowerNumRightSide_DoubleWordWithPunctuationAllParamsNullPositiveIteration_thrownIllegalArgEx(){
        final String firstWord= null;
        final String secondWord = null;
        final int iteration = 4321;
        final PunctuationStrategy punctuation = null;
        final PunctuationStrategy.Position position = null;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.RIGHT_SIDE);

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getFormatted_WordToLowerNumRightSide_DoubleWordWithPunctuationAtFirstZeroIteration_createdWord(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = 0;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.FIRST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.RIGHT_SIDE);

        final String actualFormattedWord = wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration);
        final String expectedFormatteddWord = "_passwordcar0";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumRightSide_DoubleWordWithPunctuationAtMiddleZeroIteration_createdWord(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = 0;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.MIDDLE;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.RIGHT_SIDE);

        final String actualFormattedWord = wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration);
        final String expectedFormatteddWord = "password_car0";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumRightSide_DoubleWordWithPunctuationAtLastZeroIteration_createdWord(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = 0;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.RIGHT_SIDE);

        final String actualFormattedWord = wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration);
        final String expectedFormatteddWord = "passwordcar_0";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumRightSide_DoubleWordWithoutPunctuationZeroIteration_createdWord(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = 0;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.RIGHT_SIDE);

        final String actualFormattedWord = wordFormater.getFormattedWord(firstWord, secondWord,iteration);
        final String expectedFormatteddWord = "passwordcar0";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumRightSide_DoubleWordFirstNullWithPunctuationZeroIteration_thrownIllegalArgEx(){
        final String firstWord= null;
        final String secondWord = "car";
        final int iteration = 0;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.RIGHT_SIDE);

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_WordToLowerNumRightSide_DoubleWordSecondNullWithPunctuationZeroIteration_thrownIllegalArgEx(){
        final String firstWord= "password";
        final String secondWord = null;
        final int iteration = 0;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.RIGHT_SIDE);

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_WordToLowerNumRightSide_DoubleWordBothNullWithPunctuationZeroIteration_thrownIllegalArgEx(){
        final String firstWord= null;
        final String secondWord = null;
        final int iteration = 0;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.RIGHT_SIDE);

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_WordToLowerNumRightSide_DoubleWordWithNullPunctuationZeroIteration_thrownIllegalArgEx(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = 0;
        final PunctuationStrategy punctuation = null;
        final PunctuationStrategy.Position position = null;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.RIGHT_SIDE);

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_WordToLowerNumRightSide_DoubleWordWithPunctuationAllParamsNullZeroIteration_thrownIllegalArgEx(){
        final String firstWord= null;
        final String secondWord = null;
        final int iteration = 0;
        final PunctuationStrategy punctuation = null;
        final PunctuationStrategy.Position position = null;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.RIGHT_SIDE);

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getFormatted_WordToLowerNumRightSide_DoubleWordWithPunctuationAtFirstNegativeIteration_thrownIllegalArgEx(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = -1;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.FIRST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.RIGHT_SIDE);

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getFormatted_WordToLowerNumRightSide_DoubleWordWithPunctuationAtMiddleNegativeIteration_thrownIllegalArgEx(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = -4;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.MIDDLE;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.RIGHT_SIDE);

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_WordToLowerNumRightSide_DoubleWordWithPunctuationAtLastNegativeIteration_thrownIllegalArgEx(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = -3;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.RIGHT_SIDE);

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_WordToLowerNumRightSide_DoubleWordWithoutPunctuationNegativeIteration_thrownIllegalArgEx(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = -2;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.RIGHT_SIDE);

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_WordToLowerNumRightSide_DoubleWordNullWithPunctuationNegativeIteration_thrownIllegalArgEx(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = -1;
        final PunctuationStrategy punctuation = null;
        final PunctuationStrategy.Position position =null;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.RIGHT_SIDE);

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_WordToLowerNumRightSide_DoubleWordWithNullPunctuationNegativeIteration_thrownIllegalaArgEx(){
        final String firstWord= "password";
        final String secondWord = "car";
        final int iteration = -4;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.MIDDLE;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.RIGHT_SIDE);

        assertThatThrownBy(()->wordFormater.getFormattedWord(firstWord, secondWord,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    
}