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

package edu.harvard.hul.ois.ots.schemas.TextMD;

import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.custommonkey.xmlunit.*;

import edu.harvard.hul.ois.ots.schemas.TextMD.Encoding;
import edu.harvard.hul.ois.ots.schemas.TextMD.EncodingSoftware;
import edu.harvard.hul.ois.ots.schemas.TextMD.TextMD;


public class TextMDTest  extends XMLTestCase {

	 private String xmlSample =
			"<textMD xmlns=\"info:lc/xmlns/textMD-v3\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"info:lc/xmlns/textMD-v3 http://www.loc.gov/standards/textMD/textMD-v3.01a.xsd\">" +
		    "	<encoding QUALITY=\"blah\">" +
		    "		<encoding_platform linebreak=\"CR\">blah</encoding_platform>" +
		    "		<encoding_platform linebreak=\"CR\">blah2</encoding_platform>" +
		    "		<encoding_software version=\"blahVersion\">blah</encoding_software>" +
		    "		<encoding_software version=\"blah\">blah2</encoding_software>" +
		    "		<encoding_agent role=\"EDITOR\">sdf</encoding_agent>" +
		    "		<encoding_agent role=\"EDITOR\">me</encoding_agent>" +
		    "	</encoding>" +
		    "	<character_info>" +
		    "		<charset>ANSI_X3.4-1968</charset>" +
		    "		<byte_order>little</byte_order>" +
		    "		<byte_size>123</byte_size>" +
		    "		<character_size encoding=\"123\">asdf</character_size>" +
		    "		<linebreak>CR/LF</linebreak>" +
		    "	</character_info>" +
		    "	<font_script>some font script</font_script>" +
		    "	<pageOrder>right-to-left</pageOrder>" +
		    "	<representationSequence>reading-order</representationSequence>" +
		    "	<lineLayout>left-to-right</lineLayout>" +
		    "	<characterFlow>boustrophedon start-on-left</characterFlow>" +
		    "	<characterFlow>boustrophedon start-on-left</characterFlow>" +
	    	"</textMD>";

    
    public void testRead () throws Exception {
        // set up a parser
        XMLInputFactory xmlif = XMLInputFactory.newInstance();
        XMLStreamReader xmlr = xmlif.createXMLStreamReader(new StringReader(xmlSample));
        TextMD textmd = new TextMD (xmlr,true);  
        Encoding encoding = textmd.getEncodings().get(0);
        assertNotNull (encoding);
        EncodingSoftware encodingSoft = encoding.getEncodingSoftwares().get(0);
        assertNotNull (encodingSoft);
        assertTrue ("blah".equals (encodingSoft.getValue()));
        assertTrue ("blahVersion".equals (encodingSoft.getVersion()));
    }
    
    public void testReadWrite () throws Exception {
        // set up a parser
        XMLInputFactory xmlif = XMLInputFactory.newInstance();
        XMLStreamReader xmlr = xmlif.createXMLStreamReader(new StringReader(xmlSample));
        TextMD textmd = new TextMD (xmlr,true);   
		XMLOutputFactory f = XMLOutputFactory.newInstance();
		StringWriter stringWriter = new StringWriter();
		
		textmd.getNamespaceSchemaContext().setPrefix(null);
		
		XMLStreamWriter sw = f.createXMLStreamWriter(stringWriter); 
		textmd.output(sw);
		sw.close();
	    String newXml = stringWriter.toString();
	    stringWriter.close();
       
	    System.out.println(newXml);
	    
	    XMLUnit.setIgnoreWhitespace(true);
        assertXMLEqual(xmlSample,newXml);
      }  
}
