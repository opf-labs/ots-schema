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

public class ScannerCapture extends MixElement {

    private final static String ELEM_MFR = "scannerManufacturer";
    private final static String ELEM_MODEL = "ScannerModel";
    private final static String ELEM_MAXRES = "MaximumOpticalResolution";
    private final static String ELEM_SENSOR = "scannerSensor";
    private final static String ELEM_SOFTWARE = "ScanningSystemSoftware";

    /* Permitted values for scannerSensorType */
    private final static String[] VALUES_SENSOR = 
    { "undefined",
     "MonochromeLinear",
     "ColorTriLinear",
     "ColorSequentialLinear",
     "MonochromeArea",
     "OneChipColourArea",
     "TwoChipColorArea",
     "ThreeChipColorArea",
     "ColorSequentialArea" };

    /**  Constructor from XML
     */
    public ScannerCapture (XMLStreamReader reader) 
             throws XMLStreamException, XmlContentException {
        name = "ScannerCapture";
        defineRestrictions();
        parse(reader);
    }
    
    /** No-argument constructor */
    public ScannerCapture () {
        super();
        name = "ScannerCapture";
        defineRestrictions();
    }

    /** Define restrictions */
    private void defineRestrictions () {
        addRestriction(ELEM_SENSOR, VALUES_SENSOR);
    }
    
    public StringElement getScannerManufacturer () {
        return (StringElement) getField (ELEM_MFR);
    }
    
    public void setScannerManufacturer (String s) throws XmlContentException {
        setField (ELEM_MFR, s);
    }
    
    public ScannerModel getScannerModel () {
        return (ScannerModel) getField (ELEM_MODEL);
    }
    
    public void setScannerModel (ScannerModel m) throws XmlContentException {
        setField (ELEM_MODEL, m);
    }
    
    public MaximumOpticalResolution getMaximumOpticalResolution () {
        return (MaximumOpticalResolution) getField (ELEM_MAXRES);
    }
    
    public void setMaximumOpticalResolution (MaximumOpticalResolution m) throws XmlContentException {
        setField (ELEM_MAXRES, m);
    }
    
    /** Returns scannerSensor */
    public StringElement getScannerSensor () {
        return (StringElement) getField (ELEM_SENSOR);
    }
    
    /** Sets scannerSensor */
    public void setScannerSensor (StringElement m) throws XmlContentException {
        setField (ELEM_SENSOR, m);
    }
    
    /** Returns ScanningSystemSoftware */
    public ScanningSystemSoftware getScanningSystemSoftware () {
        return (ScanningSystemSoftware) getField (ELEM_SOFTWARE);
    }
    
    /** Sets ScanningSystemSoftware 
     * @throws XmlContentException */
    public void setScanningSystemSoftware (ScanningSystemSoftware s) throws XmlContentException {
        setField (ELEM_SOFTWARE, s);
    }

    
    @Override
    public void output(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
        SMOutputElement mainElem = parent.addElement(ns, name);
        outputGenericElement (ns, ELEM_MFR, mainElem);
        outputGenericElement (ns, ELEM_MODEL, mainElem);
        outputGenericElement (ns, ELEM_MAXRES, mainElem);
        outputGenericElement (ns, ELEM_SENSOR, mainElem);
        outputGenericElement(ns, ELEM_SOFTWARE, mainElem);
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
                localName, ELEM_MFR,
                StringElement.class))
            return;
        if (match (reader, this,
                localName, ELEM_MODEL,
                ScannerModel.class))
            return;
        if (match (reader, this,
                localName, ELEM_MAXRES,
                StringElement.class))
            return;
        if (match (reader, this,
                localName, ELEM_SENSOR,
                StringElement.class))
            return;
        if (match (reader, this,
                localName, ELEM_SOFTWARE,
                ScanningSystemSoftware.class))
            return;
        throw new XmlContentException ("Unknown element name " + localName);
    }
}
