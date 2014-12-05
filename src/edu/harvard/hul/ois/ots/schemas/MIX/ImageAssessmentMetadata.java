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

/** ImageAssessmentMetadata is a top-level subelement of MIX. */
public class ImageAssessmentMetadata extends MixElement {

    private final static String ELEM_SM = "SpatialMetrics";
    private final static String ELEM_ICE = "ImageColorEncoding";
    private final static String ELEM_TD = "TargetData";

    /**  Constructor for this element
     */
    public ImageAssessmentMetadata (XMLStreamReader reader)
            throws XMLStreamException, XmlContentException {
        name = "ImageAssessmentMetadata";
        parse (reader);
    }
    
    /** No-argument constructor */
    public ImageAssessmentMetadata () {
        super();
        name = "ImageAssessmentMetadata";
    }
    

    public SpatialMetrics getSpatialMetrics () {
        return (SpatialMetrics) getField (ELEM_SM);
    }
    
    public void setSpatialMetrics (SpatialMetrics sm) throws XmlContentException {
        setField (ELEM_SM, sm);
    }
    
    public ImageColorEncoding getImageColorEncoding () {
        return (ImageColorEncoding) getField (ELEM_ICE);
    }
    
    public void setImageColorEncoding (ImageColorEncoding ice) throws XmlContentException {
        setField (ELEM_ICE, ice);
    }
    
    public TargetData getTargetData () {
        return (TargetData) getField (ELEM_TD);
    }
    
    public void setTargetData (TargetData td) throws XmlContentException {
        setField (ELEM_TD, td);
    }
    
    /** Output method which adds to a parent element.*/
    @Override
    public void output(SMNamespace ns, SMOutputElement parent) throws XMLStreamException {
        SMOutputElement mainElem = parent.addElement(ns, name);
        outputGenericElement (ns, ELEM_SM, mainElem);
        outputGenericElement (ns, ELEM_ICE, mainElem);
        outputGenericElement (ns, ELEM_TD, mainElem);
    }

    @Override
    public boolean validate() {
        // TODO Auto-generated method stub
        return false;
    }

    /** Classes must implement this to handle START_ELEMENT cases.  */
    protected void dispatch (String localName, XMLStreamReader reader) 
               throws XMLStreamException, XmlContentException {
        if (SpatialMetrics.match (reader, this,
                localName, ELEM_SM,
                SpatialMetrics.class)) {
            return;
        }
        if (ImageColorEncoding.match(reader, this,
                localName, ELEM_ICE,
                ImageColorEncoding.class)) {
            return;
        }
        if (TargetData.match(reader, this,
                localName, ELEM_TD,
                TargetData.class))
            return;
        throw new XmlContentException ("Unknown element name " + localName);
    }

}
