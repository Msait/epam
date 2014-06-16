package com.imobile.parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.imobile.model.CallPrices;
import com.imobile.model.Parameters;
import com.imobile.model.Tariff;

public class ParserDOM {

    public List<Tariff> parseDOM(String fileName) {
	File file = new File(fileName);

	DocumentBuilderFactory builderFactory = DocumentBuilderFactory
		.newInstance();
	DocumentBuilder documentBuilder;

	try {
	    documentBuilder = builderFactory.newDocumentBuilder();
	    Document document = documentBuilder.parse(file);
	    Element root = document.getDocumentElement();

	    List<Tariff> listOfTariffs = new ArrayList<Tariff>();
	    Tariff tariff = null;

	    NodeList nodeList = root.getChildNodes();

	    for (int i = 0; i < nodeList.getLength(); i++) {

		Node node = nodeList.item(i);

		if (node.getNodeType() == Node.ELEMENT_NODE) {
		    Element element = (Element) node;
		    tariff = new Tariff();

		    tariff.setId(element.getAttribute("id"));

		    tariff.setName(getTagValue("name", element));
		    tariff.setOperator(getTagValue("operator", element));

		    Double payroll = Double.parseDouble(getTagValue("payroll",
			    element));
		    tariff.setPayroll(payroll);

		    CallPrices callPrices = new CallPrices();

		    callPrices.setWithinNetwork(Double.parseDouble(getTagValue(
			    "within-the-network", element)));
		    callPrices.setOtherNetwork(Double.parseDouble(getTagValue(
			    "other-networks", element)));
		    callPrices.setLandlines(Double.parseDouble(getTagValue(
			    "landlines", element)));

		    tariff.setCallPrices(callPrices);

		    tariff.setParameters(getParameters(element));
		    listOfTariffs.add(tariff);
		}

	    }

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

    private static Parameters getParameters(Element element) {
	Parameters parameters = new Parameters();

	NodeList fnList = element.getElementsByTagName("favorite-number");

	for (int j = 0; j < fnList.getLength(); j++) {
	    Node fn = fnList.item(j);

	    if (fn.getNodeType() == Node.ELEMENT_NODE) {
		parameters.setFavoriteNumber(fn.getTextContent());
	    }
	}
	parameters.setTarification(getTagValue("tarification", element));
	parameters.setConnectionFee(Double.parseDouble(getTagValue(
		"connection-fee", element)));
	return parameters;
    }

    private static String getTagValue(String name, Element element) {
	return element.getElementsByTagName(name).item(0).getTextContent();
    }

    public static void saveToXML(List<Tariff> listOfTariffs, String fileName) {

	DocumentBuilderFactory builderFactory = DocumentBuilderFactory
		.newInstance();
	DocumentBuilder documentBuilder;
	Document document;

	try {
	    documentBuilder = builderFactory.newDocumentBuilder();
	    document = documentBuilder.newDocument();

	    Element root = document.createElement("tariff");
	    root.setAttribute("xmlns", "http://www.imobile.com/tariff");
	    root.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
	    root.setAttribute("xsi:schemaLocation", "http://www.imobile.com/tariff tariff.xsd");
	    
	    document.appendChild(root);

	    for (Tariff tariff : listOfTariffs) {

		Element tariffElement = document.createElement("tariff-element");
		tariffElement.setAttribute("id", tariff.getId());
		root.appendChild(tariffElement);

		Element name = document.createElement("name");
		name.appendChild(document.createTextNode(tariff.getName()));
		tariffElement.appendChild(name);

		Element operator = document.createElement("operator");
		operator.appendChild(document.createTextNode(tariff
			.getOperator()));
		tariffElement.appendChild(operator);

		Element payroll = document.createElement("payroll");
		payroll.appendChild(document.createTextNode(Double
			.toString(tariff.getPayroll())));
		tariffElement.appendChild(payroll);

		Element callPrices = document.createElement("call-prices");

		Element withinTheNetwork = document
			.createElement("within-the-network");
		Element otherNetworks = document
			.createElement("other-networks");
		Element landlines = document.createElement("landlines");

		String withinTheNetworkVal = Double.toString(tariff
			.getCallPrices().getWithinNetwork());
		withinTheNetwork.appendChild(document
			.createTextNode(withinTheNetworkVal));

		String otherNetworksVal = Double.toString(tariff
			.getCallPrices().getOtherNetwork());
		otherNetworks.appendChild(document
			.createTextNode(otherNetworksVal));

		String landlinesVal = Double.toString(tariff.getCallPrices()
			.getLandlines());
		landlines.appendChild(document.createTextNode(landlinesVal));

		callPrices.appendChild(withinTheNetwork);
		callPrices.appendChild(otherNetworks);
		callPrices.appendChild(landlines);
		tariffElement.appendChild(callPrices);

		Element smsPrice = document.createElement("SMS-price");
		smsPrice.appendChild(document.createTextNode(Double
			.toString(tariff.getSmsPrice())));
		tariffElement.appendChild(smsPrice);

		Element favoriteNumber;
		Element parameters = document.createElement("parameters");
		Element tarification = document.createElement("tarification");
		Element connectionFee = document.createElement("connection-fee");

		Parameters p = tariff.getParameters();
		for (String fn : p.getFavoriteNumbers()) {
		    favoriteNumber = document.createElement("favorite-number");
		    favoriteNumber.appendChild( document.createTextNode(fn) );
		    parameters.appendChild( favoriteNumber );
		}

		String tarificationVal = p.getTarification();
		tarification.appendChild(document
			.createTextNode(tarificationVal));

		String connectionFeeVal = Double.toString(p.getConnectionFee());
		connectionFee.appendChild(document
			.createTextNode(connectionFeeVal));

		parameters.appendChild(tarification);
		parameters.appendChild(connectionFee);

		tariffElement.appendChild(parameters);
	    }
	    
	    TransformerFactory transformerFactory = TransformerFactory
		    .newInstance();
	    Transformer transformer = transformerFactory.newTransformer();
	    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	    
	    DOMSource doc = new DOMSource(document);
	    StreamResult to = new StreamResult(new File( fileName ));
	    // write to file
	    transformer.transform(doc, to);

	} catch (ParserConfigurationException e) {
	    e.printStackTrace();
	} catch (TransformerConfigurationException e) {
	    e.printStackTrace();
	} catch (TransformerException e) {
	    e.printStackTrace();
	}

    }
}
