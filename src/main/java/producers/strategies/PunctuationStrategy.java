package producers.strategies;

public enum PunctuationStrategy {
    DOT("."),
    EXCLUSIVE("!"),
    HASHTAG("#"),
    COMMA(","),
    QUESTION("?"),
    PERCENT("%"),
    HYPHEN("-"),
    UNDERSCORE("_"),
    SLASH("/"),
    BACKSLASH("\\");
    public enum Position {
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
