/**********************************************************************
 * Copyright (c) 2011 by the President and Fellows of Harvard College
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or (at
 * your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 * USA.
 *
 * Contact information
 *
 * Office for Information Systems
 * Harvard University Library
 * Harvard University
 * Cambridge, MA  02138
 * (617)495-3724
 * hulois@hulmail.harvard.edu
 **********************************************************************/

package edu.harvard.hul.ois.ots.schemas.XmlContent;

import javax.xml.stream.XMLStreamException;

import org.codehaus.staxmate.out.SMNamespace;
import org.codehaus.staxmate.out.SMOutputElement;

import javax.xml.stream.XMLStreamReader;


import static javax.xml.stream.XMLStreamConstants.*;

/** IntegerElement is a generic element for anything that
 * has a long integer value. GenericElement.getField returns a 
 * Long object when a LongElement is stored. */
public class LongElement extends GenericElement {

    private Long longValue;
    
    /**  Constructor from XML
     */
    public LongElement (XMLStreamReader reader) 
             throws XMLStreamException, XmlContentException {
        textBuf = new StringBuffer ();
        longValue = null;
        parse (reader);
    }
    
    /** Constructor from XML with name. This may be the better way to do it.*/
    public LongElement (XMLStreamReader reader, String name)  
           throws XMLStreamException, XmlContentException {
        this (reader);
        this.name = name;
    }

        
    /** Constructor from a long and a name.  */
    public LongElement (long n, String name) {
        this.name = name;
        longValue = n;
    }
    
    /** Output method which adds to a parent element.  */
    @Override
    public void output(SMNamespace ns, SMOutputElement parent) throws XMLStreamException {
         outputElement (ns, name, parent, longValue.toString ());
    }
    
    /** Returns the value as a Long; generic. */
    @Override
    public Object toValue () {
        return longValue;
    }

    /** Returns the value as an Integer; type-specific. */
    public Long toLong () {
        return longValue;
    }


    /** Check the content against an array of permitted values and
     *  throw an exception if none match. The argument must be an
     *  array of int. */
    public void checkContent (Object val) throws XmlContentException {
        long[] values = (long[]) val;
        for (int i = 0; i < values.length; i++) {
            if (values[i] == longValue) {
                return;
            }
        }
        throw new XmlContentException
            ("Value '" + longValue + "' does not match permitted values");
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
                        longValue = Long.parseLong(textBuf.toString ());
                    }
                    catch (Exception e) {
                        throw new XmlContentException 
                           ("Not a long integer value: " + textBuf.toString ());
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
