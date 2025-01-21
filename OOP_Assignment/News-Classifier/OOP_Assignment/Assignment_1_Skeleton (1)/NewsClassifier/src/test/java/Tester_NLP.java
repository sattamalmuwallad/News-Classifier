import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import uob.oop.NLP;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Tester_NLP {

    @Test
    @Order(1)
    void textCleaning(){
        String strTest = "!\"$%&^%H).,e+ll~'/o/Wor.l,d!";
        assertEquals("helloworld", NLP.textCleaning(strTest));
    }

    @Test
    @Order(2)
    void textLemmatization(){
        String strTest = "bananas";
        assertEquals("banana", NLP.textLemmatization(strTest));
    }
   // To test the remove stopwords method since no provided tester
    @Test
    @Order(3)
    void removeStopWords(){
        String[] arr = {"and","or"};
        String strTest = "meshal and omar or saud";
        assertEquals("meshal omar saud", NLP.removeStopWords(strTest,arr));
    }

}
