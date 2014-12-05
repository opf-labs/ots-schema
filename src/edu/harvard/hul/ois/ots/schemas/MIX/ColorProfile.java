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

public class ColorProfile extends MixElement {

    private final static String ELEM_ICCPROFILE = "IccProfile";
    private final static String ELEM_LOCALPROFILE = "LocalProfile";
    private final static String ELEM_EMBEDDEDPROFILE = "embeddedProfile";

    /**  Constructor for this element
     */
    public ColorProfile (XMLStreamReader reader) 
           throws XMLStreamException, XmlContentException {
    	name = "ColorProfile";
        defineRestrictions();
        parse (reader);
    }
    
    /** No-argument constructor */
    public ColorProfile () {
        super();
        name = "ColorProfile";
        defineRestrictions();
    }
    
    /** Define restrictions */
    private void defineRestrictions () {
    }

    /** Returns the IccProfile */
    public IccProfile getIccProfile () {
        return (IccProfile) getField (ELEM_ICCPROFILE);
    }
    
    /** Sets the IccProfile 
     * @throws XmlContentException */
    public void setIccProfile (IccProfile prof) throws XmlContentException {
        setField (ELEM_ICCPROFILE, prof);
    }

    /** Returns the localProfile */
    public LocalProfile getLocalProfile () {
        return (LocalProfile) getField (ELEM_LOCALPROFILE);
    }
    
    /** Sets the localProfile 
     * @throws XmlContentException */
    public void setLocalProfile (LocalProfile prof) throws XmlContentException {
        setField (ELEM_ICCPROFILE, prof);
    }

    /** Returns the embeddedProfile as a base64 encoded String */
    public StringElement getEmbeddedProfile () {
        return (StringElement) getField (ELEM_EMBEDDEDPROFILE);
    }
    
    /** Sets the embeddedProfile. The argument must be a base64 
     *  encoded String. 
     * @throws XmlContentException */
    public void setEmbeddedProfile (String prof) throws XmlContentException {
        setField (ELEM_EMBEDDEDPROFILE, prof);
    }
    
    /** Output method which adds to a parent element.*/
    @Override
    public void output(SMNamespace ns, SMOutputElement parent) throws XMLStreamException {
        SMOutputElement mainElem = parent.addElement(ns, name);
        //IccProfile icc = getIccProfile ();
        outputGenericElement (ns, ELEM_ICCPROFILE, mainElem);
        outputGenericElement (ns, ELEM_LOCALPROFILE, mainElem);
        outputGenericElement (ns, ELEM_EMBEDDEDPROFILE, mainElem);
//        if (icc != null) {
//            icc.output (ns, mainElem);
//        }
//        LocalProfile loc = getLocalProfile();
//        if (loc != null) {
//            loc.output (ns, mainElem);
//        }
//        String emb = getEmbeddedProfile();
//        if (emb != null) {
//            mainElem.addElementWithCharacters(ns, ELEM_EMBEDDEDPROFILE, emb);
//        }
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
                localName, ELEM_ICCPROFILE,
                IccProfile.class))
            return;
        if (match (reader, this,
                localName, ELEM_LOCALPROFILE,
                LocalProfile.class))
            return;
        if (match (reader, this,
                localName, ELEM_EMBEDDEDPROFILE,
                StringElement.class))
            return;
        throw new XmlContentException ("Unknown element name " + localName);
    }

}
