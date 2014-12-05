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
package edu.harvard.hul.ois.ots.schemas.XmlContent;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.jdom.Document;
import org.jdom.JDOMException;

import edu.harvard.hul.ois.ots.schemas.XmlComponent;
import edu.harvard.hul.ois.ots.schemas.StaxUtils.OTSJDOMFactory;
import edu.harvard.hul.ois.ots.schemas.StaxUtils.StAXBuilder;
import edu.harvard.hul.ois.ots.schemas.StaxUtils.StAXOutputter;

public class AnyXml implements XmlComponent {
	
	private Document dom;
	
	public AnyXml() { }
	
	public AnyXml(XMLStreamReader reader) throws XMLStreamException {
		this();
		parse(reader);
	}
	
	public Document getXml() {
		return dom;
	}

	@Override
	public void output(XMLStreamWriter writer) throws XMLStreamException {
		StAXOutputter staxOut = new StAXOutputter(writer);
		
		//if dom has the default 'anyxml' element as the root node then do not output
		if(!dom.getRootElement().getName().equals("anyxml")) {
			try {
				staxOut.output(dom.getRootElement(),false);	
			} catch (JDOMException e) {
				throw new XMLStreamException("Error outputting JDOM",e);
			}
		}
	}

	@Override
	public AnyXml parse(XMLStreamReader reader) throws XMLStreamException {			
		StAXBuilder staxBuilder = new StAXBuilder();
		staxBuilder.setFactory(new OTSJDOMFactory());
		dom = staxBuilder.build(reader);
		return this;		
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return true;
	}

	/** Returns a List of all namespace schema contexts used within this JDOM object.
	 *  If a default namespace is inherited from the enclosing element, we don't list
	 *  it. */
	@Override
	public List<NamespaceSchemaContext> getAllNamespaceSchemaContexts() {
		List<NamespaceSchemaContext> ctxts = new ArrayList<NamespaceSchemaContext> ();
		//Element topElem = dom.getRootElement();
        //addNamespaceSchemaContexts (ctxts, topElem);
        return ctxts;
	}
	/*
	private void addNamespaceSchemaContexts (List<NamespaceSchemaContext> ctxts, Element elem) {
	    Namespace ns = elem.getNamespace ();
	    String nsPrefix = ns.getPrefix ();
	    String nsURI = ns.getURI ();
	    // We can get the schemaLocation only if it's an attribute in this element. If it isn't,
	    // we assume that the schemaLocation is inherited and thus already listed, and we
	    // don't bother with it.
	    String loc = elem.getAttributeValue("schemaLocation", ns);
	    if (loc != null) {
	        NamespaceSchemaContext ctx = 
	            new NamespaceSchemaContext(nsURI, nsPrefix, loc);
	        boolean dup = false;
	        for (NamespaceSchemaContext ctx1 : ctxts) {
	            if (ctx1.equals (ctx)) {
	                dup = true;
	                break;
	            }
	        }
	        if (!dup) {
	            ctxts.add (ctx);
	        }
	    }
	    // Now recurse down
        List children = elem.getChildren();
	    Iterator childIter = children.iterator ();
	    while (childIter.hasNext ()) {
	        Element child = (Element) childIter.next ();
	        addNamespaceSchemaContexts (ctxts, child);
	    }
	}
*/
    /**
     * return the namespace and schema context for this xml
     * @return
     */
	@Override
	public NamespaceSchemaContext getNamespaceSchemaContext() {
        return null;
        /*
		Element topElem = dom.getRootElement();
        org.jdom.Namespace ns = topElem.getNamespace ();
        String nsPrefix = ns.getPrefix ();
        String nsURI = ns.getURI ();
        // In this case we return the best information we have.
        String loc = topElem.getAttributeValue("schemaLocation", ns);
        return new NamespaceSchemaContext (nsURI, nsPrefix, loc);*/
	}

	@Override
	public void setRoot(boolean root) {
		//do nothing
	}

}
