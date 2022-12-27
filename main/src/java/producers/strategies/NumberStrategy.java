package producers.strategies;

public enum NumberStrategy {
    LEFT_SIDE{
        @Override
        public String doFormat(String word, int iteration){
            return iteration+word;
        }
    },
    RIGHT_SIDE{
        @Override
        public String doFormat(String word, int iteration){
            return word+iteration;
        }
    },
    BOTH_SIDES{
        @Override
        public String doFormat(String word, int iteration){
            if(iteration == 0){ //if first iteration, we don't want to add any numbers
                return word;
            }
            final int currentNum = iteration-1; //starting adding numbers from 0
            final String currentNumStr = String.valueOf(currentNum);
            final int numOfDigits = currentNumStr.length();
            String leftNum, rightNum;
            final int middleOfNum = numOfDigits/2;
            if(numOfDigits%2==0){
                leftNum = currentNumStr.substring(0,middleOfNum);
            }
            else{
                leftNum = "0" + currentNumStr.substring(0,middleOfNum);
            }
            rightNum = currentNumStr.substring(middleOfNum);
            return leftNum + word + rightNum;
        }
    };
    public abstract String doFormat(String word, int iteration);
}
