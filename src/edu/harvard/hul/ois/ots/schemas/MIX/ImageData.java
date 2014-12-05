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

import java.util.List;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.codehaus.staxmate.out.SMNamespace;
import org.codehaus.staxmate.out.SMOutputElement;

import edu.harvard.hul.ois.ots.schemas.XmlContent.IntegerElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.Rational;
import edu.harvard.hul.ois.ots.schemas.XmlContent.RationalElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.RealElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.Restriction;
import edu.harvard.hul.ois.ots.schemas.XmlContent.StringElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

public class ImageData extends MixElement {

    private final static String ELEM_FNUMBER = "fNumber";
    private final static String ELEM_EXTIME = "exposureTime";
    private final static String ELEM_EXPROG ="exposureProgram";
    private final static String ELEM_SPECSENS ="spectralSensitivity";
    private final static String ELEM_ISOSPEED ="isoSpeedRatings";
    private final static String ELEM_OECF ="oECF";
    private final static String ELEM_EXIFVSN ="exifVersion";
    private final static String ELEM_SHUTTERSPEED ="shutterSpeedValue";
    private final static String ELEM_APERTURE ="apertureValue";
    private final static String ELEM_BRIGHT ="brightnessValue";
    private final static String ELEM_EXBIAS ="exposureBiasValue";
    private final static String ELEM_MAXAPERTURE ="maxApertureValue";
    private final static String ELEM_SUBJDIST ="SubjectDistance";
    private final static String ELEM_METERMODE ="meteringMode";
    private final static String ELEM_SOURCE ="lightSource";
    private final static String ELEM_FLASH ="flash";
    private final static String ELEM_FOCALLENGTH ="focalLength";
    private final static String ELEM_FLASHENERGY ="flashEnergy";
    private final static String ELEM_BACKLIGHT ="backLight";
    private final static String ELEM_EXINDEX ="exposureIndex";
    private final static String ELEM_SENSING ="sensingMethod";
    private final static String ELEM_CFA ="cfaPattern";
    private final static String ELEM_AUTOFOCUS ="autoFocus";
    private final static String ELEM_ASPECT ="PrintAspectRatio";

    /* Permitted values for exposureProgram */
    private final static String[] VALUES_EXPROG = 
    { "Not defined",
    "Manual",
    "Normal program",
    "Aperture priority",
    "Shutter priority",
    "Creative program (biased toward depth of field)",
    "Action program (biased toward fast shutter speed)",
    "Portrait mode (for closeup photos with the background out of focus)",
    "Landscape mode (for landscape photos with the background in focus)"};
    
    /* Permitted value (singular) for exifVersion */
    private final static String[] VALUES_EXIFVSN = 
    { "0220","0221"};
    
    /* Permitted values for meteringMode */
    private final static String[] VALUES_METERMODE = 
    { "Average",
    "Center weighted average",
    "Spot",
    "Multispot",
    "Pattern",
    "Partial"};
    
    /* Permitted values for lightSource */
    private final static String[] VALUES_SOURCE = 
    { "Daylight",
    "Fluorescent",
    "Tungsten (incandescent light)",
    "Flash",
    "Fine weather",
    "Cloudy weather",
    "Shade",
    "Daylight fluorescent (D 5700 - 7100K)",
    "Day white fluorescent (N 4600 - 5400K)",
    "Cool white fluorescent (W 3900 - 4500K)",
    "White fluorescent (WW 3200 - 3700K)",
    "Standard light A",
    "Standard light B",
    "Standard light C",
    "D55",
    "D65",
    "D75",
    "D50",
    "ISO studio tungsten",
    "other light source",
    "unknown"};
    
    /* Permitted values for flash */
    private final static String[] VALUES_FLASH = 
    { "Flash did not fire",
    	"Flash fired",
    	"Strobe return light light not detected",
    	"Strobe return light detected",
    	"Flash fired, compulsary flash mode",
    	"Flash fired, compulsary flash mode, return light not detected",
    	"Flash fired, compulsary flash mode, return light detected",
    	"Flash did not fire, compulsory flash mode",
    	"Flash did not fire, auto mode",
    	"Flash fired, auto mode",
    	"Flash fired, auto mode, return light not detected",
    	"Flash fired, auto mode, return light detected",
    	"Flash fired, red-eye reduction mode",
    	"Flash fired, red-eye reduction mode, return light not detected",
    	"Flash fired, red-eye reduction mode, return light detected",
    	"Flash fired, compulsory flash mode, red-eye reduction mode",
    	"Flash fired, compulsory flash mode, red-eye reduction mode, return light not detected",
    	"Flash fired, compulsory flash mode, red-eye reduction mode, return light detected",
    	"Flash fired, auto mode, red-eye reduction mode",
    	"Flash fired, auto mode, return light not detected, red-eye reduction mode",
    	"Flash fired, auto mode, return light detected, red-eye reduction mode",
    	"No flash function"};

