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

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
//import javax.xml.stream.XMLStreamWriter;

import org.codehaus.staxmate.out.SMNamespace;
import org.codehaus.staxmate.out.SMOutputElement;

import static javax.xml.stream.XMLStreamConstants.*;

/** IntegerElement is a generic element for anything that
 * has an integer value. GenericElement.getField returns an 
 * Integer object when an IntegerElement is stored. */
public class IntegerElement extends GenericElement {

    private Integer intValue;
    
    /**  Constructor from XML
     */
    public IntegerElement (XMLStreamReader reader) 
             throws XMLStreamException, XmlContentException {
        textBuf = new StringBuffer ();
        intValue = null;
        parse (reader);
    }
    
    /** Constructor from XML with name. This may be the better way to do it.*/
    public IntegerElement (XMLStreamReader reader, String name)  
           throws XMLStreamException, XmlContentException {
        this (reader);
        this.name = name;
    }

        
    /** Constructor from an int and a name.  */
    public IntegerElement (int n, String name) {
        this.name = name;
        intValue = n;
    }
    
    /** Output method which adds to a parent element.  */
    @Override
    public void output(SMNamespace ns, SMOutputElement parent) throws XMLStreamException {
         outputElement (ns, name, parent, intValue.toString ());
    }
    
    /** Returns the value as an Integer; generic. */
    @Override
    public Object toValue () {
        return intValue;
    }

    /** Returns the value as an Integer; type-specific. */
    public Integer toInteger () {
        return intValue;
    }


    /** Check the content against an array of permitted values and
     *  throw an exception if none match. The argument must be an
     *  array of int. */
    public void checkContent (Object val) throws XmlContentException {
        int[] values = (int[]) val;
        for (int i = 0; i < values.length; i++) {
            if (values[i] == intValue) {
                return;
            }
        }
        throw new XmlContentException("Value '" + intValue + "' does not match permitted values");
    }


    /** unmarshall function which extracts the text of the element
     *  for later use as an integer */
    public XmlContent parse(XMLStreamReader reader)
    throws XMLStreamException, XmlContentException {
        String topName = reader.getLocalName ();
        String nsURI = reader.getNamespaceURI();
        addAttributes (reader);
        for (int event=reader.next(); event!=END_DOCUMENT; event=reader.next()) {
            switch(event) {
            case CHARACTERS:
                textBuf.append (reader.getText ());
                break;
            case END_ELEMENT:
                // Caution: This works only if any recursive references
                // to the name are eaten by dispatch.
                if(reader.getLocalName().equals(topName)  &&
                nsURI.equals(reader.getNamespaceURI())) {
                    try {
                        intValue = Integer.parseInt(textBuf.toString ());
                    }
                    catch (Exception e) {
                        throw new XmlContentException 
                           ("Not an integer value: " + textBuf.toString ());
                    }
                    return this;
                }
            }
        }
        return null;
    }

    @Override
    public boolean validate() {
        // TODO Auto-generated method stub
        return false;
    }

}
