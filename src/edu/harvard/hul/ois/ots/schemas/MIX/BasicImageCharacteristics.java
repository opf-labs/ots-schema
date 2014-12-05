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

import edu.harvard.hul.ois.ots.schemas.XmlContent.IntegerElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.Restriction;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

public class BasicImageCharacteristics extends MixElement {

    private final static String ELEM_WIDTH = "imageWidth";
    private final static String ELEM_HEIGHT = "imageHeight";
    private final static String ELEM_INTERP = "PhotometricInterpretation";

    /**  Constructor for this element
     */
    public BasicImageCharacteristics (XMLStreamReader reader) 
           throws XMLStreamException, XmlContentException {
        name = "BasicImageCharacteristics";
        defineRestrictions();
        parse (reader);
    }
    
    /** No-argument constructor */
    public BasicImageCharacteristics () {
        super();
        name = "BasicImageCharacteristics";
        defineRestrictions();
    }
    
    /** Define restrictions */
    private void defineRestrictions () {
        addRestriction(ELEM_WIDTH, Restriction.NONNEGATIVE);
        addRestriction(ELEM_HEIGHT, Restriction.NONNEGATIVE);
    }
    
    /** Returns the imageWidth field. Returns -1 if missing. */
    public IntegerElement getImageWidth () {
        return (IntegerElement) getField (ELEM_WIDTH);
    }
    
    /** Sets the imageWidth field. 
     * @throws XmlContentException */
    public void setImageWidth (int w) throws XmlContentException {
        setField (ELEM_WIDTH, w);

    }
    
    /** Returns the imageHeight field.  */
    public IntegerElement getImageHeight () {
        return (IntegerElement) getField (ELEM_HEIGHT);
    }

    /** Sets the imageHeight field. 
     * @throws XmlContentException */
    public void setImageHeight (int h) throws XmlContentException {
        setField (ELEM_HEIGHT, h);
    }

    /** Returns the PhotometricInterpretation field. */
    public PhotometricInterpretation getPhotometricInterpretation () {
        return (PhotometricInterpretation) fields.get (ELEM_INTERP);
    }
    
    /** Sets the PhotometricInterpretation field. */
    public void setPhotometricInterpetation (PhotometricInterpretation p) {
        fields.put (ELEM_INTERP, p);
    }
    
    /** Output method which adds to a parent element.*/
    @Override
    public void output(SMNamespace ns, SMOutputElement parent) throws XMLStreamException {
        SMOutputElement mainElem = parent.addElement(ns, name);
        outputGenericElement (ns, ELEM_WIDTH, mainElem);
        outputGenericElement (ns, ELEM_HEIGHT, mainElem);
        outputGenericElement (ns, ELEM_INTERP, mainElem);
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
                localName, ELEM_WIDTH,
                IntegerElement.class))
            return;
        if (match (reader, this,
                localName, ELEM_HEIGHT,
                IntegerElement.class))
            return;
        if (match (reader, this,
                localName, ELEM_INTERP,
                PhotometricInterpretation.class))
            return;
        throw new XmlContentException ("Unknown element name " + localName);
    }


}
