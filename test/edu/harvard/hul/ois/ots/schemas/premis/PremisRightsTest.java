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
import edu.harvard.hul.ois.ots.schemas.premis.rights.PremisRights;

public class PremisRightsTest extends XMLTestCase{
	private String premisRightsRoot =		
		"<rights xmlns=\"info:lc/xmlns/premis-v2\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"info:lc/xmlns/premis-v2 http://www.loc.gov/standards/premis/premis.xsd\" version=\"2.0\">" +
		"<rightsStatement>" +
			"<rightsStatementIdentifier xlink:role=\"blah\">" +
				"<rightsStatementIdentifierType>type</rightsStatementIdentifierType>" +
				"<rightsStatementIdentifierValue>value</rightsStatementIdentifierValue>" +
			"</rightsStatementIdentifier>" +
			"<rightsBasis>basis</rightsBasis>" +			
			"<copyrightInformation>" +
				"<copyrightStatus>status</copyrightStatus>" +
				"<copyrightJurisdiction>jurisdiction</copyrightJurisdiction>" +
				"<copyrightStatusDeterminationDate>2006-05-04T18:13:51.0Z</copyrightStatusDeterminationDate>" +
				"<copyrightNote>note A</copyrightNote>" +
				"<copyrightNote>note B</copyrightNote>" +
			"</copyrightInformation>" +
			"<licenseInformation>" +
				"<licenseIdentifier>" +
					"<licenseIdentifierType>type</licenseIdentifierType>" +
					"<licenseIdentifierValue>value</licenseIdentifierValue>" +
				"</licenseIdentifier>" +
				"<licenseTerms>terms</licenseTerms>" +
				"<licenseNote>note A</licenseNote>" +
				"<licenseNote>note B</licenseNote>" +
			"</licenseInformation>" +
			"<statuteInformation>" +
				"<statuteJurisdiction>jurisdiction</statuteJurisdiction>" +
				"<statuteCitation>citation</statuteCitation>" +
				"<statuteInformationDeterminationDate>2006-05-04T18:13:51.0Z</statuteInformationDeterminationDate>" +
				"<statuteNote>note A</statuteNote>" +
				"<statuteNote>note B</statuteNote>" +
			"</statuteInformation>" +
			"<statuteInformation>" +
				"<statuteJurisdiction>another jurisdiction</statuteJurisdiction>" +
				"<statuteCitation>another citation</statuteCitation>" +
			"</statuteInformation>" +
			"<rightsGranted>" +
				"<act>act</act>" +
				"<restriction>idk</restriction>" +
				"<restriction>idk</restriction>" +
				"<termOfGrant>" +
					"<startDate>2006-05-04T18:13:51.0Z</startDate>" +
					"<endDate>2006-05-04T18:13:51.0Z</endDate>" +
				"</termOfGrant>" +
				"<rightsGrantedNote>note A</rightsGrantedNote>" +
				"<rightsGrantedNote>note B</rightsGrantedNote>" +
			"</rightsGranted>" +
			"<rightsGranted>" +
				"<act>idk</act>" +
				"<termOfGrant>" +
					"<startDate>2006-05-04T18:13:51.0Z</startDate>" +
				"</termOfGrant>" +
			"</rightsGranted>" +		
			"<linkingObjectIdentifier>" +
				"<linkingObjectIdentifierType>type</linkingObjectIdentifierType>" +
				"<linkingObjectIdentifierValue>value</linkingObjectIdentifierValue>" +
			"</linkingObjectIdentifier>" +
			"<linkingAgentIdentifier>" +
				"<linkingAgentIdentifierType>type</linkingAgentIdentifierType>" +
				"<linkingAgentIdentifierValue>value</linkingAgentIdentifierValue>" +
			"</linkingAgentIdentifier>" +
		"</rightsStatement>" +
		"<rightsExtension>" +
			"<moreXml><andsomeMore></andsomeMore></moreXml>" +
		"</rightsExtension>" +
	"</rights>";

	
	private String premisRoot = 		 
		"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
		"<premis xmlns=\"info:lc/xmlns/premis-v2\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"info:lc/xmlns/premis-v2 http://www.loc.gov/standards/premis/premis.xsd\" version=\"2.0\">" +
			"<object xsi:type=\"representation\">" +
				"<objectIdentifier>" +
					"<objectIdentifierType>type</objectIdentifierType>" +
					"<objectIdentifierValue>value</objectIdentifierValue>" +
				"</objectIdentifier>" +
			"</object>" +
			"<rights version=\"2.0\">" +
				"<rightsStatement>" +
					"<rightsStatementIdentifier xlink:role=\"blah\">" +
						"<rightsStatementIdentifierType>type</rightsStatementIdentifierType>" +
						"<rightsStatementIdentifierValue>value</rightsStatementIdentifierValue>" +
					"</rightsStatementIdentifier>" +
					"<rightsBasis>basis</rightsBasis>" +			
					"<copyrightInformation>" +
						"<copyrightStatus>status</copyrightStatus>" +
						"<copyrightJurisdiction>jurisdiction</copyrightJurisdiction>" +
						"<copyrightStatusDeterminationDate>2006-05-04T18:13:51.0Z</copyrightStatusDeterminationDate>" +
						"<copyrightNote>note A</copyrightNote>" +
						"<copyrightNote>note B</copyrightNote>" +
					"</copyrightInformation>" +
					"<licenseInformation>" +
						"<licenseIdentifier>" +
							"<licenseIdentifierType>type</licenseIdentifierType>" +
							"<licenseIdentifierValue>value</licenseIdentifierValue>" +
						"</licenseIdentifier>" +
						"<licenseTerms>terms</licenseTerms>" +
						"<licenseNote>note A</licenseNote>" +
						"<licenseNote>note B</licenseNote>" +
					"</licenseInformation>" +
					"<statuteInformation>" +
						"<statuteJurisdiction>jurisdiction</statuteJurisdiction>" +
						"<statuteCitation>citation</statuteCitation>" +
						"<statuteInformationDeterminationDate>2006-05-04T18:13:51.0Z</statuteInformationDeterminationDate>" +
						"<statuteNote>note A</statuteNote>" +
						"<statuteNote>note B</statuteNote>" +
					"</statuteInformation>" +
					"<statuteInformation>" +
						"<statuteJurisdiction>another jurisdiction</statuteJurisdiction>" +
						"<statuteCitation>another citation</statuteCitation>" +
					"</statuteInformation>" +
					"<rightsGranted>" +
						"<act>act</act>" +
						"<restriction>idk</restriction>" +
						"<restriction>idk</restriction>" +
						"<termOfGrant>" +
							"<startDate>2006-05-04T18:13:51.0Z</startDate>" +
							"<endDate>2006-05-04T18:13:51.0Z</endDate>" +
						"</termOfGrant>" +
						"<rightsGrantedNote>note A</rightsGrantedNote>" +
						"<rightsGrantedNote>note B</rightsGrantedNote>" +
					"</rightsGranted>" +
					"<rightsGranted>" +
						"<act>idk</act>" +
						"<termOfGrant>" +
							"<startDate>2006-05-04T18:13:51.0Z</startDate>" +
						"</termOfGrant>" +
					"</rightsGranted>" +		
					"<linkingObjectIdentifier>" +
						"<linkingObjectIdentifierType>type</linkingObjectIdentifierType>" +
						"<linkingObjectIdentifierValue>value</linkingObjectIdentifierValue>" +
					"</linkingObjectIdentifier>" +
					"<linkingAgentIdentifier>" +
						"<linkingAgentIdentifierType>type</linkingAgentIdentifierType>" +
						"<linkingAgentIdentifierValue>value</linkingAgentIdentifierValue>" +
					"</linkingAgentIdentifier>" +
				"</rightsStatement>" +
				"<rightsExtension>" +
					"<moreXml><andsomeMore></andsomeMore></moreXml>" +
				"</rightsExtension>" +
			"</rights>" +
		"</premis>";

	
    public void testRead () throws Exception {
        // set up a parser
        XMLInputFactory xmlif = XMLInputFactory.newInstance();
        XMLStreamReader xmlr = xmlif.createXMLStreamReader(new StringReader(premisRightsRoot));
        PremisRights premisRights = new PremisRights(xmlr,true);
        xmlr.close();
 
        String value = premisRights.getRightsStatements().get(0).getCopyrightStatusDeterminationDate().toString();
        assertNotNull(value);
        
        xmlr = xmlif.createXMLStreamReader(new StringReader(premisRoot));
        Premis premis = new Premis(xmlr,true);
        xmlr.close();
        PremisRights rights = premis.getRights().get(0);
        String rightsVal = rights.getRightsStatements().get(0).getBasis();
        assertNotNull(rightsVal);
    }
    
    public void testReadWrite () throws Exception {
        // set up a parser
        XMLInputFactory xmlif = XMLInputFactory.newInstance();
        XMLStreamReader xmlr = xmlif.createXMLStreamReader(new StringReader(premisRightsRoot));
        PremisRights premisRights = new PremisRights(xmlr,true);
        xmlr.close();
        
		XMLOutputFactory f = XMLOutputFactory.newInstance();
		StringWriter stringWriter = new StringWriter();	
		XMLStreamWriter sw = f.createXMLStreamWriter(stringWriter); 
		premisRights.output(sw);
		sw.close();
	    String newXml = stringWriter.toString();
	    stringWriter.close();
	           
	    XMLUnit.setIgnoreWhitespace(true);
        assertXMLEqual(premisRightsRoot,newXml);
    }  
}
