package texteditor.core;

public class Word implements Comparable<Word> {
    private Char[] word;
    private int length;

    public Word(String s) {
	word = new Char[s.length()];

	for (int i = 0; i < s.length(); i++) {
	    word[i] = new Char(s.charAt(i));
	}
	
	length = s.length();
    }
    
    public Word(Char[] word) {
	this.word = new Char[ word.length ];
	
	for (int i = 0; i < word.length; i++) {
	    this.word[i] = word[i];
	}
	
	length = word.length;
    }
    
    
    

    public void clearPunctuations() {
	// contain punctuation?
	int punctuationsCount = Punctuation.contains(this);
	
	if (0 >= punctuationsCount) {
	    return;
	}
	
	Char[] newWord = new Char[length - punctuationsCount];

	int index = 0;
	for (int i = 0; i < length; i++) {
	    if ( !Punctuation.check(this, i) ) {
		newWord[index++] = word[i];
	    }
	}
	
	word = new Char[newWord.length];
	word = newWord;
	length = word.length;
    }

    public Char getChar(int index) {
	return word[index];
    }

    @Override
    public int compareTo(Word other) {
	int compare;
	int end = length;
	
	if (other.getLength() < length) {
	    end = other.getLength(); 
	}

	for (int j = 0; j < end; j++) {
	    compare = getChar(j).compareTo(other.getChar(j));
	    if (compare != 0) {
		return compare;
	    }
	}
	// if words body equals, check their length
	if (length != other.getLength()) {
	    return length - other.getLength();
	}

	return 0;
    }

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder();
	for (int i = 0; i < word.length; i++) {
	    sb.append(word[i]);
	}
	return sb.toString();
    }

    public int getLength() {
	return length;
    }

    public int findInWord(Char ch, int pos) {

	for (int i = pos; i < length; i++) {
	    if (word[i].equals(ch)) {
		// skip if for example: self-confident
		if (ch.equals(Punctuation.signs[6]) && i < length)
		    continue;
		return i;
	    }
	}
	return -1;
    }
}
