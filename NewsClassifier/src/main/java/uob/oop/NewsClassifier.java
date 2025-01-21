package uob.oop;

import java.text.DecimalFormat;

public class NewsClassifier {
    public String[] myHTMLs;
    public String[] myStopWords = new String[127];
    public String[] newsTitles;
    public String[] newsContents;
    public String[] newsCleanedContent;
    public double[][] newsTFIDF;

    private final String TITLE_GROUP1 = "Osiris-Rex's sample from asteroid Bennu will reveal secrets of our solar system";
    private final String TITLE_GROUP2 = "Bitcoin slides to five-month low amid wider sell-off";

    public Toolkit myTK;

    public NewsClassifier() {
        myTK = new Toolkit();
        myHTMLs = myTK.loadHTML();
        myStopWords = myTK.loadStopWords();

        loadData();
    }

    public static void main(String[] args) {
        NewsClassifier myNewsClassifier = new NewsClassifier();

        myNewsClassifier.newsCleanedContent = myNewsClassifier.preProcessing();

        myNewsClassifier.newsTFIDF = myNewsClassifier.calculateTFIDF(myNewsClassifier.newsCleanedContent);

        //Change the _index value to calculate similar based on a different news article.
        double[][] doubSimilarity = myNewsClassifier.newsSimilarity(0);

        System.out.println(myNewsClassifier.resultString(doubSimilarity, 10));

        String strGroupingResults = myNewsClassifier.groupingResults(myNewsClassifier.TITLE_GROUP1, myNewsClassifier.TITLE_GROUP2);
        System.out.println(strGroupingResults);
    }

    public void loadData() {
        //TODO 4.1 - 2 marks
        newsTitles = new String[myHTMLs.length];
        newsContents = new String[myHTMLs.length];
        for (int i = 0; i < myHTMLs.length; i++) {
            String html = myHTMLs[i];

            newsTitles[i] = HtmlParser.getNewsTitle(html);
            newsContents[i] = HtmlParser.getNewsContent(html);

        }
    }

    public String[] preProcessing() {
        //TODO 4.2 - 5 marks
        String[] myCleanedContent = new String[newsContents.length];
        int i = 0;
        while (i < newsContents.length) {
            String cleanedContent = NLP.textCleaning(newsContents[i]);

            cleanedContent = NLP.textLemmatization(cleanedContent);

            cleanedContent = NLP.removeStopWords(cleanedContent, myStopWords);

            myCleanedContent[i] = cleanedContent;

            i++;
        }
        return myCleanedContent;
    }

    public double[][] calculateTFIDF(String[] _cleanedContents) {
        //TODO 4.3 - 10 marks
        String[] vocabularyList = buildVocabulary(_cleanedContents);
        double[][] myTFIDF = new double[_cleanedContents.length][vocabularyList.length];

        for (int i = 0; i < _cleanedContents.length; i++) {
            String cleanedContent = _cleanedContents[i];
            if (cleanedContent != null) {
                String[] words = cleanedContent.split(" ");

                int[] termFrequency = new int[vocabularyList.length];
                int totalWords = words.length;

                for (String word : words) {
                    for (int j = 0; j < vocabularyList.length; j++) {
                        if (word.trim().equals(vocabularyList[j].trim())) {
                            termFrequency[j]++;
                            break;
                        }
                    }
                }
                int[] documentFrequency = new int[vocabularyList.length];
                for (int k = 0; k < vocabularyList.length; ++k) {
                    for (String content : _cleanedContents) {
                        String[] contentWords = content.split(" ");
                        for (String word : contentWords) {
                            if (word.equals(vocabularyList[k])) {
                                documentFrequency[k]++;
                                break;
                            }
                        }
                    }
                }
                for (int j = 0; j < vocabularyList.length; j++) {
                    int wordFrequency = termFrequency[j];
                    if (wordFrequency > 0) {
                        double tf = (double) wordFrequency / totalWords;
                        double idf = Math.log((double) _cleanedContents.length / documentFrequency[j]) + 1;
                        myTFIDF[i][j] = tf * idf;
                    }
                }
            }
        }
        return myTFIDF;
    }


