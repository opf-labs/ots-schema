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

public class OpticalStructure extends PhysicalStructure {
    
    private final static String ELEMENTNAME = "opticalStructure";
    
    private final static String ELEM_SUBSTRATE = "substrateMaterialLayer";
    private final static String ELEM_BINDER = "binderLayer";
    private final static String ELEM_REFLECTIVE = "reflectiveLayer";
    private final static String ELEM_DATA = "dataLayer";
    private final static String ELEM_PROTECTIVE = "protectiveLayer";

    private final static String ATT_STOCKBRAND = "stockBrand";
    
    public OpticalStructure (XMLStreamReader reader) 
        throws XMLStreamException, XmlContentException {
        this.name = ELEMENTNAME;
        parse (reader);
    }

    /** Constructor with no arguments. */
    public OpticalStructure () {
        super();
        this.name = ELEMENTNAME;
    }

    @SuppressWarnings("unchecked")
	public List<Layer> getSubstrateMaterialLayers () {
        return genericList (getList (ELEM_SUBSTRATE));
    }
    
    public void addSubstrateMaterialLayer (Layer ly) throws XmlContentException {
        addToList (ly, ELEM_SUBSTRATE);
    }
    
    public Layer getBinderLayer () {
        return (Layer) getField (ELEM_BINDER);
    }
    
    public void setBinderLayer (Layer ly) throws XmlContentException {
        setField (ELEM_BINDER, ly);
    }
    
    public Layer getDataLayer () {
        return (Layer) getField (ELEM_DATA);
    }
    
    public void setDataLayer (Layer ly) throws XmlContentException {
        setField (ELEM_DATA, ly);
    }
    
    @SuppressWarnings("unchecked")
	public List<Layer> getReflectiveLayers () {
        return genericList (getList (ELEM_REFLECTIVE));
    }
        
    public void addReflectiveLayer (Layer ly) throws XmlContentException {
        addToList (ly, ELEM_REFLECTIVE);
    }
    
    @SuppressWarnings("unchecked")
	public List<Layer> getProtectiveLayers () {
        return genericList (getList (ELEM_PROTECTIVE));
    }
    
    public void addProtectiveLayer (Layer ly) throws XmlContentException {
        addToList (ly, ELEM_PROTECTIVE);
    }
    
    public String getStockBrand () {
        return getAttribute (ATT_STOCKBRAND);
    }
    
    public void setStockBrand (String s) {
        setAttribute (ATT_STOCKBRAND, s);
    }
    
    @Override
    public boolean validate() {
        return true;
    }

    @Override
    public void output(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
        SMOutputElement mainElem = outputElement (ns, name, parent); 
        outputList (ns, ELEM_SUBSTRATE, mainElem);
        outputGenericElement (ns, ELEM_BINDER, mainElem);
        outputList (ns, ELEM_REFLECTIVE, mainElem);
        outputGenericElement (ns, ELEM_DATA, mainElem);
        outputList (ns, ELEM_PROTECTIVE, mainElem);

    }

    /** Classes must implement this to handle START_ELEMENT cases.  */
    @Override
    protected void dispatch (String localName, XMLStreamReader reader) 
               throws XMLStreamException, XmlContentException {
        if (matchList (reader, this,
                localName, ELEM_SUBSTRATE,
                Layer.class))
            return;
        if (match (reader, this,
                localName, ELEM_BINDER,
                Layer.class))
            return;
        if (matchList (reader, this,
                localName, ELEM_REFLECTIVE,
                Layer.class))
            return;
        if (match (reader, this,
                localName, ELEM_DATA,
                Layer.class))
            return;
        if (matchList (reader, this,
                localName, ELEM_PROTECTIVE,
                Layer.class))
            return;
        throw new XmlContentException ("Unknown element name " + localName);
    }

}
