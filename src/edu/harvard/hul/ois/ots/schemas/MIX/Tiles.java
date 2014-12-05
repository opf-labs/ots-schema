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
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

/** Tiles is a subelement of EncodingOptions */
public class Tiles extends MixElement {

    private final static String ELEM_WIDTH = "tileWidth";
    private final static String ELEM_HEIGHT = "tileHeight";

    /**  Constructor from XML
     */
    public Tiles (XMLStreamReader reader) 
             throws XMLStreamException, XmlContentException {
        name = "Tiles";
        parse(reader);
    }
    
    /** No-argument constructor */
    public Tiles () {
        super();
        name = "Tiles";
    }

    /** Returns the tileWidth */
    public IntegerElement getTileWidth () {
        return (IntegerElement) getField (ELEM_WIDTH);
    }
    
    /** Sets the tileWidth 
     * @throws XmlContentException */
    public void setTileWidth (Integer w) throws XmlContentException {
        setField (ELEM_WIDTH, w);
    }

    /** Returns the tileHeight */
    public IntegerElement getTileHeight () {
        return (IntegerElement) getField (ELEM_HEIGHT);
    }
    
    /** Sets the tileHeight 
     * @throws XmlContentException */
    public void setTileHeight (Integer h) throws XmlContentException {
        setField (ELEM_HEIGHT, h);
    }

    /** Output method which adds to a parent element.*/
    @Override
    public void output(SMNamespace ns, SMOutputElement parent) throws XMLStreamException {
        SMOutputElement mainElem = parent.addElement(ns, name);
        outputGenericElement (ns, ELEM_WIDTH, mainElem);
        outputGenericElement (ns, ELEM_HEIGHT, mainElem);
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
                localName, ELEM_WIDTH,
                IntegerElement.class))
            return;
        if (match (reader, this,
                localName, ELEM_HEIGHT,
                IntegerElement.class))
            return;
        throw new XmlContentException ("Unknown element name " + localName);
    }

}
