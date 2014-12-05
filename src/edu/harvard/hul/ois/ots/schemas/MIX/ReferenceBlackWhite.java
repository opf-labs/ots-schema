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

import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

public class ReferenceBlackWhite extends MixElement {

    public final static String ELEM_COMPONENT = "Component";
    
    /**  Constructor from XML
     */
    public ReferenceBlackWhite (XMLStreamReader reader) 
             throws XMLStreamException, XmlContentException {
        name = "ReferenceBlackWhite";
        parse(reader);
    }
    
    /** No-argument constructor */
    public ReferenceBlackWhite () {
        super();
        name = "ReferenceBlackWhite";
    }


    /** Returns a List of Component objects. */
    @SuppressWarnings("unchecked")
	public List<Component> getComponents () {
        return (List<Component>) genericList(getList (ELEM_COMPONENT));
    }
    
    /** Adds a Component object.  There can be any number of these 
     * @throws XmlContentException */
    public void addComponent (Component comp) throws XmlContentException {
        addToList (comp, ELEM_COMPONENT);
    }
    
    /** Output method which adds to a parent element.*/
    @Override
    public void output(SMNamespace ns, SMOutputElement parent) throws XMLStreamException {
        SMOutputElement mainElem = parent.addElement(ns, name);
        outputList(ns, ELEM_COMPONENT, mainElem);
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
        // Component is a repeatable element, so the Component value will
        // be a ListElement. matchList makes that happen.
        if (matchList (reader, this,
                localName, ELEM_COMPONENT,
                Component.class))
            return;
        throw new XmlContentException ("Unknown element name " + localName);
    }

}
