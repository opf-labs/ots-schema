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
package edu.harvard.hul.ois.ots.schemas.DocumentMD;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.junit.Test;
import org.custommonkey.xmlunit.*;

import edu.harvard.hul.ois.ots.schemas.DocumentMD.DocumentMD;

//import edu.harvard.hul.ois.ots.DocumentMD.DocumentMD;

public class DocumentMDInputOutputTest extends XMLTestCase {
	
    
	@Test
	public void testEquality() throws Exception {	
		// set up a parser
		XMLInputFactory xmlif = XMLInputFactory.newInstance();
		XMLStreamReader xmlr = xmlif.createXMLStreamReader(new FileReader("test_xml/documentmd.xml"));
		String originalFile = readFileAsString("test_xml/documentmd.xml");
		//move to root tag
		xmlr.nextTag();
		DocumentMD doc = new DocumentMD(xmlr,true);			
		OutputStream out = new ByteArrayOutputStream();
		XMLOutputFactory f = XMLOutputFactory.newInstance();	
		XMLStreamWriter sw = f.createXMLStreamWriter(out);
		doc.output(sw);

		String newXml = out.toString();
				     
	    XMLUnit.setIgnoreWhitespace(true);
        assertXMLEqual(originalFile,newXml);
	}
	
    private static String readFileAsString(String filePath) throws java.io.IOException{
        StringBuffer fileData = new StringBuffer(1000);
        BufferedReader reader = new BufferedReader(
                new FileReader(filePath));
        char[] buf = new char[1024];
        int numRead=0;
        while((numRead=reader.read(buf)) != -1){
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
            buf = new char[1024];
        }
        reader.close();
        return fileData.toString();
    }

}
