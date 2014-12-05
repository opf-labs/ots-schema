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

import edu.harvard.hul.ois.ots.schemas.XmlContent.IntegerElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.Restriction;
import edu.harvard.hul.ois.ots.schemas.XmlContent.StringElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

public class FaceRegion extends AESObject {

    private final static String ELEMENTNAME = "region";

    private final static String ELEM_TIMERANGE = "timeRange";
    private final static String ELEM_NUMCHANNELS = "numChannels";
    private final static String ELEM_CONDNOTE = "conditionNote";
    private final static String ELEM_SECURITYNOTE = "securityNote";
    private final static String ELEM_STREAM = "stream";

    private final static String ATT_FMTREF = "formatRef";
    private final static String ATT_FACEREF = "faceRef";
    private final static String ATT_LABEL = "label";
    
    /**  Constructor for this element
     */
    public FaceRegion (XMLStreamReader reader) 
           throws XMLStreamException, XmlContentException {
        this.name = ELEMENTNAME;
        defineRestrictions ();
        parse (reader);
    }
    
    /**  Constructor for this element
     */
    public FaceRegion (XMLStreamReader reader, String name) 
           throws XMLStreamException, XmlContentException {
        this.name = name;
        parse (reader);
    }
    

    /** Constructor with content. */
    public FaceRegion () {
        this.name = ELEMENTNAME;
        defineRestrictions ();
    }

    /** Define restrictions */
    private void defineRestrictions () {
        addRestriction(ELEM_NUMCHANNELS, Restriction.NONNEGATIVE);
    }

    public TimeRange getTimeRange () {
        return (TimeRange) getField (ELEM_TIMERANGE);
    }
    
    /** Set the timeRange field. The TimeRange object must have
     *  the name "timeRange". 
     * @throws XmlContentException */
    public void setTimeRange (TimeRange tr) throws XmlContentException {
        setField (ELEM_TIMERANGE, tr);
    }
    
    public IntegerElement getNumChannels () {
        return (IntegerElement) getField (ELEM_NUMCHANNELS);
    }
    
    public void setNumChannels (int n) throws XmlContentException {
        setField (ELEM_NUMCHANNELS, n);
    }
    
    @SuppressWarnings("unchecked")
	public List<ConditionNote> getConditionNotes () {
        return genericList (getList(ELEM_CONDNOTE));
    }
    
    public void addConditionNote (ConditionNote cn) throws XmlContentException {
        addToList (cn, ELEM_CONDNOTE);
    }
    
    @SuppressWarnings("unchecked")
	public List<StringElement> getSecurityNotes () {
        return genericList (getList (ELEM_SECURITYNOTE));
    }
    
    public void addSecurityNote (String s) throws XmlContentException {
        addToList (new StringElement(s, ELEM_SECURITYNOTE), ELEM_SECURITYNOTE);
    }
    
    @SuppressWarnings("unchecked")
	public List<Stream> getStreams () {
        return genericList (getList (ELEM_STREAM));
    }
    
    public void addStream (Stream s) throws XmlContentException {
        addToList (s, ELEM_STREAM);
    }
    
    public String getFormatRef () {
        return getAttribute (ATT_FMTREF);
    }

    public void setFormatRef (String s) {
        setAttribute (ATT_FMTREF, s);
    }
    
    public String getFaceRef () {
        return getAttribute (ATT_FACEREF);
    }

    public void setFaceRef (String s) {
        setAttribute (ATT_FACEREF, s);
    }
    
    public String getLabel () {
        return getAttribute (ATT_LABEL);
    }

    public void setLabel (String s) {
        setAttribute (ATT_LABEL, s);
    }
    

    
    @Override
    public boolean validate() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void output(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
        SMOutputElement mainElem = outputElement (ns, name, parent); 
        outputGenericElement (ns, ELEM_TIMERANGE, mainElem);
        outputGenericElement (ns, ELEM_NUMCHANNELS, mainElem);
        outputList(ns, ELEM_CONDNOTE, mainElem);
        outputList(ns, ELEM_SECURITYNOTE, mainElem);
        outputList(ns, ELEM_STREAM, mainElem);

    }

    protected void dispatch(String localName, XMLStreamReader reader)
            throws XMLStreamException, XmlContentException {
        if (match(reader, this, localName, ELEM_TIMERANGE, TimeRange.class))
            return;
        if (match(reader, this, localName, ELEM_NUMCHANNELS, IntegerElement.class))
            return;
        if (matchList(reader, this, localName, ELEM_CONDNOTE,
                ConditionNote.class))
            return;
        if (matchList(reader, this, localName, ELEM_SECURITYNOTE,
                StringElement.class))
            return;
        if (matchList(reader, this, localName, ELEM_STREAM,
                Stream.class))
            return;
        throw new XmlContentException("Unknown element name " + localName);
    }
    
}
