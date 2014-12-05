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

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.codehaus.staxmate.out.SMNamespace;
import org.codehaus.staxmate.out.SMOutputElement;

import java.util.List;

import edu.harvard.hul.ois.ots.schemas.XmlContent.StringElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

public class TargetData extends MixElement {

    private final static String ELEM_TYPE = "targetType";
    private final static String ELEM_ID = "TargetID";
    private final static String ELEM_EXT = "externalTarget";
    private final static String ELEM_DATA = "performanceData";

    /* Permitted values for targetType */
    private final static String[] VALUES_TYPE = 
    { "external", "internal" };

    /**  Constructor from XML
     */
    public TargetData (XMLStreamReader reader) 
              throws XMLStreamException, XmlContentException {
        name = "TargetData";
        defineRestrictions();
        parse(reader);
    }
    
    /** No-argument constructor */
    public TargetData () {
        super();
        name = "TargetData";
        defineRestrictions();
    }

    /** Define restrictions */
    private void defineRestrictions () {
        addRestriction(ELEM_TYPE, VALUES_TYPE);
    }

    @SuppressWarnings("unchecked")
	public List<StringElement> getTargetTypes () {
        return genericList(getList (ELEM_TYPE));
    }
    
    /** Adds a targetType. There can be an unlimited number. */
    public void addTargetType (String t) throws XmlContentException {
        addToList (new StringElement(t, ELEM_TYPE) , ELEM_TYPE);
    }
    
    @SuppressWarnings("unchecked")
	public List<TargetID> getTargetIDs () {
        return genericList( getList (ELEM_ID));
    }
    
    /** Adds a TargetID. There can be an unlimited number. */
    public void addTargetID (TargetID id) throws XmlContentException {
        addToList (id, ELEM_ID);
    }
    
    @SuppressWarnings("unchecked")
	public List<StringElement> getExternalTargets () {
        return genericList(getList (ELEM_EXT));
    }
    
    /** Adds a externalTarget. There can be an unlimited number. */
    public void addExternalTarget (String t) throws XmlContentException {
        addToList (new StringElement (t, ELEM_EXT), ELEM_EXT);
    }
    
    @SuppressWarnings("unchecked")
	public List<StringElement> getPerformanceData () {
        return genericList(getList (ELEM_EXT));
    }
    
    /** Adds a performanceData (datum?). There can be an unlimited number. */
    public void addPerformanceData (String pd) throws XmlContentException {
        addToList (new StringElement (pd, ELEM_DATA), ELEM_DATA);
    }
    
    



    @Override
    public void output(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
        SMOutputElement mainElem = parent.addElement(ns, name);
        outputList (ns, ELEM_TYPE, mainElem);
        outputList (ns, ELEM_ID, mainElem);
        outputList (ns, ELEM_EXT, mainElem);
        outputList (ns, ELEM_DATA, mainElem);
    }

    @Override
    public boolean validate() {
        // TODO Auto-generated method stub
        return false;
    }

    /** Classes must implement this to handle START_ELEMENT cases.  */
    protected void dispatch (String localName, XMLStreamReader reader) 
               throws XMLStreamException, XmlContentException {
        if (matchList (reader, this, 
                localName, ELEM_TYPE,
                StringElement.class)) {
            return;
        }
        if (matchList (reader, this, 
                localName, ELEM_ID,
                TargetID.class)) {
            return;
        }
        if (matchList (reader, this, 
                localName, ELEM_EXT,
                StringElement.class)) {
            return;
        }
        if (matchList (reader, this, 
                localName, ELEM_DATA,
                StringElement.class)) {
            return;
        }
        throw new XmlContentException ("Unknown element name " + localName);
    }

}
