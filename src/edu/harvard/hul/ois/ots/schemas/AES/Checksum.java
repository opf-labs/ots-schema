/**
 * @author Gary McGath
 *
 * Copyright (c) 
 * 2010 by the President and Fellows of Harvard College
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
 */
package edu.harvard.hul.ois.ots.schemas.AES;

import edu.harvard.hul.ois.ots.schemas.XmlContent.DateElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.StringElement;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.codehaus.staxmate.out.SMNamespace;
import org.codehaus.staxmate.out.SMOutputElement;

import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

/** The AES Checksum class applies to more than one element, 
 *  so the name must be specified in the constructor.
 */
public class Checksum extends AESObject {

    
    private final static String ELEM_VALUE = "checksumValue";
    private final static String ELEM_KIND = "checksumKind";
    private final static String ELEM_CREATEDATE = "checksumCreateDate";

    private final static String[] VALUES_KIND =
    { "CRC", "MD5", "SHA-1" };

    /**  Constructor for this element
     */
    public Checksum (XMLStreamReader reader, String name) 
           throws XMLStreamException, XmlContentException {
        defineRestrictions();
        this.name = name;
        parse (reader);
    }
    
    /** Constructor with name only. */
    public Checksum (String name) {
        super();
        this.name = name;
        defineRestrictions();
    }
    
    /** Define restrictions */
    private void defineRestrictions () {
        addRestriction(ELEM_KIND, VALUES_KIND);
    }
    
    public StringElement getChecksumValue () {
        return (StringElement) getField (ELEM_VALUE);
    }
    
    public void setChecksumValue (String s) throws XmlContentException  {
        setField (ELEM_VALUE, s);
    }

    public StringElement getChecksumKind () {
        return (StringElement) getField (ELEM_KIND);
    }
    
    public void setChecksumKind (String s) throws XmlContentException  {
        setField (ELEM_KIND, s);
    }

    public StringElement getChecksumCreateDate () {
        return (StringElement) getField (ELEM_CREATEDATE);
    }
    
    public void setChecksumCreateDate (String s) throws XmlContentException  {
        setField (ELEM_CREATEDATE, s);
    }


    @Override
    public boolean validate() {
        return getField(ELEM_VALUE) != null &&
               getField(ELEM_KIND) != null &&
               getField(ELEM_CREATEDATE) != null;
    }

    @Override
    public void output(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
        SMOutputElement mainElem = outputElement (ns, name, parent); 
        outputGenericElement (ns, ELEM_VALUE, mainElem);
        outputGenericElement (ns, ELEM_KIND, mainElem);
        outputGenericElement (ns, ELEM_CREATEDATE, mainElem);

    }
    
    
    protected void dispatch(String localName, XMLStreamReader reader)
    throws XMLStreamException, XmlContentException {
        if (match(reader, this, localName, ELEM_VALUE, StringElement.class))
            return;
        if (match(reader, this, localName, ELEM_KIND, StringElement.class))
            return;
        if (match(reader, this, localName, ELEM_CREATEDATE, DateElement.class))
            return;
        throw new XmlContentException("Unknown element name " + localName);
    }


}
