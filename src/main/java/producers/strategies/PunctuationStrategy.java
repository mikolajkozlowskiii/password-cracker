package producers.strategies;

public enum PunctuationStrategy {
    BLANK(""),
    DOT("."),
    EXCLUSIVE("!"),
    HASHTAG("#"),
    COMMA(","),
    QUESTION("?"),
    HYPHEN("-"),
    UNDERSCORE("_"),
    SLASH("/"),
    BACKSLASH("\\");
    public enum POSITION{
        FIRST,
        MIDDLE,
        LAST
    }
    private final String text;
    PunctuationStrategy(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
