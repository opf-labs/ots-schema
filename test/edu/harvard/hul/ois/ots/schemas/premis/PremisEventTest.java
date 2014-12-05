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
import edu.harvard.hul.ois.ots.schemas.premis.event.PremisEvent;

public class PremisEventTest extends XMLTestCase{
	private String premisEventRoot =		
	"<premis:event xmlns:premis=\"info:lc/xmlns/premis-v2\" " + 
	 "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" " + 
	 "xsi:schemaLocation=\"info:lc/xmlns/premis-v2 http://www.loc.gov/standards/premis/premis.xsd\">" +
			"<premis:eventIdentifier xlink:role=\"blah\">" +
				"<premis:eventIdentifierType>DRS event</premis:eventIdentifierType>" +
				"<premis:eventIdentifierValue>345</premis:eventIdentifierValue>" +
			"</premis:eventIdentifier>" +
			"<premis:eventType>object merge</premis:eventType>" +
			"<premis:eventDateTime>2006-05-04T18:13:51.0Z</premis:eventDateTime>" +
			"<premis:eventDetail/>" +
			"<premis:eventOutcomeInformation>" +
				"<premis:eventOutcome/>" +
				"<premis:eventOutcomeDetail>" +
					"<premis:eventOutcomeDetailNote/>" +
					"<premis:eventOutcomeDetailExtension>" +
						"<blahXml/>" +
					"</premis:eventOutcomeDetailExtension>" +
				"</premis:eventOutcomeDetail>" +
			"</premis:eventOutcomeInformation>" +
			"<premis:linkingAgentIdentifier>" +
				"<premis:linkingAgentIdentifierType>HUL DRS URN</premis:linkingAgentIdentifierType>" +
				"<premis:linkingAgentIdentifierValue>MergingAgentID</premis:linkingAgentIdentifierValue>" +
				"<premis:linkingAgentRole>who?</premis:linkingAgentRole>" +
				"<premis:linkingAgentRole>me</premis:linkingAgentRole>" +
			"</premis:linkingAgentIdentifier>" +
			"<premis:linkingObjectIdentifier>" +
				"<premis:linkingObjectIdentifierType>HUL DRS URN</premis:linkingObjectIdentifierType>" +
				"<premis:linkingObjectIdentifierValue>MergedFromObjectID</premis:linkingObjectIdentifierValue>" +
			"</premis:linkingObjectIdentifier>" +
			"<premis:linkingObjectIdentifier>" +
				"<premis:linkingObjectIdentifierType>HUL DRS URN</premis:linkingObjectIdentifierType>" +
				"<premis:linkingObjectIdentifierValue>MergedIntoObjectID</premis:linkingObjectIdentifierValue>" +
			"</premis:linkingObjectIdentifier>" +
		"</premis:event>";
	
	private String premisRoot = 		 
		"<premis:premis xmlns:premis=\"info:lc/xmlns/premis-v2\" " + 
		 "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " + 
		 "xsi:schemaLocation=\"info:lc/xmlns/premis-v2 http://www.loc.gov/standards/premis/premis.xsd\">" +
		"<premis:object xsi:type=\"representation\">" +
			"<premis:objectIdentifier>" +
				"<premis:objectIdentifierType></premis:objectIdentifierType>" +
				"<premis:objectIdentifierValue></premis:objectIdentifierValue>" +
			"</premis:objectIdentifier>" +
		"</premis:object>" +
		 "<premis:event>" +
			"<premis:eventIdentifier>" +
				"<premis:eventIdentifierType>DRS event</premis:eventIdentifierType>" +
				"<premis:eventIdentifierValue>345</premis:eventIdentifierValue>" +
			"</premis:eventIdentifier>" +
			"<premis:eventType>object merge</premis:eventType>" +
			"<premis:eventDateTime>2006-05-04T18:13:51.0Z</premis:eventDateTime>" +
			"<premis:eventDetail/>" +
			"<premis:eventOutcomeInformation>" +
				"<premis:eventOutcome/>" +
				"<premis:eventOutcomeDetail>" +
					"<premis:eventOutcomeDetailNote/>" +
					"<premis:eventOutcomeDetailExtension>" +
						"<blahXml/>" +
					"</premis:eventOutcomeDetailExtension>" +
				"</premis:eventOutcomeDetail>" +
			"</premis:eventOutcomeInformation>" +
			"<premis:linkingAgentIdentifier>" +
				"<premis:linkingAgentIdentifierType>HUL DRS URN</premis:linkingAgentIdentifierType>" +
				"<premis:linkingAgentIdentifierValue>MergingAgentID</premis:linkingAgentIdentifierValue>" +
				"<premis:linkingAgentRole>who?</premis:linkingAgentRole>" +
				"<premis:linkingAgentRole>me</premis:linkingAgentRole>" +
			"</premis:linkingAgentIdentifier>" +
			"<premis:linkingObjectIdentifier>" +
				"<premis:linkingObjectIdentifierType>HUL DRS URN</premis:linkingObjectIdentifierType>" +
				"<premis:linkingObjectIdentifierValue>MergedFromObjectID</premis:linkingObjectIdentifierValue>" +
			"</premis:linkingObjectIdentifier>" +
			"<premis:linkingObjectIdentifier>" +
				"<premis:linkingObjectIdentifierType>HUL DRS URN</premis:linkingObjectIdentifierType>" +
				"<premis:linkingObjectIdentifierValue>MergedIntoObjectID</premis:linkingObjectIdentifierValue>" +
			"</premis:linkingObjectIdentifier>" +
		"</premis:event>" +
		"</premis:premis>";
	
    public void testRead () throws Exception {
        // set up a parser
        XMLInputFactory xmlif = XMLInputFactory.newInstance();
        XMLStreamReader xmlr = xmlif.createXMLStreamReader(new StringReader(premisEventRoot));
        PremisEvent premisEvent = new PremisEvent(xmlr,true);
        xmlr.close();
 
        String value = premisEvent.getEventIdentifierValue();
        assertNotNull(value);
        
        xmlr = xmlif.createXMLStreamReader(new StringReader(premisRoot));
        Premis premis = new Premis(xmlr,true);
        xmlr.close();
        PremisEvent event = premis.getEvents().get(0);
        String eventVal = event.getEventIdentifierValue();
        assertNotNull(eventVal);
        
    }
    
    public void testReadWrite () throws Exception {
        // set up a parser
        XMLInputFactory xmlif = XMLInputFactory.newInstance();
        XMLStreamReader xmlr = xmlif.createXMLStreamReader(new StringReader(premisEventRoot));
        PremisEvent event = new PremisEvent(xmlr,true);  
        xmlr.close();
        
		XMLOutputFactory f = XMLOutputFactory.newInstance();
		StringWriter stringWriter = new StringWriter();
		XMLStreamWriter sw = f.createXMLStreamWriter(stringWriter); 
		event.output(sw);
		sw.close();
	    String newXml = stringWriter.toString();
	    stringWriter.close();
	           
	    XMLUnit.setIgnoreWhitespace(true);
        assertXMLEqual(premisEventRoot,newXml);
    }  
}
