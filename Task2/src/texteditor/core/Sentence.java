package texteditor.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Sentence implements Iterable<Word> {

    private List<Word> words;

    public Sentence(String text) {

	String[] splitedWords = text.split("[\\s+.]");

	words = new ArrayList<Word>();

	for (int i = 0; i < splitedWords.length; i++) {
	    // add words without punctuation marks
	    Word word = new Word(splitedWords[i]);
	    words.add( Punctuation.clear(word) );
	}
    }
    
    @Override
    public Iterator<Word> iterator() {
	
	return new Iterator<Word>() {
	    int index = 0;

	    @Override
	    public boolean hasNext() {
		return index < words.size();
	    }

	    @Override
	    public Word next() {
		return words.get(index++);
	    }

	    @Override
	    public void remove() {}
	    
	};
    }
    
    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder();
	
        for (Word word : words) {
            sb.append( word.toString() ).append(" ");
	}
        return sb.toString();
    }

}
