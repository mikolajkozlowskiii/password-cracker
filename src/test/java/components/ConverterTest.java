package components;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ConverterTest {
    private Converter converter;
    @BeforeEach
    void setUp() {
        converter = new Converter();
    }

    @Test
    void convertToMD5ByMD_ParamOk_cratedMD5() {
        final String word = "password";

        final String actualWord = converter.convertToMD5ByMD(word);
        final String expectedWord = "5f4dcc3b5aa765d61d8327deb882cf99";

        assertThat(actualWord).isEqualTo(expectedWord);
    }
    @Test
    void convertToMD5ByMD_EmptyString_cratedMD5() {
        final String word = "";

        final String actualWord = converter.convertToMD5ByMD(word);
        final String expectedWord = "d41d8cd98f00b204e9800998ecf8427e";

        assertThat(actualWord).isEqualTo(expectedWord);
    }
    @Test
    void convertToMD5ByMD_NullString_thrownIllegalArgEx() {
        final String word = null;

        assertThatThrownBy(()->converter.convertToMD5ByMD(word))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void convertToMD5ByGuava_ParamOk_cratedMD5() {
        final String word = "password";

        final String actualWord = converter.convertToMD5ByGuava(word);
        final String expectedWord = "5f4dcc3b5aa765d61d8327deb882cf99";

        assertThat(actualWord).isEqualTo(expectedWord);
    }
    @Test
    void convertToMD5ByGuava_EmptyString_cratedMD5() {
        final String word = "";

        final String actualWord = converter.convertToMD5ByGuava(word);
        final String expectedWord = "d41d8cd98f00b204e9800998ecf8427e";

        assertThat(actualWord).isEqualTo(expectedWord);
    }
    @Test
    void convertToMD5ByGuava_NullString_thrownIllegalArgEx() {
        final String word = null;

        assertThatThrownBy(()->converter.convertToMD5ByMD(word))
                .isInstanceOf(IllegalArgumentException.class);
    }
}