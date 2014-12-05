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

public class Colormap extends MixElement {

    private final static String ELEM_REF = "colormapReference";
    private final static String ELEM_EMBED = "embeddedColormap";

    /**  Constructor from XML
     */
    public Colormap (XMLStreamReader reader) 
              throws XMLStreamException, XmlContentException {
        defineRestrictions();
        parse(reader);
    }
    
    /** No-argument constructor */
    public Colormap () {
        super();
        defineRestrictions();
    }
    
    private void defineRestrictions () {
    }


    /** Returns the colormapReference */
    public StringElement getColormapReference () {
        return (StringElement) getField (ELEM_REF);
    }
    
    /** Sets the colormapReference 
     * @throws XmlContentException */
    public void setColormapReference (String s) throws XmlContentException {
        setField (ELEM_REF, s);
    }

    /** Returns the embeddedColormap */
    public StringElement getEmbeddedColormap () {
        return (StringElement) getField (ELEM_EMBED);
    }
    
    /** Sets the colormapReference 
     * @throws XmlContentException */
    public void setEmbeddedColormap (String s) throws XmlContentException {
        setField (ELEM_EMBED, s);
    }
    
    @Override
    public void output(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
        SMOutputElement mainElem = parent.addElement(ns, name);
        outputGenericElement (ns, ELEM_REF, mainElem);
        outputGenericElement (ns, ELEM_EMBED, mainElem);
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
                localName, ELEM_REF,
                StringElement.class)) {
            return;
        }
        if (match (reader, this,
                localName, ELEM_EMBED,
                StringElement.class))
            return;
        throw new XmlContentException ("Unknown element name " + localName);
    }

}
