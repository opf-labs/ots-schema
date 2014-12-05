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

public class DigitalCameraCapture extends MixElement {

    private final static String ELEM_MFR = "digitalCameraManufacturer";
    private final static String ELEM_MODEL = "DigitalCameraModel";
    private final static String ELEM_SENSOR = "cameraSensor";
    private final static String ELEM_SETTINGS = "CameraCaptureSettings";

    /* Permitted values for cameraSensorType */
    private final static String[] VALUES_SENSOR = 
    { "undefined", "MonochromeArea", "OneChipColorArea", "TwoChipColorArea",
        "ThreeChipColorArea", "MonochromeLinear", "ColorTriLinear",
        "ColorSequentialLinear" };

    /**  Constructor from XML
     */
    public DigitalCameraCapture (XMLStreamReader reader) 
             throws XMLStreamException, XmlContentException {
    	name = "DigitalCameraCapture";
        defineRestrictions();
        parse(reader);
    }
    
    /** No-argument constructor */
    public DigitalCameraCapture () {
        super();
        name = "DigitalCameraCapture";
        defineRestrictions();
    }

    /** Define restrictions */
    private void defineRestrictions () {
        addRestriction(ELEM_SENSOR, VALUES_SENSOR);
    }

    /** Returns objectIdentifierType */
    public StringElement getDigitalCameraManufacturer () {
        return (StringElement) getField (ELEM_MFR);
    }
    
    /** Sets objectIdentifierType 
     * @throws XmlContentException */
    public void setDigitalCameraManufacturer (String s) throws XmlContentException {
        setField (ELEM_MFR, s);
    }
    
    /** Returns DigitalCameraModel */
    public DigitalCameraModel getDigitalCameraModel () {
        return (DigitalCameraModel) getField (ELEM_MODEL);
    }
    
    public void setDigitalCameraModel (DigitalCameraModel m) throws XmlContentException {
        setField (ELEM_MODEL, m);
    }

    /** Returns cameraSensor */
    public StringElement getCameraSensor () {
        return (StringElement) getField (ELEM_SENSOR);
    }
    
    /** Sets cameraSensor */
    public void setCameraSensor (String m) throws XmlContentException {
        setField (ELEM_SENSOR, m);
//        StringElement elem = (StringElement) getFieldElement (ELEM_SENSOR);
//        elem.checkContent(VALUES_SENSOR);
    }
    
    /** Returns CameraCaptureSettings */
    public CameraCaptureSettings getCameraCaptureSettings () {
        return (CameraCaptureSettings) getField (ELEM_SETTINGS);
    }
    
    /** Sets CameraCaptureSettings 
     * @throws XmlContentException */
    public void setCameraCaptureSettings (CameraCaptureSettings c) throws XmlContentException {
        setField (ELEM_SETTINGS, c);
    }

    @Override
    public void output(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
        SMOutputElement mainElem = parent.addElement(ns, name);
        outputGenericElement (ns, ELEM_MFR, mainElem);
        outputGenericElement (ns, ELEM_MODEL, mainElem);
        outputList (ns, ELEM_SENSOR, mainElem);
        outputGenericElement (ns, ELEM_SETTINGS, mainElem);
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
                localName, ELEM_MFR,
                StringElement.class)) {
            return;
        }
        if (match (reader, this, 
                localName, ELEM_MODEL,
                DigitalCameraModel.class)) {
            return;
        }
        if (match (reader, this, 
                localName, ELEM_SENSOR,
                StringElement.class)) {
            return;
        }
        if (match (reader, this, 
                localName, ELEM_SETTINGS,
                CameraCaptureSettings.class))
            return;
        throw new XmlContentException ("Unknown element name " + localName);
    }


}
