package producers;

import org.junit.jupiter.api.Test;
import producers.WordFormater;
import producers.strategies.CapitalizeStrategy;
import producers.strategies.NumberStrategy;
import producers.strategies.PunctuationStrategy;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SingleWordFormaterTest {
    private WordFormater wordFormater;

    @Test
    void getFormatted_WordToLowerNumBothSides_SingleWordWithPunctuationAtFirstPositiveIteration_createdWord(){
        final String word = "password";
        final int iteration = 2;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.FIRST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.BOTH_SIDES);

        final String actualFormattedWord = wordFormater.getFormattedWord(word,punctuation,position,iteration);
        final String expectedFormatteddWord = "0_password1";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumBothSides_SingleWordWithPunctuationAtMiddlePositiveIteration_createdWord(){
        final String word = "password";
        final int iteration = 2;
        final PunctuationStrategy punctuation = PunctuationStrategy.HYPHEN;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.MIDDLE;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.BOTH_SIDES);

        final String actualFormattedWord = wordFormater.getFormattedWord(word,punctuation,position,iteration);
        final String expectedFormatteddWord = "0pass-word1";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumBothSides_SingleWordWithPunctuationAtLastPositiveIteration_createdWord(){
        final String word = "password";
        final int iteration = 2;
        final PunctuationStrategy punctuation = PunctuationStrategy.DOLLAR;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.BOTH_SIDES);

        final String actualFormattedWord = wordFormater.getFormattedWord(word,punctuation,position,iteration);
        final String expectedFormatteddWord = "0password$1";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumBothSides_SingleWordWithPunctuationPositiveIterationOneDigit_createdWord(){
        final String word = "password";
        final int iteration = 2;
        final PunctuationStrategy punctuation = PunctuationStrategy.DOLLAR;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.BOTH_SIDES);

        final String actualFormattedWord = wordFormater.getFormattedWord(word,punctuation,position,iteration);
        final String expectedFormatteddWord = "0password$1";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumBothSides_SingleWordWithPunctuationPositiveIterationTwoDigits_createdWord(){
        final String word = "password";
        final int iteration = 21;
        final PunctuationStrategy punctuation = PunctuationStrategy.DOLLAR;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.BOTH_SIDES);

        final String actualFormattedWord = wordFormater.getFormattedWord(word,punctuation,position,iteration);
        final String expectedFormatteddWord = "2password$0";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumBothSides_SingleWordWithPunctuationPositiveIterationThreeDigits_createdWord(){
        final String word = "password";
        final int iteration = 321;
        final PunctuationStrategy punctuation = PunctuationStrategy.DOLLAR;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.BOTH_SIDES);

        final String actualFormattedWord = wordFormater.getFormattedWord(word,punctuation,position,iteration);
        final String expectedFormatteddWord = "03password$20";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumBothSides_SingleWordWithPunctuationPositiveIterationFourDigits_createdWord(){
        final String word = "password";
        final int iteration = 4321;
        final PunctuationStrategy punctuation = PunctuationStrategy.DOLLAR;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.BOTH_SIDES);

        final String actualFormattedWord = wordFormater.getFormattedWord(word,punctuation,position,iteration);
        final String expectedFormatteddWord = "43password$20";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumBothSides_SingleWordWithoutPunctuationPositiveIteration_createdWord(){
        final String word = "password";
        final int iteration = 21;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.BOTH_SIDES);

        final String actualFormattedWord = wordFormater.getFormattedWord(word,iteration);
        final String expectedFormatteddWord = "2password0";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumBothSides_SingleWordNullWithPunctuationPositiveIteration_thrownIllegalArgEx(){
        final String word = null;
        final int iteration = 4;
        final PunctuationStrategy punctuation = PunctuationStrategy.DOLLAR;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.BOTH_SIDES);

        assertThatThrownBy(()->wordFormater.getFormattedWord(word,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_WordToLowerNumBothSides_SingleWordWithNullPunctuationPositiveIteration_thrownIllegalArgEx(){
        final String word = "password";
        final int iteration = 4321;
        final PunctuationStrategy punctuation = null;
        final PunctuationStrategy.Position position = null;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.BOTH_SIDES);

        assertThatThrownBy(()->wordFormater.getFormattedWord(word,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_WordToLowerNumBothSides_SingleWordWithPunctuationAllParamsNullPositiveIteration_thrownIllegalArgEx(){
        final String word = null;
        final int iteration = 4321;
        final PunctuationStrategy punctuation = null;
        final PunctuationStrategy.Position position = null;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.BOTH_SIDES);

        assertThatThrownBy(()->wordFormater.getFormattedWord(word,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getFormatted_WordToLowerNumBothSides_SingleWordWithPunctuationAtFirstZeroIteration_createdWord(){
        final String word = "password";
        final int iteration = 0;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.FIRST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.BOTH_SIDES);

        final String actualFormattedWord = wordFormater.getFormattedWord(word,punctuation,position,iteration);
        final String expectedFormatteddWord = "_password";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumBothSides_SingleWordWithPunctuationAtMiddleZeroIteration_createdWord(){
        final String word = "password";
        final int iteration = 0;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.MIDDLE;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.BOTH_SIDES);

        final String actualFormattedWord = wordFormater.getFormattedWord(word,punctuation,position,iteration);
        final String expectedFormatteddWord = "pass_word";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumBothSides_SingleWordWithPunctuationAtLastZeroIteration_createdWord(){
        final String word = "password";
        final int iteration = 0;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.BOTH_SIDES);

        final String actualFormattedWord = wordFormater.getFormattedWord(word,punctuation,position,iteration);
        final String expectedFormatteddWord = "password_";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumBothSides_SingleWordWithoutPunctuationZeroIteration_createdWord(){
        final String word = "password";
        final int iteration = 0;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.BOTH_SIDES);

        final String actualFormattedWord = wordFormater.getFormattedWord(word,iteration);
        final String expectedFormatteddWord = "password";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumBothSides_SingleWordNullWithPunctuationZeroIteration_thrownIllegalArgEx(){
        final String word = null;
        final int iteration = 0;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.BOTH_SIDES);

        assertThatThrownBy(()->wordFormater.getFormattedWord(word,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_WordToLowerNumBothSides_SingleWordWithNullPunctuationZeroIteration_thrownIllegalArgEx(){
        final String word = "password";
        final int iteration = 0;
        final PunctuationStrategy punctuation = null;
        final PunctuationStrategy.Position position = null;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.BOTH_SIDES);

        assertThatThrownBy(()->wordFormater.getFormattedWord(word,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_WordToLowerNumBothSides_SingleWordWithPunctuationAllParamsNullZeroIteration_thrownIllegalArgEx(){
        final String word = null;
        final int iteration = 0;
        final PunctuationStrategy punctuation = null;
        final PunctuationStrategy.Position position = null;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.BOTH_SIDES);

        assertThatThrownBy(()->wordFormater.getFormattedWord(word,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getFormatted_WordToLowerNumBothSides_SingleWordWithPunctuationAtFirstNegativeIteration_thrownIllegalArgEx(){
        final String word = "password";
        final int iteration = -1;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.FIRST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.BOTH_SIDES);

        assertThatThrownBy(()->wordFormater.getFormattedWord(word,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getFormatted_WordToLowerNumBothSides_SingleWordWithPunctuationAtMiddleNegativeIteration_thrownIllegalArgEx(){
        final String word = "password";
        final int iteration = -4;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.MIDDLE;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.BOTH_SIDES);

        assertThatThrownBy(()->wordFormater.getFormattedWord(word,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_WordToLowerNumBothSides_SingleWordWithPunctuationAtLastNegativeIteration_thrownIllegalArgEx(){
        final String word = "password";
        final int iteration = -3;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.BOTH_SIDES);

        assertThatThrownBy(()->wordFormater.getFormattedWord(word,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_WordToLowerNumBothSides_SingleWordWithoutPunctuationNegativeIteration_thrownIllegalArgEx(){
        final String word = "password";
        final int iteration = -2;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.BOTH_SIDES);

        assertThatThrownBy(()->wordFormater.getFormattedWord(word,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_WordToLowerNumBothSides_SingleWordNullWithPunctuationNegativeIteration_thrownIllegalArgEx(){
        final String word = "password";
        final int iteration = -1;
        final PunctuationStrategy punctuation = null;
        final PunctuationStrategy.Position position =null;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.BOTH_SIDES);

        assertThatThrownBy(()->wordFormater.getFormattedWord(word,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_WordToLowerNumBothSides_SingleWordWithNullPunctuationNegativeIteration_thrownIllegalaArgEx(){
        final String word = "password";
        final int iteration = -4;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.MIDDLE;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.BOTH_SIDES);

        assertThatThrownBy(()->wordFormater.getFormattedWord(word,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }



    @Test
    void getFormatted_WordToLowerNumLeftSide_SingleWordWithPunctuationAtFirstPositiveIteration_createdWord(){
        final String word = "password";
        final int iteration = 2;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.FIRST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.LEFT_SIDE);

        final String actualFormattedWord = wordFormater.getFormattedWord(word,punctuation,position,iteration);
        final String expectedFormatteddWord = "2_password";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumLeftSide_SingleWordWithPunctuationAtMiddlePositiveIteration_createdWord(){
        final String word = "password";
        final int iteration = 2;
        final PunctuationStrategy punctuation = PunctuationStrategy.HYPHEN;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.MIDDLE;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.LEFT_SIDE);

        final String actualFormattedWord = wordFormater.getFormattedWord(word,punctuation,position,iteration);
        final String expectedFormatteddWord = "2pass-word";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumLeftSide_SingleWordWithPunctuationAtLastPositiveIteration_createdWord(){
        final String word = "password";
        final int iteration = 2;
        final PunctuationStrategy punctuation = PunctuationStrategy.DOLLAR;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.LEFT_SIDE);

        final String actualFormattedWord = wordFormater.getFormattedWord(word,punctuation,position,iteration);
        final String expectedFormatteddWord = "2password$";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumLeftSide_SingleWordWithPunctuationPositiveIterationOneDigit_createdWord(){
        final String word = "password";
        final int iteration = 2;
        final PunctuationStrategy punctuation = PunctuationStrategy.DOLLAR;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.LEFT_SIDE);

        final String actualFormattedWord = wordFormater.getFormattedWord(word,punctuation,position,iteration);
        final String expectedFormatteddWord = "2password$";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumLeftSide_SingleWordWithPunctuationPositiveIterationTwoDigits_createdWord(){
        final String word = "password";
        final int iteration = 21;
        final PunctuationStrategy punctuation = PunctuationStrategy.DOLLAR;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.LEFT_SIDE);

        final String actualFormattedWord = wordFormater.getFormattedWord(word,punctuation,position,iteration);
        final String expectedFormatteddWord = "21password$";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumLeftSide_SingleWordWithPunctuationPositiveIterationThreeDigits_createdWord(){
        final String word = "password";
        final int iteration = 321;
        final PunctuationStrategy punctuation = PunctuationStrategy.DOLLAR;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.LEFT_SIDE);

        final String actualFormattedWord = wordFormater.getFormattedWord(word,punctuation,position,iteration);
        final String expectedFormatteddWord = "321password$";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumLeftSide_SingleWordWithPunctuationPositiveIterationFourDigits_createdWord(){
        final String word = "password";
        final int iteration = 4321;
        final PunctuationStrategy punctuation = PunctuationStrategy.DOLLAR;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.LEFT_SIDE);

        final String actualFormattedWord = wordFormater.getFormattedWord(word,punctuation,position,iteration);
        final String expectedFormatteddWord = "4321password$";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumLeftSide_SingleWordWithoutPunctuationPositiveIteration_createdWord(){
        final String word = "password";
        final int iteration = 21;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.LEFT_SIDE);

        final String actualFormattedWord = wordFormater.getFormattedWord(word,iteration);
        final String expectedFormatteddWord = "21password";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumLeftSide_SingleWordNullWithPunctuationPositiveIteration_thrownIllegalArgEx(){
        final String word = null;
        final int iteration = 4;
        final PunctuationStrategy punctuation = PunctuationStrategy.DOLLAR;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.LEFT_SIDE);

        assertThatThrownBy(()->wordFormater.getFormattedWord(word,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_WordToLowerNumLeftSide_SingleWordWithNullPunctuationPositiveIteration_thrownIllegalArgEx(){
        final String word = "password";
        final int iteration = 4321;
        final PunctuationStrategy punctuation = null;
        final PunctuationStrategy.Position position = null;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.LEFT_SIDE);

        assertThatThrownBy(()->wordFormater.getFormattedWord(word,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_WordToLowerNumLeftSide_SingleWordWithPunctuationAllParamsNullPositiveIteration_thrownIllegalArgEx(){
        final String word = null;
        final int iteration = 4321;
        final PunctuationStrategy punctuation = null;
        final PunctuationStrategy.Position position = null;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.LEFT_SIDE);

        assertThatThrownBy(()->wordFormater.getFormattedWord(word,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getFormatted_WordToLowerNumLeftSide_SingleWordWithPunctuationAtFirstZeroIteration_createdWord(){
        final String word = "password";
        final int iteration = 0;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.FIRST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.LEFT_SIDE);

        final String actualFormattedWord = wordFormater.getFormattedWord(word,punctuation,position,iteration);
        final String expectedFormatteddWord = "0_password";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumLeftSide_SingleWordWithPunctuationAtMiddleZeroIteration_createdWord(){
        final String word = "password";
        final int iteration = 0;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.MIDDLE;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.LEFT_SIDE);

        final String actualFormattedWord = wordFormater.getFormattedWord(word,punctuation,position,iteration);
        final String expectedFormatteddWord = "0pass_word";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumLeftSide_SingleWordWithPunctuationAtLastZeroIteration_createdWord(){
        final String word = "password";
        final int iteration = 0;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.LEFT_SIDE);

        final String actualFormattedWord = wordFormater.getFormattedWord(word,punctuation,position,iteration);
        final String expectedFormatteddWord = "0password_";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumLeftSide_SingleWordWithoutPunctuationZeroIteration_createdWord(){
        final String word = "password";
        final int iteration = 0;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.LEFT_SIDE);

        final String actualFormattedWord = wordFormater.getFormattedWord(word,iteration);
        final String expectedFormatteddWord = "0password";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumLeftSide_SingleWordNullWithPunctuationZeroIteration_thrownIllegalArgEx(){
        final String word = null;
        final int iteration = 0;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.LEFT_SIDE);

        assertThatThrownBy(()->wordFormater.getFormattedWord(word,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_WordToLowerNumLeftSide_SingleWordWithNullPunctuationZeroIteration_thrownIllegalArgEx(){
        final String word = "password";
        final int iteration = 0;
        final PunctuationStrategy punctuation = null;
        final PunctuationStrategy.Position position = null;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.LEFT_SIDE);

        assertThatThrownBy(()->wordFormater.getFormattedWord(word,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_WordToLowerNumLeftSide_SingleWordWithPunctuationAllParamsNullZeroIteration_thrownIllegalArgEx(){
        final String word = null;
        final int iteration = 0;
        final PunctuationStrategy punctuation = null;
        final PunctuationStrategy.Position position = null;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.LEFT_SIDE);

        assertThatThrownBy(()->wordFormater.getFormattedWord(word,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getFormatted_WordToLowerNumLeftSide_SingleWordWithPunctuationAtFirstNegativeIteration_thrownIllegalArgEx(){
        final String word = "password";
        final int iteration = -1;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.FIRST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.LEFT_SIDE);

        assertThatThrownBy(()->wordFormater.getFormattedWord(word,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getFormatted_WordToLowerNumLeftSide_SingleWordWithPunctuationAtMiddleNegativeIteration_thrownIllegalArgEx(){
        final String word = "password";
        final int iteration = -4;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.MIDDLE;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.LEFT_SIDE);

        assertThatThrownBy(()->wordFormater.getFormattedWord(word,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_WordToLowerNumLeftSide_SingleWordWithPunctuationAtLastNegativeIteration_thrownIllegalArgEx(){
        final String word = "password";
        final int iteration = -3;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.LEFT_SIDE);

        assertThatThrownBy(()->wordFormater.getFormattedWord(word,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_WordToLowerNumLeftSide_SingleWordWithoutPunctuationNegativeIteration_thrownIllegalArgEx(){
        final String word = "password";
        final int iteration = -2;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.LEFT_SIDE);

        assertThatThrownBy(()->wordFormater.getFormattedWord(word,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_WordToLowerNumLeftSide_SingleWordNullWithPunctuationNegativeIteration_thrownIllegalArgEx(){
        final String word = "password";
        final int iteration = -1;
        final PunctuationStrategy punctuation = null;
        final PunctuationStrategy.Position position =null;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.LEFT_SIDE);

        assertThatThrownBy(()->wordFormater.getFormattedWord(word,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_WordToLowerNumLeftSide_SingleWordWithNullPunctuationNegativeIteration_thrownIllegalaArgEx(){
        final String word = "password";
        final int iteration = -4;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.MIDDLE;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.LEFT_SIDE);

        assertThatThrownBy(()->wordFormater.getFormattedWord(word,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getFormatted_WordToLowerNumRightSide_SingleWordWithPunctuationAtFirstPositiveIteration_createdWord(){
        final String word = "password";
        final int iteration = 2;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.FIRST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.RIGHT_SIDE);

        final String actualFormattedWord = wordFormater.getFormattedWord(word,punctuation,position,iteration);
        final String expectedFormatteddWord = "_password2";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumRightSide_SingleWordWithPunctuationAtMiddlePositiveIteration_createdWord(){
        final String word = "password";
        final int iteration = 2;
        final PunctuationStrategy punctuation = PunctuationStrategy.HYPHEN;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.MIDDLE;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.RIGHT_SIDE);

        final String actualFormattedWord = wordFormater.getFormattedWord(word,punctuation,position,iteration);
        final String expectedFormatteddWord = "pass-word2";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumRightSide_SingleWordWithPunctuationAtLastPositiveIteration_createdWord(){
        final String word = "password";
        final int iteration = 2;
        final PunctuationStrategy punctuation = PunctuationStrategy.DOLLAR;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.RIGHT_SIDE);

        final String actualFormattedWord = wordFormater.getFormattedWord(word,punctuation,position,iteration);
        final String expectedFormatteddWord = "password$2";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumRightSide_SingleWordWithPunctuationPositiveIterationOneDigit_createdWord(){
        final String word = "password";
        final int iteration = 2;
        final PunctuationStrategy punctuation = PunctuationStrategy.DOLLAR;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.RIGHT_SIDE);

        final String actualFormattedWord = wordFormater.getFormattedWord(word,punctuation,position,iteration);
        final String expectedFormatteddWord = "password$2";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumRightSide_SingleWordWithPunctuationPositiveIterationTwoDigits_createdWord(){
        final String word = "password";
        final int iteration = 21;
        final PunctuationStrategy punctuation = PunctuationStrategy.DOLLAR;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.RIGHT_SIDE);

        final String actualFormattedWord = wordFormater.getFormattedWord(word,punctuation,position,iteration);
        final String expectedFormatteddWord = "password$21";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumRightSide_SingleWordWithPunctuationPositiveIterationThreeDigits_createdWord(){
        final String word = "password";
        final int iteration = 321;
        final PunctuationStrategy punctuation = PunctuationStrategy.DOLLAR;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.RIGHT_SIDE);

        final String actualFormattedWord = wordFormater.getFormattedWord(word,punctuation,position,iteration);
        final String expectedFormatteddWord = "password$321";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumRightSide_SingleWordWithPunctuationPositiveIterationFourDigits_createdWord(){
        final String word = "password";
        final int iteration = 4321;
        final PunctuationStrategy punctuation = PunctuationStrategy.DOLLAR;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.RIGHT_SIDE);

        final String actualFormattedWord = wordFormater.getFormattedWord(word,punctuation,position,iteration);
        final String expectedFormatteddWord = "password$4321";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumRightSide_SingleWordWithoutPunctuationPositiveIteration_createdWord(){
        final String word = "password";
        final int iteration = 21;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.RIGHT_SIDE);

        final String actualFormattedWord = wordFormater.getFormattedWord(word,iteration);
        final String expectedFormatteddWord = "password21";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumRightSide_SingleWordNullWithPunctuationPositiveIteration_thrownIllegalArgEx(){
        final String word = null;
        final int iteration = 4;
        final PunctuationStrategy punctuation = PunctuationStrategy.DOLLAR;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.RIGHT_SIDE);

        assertThatThrownBy(()->wordFormater.getFormattedWord(word,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_WordToLowerNumRightSide_SingleWordWithNullPunctuationPositiveIteration_thrownIllegalArgEx(){
        final String word = "password";
        final int iteration = 4321;
        final PunctuationStrategy punctuation = null;
        final PunctuationStrategy.Position position = null;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.RIGHT_SIDE);

        assertThatThrownBy(()->wordFormater.getFormattedWord(word,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_WordToLowerNumRightSide_SingleWordWithPunctuationAllParamsNullPositiveIteration_thrownIllegalArgEx(){
        final String word = null;
        final int iteration = 4321;
        final PunctuationStrategy punctuation = null;
        final PunctuationStrategy.Position position = null;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.RIGHT_SIDE);

        assertThatThrownBy(()->wordFormater.getFormattedWord(word,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getFormatted_WordToLowerNumRightSide_SingleWordWithPunctuationAtFirstZeroIteration_createdWord(){
        final String word = "password";
        final int iteration = 0;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.FIRST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.RIGHT_SIDE);

        final String actualFormattedWord = wordFormater.getFormattedWord(word,punctuation,position,iteration);
        final String expectedFormatteddWord = "_password0";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumRightSide_SingleWordWithPunctuationAtMiddleZeroIteration_createdWord(){
        final String word = "password";
        final int iteration = 0;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.MIDDLE;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.RIGHT_SIDE);

        final String actualFormattedWord = wordFormater.getFormattedWord(word,punctuation,position,iteration);
        final String expectedFormatteddWord = "pass_word0";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumRightSide_SingleWordWithPunctuationAtLastZeroIteration_createdWord(){
        final String word = "password";
        final int iteration = 0;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.RIGHT_SIDE);

        final String actualFormattedWord = wordFormater.getFormattedWord(word,punctuation,position,iteration);
        final String expectedFormatteddWord = "password_0";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumRightSide_SingleWordWithoutPunctuationZeroIteration_createdWord(){
        final String word = "password";
        final int iteration = 0;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.RIGHT_SIDE);

        final String actualFormattedWord = wordFormater.getFormattedWord(word,iteration);
        final String expectedFormatteddWord = "password0";

        assertThat(actualFormattedWord).isEqualTo(expectedFormatteddWord);
    }
    @Test
    void getFormatted_WordToLowerNumRightSide_SingleWordNullWithPunctuationZeroIteration_thrownIllegalArgEx(){
        final String word = null;
        final int iteration = 0;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.RIGHT_SIDE);

        assertThatThrownBy(()->wordFormater.getFormattedWord(word,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_WordToLowerNumRightSide_SingleWordWithNullPunctuationZeroIteration_thrownIllegalArgEx(){
        final String word = "password";
        final int iteration = 0;
        final PunctuationStrategy punctuation = null;
        final PunctuationStrategy.Position position = null;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.RIGHT_SIDE);

        assertThatThrownBy(()->wordFormater.getFormattedWord(word,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_WordToLowerNumRightSide_SingleWordWithPunctuationAllParamsNullZeroIteration_thrownIllegalArgEx(){
        final String word = null;
        final int iteration = 0;
        final PunctuationStrategy punctuation = null;
        final PunctuationStrategy.Position position = null;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.RIGHT_SIDE);

        assertThatThrownBy(()->wordFormater.getFormattedWord(word,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getFormatted_WordToLowerNumRightSide_SingleWordWithPunctuationAtFirstNegativeIteration_thrownIllegalArgEx(){
        final String word = "password";
        final int iteration = -1;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.FIRST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.RIGHT_SIDE);

        assertThatThrownBy(()->wordFormater.getFormattedWord(word,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getFormatted_WordToLowerNumRightSide_SingleWordWithPunctuationAtMiddleNegativeIteration_thrownIllegalArgEx(){
        final String word = "password";
        final int iteration = -4;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.MIDDLE;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.RIGHT_SIDE);

        assertThatThrownBy(()->wordFormater.getFormattedWord(word,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_WordToLowerNumRightSide_SingleWordWithPunctuationAtLastNegativeIteration_thrownIllegalArgEx(){
        final String word = "password";
        final int iteration = -3;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.LAST;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.RIGHT_SIDE);

        assertThatThrownBy(()->wordFormater.getFormattedWord(word,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_WordToLowerNumRightSide_SingleWordWithoutPunctuationNegativeIteration_thrownIllegalArgEx(){
        final String word = "password";
        final int iteration = -2;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.RIGHT_SIDE);

        assertThatThrownBy(()->wordFormater.getFormattedWord(word,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_WordToLowerNumRightSide_SingleWordNullWithPunctuationNegativeIteration_thrownIllegalArgEx(){
        final String word = "password";
        final int iteration = -1;
        final PunctuationStrategy punctuation = null;
        final PunctuationStrategy.Position position =null;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.RIGHT_SIDE);

        assertThatThrownBy(()->wordFormater.getFormattedWord(word,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void getFormatted_WordToLowerNumRightSide_SingleWordWithNullPunctuationNegativeIteration_thrownIllegalaArgEx(){
        final String word = "password";
        final int iteration = -4;
        final PunctuationStrategy punctuation = PunctuationStrategy.UNDERSCORE;
        final PunctuationStrategy.Position position = PunctuationStrategy.Position.MIDDLE;
        wordFormater = new WordFormater(CapitalizeStrategy.WORD_TO_LOWER_CASE, NumberStrategy.RIGHT_SIDE);

        assertThatThrownBy(()->wordFormater.getFormattedWord(word,punctuation,position,iteration))
                .isInstanceOf(IllegalArgumentException.class);
    }


}