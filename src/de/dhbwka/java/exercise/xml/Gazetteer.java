package de.dhbwka.java.exercise.xml;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class Gazetteer {

	public static void main(String[] args) {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder parser = null;
		try {
			parser = factory.newDocumentBuilder();
			
//			String url = "https://maps.googleapis.com/maps/api/geocode/xml?address=goldbach&sensor=false";
//			Document doc = parser.parse(url);
			Document doc = parser.parse(new File("C:\\Users\\d070497\\Desktop\\result.xml"));
			Element root = doc.getDocumentElement();
			
			Node formatted_address = root.getElementsByTagName("formatted_address").item(0);
			System.out.println("Formatted Address: " + formatted_address.getTextContent());

			Element address_components = (Element) root.getElementsByTagName("address_component").item(0);
			System.out.println("Long Name: " + ((Element) address_components.getElementsByTagName("long_name").item(0)).getTextContent());
			
			Element location = (Element) root.getElementsByTagName("location").item(0);
			System.out.println("Location: (latitude=" + ((Element) location.getElementsByTagName("lat").item(0)).getTextContent() + ", longitude=" + ((Element) location.getElementsByTagName("lng").item(0)).getTextContent() + ")");
			
			Element bounds = (Element) root.getElementsByTagName("bounds").item(0);
			Element bounds_sw = (Element) bounds.getElementsByTagName("southwest").item(0);
			Element bounds_ne = (Element) bounds.getElementsByTagName("northeast").item(0);

			System.out.print("Bounds: (north=" + ((Element) bounds_ne.getElementsByTagName("lat").item(0)).getTextContent() + ", east=" + ((Element) bounds_ne.getElementsByTagName("lng").item(0)).getTextContent() + ", ");
			System.out.println("south=" + ((Element) bounds_sw.getElementsByTagName("lat").item(0)).getTextContent() + ", west=" + ((Element) bounds_sw.getElementsByTagName("lng").item(0)).getTextContent() + ")");
			
			} catch (ParserConfigurationException | SAXException | IOException| DOMException ex ) {
				System.err.println (ex.getMessage());
			}
		
	}

}