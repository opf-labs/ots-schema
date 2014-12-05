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

public class SpecialFormatCharacteristics extends MixElement {

    private final static String ELEM_JPEG2000 = "JPEG2000";
    private final static String ELEM_MRSID = "MrSID";
    private final static String ELEM_DJVU = "Djvu";

    /**  Constructor from XML
     */
    public SpecialFormatCharacteristics (XMLStreamReader reader) 
             throws XMLStreamException, XmlContentException {
        name = "SpecialFormatCharacteristics";
        parse(reader);
    }
    
    /** No-argument constructor */
    public SpecialFormatCharacteristics () {
        super();
        name = "SpecialFormatCharacteristics";
    }



    /** Returns the JPEG2000 element */
    public JPEG2000 getJPEG2000 () {
        return (JPEG2000) getField (ELEM_JPEG2000);
    }
    
    /** Sets the JPEG2000 element */
    public void setJPEG2000 (JPEG2000 j) {
        fields.put (ELEM_JPEG2000, j);
    }
    
    /** Returns the MrSid element. MrSid elements aren't supported,
     *  but shouldn't cause trouble if included. */
    public MrSID getMrSID () {
        return (MrSID) getField (ELEM_MRSID);
    }
    
    /** Sets the MrSID element */
    public void setMrSID (MrSID m) {
        fields.put (ELEM_MRSID, m);
    }
    
    /** Returns the Djvu element. Djvu elements aren't supported,
     *  but shouldn't cause trouble if included. */
    public Djvu getDjvu () {
        return (Djvu) getField (ELEM_DJVU);
    }
    
    /** Sets the MrDjvu element */
    public void setDjvu (Djvu d) {
        fields.put (ELEM_DJVU, d);
    }

    /** Output method which adds to a parent element.*/
    @Override
    public void output(SMNamespace ns, SMOutputElement parent) throws XMLStreamException {
        SMOutputElement mainElem = parent.addElement(ns, name);
        outputGenericElement (ns, ELEM_JPEG2000, mainElem);
        outputGenericElement (ns, ELEM_MRSID, mainElem);
        outputGenericElement (ns, ELEM_DJVU, mainElem);
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
        if (JPEG2000.match (reader, this,
                localName, ELEM_JPEG2000,
                JPEG2000.class))
            return;
        if (MrSID.match (reader, this,
                localName, ELEM_MRSID,
                MrSID.class))
            return;
        if (Djvu.match (reader, this,
                localName, ELEM_DJVU,
                Djvu.class))
            return;
        throw new XmlContentException ("Unknown element name " + localName);
    }

}
