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

public class CameraCaptureSettings extends MixElement {

    private final static String ELEM_IMG = "ImageData";
    private final static String ELEM_GPS = "GPSData";

    /**  Constructor from XML
     */
    public CameraCaptureSettings (XMLStreamReader reader) 
             throws XMLStreamException, XmlContentException {
        name = "CameraCaptureSettings";
        defineRestrictions();
        parse(reader);
    }
    
    /** No-argument constructor */
    public CameraCaptureSettings () {
        super();
        name = "CameraCaptureSettings";
        defineRestrictions();
    }

    /** Define restrictions */
    private void defineRestrictions () {
    }

    public ImageData getImageData () {
        return (ImageData) getField (ELEM_IMG);
    }
    
    public void setImageData (ImageData d) throws XmlContentException {
        setField (ELEM_IMG, d);
    }

    public GPSData getGPSData () {
        return (GPSData) getField (ELEM_GPS);
    }
    
    public void setGPSData (GPSData d) throws XmlContentException {
        setField (ELEM_GPS, d);
    }

    @Override
    public void output(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
        SMOutputElement mainElem = parent.addElement(ns, name);
        outputGenericElement (ns, ELEM_IMG, mainElem);
        outputGenericElement (ns, ELEM_GPS, mainElem);
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
                localName, ELEM_IMG,
                ImageData.class)) {
            return;
        }
        if (GPSData.match (reader, this, 
                localName, ELEM_GPS,
                GPSData.class)) {
            return;
        }
        throw new XmlContentException ("Unknown element name " + localName);
    }

}
