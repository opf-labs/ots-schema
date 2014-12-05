/**
 * @author Gary McGath
 *
 * Copyright (c) 
 * 2010 by the President and Fellows of Harvard College
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or (at
 * your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 * USA.
 *
 * Contact information
 *
 * Office for Information Systems
 * Harvard University Library
 * Harvard University
 * Cambridge, MA  02138
 * (617)495-3724
 * hulois@hulmail.harvard.edu
 */
package edu.harvard.hul.ois.ots.schemas.AES;

import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.codehaus.staxmate.out.SMNamespace;
import org.codehaus.staxmate.out.SMOutputElement;

import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

public class CylinderStructure extends PhysicalStructure {

    private final static String ELEMENTNAME = "cylinderStructure";

    private final static String ELEM_SUBSTRATE = "substrateMaterialLayer";
    private final static String ELEM_FILLER = "fillerLayer";
    private final static String ELEM_SURFACE = "surfaceLayer";

    private final static String ATT_BRAND = "stockBrand";

    
    public CylinderStructure (XMLStreamReader reader) 
            throws XMLStreamException, XmlContentException {
        this.name = ELEMENTNAME;
        parse (reader);
    }

    /** Constructor with no arguments. */
    public CylinderStructure () {
        super();
        this.name = ELEMENTNAME;
    }

    public Layer getSubstrateMaterialLayer () {
        return (Layer) getField (ELEM_SUBSTRATE);
    }
    
    public void setSubstrateMaterialLayer (Layer ly) throws XmlContentException {
        setField (ELEM_SUBSTRATE, ly);
    }
    
    @SuppressWarnings("unchecked")
	public List<Layer> getFillerLayers () {
        return genericList (getList(ELEM_FILLER));
    }
    
    public void addFillerLayer (Layer ly) throws XmlContentException {
        addToList (ly, ELEM_FILLER);
    }
    
    public Layer getSurfaceLayer () {
        return (Layer) getField (ELEM_SURFACE);
    }
    
    public void setSurfaceLayer (Layer ly) throws XmlContentException {
        setField (ELEM_SURFACE, ly);
    }
    
    public String getStockBrand () {
        return getAttribute(ATT_BRAND);
    }
    
    public void setStockBrand (String b) {
        setAttribute (ATT_BRAND, b);
    }

    @Override
    public boolean validate() {
        return true;
    }

    @Override
    public void output(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
        SMOutputElement mainElem = outputElement (ns, name, parent); 
        outputGenericElement (ns, ELEM_SUBSTRATE, mainElem);
        outputList (ns, ELEM_FILLER, mainElem);
        outputGenericElement (ns, ELEM_SURFACE, mainElem);
    }
    
    /** Classes must implement this to handle START_ELEMENT cases.  */
    @Override
    protected void dispatch (String localName, XMLStreamReader reader) 
               throws XMLStreamException, XmlContentException {
        if (match (reader, this, 
                localName, ELEM_SUBSTRATE,
                Layer.class))
            return;
        if (matchList (reader, this,
                localName, ELEM_FILLER,
                Layer.class))
            return;
        if (match (reader, this,
                localName, ELEM_SURFACE,
                Layer.class))
            return;
        throw new XmlContentException ("Unknown element name " + localName);
    }


}
