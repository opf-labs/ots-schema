/**********************************************************************
 * Copyright (c) 2010 by the President and Fellows of Harvard College
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

package edu.harvard.hul.ois.ots.schemas.AES;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.codehaus.staxmate.out.SMNamespace;
import org.codehaus.staxmate.out.SMOutputElement;

import edu.harvard.hul.ois.ots.schemas.XmlContent.StringElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

/** The analogTapeFormatRegionType in the AES schema is never directly
 *  instantiated, but rather is an element of baseFormatRegionType with
 *  type=analogTapeFormatRegionType. 
 */
public class AnalogTapeFormatRegion extends FormatRegionSubtype {

    protected final static String TYPENAME = "aes:analogTapeFormatRegionType";

    private final static String ELEM_PHYSPROPS = "physicalProperties";
    private final static String ELEM_SPEED = "speed";
    private final static String ELEM_TRACKLAYOUT = "trackLayout";
    private final static String ELEM_SOUNDFIELD = "soundField";
    private final static String ELEM_REDUCTION = "noiseReduction";
    private final static String ELEM_EQ = "equalization";

    public AnalogTapeFormatRegion () {
    	super();
    }

//    public AnalogTapeFormatRegion (XMLStreamReader reader) 
//    throws XMLStreamException, XmlContentException {
//        parse (reader);
//    }
//
//    public AnalogTapeFormatRegion (XMLStreamReader reader, String name) 
//    throws XMLStreamException, XmlContentException {
//        this.name = name;
//        parse (reader);
//    }

    /** Returns the type name for this class. */
    @Override
    public String getTypeName () {
        return TYPENAME;
    }

    public PhysProps getPhysicalProperties () {
        return (PhysProps) getField (ELEM_PHYSPROPS);
    }
    
    public void setPhysicalProperties (PhysProps p) throws XmlContentException {
        setField (ELEM_PHYSPROPS, p);
    }

    public Speed getSpeed () {
        return (Speed) getField (ELEM_SPEED);
    }
    
    public void setSpeed (Speed s) throws XmlContentException {
        setField (ELEM_SPEED, s);
    }
    
    public StringElement getTrackLayout () {
        return (StringElement) getField (ELEM_TRACKLAYOUT);
    }
    
    public void setTrackLayout (String s) throws XmlContentException {
        setField (ELEM_TRACKLAYOUT, s);
    }
    
    public StringElement getSoundField () {
        return (StringElement) getField (ELEM_SOUNDFIELD);
    }
    
    public void setSoundField (String s) throws XmlContentException {
        setField (ELEM_SOUNDFIELD, s);
    }
    
    public StringElement getNoiseReduction () {
        return (StringElement) getField (ELEM_REDUCTION);
    }
    
    public void setNoiseReduction (String s) throws XmlContentException {
        setField (ELEM_REDUCTION, s);
    }
    
    public StringElement getEqualization () {
        return (StringElement) getField(ELEM_EQ);
    }
    
    public void setEqualization (String s) throws XmlContentException {
        setField (ELEM_EQ, s);
    }

    @Override
    public void output(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
        SMOutputElement mainElem = parent.addElement(ns, name);
        outputAttrs (mainElem);
        outputGenericElement (ns, ELEM_PHYSPROPS, mainElem);
        outputGenericElement (ns, ELEM_SPEED, mainElem);
        outputGenericElement (ns, ELEM_TRACKLAYOUT, mainElem);
        outputGenericElement (ns, ELEM_SOUNDFIELD, mainElem);
        outputGenericElement (ns, ELEM_REDUCTION, mainElem);
        outputGenericElement (ns, ELEM_EQ, mainElem);
    }

    @Override
    public boolean validate() {
        return true;
    }

    @Override
    protected void dispatch(String localName, XMLStreamReader reader)
                 throws XMLStreamException, XmlContentException {
        if (match(reader, this, localName, ELEM_PHYSPROPS, PhysProps.class))
            return;
        if (match(reader, this, localName, ELEM_SPEED, Speed.class))
            return;
        if (match(reader, this, localName, ELEM_TRACKLAYOUT, StringElement.class))
            return;
        if (match(reader, this, localName, ELEM_SOUNDFIELD, StringElement.class))
            return;
        if (match(reader, this, localName, ELEM_REDUCTION, StringElement.class))
            return;
        if (match(reader, this, localName, ELEM_EQ, StringElement.class))
            return;
        throw new XmlContentException("Unknown element name " + localName);
    }

}
