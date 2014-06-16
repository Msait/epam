package com.imobile.parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.imobile.model.CallPrices;
import com.imobile.model.Parameters;
import com.imobile.model.Tariff;

public class ParserSAX extends DefaultHandler {
    
    private List<Tariff> listOfTariffs;
    private Tariff tariff;
    private CallPrices callPrices;
    private Parameters parameters;
    private String tagValue;
    private String startElement;
    
    public ParserSAX() {
	listOfTariffs = new ArrayList<Tariff>();
    }
    
    public List<Tariff> parse(String fileName) {
	SAXParserFactory factory = SAXParserFactory.newInstance();
	SAXParser saxParser;
	
	try {
	    saxParser = factory.newSAXParser();
	    saxParser.parse(new File( fileName ), this);
	    
	    return listOfTariffs;
	    
	} catch (ParserConfigurationException e) {
	    e.printStackTrace();
	} catch (SAXException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	} 
	
	return null;
    }
    
    @Override
    public void startElement(String uri, String localName, String qName,
	    Attributes attributes) throws SAXException {
	
	startElement = qName;

	if ( "tariff-element".equalsIgnoreCase( startElement ) ) {
	    tariff = new Tariff();

	    if ( attributes.getIndex("id") != -1 ) {
		String id = attributes.getValue(attributes.getIndex("id"));
		tariff.setId(id);
	    }

	} else if ( "call-prices".equalsIgnoreCase( startElement ) ) {
	    callPrices = new CallPrices();
	} else if ( "parameters".equalsIgnoreCase( startElement ) ) {
	    parameters = new Parameters();
	}

    }

    @Override
    public void characters(char[] ch, int start, int length)
	    throws SAXException {

	tagValue = new String(ch, start, length);
	setTagValue(tagValue);

    }

    @Override
    public void endElement(String uri, String localName, String qName)
	    throws SAXException {

	if ("tariff-element".equalsIgnoreCase(qName)) {
	    listOfTariffs.add(tariff);
	} else if ("call-prices".equalsIgnoreCase(qName)) {
	    tariff.setCallPrices(callPrices);
	} else if ("parameters".equalsIgnoreCase(qName)) {
	    tariff.setParameters(parameters);
	} 
	
	startElement = "";
    }

    private void setTagValue(String qName) {
	if ( "name".equalsIgnoreCase( startElement ) ) {
	    tariff.setName(tagValue);
	} else if ("operator".equalsIgnoreCase( startElement )) {
	    tariff.setOperator(tagValue);
	} else if ("payroll".equalsIgnoreCase( startElement )) {
	    tariff.setPayroll(Double.parseDouble(tagValue));
	} else if ("within-the-network".equalsIgnoreCase( startElement )) {
	    callPrices.setWithinNetwork(Double.parseDouble(tagValue));
	} else if ("other-networks".equalsIgnoreCase( startElement )) {
	    callPrices.setOtherNetwork(Double.parseDouble(tagValue));
	} else if ("landlines".equalsIgnoreCase( startElement )) {
	    callPrices.setLandlines(Double.parseDouble(tagValue));
	} else if ("SMS-price".equalsIgnoreCase( startElement )) {
	    tariff.setSmsPrice(Double.parseDouble(tagValue));
	} else if ("favorite-number".equalsIgnoreCase( startElement )) {
	    parameters.setFavoriteNumber(tagValue);
	} else if ("tarification".equalsIgnoreCase( startElement )) {
	    parameters.setTarification(tagValue);
	} else if ("connection-fee".equalsIgnoreCase( startElement )) {
	    parameters.setConnectionFee(Double.parseDouble(tagValue));
	}
    }
    
}
