package org.q10viking.file;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class GetPort {

	public static int getPort() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse("server.xml");
			NodeList list = doc.getElementsByTagName("port");
			Node portNode = list.item(0);
			String port = portNode.getTextContent();

			return Integer.parseInt(port);
		} catch (Exception e) {
			return 80;
		}
	}
	
	public static void main(String[] args) {
		System.out.println("The port is: "+getPort());
	}
	

}
