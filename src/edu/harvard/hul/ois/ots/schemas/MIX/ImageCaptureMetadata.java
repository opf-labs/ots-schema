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

/** ImageCaptureMetadata is a top-level subelement of MIX. */
public class ImageCaptureMetadata extends MixElement {

    private final static String ELEM_SI = "SourceInformation";
    private final static String ELEM_GCI = "GeneralCaptureInformation";
    private final static String ELEM_SC = "ScannerCapture";
    private final static String ELEM_DCC = "DigitalCameraCapture";
    private final static String ELEM_ORIENT = "orientation";
    private final static String ELEM_METH = "methodology";

    /* Permitted values for orientation */
    public final static String[] VALUES_ORIENT = 
    { "normal*",
        "normal, image flipped",
        "normal, rotated 180°",
        "normal, image flipped, rotated 180°",
        "normal, image flipped, rotated cw 90°",
        "normal, rotated ccw 90°",
        "normal, image flipped, rotated ccw 90°",
        "normal, rotated cw 90°",
        "unknown"};

    /**  Constructor from XML
     */
    public ImageCaptureMetadata (XMLStreamReader reader) 
             throws XMLStreamException, XmlContentException {
        name = "ImageCaptureMetadata";
        defineRestrictions();
        parse(reader);
    }
    
    /**  Constructor for this element
     */
    public ImageCaptureMetadata () {
        super();
        name = "ImageCaptureMetadata";
        defineRestrictions();
    }

    /** Define restrictions */
    private void defineRestrictions () {
        addRestriction(ELEM_ORIENT, VALUES_ORIENT);
    }


    public SourceInformation getSourceInformation () {
        return (SourceInformation) getField (ELEM_SI);
    }
    
    public void setSourceInformation (SourceInformation si) throws XmlContentException {
        setField (ELEM_SI, si);
    }
    
    public GeneralCaptureInformation getGeneralCaptureInformation () {
        return (GeneralCaptureInformation) getField (ELEM_GCI);
    }
    
    public void setGeneralCaptureInformation (GeneralCaptureInformation gci) 
                                          throws XmlContentException {
        setField (ELEM_GCI, gci);
    }
    
    public ScannerCapture getScannerCapture () {
        return (ScannerCapture) getField (ELEM_SC);
    }
    
    public void setScannerCapture (ScannerCapture sc) throws XmlContentException {
        setField (ELEM_SC, sc);
    }
    
    public DigitalCameraCapture getDigitalCameraCapture () {
        return (DigitalCameraCapture) getField (ELEM_DCC);
    }
    
    public void setDigitalCameraCapture (DigitalCameraCapture dcc) throws XmlContentException {
        setField (ELEM_DCC, dcc);
    }
    
    /** orientation is a restricted string */
    public StringElement getOrientation () {
        return (StringElement) getField (ELEM_ORIENT);
    }
    
    public void setOrientation (String s) throws XmlContentException {
        setField (ELEM_ORIENT, s);
    }
    
    public StringElement getMethodology () {
        return (StringElement) getField (ELEM_METH);
    }
    
    public void setMethodology (String s) throws XmlContentException {
        setField (ELEM_METH, s);
    }

    /** Output method which adds to a parent element.*/
    @Override
    public void output(SMNamespace ns, SMOutputElement parent) throws XMLStreamException {
        SMOutputElement mainElem = parent.addElement(ns, name);
        outputGenericElement (ns, ELEM_SI, mainElem);
        outputGenericElement (ns, ELEM_GCI, mainElem);
        outputGenericElement (ns, ELEM_SC, mainElem);
        outputGenericElement (ns, ELEM_DCC, mainElem);
        outputGenericElement (ns, ELEM_ORIENT, mainElem);
        outputGenericElement (ns, ELEM_METH, mainElem);
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
            if (SourceInformation.match (reader, this, 
                    localName, ELEM_SI, 
                    SourceInformation.class))
                return;
            if (GeneralCaptureInformation.match (reader, this,
                    localName, ELEM_GCI,
                    GeneralCaptureInformation.class))
                return;
            if (ScannerCapture.match (reader, this,
                    localName, ELEM_SC,
                    ScannerCapture.class))
                return;
            if (DigitalCameraCapture.match (reader, this,
                    localName, ELEM_DCC,
                    DigitalCameraCapture.class))
                return;
            if (StringElement.match (reader, this,
                    localName, ELEM_ORIENT,
                    StringElement.class)) {
                return;
            }
            if (StringElement.match (reader, this,
                    localName, ELEM_METH,
                    StringElement.class))
                return;
            throw new XmlContentException ("Unknown element name " + localName);
    }

    
}
