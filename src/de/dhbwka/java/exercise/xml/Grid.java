package de.dhbwka.java.exercise.xml;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class Grid {

	public static void main(String[] args) {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder parser = null;
		try {
			parser = factory.newDocumentBuilder();
			Document doc = parser.newDocument();

			Element kml = doc.createElement("kml");
			kml.setAttribute("xmlns", "http://www.opengis.net/kml/2.2");
			doc.appendChild(kml);
			Element document = doc.createElement("Document");
			kml.appendChild(document);

//			String url = "https://maps.googleapis.com/maps/api/geocode/xml?address=goldbach&sensor=false";
//			Document mapxml = parser.parse(url);
			Document mapxml = parser.parse(new File("C:\\Users\\d070497\\Desktop\\result.xml"));
			Element root = mapxml.getDocumentElement();
			Element bounds = (Element) root.getElementsByTagName("bounds").item(0);
			Element bounds_sw = (Element) bounds.getElementsByTagName("southwest").item(0);
			Element bounds_ne = (Element) bounds.getElementsByTagName("northeast").item(0);

			double dlng = (Double
					.parseDouble(((Element) bounds_ne.getElementsByTagName("lng").item(0)).getTextContent())
					- Double.parseDouble(((Element) bounds_sw.getElementsByTagName("lng").item(0)).getTextContent()))
					/ 4;
			double dlat = (Double
					.parseDouble(((Element) bounds_ne.getElementsByTagName("lat").item(0)).getTextContent())
					- Double.parseDouble(((Element) bounds_sw.getElementsByTagName("lat").item(0)).getTextContent()))
					/ 4;

			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					Element placemark = doc.createElement("Placemark");

					Element name = doc.createElement("name");
					name.setTextContent("Spot");
					Element description = doc.createElement("description");
					description.setTextContent("This is a description ;)");

					document.appendChild(placemark);
					placemark.appendChild(name);
					placemark.appendChild(description);

					Element point = doc.createElement("Point");
					placemark.appendChild(point);
					Element coordinates = doc.createElement("coordinates");
					double yPos = Double.parseDouble(
							((Element) bounds_sw.getElementsByTagName("lng").item(0)).getTextContent()) + i * dlng;
					double xPos = Double.parseDouble(
							((Element) bounds_sw.getElementsByTagName("lat").item(0)).getTextContent()) + j * dlat;
					coordinates.setTextContent("" + yPos + "," + xPos);
					point.appendChild(coordinates);
				}
			}

			TransformerFactory transfac = TransformerFactory.newInstance();
			transfac.setAttribute("indent-number", 4);

			Transformer trans = transfac.newTransformer();
			trans.setOutputProperty(OutputKeys.METHOD, "xml");
			trans.setOutputProperty(OutputKeys.INDENT, "yes");
			trans.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", Integer.toString(2));

			StreamResult result = new StreamResult(System.out);
			DOMSource source = new DOMSource(doc.getDocumentElement());

			trans.transform(source, result);

		} catch (ParserConfigurationException | SAXException | IOException | TransformerException ex) {
			ex.printStackTrace();
		}

	}

	public static Element getNamedChildElement(Node n, String name) {
		return getNamedChildElement(n, name, 0);
	}

	public static Element getNamedChildElement(Node n, String name, int count) {
		for (int i = 0; i < n.getChildNodes().getLength(); i++) {
			Node ithChild = n.getChildNodes().item(i);
			if (ithChild.getNodeType() == Node.ELEMENT_NODE && ithChild.getNodeName().equals(name)) {
				if (count == 0) {
					return (Element) ithChild;
				}
				count--;
			}
		}
		return null;
	}

}