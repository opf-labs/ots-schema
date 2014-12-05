/* 
 * Copyright 2010 Harvard University Library
 * 
 * This file is part of OTS-Schemas.
 * 
 * OTS-Schemas is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * OTS-Schemas is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with OTS-Schemas.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.harvard.hul.ois.ots.schemas.StaxUtils;

import java.io.FileReader;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XmlValidator {
	
	public static void main(String[] args) throws JDOMException, IOException, ParserConfigurationException, SAXException {
		System.out.println(validateXml(args[0]));
	}
	
	public static boolean validateXml(String xml) {
		SAXBuilder builder =  new SAXBuilder("org.apache.xerces.parsers.SAXParser", true);
		builder.setFeature("http://apache.org/xml/features/validation/schema", true);
		
		try {
			builder.build(new InputSource(new FileReader(xml)));
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		} 
	    return true;
	}

}
