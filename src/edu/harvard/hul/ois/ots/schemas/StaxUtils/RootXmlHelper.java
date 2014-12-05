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

import java.util.ArrayList;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.codehaus.staxmate.out.SMNamespace;
import org.codehaus.staxmate.out.SMOutputElement;

import edu.harvard.hul.ois.ots.schemas.XmlContent.NamespaceSchemaContext;

public class RootXmlHelper {
	
	private static final String SCHEMA_INSTANCE_PREFIX = "xsi";
	
	public static void output(XMLStreamWriter writer, List<NamespaceSchemaContext> contexts, SMOutputElement root) throws XMLStreamException {
		String schemaLocation = "";
		SMNamespace xsi = root.getNamespace(XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI,SCHEMA_INSTANCE_PREFIX);
		
		List<NamespaceSchemaContext> filteredContexts = new ArrayList<NamespaceSchemaContext>();
		for(NamespaceSchemaContext nsSchemaCntxt : contexts) {
			if(!filteredContexts.contains(nsSchemaCntxt)) {
				filteredContexts.add(nsSchemaCntxt);
			}
		}
		
		NamespaceContext nsContext = writer.getNamespaceContext();
		for(NamespaceSchemaContext nsSchemaCntxt : filteredContexts) {
			String uri = nsSchemaCntxt.getNamespaceURI();
			String prefix = nsSchemaCntxt.getPrefix();
			String schemaLoc = nsSchemaCntxt.getSchemaLocation();
			
			//build xsi:schemaLocation string, only when a schemaLocation is defined
			if(uri != null && (schemaLoc != null && schemaLoc.length() > 0)) {
				String item = uri + " " + schemaLoc;
				schemaLocation = schemaLocation + item + " "; 
			}
			//add namespace to writer if it has not already been declared, and if it is not xmlns:xsi		
				//xmlns:xsi will be added automaticalaly below when adding the schemaLocation attribute
			if(nsContext.getPrefix(uri) == null && !uri.equals(XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI)) {
				writer.writeNamespace(prefix,uri);
			}
		}
		//trim off last trailing space
		schemaLocation = schemaLocation.trim();
		
		//if xsi
		root.addAttribute(xsi,"schemaLocation", schemaLocation);
	}
	
	public static List<NamespaceSchemaContext> parse(XMLStreamReader reader) {
		List<NamespaceSchemaContext> contexts = new ArrayList<NamespaceSchemaContext>();
		String schemaLocation = "";
		
		//get the schema location string
		for(int i=0;i<reader.getAttributeCount();i++) {
			QName qname = reader.getAttributeName(i);
			if(qname.getLocalPart().equals("schemaLocation")) {
				schemaLocation = reader.getAttributeValue(i);
			}
		}		
		String[] schemaLocations = schemaLocation.split(" ");
		
		//for each xml namespace		
		for(int i=0;i<reader.getNamespaceCount();i++) {
			String prefix = reader.getNamespacePrefix(i);
			String uri = reader.getNamespaceURI(i);
			NamespaceSchemaContext context = new NamespaceSchemaContext(uri, prefix,"");
			//check if there is a corresponding schema location
			for(int j=0; j<schemaLocations.length;j=j+2) {
				if(uri.equals(schemaLocations[j])) {
					context.setSchemaLocation(schemaLocations[j+1]);
					break;
				}
			}
			contexts.add(context);
		}
		return contexts;
	}
	

}
