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

/** EncodingOptions is a subelement of JPEG2000 */
public class EncodingOptions extends MixElement {

    private final static String ELEM_TILES = "Tiles";
    private final static String ELEM_QL = "qualityLayers";
    private final static String ELEM_RL = "resolutionLevels";

    /**  Constructor from XML
     */
    public EncodingOptions (XMLStreamReader reader) 
             throws XMLStreamException, XmlContentException {
    	name = "EncodingOptions";
        parse(reader);
    }
    
    /** No-argument constructor */
    public EncodingOptions () {
        super();
        name = "EncodingOptions";
    }


    /** Output method which adds to a parent element.*/
    @Override
    public void output(SMNamespace ns, SMOutputElement parent) throws XMLStreamException {
        SMOutputElement mainElem = parent.addElement(ns, name);
        outputGenericElement (ns, ELEM_TILES, mainElem);
        outputGenericElement (ns, ELEM_QL, mainElem);
        outputGenericElement (ns, ELEM_RL, mainElem);
//        Tiles tiles = getTiles ();
//        if (tiles != null) {
//            tiles.output (ns, mainElem);
//        }
//        IntegerElement ql = (IntegerElement) getFieldElement(ELEM_QL);
//        if (ql != null) {
//            ql.output (ns, mainElem);
//        }
//        IntegerElement rl = (IntegerElement) getFieldElement(ELEM_RL);
//        if (rl != null) {
//            rl.output (ns, mainElem);
//        }
    }

    @Override
    public boolean validate() {
        // TODO Auto-generated method stub
        return false;
    }

    /** Returns the Tiles */
    public Tiles getTiles () {
        return (Tiles) getField (ELEM_TILES);
    }
    
    /** Sets the qualityLayers 
     * @throws XmlContentException */
    public void setTiles (Tiles ql) throws XmlContentException {
        setField (ELEM_TILES, ql);
    }

    /** Returns the qualityLayers */
    public IntegerElement getQualityLayers () {
        return (IntegerElement) getField (ELEM_QL);
    }
    
    /** Sets the qualityLayers 
     * @throws XmlContentException */
    public void setQualityLayers (Integer ql) throws XmlContentException {
        setField (ELEM_QL, ql);
    }

    /** Returns the resolutionLevels */
    public IntegerElement getResolutionLevels () {
        return (IntegerElement) getField (ELEM_RL);
    }
    
    /** Sets the resolutionLevels 
     * @throws XmlContentException */
    public void setResolutionLevels (Integer rl) throws XmlContentException {
        setField (ELEM_RL, rl);
    }

    /** Classes must implement this to handle START_ELEMENT cases.  */
    protected void dispatch (String localName, XMLStreamReader reader) 
                  throws XMLStreamException, XmlContentException {
        if (match (reader, this,
                localName, ELEM_TILES,
                Tiles.class))
            return;
        if (IntegerElement.match (reader, this,
                localName, ELEM_QL,
                IntegerElement.class))
            return;
        if (IntegerElement.match (reader, this,
                localName, ELEM_RL,
                IntegerElement.class))
            return;
        throw new XmlContentException ("Unknown element name " + localName);
    }


}