    /* Permitted values for backlight */
    private final static String[] VALUES_BACKLIGHT = 
    { "Front light",
    "Backlight 1",
    "Backlight 2"};
    
    /* Permitted values for sensingMethod */
    private final static String[] VALUES_SENSING = 
    { "Not defined", 
    "One-chip color area sensor" , 
    "Two-chip color area sensor" , 
    "Three-chip color area sensor" , 
    "Color sequential area sensor" , 
    "Trilinear sensor" , 
    "Color sequential linear sensor"};
    
    /**  Constructor from XML
     */
    public ImageData (XMLStreamReader reader) 
             throws XMLStreamException, XmlContentException {
        name = "ImageData";
        defineRestrictions();
        parse(reader);
    }
    
    /** No-argument constructor */
    public ImageData () {
        super();
        name = "ImageData";
        defineRestrictions();
    }

    /** Define restrictions */
    private void defineRestrictions () {
        addRestriction(ELEM_EXPROG, VALUES_EXPROG);
        addRestriction(ELEM_EXIFVSN, VALUES_EXIFVSN);
        addRestriction(ELEM_METERMODE, VALUES_METERMODE);
        addRestriction(ELEM_SOURCE, VALUES_SOURCE);
        addRestriction(ELEM_FLASH, VALUES_FLASH);
        addRestriction(ELEM_FOCALLENGTH, Restriction.NONNEGATIVE);
        addRestriction(ELEM_BACKLIGHT, VALUES_BACKLIGHT);
        addRestriction(ELEM_EXINDEX, Restriction.NONNEGATIVE);
        addRestriction(ELEM_SENSING, VALUES_SENSING);
    }

    public RealElement getFNumber () {
        return (RealElement) getField (ELEM_FNUMBER);
    }
    
    public void setFNumber (Double d) throws XmlContentException {
        setField (ELEM_FNUMBER, d);
    }
    
    public RealElement getExposureTime () {
        return (RealElement) getField (ELEM_EXTIME);
    }
    
    public void setExposureTime (Double d) throws XmlContentException {
        setField (ELEM_EXTIME, d);
    }
    
    public StringElement getExposureProgram () {
        return (StringElement) getField (ELEM_EXPROG);
    }
    
    public void setExposureProgram (String s) throws XmlContentException {
        setField (ELEM_EXPROG, s);
    }
    
    @SuppressWarnings("unchecked")
	public List<StringElement> getSpectralSensitivities () {
        return genericList( getList (ELEM_SPECSENS));
    }
    
    public void addSpectralSensitivity (String s) throws XmlContentException {
        addToList (new StringElement (s, ELEM_SPECSENS), ELEM_SPECSENS);
    }

    public IntegerElement getIsoSpeedRatings () {
        return (IntegerElement) getField (ELEM_ISOSPEED);
    }
    
    public void setIsoSpeedRatings (int n) throws XmlContentException{
        setField (ELEM_ISOSPEED, n);
    }
    
    public RationalElement getOECF () {
        return (RationalElement) getField (ELEM_OECF);
    }
    
    public void setOECF (Rational r) throws XmlContentException {
        setField (ELEM_OECF, r);
    }

    public StringElement getExifVersion () {
        return (StringElement) getField (ELEM_EXIFVSN);
    }
    
    public void setExifVersion (String s) throws XmlContentException {
        setField (ELEM_EXIFVSN, s);
    }
    
    public RationalElement getShutterSpeedValue () {
        return (RationalElement) getField (ELEM_SHUTTERSPEED);
    }
    
    public void setShutterSpeedValue (Rational r) throws XmlContentException {
        setField (ELEM_SHUTTERSPEED, r);
    }
    
    public RationalElement getApertureValue () {
        return (RationalElement) getField (ELEM_APERTURE);
    }

