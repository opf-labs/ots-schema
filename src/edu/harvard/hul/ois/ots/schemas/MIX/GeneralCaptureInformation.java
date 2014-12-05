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

import java.util.List;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.codehaus.staxmate.out.SMNamespace;
import org.codehaus.staxmate.out.SMOutputElement;

import edu.harvard.hul.ois.ots.schemas.XmlContent.DateElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.StringElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

public class GeneralCaptureInformation extends MixElement {

    private static final String ELEM_CREATED = "dateTimeCreated";
    private static final String ELEM_PRODUCER = "imageProducer";
    private static final String ELEM_DEVICE = "captureDevice";

    /* Permitted values for messageDigestAlgorithm */
    private final static String[] VALUES_DEVICE = 
    { "transmission scanner",
      "reflection print scanner",
      "digital still camera",
      "still from video"};

    /**  Constructor from XML
     */
    public GeneralCaptureInformation (XMLStreamReader reader) 
             throws XMLStreamException, XmlContentException {
    	name = "GeneralCaptureInformation";
        defineRestrictions();
        parse(reader);
    }
    
    /** No-argument constructor */
    public GeneralCaptureInformation () {
        super();
        name = "GeneralCaptureInformation";
        defineRestrictions();
    }

    /** Define restrictions */
    private void defineRestrictions () {
        addRestriction(ELEM_DEVICE, VALUES_DEVICE);
    }
    
    public DateElement getDateTimeCreated () {
        return (DateElement) getField (ELEM_CREATED);
    }
    
    public void setDateTimeCreated (String s) throws XmlContentException {
        setField (ELEM_CREATED, new DateElement (s, ELEM_CREATED));
    }
    
    @SuppressWarnings("unchecked")
	public List<StringElement> getImageProducers () {
        return genericList(getList (ELEM_PRODUCER));
    }
    
    public void addImageProducer (String p) throws XmlContentException {
        addToList(new StringElement (p, ELEM_PRODUCER), ELEM_PRODUCER);
    }
    
    public StringElement getCaptureDevice () {
        return (StringElement) getField (ELEM_DEVICE);
    }
    
    public void setCaptureDevice (String s) throws XmlContentException {
        setField (ELEM_DEVICE, s);
    }
    
    
    @Override
    public void output(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
        SMOutputElement mainElem = parent.addElement(ns, name);
        outputGenericElement (ns, ELEM_CREATED, mainElem);
        outputList (ns, ELEM_PRODUCER, mainElem);
        outputGenericElement (ns, ELEM_DEVICE, mainElem);
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
                localName, ELEM_CREATED, 
                DateElement.class))
            return;
        if (matchList (reader, this,
                localName, ELEM_PRODUCER,
                StringElement.class))
            return;
        if (match (reader, this,
                localName, ELEM_DEVICE,
                StringElement.class)) {
            return;
        }
        throw new XmlContentException ("Unknown element name " + localName);
    }

}
