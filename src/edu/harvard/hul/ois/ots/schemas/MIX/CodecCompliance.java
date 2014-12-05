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

import edu.harvard.hul.ois.ots.schemas.XmlContent.StringElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

/** CodecCompliance is a subelement of JPEG2000 */
public class CodecCompliance extends MixElement {

    private final static String ELEM_CODEC = "codec";
    private final static String ELEM_VERSION = "codecVersion";
    private final static String ELEM_PROFILE = "codestreamProfile";
    private final static String ELEM_CLASS = "complianceClass";
    
    /**  Constructor from XML
     */
    public CodecCompliance (XMLStreamReader reader) 
             throws XMLStreamException, XmlContentException {
    	name = "CodecCompliance";
        parse(reader);
    }
    
    /** No-argument constructor */
    public CodecCompliance () {
        super();
        name = "CodecCompliance";
    }


    /** Returns the codec */
    public StringElement getCodec () {
        return (StringElement) getField (ELEM_CODEC);
    }
    
    /** Sets the codec 
     * @throws XmlContentException */
    public void setCodec (String c) throws XmlContentException {
        setField (ELEM_CODEC, c);
    }

    /** Returns the codecVersion */
    public StringElement getCodecVersion () {
        return (StringElement) getField (ELEM_VERSION);
    }
    
    /** Sets the CodecVersion 
     * @throws XmlContentException */
    public void setCodecVersion (String c) throws XmlContentException {
        setField (ELEM_VERSION, c);
    }

    /** Returns the codestreamProfile */
    public StringElement getCodestreamProfile () {
        return (StringElement) getField (ELEM_PROFILE);
    }
    
    /** Sets the CodecVersion 
     * @throws XmlContentException */
    public void setCodestreamProfile (String c) throws XmlContentException {
        setField (ELEM_PROFILE, c);
    }

    /** Returns the complianceClass */
    public StringElement getComplianceClass () {
        return (StringElement) getField (ELEM_CLASS);
    }
    
    /** Sets the ComplianceClass 
     * @throws XmlContentException */
    public void setComplianceClass (String c) throws XmlContentException {
        setField (ELEM_CLASS, c);
    }

    /** Output method which adds to a parent element.*/
    @Override
    public void output(SMNamespace ns, SMOutputElement parent) throws XMLStreamException {
        SMOutputElement mainElem = parent.addElement(ns, name);
        outputGenericElement (ns, ELEM_CODEC, mainElem);
        outputGenericElement (ns, ELEM_VERSION, mainElem);
        outputGenericElement (ns, ELEM_PROFILE, mainElem);
        outputGenericElement (ns, ELEM_CLASS, mainElem);
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
                localName, ELEM_CODEC,
                StringElement.class))
            return;
        if (match (reader, this,
                localName, ELEM_VERSION,
                StringElement.class))
            return;
        if (match (reader, this,
                localName, ELEM_PROFILE,
                StringElement.class))
            return;
        if (match (reader, this,
                localName, ELEM_CLASS,
                StringElement.class))
            return;
        throw new XmlContentException ("Unknown element name " + localName);
    }

}
