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

import edu.harvard.hul.ois.ots.schemas.XmlContent.DateElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.StringElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

public class ImageProcessing extends MixElement {

    private final static String ELEM_DTP = "dateTimeProcessed";
    private final static String ELEM_SD = "sourceData";
    private final static String ELEM_AGENCY = "processingAgency";
    private final static String ELEM_RATIONALE = "processingRationale";
    private final static String ELEM_SOFTWARE = "ProcessingSoftware";
    private final static String ELEM_ACTIONS = "processingActions";

    /**  Constructor from XML
     */
    public ImageProcessing (XMLStreamReader reader) 
             throws XMLStreamException, XmlContentException {
        name = "ImageProcessing";
        parse(reader);
    }
    
    /** No-argument constructor */
    public ImageProcessing () {
        super();
        name = "ImageProcessing";
    }

    
    public StringElement getDateTimeProcessed () {
        return (StringElement) getField (ELEM_DTP);
    }
    
    public void setDateTimeProcessed (String dt) throws XmlContentException {
        //TODO should validate
        setField (ELEM_DTP, dt);
    }
    
    public StringElement getSourceData () {
        return (StringElement) getField (ELEM_SD);
    }
    
    public void setSourceData (String sd) throws XmlContentException {
        setField (ELEM_SD, sd);
    }
    
    public StringElement getProcessingAgency () {
        return (StringElement) getField (ELEM_AGENCY);
    }
    
    public void setProcessingAgency (String a) throws XmlContentException {
        setField (ELEM_AGENCY, a);
    }
    
    public StringElement getProcessingRationale () {
        return (StringElement) getField (ELEM_RATIONALE);
    }
    
    public void setProcessingRationale (String a) throws XmlContentException {
        setField (ELEM_RATIONALE, a);
    }
    
    public ProcessingSoftware getProcessingSoftware () {
        return (ProcessingSoftware) getField (ELEM_SOFTWARE);
    }
    
    public void setProcessingSoftware (String s) throws XmlContentException {
        setField (ELEM_SOFTWARE, s);
    }

    public StringElement getProcessingActions () {
        return (StringElement) getField (ELEM_ACTIONS);
    }

    public void setProcessingActions (String a) throws XmlContentException {
        setField (ELEM_ACTIONS, a);
    }
    

    @Override
    public void output(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
        SMOutputElement mainElem = parent.addElement(ns, name);
        outputGenericElement (ns, ELEM_DTP, mainElem);
        outputGenericElement (ns, ELEM_SD, mainElem);
        outputList (ns, ELEM_AGENCY, mainElem);
        outputGenericElement (ns, ELEM_RATIONALE, mainElem);
        outputList (ns, ELEM_SOFTWARE, mainElem);

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
                localName, ELEM_DTP,
                DateElement.class))
            return;
        if (match(reader, this,
                localName, ELEM_SD,
                StringElement.class))
            return;
        if (match(reader, this,
                localName, ELEM_AGENCY,
                StringElement.class))
            return;
        if (match(reader, this,
                localName, ELEM_RATIONALE,
                StringElement.class))
            return;
        if (match(reader, this,
                localName, ELEM_SOFTWARE,
                StringElement.class))
            return;
        if (match(reader, this,
                localName, ELEM_ACTIONS,
                StringElement.class))
            return;
    }

}
