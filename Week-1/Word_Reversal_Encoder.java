public class Word_Reversal_Encoder {

    static String reverseEachWord(String sentence) {
        String[] words = sentence.split(" ");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            StringBuilder reversed = new StringBuilder(words[i]);
            reversed.reverse();

            result.append(reversed);

            if (i < words.length - 1) {
                result.append(" ");
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        String sentence = "hello club";

        System.out.println(reverseEachWord(sentence));
    }
}
