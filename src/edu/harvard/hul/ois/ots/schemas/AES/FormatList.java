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

/** The formatRegion element is based on the abstract baseFormatRegionType,
 *  which is a characteristic trick of AES metadata. This is apparently
 *  intentional, and it means that we have to grab the xsd:type
 *  attribute to figure out what it really is.
 */
public class FormatList extends AESObject {

    private final static String ELEMENTNAME = "formatList";

    private final static String ELEM_FMTREGION = "formatRegion";

    /**  Constructor for this element
     */
    public FormatList (XMLStreamReader reader) 
           throws XMLStreamException, XmlContentException {
        this.name = ELEMENTNAME;
        parse (reader);
    }
    
    /** Constructor with no arguments. */
    public FormatList () {
        this.name = ELEMENTNAME;
    }

    @SuppressWarnings("unchecked")
	public List<FormatRegion> getFormatRegions () {
        return genericList( getList (ELEM_FMTREGION));
    }

    public void addFormatRegion (FormatRegion r) throws XmlContentException {
        addToList (r, ELEM_FMTREGION);
    }

    @Override
    public boolean validate() {
        return true;
    }

    @Override
    public void output(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
        SMOutputElement mainElem = outputElement (ns, name, parent); 
        outputList (ns, ELEM_FMTREGION, mainElem);
    }

    /** Classes must implement this to handle START_ELEMENT cases.  */
    @Override
    protected void dispatch (String localName, XMLStreamReader reader) 
               throws XMLStreamException, XmlContentException {
        if (matchList(reader, this, localName, ELEM_FMTREGION, FormatRegion.class))
            return;
        throw new XmlContentException ("Unknown element name " + localName);
    }

}