    public void setApertureValue (Rational r) throws XmlContentException{
        setField (ELEM_APERTURE, r);
    }
    
    public RationalElement getBrightnessValue () {
        return (RationalElement) getField (ELEM_BRIGHT);
    }
    
    public void setBrightnessValue (Rational r) throws XmlContentException {
        setField (ELEM_BRIGHT, r);
    }
    
    public RationalElement getExposureBiasValue () {
        return (RationalElement) getField (ELEM_EXBIAS);
    }
    
    public void setExposureBiasValue (Rational r) throws XmlContentException {
        setField (ELEM_EXBIAS, r);
    }
    
    public RationalElement getMaxApertureValue () {
        return (RationalElement) getField (ELEM_MAXAPERTURE);
    }
    
    public void setMaxApertureValue (Rational r) throws XmlContentException {
        setField (ELEM_MAXAPERTURE, r);
    }
    
    public SubjectDistance getSubjectDistance () {
        return (SubjectDistance) getField (ELEM_SUBJDIST);
    }
    
    public void setSubjectDistance (SubjectDistance s) throws XmlContentException {
        setField (ELEM_SUBJDIST, s);
    }
    
    public StringElement getMeteringMode () {
        return (StringElement) getField (ELEM_METERMODE);
    }
    
    public void setMeteringMode (String s) throws XmlContentException {
        setField (ELEM_METERMODE, s);
    }
    
    public StringElement getLightSource () {
        return (StringElement) getField (ELEM_SOURCE);
    }
    
    public void setLightSource (String s) throws XmlContentException {
        setField (ELEM_SOURCE, s);
    }
    
    public StringElement getFlash () {
        return (StringElement) getField(ELEM_FLASH);
    }
    
    public void setFlash (String s) throws XmlContentException {
        setField (ELEM_FLASH, s);
    }
    
    public RealElement getFocalLength () {  
        return (RealElement) getField (ELEM_FOCALLENGTH);
        // Note: this is a Decimal. We may want to have special code for Decimal numbers
        // to insure they're output only in xxx.yyy notation. But then we have to preserve
        // the information that it's Double.
    }
    
    public void setFocalLength (Double d) throws XmlContentException {
        setField (ELEM_FOCALLENGTH, d);
    }
    
    public RationalElement getFlashEnergy () {
        return (RationalElement) getField (ELEM_FLASHENERGY);
    }
    
    public void setFlashEnergy (Rational r) throws XmlContentException {
        setField (ELEM_FLASHENERGY, r);
    }
    
    public StringElement getBackLight () {
        return (StringElement) getField (ELEM_BACKLIGHT);
    }
    
    public void setBackLight (String s) throws XmlContentException {
        setField (ELEM_BACKLIGHT, s);
    }
    
    public RealElement getExposureIndex () {
        return (RealElement) getField (ELEM_EXINDEX);
    }
    
    public void setExposureIndex (Double d) throws XmlContentException {
        setField (ELEM_EXINDEX, d);
    }
    
    public StringElement getSensingMethod () {
        return (StringElement) getField (ELEM_SENSING);
    }
    
    public void setSensingMethod (String s) throws XmlContentException {
        setField (ELEM_SENSING, s);
    }
    
    public IntegerElement getCfaPattern () {
        return (IntegerElement) getField (ELEM_CFA);
    }
    
    public void setCfaPattern (Integer i) throws XmlContentException {
        setField (ELEM_CFA, i);
    }
    
    public StringElement getAutoFocus () {
        return (StringElement) getField (ELEM_AUTOFOCUS);
    }
    
    public void setAutoFocus (String s) throws XmlContentException {
        setField (ELEM_AUTOFOCUS, s);
    }
    
    public PrintAspectRatio getPrintAspectRatio () {
        return (PrintAspectRatio) getField (ELEM_ASPECT);
    }
    
