package com.imobile.parser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;

import com.imobile.model.CallPrices;
import com.imobile.model.Parameters;
import com.imobile.model.Tariff;

public class ParserStAX {
    
    public ParserStAX() {
	
    }
    
    public List<Tariff> parse(String fileName) {
	XMLStreamReader eventReader = null;
	List<Tariff> listOfTariffs;
	try {

	    XMLInputFactory inputFactory = XMLInputFactory.newInstance();
	    InputStream stream = new FileInputStream( fileName );
	    eventReader = inputFactory.createXMLStreamReader(stream);
	    
	    listOfTariffs = new ArrayList<Tariff>();
	    Tariff tariff = null;
	    CallPrices callPrices = null;
	    Parameters parameters = null;
	    String startElement = null;
	    int eventType;
	    
	    while (eventReader.hasNext()) {
		eventType = eventReader.next();
		if ( eventType == XMLEvent.START_ELEMENT) {
		    startElement = eventReader.getName().getLocalPart().toString().trim();
		    if ("tariff-element".equalsIgnoreCase( startElement )) {
			tariff = new Tariff();
			
			// find attribute id
			for (int i = 0; i < eventReader.getAttributeCount(); i++) {
			    if ( eventReader.getAttributeLocalName(i).equalsIgnoreCase("id") ) {
				tariff.setId( eventReader.getAttributeValue(i) );
			    }
			}
		    } else if ("call-prices".equalsIgnoreCase( startElement )) {
			callPrices = new CallPrices();
		    } else if ("parameters".equalsIgnoreCase( startElement )) {
			parameters = new Parameters();
		    }
		    
		} else if ( eventType == XMLEvent.CHARACTERS ) {
		    if ("name".equalsIgnoreCase( startElement )) {
			tariff.setName( eventReader.getText().trim() );
		    } else if ("operator".equalsIgnoreCase( startElement )) {
			tariff.setOperator( eventReader.getText().trim() );
		    } else if ("payroll".equalsIgnoreCase( startElement )) {
			tariff.setPayroll( Double.parseDouble( eventReader.getText().trim() ) );
		    } else if ("within-the-network".equalsIgnoreCase( startElement )) {
			callPrices.setWithinNetwork( Double.parseDouble( eventReader.getText().trim() ) );
		    } else if ("other-networks".equalsIgnoreCase( startElement )) {
			callPrices.setOtherNetwork( Double.parseDouble( eventReader.getText().trim() ) );
		    } else if ("landlines".equalsIgnoreCase( startElement )) {
			callPrices.setLandlines( Double.parseDouble( eventReader.getText().trim() ) );
		    } else if ("favorite-number".equalsIgnoreCase( startElement )) {
			parameters.setFavoriteNumber( eventReader.getText().trim() );
		    } else if ("tarification".equalsIgnoreCase( startElement )) {
			parameters.setTarification( eventReader.getText().trim() );
		    } else if ("connection-fee".equalsIgnoreCase( startElement )) {
			parameters.setConnectionFee( Double.parseDouble( eventReader.getText().trim() ) );
		    }
		    
		} else if ( eventType == XMLEvent.END_ELEMENT ) {
		    String endElement = eventReader.getName().getLocalPart().toString().trim();
		    
		    if ( "tariff-element".equalsIgnoreCase( endElement ) ) {
			listOfTariffs.add(tariff);
		    } else if ( "call-prices".equalsIgnoreCase( endElement ) ) {
			tariff.setCallPrices(callPrices);
		    } else if ( "parameters".equalsIgnoreCase( endElement ) ) {
			tariff.setParameters(parameters);
		    }
		    
		    startElement = "";
		}

	    }
	    
	    return listOfTariffs;

	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (FactoryConfigurationError e) {
	    e.printStackTrace();
	} catch (XMLStreamException e) {
	    e.printStackTrace();
	} finally {
	    if (eventReader != null)
		try {
		    eventReader.close();
		} catch (XMLStreamException e) {
		    e.printStackTrace();
		}
	}
	
	return null;
    }

}
