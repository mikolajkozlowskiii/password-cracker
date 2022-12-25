package producers.strategies;

public enum CapitalizeStrategy {
    WORD_TO_lOWER_CASE{
        @Override
        public String doFormat(String word){
            return word.toLowerCase();
        }
    },
    WORD_TO_UPPER_CASE{
        @Override
        public String doFormat(String word){
            return word.toUpperCase();
        }
    },
    WORD_FIRST_LETTER_UP{
        @Override
        public String doFormat(String word){
            if(word.length()>1){
                return word.substring(0,1).toUpperCase()+word.substring(1);
            }
            return word.toUpperCase();
        }
    };

    public abstract String doFormat(String word);
}
