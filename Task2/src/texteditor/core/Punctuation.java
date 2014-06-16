package texteditor.core;

public final class Punctuation {
    
    public static final Char[] signs = { new Char('.'), new Char(','),
	    new Char('!'), new Char('?'), new Char(':'), new Char(';'),
	    new Char('-'), new Char('('), new Char(')'), new Char('<'), 
	    new Char('>'), new Char('«'), new Char('»'),};

    public static String removeDoubleSpaces(String text) {
	String pattern = "\\s{2,}|\\t";
	return text.replaceAll(pattern, " ");
    }

    public static int contains(Word word) {
	int found = 0;
	
	for (int i = 0; i < word.getLength(); i++) {
	    if (check(word, i) == true)
		found++;
	}
	return found;
    }
    
    public static boolean isSign (Char ch) {
	for (int i = 0; i < signs.length; i++) {
	    if ( signs[i].equals( ch ) ) {
		    return true;
	    }
	}
	return false;
    }

    public static boolean check(Word word, int i) {
	if (isSign(word.getChar(i))) {
//	    if (word.getChar(i).equals(signs[6]) && i < word.getLength()) {
//		return false;
//	    }
//	    if (word.getChar(i).equals(signs[0]) && i < word.getLength()) {
//		return false;
//	    }
	    // maybe its a part of complicated word?
	    if (i < word.getLength()-1 && i != 0) {
		// check last char if it is not a sign
		if ( isSign( word.getChar( i+1 )) == false )
		    return false;
	    }
	    return true;
	}
	return false;
    }
    
    public static Word clear(Word word) {
	// contain punctuation?
	int punctuationsCount = contains(word);
	
	if (0 >= punctuationsCount) {
	    return word;
	}
	
	Char[] newWord = new Char[word.getLength() - punctuationsCount];

	int index = 0;
	for (int i = 0; i < word.getLength(); i++) {
	    if ( !check(word, i) ) {
		newWord[index++] = word.getChar(i);
	    }
	}
	
	return new Word(newWord);
    }
}
