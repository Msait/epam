package texteditor;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import texteditor.core.Punctuation;
import texteditor.core.Sentence;
import texteditor.core.Word;

public class TextEditorTest {
    String strWithWhiteSpaces;
    String strWithRepetitions;
    Sentence sentence;
    Word word1;
    Word word2;
    Word word3;
    Word word4;
    Word word5;

    @Before
    public void setUp() {
	strWithWhiteSpaces = "  test      string  \nNext line here.";
	strWithRepetitions = "Some text in this text is. " + "Hello is hello. "
		+ "Same means lame" + "Alex isAlex";

	word1 = new Word("Word");
	word2 = new Word("Word.");
	word3 = new Word(";Word.");
	word4 = new Word(";Wo,rd.");
	word5 = new Word("self-confident;");
    }

    @Test
    public void testPunctuationCleanInWord() {
	Word word1 = new Word("Word");
	word1.clearPunctuations();
	Word word2 = new Word("Word.");
	word2.clearPunctuations();
	Word word3 = new Word(";Word.");
	word3.clearPunctuations();
	Word word4 = new Word(";Wo,rd.");
	word4.clearPunctuations();
	Word word5 = new Word("self-confident;");
	word5.clearPunctuations();

	String expected = "Word";

	assertEquals(expected, word1.toString());
	assertEquals(expected, word2.toString());
	assertEquals(expected, word3.toString());
	assertEquals(expected, word4.toString());
	assertEquals("self-confident", word5.toString());
    }

    @Test
    public void testContainsPunctuation() {
	assertEquals(0, Punctuation.contains(word1));
	assertEquals(1, Punctuation.contains(word2));
	assertEquals(2, Punctuation.contains(word3));
	assertEquals(3, Punctuation.contains(word4));
	assertEquals(1, Punctuation.contains(word5));
    }

    @Test
    public void testWordCompare() {
	Word expected = new Word("Word");
	int compare = word1.compareTo(expected);
	assertTrue( compare == 0);
	
	compare = word1.compareTo(word3);
	assertTrue( compare > 0);
    }

    @Test
    public void testWordCompareLessThan() {
	Word expected = new Word("word");
	int compare = word1.compareTo(expected);
	assertTrue( compare < 0);
	
	expected = new Word("word");
    }
    
    @Test
    public void testWordCompareGreaterThan() {
	int compare = word1.compareTo(word2);
	assertTrue( compare < 0);
    }
    
    @Test
    public void testWordCompareDiffWords() {
	Word w1 = new Word("Samuel");
	Word w2 = new Word("Sam");
	int compare = w1.compareTo(w2);
	assertTrue( compare > 0);
    }

}
