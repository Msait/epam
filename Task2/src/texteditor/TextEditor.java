package texteditor;

import java.util.ArrayList;
import java.util.List;

import texteditor.core.Punctuation;
import texteditor.core.Sentence;
import texteditor.core.Word;

/**
 * Создать программу обработки текста учебника по программированию с
 * использованием классов: Символ, Слово, Предложение, Знак препинания и др.
 * Во всех задачах с формированием текста заменять табуляции и
 * последовательности пробелов одним пробелом.
 * 
 * 3. Найти такое слово в первом предложении, которого нет ни в одном из
 * остальных предложений.
 */
public class TextEditor {

    private List<Sentence> sentences;

    public static final String text = "Следовательно, использовать именованную "
	    + "ссылку для каждого из возможных объектов не удастся: МуТуре "
	    + "aReference; так как заранее неизвестно, сколько таких ссылок "
	    + "реально потребуется. В большинстве языков существуют "
	    + "некоторые пути решения этой крайне насущной задачи. "
	    + "В Java предусмотрено несколько способов хранения объектов "
	    + "(или, точнее, ссылок на объекты). Встроенным типом является"
	    + " массив, который мы уже рассмотрели. Библиотека утилит Java"
	    + " (java.util.*) также содержит достаточно полный набор "
	    + "классов контейнеров (также известных, как классы кол­лекций,"
	    + " но, поскольку имя Collection (коллекция) используется для "
	    + "обозначения определенного подмножества библиотеки Java, я "
	    + "буду употреблять общий термин «контейнер»). Контейнеры "
	    + "обладают весьма изощренными возможностями для хранения "
	    + "объектов и работы с ними, и с их помощью удается решить "
	    + "огромное количество задач.следовательно.";

    public static final String text2 = "Our next sorting algorithm, merge sort. "
	    + "Our sorting algorithm, merge sort. "
	    + "Our algorithm, merge sort. "
	    + "Our sorting algorithm, merge sort.";

    public TextEditor(String text) {
	sentences = new ArrayList<Sentence>();
	String[] splitedSentences = text.split("[.]\\s+");

	for (int i = 0; i < splitedSentences.length; i++) {
	    sentences.add(new Sentence(splitedSentences[i]));
	}
    }

    public static void main(String[] args) {
	String formatedStr = Punctuation.removeDoubleSpaces(text);
	
	System.out.println( formatedStr );
	
	TextEditor te = new TextEditor( formatedStr );
	
	System.out.println(te);
	
	Word result = findUnicWord(te);
	
	System.out.println("\nFOUND: " + (result == null ? "0" : result.toString()));
    }
    
    public Sentence getSentence(int index) {
	return sentences.get(index);
    }

    public int size() {
	return sentences.size();
    }

    public static Word findUnicWord(TextEditor te) {
	boolean isUnique = true;
	for (Word word : te.getSentence(0)) {

//	    System.out.println("Word: " + word);
	    // go through next sentences
	    isUnique = true;
	    for (int i = 1; i < te.size(); i++) {
		if (isUnique == false)
		    break;

		for (Word otherElement : te.getSentence(i)) {
//		    System.out.printf("check: %s == %s ?\n",word, otherElement);
		    if (word.compareTo(otherElement) == 0) {
			isUnique = false;
			break; // continue to search in other sentences
		    }
		}
	    }
	    // if we found unique word
	    if (isUnique == true)
		return word;
	}

	return null;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Sentence sentence: sentences) {
            sb.append( sentence.toString() ).append(" ");
	}
        return sb.toString();
    }

}
