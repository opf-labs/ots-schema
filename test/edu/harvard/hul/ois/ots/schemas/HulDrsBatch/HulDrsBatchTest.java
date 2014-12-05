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

package edu.harvard.hul.ois.ots.schemas.HulDrsBatch;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Date;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.custommonkey.xmlunit.*;

import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlDateFormat;


public class HulDrsBatchTest  extends XMLTestCase {
	
	 private String xmlSample =
		"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
	 	"<hulDrsBatch xmlns=\"http://hul.harvard.edu/ois/xml/ns/hulDrsBatch\" " +
	 	"xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " + 
	 	"xsi:schemaLocation=\"http://hul.harvard.edu/ois/xml/ns/hulDrsBatch " + 
	 	"http://hul.harvard.edu/ois/xml/xsd/drs/hulDrsBatch.xsd\">" +
		"<batchId>123456</batchId>" +
		"<batchName>name</batchName>" +
		"<batchDirectoryName>test</batchDirectoryName>" +
		"<loadStartTime>2010-11-17T10:22:29.463Z</loadStartTime>" +
	 	"</hulDrsBatch>";
	 
	 
    public void testRead () throws Exception {
        // set up a parser
        XMLInputFactory xmlif = XMLInputFactory.newInstance();
        XMLStreamReader xmlr = xmlif.createXMLStreamReader(new StringReader(xmlSample));
        HulDrsBatch batchMD = new HulDrsBatch (xmlr,true);  
        xmlr.close();
        String batchId = batchMD.getBatchId();
        assertNotNull (batchId);
        String batchName = batchMD.getBatchName();
        assertNotNull (batchName);
        String batchDirName = batchMD.getBatchDirectoryName();
        assertNotNull (batchDirName);
        Date loadStartTime = batchMD.getLoadStartTime();
        assertNotNull (loadStartTime);
        
        assertTrue ("123456".equals(batchId));
        assertTrue ("name".equals(batchName));
        assertTrue ("test".equals(batchDirName));
        assertTrue ("2010-11-17T10:22:29.463Z".equals(XmlDateFormat.formatDateTime(loadStartTime)));
    }
    
    public void testReadWrite () throws Exception {
        // set up a parser
        XMLInputFactory xmlif = XMLInputFactory.newInstance();
        XMLStreamReader xmlr = xmlif.createXMLStreamReader(new StringReader(xmlSample));
        HulDrsBatch batchMD = new HulDrsBatch (xmlr,true);   
		XMLOutputFactory f = XMLOutputFactory.newInstance();
		StringWriter stringWriter = new StringWriter();
				
		XMLStreamWriter sw = f.createXMLStreamWriter(stringWriter); 
		batchMD.output(sw);
		sw.close();
	    String newXml = stringWriter.toString();
	    stringWriter.close();
       
	    System.out.println(newXml);
	    
	    XMLUnit.setIgnoreWhitespace(true);
        assertXMLEqual(xmlSample,newXml);
      }  
}
