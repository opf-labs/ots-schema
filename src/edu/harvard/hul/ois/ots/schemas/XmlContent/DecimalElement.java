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

import org.codehaus.staxmate.out.SMNamespace;
import org.codehaus.staxmate.out.SMOutputElement;


/** This class modifies RealElement by allowing output only of the form
 *  xxx.yyy. Or at least it does that in theory. */
public class DecimalElement extends RealElement {

    /** Constructor from XML with name.*/
    public DecimalElement (XMLStreamReader reader)  
           throws XMLStreamException, XmlContentException {
        super (reader);
        textBuf = new StringBuffer ();
    }

    /** Constructor from XML with name.*/
    public DecimalElement (XMLStreamReader reader, String name)  
           throws XMLStreamException, XmlContentException {
        super (reader, name);
        textBuf = new StringBuffer ();
        doubleValue = null;
        this.name = name;
    }

    /** Constructor from an double and a name. */
    public DecimalElement (double n, String name) {
        super (n, name);
        this.name = name;
        doubleValue = n;
    }
    
//    public double getValue () {
//        return doubleValue;
//    }
    
    /** Output method which adds to a parent element.  */
    @Override
    public void output(SMNamespace ns, SMOutputElement parent) throws XMLStreamException {
// TODO use formatting classes
        outputElement (ns, name, parent, doubleValue.toString ());
    }


    
    

}
