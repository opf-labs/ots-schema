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

import edu.harvard.hul.ois.ots.schemas.XmlContent.IntegerElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.RealElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.StringElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

public class DigitalTapeFormatRegion extends FormatRegionSubtype {

    protected final static String TYPENAME = "aes:digitalTapeFormatRegionType";

    private final static String ELEM_PHYSPROPS = "physicalProperties";
    private final static String ELEM_SPEED = "speed";
    private final static String ELEM_BITDEPTH = "bitDepth";
    private final static String ELEM_SAMPLERATE = "sampleRate";
    private final static String ELEM_WORDSIZE = "wordSize";
    private final static String ELEM_TRACKLAYOUT = "trackLayout";
    private final static String ELEM_SOUNDFIELD = "soundField";
    private final static String ELEM_BITRATEREDUCTION = "bitrateReduction";

    public DigitalTapeFormatRegion () {
    	super();
    }

//    public DigitalTapeFormatRegion (XMLStreamReader reader) 
//    throws XMLStreamException, XmlContentException {
//        parse (reader);
//    }
//
//    public DigitalTapeFormatRegion (XMLStreamReader reader, String name) 
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
    
    public IntegerElement getBitDepth () {
        return (IntegerElement) getField (ELEM_BITDEPTH);
    }
    
    public void setBitDepth (int d) throws XmlContentException {
        setField (ELEM_BITDEPTH, d);
    }
    
    public RealElement getSampleRate () {
        return (RealElement) getField (ELEM_SAMPLERATE);
    }
    
    public void setSampleRate (double d) throws XmlContentException {
        setField (ELEM_SAMPLERATE, d);
    }

    public IntegerElement getWordSize () {
        return (IntegerElement) getField (ELEM_WORDSIZE);
    }
    
    public void setWordSize (int d) throws XmlContentException {
        setField (ELEM_WORDSIZE, d);
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
    
    public BitrateReduction getBitrateReduction () {
        return (BitrateReduction) getField (ELEM_BITRATEREDUCTION);
    }
    
    public void setBitrateReduction (BitrateReduction b)
                                throws XmlContentException {
        setField (ELEM_BITRATEREDUCTION, b);
    }
    

    
    @Override
    public void output(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
        SMOutputElement mainElem = outputElement (ns, name, parent); 
        outputGenericElement (ns, ELEM_PHYSPROPS, mainElem);
        outputGenericElement (ns, ELEM_SPEED, mainElem);
        outputGenericElement (ns, ELEM_BITDEPTH, mainElem);
        outputGenericElement (ns, ELEM_SAMPLERATE, mainElem);
        outputGenericElement (ns, ELEM_WORDSIZE, mainElem);
        outputGenericElement (ns, ELEM_TRACKLAYOUT, mainElem);
        outputGenericElement (ns, ELEM_SOUNDFIELD, mainElem);
        outputGenericElement (ns, ELEM_BITRATEREDUCTION, mainElem);
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
        if (match(reader, this, localName, ELEM_BITDEPTH, IntegerElement.class))
            return;
        if (match(reader, this, localName, ELEM_SAMPLERATE, RealElement.class))
            return;
        if (match(reader, this, localName, ELEM_WORDSIZE, IntegerElement.class))
            return;
        if (match(reader, this, localName, ELEM_TRACKLAYOUT, StringElement.class))
            return;
        if (match(reader, this, localName, ELEM_SOUNDFIELD, StringElement.class))
            return;
        if (match(reader, this, localName, ELEM_BITRATEREDUCTION, BitrateReduction.class))
            return;
        throw new XmlContentException("Unknown element name " + localName);
    }

}
