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

import static javax.xml.stream.XMLStreamConstants.*;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.codehaus.staxmate.out.SMNamespace;
import org.codehaus.staxmate.out.SMOutputElement;


public class RealElement extends GenericElement {

    protected Double doubleValue;

    /** Constructor from XML with name.*/
    public RealElement (XMLStreamReader reader)  
           throws XMLStreamException, XmlContentException {
        textBuf = new StringBuffer ();
        doubleValue = null;
        parse (reader);
    }
    
    /** Constructor from XML with name.*/
    public RealElement (XMLStreamReader reader, String name)  
           throws XMLStreamException, XmlContentException {
        textBuf = new StringBuffer ();
        doubleValue = null;
        this.name = name;
    }

    /** Constructor from an double and a name. */
    public RealElement (double n, String name) {
        this.name = name;
        doubleValue = n;
    }
    
    /** Output method which adds to a parent element.  */
    @Override
    public void output(SMNamespace ns, SMOutputElement parent) throws XMLStreamException {
         outputElement (ns, name, parent, doubleValue.toString ());
    }

    /** Returns the value as a Double; generic. */
    @Override
    public Object toValue () {
        return doubleValue;
    }

    /** Returns the value as a Double; type-specific. */
    public Double toDouble () {
        return doubleValue;
    }

    /** Check the content against an array of permitted values and
     *  throw an exception if none match. */
    public void checkContent (double[] values) throws XmlContentException {
        for (int i = 0; i < values.length; i++) {
            if (values[i] == doubleValue) {
                return;
            }
        }
        throw new XmlContentException("Value '" + doubleValue + "' does not match permitted values");
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
            case START_ELEMENT:
                String localName = reader.getLocalName();
                dispatch (localName, reader);
                break;
            case CHARACTERS:
                textBuf.append (reader.getText ());
                break;
            case END_ELEMENT:
                // Caution: This works only if any recursive references
                // to the name are eaten by dispatch. Shouldn't be a problem in MIX.
                if(reader.getLocalName().equals(topName)  &&
                nsURI.equals(reader.getNamespaceURI())) {
                    try {
                        doubleValue = Double.parseDouble(textBuf.toString ());
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
