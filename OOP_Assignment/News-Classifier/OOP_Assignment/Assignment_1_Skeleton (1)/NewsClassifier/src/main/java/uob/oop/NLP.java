package uob.oop;

public class NLP {
    /***
     * Clean the given (_content) text by removing all the characters that are not 'a'-'z', '0'-'9' and white space.
     * @param _content Text that need to be cleaned.
     * @return The cleaned text.
     */
    public static String textCleaning(String _content) {
        //TODO Task 2.1 - 3 marks
        char[] contentArray = _content.toLowerCase().toCharArray();
        StringBuilder fs = new StringBuilder();
        for (char c : contentArray)
            if (c >= '0' && c <= '9' || c >= 'a' && c <= 'z' || c == ' ')
                fs.append(c);
        char[] cArr = fs.toString().toCharArray();
        String string = new String(cArr);
        return string;
    }

    public static String textLemmatization(String _content) {
        StringBuilder sbContent = new StringBuilder();
        //TODO Task 2.2 - 3 marks
        String[] words = _content.split(" ");
        for (String i : words) {

            if (i.endsWith("ing"))
                sbContent.append(i.substring(0, i.length() - 3)).append(" ");
            else if (i.endsWith("ed"))
                sbContent.append(i.substring(0, i.length() - 2)).append(" ");
            else if (i.endsWith("es"))
                sbContent.append(i.substring(0, i.length() - 2)).append(" ");
            else if (i.endsWith("s"))
                sbContent.append(i.substring(0, i.length() - 1)).append(" ");
            else
                sbContent.append(i).append(" ");
        }
        return sbContent.toString().trim();
    }

    /***
     * Remove stop-words from the text.
     * @param _content The original text.
     * @param _stopWords An array that contains stop-words.
     * @return Modified text.
     */
    public static String removeStopWords(String _content, String[] _stopWords) {
        StringBuilder sbContent = new StringBuilder();
        //TODO Task 2.3 - 3 marks
        String[] words = _content.split(" ");
        for (String i : words) {
            Boolean found = false;
            for (String word : _stopWords) {
                if (i.equals(word)) {
                    found = true;
                    break;
                }
            }
            if (!found)
                sbContent.append(i).append(" ");
        }
        return sbContent.toString().trim();
    }
}



