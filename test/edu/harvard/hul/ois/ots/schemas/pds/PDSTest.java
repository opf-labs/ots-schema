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

package edu.harvard.hul.ois.ots.schemas.pds;

import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.custommonkey.xmlunit.*;

import edu.harvard.hul.ois.ots.schemas.pds.PdsMD;


public class PDSTest  extends XMLTestCase {
	
	 private String xmlSample =
		"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
	 	"<pds xmlns=\"http://hul.harvard.edu/ois/xml/ns/pds\" " +
	 	"xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " + 
	 	"xsi:schemaLocation=\"http://hul.harvard.edu/ois/xml/ns/pds " + 
	 	"http://hul.harvard.edu/ois/xml/xsd/pds/pds.xsd\">" +
		 	"<date>" +
		 		"<fromDate>2007-08-24</fromDate>" + 
		 		"<toDate>2005</toDate>" +
		 	"</date>" +
		 	"<function status=\"false\">goto</function>" +
		 	"<function status=\"false\">viewocr</function>" +
		 	"<header>blah</header>" +
	 	"</pds>";

    
    public void testRead () throws Exception {
        // set up a parser
        XMLInputFactory xmlif = XMLInputFactory.newInstance();
        XMLStreamReader xmlr = xmlif.createXMLStreamReader(new StringReader(xmlSample));
        PdsMD pdsmd = new PdsMD (xmlr,true);  
        xmlr.close();
        String header = pdsmd.getHeader();
        assertNotNull (header);
        Boolean status = pdsmd.getFunctions().get(0).getStatus();
        assertNotNull (status);
        assertTrue ("false".equals(status.toString()));
        assertTrue ("blah".equals(header));
    }
    
    public void testReadWrite () throws Exception {
        // set up a parser
        XMLInputFactory xmlif = XMLInputFactory.newInstance();
        XMLStreamReader xmlr = xmlif.createXMLStreamReader(new StringReader(xmlSample));
        PdsMD pdsmd = new PdsMD (xmlr,true);   
		XMLOutputFactory f = XMLOutputFactory.newInstance();
		StringWriter stringWriter = new StringWriter();
				
		XMLStreamWriter sw = f.createXMLStreamWriter(stringWriter); 
		pdsmd.output(sw);
		sw.close();
	    String newXml = stringWriter.toString();
	    stringWriter.close();
       
	    System.out.println(newXml);
	    
	    XMLUnit.setIgnoreWhitespace(true);
        assertXMLEqual(xmlSample,newXml);
      }  
}
