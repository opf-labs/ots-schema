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

public class Speed extends AESObject {

    private final static String ELEMENTNAME = "speed";
    
    private final static String ELEM_DESIGNATED = "speedDesignated";
    private final static String ELEM_ADJUSTMENT = "varispeedAdjustment";
    private final static String ELEM_NOTE = "speedNote";

    public Speed(XMLStreamReader reader) 
               throws XMLStreamException, XmlContentException {
        this.name = ELEMENTNAME;
        parse (reader);
    }
    
    public SpeedMeasurement getSpeedDesignated () {
        return (SpeedMeasurement) getField (ELEM_DESIGNATED);
    }
    
    public void setSpeedDesignated (SpeedMeasurement s) throws XmlContentException {
        setField (ELEM_DESIGNATED, s);
    }
    
    public SpeedMeasurement getVariSpeedAdjustment () {
        return (SpeedMeasurement) getField (ELEM_ADJUSTMENT);
    }
    
    public void setVariSpeedAdjustment (SpeedMeasurement s) throws XmlContentException {
        setField (ELEM_ADJUSTMENT, s);
    }
    
    public StringElement getSpeedNote () {
        return (StringElement) getField (ELEM_NOTE);
    }
    
    public void setSpeedNote (StringElement s) throws XmlContentException {
        setField (ELEM_NOTE, s);
    }
    
    @Override
    public void output(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
        SMOutputElement mainElem = outputElement (ns, name, parent); 
        outputGenericElement (ns, ELEM_DESIGNATED, mainElem);
        outputGenericElement (ns, ELEM_ADJUSTMENT, mainElem);
        outputGenericElement (ns, ELEM_NOTE, mainElem);
    }

    @Override
    public boolean validate() {
        // TODO Auto-generated method stub
        return false;
    }
    
    @Override
    protected void dispatch(String localName, XMLStreamReader reader)
            throws XMLStreamException, XmlContentException {
        if (match(reader, this, localName, ELEM_DESIGNATED, SpeedMeasurement.class))
            return;
        if (match(reader, this, localName, ELEM_ADJUSTMENT, SpeedMeasurement.class))
            return;
        if (match(reader, this, localName, ELEM_NOTE, StringElement.class))
            return;
        throw new XmlContentException("Unknown element name " + localName);
    }

}
