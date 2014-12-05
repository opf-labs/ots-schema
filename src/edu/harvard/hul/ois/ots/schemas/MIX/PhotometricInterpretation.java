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

import edu.harvard.hul.ois.ots.schemas.XmlContent.StringElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

public class PhotometricInterpretation extends MixElement {

    private final static String ELEM_COLORSPACE = "colorSpace";
    private final static String ELEM_COLORPROFILE = "ColorProfile";
    private final static String ELEM_YCBCR = "YCbCr";
    private final static String ELEM_REFBW = "ReferenceBlackWhite";

    /**  Constructor for this element
     */
    public PhotometricInterpretation (XMLStreamReader reader) 
           throws XMLStreamException, XmlContentException {
        name = "PhotometricInterpretation";
        parse (reader);
    }
    
    /** No-argument constructor */
    public PhotometricInterpretation () {
        super();
        name = "PhotometricInterpretation";
    }
    
    
    /** Returns the colorSpace */
    public StringElement getColorSpace () {
        return (StringElement) getField (ELEM_COLORSPACE);
    }
    
    /** Sets the colorSpace 
     * @throws XmlContentException */
    public void setColorSpace (String cs) throws XmlContentException {
        setField (ELEM_COLORSPACE, cs);
    }
    
    /** Returns the colorProfile */
    public ColorProfile getColorProfile () {
        return (ColorProfile) getField (ELEM_COLORPROFILE);
    }
    
    /** Sets the colorProfile 
     * @throws XmlContentException */
    public void setColorProfile (ColorProfile cp) throws XmlContentException {
        setField (ELEM_COLORPROFILE, cp);
    }

    /** Returns the YCbCr */
    public YCbCr getYCbCr () {
        return (YCbCr) getField (ELEM_YCBCR);
    }
    
    /** Sets the YCbCr 
     * @throws XmlContentException */
    public void setYCbCr (YCbCr cp) throws XmlContentException {
        setField (ELEM_YCBCR, cp);
    }

    /** Returns the ReferenceBlackWhite list */
    @SuppressWarnings("unchecked")
	public List<ReferenceBlackWhite> getReferenceBlackWhites () {
        return (List<ReferenceBlackWhite>) genericList(getList (ELEM_REFBW));
    }
    
    /** Add a ReferenceBlackWhite
     * @throws XmlContentException */
    public void addReferenceBlackWhite (ReferenceBlackWhite cp) throws XmlContentException {
        addToList (cp, ELEM_REFBW);
    }


    /** Output method which adds to a parent element.*/
    @Override
    public void output(SMNamespace ns, SMOutputElement parent) throws XMLStreamException {
        SMOutputElement mainElem = parent.addElement(ns, name);
        outputGenericElement (ns, ELEM_COLORSPACE, mainElem);
        outputGenericElement (ns, ELEM_COLORPROFILE, mainElem);
        outputGenericElement (ns, ELEM_YCBCR, mainElem);
        outputGenericElement (ns, ELEM_REFBW, mainElem);
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
                localName, ELEM_COLORSPACE,
                StringElement.class))
            return;
        if (match (reader, this,
                localName, ELEM_COLORPROFILE,
                ColorProfile.class))
            return;
        if (match (reader, this,
                localName, ELEM_YCBCR,
                YCbCr.class))
            return;
        if (matchList (reader, this,
                localName, ELEM_REFBW,
                ReferenceBlackWhite.class))
            return;
        throw new XmlContentException ("Unknown element name " + localName);
    }

}
