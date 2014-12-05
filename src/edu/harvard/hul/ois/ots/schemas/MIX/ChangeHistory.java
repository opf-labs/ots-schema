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

import edu.harvard.hul.ois.ots.schemas.XmlContent.GenericElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.ListElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

public class ChangeHistory extends MixElement {

    private final static String ELEM_IMP = "ImageProcessing";
    private final static String ELEM_PREV = "PreviousImageMetadata";

    /**  Constructor for this element
     */
    public ChangeHistory (XMLStreamReader reader)
            throws XMLStreamException, XmlContentException {
        parse (reader);
        name = "ChangeHistory";
    }
    
    /** No-argument constructor */
    public ChangeHistory () {
        super();
        name = "ChangeHistory";
    }
    

    /** Output method which adds to a parent element.*/
    @Override
    public void output(SMNamespace ns, SMOutputElement parent) throws XMLStreamException {
        SMOutputElement mainElem = parent.addElement(ns, name);
        ListElement imp = (ListElement) getFieldElement (ELEM_IMP);
        if (imp != null)
            imp.output (ns, mainElem);
        ListElement prev = (ListElement) getFieldElement (ELEM_PREV);
        if (prev != null)
            prev.output (ns, mainElem);
    }
    
    @Override
    public boolean validate() {
        // TODO Auto-generated method stub
        return false;
    }

    /** Returns a List of ImageProcessing objects. */
    @SuppressWarnings("unchecked")
	public List<ImageProcessing> getImageProcessings () {
        return (List<ImageProcessing>)
              genericList( getList (ELEM_IMP));
    }
    
    /** Sets the list of ImageProcessing objects. This replaces any previous list. */
    public void addImageProcessing (ImageProcessing imp) {
        List<GenericElement> lst = getList(ELEM_IMP);
        lst.add (imp);
    }
    
    /** Returns PreviousImageMetadata. This is undefined in the schema
     *  and is intended to hold an earlier copy of a whole MIX structure,
     *  which would make the whole thing recursive; for the present we
     *  merely note the presence of the element. 
     */
    public PreviousImageMetadata getPreviousImageMetadata () {
        return (PreviousImageMetadata) getField (ELEM_PREV);
    }

    /** Classes must implement this to handle START_ELEMENT cases.  */
    protected void dispatch (String localName, XMLStreamReader reader) 
                  throws XMLStreamException, XmlContentException {
        if (matchList (reader, this,
                localName, ELEM_IMP,
                ImageProcessing.class))
            return;
        if (match (reader, this,
                localName, ELEM_PREV,
                PreviousImageMetadata.class))
            return;
        throw new XmlContentException ("Unknown element name " + localName);
    }

}