    public void setPrintAspectRatio (PrintAspectRatio p) throws XmlContentException {
        setField (ELEM_ASPECT, p);
    }
    
    
    
    
    @Override
    public void output(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
        SMOutputElement mainElem = parent.addElement(ns, name);
        outputGenericElement (ns, ELEM_FNUMBER, mainElem);
        outputGenericElement (ns, ELEM_EXTIME, mainElem);
        outputGenericElement (ns, ELEM_EXPROG, mainElem);
        outputGenericElement (ns, ELEM_SPECSENS, mainElem);
        outputGenericElement (ns, ELEM_ISOSPEED, mainElem);
        outputGenericElement (ns, ELEM_OECF, mainElem);
        outputGenericElement (ns, ELEM_EXIFVSN, mainElem);
        outputGenericElement (ns, ELEM_SHUTTERSPEED, mainElem);
        outputGenericElement (ns, ELEM_APERTURE, mainElem);
        outputGenericElement (ns, ELEM_BRIGHT, mainElem);
        outputGenericElement (ns, ELEM_EXBIAS, mainElem);
        outputGenericElement (ns, ELEM_MAXAPERTURE, mainElem);
        outputGenericElement (ns, ELEM_SUBJDIST, mainElem);
        outputGenericElement (ns, ELEM_METERMODE, mainElem);
        outputGenericElement (ns, ELEM_SOURCE, mainElem);
        outputGenericElement (ns, ELEM_FLASH, mainElem);
        outputGenericElement (ns, ELEM_FOCALLENGTH, mainElem);
        outputGenericElement (ns, ELEM_FLASHENERGY, mainElem);
        outputGenericElement (ns, ELEM_BACKLIGHT, mainElem);
        outputGenericElement (ns, ELEM_EXINDEX, mainElem);
        outputGenericElement (ns, ELEM_SENSING, mainElem);
        outputGenericElement (ns, ELEM_CFA, mainElem);
        outputGenericElement (ns, ELEM_AUTOFOCUS, mainElem);
        outputGenericElement (ns, ELEM_ASPECT, mainElem);
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
                localName, ELEM_FNUMBER,
                RealElement.class)) {
            return;
        }
        if (match (reader, this, 
                localName, ELEM_EXTIME,
                RealElement.class)) {
            return;
        }
        if (match (reader, this, 
                localName, ELEM_EXPROG,
                StringElement.class)) {
            return;
        }
        if (match (reader, this, 
                localName, ELEM_SPECSENS,
                StringElement.class)) {
            return;
        }
        if (match (reader, this, 
                localName, ELEM_ISOSPEED,
                IntegerElement.class)) {
            return;
        }
        if (match (reader, this, 
                localName, ELEM_OECF,
                RationalElement.class)) {
            return;
        }
        if (match (reader, this, 
                localName, ELEM_EXIFVSN,
                StringElement.class)) {
            return;
        }
        if (match (reader, this, 
                localName, ELEM_SHUTTERSPEED,
                RationalElement.class)) {
            return;
        }
        if (match (reader, this, 
                localName, ELEM_APERTURE,
                RationalElement.class)) {
            return;
        }
        if (match (reader, this, 
                localName, ELEM_BRIGHT,
                RationalElement.class)) {
            return;
        }
        if (match (reader, this, 
                localName, ELEM_EXBIAS,
                RationalElement.class)) {
            return;
        }
        if (match (reader, this, 
                localName, ELEM_MAXAPERTURE,
                RationalElement.class)) {
            return;
        }
        if (match (reader, this, 
                localName, ELEM_SUBJDIST,
                SubjectDistance.class)) {
            return;
        }
        if (match (reader, this, 
                localName, ELEM_METERMODE,
                StringElement.class)) {
            return;
        }
        if (match (reader, this, 
                localName, ELEM_SOURCE,
                StringElement.class)) {
            return;
        }
        if (match (reader, this, 
                localName, ELEM_FLASH,
                StringElement.class)) {
            return;
        }
        if (match (reader, this, 
                localName, ELEM_FOCALLENGTH,
                RealElement.class)) {
            return;
        }
        if (match (reader, this, 
                localName, ELEM_FLASHENERGY,
                RationalElement.class)) {
            return;
        }
        if (match (reader, this, 
                localName, ELEM_BACKLIGHT,
                StringElement.class)) {
            //StringElement elem = (StringElement) getFieldElement (ELEM_BACKLIGHT);
            return;
        }
        if (match (reader, this, 
                localName, ELEM_EXINDEX,
                RealElement.class)) {
            return;
        }
        if (match (reader, this, 
                localName, ELEM_SENSING,
                StringElement.class)) {
            return;
        }
        if (match (reader, this, 
                localName, ELEM_CFA,
                IntegerElement.class)) {
            return;
        }
        
        if (match (reader, this,
                localName, ELEM_ASPECT,
                PrintAspectRatio.class)) {
            return;
        }
        throw new XmlContentException ("Unknown element name " + localName);
    }

}