    public String[] buildVocabulary(String[] _cleanedContents) {
        //TODO 4.4 - 10 marks
        int vocabIndex = 0;
        int maxVocabSize = 800;

        String[] arrayVocabulary = new String[maxVocabSize];

        for (String content : _cleanedContents) {
            if (content != null) {
                String words[] = content.split(" ");

                for (String word : words) {
                    Boolean found = false;
                    for (int i = 0; i < vocabIndex; i++) {
                        if (arrayVocabulary[i].equals(word)) {
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        arrayVocabulary[vocabIndex] = word;
                        vocabIndex++;

                        if (vocabIndex >= maxVocabSize) {
                            maxVocabSize *= 2;
                            String temp[] = new String[maxVocabSize];
                            for (int i = 0; i < arrayVocabulary.length; i++)
                                temp[i] = arrayVocabulary[i];

                            arrayVocabulary = temp;
                        }
                    }
                }
            }
        }
        String vocabulary[] = new String[vocabIndex];
        for (int i = 0; i < vocabIndex; i++)
            vocabulary[i] = arrayVocabulary[i];


        return vocabulary;
    }


    public double[][] newsSimilarity(int _newsIndex) {
        //TODO 4.5 - 15 marks
        double[][] mySimilarity = new double[newsCleanedContent.length][2];
        Vector news1 = new Vector(newsTFIDF[_newsIndex]);
        int i = 0;
        while (i < newsTFIDF.length) {
            Vector news2 = new Vector(newsTFIDF[i]);
            mySimilarity[i][0] = i;
            mySimilarity[i][1] = news1.cosineSimilarity(news2);
            i++;
        }
        for (i = 0; i < mySimilarity.length - 1; i++) {
            for (int j = 0; j < mySimilarity.length - i - 1; j++) {
                if (mySimilarity[j][1] < mySimilarity[j + 1][1]) {
                    double[] tempArr = mySimilarity[j];
                    mySimilarity[j] = mySimilarity[j + 1];
                    mySimilarity[j + 1] = tempArr;
                }
            }
        }
        return mySimilarity;
    }

    public String groupingResults(String _firstTitle, String _secondTitle) {
        int[] arrayGroup1 = null, arrayGroup2 = null;
        //TODO 4.6 - 15 marks
        int firstIndex = 0;
        for (String i : newsTitles)
            if (i.equals(_firstTitle))
                break;
            else firstIndex++;

        int secondIndex = 0;
        for (String i : newsTitles)
            if (i.equals(_secondTitle))
                break;
            else secondIndex++;

        int[] associatedIndex = new int[newsTitles.length];
        int index = 0;
        int k = 0;
        while (k < newsTitles.length) {
            if (!newsTitles[k].equals(_firstTitle) || !newsTitles[k].equals(_secondTitle)) {
                associatedIndex[index] = k;
                index++;
            }
            k++;
        }

        double[][] related_TFIDF = new double[newsTitles.length][];
        int z = 0;
        while (z < newsTitles.length) {
            related_TFIDF[z] = newsTFIDF[associatedIndex[z]];
            z++;
        }

        double[][] associatedCS = new double[newsTitles.length][newsTitles.length];
        int m = 0;
        while (m < related_TFIDF.length) {
            int l = 0;
            while (l < related_TFIDF.length) {
                Vector news1 = new Vector(related_TFIDF[m]);
                Vector news2 = new Vector(related_TFIDF[l]);
                associatedCS[m][l] = news1.cosineSimilarity(news2);
                l++;
            }
            m++;
        }

        arrayGroup1 = new int[index];
        arrayGroup2 = new int[index];
        int counter1 = 0;
        int counter2 = 0;
        int i = 0;
        while (i < index) {
            double simFirstTitle = associatedCS[i][0];
            double simSecondTitle = associatedCS[i][1];
            if (simSecondTitle > simFirstTitle) {
                arrayGroup2[counter2] = associatedIndex[i];
                counter2++;
            } else {
                arrayGroup1[counter1] = associatedIndex[i];
                counter1++;
            }
            i++;
        }
        int[] temp1 = new int[counter1];
        int[] temp2 = new int[counter2];
        for (int j = 0; j < counter1; j++)
            temp1[j] = arrayGroup1[j];
        for (int j = 0; j < counter2; j++)
            temp2[j] = arrayGroup2[j];
        arrayGroup1 = temp1;
        arrayGroup2 = temp2;
        return resultString(arrayGroup1, arrayGroup2);
    }

    public String resultString(double[][] _similarityArray, int _groupNumber) {
        StringBuilder mySB = new StringBuilder();
        DecimalFormat decimalFormat = new DecimalFormat("#.#####");
        for (int j = 0; j < _groupNumber; j++) {
            for (int k = 0; k < _similarityArray[j].length; k++) {
                if (k == 0) {
                    mySB.append((int) _similarityArray[j][k]).append(" ");
                } else {
                    String formattedCS = decimalFormat.format(_similarityArray[j][k]);
                    mySB.append(formattedCS).append(" ");
                }
            }
            mySB.append(newsTitles[(int) _similarityArray[j][0]]).append("\r\n");
        }
        mySB.delete(mySB.length() - 2, mySB.length());
        return mySB.toString();
    }

    public String resultString(int[] _firstGroup, int[] _secondGroup) {
        StringBuilder mySB = new StringBuilder();
        mySB.append("There are ").append(_firstGroup.length).append(" news in Group 1, and ").append(_secondGroup.length).append(" in Group 2.\r\n").append("=====Group 1=====\r\n");

        for (int i : _firstGroup) {
            mySB.append("[").append(i + 1).append("] - ").append(newsTitles[i]).append("\r\n");
        }
        mySB.append("=====Group 2=====\r\n");
        for (int i : _secondGroup) {
            mySB.append("[").append(i + 1).append("] - ").append(newsTitles[i]).append("\r\n");
        }

        mySB.delete(mySB.length() - 2, mySB.length());
        return mySB.toString();
    }

}
