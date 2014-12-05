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
package edu.harvard.hul.ois.ots.schemas.premis;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.custommonkey.xmlunit.XMLTestCase;
import org.custommonkey.xmlunit.XMLUnit;

import edu.harvard.hul.ois.ots.schemas.premis.Premis;
import edu.harvard.hul.ois.ots.schemas.premis.agent.PremisAgent;

public class PremisAgentTest extends XMLTestCase{
	private String premisAgentRoot =		
		"<premis:agent xmlns:premis=\"info:lc/xmlns/premis-v2\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"info:lc/xmlns/premis-v2 http://www.loc.gov/standards/premis/premis.xsd\" version=\"2.0\">" +
		"<premis:agentIdentifier xlink:role=\"idk\">" +
			"<premis:agentIdentifierType>id</premis:agentIdentifierType>" +
			"<premis:agentIdentifierValue>123</premis:agentIdentifierValue>" +
		"</premis:agentIdentifier>" +
		"<premis:agentIdentifier xlink:title=\"the title\">" +
			"<premis:agentIdentifierType>another id</premis:agentIdentifierType>" +
			"<premis:agentIdentifierValue>456</premis:agentIdentifierValue>" +
		"</premis:agentIdentifier>" +
		"<premis:agentName>first</premis:agentName>" +
		"<premis:agentName>last</premis:agentName>" +
		"<premis:agentType>programmer</premis:agentType>" +
		"</premis:agent>";
	
	private String premisRoot = 		 
		"<premis:premis xmlns:premis=\"info:lc/xmlns/premis-v2\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"info:lc/xmlns/premis-v2 http://www.loc.gov/standards/premis/premis.xsd\" version=\"2.0\">" +
		"<premis:object xsi:type=\"representation\">" +
		"<premis:objectIdentifier>" +
			"<premis:objectIdentifierType></premis:objectIdentifierType>" +
			"<premis:objectIdentifierValue></premis:objectIdentifierValue>" +
		"</premis:objectIdentifier>" +
		"</premis:object>" +
		"<premis:agent>" +
		"<premis:agentIdentifier xlink:role=\"idk\">" +
			"<premis:agentIdentifierType>id</premis:agentIdentifierType>" +
			"<premis:agentIdentifierValue>123</premis:agentIdentifierValue>" +
		"</premis:agentIdentifier>" +
		"<premis:agentIdentifier xlink:title=\"the title\">" +
			"<premis:agentIdentifierType>another id</premis:agentIdentifierType>" +
			"<premis:agentIdentifierValue>456</premis:agentIdentifierValue>" +
		"</premis:agentIdentifier>" +
		"<premis:agentName>first</premis:agentName>" +
		"<premis:agentName>last</premis:agentName>" +
		"<premis:agentType>programmer</premis:agentType>" +
		"</premis:agent>" +
		"</premis:premis>";
	
    public void testRead () throws Exception {
        // set up a parser
        XMLInputFactory xmlif = XMLInputFactory.newInstance();
        XMLStreamReader xmlr = xmlif.createXMLStreamReader(new StringReader(premisAgentRoot));
        PremisAgent premisAgent = new PremisAgent(xmlr,true);
        xmlr.close();
 
        String value = premisAgent.getAgentIdentifiers().get(0).getValue();
        assertNotNull(value);
        
        xmlr = xmlif.createXMLStreamReader(new StringReader(premisRoot));
        Premis premis = new Premis(xmlr,true);
        xmlr.close();
        PremisAgent agent = premis.getAgents().get(0);
        String agentVal = agent.getAgentIdentifiers().get(0).getValue();
        assertNotNull(agentVal);
    }
    
    public void testReadWrite () throws Exception {
        // set up a parser
        XMLInputFactory xmlif = XMLInputFactory.newInstance();
        XMLStreamReader xmlr = xmlif.createXMLStreamReader(new StringReader(premisAgentRoot));
        PremisAgent agent = new PremisAgent(xmlr,true); 
        xmlr.close();
        
		XMLOutputFactory f = XMLOutputFactory.newInstance();
		StringWriter stringWriter = new StringWriter();	
		XMLStreamWriter sw = f.createXMLStreamWriter(stringWriter); 
		agent.output(sw);
		sw.close();
	    String newXml = stringWriter.toString();
	    stringWriter.close();
	           
	    XMLUnit.setIgnoreWhitespace(true);
        assertXMLEqual(premisAgentRoot,newXml);
    }  
}
