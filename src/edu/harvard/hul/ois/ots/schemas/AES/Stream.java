/**********************************************************************
 * Copyright (c) 2010 by the President and Fellows of Harvard College
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
 **********************************************************************/

package edu.harvard.hul.ois.ots.schemas.AES;

import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.codehaus.staxmate.out.SMNamespace;
import org.codehaus.staxmate.out.SMOutputElement;

import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

public class Stream extends AESObject {

    private final static String ELEMENTNAME = "stream";
    
    private final static String ELEM_CHANASSIGN = "channelAssignment";
    private final static String ELEM_CONDNOTE = "conditionNote";
    
    private final static String ATT_LABEL = "label";
    private final static String ATT_FACERG = "faceRegionRef";

    /**  Constructor for this element
     */
    public Stream (XMLStreamReader reader) 
           throws XMLStreamException, XmlContentException {
        this.name = ELEMENTNAME;
        parse (reader);
    }
    
    /** Constructor with content. */
    public Stream () {
        this.name = ELEMENTNAME;
    }

    /**  Constructor for this element
     */
    public Stream (XMLStreamReader reader, String name) 
           throws XMLStreamException, XmlContentException {
        this.name = name;
        parse (reader);
    }

    /** Return the label attribute */
    public String getLabel () {
        try {
            return getAttribute (ATT_LABEL);
        }
        catch (Exception e) {
            return null;
        }
    }
    
    /** Set the label attribute.  */
    public void setLabel (String t) {
        setAttribute (ATT_LABEL, t);
    }
    
    public String getFaceRegionRef () {
        return getAttribute(ATT_FACERG);
    }
    
    public void setFaceRegionRef (String s) {
        setAttribute (ATT_FACERG, s);
    }

    public ChannelAssignment getChannelAssignment () {
        return (ChannelAssignment) getField (ELEM_CHANASSIGN);
    }
    
    public void setChannelAssignment (ChannelAssignment ca) throws XmlContentException {
        setField (ELEM_CHANASSIGN, ca);
    }
    
    @SuppressWarnings("unchecked")
	public List<ConditionNote> getConditionNotes () {
        return genericList (getList (ELEM_CONDNOTE));
    }
    
    public void addConditionNote (ConditionNote cn) throws XmlContentException {
        addToList (cn, ELEM_CONDNOTE);
    }

    @Override
    public void output(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
        SMOutputElement mainElem = outputElement (ns, name, parent); 
        outputGenericElement (ns, ELEM_CHANASSIGN, mainElem);
        outputList(ns, ELEM_CONDNOTE, mainElem);
    }

    @Override
    public boolean validate() {
        return true;
    }

    /** Classes must implement this to handle START_ELEMENT cases.  */
    @Override
    protected void dispatch (String localName, XMLStreamReader reader) 
               throws XMLStreamException, XmlContentException {
        if (match(reader, this, localName, ELEM_CHANASSIGN, ChannelAssignment.class))
            return;
        if (matchList(reader, this, localName, ELEM_CONDNOTE, ConditionNote.class))
            return;
        throw new XmlContentException ("Unknown element name " + localName);
    }

}
