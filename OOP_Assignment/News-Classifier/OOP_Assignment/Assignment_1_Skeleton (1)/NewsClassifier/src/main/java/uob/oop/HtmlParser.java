package uob.oop;

public class HtmlParser {
    /***
     * Extract the title of the news from the _htmlCode.
     * @param _htmlCode Contains the full HTML string from a specific news. E.g. 01.htm.
     * @return Return the title if it's been found. Otherwise, return "Title not found!".
     */
    public static String getNewsTitle(String _htmlCode) {
        //TODO Task 1.1 - 5 marks
        String startTag = "<title>";
        String endTag = " |";
        int startIndex = _htmlCode.indexOf(startTag);
        int endIndex = _htmlCode.indexOf(endTag);

        if (!(startIndex == -1 || endIndex == -1)) {
            String title = _htmlCode.substring(startIndex + startTag.length(), endIndex);
            return title;
        } else
            return "Title not found!";

    }

    /***
     * Extract the content of the news from the _htmlCode.
     * @param _htmlCode Contains the full HTML string from a specific news. E.g. 01.htm.
     * @return Return the content if it's been found. Otherwise, return "Content not found!".
     */
    public static String getNewsContent(String _htmlCode) {
        //TODO Task 1.2 - 5 marks

        int startIndex = _htmlCode.indexOf("\"articleBody\": \"") + "\"articleBody\": \":".length() - 1;
        int endIndex = _htmlCode.indexOf(",\"mainEntityOfPage\":") - 2;

        if (startIndex == -1 || endIndex == -1)
            return "Content not found!";
        else {
            String content = _htmlCode.substring(startIndex, endIndex);
            return content.toLowerCase();
        }


    }

}