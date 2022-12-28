package producers.strategies;

public enum CapitalizeStrategy {
    WORD_TO_LOWER_CASE {
        @Override
        public String doFormat(String word){
            nullCheck(word);
            return word.toLowerCase();
        }
    },
    WORD_TO_UPPER_CASE{
        @Override
        public String doFormat(String word){
            nullCheck(word);
            return word.toUpperCase();
        }
    },
    WORD_FIRST_LETTER_UP{
        @Override
        public String doFormat(String word){
            nullCheck(word);
            if(word.length()>1){
                return word.substring(0,1).toUpperCase()+word.substring(1);
            }
            return word.toUpperCase();
        }
    };

    private static void nullCheck(String word) {
        if(word == null){
            throw new IllegalArgumentException("Word can't be null");
        }
    }

    public abstract String doFormat(String word);
}
