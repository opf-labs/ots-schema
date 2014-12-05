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
package edu.harvard.hul.ois.ots.schemas.MIX;

import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.codehaus.staxmate.out.SMNamespace;
import org.codehaus.staxmate.out.SMOutputElement;

import edu.harvard.hul.ois.ots.schemas.XmlContent.GenericElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.StringElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

public class SourceInformation extends MixElement {

    private final static String ELEM_TYPE = "sourceType";
    private final static String ELEM_ID = "SourceID";
    private final static String ELEM_SIZE = "SourceSize";

    /**  Constructor from XML
     */
    public SourceInformation (XMLStreamReader reader) 
             throws XMLStreamException, XmlContentException {
        name = "SourceInformation";
        parse(reader);
    }
    
    /** No-argument constructor */
    public SourceInformation () {
        super();
        name = "SourceInformation";
    }

    public StringElement getSourceType () {
        return (StringElement) getField (ELEM_TYPE);
    }
    
    public void setSourceType (String t) throws XmlContentException {
        setField (ELEM_TYPE, t);
    }
    
    @SuppressWarnings("unchecked")
	public List<SourceID> getSourceIDs () {
        return (List<SourceID>) genericList( getList (ELEM_ID));
    }
    
    /** Add a sourceID. There can be any number of these. */
    public void addSourceID (SourceID id) {
        List<GenericElement> lst = getList (ELEM_ID);
        lst.add (id);
    }

    public SourceSize getSourceSize () {
        return (SourceSize) getField (ELEM_SIZE);
    }
    
    public void setSourceSize (SourceSize size) throws XmlContentException {
        setField (ELEM_SIZE, size);
    }
    
    @Override
    public void output(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
        SMOutputElement mainElem = parent.addElement(ns, name);
        outputGenericElement (ns, ELEM_TYPE, mainElem);
        outputList (ns, ELEM_ID, mainElem);
        outputGenericElement (ns, ELEM_SIZE, mainElem);
    }

    @Override
    public boolean validate() {
        // TODO Auto-generated method stub
        return false;
    }

    /** Classes must implement this to handle START_ELEMENT cases.  */
    @Override
    protected void dispatch (String localName, XMLStreamReader reader) 
               throws XMLStreamException, XmlContentException {
        if (match (reader, this, 
                localName, ELEM_TYPE,
                StringElement.class)) {
            return;
        }
        if (matchList (reader, this, 
                localName, ELEM_ID,
                SourceID.class)) {
            return;
        }
        if (match (reader, this, 
                localName, ELEM_SIZE,
                StringElement.class)) {
            return;
        }
        throw new XmlContentException ("Unknown element name " + localName);
    }

}
