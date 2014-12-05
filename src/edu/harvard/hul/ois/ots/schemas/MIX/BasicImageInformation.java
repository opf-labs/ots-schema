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

public class BasicImageInformation extends MixElement {

    private final static String ELEM_BASIC = "BasicImageCharacteristics";
    private final static String ELEM_SPECIAL = "SpecialFormatCharacteristics";
    
    /**  Constructor for this element
     */
    public BasicImageInformation (XMLStreamReader reader) 
           throws XMLStreamException, XmlContentException {
        name = "BasicImageInformation";
        parse (reader);
    }
    
    /** No-argument constructor */
    public BasicImageInformation () {
        super();
        name = "BasicImageInformation";
    }
    
    /** Output method which adds to a parent element.*/
    @Override
    public void output(SMNamespace ns, SMOutputElement parent) throws XMLStreamException {
        SMOutputElement mainElem = outputElement (ns, name, parent); 
        BasicImageCharacteristics bic = getBasicImageCharacteristics();
        if (bic != null) {
            bic.output (ns, mainElem);
        }
        SpecialFormatCharacteristics sfc = getSpecialFormatCharacteristics();
        if (sfc != null) {
            sfc.output (ns, mainElem);
        }
    }


    @Override
    public boolean validate() {
        // TODO Auto-generated method stub
        return false;
    }
    
    /** Returns the BasicImageCharacteristics. */
    public BasicImageCharacteristics getBasicImageCharacteristics () {
        return (BasicImageCharacteristics) getField (ELEM_BASIC);   
    }
    
    /** Sets the BasicImageCharacteristics. */
    public void setBasicImageCharacteristics (BasicImageCharacteristics x) {
        fields.put (ELEM_BASIC, x);
    }
    
    /** Returns the SpecialFormatCharacteristics. */
    public SpecialFormatCharacteristics getSpecialFormatCharacteristics () {
        return (SpecialFormatCharacteristics) getField (ELEM_SPECIAL);   
    }
    
    /** Sets the SpecialFormatCharacteristics. */
    public void setSpecialFormatCharacteristics (SpecialFormatCharacteristics x) {
        fields.put (ELEM_SPECIAL, x);
    }

    /** Classes must implement this to handle START_ELEMENT cases.  */
    protected void dispatch (String localName, XMLStreamReader reader) 
                  throws XMLStreamException, XmlContentException {
        if (match (reader, this,
                localName, ELEM_BASIC,
                BasicImageCharacteristics.class))
            return;
        if (match (reader, this,
                localName, ELEM_SPECIAL,
                SpecialFormatCharacteristics.class))
            return;
        throw new XmlContentException ("Unknown element name " + localName);
    }

}
