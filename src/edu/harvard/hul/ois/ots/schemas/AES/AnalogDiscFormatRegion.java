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

public class AnalogDiscFormatRegion extends FormatRegionSubtype {

    protected final static String TYPENAME = "aes:analogDiscFormatRegionType";

    private final static String ELEM_PHYSPROPS = "physicalProperties";
    private final static String ELEM_SPEED = "speed";
    private final static String ELEM_GRVORIENT = "grooveOrientation";
    private final static String ELEM_GRVWIDTH = "grooveWidth";
    private final static String ELEM_GRVCREATION = "grooveCreationMethod";
    private final static String ELEM_SOUNDFIELD = "soundField";
    private final static String ELEM_NOISEREDUCTION = "noiseReduction";
    private final static String ELEM_EQ = "equalization";

    public AnalogDiscFormatRegion() {
    	super();
        defineRestrictions ();
    }
    
    /** Define restrictions */
    private void defineRestrictions () {
        addRestriction(ELEM_GRVORIENT, VALUES_GRVORIENT);
        addRestriction(ELEM_GRVCREATION, VALUES_GRVCREATION);
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
    
    public StringElement getGrooveOrientation () {
        return (StringElement) getField (ELEM_GRVORIENT);
    }
    
    public void setGrooveOrientation (String s) throws XmlContentException {
        setField (ELEM_GRVORIENT, s);
    }
    
    public GrooveWidth getGrooveWidth () {
        return (GrooveWidth) getField (ELEM_GRVWIDTH);
    }
    
    public void setGrooveWidth (GrooveWidth g) throws XmlContentException {
        setField (ELEM_GRVWIDTH, g);
    }
    
    public StringElement getGrooveCreationMethod () {
        return (StringElement) getField (ELEM_GRVCREATION);
    }
    
    public void setGrooveCreationMethod (String s) throws XmlContentException {
        setField (ELEM_GRVCREATION, s);
    }
    
    public StringElement getSoundField () {
        return (StringElement) getField (ELEM_SOUNDFIELD);
    }
    
    public void setSoundField (String s) throws XmlContentException {
        setField (ELEM_SOUNDFIELD, s);
    }

    public StringElement getNoiseReduction () {
        return (StringElement) getField (ELEM_NOISEREDUCTION);
    }
    
    public void setNoiseReduction (String s) throws XmlContentException {
        setField (ELEM_NOISEREDUCTION, s);
    }
    
    public StringElement getEqualization () {
        return (StringElement) getField (ELEM_EQ);
    }
    
    public void setEqualization (String s) throws XmlContentException {
        setField (ELEM_EQ, s);
    }
    
    
    

    /** Returns the type name for this class. */
    @Override
    public String getTypeName () {
        return TYPENAME;
    }

    @Override
    public void output(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
        SMOutputElement mainElem = parent.addElement(ns, name);
        outputAttrs (mainElem);
        outputGenericElement (ns, ELEM_PHYSPROPS, mainElem);
        outputGenericElement (ns, ELEM_SPEED, mainElem);
        outputGenericElement (ns, ELEM_GRVORIENT, mainElem);    
        outputGenericElement (ns, ELEM_GRVWIDTH, mainElem);
        outputGenericElement (ns, ELEM_GRVCREATION, mainElem);
        outputGenericElement (ns, ELEM_SOUNDFIELD, mainElem);    
        outputGenericElement (ns, ELEM_NOISEREDUCTION, mainElem);
        outputGenericElement (ns, ELEM_EQ, mainElem);    
    }

    
    @Override
    public boolean validate() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    protected void dispatch(String localName, XMLStreamReader reader)
         throws XMLStreamException, XmlContentException {
        if (match(reader, this, localName, ELEM_PHYSPROPS, PhysProps.class))
            return;
        if (match(reader, this, localName, ELEM_SPEED, Speed.class))
            return;
        if (match(reader, this, localName, ELEM_GRVORIENT, StringElement.class))
            return;
        if (match(reader, this, localName, ELEM_GRVWIDTH, GrooveWidth.class))
            return;
        if (match(reader, this, localName, ELEM_GRVCREATION, StringElement.class))
            return;
        if (match(reader, this, localName, ELEM_SOUNDFIELD, StringElement.class))
            return;
        if (match(reader, this, localName, ELEM_NOISEREDUCTION, StringElement.class))
            return;
        if (match(reader, this, localName, ELEM_EQ, StringElement.class))
            return;
        throw new XmlContentException("Unknown element name " + localName);
    }

}
