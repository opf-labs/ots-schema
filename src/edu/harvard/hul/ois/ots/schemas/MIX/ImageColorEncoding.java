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
import edu.harvard.hul.ois.ots.schemas.XmlContent.Restriction;
import edu.harvard.hul.ois.ots.schemas.XmlContent.StringElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

public class ImageColorEncoding extends MixElement {

    private final static String ELEM_BPS = "BitsPerSample";
    private final static String ELEM_SPP = "samplesPerPixel";
    private final static String ELEM_EXTRA = "extraSamples";
    private final static String ELEM_COLORMAP = "Colormap";
    private final static String ELEM_GRC = "grayResponseCurve";
    private final static String ELEM_GRU = "grayResponseUnit";
    private final static String ELEM_WP = "WhitePoint";
    private final static String ELEM_CHROM = "PrimaryChromaticities";

    /* Permitted values for extraSamples */
    private final static String[] VALUES_EXTRA = 
    { "unspecified data",
     "associated alpha data (with pre-multiplied color)",
     "unassociated alpha data",
     "range or depth data"};

    /* Permitted values for grayResponseUnit */
    private final static String[] VALUES_GRU = 
    { "Number represents tenths of a unit",
      "Number represents hundredths of a unit",
      "Number represents thousandths of a unit",
      "Number represents ten-thousandths of a unit",
      "Number represents hundred-thousandths of a unit"};

    /**  Constructor from XML
     */
    public ImageColorEncoding (XMLStreamReader reader) 
              throws XMLStreamException, XmlContentException {
        name = "ImageColorEncoding";
        defineRestrictions();
        parse(reader);
    }
    
    /** No-argument constructor */
    public ImageColorEncoding () {
        super();
        name = "ImageColorEncoding";
        defineRestrictions();
    }

    /** Define restrictions */
    private void defineRestrictions () {
        addRestriction(ELEM_SPP, Restriction.NONNEGATIVE);
        addRestriction(ELEM_EXTRA, VALUES_EXTRA);
        addRestriction(ELEM_GRU, VALUES_GRU);
        addRestriction(ELEM_GRC, Restriction.NONNEGATIVE);
    }


    public BitsPerSample getBitsPerSample () {
        return (BitsPerSample) getField (ELEM_BPS);
    }
    
    public void setBitsPerSample (BitsPerSample bps) throws XmlContentException {
        setField (ELEM_BPS, bps);
    }
    
    public IntegerElement getSamplesPerPixel () {
        return (IntegerElement) getField (ELEM_SPP);
    }
    
    public void setSamplesPerPixel (int n) throws XmlContentException {
        setField (ELEM_SPP, n);
    }
    
    /** Returns a List of Strings for extraSamples elements */
    @SuppressWarnings("unchecked")
	public List<StringElement> getExtraSamples () {
        return genericList (getList (ELEM_EXTRA));
    }
    
    public void addExtraSamples (String s) throws XmlContentException {
        addToList (new StringElement (s, ELEM_EXTRA), ELEM_EXTRA);
//        StringElement xs = (StringElement) getFieldElement (ELEM_EXTRA);
//        xs.checkContent(VALUES_EXTRA);
    }
    
    public Colormap getColormap () {
        return (Colormap) getField (ELEM_COLORMAP);
    }
    
    public void setColormap (Colormap c) throws XmlContentException {
        setField (ELEM_COLORMAP, c);
    }
    
    /** Returns a List of grayResponseCurve objects. */
    @SuppressWarnings("unchecked")
	public List<Integer> getGrayResponseCurves () {
        return genericList( getList (ELEM_GRC));
    }
    
    /** Add a grayResponseCurve value. */
    public void addGrayResponseCurve (int grc) throws XmlContentException {
        //List<GenericElement> lst = getList (ELEM_GRC);
        //lst.add (new IntegerElement (grc, ELEM_GRC));
        addToList (new IntegerElement (grc, ELEM_GRC), ELEM_GRC);
    }
    
    /** Returns the grayResponseUnit */
    public StringElement getGrayResponseUnit () {
        return (StringElement) getField (ELEM_GRU);
    }
    
    /** Sets the grayResponseUnit 
     * @throws XmlContentException */
    public void setGrayResponseUnit (String g) throws XmlContentException {
        setField (ELEM_GRU, g);
//        StringElement elem = (StringElement) getFieldElement (ELEM_GRU);
//        elem.checkContent (VALUES_GRU);
    }

    /** Returns the WhitePoint */
    @SuppressWarnings("unchecked")
	public List<WhitePoint> getWhitePoints () {
        return genericList ( getList (ELEM_WP));
    }
    
    public void addWhitePoint (WhitePoint wp) throws XmlContentException {
        //List<GenericElement> lst = getList (ELEM_WP);
        addToList (wp, ELEM_WP);
    }
    
    /** Returns the PrimaryChromaticities. (I refuse to double-pluralize the name!) */
    @SuppressWarnings("unchecked")
	public List<PrimaryChromaticities> getPrimaryChromaticities () {
        return genericList ( getList (ELEM_CHROM));
    }
    
    public void addPrimaryChromaticities (PrimaryChromaticities pc) throws XmlContentException {
        //List<GenericElement> lst = getList (ELEM_CHROM);
        //lst.add (pc);
        addToList (pc, ELEM_CHROM);
    }

    @Override
    public void output(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
        SMOutputElement mainElem = parent.addElement(ns, name);
        outputGenericElement (ns, ELEM_BPS, mainElem);
        outputGenericElement (ns, ELEM_SPP, mainElem);
        outputList (ns, ELEM_EXTRA, mainElem);
        outputGenericElement (ns, ELEM_COLORMAP, mainElem);
        outputList (ns, ELEM_GRC, mainElem);
        outputGenericElement (ns, ELEM_GRU, mainElem);
        outputList (ns, ELEM_WP, mainElem);
        outputList (ns, ELEM_CHROM, mainElem);
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
                localName, ELEM_BPS, 
                BitsPerSample.class))
            return;
        if (match (reader, this,
                localName, ELEM_SPP,
                IntegerElement.class))
            return;
        if (matchList (reader, this,
                localName, ELEM_EXTRA,
                StringElement.class)) {
            return;
        }
        if (match (reader, this,
                localName, ELEM_COLORMAP,
                Colormap.class))
            return;
        if (matchList (reader, this,
                localName, ELEM_GRC,
                IntegerElement.class)) {
            return;
        }
        if (match (reader, this,
                localName, ELEM_GRU,
                StringElement.class))
            return;
        if (matchList (reader, this,
                localName, ELEM_WP,
                WhitePoint.class))
            return;
        if (matchList (reader, this,
                localName, ELEM_CHROM,
                PrimaryChromaticities.class))
            return;
    }

}
