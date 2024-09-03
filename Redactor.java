public class Redactor {
    public static String redact(String input, String[] redactableWords) {
        String[] words = input.split(" ");
        String result = "";

        for (int w = 0; w < words.length; w++) {
            String word = words[w];
            String cleanWord = "";
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (Character.isLetterOrDigit(c)) {
                    cleanWord = cleanWord + c;
                }
            }

            boolean redact = false;
            for (int i = 0; i < redactableWords.length; i++) {
                String redactableWord = redactableWords[i];
                String capsRedactableWord = redactableWord.toUpperCase();
                String capsCleanWord = cleanWord.toUpperCase();
                if (capsCleanWord.equals(capsRedactableWord)) {
                    redact = true;
                    break;
                }
            }

            if (redact == true) {
                String asterisks = "";
                for (int j = 0; j < cleanWord.length(); j++) {
                    asterisks += "*";
                }
                for (int j = cleanWord.length(); j < word.length(); j++) {
                    asterisks += word.charAt(j);
                }
                result = result + asterisks + " ";
            } else {
                result += word + " ";
            }
        }

        return result.trim();
    }

    public static void main(String[] args) {
        // Example input
        String input = "The' quick brown fox jumps over the lazy d0g 2";
        String[] redactableWords = {"fox", "jumps", "d0g","2"};

        // Redact the input and print the result
        String redactedStatement = redact(input, redactableWords);
        System.out.println("Redacted statement: " + redactedStatement);
    }
}


