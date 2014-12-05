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

import edu.harvard.hul.ois.ots.schemas.XmlContent.IntegerElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.StringElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

import org.codehaus.staxmate.out.SMNamespace;
import org.codehaus.staxmate.out.SMOutputElement;

import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/** BasicDigitalObjectInformation is a top-level subelement of MIX. */
public class BasicDigitalObjectInformation extends MixElement  {
    
    private final static String ELEM_OBJID = "ObjectIdentifier";
    private final static String ELEM_FILESIZE = "fileSize";
    private final static String ELEM_BYTEORDER = "byteOrder";
    private final static String ELEM_FMTREGISTRY = "FormatRegistry";
    private final static String ELEM_COMPRESSION = "Compression";
    private final static String ELEM_FMTDES = "FormatDesignation";
    private final static String ELEM_FIXITY = "Fixity";
    
    public final static String[] VALUES_BYTEORDER =
    { "big endian", "little endian" };
    
    /**  Constructor for this element
     */
    public BasicDigitalObjectInformation (XMLStreamReader reader)
            throws XMLStreamException, XmlContentException {
        name = "BasicDigitalObjectInformation";
        defineRestrictions();
        parse (reader);
    }
    
    /** No-argument constructor */
    public BasicDigitalObjectInformation () {
        super();
        name = "BasicDigitalObjectInformation";
        defineRestrictions();
    }

    /** Define restrictions */
    private void defineRestrictions () {
        addRestriction(ELEM_BYTEORDER, VALUES_BYTEORDER);
    }

    /** Returns the ObjectIdentifier.  */
    public ObjectIdentifier getObjectIdentifier () {
        return (ObjectIdentifier) getField (ELEM_OBJID);
    }
    
    /** Sets the ObjectIdentifier. 
     * @throws XmlContentException */
    public void setObjectIdentifier (ObjectIdentifier x) throws XmlContentException {
        setField (ELEM_OBJID, x);
    }
    
    /** Returns the file size.  */
    public IntegerElement getFileSize () {
        IntegerElement fsiz = (IntegerElement) getField (ELEM_FILESIZE);
        return fsiz;
    }
    
    /** Sets the file size. 
     * @throws XmlContentException */
    public void setFileSize (int size) throws XmlContentException {
        setField (ELEM_FILESIZE, size);
    }
    
    /** Returns the byteOrder. */
    public StringElement getByteOrder () {
        return (StringElement) getField (ELEM_BYTEORDER);
    }
    
    /** Sets the byteOrder. Doesn't check for valid strings. 
     * @throws XmlContentException */
    public void setByteOrder (String s) throws XmlContentException {
        setField (ELEM_BYTEORDER, s);
    }
    
    /** Returns the FormatDesignation. */
    public FormatDesignation getFormatDesignation () {
        return (FormatDesignation) getField (ELEM_FMTDES);
    }
    
    /** Sets the FormatDesignation. 
     * @throws XmlContentException */
    public void setFormatDesignation (FormatDesignation x) throws XmlContentException {
        setField (ELEM_FMTDES, x);
    }
    
    /** Returns the FormatRegistry. */
    public FormatRegistry getFormatRegistry () {
        return (FormatRegistry) getField (ELEM_FMTREGISTRY);
    }
    
    /** Sets the FormatRegistry. 
     * @throws XmlContentException */
    public void setFormatRegistry (FormatRegistry x) throws XmlContentException {
        setField (ELEM_FMTREGISTRY, x);
    }
    
    /** Returns the Compression. */
    public Compression getCompression () {
        return (Compression) getField (ELEM_COMPRESSION);
    }
    
    /** Sets the Compression. 
     * @throws XmlContentException */
    public void setCompression (Compression x) throws XmlContentException {
        setField (ELEM_COMPRESSION, x);
    }
    
    /** Returns a List of Fixity objects. */
    @SuppressWarnings("unchecked")
	public List<Fixity> getFixities () {
        return (List<Fixity>)
           genericList(getList (ELEM_FIXITY));
    }
    
    /** Add a single Fixity object. There can be any number of them. 
     * @throws XmlContentException */
    public void addFixity(Fixity f) throws XmlContentException {
    	addToList(f, ELEM_FIXITY);
    }

    public void output(SMNamespace ns, SMOutputElement parent) throws XMLStreamException {
        SMOutputElement mainElem = outputElement (ns, name, parent); 
        outputGenericElement (ns, ELEM_OBJID, mainElem);
        outputGenericElement (ns, ELEM_FILESIZE, mainElem);
        outputGenericElement (ns, ELEM_FMTDES, mainElem);
        outputGenericElement (ns, ELEM_FMTREGISTRY, mainElem);
        outputGenericElement (ns, ELEM_BYTEORDER, mainElem);
        outputGenericElement (ns, ELEM_COMPRESSION, mainElem);
        outputList (ns, ELEM_FIXITY, mainElem);
    }


    @Override
    public boolean validate() {
        // TODO Auto-generated method stub
        return false;
    }
    

    /** Classes must implement this to handle START_ELEMENT cases.  */
    protected void dispatch (String localName, XMLStreamReader reader) 
                  throws XMLStreamException, XmlContentException {
        if (match (reader, this, 
                localName, ELEM_OBJID, 
                ObjectIdentifier.class))
            return;
        if (match (reader, this,
                localName, ELEM_FILESIZE,
                IntegerElement.class))
            return;
        if (match (reader, this,
                localName, ELEM_FMTDES,
                FormatDesignation.class))
            return;
        if (match (reader, this,
                localName, ELEM_FMTREGISTRY,
                FormatRegistry.class))
            return;
        if (match (reader, this,
                localName, ELEM_BYTEORDER,
                StringElement.class)) {
//            StringElement elem = (StringElement) getFieldElement (ELEM_BYTEORDER);
//            elem.checkContent(VALUES_BYTEORDER);
            return;
        }
        if (match (reader, this,
                localName, ELEM_COMPRESSION,
                Compression.class))
            return;
        // Fixity is a repeatable element, so the Fixity value will
        // be a ListElement. matchList makes that happen.
        if (matchList (reader, this,
                localName, ELEM_FIXITY,
                Fixity.class))
            return;
        throw new XmlContentException ("Unknown element name " + localName);
    }

}
