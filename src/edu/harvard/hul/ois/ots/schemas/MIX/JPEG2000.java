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

import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

public class JPEG2000 extends MixElement {

    
    private final static String ELEM_CC = "CodecCompliance";
    private final static String ELEM_EO = "EncodingOptions";

    /**  Constructor from XML
     */
    public JPEG2000 (XMLStreamReader reader) 
             throws XMLStreamException, XmlContentException {
        name = "JPEG2000";
        parse(reader);
    }
    
    /** No-argument constructor */
    public JPEG2000 () {
        super();
        name = "JPEG2000";
    }


    /** Output method which adds to a parent element.*/
    @Override
    public void output(SMNamespace ns, SMOutputElement parent) throws XMLStreamException {
        SMOutputElement mainElem = parent.addElement(ns, name);
        outputGenericElement (ns, ELEM_CC, mainElem);
        outputGenericElement (ns, ELEM_EO, mainElem);
    }

    @Override
    public boolean validate() {
        // TODO Auto-generated method stub
        return false;
    }
    
    /** Returns the CodecCompliance */
    public CodecCompliance getCodecCompliance () {
        return (CodecCompliance) getField (ELEM_CC);
    }
    
    /** Sets the CodecCompliance 
     * @throws XmlContentException */
    public void setCodecCompliance (CodecCompliance cc) throws XmlContentException {
        setField (ELEM_CC, cc);
    }

    /** Returns the EncodingOptions */
    public EncodingOptions getEncodingOptions () {
        return (EncodingOptions) getField (ELEM_EO);
    }
    
    /** Sets the EncodingOptions 
     * @throws XmlContentException */
    public void setEncodingOptions (EncodingOptions cc) throws XmlContentException {
        setField (ELEM_EO, cc);
    }

    /** Classes must implement this to handle START_ELEMENT cases.  */
    protected void dispatch (String localName, XMLStreamReader reader) 
                  throws XMLStreamException, XmlContentException {
        if (match (reader, this,
                localName, ELEM_CC,
                CodecCompliance.class))
            return;
        if (match (reader, this,
                localName, ELEM_EO,
                EncodingOptions.class))
            return;
        throw new XmlContentException ("Unknown element name " + localName);
    }

}
