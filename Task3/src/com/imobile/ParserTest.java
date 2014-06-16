package com.imobile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.imobile.model.Tariff;
import com.imobile.parser.ParserDOM;
import com.imobile.parser.ParserSAX;
import com.imobile.parser.ParserStAX;

public class ParserTest {
    
    public static void main(String[] args) {
	List<Tariff> listOfTariffs = new ArrayList<Tariff>();
	
	// DOM PARSER
	ParserDOM dom = new ParserDOM();
	listOfTariffs = dom.parseDOM("assets/kievstar.xml");
	Collections.sort(listOfTariffs);
	
	printTariffs(ParserDOM.class.getSimpleName().toUpperCase(), listOfTariffs);
	
	// SAX PARSER
	ParserSAX parserSAX = new ParserSAX();
	listOfTariffs = parserSAX.parse("assets/kievstar.xml");

	printTariffs(ParserSAX.class.getSimpleName().toUpperCase(), listOfTariffs);
	
	// StAX PARSER
	ParserStAX parserStAX = new ParserStAX();
	listOfTariffs = parserStAX.parse("assets/kievstar.xml");
	
	printTariffs(ParserStAX.class.getSimpleName().toUpperCase(), listOfTariffs);
	
	// SAVE XML
	ParserDOM.saveToXML(listOfTariffs, "assets/copy.xml");
    }

    
    public static void printTariffs(String str, List<Tariff> listOfTariffs) {
	
	System.out.print( String.format("%25s%n", str) );
	
	for (Tariff t : listOfTariffs) {
	    System.out.println(t + "\n\n");
	}
    }
}
