package texteditor.core;

public class Char implements Comparable<Char> {
    private char ch;

    public Char() {

    }

    public Char(char ch) {
	this.ch = ch;
    }

    @Override
    public int compareTo(Char other) {
	return Character.compare(Character.toLowerCase(ch), Character.toLowerCase( other.getValue() ));
    }

    public char getValue() {
	return ch;
    }

    @Override
    public String toString() {
	return Character.toString(ch);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;

	if (obj instanceof Char)
	    return ((Char) obj).getValue() == ch;

	return false;
    }
}
